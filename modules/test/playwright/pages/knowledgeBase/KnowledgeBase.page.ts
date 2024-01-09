/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {ProductMenuPage} from '../product-navigation-product-menu/productMenu.page';

export class KnowledgeBasePage {
	readonly foldersAndArticlesButton: Locator;
	readonly page: Page;
	readonly productMenuPage: ProductMenuPage;
	readonly suggestionsButton: Locator;
	readonly templatesButton: Locator;

	constructor(page: Page) {
		this.foldersAndArticlesButton = page.getByLabel('Folders and Articles');
		this.page = page;
		this.productMenuPage = new ProductMenuPage(page);
		this.suggestionsButton = page.getByLabel('Suggestions');
		this.templatesButton = page.getByLabel('Templates');
	}

	async goto() {
		await this.productMenuPage.goToKnowledgeBaseMenuItem();
	}

	async goToFoldersAndArticles() {
		const foldersAndArticlesButtonVisible = await this.foldersAndArticlesButton.isVisible();

		if (foldersAndArticlesButtonVisible) {
			await this.foldersAndArticlesButton.click();
		}
	}

	async goToSuggestions() {
		const suggestionsButtonVisible = await this.suggestionsButton.isVisible();

		if (suggestionsButtonVisible) {
			await this.suggestionsButton.click();
		}
	}
	async goToTemplates() {
		const templatesButtonVisible = await this.templatesButton.isVisible();

		if (templatesButtonVisible) {
			await this.templatesButton.click();
		}
	}
}
