/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.iframe.sanitizer.internal.configuration.helper;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.security.iframe.sanitizer.configuration.IFrameConfiguration;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Alicia García
 */
@Component(
	configurationPid = "com.liferay.portal.security.iframe.sanitizer.configuration.IFrameConfiguration",
	service = IFrameConfigurationHelper.class
)
public class IFrameConfigurationHelper {

	public IFrameConfiguration getCompanyIFrameConfiguration(long companyId) {
		return _getIFrameConfiguration(
			companyId, _companyConfigurationBeans,
			() -> _defaultIFrameConfiguration);
	}

	public void unmapPid(String pid) {
		if (_companyIds.containsKey(pid)) {
			long companyId = _companyIds.remove(pid);

			_companyConfigurationBeans.remove(companyId);
		}
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		modified(properties);

		_serviceRegistration = bundleContext.registerService(
			ManagedServiceFactory.class,
			new IFrameSanitizerManagedServiceFactory(),
			MapUtil.singletonDictionary(
				Constants.SERVICE_PID,
				"com.liferay.portal.security.iframe.sanitizer.configuration." +
					"IFrameConfiguration.scoped"));
	}

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		_defaultIFrameConfiguration = ConfigurableUtil.createConfigurable(
			IFrameConfiguration.class, properties);
	}

	private IFrameConfiguration _getIFrameConfiguration(
		long key, Map<Long, IFrameConfiguration> configurationBeans,
		Supplier<IFrameConfiguration> supplier) {

		if (configurationBeans.containsKey(key)) {
			return configurationBeans.get(key);
		}

		return supplier.get();
	}

	private final Map<Long, IFrameConfiguration> _companyConfigurationBeans =
		new ConcurrentHashMap<>();
	private final Map<String, Long> _companyIds = new ConcurrentHashMap<>();
	private volatile IFrameConfiguration _defaultIFrameConfiguration;
	private ServiceRegistration<ManagedServiceFactory> _serviceRegistration;

	private class IFrameSanitizerManagedServiceFactory
		implements ManagedServiceFactory {

		@Override
		public void deleted(String pid) {
			unmapPid(pid);
		}

		@Override
		public String getName() {
			return "com.liferay.portal.security.iframe.sanitizer." +
				"configuration.IFrameConfiguration.scoped";
		}

		@Override
		public void updated(String pid, Dictionary<String, ?> dictionary)
			throws ConfigurationException {

			unmapPid(pid);

			long companyId = GetterUtil.getLong(
				dictionary.get("companyId"), CompanyConstants.SYSTEM);

			if (companyId != CompanyConstants.SYSTEM) {
				_companyConfigurationBeans.put(
					companyId,
					ConfigurableUtil.createConfigurable(
						IFrameConfiguration.class, dictionary));
				_companyIds.put(pid, companyId);
			}
		}

	}

}