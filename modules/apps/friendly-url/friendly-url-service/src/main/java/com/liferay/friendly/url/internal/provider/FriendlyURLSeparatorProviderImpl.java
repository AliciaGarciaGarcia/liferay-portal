/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.internal.provider;

import com.liferay.friendly.url.configuration.manager.FriendlyURLSeparatorConfigurationManager;
import com.liferay.friendly.url.provider.FriendlyURLSeparatorProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mikel Lorza
 */
@Component(
	configurationPid = "com.liferay.friendly.url.configuration.FriendlyURLSeparatorCompanyConfiguration",
	service = FriendlyURLSeparatorProvider.class
)
public class FriendlyURLSeparatorProviderImpl
	implements FriendlyURLSeparatorProvider {

	@Override
	public String getFriendlyURLSeparator(long companyId, String key) {
		try {
			if (MapUtil.isNotEmpty(_friendlyURLSeparatorsJSONObjectMap) &&
				_friendlyURLSeparatorsJSONObjectMap.containsKey(companyId)) {

				JSONObject jsonObject = _friendlyURLSeparatorsJSONObjectMap.get(
					companyId);

				return jsonObject.getString(key);
			}

			JSONObject friendlyURLSeparatorsJSONObject =
				_jsonFactory.createJSONObject(
					_friendlyURLSeparatorConfigurationManager.
						getFriendlyURLSeparatorsJSON(companyId));

			_friendlyURLSeparatorsJSONObjectMap.put(
				companyId, friendlyURLSeparatorsJSONObject);

			return friendlyURLSeparatorsJSONObject.getString(key);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return null;
	}

	@Activate
	protected void activate() {
		_friendlyURLSeparatorsJSONObjectMap = new HashMap<>();

		_companyLocalService.forEachCompanyId(
			companyId -> {
				try {
					_friendlyURLSeparatorsJSONObjectMap.put(
						companyId,
						_jsonFactory.createJSONObject(
							_friendlyURLSeparatorConfigurationManager.
								getFriendlyURLSeparatorsJSON(companyId)));
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(portalException);
					}
				}
			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FriendlyURLSeparatorProviderImpl.class.getName());

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private FriendlyURLSeparatorConfigurationManager
		_friendlyURLSeparatorConfigurationManager;

	private Map<Long, JSONObject> _friendlyURLSeparatorsJSONObjectMap;

	@Reference
	private JSONFactory _jsonFactory;

}