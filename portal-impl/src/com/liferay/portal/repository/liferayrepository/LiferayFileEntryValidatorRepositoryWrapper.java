/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.liferayrepository;

import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.repository.util.RepositoryWrapper;
import com.liferay.portlet.documentlibrary.util.DLAppUtil;

import java.io.File;
import java.io.InputStream;

import java.util.Date;

/**
 * @author Alicia García
 */
public class LiferayFileEntryValidatorRepositoryWrapper
	extends RepositoryWrapper {

	public LiferayFileEntryValidatorRepositoryWrapper(
		Repository repository, DLValidator dlValidator) {

		super(repository);

		_dlValidator = dlValidator;
	}

	@Override
	public FileEntry addFileEntry(
			String externalReferenceCode, long userId, long folderId,
			String sourceFileName, String mimeType, String title,
			String urlTitle, String description, String changeLog, File file,
			Date displayDate, Date expirationDate, Date reviewDate,
			ServiceContext serviceContext)
		throws PortalException {

		long size = 0;

		if (file != null) {
			size = file.length();
		}

		validate(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			DLAppUtil.getExtension(title, sourceFileName), mimeType, size,
			sourceFileName);

		return super.addFileEntry(
			externalReferenceCode, userId, folderId, sourceFileName, mimeType,
			title, urlTitle, description, changeLog, file, displayDate,
			expirationDate, reviewDate, serviceContext);
	}

	@Override
	public FileEntry addFileEntry(
			String externalReferenceCode, long userId, long folderId,
			String sourceFileName, String mimeType, String title,
			String urlTitle, String description, String changeLog,
			InputStream inputStream, long size, Date displayDate,
			Date expirationDate, Date reviewDate, ServiceContext serviceContext)
		throws PortalException {

		validate(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			DLAppUtil.getExtension(title, sourceFileName), mimeType, size,
			sourceFileName);

		return super.addFileEntry(
			externalReferenceCode, userId, folderId, sourceFileName, mimeType,
			title, urlTitle, description, changeLog, inputStream, size,
			displayDate, expirationDate, reviewDate, serviceContext);
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String urlTitle, String description,
			String changeLog, DLVersionNumberIncrease dlVersionNumberIncrease,
			File file, Date displayDate, Date expirationDate, Date reviewDate,
			ServiceContext serviceContext)
		throws PortalException {

		long size = 0;

		if (file != null) {
			size = file.length();
		}

		validate(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			DLAppUtil.getExtension(title, sourceFileName), mimeType, size,
			sourceFileName);

		return super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, urlTitle,
			description, changeLog, dlVersionNumberIncrease, file, displayDate,
			expirationDate, reviewDate, serviceContext);
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String urlTitle, String description,
			String changeLog, DLVersionNumberIncrease dlVersionNumberIncrease,
			InputStream inputStream, long size, Date displayDate,
			Date expirationDate, Date reviewDate, ServiceContext serviceContext)
		throws PortalException {

		validate(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			DLAppUtil.getExtension(title, sourceFileName), mimeType, size,
			sourceFileName);

		return super.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, urlTitle,
			description, changeLog, dlVersionNumberIncrease, inputStream, size,
			displayDate, expirationDate, reviewDate, serviceContext);
	}

	public void validate(
			long companyId, long groupId, String fileExtension, String mimeType,
			long size, String sourceFileName)
		throws PortalException {

		if (Validator.isNotNull(sourceFileName)) {
			_dlValidator.validateFileName(sourceFileName);

			_dlValidator.validateFileExtension(sourceFileName);

			_dlValidator.validateSourceFileExtension(
				fileExtension, sourceFileName);

			if (size != 0) {
				_dlValidator.validateFileMimeType(companyId, mimeType);
			}
		}

		_dlValidator.validateFileSize(groupId, sourceFileName, mimeType, size);
	}

	private final DLValidator _dlValidator;

}