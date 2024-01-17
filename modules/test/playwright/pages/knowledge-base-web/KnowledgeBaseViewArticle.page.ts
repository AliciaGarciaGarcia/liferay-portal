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

		// this.deleteMenuItem = page.getByRole('menuitem', {name: 'Delete'});

		this.page = page;

		// this.showActionsButton = this.page
		// 	.locator(
		// 		'#portlet_com_liferay_knowledge_base_web_portlet_AdminPortlet'
		// 	)
		// 	.getByRole('list')
		// 	.locator('div')
		// 	.nth(1);

	}

	async deleteKnowledgeBaseArticle() {
		this.showActionsButton = this.page
			.locator(
				'#portlet_com_liferay_knowledge_base_web_portlet_AdminPortlet'
			)
			.getByRole('list')
			.locator('div')
			.nth(1);

		await this.page.getByLabel('Show Actions').last().click();
		await this.showActionsButton.click();
		await this.page.getByRole('menuitem', {name: 'Delete'}).click();
	}
}
