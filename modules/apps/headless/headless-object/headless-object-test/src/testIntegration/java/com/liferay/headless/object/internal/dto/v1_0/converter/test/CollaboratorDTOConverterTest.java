/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.internal.dto.v1_0.converter.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.object.dto.v1_0.Collaborator;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.field.util.ObjectFieldUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.sharing.constants.SharingTicketConstants;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.security.permission.SharingEntryAction;
import com.liferay.sharing.service.SharingEntryLocalService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alicia García
 */
@FeatureFlag("LPD-17564")
@RunWith(Arquillian.class)
public class CollaboratorDTOConverterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_objectDefinition = _addObjectDefinition();

		_objectEntry = _addObjectEntry(
			_objectDefinition.getObjectDefinitionId());
	}

	@Test
	public void testToDTOEmail() throws Exception {
		String emailAddress =
			StringUtil.toLowerCase(RandomTestUtil.randomString()) +
				"@liferay.com";

		Date expirationDate = RandomTestUtil.nextDate();

		Ticket ticket = _ticketLocalService.addTicket(
			TestPropsValues.getCompanyId(), _objectEntry.getModelClassName(),
			_objectEntry.getObjectEntryId(),
			SharingTicketConstants.TYPE_INVITE_COLLABORATOR,
			JSONUtil.put(
				"actionIds", JSONUtil.put(SharingEntryAction.VIEW.getActionId())
			).put(
				"emailAddress", emailAddress
			).put(
				"share", true
			).put(
				"type", "Email"
			).toString(),
			expirationDate, null);

		SharingEntry sharingEntry = _sharingEntryLocalService.addSharingEntry(
			null, TestPropsValues.getUserId(), ticket.getTicketId(), 0, 0,
			_classNameLocalService.getClassNameId(
				_objectEntry.getModelClassName()),
			_objectEntry.getObjectEntryId(), _group.getGroupId(), true,
			Arrays.asList(SharingEntryAction.VIEW), expirationDate,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));

		_tickets.add(ticket);

		_sharingEntries.add(sharingEntry);

		Collaborator collaborator = _toDTO(sharingEntry);

		Assert.assertEquals("Email", collaborator.getType());
		Assert.assertEquals(emailAddress, collaborator.getEmailAddress());
		Assert.assertEquals(emailAddress, collaborator.getName());
		Assert.assertEquals(
			Long.valueOf(ticket.getTicketId()), collaborator.getId());
		Assert.assertNull(collaborator.getExternalReferenceCode());
		Assert.assertNull(collaborator.getPortrait());
		Assert.assertTrue(collaborator.getShare());
		Assert.assertEquals(expirationDate, collaborator.getDateExpired());
		Assert.assertArrayEquals(
			new String[] {SharingEntryAction.VIEW.getActionId()},
			collaborator.getActionIds());
	}

	@Test
	public void testToDTOUser() throws Exception {
		User user = UserTestUtil.addUser();

		Date expirationDate = RandomTestUtil.nextDate();

		SharingEntry sharingEntry = _sharingEntryLocalService.addSharingEntry(
			null, TestPropsValues.getUserId(), 0, 0, user.getUserId(),
			_classNameLocalService.getClassNameId(
				_objectEntry.getModelClassName()),
			_objectEntry.getObjectEntryId(), _group.getGroupId(), true,
			Arrays.asList(SharingEntryAction.VIEW), expirationDate,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));

		_sharingEntries.add(sharingEntry);

		Collaborator collaborator = _toDTO(sharingEntry);

		Assert.assertEquals("User", collaborator.getType());
		Assert.assertNull(collaborator.getEmailAddress());
		Assert.assertEquals(user.getFullName(), collaborator.getName());
		Assert.assertEquals(
			Long.valueOf(user.getUserId()), collaborator.getId());
		Assert.assertEquals(
			user.getExternalReferenceCode(),
			collaborator.getExternalReferenceCode());
		Assert.assertNull(collaborator.getPortrait());
		Assert.assertTrue(collaborator.getShare());
		Assert.assertEquals(expirationDate, collaborator.getDateExpired());
		Assert.assertArrayEquals(
			new String[] {SharingEntryAction.VIEW.getActionId()},
			collaborator.getActionIds());
	}

	@Test
	public void testToDTOUserGroup() throws Exception {
		UserGroup userGroup = UserGroupTestUtil.addUserGroup();

		Date expirationDate = RandomTestUtil.nextDate();

		SharingEntry sharingEntry = _sharingEntryLocalService.addSharingEntry(
			null, TestPropsValues.getUserId(), 0, userGroup.getUserGroupId(), 0,
			_classNameLocalService.getClassNameId(
				_objectEntry.getModelClassName()),
			_objectEntry.getObjectEntryId(), _group.getGroupId(), true,
			Arrays.asList(SharingEntryAction.VIEW), expirationDate,
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));

		_sharingEntries.add(sharingEntry);

		Collaborator collaborator = _toDTO(sharingEntry);

		Assert.assertEquals("UserGroup", collaborator.getType());
		Assert.assertNull(collaborator.getEmailAddress());
		Assert.assertEquals(userGroup.getName(), collaborator.getName());
		Assert.assertEquals(
			Long.valueOf(userGroup.getUserGroupId()), collaborator.getId());
		Assert.assertEquals(
			userGroup.getExternalReferenceCode(),
			collaborator.getExternalReferenceCode());
		Assert.assertNull(collaborator.getPortrait());
		Assert.assertTrue(collaborator.getShare());
		Assert.assertEquals(expirationDate, collaborator.getDateExpired());
		Assert.assertArrayEquals(
			new String[] {SharingEntryAction.VIEW.getActionId()},
			collaborator.getActionIds());
	}

	private ObjectDefinition _addObjectDefinition() throws Exception {
		return ObjectDefinitionTestUtil.publishObjectDefinition(
			Collections.singletonList(
				ObjectFieldUtil.createObjectField(
					ObjectFieldConstants.BUSINESS_TYPE_TEXT,
					ObjectFieldConstants.DB_TYPE_STRING,
					RandomTestUtil.randomString(), "fieldName")),
			ObjectDefinitionConstants.SCOPE_SITE);
	}

	private ObjectEntry _addObjectEntry(long objectDefinitionId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, TestPropsValues.getUserId());

		return _objectEntryLocalService.addObjectEntry(
			TestPropsValues.getUserId(), _group.getGroupId(),
			objectDefinitionId,
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, Collections.emptyMap(), serviceContext);
	}

	private Collaborator _toDTO(SharingEntry sharingEntry) throws Exception {
		DTOConverter<SharingEntry, Collaborator> dtoConverter =
			(DTOConverter<SharingEntry, Collaborator>)
				_dtoConverterRegistry.getDTOConverter(
					Collaborator.class.getName());

		return dtoConverter.toDTO(
			new DefaultDTOConverterContext(
				_dtoConverterRegistry, sharingEntry.getSharingEntryId(),
				LocaleUtil.getDefault(), null, null),
			sharingEntry);
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private DTOConverterRegistry _dtoConverterRegistry;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private ObjectDefinition _objectDefinition;

	private ObjectEntry _objectEntry;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	private final List<SharingEntry> _sharingEntries = new ArrayList<>();

	@Inject
	private SharingEntryLocalService _sharingEntryLocalService;

	@Inject
	private TicketLocalService _ticketLocalService;

	@DeleteAfterTestRun
	private final List<Ticket> _tickets = new ArrayList<>();

}