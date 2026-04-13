/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.dto.v1_0.converter.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryTicketConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.rest.dto.v1_0.InvitedCollaborator;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.sharing.security.permission.SharingEntryAction;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alicia García
 */
@RunWith(Arquillian.class)
public class InvitedCollaboratorDTOConverterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Test
	public void testToDTO() throws Exception {
		_objectDefinition = ObjectDefinitionTestUtil.publishObjectDefinition(
			ObjectDefinitionTestUtil.getRandomName(),
			Collections.singletonList(
				new TextObjectFieldBuilder(
				).labelMap(
					LocalizedMapUtil.getLocalizedMap(
						RandomTestUtil.randomString())
				).indexed(
					true
				).indexedAsKeyword(
					true
				).name(
					"title"
				).localized(
					false
				).build()),
			ObjectDefinitionConstants.SCOPE_SITE, TestPropsValues.getUserId());

		ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(
			TestPropsValues.getGroupId(), TestPropsValues.getUserId(),
			_objectDefinition.getObjectDefinitionId(), 0, null,
			HashMapBuilder.<String, Serializable>put(
				"title", RandomTestUtil.randomString()
			).build(),
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId(), TestPropsValues.getUserId()));

		String emailAddress = StringUtil.lowerCase(
			RandomTestUtil.randomString() + "@liferay.com");

		_ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			JSONUtil.put(
				"actionIds", SharingEntryAction.VIEW.getBitwiseValue()
			).put(
				"emailAddress", emailAddress
			).put(
				"isShareable", true
			).toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		InvitedCollaborator invitedCollaborator = _toDTO(_ticket);

		Assert.assertEquals(
			emailAddress, invitedCollaborator.getEmailAddress());

		Long invitedCollaboratorId = invitedCollaborator.getId();

		Assert.assertEquals(
			_ticket.getTicketId(), invitedCollaboratorId.longValue());

		Assert.assertTrue(invitedCollaborator.getShare());
		Assert.assertEquals("User", invitedCollaborator.getType());

		String[] actionIdsArray = invitedCollaborator.getActionIds();

		Assert.assertEquals(
			Arrays.toString(actionIdsArray), 1, actionIdsArray.length);
		Assert.assertEquals(
			Arrays.toString(actionIdsArray),
			SharingEntryAction.VIEW.getActionId(), actionIdsArray[0]);
	}

	private InvitedCollaborator _toDTO(Ticket ticket) throws Exception {
		DTOConverter<Ticket, InvitedCollaborator> dtoConverter =
			(DTOConverter<Ticket, InvitedCollaborator>)
				_dtoConverterRegistry.getDTOConverter(
					InvitedCollaborator.class.getName());

		DefaultDTOConverterContext dtoConverterContext =
			new DefaultDTOConverterContext(
				_dtoConverterRegistry, ticket.getTicketId(),
				LocaleUtil.getDefault(), null, null);

		return dtoConverter.toDTO(dtoConverterContext, ticket);
	}

	@Inject
	private DTOConverterRegistry _dtoConverterRegistry;

	@DeleteAfterTestRun
	private ObjectDefinition _objectDefinition;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@DeleteAfterTestRun
	private Ticket _ticket;

	@Inject
	private TicketLocalService _ticketLocalService;

}