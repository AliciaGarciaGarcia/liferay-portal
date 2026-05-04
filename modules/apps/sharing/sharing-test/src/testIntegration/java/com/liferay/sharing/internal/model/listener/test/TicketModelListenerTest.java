/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.internal.model.listener.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.test.TestInfo;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.security.permission.SharingEntryAction;
import com.liferay.sharing.service.SharingEntryLocalService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alicia García
 */
@RunWith(Arquillian.class)
public class TicketModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousMailTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	@TestInfo("LPD-48130")
	public void testDeletingTicketDeletesSharingEntries() throws Exception {
		Ticket ticket1 = _addInviteCollaboratorTicket();
		Ticket ticket2 = _addInviteCollaboratorTicket();

		_addToTicketIdSharingEntry(_group.getGroupId(), ticket1.getTicketId());
		_addToTicketIdSharingEntry(_group.getGroupId(), ticket2.getTicketId());

		List<SharingEntry> toTicketSharingEntries =
			_sharingEntryLocalService.getToTicketSharingEntries(
				ticket1.getTicketId());

		Assert.assertEquals(
			toTicketSharingEntries.toString(), 1,
			toTicketSharingEntries.size());

		toTicketSharingEntries =
			_sharingEntryLocalService.getToTicketSharingEntries(
				ticket2.getTicketId());

		Assert.assertEquals(
			toTicketSharingEntries.toString(), 1,
			toTicketSharingEntries.size());

		_ticketLocalService.deleteTicket(ticket1);

		toTicketSharingEntries =
			_sharingEntryLocalService.getToTicketSharingEntries(
				ticket1.getTicketId());

		Assert.assertEquals(
			toTicketSharingEntries.toString(), 0,
			toTicketSharingEntries.size());

		toTicketSharingEntries =
			_sharingEntryLocalService.getToTicketSharingEntries(
				ticket2.getTicketId());

		Assert.assertEquals(
			toTicketSharingEntries.toString(), 1,
			toTicketSharingEntries.size());
	}

	private Ticket _addInviteCollaboratorTicket() throws Exception {
		return _ticketLocalService.addTicket(
			TestPropsValues.getCompanyId(), Group.class.getName(),
			_group.getGroupId(), TicketConstants.TYPE_INVITE_COLLABORATOR,
			JSONUtil.put(
				"emailAddress", RandomTestUtil.randomString() + "@liferay.com"
			).toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			new ServiceContext());
	}

	private void _addToTicketIdSharingEntry(long classPK, long toTicketId)
		throws Exception {

		_sharingEntryLocalService.addSharingEntry(
			null, TestPropsValues.getUserId(), toTicketId, 0, 0,
			_classNameLocalService.getClassNameId(Group.class.getName()),
			classPK, _group.getGroupId(), true,
			Arrays.asList(SharingEntryAction.VIEW), null,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private SharingEntryLocalService _sharingEntryLocalService;

	@Inject
	private TicketLocalService _ticketLocalService;

}