/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */


import {KnowledgeBaseHelper} from '../../helpers/knowledgeBase/knowledgeBaseHelper';

import {test} from '@playwright/test';

const knowledgeBaseTest = test.extend<{_knowledgeBaseHelper: KnowledgeBaseHelper}>({
	_knowledgeBaseHelper: async ({page}, use) => {
		await use(new KnowledgeBaseHelper(page));
	},
});

export {knowledgeBaseTest};
