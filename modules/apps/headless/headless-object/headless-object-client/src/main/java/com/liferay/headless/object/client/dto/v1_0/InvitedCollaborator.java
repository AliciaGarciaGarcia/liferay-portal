/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.object.client.dto.v1_0;

import com.liferay.headless.object.client.function.UnsafeSupplier;
import com.liferay.headless.object.client.serdes.v1_0.InvitedCollaboratorSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Alicia García
 * @generated
 */
@Generated("")
public class InvitedCollaborator implements Cloneable, Serializable {

	public static InvitedCollaborator toDTO(String json) {
		return InvitedCollaboratorSerDes.toDTO(json);
	}

	public String[] getActionIds() {
		return actionIds;
	}

	public void setActionIds(String[] actionIds) {
		this.actionIds = actionIds;
	}

	public void setActionIds(
		UnsafeSupplier<String[], Exception> actionIdsUnsafeSupplier) {

		try {
			actionIds = actionIdsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] actionIds;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setEmailAddress(
		UnsafeSupplier<String, Exception> emailAddressUnsafeSupplier) {

		try {
			emailAddress = emailAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String emailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public Boolean getShare() {
		return share;
	}

	public void setShare(Boolean share) {
		this.share = share;
	}

	public void setShare(
		UnsafeSupplier<Boolean, Exception> shareUnsafeSupplier) {

		try {
			share = shareUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean share;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		try {
			type = typeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String type;

	@Override
	public InvitedCollaborator clone() throws CloneNotSupportedException {
		return (InvitedCollaborator)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof InvitedCollaborator)) {
			return false;
		}

		InvitedCollaborator invitedCollaborator = (InvitedCollaborator)object;

		return Objects.equals(toString(), invitedCollaborator.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return InvitedCollaboratorSerDes.toJSON(this);
	}

}
// LIFERAY-REST-BUILDER-HASH:1799475547