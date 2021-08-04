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

import com.liferay.info.item.provider.InfoItemWorkflowProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceServiceUtil;
import com.liferay.translation.constants.TranslationPortletKeys;
import com.liferay.translation.exporter.TranslationInfoItemFieldValuesExporter;
import com.liferay.translation.exporter.TranslationInfoItemFieldValuesExporterTracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge González
 */
public class ExportTranslationDisplayContext {

	public ExportTranslationDisplayContext(
		long classNameId, long classPK, long groupId,
		HttpServletRequest httpServletRequest,
		InfoItemWorkflowProvider<Object> infoItemWorkflowProvider,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, Object model,
		String title,
		TranslationInfoItemFieldValuesExporterTracker
			translationInfoItemFieldValuesExporterTracker) {

		_classNameId = classNameId;
		_classPK = classPK;
		_groupId = groupId;
		_httpServletRequest = httpServletRequest;
		_infoItemWorkflowProvider = infoItemWorkflowProvider;
		_liferayPortletResponse = liferayPortletResponse;
		_model = model;
		_title = title;
		_translationInfoItemFieldValuesExporterTracker =
			translationInfoItemFieldValuesExporterTracker;

		_themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getExportTranslationData()
		throws PortalException {

		ResourceURL exportTranslationURL =
			_liferayPortletResponse.createResourceURL(
				TranslationPortletKeys.TRANSLATION);

		exportTranslationURL.setParameter(
			"groupId", String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID));
		exportTranslationURL.setParameter(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(Layout.class.getName())));
		exportTranslationURL.setResourceID("/translation/export_translation");

		ResourceURL getExportTranslationAvailableLocalesURL =
			_liferayPortletResponse.createResourceURL(
				TranslationPortletKeys.TRANSLATION);

		getExportTranslationAvailableLocalesURL.setParameter(
			"groupId", String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID));
		getExportTranslationAvailableLocalesURL.setParameter(
			"classNameId",
			String.valueOf(PortalUtil.getClassNameId(Layout.class.getName())));
		getExportTranslationAvailableLocalesURL.setResourceID(
			"/translation/get_export_translation_available_locales");

		return HashMapBuilder.<String, Object>put(
			"availableExportFileFormats",
			() -> {
				Collection<TranslationInfoItemFieldValuesExporter>
					translationInfoItemFieldValuesExporters =
						_translationInfoItemFieldValuesExporterTracker.
							getTranslationInfoItemFieldValuesExporters();

				Stream<TranslationInfoItemFieldValuesExporter>
					translationInfoItemFieldValuesExporterStream =
						translationInfoItemFieldValuesExporters.stream();

				return translationInfoItemFieldValuesExporterStream.map(
					this::_getExportFileFormatJSONObject
				).collect(
					Collectors.toList()
				);
			}
		).put(
			"availableTargetLocales",
			_getLocalesJSONArray(
				_themeDisplay.getLocale(),
				LanguageUtil.getAvailableLocales(
					_themeDisplay.getSiteGroupId()))
		).put(
			"experiences", _getExperiences()
		).put(
			"exportTranslationURL", exportTranslationURL.toString()
		).put(
			"getExportTranslationAvailableLocalesURL",
			getExportTranslationAvailableLocalesURL.toString()
		).put(
			"pathModule", PortalUtil.getPathModule()
		).build();
	}

	public String getRedirect() {
		if (Validator.isNotNull(_redirect)) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		return _redirect;
	}

	public String getTitle() throws PortalException {
		return _title;
	}

	private List<Map<String, String>> _getExperiences() throws PortalException {
		if (!Objects.equals(
				PortalUtil.getClassName(_classNameId),
				Layout.class.getName())) {

			return null;
		}

		List<SegmentsExperience> segmentsExperiences =
			SegmentsExperienceServiceUtil.getSegmentsExperiences(
				_groupId, PortalUtil.getClassNameId(Layout.class.getName()),
				_classPK, true);

		boolean addedDefault = false;

		HashMap<String, String> defaultExperience = HashMapBuilder.put(
			"label",
			SegmentsExperienceConstants.getDefaultSegmentsExperienceName(
				_themeDisplay.getLocale())
		).put(
			"value",
			String.valueOf((Object)SegmentsExperienceConstants.ID_DEFAULT)
		).build();

		List<Map<String, String>> experiences = new ArrayList<>();

		for (SegmentsExperience segmentsExperience : segmentsExperiences) {
			if ((segmentsExperience.getPriority() <
					SegmentsExperienceConstants.PRIORITY_DEFAULT) &&
				!addedDefault) {

				experiences.add(defaultExperience);

				addedDefault = true;
			}

			experiences.add(
				HashMapBuilder.put(
					"label",
					segmentsExperience.getName(_themeDisplay.getLocale())
				).put(
					"value",
					String.valueOf(segmentsExperience.getSegmentsExperienceId())
				).build());
		}

		if (!addedDefault) {
			experiences.add(defaultExperience);
		}

		return experiences;
	}

	private JSONObject _getExportFileFormatJSONObject(
		TranslationInfoItemFieldValuesExporter
			translationInfoItemFieldValuesExporter) {

		InfoLocalizedValue<String> labelInfoLocalizedValue =
			translationInfoItemFieldValuesExporter.getLabelInfoLocalizedValue();

		return JSONUtil.put(
			"displayName",
			labelInfoLocalizedValue.getValue(_themeDisplay.getLocale())
		).put(
			"mimeType", translationInfoItemFieldValuesExporter.getMimeType()
		);
	}

	private JSONArray _getLocalesJSONArray(
		Locale currentLocale, Collection<Locale> locales) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		locales.forEach(
			locale -> jsonArray.put(
				JSONUtil.put(
					"displayName", locale.getDisplayName(currentLocale)
				).put(
					"languageId", LocaleUtil.toLanguageId(locale)
				)));

		return jsonArray;
	}

	private final long _classNameId;
	private final long _classPK;
	private final long _groupId;
	private final HttpServletRequest _httpServletRequest;
	private final InfoItemWorkflowProvider<Object> _infoItemWorkflowProvider;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final Object _model;
	private String _redirect;
	private final ThemeDisplay _themeDisplay;
	private final String _title;
	private final TranslationInfoItemFieldValuesExporterTracker
		_translationInfoItemFieldValuesExporterTracker;

}