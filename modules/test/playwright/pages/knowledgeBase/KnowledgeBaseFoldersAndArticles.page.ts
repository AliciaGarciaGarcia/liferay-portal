/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FrameLocator, Locator, Page} from '@playwright/test';

import {KnowledgeBasePage} from './KnowledgeBase.page';

export class KnowledgeBaseFoldersAndArticlesPage extends KnowledgeBasePage {
	readonly basicArticleMenuItem: Locator;
	readonly contentFrameLocator: FrameLocator;
	readonly contentTextBox: Locator;
	readonly newButton: Locator;
	readonly page: Page;
	readonly titlePlaceholder: Locator;

	constructor(page: Page) {
		super(page);
		this.basicArticleMenuItem = page.getByRole('menuitem', {
			name: 'Basic Article',
		});

		this.contentFrameLocator = page.frameLocator('iframe');
		this.contentTextBox = this.contentFrameLocator.getByRole('textbox');

		this.newButton = page.getByLabel('New');
		this.titlePlaceholder = page.getByPlaceholder('Untitled Article');
	}

	async createNewKnowledgeBaseArticle(content: string, title: string) {
		await this.goToFoldersAndArticles();
		await this.newButton.click();
		await this.basicArticleMenuItem.click();
		await this.titlePlaceholder.fill(title);
		await this.contentTextBox.fill(content);
	}
}
