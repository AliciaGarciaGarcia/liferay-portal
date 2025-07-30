/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.instance.lifecycle;

import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(service = PortalInstanceLifecycleListener.class)
public class AddDefaultServiceAccountUserPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		if (FeatureFlagManagerUtil.isEnabled(
				company.getCompanyId(), "LPD-17564")) {

			Role role = _roleLocalService.fetchRole(
				company.getCompanyId(), RoleConstants.CMS_MEMBER);

			if (role == null) {
				boolean addResource = PermissionThreadLocal.isAddResource();

				try {
					PermissionThreadLocal.setAddResource(false);

					User user = _userLocalService.getGuestUser(
						company.getCompanyId());

					role = _roleLocalService.addRole(
						null, user.getUserId(), null, 0,
						RoleConstants.CMS_MEMBER, null, null,
						RoleConstants.TYPE_REGULAR, null, null);
				}
				finally {
					PermissionThreadLocal.setAddResource(addResource);
				}
			}

			_resourceLocalService.addResources(
				company.getCompanyId(), 0, 0, Role.class.getName(),
				role.getRoleId(), false, false, false);
		}

		_userLocalService.addDefaultServiceAccountUser(company.getCompanyId());
	}

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}