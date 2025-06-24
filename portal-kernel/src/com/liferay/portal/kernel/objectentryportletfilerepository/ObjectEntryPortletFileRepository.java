/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.objectentryportletfilerepository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Alicia García
 */
@ProviderType
public interface ObjectEntryPortletFileRepository {

	public Repository addPortletRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException;

	public Repository fetchPortletRepository(long groupId, String portletId);

}