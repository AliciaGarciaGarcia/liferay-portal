/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action.util;

import com.liferay.portal.kernel.url.URLBuilder;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alicia García
 */
public class RedirectUtil {

	public static String clearSearchParameters(
		String destination, String redirect, String target) {

		String portletName = _getPortletName(redirect);

		if (Validator.isNull(portletName)) {
			return redirect;
		}

		return URLBuilder.create(
			redirect
		).removeParameter(
			portletName + "displayStyle"
		).removeParameter(
			portletName + "keywords"
		).removeParameter(
			portletName + "searchFolderId"
		).removeParameter(
			portletName + "searchRepositoryId"
		).removeParameter(
			portletName + "showSearchInfo"
		).setParameter(
			portletName + target, destination
		).build();
	}

	private static String _getPortletName(String url) {
		String pattern =
			"(?<=\\?|&)([^&]+)" +
				"(?=(assetCategoryId|assetTagId|extension|keywords))";

		Pattern regexPattern = Pattern.compile(pattern);

		Matcher matcher = regexPattern.matcher(url);

		if (!matcher.find()) {
			return null;
		}

		return matcher.group(1);
	}

}