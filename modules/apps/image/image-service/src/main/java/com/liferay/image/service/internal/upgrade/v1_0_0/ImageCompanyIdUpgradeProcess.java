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

package com.liferay.image.service.internal.upgrade.v1_0_0;

import com.liferay.image.company.provider.ImageCompanyIdUpgradeProvider;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Adolfo Pérez
 */
public class ImageCompanyIdUpgradeProcess<T> extends UpgradeProcess {

	public ImageCompanyIdUpgradeProcess(
		Supplier<ActionableDynamicQuery> actionableDynamicQuerySupplier,
		Function<T, Long> companyIdFunction,
		ImageCompanyIdUpgradeProvider imageCompanyIdUpgradeProvider,
		Function<T, Long> imageIdFunction) {

		_actionableDynamicQuerySupplier = actionableDynamicQuerySupplier;
		_companyIdFunction = companyIdFunction;
		_imageCompanyIdUpgradeProvider = imageCompanyIdUpgradeProvider;
		_imageIdFunction = imageIdFunction;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_imageCompanyIdUpgradeProvider.imageCompanyIdUpgrade(
			_actionableDynamicQuerySupplier, connection, _companyIdFunction,
			_imageIdFunction);
	}

	private final Supplier<ActionableDynamicQuery>
		_actionableDynamicQuerySupplier;
	private final Function<T, Long> _companyIdFunction;
	private final ImageCompanyIdUpgradeProvider _imageCompanyIdUpgradeProvider;
	private final Function<T, Long> _imageIdFunction;

}