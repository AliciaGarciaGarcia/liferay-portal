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

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Alicia García
 */
public class CrawlerUserAgentsProviderImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtil.setFieldValue(
			_crawlerUserAgentsProviderImpl, "_portalCache", _portalCache);
	}

	@Test
	public void testIsCrawlerUserAgent() {
		_crawlerUserAgentsProviderImpl.setCrawlerUserAgents(_crawlerUserAgents);

		Mockito.when(
			_portalCache.get(Mockito.anyString())
		).thenReturn(
			null
		);

		Assert.assertFalse(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent("another"));
		Assert.assertTrue(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent("crawlerbot"));
		Assert.assertFalse(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent("crawler_bot"));
		Assert.assertTrue(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent("W3C_Validator"));
		Assert.assertTrue(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent("w3c_validator"));
		Assert.assertTrue(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent(
				"W3C_Validator/1.3"));
		Assert.assertFalse(
			_crawlerUserAgentsProviderImpl.isCrawlerUserAgent(
				"W3C Validator 1.3"));
	}

	private static final Set<String> _crawlerUserAgents = SetUtil.fromArray(
		"w3c_validator", "crawlerbot");

	private final CrawlerUserAgentsProviderImpl _crawlerUserAgentsProviderImpl =
		new CrawlerUserAgentsProviderImpl();

	@Mock
	private PortalCache<String, Boolean> _portalCache;

}