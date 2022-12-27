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

package com.liferay.headless.user.notification.internal.resource.v1_0;

import com.liferay.headless.user.notification.dto.v1_0.UserNotification;
import com.liferay.headless.user.notification.internal.dto.v1_0.UserNotificationDTOConverter;
import com.liferay.headless.user.notification.internal.odata.entity.v1_0.UserNotificationEntityModel;
import com.liferay.headless.user.notification.resource.v1_0.UserNotificationResource;
import com.liferay.portal.kernel.exception.NoSuchUserNotificationEventException;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Carlos Correa
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/user-notification.properties",
	scope = ServiceScope.PROTOTYPE, service = UserNotificationResource.class
)
public class UserNotificationResourceImpl
	extends BaseUserNotificationResourceImpl {

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public UserNotification getMyUserNotification(Long userNotificationId)
		throws Exception {

		return _toUserNotification(
			_getUserNotificationEvent(userNotificationId));
	}

	@Override
	public Page<UserNotification> getMyUserNotificationsPage(
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_userNotificationEventLocalService.getUserNotificationEvents(
					contextUser.getUserId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				this::_toUserNotification),
			pagination,
			_userNotificationEventLocalService.getUserNotificationEventsCount(
				contextUser.getUserId()));
	}

	@Override
	public Page<UserNotification> getMyUserNotificationsReadPage(
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_userNotificationEventLocalService.
					getArchivedUserNotificationEvents(
						contextUser.getUserId(), true,
						pagination.getStartPosition(),
						pagination.getEndPosition()),
				this::_toUserNotification),
			pagination,
			_userNotificationEventLocalService.
				getArchivedUserNotificationEventsCount(
					contextUser.getUserId(), true));
	}

	@Override
	public Page<UserNotification> getMyUserNotificationsUnreadPage(
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_userNotificationEventLocalService.
					getArchivedUserNotificationEvents(
						contextUser.getUserId(), false,
						pagination.getStartPosition(),
						pagination.getEndPosition()),
				this::_toUserNotification),
			pagination,
			_userNotificationEventLocalService.
				getArchivedUserNotificationEventsCount(
					contextUser.getUserId(), false));
	}

	@Override
	public void putMyUserNotificationRead(Long userNotificationId)
		throws Exception {

		UserNotificationEvent userNotificationEvent = _getUserNotificationEvent(
			userNotificationId);

		_userNotificationEventLocalService.updateUserNotificationEvent(
			userNotificationEvent.getUuid(),
			userNotificationEvent.getCompanyId(), true);
	}

	@Override
	public void putMyUserNotificationUnread(Long userNotificationId)
		throws Exception {

		UserNotificationEvent userNotificationEvent = _getUserNotificationEvent(
			userNotificationId);

		_userNotificationEventLocalService.updateUserNotificationEvent(
			userNotificationEvent.getUuid(),
			userNotificationEvent.getCompanyId(), false);
	}

	private UserNotificationEvent _getUserNotificationEvent(
			long userNotificationId)
		throws Exception {

		UserNotificationEvent userNotificationEvent =
			_userNotificationEventLocalService.getUserNotificationEvent(
				userNotificationId);

		if (userNotificationEvent.getUserId() != contextUser.getUserId()) {
			throw new NoSuchUserNotificationEventException();
		}

		return userNotificationEvent;
	}

	private UserNotification _toUserNotification(
			UserNotificationEvent userNotificationEvent)
		throws Exception {

		return _userNotificationDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), null, null,
				userNotificationEvent.getUserNotificationEventId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			userNotificationEvent);
	}

	private static final EntityModel _entityModel =
		new UserNotificationEntityModel();

	@Reference
	private UserNotificationDTOConverter _userNotificationDTOConverter;

	@Reference
	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

}