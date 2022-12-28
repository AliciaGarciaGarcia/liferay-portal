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

package com.liferay.user.notification.internal.search.spi.model.result.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

/**
 * @author Carlos Correa
 */
@Component(
	property = "indexer.class.name=com.liferay.portal.kernel.model.UserNotificationEvent",
	service = ModelSummaryContributor.class
)
public class UserNotificationEventModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		String languageId = LocaleUtil.toLanguageId(locale);

		return _createSummary(
			document, _localization.getLocalizedName(Field.CONTENT, languageId),
			_localization.getLocalizedName(Field.TITLE, languageId));
	}

	private Summary _createSummary(
		Document document, String contentField, String titleField) {

		Summary summary = new Summary(
			document.get(titleField, titleField),
			document.get(contentField, contentField));

		summary.setMaxContentLength(200);

		return summary;
	}

	@Reference
	private Localization _localization;

}