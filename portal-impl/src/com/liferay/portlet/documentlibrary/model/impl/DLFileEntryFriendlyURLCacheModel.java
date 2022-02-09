/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DLFileEntryFriendlyURL in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileEntryFriendlyURLCacheModel
	implements CacheModel<DLFileEntryFriendlyURL>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DLFileEntryFriendlyURLCacheModel)) {
			return false;
		}

		DLFileEntryFriendlyURLCacheModel dlFileEntryFriendlyURLCacheModel =
			(DLFileEntryFriendlyURLCacheModel)object;

		if ((fileEntryFriendlyURLId ==
				dlFileEntryFriendlyURLCacheModel.fileEntryFriendlyURLId) &&
			(mvccVersion == dlFileEntryFriendlyURLCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fileEntryFriendlyURLId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", ctCollectionId=");
		sb.append(ctCollectionId);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", fileEntryFriendlyURLId=");
		sb.append(fileEntryFriendlyURLId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", friendlyURL=");
		sb.append(friendlyURL);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DLFileEntryFriendlyURL toEntityModel() {
		DLFileEntryFriendlyURLImpl dlFileEntryFriendlyURLImpl =
			new DLFileEntryFriendlyURLImpl();

		dlFileEntryFriendlyURLImpl.setMvccVersion(mvccVersion);
		dlFileEntryFriendlyURLImpl.setCtCollectionId(ctCollectionId);

		if (uuid == null) {
			dlFileEntryFriendlyURLImpl.setUuid("");
		}
		else {
			dlFileEntryFriendlyURLImpl.setUuid(uuid);
		}

		dlFileEntryFriendlyURLImpl.setFileEntryFriendlyURLId(
			fileEntryFriendlyURLId);
		dlFileEntryFriendlyURLImpl.setGroupId(groupId);
		dlFileEntryFriendlyURLImpl.setCompanyId(companyId);
		dlFileEntryFriendlyURLImpl.setUserId(userId);

		if (userName == null) {
			dlFileEntryFriendlyURLImpl.setUserName("");
		}
		else {
			dlFileEntryFriendlyURLImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dlFileEntryFriendlyURLImpl.setCreateDate(null);
		}
		else {
			dlFileEntryFriendlyURLImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dlFileEntryFriendlyURLImpl.setModifiedDate(null);
		}
		else {
			dlFileEntryFriendlyURLImpl.setModifiedDate(new Date(modifiedDate));
		}

		dlFileEntryFriendlyURLImpl.setFileEntryId(fileEntryId);

		if (friendlyURL == null) {
			dlFileEntryFriendlyURLImpl.setFriendlyURL("");
		}
		else {
			dlFileEntryFriendlyURLImpl.setFriendlyURL(friendlyURL);
		}

		if (languageId == null) {
			dlFileEntryFriendlyURLImpl.setLanguageId("");
		}
		else {
			dlFileEntryFriendlyURLImpl.setLanguageId(languageId);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			dlFileEntryFriendlyURLImpl.setLastPublishDate(null);
		}
		else {
			dlFileEntryFriendlyURLImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		dlFileEntryFriendlyURLImpl.resetOriginalValues();

		return dlFileEntryFriendlyURLImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		ctCollectionId = objectInput.readLong();
		uuid = objectInput.readUTF();

		fileEntryFriendlyURLId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		fileEntryId = objectInput.readLong();
		friendlyURL = objectInput.readUTF();
		languageId = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(ctCollectionId);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(fileEntryFriendlyURLId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(fileEntryId);

		if (friendlyURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(friendlyURL);
		}

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public long ctCollectionId;
	public String uuid;
	public long fileEntryFriendlyURLId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long fileEntryId;
	public String friendlyURL;
	public String languageId;
	public long lastPublishDate;

}