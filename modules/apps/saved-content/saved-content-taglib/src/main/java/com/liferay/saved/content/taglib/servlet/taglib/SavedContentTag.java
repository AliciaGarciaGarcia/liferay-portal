/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saved.content.taglib.servlet.taglib;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.saved.content.model.SavedContentEntry;
import com.liferay.saved.content.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Alicia Garcia
 */
public class SavedContentTag extends IncludeTag {

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public String getContentTitle() {
		return _contentTitle;
	}

	public long getGroupId() {
		return _groupId;
	}

	public SavedContentEntry getSavedContentEntry() {
		return _savedContentEntry;
	}

	public void setClassName(String className) {
		if (className.equals(DLFileEntry.class.getName()) ||
			className.equals(FileEntry.class.getName()) ||
			className.equals(LiferayFileEntry.class.getName())) {

			className = DLFileEntryConstants.getClassName();
		}

		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setContentTitle(String contentTitle) {
		_contentTitle = contentTitle;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setInTrash(boolean inTrash) {
		_inTrash = inTrash;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	public void setSavedContentEntry(SavedContentEntry savedContentEntry) {
		_savedContentEntry = savedContentEntry;

		_setSavedContentEntry = true;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_className = null;
		_classPK = 0;
		_contentTitle = null;
		_groupId = 0;
		_inTrash = null;
		_savedContentEntry = null;
		_setSavedContentEntry = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		try {
		}
		catch (Exception exception) {
			_log.error(exception);
		}
	}

	private SavedContentEntry _getSavedContentEntry(ThemeDisplay themeDisplay) {
		if (!_setSavedContentEntry) {
			return com.liferay.saved.content.service.
				SavedContentEntryLocalServiceUtil.fetchSavedContentEntry(
					_groupId, themeDisplay.getUserId(), _className, _classPK);
		}

		return _savedContentEntry;
	}

	private boolean _isEnabled(ThemeDisplay themeDisplay, boolean inTrash) {
		if (!inTrash) {
			Group group = themeDisplay.getSiteGroup();

			if (!group.isStagingGroup() && !group.isStagedRemotely()) {
				return true;
			}
		}

		return false;
	}

	private boolean _isInTrash() throws PortalException {
		if (_inTrash == null) {
			TrashHandler trashHandler =
				TrashHandlerRegistryUtil.getTrashHandler(_className);

			if (trashHandler == null) {
				return false;
			}

			return trashHandler.isInTrash(_classPK);
		}

		return _inTrash;
	}

	private static final String _PAGE = "/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		SavedContentTag.class);

	private String _className;
	private long _classPK;
	private String _contentTitle;
	private long _groupId;
	private Boolean _inTrash;
	private SavedContentEntry _savedContentEntry;
	private boolean _setSavedContentEntry;

}