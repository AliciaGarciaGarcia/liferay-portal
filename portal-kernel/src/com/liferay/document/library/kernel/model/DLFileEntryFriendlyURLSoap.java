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

package com.liferay.document.library.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.documentlibrary.service.http.DLFileEntryFriendlyURLServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class DLFileEntryFriendlyURLSoap implements Serializable {

	public static DLFileEntryFriendlyURLSoap toSoapModel(
		DLFileEntryFriendlyURL model) {

		DLFileEntryFriendlyURLSoap soapModel = new DLFileEntryFriendlyURLSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setCtCollectionId(model.getCtCollectionId());
		soapModel.setUuid(model.getUuid());
		soapModel.setFileEntryFriendlyURLId(model.getFileEntryFriendlyURLId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setFriendlyURL(model.getFriendlyURL());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static DLFileEntryFriendlyURLSoap[] toSoapModels(
		DLFileEntryFriendlyURL[] models) {

		DLFileEntryFriendlyURLSoap[] soapModels =
			new DLFileEntryFriendlyURLSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DLFileEntryFriendlyURLSoap[][] toSoapModels(
		DLFileEntryFriendlyURL[][] models) {

		DLFileEntryFriendlyURLSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DLFileEntryFriendlyURLSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DLFileEntryFriendlyURLSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DLFileEntryFriendlyURLSoap[] toSoapModels(
		List<DLFileEntryFriendlyURL> models) {

		List<DLFileEntryFriendlyURLSoap> soapModels =
			new ArrayList<DLFileEntryFriendlyURLSoap>(models.size());

		for (DLFileEntryFriendlyURL model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new DLFileEntryFriendlyURLSoap[soapModels.size()]);
	}

	public DLFileEntryFriendlyURLSoap() {
	}

	public long getPrimaryKey() {
		return _fileEntryFriendlyURLId;
	}

	public void setPrimaryKey(long pk) {
		setFileEntryFriendlyURLId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFileEntryFriendlyURLId() {
		return _fileEntryFriendlyURLId;
	}

	public void setFileEntryFriendlyURLId(long fileEntryFriendlyURLId) {
		_fileEntryFriendlyURLId = fileEntryFriendlyURLId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public String getFriendlyURL() {
		return _friendlyURL;
	}

	public void setFriendlyURL(String friendlyURL) {
		_friendlyURL = friendlyURL;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private String _uuid;
	private long _fileEntryFriendlyURLId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fileEntryId;
	private String _friendlyURL;
	private String _languageId;
	private Date _lastPublishDate;

}