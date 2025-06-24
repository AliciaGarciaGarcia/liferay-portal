/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.repository;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.DLAppHelperThreadLocal;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.objectentryportletfilerepository.ObjectEntryPortletFileRepository;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.repository.portletrepository.PortletRepository;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(service = ObjectEntryPortletFileRepository.class)
public class ObjectEntryPortletFileRepositoryImpl
	implements ObjectEntryPortletFileRepository {

	@Override
	public Repository addPortletRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = _repositoryLocalService.fetchRepository(
			groupId, portletId);

		if (repository != null) {
			return repository;
		}

		Group group = _groupLocalService.getGroup(groupId);

		User user = _userLocalService.getGuestUser(group.getCompanyId());

		long classNameId = _portal.getClassNameId(
			PortletRepository.class.getName());

		UnicodeProperties typeSettingsUnicodeProperties =
			new UnicodeProperties();

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					group.getCtCollectionId())) {

			return _run(
				() -> _repositoryLocalService.addRepository(
					null, user.getUserId(), groupId, classNameId,
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, portletId,
					StringPool.BLANK, portletId, typeSettingsUnicodeProperties,
					true, serviceContext));
		}
	}

	@Override
	public Repository fetchPortletRepository(long groupId, String portletId) {
		return _repositoryLocalService.fetchRepository(groupId, portletId);
	}

	private <T, E extends Throwable> T _run(
			Class<?> clazz, UnsafeSupplier<T, E> unsafeSupplier)
		throws E {

		boolean dlAppHelperEnabled = DLAppHelperThreadLocal.isEnabled();

		try {
			DLAppHelperThreadLocal.setEnabled(false);

			if (clazz != null) {
				SystemEventHierarchyEntryThreadLocal.push(clazz);
			}

			return unsafeSupplier.get();
		}
		finally {
			if (clazz != null) {
				SystemEventHierarchyEntryThreadLocal.pop(clazz);
			}

			DLAppHelperThreadLocal.setEnabled(dlAppHelperEnabled);
		}
	}

	private <T, E extends Throwable> T _run(UnsafeSupplier<T, E> unsafeSupplier)
		throws E {

		return _run(null, unsafeSupplier);
	}

	@Reference
	private GroupLocalService _groupLocalService;
	//
	@Reference
	private Portal _portal;

	@Reference
	private RepositoryLocalService _repositoryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}