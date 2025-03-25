/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.friendly.url.internal.configuration.persistence.listener;

import com.liferay.friendly.url.provider.FriendlyURLSeparatorProvider;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Dictionary;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(
	property = "model.class.name=com.liferay.friendly.url.configuration.FriendlyURLSeparatorCompanyConfiguration",
	service = ConfigurationModelListener.class
)
public class FriendlyURLSeparatorCompanyConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onAfterSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		_portalCache =
			(PortalCache<Long, JSONObject>)_multiVMPool.getPortalCache(
				FriendlyURLSeparatorProvider.class.getName());

		_portalCache.remove(
			GetterUtil.getLong(
				properties.get("companyId"), CompanyConstants.SYSTEM));
	}

	@Reference
	private MultiVMPool _multiVMPool;

	private PortalCache<Long, JSONObject> _portalCache;

}