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

package com.liferay.document.library.internal.model.listener;

import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.staging.StagingGroupHelper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(service = ModelListener.class)
public class DLFileEntryFriendlyURLModelListener
	extends BaseModelListener<DLFileEntryFriendlyURL> {

	@Override
	public void onAfterCreate(DLFileEntryFriendlyURL dlFileEntryFriendlyURL)
		throws ModelListenerException {

		if (!ExportImportThreadLocal.isImportInProcess()) {
			_addFriendlyURLEntry(dlFileEntryFriendlyURL);
		}
	}

	@Override
	public void onAfterRemove(DLFileEntryFriendlyURL dlFileEntryFriendlyURL)
		throws ModelListenerException {

		try {
			if (!ExportImportThreadLocal.isImportInProcess()) {
				List<FriendlyURLEntry> friendlyURLEntries =
					_friendlyURLEntryLocalService.getFriendlyURLEntries(
						dlFileEntryFriendlyURL.getGroupId(),
						_classNameLocalService.getClassNameId(
							DLFileEntryFriendlyURL.class),
						dlFileEntryFriendlyURL.getPrimaryKey());

				for (FriendlyURLEntry friendlyURLEntry : friendlyURLEntries) {
					_friendlyURLEntryLocalService.deleteFriendlyURLEntry(
						friendlyURLEntry.getFriendlyURLEntryId());
				}
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onAfterUpdate(
			DLFileEntryFriendlyURL originalDLFileEntryFriendlyURL,
			DLFileEntryFriendlyURL dlFileEntryFriendlyURL)
		throws ModelListenerException {

		if (!ExportImportThreadLocal.isImportInProcess()) {
			_addFriendlyURLEntry(dlFileEntryFriendlyURL);
		}
	}

	private void _addFriendlyURLEntry(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		try {
			if (!_stagingGroupHelper.isLiveGroup(
					dlFileEntryFriendlyURL.getGroupId())) {

				String urlTitle =
					_friendlyURLEntryLocalService.getUniqueUrlTitle(
						dlFileEntryFriendlyURL.getGroupId(),
						_classNameLocalService.getClassNameId(
							DLFileEntryFriendlyURL.class),
						dlFileEntryFriendlyURL.getPrimaryKey(),
						dlFileEntryFriendlyURL.getFriendlyURL(),
						dlFileEntryFriendlyURL.getLanguageId());

				_friendlyURLEntryLocalService.addFriendlyURLEntry(
					dlFileEntryFriendlyURL.getGroupId(),
					_classNameLocalService.getClassNameId(
						DLFileEntryFriendlyURL.class),
					dlFileEntryFriendlyURL.getPrimaryKey(), urlTitle,
					ServiceContextThreadLocal.getServiceContext());
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private FriendlyURLEntryLocalService _friendlyURLEntryLocalService;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

}