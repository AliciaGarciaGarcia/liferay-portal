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

package com.liferay.image.company.provider;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;

import java.sql.Connection;

import java.util.function.Function;
import java.util.function.Supplier;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Alicia Garcia
 */
@ProviderType
public interface ImageCompanyIdUpgradeProvider<T> {

	public void imageCompanyIdUpgrade(
			Supplier<ActionableDynamicQuery> actionableDynamicQuerySupplier,
			Connection connection, Function<T, Long> companyIdFunction,
			Function<T, Long> imageIdFunction)
		throws Exception;

}