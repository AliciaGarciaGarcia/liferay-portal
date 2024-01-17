/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class KnowledgeBaseViewArticlePage {
	readonly page: Page;
	showActionsButton: Locator;
	deleteMenuItem: Locator;

	constructor(page: Page) {
		this.showActionsButton = page.getByLabel('Show Actions');
		this.page = page;
	}

	async deleteKnowledgeBaseArticle() {
		// await this.showActionsButton.locator('[aria-haspopup]').waitFor();

		await this.showActionsButton.click();
		await this.page.getByRole('menuitem', {name: 'Delete'}).click();
	}
}
