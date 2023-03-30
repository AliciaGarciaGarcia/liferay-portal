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

package com.liferay.redirect.internal.provider;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.redirect.configuration.CrawlerUserAgentsConfiguration;
import com.liferay.redirect.provider.CrawlerUserAgentsProvider;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.annotations.Component;

/**
 * @author Alicia García
 */
@Component(
	configurationPid = "com.liferay.redirect.configuration.CrawlerUserAgentsConfiguration",
	service = {CrawlerUserAgentsProvider.class, ManagedService.class}
)
public class CrawlerUserAgentsProviderImpl
	implements CrawlerUserAgentsProvider, ManagedService {

	@Override
	public Set<String> getCrawlerUserAgents() {
		return _crawlerUserAgents;
	}

	@Override
	public void updated(Dictionary<String, ?> dictionary)
		throws ConfigurationException {

		_crawlerUserAgents = new HashSet<>();

		_crawlerUserAgentsConfiguration = ConfigurableUtil.createConfigurable(
			CrawlerUserAgentsConfiguration.class, dictionary);

		for (String crawlerUserAgent :
			_crawlerUserAgentsConfiguration.crawlerUserAgents()) {

			_crawlerUserAgents.add(StringUtil.toLowerCase(crawlerUserAgent));
		}
	}

	private volatile Set<String> _crawlerUserAgents;
	private volatile CrawlerUserAgentsConfiguration
		_crawlerUserAgentsConfiguration;
}