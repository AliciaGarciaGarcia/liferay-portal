/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {KnowledgeBasePage} from '../pages/knowledge-base-web/KnowledgeBasePage.page';

const knowledgeBasePages = test.extend<{
	knowledgeBasePage: KnowledgeBasePage;
}>({
	knowledgeBasePage: async ({page}, use) => {
		await use(new KnowledgeBasePage(page));
	},
});

export {knowledgeBasePages};
