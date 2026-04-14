/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryTicketConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.HTTPTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.sharing.security.permission.SharingEntryAction;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alicia García
 */
@FeatureFlag("LPD-17564")
@RunWith(Arquillian.class)
public class InvitedCollaboratorResourceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		_objectDefinition = _getObjectDefinition();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_objectDefinitionLocalService.deleteObjectDefinition(_objectDefinition);
	}

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testDeleteObjectEntryInvitedCollaborator() throws Exception {
		ObjectEntry objectEntry = _addObjectEntry();

		JSONObject invitedCollaboratorJSONObject =
			_getInvitedCollaboratorJSONObject(
				RandomTestUtil.randomString() + "@liferay.com");

		Ticket ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			invitedCollaboratorJSONObject.toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		_tickets.add(ticket);

		long invitedCollaboratorId = ticket.getTicketId();

		String endpoint = StringBundler.concat(
			_objectDefinition.getRESTContextPath(), StringPool.SLASH,
			objectEntry.getObjectEntryId(), "/invited-collaborators/",
			invitedCollaboratorId);

		_assertDeleteObjectEntryInvitedCollaborator(
			endpoint, invitedCollaboratorJSONObject);
	}

	@Test
	public void testDeleteScopeScopeKeyByExternalReferenceCodeInvitedCollaborator()
		throws Exception {

		ObjectEntry objectEntry = _addObjectEntry();

		JSONObject invitedCollaboratorJSONObject =
			_getInvitedCollaboratorJSONObject(
				RandomTestUtil.randomString() + "@liferay.com");

		Ticket ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			invitedCollaboratorJSONObject.toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		_tickets.add(ticket);

		long invitedCollaboratorId = ticket.getTicketId();

		String endpoint = StringBundler.concat(
			_objectDefinition.getRESTContextPath(), "/scopes/",
			_group.getGroupId(), "/by-external-reference-code/",
			objectEntry.getExternalReferenceCode(), "/invited-collaborators/",
			invitedCollaboratorId);

		_assertDeleteObjectEntryInvitedCollaborator(
			endpoint, invitedCollaboratorJSONObject);
	}

	@Test
	public void testGetObjectEntryInvitedCollaboratorsPage() throws Exception {
		ObjectEntry objectEntry = _addObjectEntry();

		String endpoint = StringBundler.concat(
			_objectDefinition.getRESTContextPath(), StringPool.SLASH,
			objectEntry.getObjectEntryId(), "/invited-collaborators");

		_assertGetObjectEntryInvitedCollaboratorsPage(endpoint, objectEntry);
	}

	@Test
	public void testGetScopeScopeKeyByExternalReferenceCodeInvitedCollaboratorsPage()
		throws Exception {

		ObjectEntry objectEntry = _addObjectEntry();

		String endpoint = StringBundler.concat(
			_objectDefinition.getRESTContextPath(), "/scopes/",
			_group.getGroupId(), "/by-external-reference-code/",
			objectEntry.getExternalReferenceCode(), "/invited-collaborators");

		_assertGetObjectEntryInvitedCollaboratorsPage(endpoint, objectEntry);
	}

	private static ObjectDefinition _getObjectDefinition() throws Exception {
		return ObjectDefinitionTestUtil.publishObjectDefinition(
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
	}

	private ObjectEntry _addObjectEntry() throws Exception {
		return _objectEntryLocalService.addObjectEntry(
			_group.getGroupId(), TestPropsValues.getUserId(),
			_objectDefinition.getObjectDefinitionId(), 0, null,
			HashMapBuilder.<String, Serializable>put(
				"title", RandomTestUtil.randomString()
			).build(),
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId()));
	}

	private void _assertDeleteObjectEntryInvitedCollaborator(
			String endpoint, JSONObject invitedCollaboratorJSONObject)
		throws Exception {

		JSONObject jsonObject1 = HTTPTestUtil.invokeToJSONObject(
			null, endpoint, Http.Method.GET);

		_assertEquals(invitedCollaboratorJSONObject, jsonObject1);

		HTTPTestUtil.invokeToJSONObject(null, endpoint, Http.Method.DELETE);

		JSONObject jsonObject2 = HTTPTestUtil.invokeToJSONObject(
			null, endpoint, Http.Method.GET);

		Assert.assertEquals("NOT_FOUND", jsonObject2.getString("status"));
	}

	private void _assertEquals(
		JSONArray actualJSONArray, JSONArray expectedJSONArray) {

		Assert.assertEquals(
			expectedJSONArray.length(), actualJSONArray.length());

		for (int i = 0; i < expectedJSONArray.length(); i++) {
			_assertEquals(
				actualJSONArray.getJSONObject(i),
				expectedJSONArray.getJSONObject(i));
		}
	}

	private void _assertEquals(JSONObject jsonObject1, JSONObject jsonObject2) {
		Assert.assertTrue(_equals(jsonObject1, jsonObject2));
	}

	private void _assertGetObjectEntryInvitedCollaboratorsPage(
			String endpoint, ObjectEntry objectEntry)
		throws Exception {

		JSONObject invitedCollaboratorJSONObject1 =
			_getInvitedCollaboratorJSONObject(
				RandomTestUtil.randomString() + "@liferay.com");
		JSONObject invitedCollaboratorJSONObject2 =
			_getInvitedCollaboratorJSONObject(
				RandomTestUtil.randomString() + "@liferay.com");
		JSONObject invitedCollaboratorJSONObject3 =
			_getInvitedCollaboratorJSONObject(
				RandomTestUtil.randomString() + "@liferay.com");

		Ticket ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			invitedCollaboratorJSONObject1.toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		_tickets.add(ticket);

		ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			invitedCollaboratorJSONObject2.toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		_tickets.add(ticket);

		ticket = _ticketLocalService.addTicket(
			objectEntry.getCompanyId(), ObjectEntry.class.getName(),
			objectEntry.getObjectEntryId(),
			ObjectEntryTicketConstants.TYPE_INVITE_COLLABORATOR,
			invitedCollaboratorJSONObject3.toString(),
			new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(48)),
			null);

		_tickets.add(ticket);

		JSONObject jsonObject = HTTPTestUtil.invokeToJSONObject(
			null, endpoint, Http.Method.GET);

		_assertEquals(
			JSONUtil.putAll(
				invitedCollaboratorJSONObject1, invitedCollaboratorJSONObject2,
				invitedCollaboratorJSONObject3),
			jsonObject.getJSONArray("items"));
	}

	private boolean _equals(JSONObject jsonObject1, JSONObject jsonObject2) {
		for (String assertFieldName : _ASSERT_FIELD_NAMES) {
			if (Objects.equals(assertFieldName, "actionIds")) {
				if (!JSONUtil.equals(
						jsonObject1.getJSONArray("actionIds"),
						jsonObject2.getJSONArray("actionIds"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals(assertFieldName, "emailAddress") &&
				!StringUtil.equals(
					jsonObject1.getString("emailAddress"),
					jsonObject2.getString("emailAddress"))) {

				return false;
			}

			if (Objects.equals(assertFieldName, "share")) {
				if (!jsonObject1.getBoolean("share") == jsonObject2.getBoolean(
						"share")) {

					return false;
				}

				continue;
			}

			if (Objects.equals(assertFieldName, "type") &&
				!StringUtil.equals(
					jsonObject1.getString("type"),
					jsonObject2.getString("type"))) {

				return false;
			}
		}

		return true;
	}

	private JSONObject _getInvitedCollaboratorJSONObject(String emailAddress) {
		return JSONUtil.put(
			"actionIds", JSONUtil.put(SharingEntryAction.VIEW.getActionId())
		).put(
			"emailAddress", emailAddress
		).put(
			"share", true
		).put(
			"type", "User"
		);
	}

	private static final String[] _ASSERT_FIELD_NAMES = {
		"actionIds", "emailAddress", "share", "type"
	};

	private static ObjectDefinition _objectDefinition;

	@Inject
	private static ObjectDefinitionLocalService _objectDefinitionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private TicketLocalService _ticketLocalService;

	@DeleteAfterTestRun
	private List<Ticket> _tickets = new ArrayList<>();

}