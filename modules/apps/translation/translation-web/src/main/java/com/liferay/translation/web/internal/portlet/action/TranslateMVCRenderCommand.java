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

package com.liferay.translation.web.internal.portlet.action;

import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.item.provider.InfoItemLanguagesProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.info.item.provider.InfoItemPermissionProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.translation.constants.TranslationActionKeys;
import com.liferay.translation.constants.TranslationConstants;
import com.liferay.translation.constants.TranslationPortletKeys;
import com.liferay.translation.info.field.TranslationInfoFieldChecker;
import com.liferay.translation.model.TranslationEntry;
import com.liferay.translation.service.TranslationEntryLocalService;
import com.liferay.translation.web.internal.constants.TranslationWebConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ambrín Chaudhary
 * @author Alicia Garcia
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TranslationPortletKeys.TRANSLATION,
		"mvc.command.name=/translation/translate"
	},
	service = MVCRenderCommand.class
)
public class TranslateMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String className = ParamUtil.getString(renderRequest, "className");

			long classPK = ParamUtil.getLong(renderRequest, "classPK");

			InfoItemFormProvider<Object> infoItemFormProvider =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemFormProvider.class, className);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			InfoItemObjectProvider<Object> infoItemObjectProvider =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemObjectProvider.class, className);

			Object infoItem = infoItemObjectProvider.getInfoItem(classPK);

			InfoItemFieldValues sourceInfoItemFieldValues =
				_getSourceInfoItemFieldValues(className, infoItem);

			InfoItemLanguagesProvider<Object> infoItemLanguagesProvider =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemLanguagesProvider.class, className);

			String sourceLanguageId = ParamUtil.getString(
				renderRequest, "sourceLanguageId"
				,infoItemLanguagesProvider.getDefaultLanguageId(infoItem)
			);

			List<String> availableTargetLanguageIds =
				_getAvailableTargetLanguageIds(
					className, classPK, sourceLanguageId, themeDisplay);

			String targetLanguageId = ParamUtil.getString(
				renderRequest, "targetLanguageId",
				_getDefaultTargetLanguageId(availableTargetLanguageIds));

				List<String> availableSourceLanguageIds = Arrays.asList(
					infoItemLanguagesProvider.getAvailableLanguageIds(infoItem));

				renderRequest.setAttribute(
					TranslationWebConstants.AVAILABLE_SOURCE_LANGUAGE_IDS,
					availableSourceLanguageIds);
			renderRequest.setAttribute(
				TranslationWebConstants.AVAILABLE_TARGET_LANGUAGE_IDS,
				availableTargetLanguageIds);
			renderRequest.setAttribute(InfoDisplayWebKeys.INFO_ITEM, infoItem);
			renderRequest.setAttribute(
				InfoForm.class.getName(),
				infoItemFormProvider.getInfoForm(infoItem));
			renderRequest.setAttribute(
				TranslationWebConstants.SOURCE_INFO_ITEM_FIELD_VALUES,
				sourceInfoItemFieldValues);
			renderRequest.setAttribute(
				TranslationWebConstants.SOURCE_LANGUAGE_ID, sourceLanguageId);
			renderRequest.setAttribute(
				TranslationWebConstants.TARGET_INFO_ITEM_FIELD_VALUES,
				_getTargetInfoItemFieldValues(
					className, classPK, sourceInfoItemFieldValues,
					targetLanguageId));
			renderRequest.setAttribute(
				TranslationWebConstants.TARGET_LANGUAGE_ID, targetLanguageId);
			renderRequest.setAttribute(
				TranslationInfoFieldChecker.class.getName(),
				_translationInfoFieldChecker);

			return "/translate.jsp";
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	private List<String> _getAvailableTargetLanguageIds(
			String className, long classPK, String sourceLanguageId,
			ThemeDisplay themeDisplay)
		throws PortalException {

		InfoItemPermissionProvider infoItemPermissionProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemPermissionProvider.class, className);

		InfoItemReference infoItemReference = new InfoItemReference(
			className, classPK);

		boolean hasUpdatePermission = infoItemPermissionProvider.hasPermission(
			themeDisplay.getPermissionChecker(), infoItemReference,
			ActionKeys.UPDATE);

		Set<Locale> availableLocales = LanguageUtil.getAvailableLocales(
			themeDisplay.getSiteGroupId());

		Stream<Locale> stream = availableLocales.stream();

		return stream.map(
			LocaleUtil::toLanguageId
		).filter(
			languageId ->
				!Objects.equals(languageId, sourceLanguageId) &&
				(hasUpdatePermission ||
				 _hasTranslatePermission(languageId, themeDisplay))
		).collect(
			Collectors.toList()
		);
	}

	private String _getDefaultTargetLanguageId(
		List<String> availableTargetLanguageIds) {

		if (availableTargetLanguageIds.isEmpty()) {
			return StringPool.BLANK;
		}

		return availableTargetLanguageIds.get(0);
	}

	private InfoItemFieldValues _getSourceInfoItemFieldValues(
		String className, Object infoItem) {

		InfoItemFieldValuesProvider infoItemFieldValuesProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFieldValuesProvider.class, className);

		return infoItemFieldValuesProvider.getInfoItemFieldValues(infoItem);
	}

	private InfoItemFieldValues _getTargetInfoItemFieldValues(
			String className, long classPK,
			InfoItemFieldValues journalArticleInfoItemFieldValues,
			String targetLanguageId)
		throws PortalException {

		TranslationEntry translationEntry =
			_translationEntryLocalService.fetchTranslationEntry(
				className, classPK, targetLanguageId);

		if (translationEntry == null) {
			return journalArticleInfoItemFieldValues;
		}

		InfoItemFieldValues translationEntryInfoItemFieldValues =
			_translationEntryLocalService.getInfoItemFieldValues(
				translationEntry.getGroupId(), translationEntry.getClassName(),
				translationEntry.getClassPK(), translationEntry.getContent());

		Collection<InfoFieldValue<Object>> infoFieldValues =
			journalArticleInfoItemFieldValues.getInfoFieldValues();

		Stream<InfoFieldValue<Object>> stream = infoFieldValues.stream();

		return InfoItemFieldValues.builder(
		).infoItemReference(
			journalArticleInfoItemFieldValues.getInfoItemReference()
		).infoFieldValues(
			stream.map(
				infoFieldValue -> new InfoFieldValue<>(
					infoFieldValue.getInfoField(),
					GetterUtil.getObject(
						_getValue(
							translationEntryInfoItemFieldValues,
							infoFieldValue.getInfoField()),
						infoFieldValue.getValue()))
			).collect(
				Collectors.toList()
			)
		).build();
	}

	private Object _getValue(
		InfoItemFieldValues translationEntryInfoItemFieldValues,
		InfoField infoField) {

		InfoFieldValue<Object> infoFieldValue =
			translationEntryInfoItemFieldValues.getInfoFieldValue(
				infoField.getName());

		if (infoFieldValue != null) {
			return infoFieldValue.getValue();
		}

		return null;
	}

	private boolean _hasTranslatePermission(
		String languageId, ThemeDisplay themeDisplay) {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		String name = TranslationConstants.RESOURCE_NAME + "." + languageId;

		return permissionChecker.hasPermission(
			themeDisplay.getScopeGroup(), name, name,
			TranslationActionKeys.TRANSLATE);
	}

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private TranslationEntryLocalService _translationEntryLocalService;

	@Reference
	private TranslationInfoFieldChecker _translationInfoFieldChecker;

}