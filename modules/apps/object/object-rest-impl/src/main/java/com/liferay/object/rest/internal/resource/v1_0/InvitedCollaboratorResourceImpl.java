/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.resource.v1_0;

import com.liferay.object.constants.ObjectEntryTicketConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.rest.dto.v1_0.InvitedCollaborator;
import com.liferay.object.rest.resource.v1_0.InvitedCollaboratorResource;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;

import jakarta.ws.rs.core.Context;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/invited-collaborator.properties",
	scope = ServiceScope.PROTOTYPE, service = InvitedCollaboratorResource.class
)
public class InvitedCollaboratorResourceImpl
	extends BaseInvitedCollaboratorResourceImpl {

	public InvitedCollaboratorResourceImpl(
		DTOConverterRegistry dtoConverterRegistry,
		DTOConverter<Ticket, InvitedCollaborator>
			invitedCollaboratorDTOConverter,
		ObjectEntryLocalService objectEntryLocalService,
		TicketLocalService ticketLocalService) {

		_dtoConverterRegistry = dtoConverterRegistry;
		_invitedCollaboratorDTOConverter = invitedCollaboratorDTOConverter;
		_objectEntryLocalService = objectEntryLocalService;
		_ticketLocalService = ticketLocalService;
	}

	@Override
	public Page<InvitedCollaborator> getObjectEntryInvitedCollaboratorsPage(
			Long objectEntryId)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
			objectEntryId);

		return Page.of(
			transform(
				_ticketLocalService.getTickets(
					objectEntry.getCompanyId(), ObjectEntry.class.getName(),
					objectEntry.getObjectEntryId(),
					ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR),
				this::_toInvitedCollaborator));
	}

	public void setObjectDefinition(ObjectDefinition objectDefinition) {
		_objectDefinition = objectDefinition;
	}

	private InvitedCollaborator _toInvitedCollaborator(Ticket ticket)
		throws Exception {

		return _invitedCollaboratorDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				true, null, _dtoConverterRegistry, contextUser.getUserId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			ticket);
	}

	private final DTOConverterRegistry _dtoConverterRegistry;
	private final DTOConverter<Ticket, InvitedCollaborator>
		_invitedCollaboratorDTOConverter;

	@Context
	private ObjectDefinition _objectDefinition;

	private final ObjectEntryLocalService _objectEntryLocalService;
	private final TicketLocalService _ticketLocalService;

}

// LIFERAY-REST-BUILDER-HASH:1302460789