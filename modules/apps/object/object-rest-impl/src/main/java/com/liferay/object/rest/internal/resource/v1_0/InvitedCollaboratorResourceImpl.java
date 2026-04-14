/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.resource.v1_0;

import com.liferay.headless.object.dto.v1_0.InvitedCollaborator;
import com.liferay.headless.object.util.v1_0.CollaboratorUtil;
import com.liferay.object.constants.ObjectEntryTicketConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;

import jakarta.ws.rs.core.Context;

import java.util.Objects;

/**
 * @author Alicia García
 */
public class InvitedCollaboratorResourceImpl
	extends BaseInvitedCollaboratorResourceImpl {

	public InvitedCollaboratorResourceImpl(
		DTOConverterRegistry dtoConverterRegistry,
		GroupLocalService groupLocalService,
		DTOConverter<Ticket, InvitedCollaborator>
			invitedCollaboratorDTOConverter,
		ObjectEntryLocalService objectEntryLocalService,
		TicketLocalService ticketLocalService) {

		_dtoConverterRegistry = dtoConverterRegistry;
		_groupLocalService = groupLocalService;
		_invitedCollaboratorDTOConverter = invitedCollaboratorDTOConverter;
		_objectEntryLocalService = objectEntryLocalService;
		_ticketLocalService = ticketLocalService;
	}

	@Override
	public void deleteObjectEntryInvitedCollaborator(
			Long objectEntryId, Long invitedCollaboratorId)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		_deleteInvitedCollaborator(
			invitedCollaboratorId,
			_objectEntryLocalService.getObjectEntry(objectEntryId));
	}

	@Override
	public void deleteScopeScopeKeyByExternalReferenceCodeInvitedCollaborator(
			String scopeKey, String externalReferenceCode,
			Long invitedCollaboratorId)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		_deleteInvitedCollaborator(
			invitedCollaboratorId,
			_objectEntryLocalService.getObjectEntry(
				externalReferenceCode,
				CollaboratorUtil.getGroupId(
					contextCompany.getCompanyId(), _groupLocalService,
					scopeKey),
				_objectDefinition.getObjectDefinitionId()));
	}

	@Override
	public InvitedCollaborator getObjectEntryInvitedCollaborator(
			Long objectEntryId, Long invitedCollaboratorId)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		return _getInvitedCollaborator(
			invitedCollaboratorId,
			_objectEntryLocalService.getObjectEntry(objectEntryId));
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

	@Override
	public InvitedCollaborator
			getScopeScopeKeyByExternalReferenceCodeInvitedCollaborator(
				String scopeKey, String externalReferenceCode,
				Long invitedCollaboratorId)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		return _getInvitedCollaborator(
			invitedCollaboratorId,
			_objectEntryLocalService.getObjectEntry(
				externalReferenceCode,
				CollaboratorUtil.getGroupId(
					contextCompany.getCompanyId(), _groupLocalService,
					scopeKey),
				_objectDefinition.getObjectDefinitionId()));
	}

	@Override
	public Page<InvitedCollaborator>
			getScopeScopeKeyByExternalReferenceCodeInvitedCollaboratorsPage(
				String scopeKey, String externalReferenceCode)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				_objectDefinition.getCompanyId(), "LPD-17564")) {

			throw new UnsupportedOperationException();
		}

		ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
			externalReferenceCode,
			CollaboratorUtil.getGroupId(
				contextCompany.getCompanyId(), _groupLocalService, scopeKey),
			_objectDefinition.getObjectDefinitionId());

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

	private void _deleteInvitedCollaborator(
			Long invitedCollaboratorId, ObjectEntry objectEntry)
		throws Exception {

		Ticket ticket = _ticketLocalService.getTicket(invitedCollaboratorId);

		if (!Objects.equals(
				ObjectEntry.class.getName(), ticket.getClassName()) ||
			(objectEntry.getObjectEntryId() != ticket.getClassPK()) ||
			(ticket.getType() !=
				ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR)) {

			throw new NoSuchModelException();
		}

		_ticketLocalService.deleteTicket(ticket.getTicketId());
	}

	private InvitedCollaborator _getInvitedCollaborator(
			Long invitedCollaboratorId, ObjectEntry objectEntry)
		throws Exception {

		Ticket ticket = _ticketLocalService.getTicket(invitedCollaboratorId);

		if (!Objects.equals(
				ObjectEntry.class.getName(), ticket.getClassName()) ||
			(objectEntry.getObjectEntryId() != ticket.getClassPK()) ||
			(ticket.getType() !=
				ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR)) {

			throw new NoSuchModelException();
		}

		return _toInvitedCollaborator(ticket);
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
	private final GroupLocalService _groupLocalService;
	private final DTOConverter<Ticket, InvitedCollaborator>
		_invitedCollaboratorDTOConverter;

	@Context
	private ObjectDefinition _objectDefinition;

	private final ObjectEntryLocalService _objectEntryLocalService;
	private final TicketLocalService _ticketLocalService;

}

// LIFERAY-REST-BUILDER-HASH:1302460789