/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action.util;

import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alicia García
 */
public class RedirectUtil {

	public static String clearSearchParameters(String redirect) {
		String portletName = _getPortletName(redirect);

		if (Validator.isNull(portletName)) {
			return redirect;
		}

		List<String> parameters = _getParameters(redirect, portletName);

		for (String parameter : parameters) {
			if (!Objects.equals(parameter, "folderId") &&
				!Objects.equals(parameter, "groupId") &&
				!Objects.equals(parameter, "mvcRenderCommandName") &&
				!Objects.equals(parameter, "navigation") &&
				!Objects.equals(parameter, "repositoryId")) {

				redirect = HttpComponentsUtil.removeParameter(
					redirect, portletName + parameter);
			}
		}

		return redirect;
	}

	private static List<String> _getParameters(String url, String portletName) {
		List<String> parameters = new ArrayList<>();

		String pattern = portletName + "([^=&]*)";

		Pattern regexPattern = Pattern.compile(pattern);

		Matcher matcher = regexPattern.matcher(url);

		while (matcher.find()) {
			parameters.add(matcher.group());
		}

		return parameters;
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