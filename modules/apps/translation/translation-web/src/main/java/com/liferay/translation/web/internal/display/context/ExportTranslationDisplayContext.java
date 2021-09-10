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

package com.liferay.translation.web.internal.display.context;

import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.translation.exporter.TranslationInfoItemFieldValuesExporterTracker;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge González
 */
public class ExportTranslationDisplayContext {

	public ExportTranslationDisplayContext(
		long classNameId, long classPK,
		long groupId, HttpServletRequest httpServletRequest,
		InfoItemServiceTracker infoItemServiceTracker,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, Object model,
		String title,
		TranslationInfoItemFieldValuesExporterTracker
			translationInfoItemFieldValuesExporterTracker) {

		_classNameId = classNameId;

		_className = PortalUtil.getClassName(_classNameId);

		_classPK = classPK;
		_groupId = groupId;
		_httpServletRequest = httpServletRequest;
		_infoItemServiceTracker = infoItemServiceTracker;
		_liferayPortletResponse = liferayPortletResponse;
		_model = model;
		_title = title;
		_translationInfoItemFieldValuesExporterTracker =
			translationInfoItemFieldValuesExporterTracker;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	private final String _className;
	private final long _classNameId;
	private final long _classPK;
	private final long _groupId;
	private final HttpServletRequest _httpServletRequest;
	private final InfoItemServiceTracker _infoItemServiceTracker;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final Object _model;
	private final ThemeDisplay _themeDisplay;
	private final String _title;
	private final TranslationInfoItemFieldValuesExporterTracker
		_translationInfoItemFieldValuesExporterTracker;

}