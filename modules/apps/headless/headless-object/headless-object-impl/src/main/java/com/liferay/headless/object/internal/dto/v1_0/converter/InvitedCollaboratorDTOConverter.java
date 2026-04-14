/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.internal.dto.v1_0.converter;

import com.liferay.headless.object.dto.v1_0.InvitedCollaborator;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(
	property = "dto.class.name=com.liferay.headless.object.dto.v1_0.InvitedCollaborator",
	service = DTOConverter.class
)
public class InvitedCollaboratorDTOConverter
	implements DTOConverter<Ticket, InvitedCollaborator> {

	@Override
	public String getContentType() {
		return InvitedCollaborator.class.getSimpleName();
	}

	@Override
	public InvitedCollaborator toDTO(
			DTOConverterContext dtoConverterContext, Ticket ticket)
		throws Exception {

		if (ticket == null) {
			return null;
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			ticket.getExtraInfo());

		return new InvitedCollaborator() {
			{
				setActionIds(
					() -> JSONUtil.toStringArray(
						jsonObject.getJSONArray("actionIds")));
				setEmailAddress(() -> jsonObject.getString("emailAddress"));
				setId(ticket::getTicketId);
				setShare(() -> jsonObject.getBoolean("share"));
				setType(() -> "User");
			}
		};
	}

	@Reference
	private JSONFactory _jsonFactory;

}