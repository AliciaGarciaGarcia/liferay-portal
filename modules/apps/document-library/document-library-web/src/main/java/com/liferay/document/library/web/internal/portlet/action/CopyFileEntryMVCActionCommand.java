/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.NoSuchEntryException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.service.DepotEntryGroupRelLocalService;
import com.liferay.depot.service.DepotEntryLocalService;
import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Galluzzi
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/copy_file_entry"
	},
	service = MVCActionCommand.class
)
public class CopyFileEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		try {
			_copyFileEntry(actionRequest, actionResponse);
		}
		catch (IOException ioException) {
			_log.error(ioException);

			throw new PortalException(ioException);
		}
	}

	private void _checkDestinationRepository(Group group)
		throws PortalException {

		if ((group != null) && group.isStaged() && !group.isStagingGroup()) {
			throw new PortalException(
				"cannot-copy-file-entries-to-the-live-version-of-a-group");
		}
	}

	private void _copyFileEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
		long destinationFolderId = ParamUtil.getLong(
			actionRequest, "destinationFolderId");
		long destinationRepositoryId = ParamUtil.getLong(
			actionRequest, "destinationRepositoryId");

		try {
			Group group = _groupLocalService.fetchGroup(
				destinationRepositoryId);

			_checkDestinationRepository(group);

			ServiceContext serviceContext = _createServiceContext(
				actionRequest, fileEntryId, group);

			_dlAppService.copyFileEntry(
				fileEntryId, destinationFolderId, destinationRepositoryId,
				serviceContext);

			JSONPortletResponseUtil.writeJSON(
				actionRequest, actionResponse, _jsonFactory.createJSONObject());
		}
		catch (PortalException portalException) {
			String errorMessage = themeDisplay.translate(
				portalException.getMessage());

			JSONPortletResponseUtil.writeJSON(
				actionRequest, actionResponse,
				JSONUtil.put("errorMessage", errorMessage));

			hideDefaultSuccessMessage(actionRequest);
		}
	}

	private ServiceContext _createServiceContext(
			ActionRequest actionRequest, long fileEntryId, Group group)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DLFileEntry.class.getName(), actionRequest);

		try {
			List<Long> currentAndAncestorSiteGroupsIds = ListUtil.fromArray(
				_portal.getCurrentAndAncestorSiteGroupIds(group.getGroupId()));

			List<Long> groupIds = _relatedGroupIds(
				currentAndAncestorSiteGroupsIds, group);

			DepotEntry groupDepotEntry = null;

			if (group.isDepot()) {
				groupDepotEntry = _depotEntryLocalService.fetchGroupDepotEntry(
					group.getGroupId());
			}

			Set<Long> categoryIds = new HashSet<>();

			AssetEntry assetEntry = _assetEntryService.getEntry(
				DLFileEntry.class.getName(), fileEntryId);

			for (long categoryId : assetEntry.getCategoryIds()) {
				if (_isCategoryIdAllowed(
						categoryId, groupIds, group, groupDepotEntry)) {

					categoryIds.add(categoryId);
				}
			}

			serviceContext.setAssetCategoryIds(
				ArrayUtil.toLongArray(categoryIds));

			Set<String> tagNames = new HashSet<>();

			for (String tagName : assetEntry.getTagNames()) {
				if (_isTagNameAllowed(groupIds, tagName)) {
					tagNames.add(tagName);
				}
			}

			serviceContext.setAssetTagNames(ArrayUtil.toStringArray(tagNames));
		}
		catch (PortalException portalException) {
			if (!(portalException instanceof NoSuchEntryException)) {
				throw portalException;
			}
		}

		return serviceContext;
	}

	private boolean _isCategoryIdAllowed(
			long categoryId, List<Long> groupsIds, Group group,
			DepotEntry groupDepotEntry)
		throws PortalException {

		AssetCategory assetCategory = _assetCategoryService.fetchCategory(
			categoryId);

		if (assetCategory == null) {
			return false;
		}

		if (groupsIds.contains(assetCategory.getGroupId())) {
			return true;
		}

		if (group.isDepot()) {
			if (groupDepotEntry == null) {
				return false;
			}

			DepotEntryGroupRel depotEntryGroupRel =
				_depotEntryGroupRelLocalService.
					fetchDepotEntryGroupRelByDepotEntryIdToGroupId(
						groupDepotEntry.getDepotEntryId(),
						assetCategory.getGroupId());

			if (depotEntryGroupRel != null) {
				return true;
			}

			return false;
		}

		return false;
	}

	private boolean _isTagNameAllowed(
		List<Long> groupIds, String tagName) {

		for (Long groupId : groupIds) {
			AssetTag assetTag = _assetTagLocalService.fetchTag(
				groupId, tagName);

			if (assetTag != null) {
				return true;
			}
		}

		return false;
	}

	private List<Long> _relatedGroupIds(
			List<Long> currentAndAncestorSiteGroupsIds, Group group)
		throws PortalException {

		DepotEntry groupDepotEntry =
			_depotEntryLocalService.fetchGroupDepotEntry(group.getGroupId());

		if (groupDepotEntry != null) {
			return currentAndAncestorSiteGroupsIds;
		}

		List<DepotEntryGroupRel> depotEntryGroupRels =
			_depotEntryGroupRelLocalService.getDepotEntryGroupRels(
				group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (ListUtil.isEmpty(depotEntryGroupRels)) {
			return currentAndAncestorSiteGroupsIds;
		}

		List<Long> relatedGroupIds = new ArrayList<>(
			currentAndAncestorSiteGroupsIds);

		for (DepotEntryGroupRel depotEntryGroupRel : depotEntryGroupRels) {
			DepotEntry depotEntry = _depotEntryLocalService.getDepotEntry(
				depotEntryGroupRel.getDepotEntryId());

			relatedGroupIds.add(depotEntry.getGroupId());
		}

		return relatedGroupIds;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CopyFileEntryMVCActionCommand.class);

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private AssetEntryService _assetEntryService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private DepotEntryGroupRelLocalService _depotEntryGroupRelLocalService;

	@Reference
	private DepotEntryLocalService _depotEntryLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}