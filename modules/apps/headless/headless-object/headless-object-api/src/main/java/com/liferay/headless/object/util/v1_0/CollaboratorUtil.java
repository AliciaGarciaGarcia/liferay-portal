/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.util.v1_0;

import com.liferay.headless.object.constants.v1_0.CollaboratorTicketConstants;
import com.liferay.headless.object.dto.v1_0.Collaborator;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.GroupUtil;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.security.permission.SharingEntryAction;
import com.liferay.sharing.service.SharingEntryLocalService;
import com.liferay.sharing.service.SharingEntryService;

import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author Mikel Lorza
 */
public class CollaboratorUtil {

	public static Collaborator addOrUpdateCollaborator(
			AcceptLanguage acceptLanguage, long classNameId, long classPK,
			Collaborator collaborator, long collaboratorId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			SharingEntryService sharingEntryService, String type,
			UserGroupLocalService userGroupLocalService, UriInfo uriInfo,
			User user, UserLocalService userLocalService)
		throws Exception {

		_validateType(type);

		if ("Email".equals(type)) {
			return null;
		}

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry,
			_addOrUpdateSharingEntry(
				classNameId, classPK, collaborator, collaboratorId, groupId,
				sharingEntryService, type, userGroupLocalService,
				userLocalService),
			uriInfo, user);
	}

	public static Page<Collaborator> addOrUpdateCollaborators(
			AcceptLanguage acceptLanguage, long classNameId, long classPK,
			Collaborator[] collaborators,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			SharingEntryService sharingEntryService, UriInfo uriInfo, User user,
			UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		List<SharingEntry> oldSharingEntries =
			sharingEntryService.getSharingEntries(
				classNameId, classPK, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		List<SharingEntry> newSharingEntries = new ArrayList<>();

		List<Long> sharingEntriesIds = new ArrayList<>();

		for (Collaborator collaborator : collaborators) {
			if ("Email".equals(collaborator.getType())) {
				return null;
			}

			SharingEntry sharingEntry = _addOrUpdateSharingEntry(
				classNameId, classPK, collaborator, collaborator.getId(),
				groupId, sharingEntryService, collaborator.getType(),
				userGroupLocalService, userLocalService);

			newSharingEntries.add(sharingEntry);
			sharingEntriesIds.add(sharingEntry.getSharingEntryId());
		}

		for (SharingEntry sharingEntry : oldSharingEntries) {
			if (!sharingEntriesIds.contains(sharingEntry.getSharingEntryId())) {
				sharingEntryService.deleteSharingEntry(sharingEntry);
			}
		}

		Collections.sort(
			newSharingEntries,
			Comparator.comparing(
				SharingEntry::getCreateDate, Comparator.reverseOrder()
			).thenComparing(
				SharingEntry::getSharingEntryId, Comparator.reverseOrder()
			));

		return Page.of(
			TransformUtil.transform(
				newSharingEntries,
				sharingEntry -> toCollaborator(
					acceptLanguage, dtoConverter, dtoConverterRegistry,
					sharingEntry, uriInfo, user)));
	}

	public static void deleteCollaborator(
			String className, long classNameId, long classPK,
			Long collaboratorId, SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, String type)
		throws Exception {

		_validateType(type);

		if (StringUtil.equals("Email", type)) {
			_deleteInvitedCollaborator(
				collaboratorId, className, classPK, ticketLocalService);
		}
		else if (StringUtil.equals("User", type)) {
			sharingEntryService.deleteSharingEntry(
				0, collaboratorId, classNameId, classPK);
		}
		else if (StringUtil.equals("UserGroup", type)) {
			sharingEntryService.deleteSharingEntry(
				collaboratorId, 0, classNameId, classPK);
		}
	}

	public static Collaborator getCollaborator(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, Long collaboratorId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, JSONFactory jsonFactory,
			SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, String type, UriInfo uriInfo,
			User user)
		throws Exception {

		_validateType(type);

		if (StringUtil.equals("Email", type)) {
			Ticket ticket = ticketLocalService.getTicket(collaboratorId);

			if (!Objects.equals(className, ticket.getClassName()) ||
				(classPK != ticket.getClassPK()) ||
				(ticket.getType() !=
					CollaboratorTicketConstants.TYPE_INVITE_COLLABORATOR)) {

				throw new NoSuchModelException();
			}

			return toCollaborator(jsonFactory, ticket);
		}

		if (StringUtil.equals("User", type)) {
			return toCollaborator(
				acceptLanguage, dtoConverter, dtoConverterRegistry,
				sharingEntryService.getSharingEntry(
					0, collaboratorId, classNameId, classPK),
				uriInfo, user);
		}

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry,
			sharingEntryService.getSharingEntry(
				collaboratorId, 0, classNameId, classPK),
			uriInfo, user);
	}

	public static Page<Collaborator> getCollaborators(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			JSONFactory jsonFactory, Pagination pagination,
			SharingEntryLocalService sharingEntryLocalService,
			SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, UriInfo uriInfo, User user)
		throws Exception {

		List<Collaborator> collaborators = TransformUtil.transform(
			sharingEntryService.getSharingEntries(
				classNameId, classPK, groupId, pagination.getStartPosition(),
				pagination.getEndPosition(),
				OrderByComparatorFactoryUtil.create(
					"SharingEntry", "createDate", false, "sharingEntryId",
					false)),
			sharingEntry -> toCollaborator(
				acceptLanguage, dtoConverter, dtoConverterRegistry,
				sharingEntry, uriInfo, user));

		int collaboratorsCount =
			sharingEntryLocalService.getSharingEntriesCount(
				classNameId, classPK);

		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		List<Ticket> tickets = ticketLocalService.getTickets(
			group.getCompanyId(), className, classPK,
			CollaboratorTicketConstants.TYPE_INVITE_COLLABORATOR);

		for (Ticket ticket : tickets) {
			collaborators.add(toCollaborator(jsonFactory, ticket));
			collaboratorsCount++;
		}

		return Page.of(collaborators, pagination, collaboratorsCount);
	}

	public static long getGroupId(
			long companyId, GroupLocalService groupLocalService,
			String scopeKey)
		throws Exception {

		Long groupId = GroupUtil.getGroupId(
			companyId, scopeKey, groupLocalService);

		if (groupId != null) {
			return groupId;
		}

		if (Objects.equals(scopeKey, "0")) {
			return 0;
		}

		throw new NoSuchGroupException();
	}

	public static Collaborator toCollaborator(
			AcceptLanguage acceptLanguage,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry,
			SharingEntry sharingEntry, UriInfo uriInfo, User user)
		throws Exception {

		return dtoConverter.toDTO(
			new DefaultDTOConverterContext(
				acceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				dtoConverterRegistry, sharingEntry.getSharingEntryId(),
				acceptLanguage.getPreferredLocale(), uriInfo, user),
			sharingEntry);
	}

	public static Collaborator toCollaborator(
			JSONFactory jsonFactory, Ticket ticket)
		throws Exception {

		JSONObject jsonObject = jsonFactory.createJSONObject(
			ticket.getExtraInfo());

		return new Collaborator() {
			{
				setActionIds(
					() -> JSONUtil.toStringArray(
						jsonObject.getJSONArray("actionIds")));
				setEmailAddress(() -> jsonObject.getString("emailAddress"));
				setId(ticket::getTicketId);
				setShare(() -> jsonObject.getBoolean("share"));
				setType(() -> "Email");
			}
		};
	}

	private static SharingEntry _addOrUpdateSharingEntry(
			long classNameId, long classPK, Collaborator collaborator,
			long collaboratorId, long groupId,
			SharingEntryService sharingEntryService, String type,
			UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		_validateType(type);

		long toUserGroupId = 0;
		long toUserId = 0;

		if (StringUtil.equals("UserGroup", type)) {
			UserGroup userGroup = userGroupLocalService.getUserGroup(
				collaboratorId);

			toUserGroupId = userGroup.getUserGroupId();
		}
		else {
			User user = userLocalService.getUser(collaboratorId);

			toUserId = user.getUserId();
		}

		boolean shareable = false;

		if (collaborator.getShare() != null) {
			shareable = collaborator.getShare();
		}

		return sharingEntryService.addOrUpdateSharingEntry(
			null, toUserGroupId, toUserId, classNameId, classPK, groupId,
			shareable,
			TransformUtil.transformToList(
				collaborator.getActionIds(),
				SharingEntryAction::parseFromActionId),
			collaborator.getDateExpired(), new ServiceContext());
	}

	private static void _deleteInvitedCollaborator(
			Long invitedCollaboratorId, String className, long classPK,
			TicketLocalService ticketLocalService)
		throws Exception {

		Ticket ticket = ticketLocalService.getTicket(invitedCollaboratorId);

		if (!Objects.equals(className, ticket.getClassName()) ||
			(classPK != ticket.getClassPK()) ||
			(ticket.getType() !=
				CollaboratorTicketConstants.TYPE_INVITE_COLLABORATOR)) {

			throw new NoSuchModelException();
		}

		ticketLocalService.deleteTicket(ticket.getTicketId());
	}

	private static void _validateType(String type) {
		if (!StringUtil.equals("Email", type) &&
			!StringUtil.equals("User", type) &&
			!StringUtil.equals("UserGroup", type)) {

			throw new IllegalArgumentException(
				"Collaborator type must be \"Email\", \"User\" or \"UserGroup\"");
		}
	}

}