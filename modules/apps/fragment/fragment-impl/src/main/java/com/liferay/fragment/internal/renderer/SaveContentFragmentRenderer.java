/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.renderer;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.saved.content.taglib.servlet.taglib.SavedContentTag;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(service = FragmentRenderer.class)
public class SaveContentFragmentRenderer extends BaseContentFragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "content-display";
	}

	@Override
	public String getIcon() {
		return "bookmarks";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "save-content");
	}

	public boolean hasViewPermission(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest) {

		if (!FeatureFlagManagerUtil.isEnabled("LPS-197909")) {
			return false;
		}

		return true;
	}

	public boolean isSelectable(HttpServletRequest httpServletRequest) {
		if (!FeatureFlagManagerUtil.isEnabled("LPS-197909")) {
			return false;
		}

		return true;
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		SavedContentTag savedContentTag = new SavedContentTag();

		savedContentTag.isViewMode(fragmentRendererContext.isViewMode());

		Tuple displayObject = getDisplayObject(
			fragmentRendererContext, httpServletRequest);

		String className = GetterUtil.getString(displayObject.getObject(0));
		long classPK = GetterUtil.getLong(displayObject.getObject(1));

		savedContentTag.setClassName(className);

		savedContentTag.setClassPK(classPK);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		savedContentTag.setGroupId(themeDisplay.getScopeGroupId());

		savedContentTag.setContentTitle(
			_getAssetTitle(className, classPK, themeDisplay.getLocale()));

		savedContentTag.setInTrash(false);

		try {
			savedContentTag.doTag(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			_log.error("Unable to render content ratings fragment", exception);
		}
	}

	private String _getAssetTitle(
		String className, long classPK, Locale locale) {

		try {
			AssetRendererFactory<?> assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(className);

			if (assetRendererFactory == null) {
				return null;
			}

			AssetRenderer<?> assetRenderer =
				assetRendererFactory.getAssetRenderer(classPK);

			if (assetRenderer == null) {
				return null;
			}

			return assetRenderer.getTitle(locale);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get asset renderer with class primary key " +
						classPK,
					portalException);
			}

			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SaveContentFragmentRenderer.class);

	@Reference
	private Language _language;

}