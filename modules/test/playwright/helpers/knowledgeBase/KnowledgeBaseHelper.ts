/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Page} from '@playwright/test';

import {KnowledgeBaseFoldersAndArticlesPage} from '../../pages/knowledge-base-web/KnowledgeBaseFoldersAndArticles.page';
import {ProductMenuPage} from '../../pages/product-navigation-product-menu/productMenu.page';

export class KnowledgeBaseHelper {
	readonly knowledgeBaseFoldersAndArticlesPage: KnowledgeBaseFoldersAndArticlesPage;
	readonly page: Page;
	readonly productMenuPage: ProductMenuPage;

	constructor(page: Page) {
		this.productMenuPage = new ProductMenuPage(page);
		this.knowledgeBaseFoldersAndArticlesPage = new KnowledgeBaseFoldersAndArticlesPage(
			page
		);
		this.page = page;
	}

	async openAdmin() {
		await this.knowledgeBaseFoldersAndArticlesPage.goto();
	}

	async createNewKnowledgeBaseArticle(content: any, title: any) {
		await this.openAdmin();
		await this.knowledgeBaseFoldersAndArticlesPage.createNewKnowledgeBaseArticle(
			content,
			title
		);
	}
}
