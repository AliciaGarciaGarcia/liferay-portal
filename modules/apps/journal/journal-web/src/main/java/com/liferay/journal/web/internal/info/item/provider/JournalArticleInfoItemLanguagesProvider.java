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

package com.liferay.journal.web.internal.info.item.provider;

import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupKeyInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.provider.InfoItemLanguagesProvider;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.exception.NoSuchArticleResourceException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true, property = Constants.SERVICE_RANKING + ":Integer=10",
	service = InfoItemLanguagesProvider.class
)
public class JournalArticleInfoItemLanguagesProvider
	implements InfoItemLanguagesProvider<JournalArticle> {

	@Override
	public String getDefaultLanguageId(
		JournalArticle journalArticle) {
		return journalArticle.getDefaultLanguageId();
	}

	@Override
	public String[] getAvailableLanguageIds(
		JournalArticle journalArticle) {
		return journalArticle.getAvailableLanguageIds();
	}
}