/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.client.serdes.v1_0;

import com.liferay.headless.object.client.dto.v1_0.InvitedCollaborator;
import com.liferay.headless.object.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Alicia García
 * @generated
 */
@Generated("")
public class InvitedCollaboratorSerDes {

	public static InvitedCollaborator toDTO(String json) {
		InvitedCollaboratorJSONParser invitedCollaboratorJSONParser =
			new InvitedCollaboratorJSONParser();

		return invitedCollaboratorJSONParser.parseToDTO(json);
	}

	public static InvitedCollaborator[] toDTOs(String json) {
		InvitedCollaboratorJSONParser invitedCollaboratorJSONParser =
			new InvitedCollaboratorJSONParser();

		return invitedCollaboratorJSONParser.parseToDTOs(json);
	}

	public static String toJSON(InvitedCollaborator invitedCollaborator) {
		if (invitedCollaborator == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (invitedCollaborator.getActionIds() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actionIds\": ");

			sb.append("[");

			for (int i = 0; i < invitedCollaborator.getActionIds().length;
				 i++) {

				sb.append(_toJSON(invitedCollaborator.getActionIds()[i]));

				if ((i + 1) < invitedCollaborator.getActionIds().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (invitedCollaborator.getEmailAddress() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddress\": ");

			sb.append("\"");

			sb.append(_escape(invitedCollaborator.getEmailAddress()));

			sb.append("\"");
		}

		if (invitedCollaborator.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(invitedCollaborator.getId());
		}

		if (invitedCollaborator.getShare() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"share\": ");

			sb.append(invitedCollaborator.getShare());
		}

		if (invitedCollaborator.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(invitedCollaborator.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		InvitedCollaboratorJSONParser invitedCollaboratorJSONParser =
			new InvitedCollaboratorJSONParser();

		return invitedCollaboratorJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		InvitedCollaborator invitedCollaborator) {

		if (invitedCollaborator == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (invitedCollaborator.getActionIds() == null) {
			map.put("actionIds", null);
		}
		else {
			map.put(
				"actionIds",
				String.valueOf(invitedCollaborator.getActionIds()));
		}

		if (invitedCollaborator.getEmailAddress() == null) {
			map.put("emailAddress", null);
		}
		else {
			map.put(
				"emailAddress",
				String.valueOf(invitedCollaborator.getEmailAddress()));
		}

		if (invitedCollaborator.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(invitedCollaborator.getId()));
		}

		if (invitedCollaborator.getShare() == null) {
			map.put("share", null);
		}
		else {
			map.put("share", String.valueOf(invitedCollaborator.getShare()));
		}

		if (invitedCollaborator.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(invitedCollaborator.getType()));
		}

		return map;
	}

	public static class InvitedCollaboratorJSONParser
		extends BaseJSONParser<InvitedCollaborator> {

		@Override
		protected InvitedCollaborator createDTO() {
			return new InvitedCollaborator();
		}

		@Override
		protected InvitedCollaborator[] createDTOArray(int size) {
			return new InvitedCollaborator[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "actionIds")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "share")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			InvitedCollaborator invitedCollaborator, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actionIds")) {
				if (jsonParserFieldValue != null) {
					invitedCollaborator.setActionIds(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				if (jsonParserFieldValue != null) {
					invitedCollaborator.setEmailAddress(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					invitedCollaborator.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "share")) {
				if (jsonParserFieldValue != null) {
					invitedCollaborator.setShare((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					invitedCollaborator.setType((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}
// LIFERAY-REST-BUILDER-HASH:-1220955026