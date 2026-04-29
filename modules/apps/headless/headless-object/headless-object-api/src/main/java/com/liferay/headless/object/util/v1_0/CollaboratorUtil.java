/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.util.v1_0;

import com.liferay.headless.object.dto.v1_0.Collaborator;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.auth.GuestOrUserUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.GroupUtil;
import com.liferay.sharing.constants.SharingTicketConstants;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.security.permission.SharingEntryAction;
import com.liferay.sharing.service.SharingEntryLocalService;
import com.liferay.sharing.service.SharingEntryService;

import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Mikel Lorza
 */
public class CollaboratorUtil {

	public static Collaborator addOrUpdateCollaborator(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, Collaborator collaborator, long collaboratorId,
			long companyId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			JSONFactory jsonFactory, SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, String type, UriInfo uriInfo,
			User user, UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		_validateType(type);

		if (StringUtil.equals("Email", type)) {
			return addOrUpdateCollaboratorByEmailAddress(
				acceptLanguage, className, classNameId, classPK, collaborator,
				companyId, dtoConverter, dtoConverterRegistry,
				collaborator.getEmailAddress(), groupId, jsonFactory,
				sharingEntryService, ticketLocalService, uriInfo, user,
				userGroupLocalService, userLocalService);
		}

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry,
			_addOrUpdateSharingEntry(
				classNameId, classPK, collaborator, collaboratorId, groupId,
				sharingEntryService, type, userGroupLocalService,
				userLocalService),
			uriInfo, user);
	}

	public static Collaborator addOrUpdateCollaboratorByEmailAddress(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, Collaborator collaborator, long companyId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, String emailAddress,
			long groupId, JSONFactory jsonFactory,
			SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, UriInfo uriInfo, User user,
			UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		_validateEmailAddress(emailAddress);
		_validateEmailActionIds(collaborator.getActionIds());

		collaborator.setEmailAddress(() -> emailAddress);

		long userId = _fetchUserIdByEmailAddress(
			companyId, emailAddress, userLocalService);

		if (userId > 0) {
			SharingEntry sharingEntry = _addOrUpdateSharingEntry(
				classNameId, classPK, collaborator, userId, groupId,
				sharingEntryService, "User", userGroupLocalService,
				userLocalService);

			Ticket ticket = _fetchTicketByEmailAddress(
				className, classPK, companyId, emailAddress, jsonFactory,
				ticketLocalService);

			if (ticket != null) {
				ticketLocalService.deleteTicket(ticket.getTicketId());
			}

			return toCollaborator(
				acceptLanguage, dtoConverter, dtoConverterRegistry,
				sharingEntry, uriInfo, user);
		}

		Ticket ticket = _addOrUpdateTicket(
			className, classPK, collaborator, companyId,
			_fetchTicketByEmailAddress(
				className, classPK, companyId, emailAddress, jsonFactory,
				ticketLocalService),
			ticketLocalService, "Email");

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry,
			_addOrUpdateSharingEntry(
				classNameId, classPK, collaborator, ticket.getTicketId(),
				groupId, sharingEntryService, "Email", userGroupLocalService,
				userLocalService),
			uriInfo, user);
	}

	public static Page<Collaborator> addOrUpdateCollaborators(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, Collaborator[] collaborators, long companyId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			JSONFactory jsonFactory, SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, UriInfo uriInfo, User user,
			UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		List<SharingEntry> oldSharingEntries =
			sharingEntryService.getSharingEntries(
				classNameId, classPK, groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		List<String> collaboratorEmailAddresses = new ArrayList<>();
		List<SharingEntry> newSharingEntries = new ArrayList<>();
		List<Long> sharingEntriesIds = new ArrayList<>();

		for (Collaborator collaborator : collaborators) {
			_validateType(collaborator.getType());

			SharingEntry sharingEntry = null;

			if (StringUtil.equals("Email", collaborator.getType())) {
				_validateEmailAddress(collaborator.getEmailAddress());
				_validateEmailActionIds(collaborator.getActionIds());

				long userId = _fetchUserIdByEmailAddress(
					companyId, collaborator.getEmailAddress(),
					userLocalService);

				if (userId > 0) {
					sharingEntry = _addOrUpdateSharingEntry(
						classNameId, classPK, collaborator, userId, groupId,
						sharingEntryService, "User", userGroupLocalService,
						userLocalService);
				}
				else {
					Ticket ticket = _addOrUpdateTicket(
						className, classPK, collaborator, companyId,
						_fetchTicketByEmailAddress(
							className, classPK, companyId,
							collaborator.getEmailAddress(), jsonFactory,
							ticketLocalService),
						ticketLocalService, collaborator.getType());

					sharingEntry = _addOrUpdateSharingEntry(
						classNameId, classPK, collaborator,
						ticket.getTicketId(), groupId, sharingEntryService,
						collaborator.getType(), userGroupLocalService,
						userLocalService);

					collaboratorEmailAddresses.add(
						collaborator.getEmailAddress());
				}
			}
			else {
				sharingEntry = _addOrUpdateSharingEntry(
					classNameId, classPK, collaborator,
					GetterUtil.getLong(collaborator.getId()), groupId,
					sharingEntryService, collaborator.getType(),
					userGroupLocalService, userLocalService);
			}

			newSharingEntries.add(sharingEntry);
			sharingEntriesIds.add(sharingEntry.getSharingEntryId());
		}

		List<Ticket> tickets = ticketLocalService.getTickets(
			companyId, className, classPK,
			SharingTicketConstants.TYPE_INVITE_COLLABORATOR);

		for (Ticket ticket : tickets) {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				ticket.getExtraInfo());

			if (!collaboratorEmailAddresses.contains(
					jsonObject.getString("emailAddress"))) {

				ticketLocalService.deleteTicket(ticket.getTicketId());
			}
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
			_deleteCollaboratorTicket(
				className, classNameId, classPK, collaboratorId,
				sharingEntryService, ticketLocalService);
		}
		else if (StringUtil.equals("User", type)) {
			sharingEntryService.deleteSharingEntry(
				0, 0, collaboratorId, classNameId, classPK);
		}
		else if (StringUtil.equals("UserGroup", type)) {
			sharingEntryService.deleteSharingEntry(
				0, collaboratorId, 0, classNameId, classPK);
		}
	}

	public static void deleteCollaboratorByEmailAddress(
			String className, long classNameId, long classPK, long companyId,
			String emailAddress, JSONFactory jsonFactory,
			SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, long userId,
			UserLocalService userLocalService)
		throws Exception {

		_validateEmailAddress(emailAddress);

		User user = userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
				0, 0, user.getUserId(), classNameId, classPK);

			if (sharingEntry != null) {
				if ((sharingEntry.getUserId() != userId) &&
					!_hasViewPermission(user)) {

					throw new NoSuchModelException();
				}

				sharingEntryService.deleteSharingEntry(sharingEntry);
			}
		}

		Ticket ticket = _fetchTicketByEmailAddress(
			className, classPK, companyId, emailAddress, jsonFactory,
			ticketLocalService);

		if (ticket == null) {
			return;
		}

		SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
			ticket.getTicketId(), 0, 0, classNameId, classPK);

		if (sharingEntry != null) {
			sharingEntryService.deleteSharingEntry(sharingEntry);
		}

		ticketLocalService.deleteTicket(ticket.getTicketId());
	}

	public static Collaborator getCollaborator(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, Long collaboratorId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry,
			SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, String type, UriInfo uriInfo,
			User user)
		throws Exception {

		_validateType(type);

		if (StringUtil.equals("Email", type)) {
			Ticket ticket = ticketLocalService.fetchTicket(collaboratorId);

			if ((ticket == null) ||
				!Objects.equals(className, ticket.getClassName()) ||
				(classPK != ticket.getClassPK()) ||
				(ticket.getType() !=
					SharingTicketConstants.TYPE_INVITE_COLLABORATOR)) {

				throw new NoSuchModelException();
			}

			SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
				collaboratorId, 0, 0, classNameId, classPK);

			if (sharingEntry == null) {
				throw new NoSuchModelException();
			}

			return toCollaborator(
				acceptLanguage, dtoConverter, dtoConverterRegistry,
				sharingEntry, uriInfo, user);
		}

		if (StringUtil.equals("User", type)) {
			return toCollaborator(
				acceptLanguage, dtoConverter, dtoConverterRegistry,
				sharingEntryService.getSharingEntry(
					0, 0, collaboratorId, classNameId, classPK),
				uriInfo, user);
		}

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry,
			sharingEntryService.getSharingEntry(
				0, collaboratorId, 0, classNameId, classPK),
			uriInfo, user);
	}

	public static Collaborator getCollaboratorByEmailAddress(
			AcceptLanguage acceptLanguage, String className, long classNameId,
			long classPK, long companyId,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, String emailAddress,
			JSONFactory jsonFactory, SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService, UriInfo uriInfo, User user,
			UserLocalService userLocalService)
		throws Exception {

		_validateEmailAddress(emailAddress);

		long userId = _fetchUserIdByEmailAddress(
			companyId, emailAddress, userLocalService);

		if (userId > 0) {
			SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
				0, 0, userId, classNameId, classPK);

			if (sharingEntry != null) {
				return toCollaborator(
					acceptLanguage, dtoConverter, dtoConverterRegistry,
					sharingEntry, uriInfo, user);
			}

			throw new NoSuchModelException();
		}

		Ticket ticket = _fetchTicketByEmailAddress(
			className, classPK, companyId, emailAddress, jsonFactory,
			ticketLocalService);

		if (ticket == null) {
			throw new NoSuchModelException();
		}

		SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
			ticket.getTicketId(), 0, 0, classNameId, classPK);

		if (sharingEntry == null) {
			throw new NoSuchModelException();
		}

		return toCollaborator(
			acceptLanguage, dtoConverter, dtoConverterRegistry, sharingEntry,
			uriInfo, user);
	}

	public static Page<Collaborator> getCollaborators(
			AcceptLanguage acceptLanguage, long classNameId, long classPK,
			DTOConverter<SharingEntry, Collaborator> dtoConverter,
			DTOConverterRegistry dtoConverterRegistry, long groupId,
			Pagination pagination,
			SharingEntryLocalService sharingEntryLocalService,
			SharingEntryService sharingEntryService, UriInfo uriInfo, User user)
		throws Exception {

		int sharingEntriesCount =
			sharingEntryLocalService.getSharingEntriesCount(
				classNameId, classPK);

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

		return Page.of(collaborators, pagination, sharingEntriesCount);
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

	private static SharingEntry _addOrUpdateSharingEntry(
			long classNameId, long classPK, Collaborator collaborator,
			long collaboratorId, long groupId,
			SharingEntryService sharingEntryService, String type,
			UserGroupLocalService userGroupLocalService,
			UserLocalService userLocalService)
		throws Exception {

		long toTicketId = 0;
		long toUserGroupId = 0;
		long toUserId = 0;

		if (StringUtil.equals("Email", type)) {
			toTicketId = collaboratorId;
		}
		else if (StringUtil.equals("UserGroup", type)) {
			UserGroup userGroup = userGroupLocalService.getUserGroup(
				collaboratorId);

			toUserGroupId = userGroup.getUserGroupId();
		}
		else if (StringUtil.equals("User", type)) {
			User user = userLocalService.getUser(collaboratorId);

			toUserId = user.getUserId();
		}

		boolean shareable = false;

		if (collaborator.getShare() != null) {
			shareable = collaborator.getShare();
		}

		return sharingEntryService.addOrUpdateSharingEntry(
			null, toTicketId, toUserGroupId, toUserId, classNameId, classPK,
			groupId, shareable,
			TransformUtil.transformToList(
				collaborator.getActionIds(),
				SharingEntryAction::parseFromActionId),
			collaborator.getDateExpired(), new ServiceContext());
	}

	private static Ticket _addOrUpdateTicket(
			String className, long classPK, Collaborator collaborator,
			long companyId, Ticket ticket,
			TicketLocalService ticketLocalService, String type)
		throws Exception {

		if ((ticket != null) &&
			(!Objects.equals(className, ticket.getClassName()) ||
			 (classPK != ticket.getClassPK()) ||
			 (ticket.getType() !=
				 SharingTicketConstants.TYPE_INVITE_COLLABORATOR))) {

			throw new NoSuchModelException();
		}

		String extraInfo = JSONUtil.put(
			"actionIds", collaborator.getActionIds()
		).put(
			"emailAddress", collaborator.getEmailAddress()
		).put(
			"share", collaborator.getShare()
		).put(
			"type", type
		).toString();

		if (ticket == null) {
			Date expirationDate = collaborator.getDateExpired();

			if (expirationDate == null) {
				expirationDate = new Date(
					System.currentTimeMillis() +
						TimeUnit.HOURS.toMillis(
							SharingTicketConstants.
								DEFAULT_INVITATION_EXPIRATION_HOURS));
			}

			return ticketLocalService.addTicket(
				companyId, className, classPK,
				SharingTicketConstants.TYPE_INVITE_COLLABORATOR, extraInfo,
				expirationDate, null);
		}

		if (collaborator.getDateExpired() != null) {
			ticket.setExpirationDate(collaborator.getDateExpired());
		}

		ticket.setExtraInfo(extraInfo);

		return ticketLocalService.updateTicket(ticket);
	}

	private static void _deleteCollaboratorTicket(
			String className, long classNameId, long classPK,
			Long collaboratorId, SharingEntryService sharingEntryService,
			TicketLocalService ticketLocalService)
		throws Exception {

		Ticket ticket = ticketLocalService.fetchTicket(collaboratorId);

		if ((ticket == null) ||
			!Objects.equals(className, ticket.getClassName()) ||
			(classPK != ticket.getClassPK()) ||
			(ticket.getType() !=
				SharingTicketConstants.TYPE_INVITE_COLLABORATOR)) {

			throw new NoSuchModelException();
		}

		SharingEntry sharingEntry = sharingEntryService.fetchSharingEntry(
			collaboratorId, 0, 0, classNameId, classPK);

		if (sharingEntry != null) {
			sharingEntryService.deleteSharingEntry(sharingEntry);
		}

		ticketLocalService.deleteTicket(ticket.getTicketId());
	}

	private static Ticket _fetchTicketByEmailAddress(
			String className, long classPK, long companyId, String emailAddress,
			JSONFactory jsonFactory, TicketLocalService ticketLocalService)
		throws Exception {

		List<Ticket> tickets = ticketLocalService.getTickets(
			companyId, className, classPK,
			SharingTicketConstants.TYPE_INVITE_COLLABORATOR);

		for (Ticket ticket : tickets) {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				ticket.getExtraInfo());

			if (Objects.equals(
					emailAddress, jsonObject.getString("emailAddress"))) {

				return ticket;
			}
		}

		return null;
	}

	private static long _fetchUserIdByEmailAddress(
		long companyId, String emailAddress,
		UserLocalService userLocalService) {

		User user = userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user == null) {
			return 0;
		}

		return user.getUserId();
	}

	private static boolean _hasViewPermission(User user) throws Exception {
		return UserPermissionUtil.contains(
			GuestOrUserUtil.getPermissionChecker(), user.getUserId(),
			ActionKeys.VIEW);
	}

	private static void _validateEmailActionIds(String[] actionIds) {
		if (actionIds == null) {
			return;
		}

		for (String actionId : actionIds) {
			if (!Objects.equals(
					SharingEntryAction.VIEW.getActionId(), actionId)) {

				throw new IllegalArgumentException(
					"Collaborators of type \"Email\" can only be granted the " +
						"VIEW action");
			}
		}
	}

	private static void _validateEmailAddress(String emailAddress) {
		if (Validator.isNull(emailAddress)) {
			throw new IllegalArgumentException(
				"Collaborator type \"Email\" must have an email address");
		}

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new IllegalArgumentException(
				"Invalid email address: " + emailAddress);
		}
	}

	private static void _validateType(String type) {
		if (!StringUtil.equals("Email", type) &&
			!StringUtil.equals("User", type) &&
			!StringUtil.equals("UserGroup", type)) {

			throw new IllegalArgumentException(
				"Collaborator type must be \"Email\", \"User\" or " +
					"\"UserGroup\"");
		}
	}

}