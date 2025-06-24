/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.repository.capabilities;

import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.capabilities.FileEntryValidatorCapability;
import com.liferay.portal.repository.liferayrepository.LiferayFileEntryValidatorLocalRepositoryWrapper;
import com.liferay.portal.repository.liferayrepository.LiferayFileEntryValidatorRepositoryWrapper;
import com.liferay.portal.repository.util.RepositoryWrapperAware;

/**
 * @author Alicia García
 */
public class LiferayFileEntryValidatorCapability
	implements FileEntryValidatorCapability, RepositoryWrapperAware {

	public LiferayFileEntryValidatorCapability(DLValidator dlValidator) {
		_dlValidator = dlValidator;
	}

	@Override
	public LocalRepository wrapLocalRepository(
		LocalRepository localRepository) {

		return new LiferayFileEntryValidatorLocalRepositoryWrapper(
			localRepository, _dlValidator);
	}

	@Override
	public Repository wrapRepository(Repository repository) {
		return new LiferayFileEntryValidatorRepositoryWrapper(
			repository, _dlValidator);
	}

	private final DLValidator _dlValidator;

}