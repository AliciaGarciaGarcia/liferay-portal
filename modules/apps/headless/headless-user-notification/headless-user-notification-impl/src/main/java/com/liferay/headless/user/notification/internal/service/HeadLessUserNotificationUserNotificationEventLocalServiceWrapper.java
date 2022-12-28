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

package com.liferay.headless.user.notification.internal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceWrapper;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(service = ServiceWrapper.class)
public class HeadLessUserNotificationUserNotificationEventLocalServiceWrapper
	extends UserNotificationEventLocalServiceWrapper {

	@Override
	public UserNotificationEvent getUserNotificationEvent(
			long userNotificationEventId)
		throws PortalException {

		UserNotificationEvent userNotificationEvent =
			super.getUserNotificationEvent(userNotificationEventId);

		try {
			userNotificationEvent.setPayload(
				_getPayload(userNotificationEvent));
		}
		catch (JSONException jsonException) {
			throw new PortalException(jsonException);
		}

		return userNotificationEvent;
	}

	@Override
	public List<UserNotificationEvent> getUserNotificationEvents(
		long userId, int start, int end) {

		List<UserNotificationEvent> userNotificationEvents =
			super.getUserNotificationEvents(userId, start, end);

		try {
			for (UserNotificationEvent userNotificationEvent :
					userNotificationEvents) {

				userNotificationEvent.setPayload(
					_getPayload(userNotificationEvent));
			}
		}
		catch (JSONException jsonException) {
			throw new RuntimeException(jsonException);
		}

		return userNotificationEvents;
	}

	private String _getPayload(UserNotificationEvent userNotificationEvent)
		throws JSONException {

		String payload = userNotificationEvent.getPayload();

		JSONObject userNotificationEventPayloadJSONObject =
			_jsonFactory.createJSONObject(payload);

		String notificationMessage =
			userNotificationEventPayloadJSONObject.getString(
				"notificationMessage");

		if (Validator.isNotNull(notificationMessage)) {
			return notificationMessage;
		}

		return payload;
	}


	@Reference
	private JSONFactory _jsonFactory;


}