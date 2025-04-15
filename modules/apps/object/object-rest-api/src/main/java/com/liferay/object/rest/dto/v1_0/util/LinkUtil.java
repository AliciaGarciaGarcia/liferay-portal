/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.dto.v1_0.util;

import com.liferay.object.rest.dto.v1_0.Link;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Portal;

/**
 * @author Carolina Barbosa
 */
public class LinkUtil {

	public static Link toLink(
		String fileName, String objectDefinitionExternalReferenceCode,
		String objectEntryExternalReferenceCode, Portal portal, String url) {

		return new Link() {
			{
				setHref(
					() -> {
						try {
							String href = HttpComponentsUtil.addParameter(
								url, "objectDefinitionExternalReferenceCode",
								objectDefinitionExternalReferenceCode);

							return HttpComponentsUtil.addParameter(
								href, "objectEntryExternalReferenceCode",
								objectEntryExternalReferenceCode);
						}
						catch (Exception exception) {
							if (_log.isWarnEnabled()) {
								_log.warn(exception);
							}
						}

						return StringBundler.concat(
							portal.getPathContext(), portal.getPathMain(),
							"/portal/login");
					});
				setLabel(() -> fileName);
			}
		};
	}

	private static final Log _log = LogFactoryUtil.getLog(LinkUtil.class);

}