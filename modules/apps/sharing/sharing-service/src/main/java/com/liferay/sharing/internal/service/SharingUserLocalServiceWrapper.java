/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.internal.service;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.service.SharingEntryLocalService;
import com.liferay.sharing.service.persistence.SharingEntryPersistence;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alicia García
 */
@Component(service = ServiceWrapper.class)
public class SharingUserLocalServiceWrapper extends UserLocalServiceWrapper {

	@Override
	public User addUserWithWorkflow(
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, Locale locale,
			String firstName, String middleName, String lastName,
			long prefixListTypeId, long suffixListTypeId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, int type, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			ServiceContext serviceContext)
		throws PortalException {

		User user = super.addUserWithWorkflow(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, locale, firstName,
			middleName, lastName, prefixListTypeId, suffixListTypeId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, type, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		_processInviteCollaboratorTickets(user);

		return user;
	}

	private void _processInviteCollaboratorTickets(User user)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			_ticketLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				dynamicQuery.add(
					RestrictionsFactoryUtil.eq(
						"companyId", user.getCompanyId()));
				dynamicQuery.add(
					RestrictionsFactoryUtil.ilike(
						"extraInfo",
						"%\"emailAddress\":\"" + user.getEmailAddress() +
							"\"%"));
				dynamicQuery.add(
					RestrictionsFactoryUtil.eq(
						"type", TicketConstants.TYPE_INVITE_COLLABORATOR));
			});

		actionableDynamicQuery.setPerformActionMethod(
			(Ticket ticket) -> {
				JSONObject jsonObject = _jsonFactory.createJSONObject(
					ticket.getExtraInfo());

				if (!StringUtil.equalsIgnoreCase(
						jsonObject.getString("emailAddress"),
						user.getEmailAddress())) {

					return;
				}

				_updateSharingEntries(ticket, user);

				_ticketLocalService.deleteTicket(ticket);
			});

		actionableDynamicQuery.performActions();
	}

	private void _updateSharingEntries(Ticket ticket, User user) {
		Indexer<SharingEntry> indexer = _indexerRegistry.getIndexer(
			SharingEntry.class.getName());

		for (SharingEntry sharingEntry :
				_sharingEntryPersistence.findByToTicketId(
					ticket.getTicketId())) {

			SharingEntry existingSharingEntry =
				_sharingEntryLocalService.fetchSharingEntry(
					0, sharingEntry.getToUserGroupId(), user.getUserId(),
					sharingEntry.getClassNameId(), sharingEntry.getClassPK());

			if (existingSharingEntry != null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						StringBundler.concat(
							"A sharing entry already exists for user ",
							user.getUserId(), " with classNameId ",
							sharingEntry.getClassNameId(), " and classPK ",
							sharingEntry.getClassPK()));
				}

				continue;
			}

			sharingEntry.setToTicketId(0);
			sharingEntry.setToUserId(user.getUserId());

			SharingEntry updatedSharingEntry = _sharingEntryPersistence.update(
				sharingEntry);

			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					try {
						if (indexer != null) {
							indexer.reindex(updatedSharingEntry);
						}
					}
					catch (Exception exception) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to reindex sharing entry " +
									updatedSharingEntry.getSharingEntryId(),
								exception);
						}
					}

					return null;
				});
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SharingUserLocalServiceWrapper.class);

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private SharingEntryLocalService _sharingEntryLocalService;

	@Reference
	private SharingEntryPersistence _sharingEntryPersistence;

	@Reference
	private TicketLocalService _ticketLocalService;

}