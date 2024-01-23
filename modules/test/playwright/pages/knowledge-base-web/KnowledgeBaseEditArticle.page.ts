/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FrameLocator, Locator, Page} from '@playwright/test';

import {KnowledgeBasePage} from './KnowledgeBasePage.page';

export class KnowledgeBaseEditArticlePage {
	readonly contentFrameLocator: FrameLocator;
	readonly contentTextBox: Locator;
	readonly page: Page;
	readonly publishButton: Locator;
	readonly publishMenuItem: Locator;
	readonly titlePlaceholder: Locator;

	knowledgeBasePage: KnowledgeBasePage;

	constructor(page: Page) {
		this.contentFrameLocator = page.frameLocator('iframe');
		this.contentTextBox = this.contentFrameLocator.getByRole('textbox');
		this.knowledgeBasePage = new KnowledgeBasePage(page);
		this.publishButton = page.getByRole('button', {name: 'Publish'});
		this.publishMenuItem = page.getByRole('menuitem', {name: 'Publish'});
		this.titlePlaceholder = page.getByPlaceholder('Untitled Article');
		this.page = page;
	}

	async publishNewKnowledgeBaseArticle(content: string, title: string) {
		await this.knowledgeBasePage.goToCreateNewArticle();
		await this.titlePlaceholder.fill(title);
		await this.contentTextBox.fill(content);
		await this.publishButton.click();
	}

	async publishNewKnowledgeBaseArticleWithSchedule(
		content: string,
		title: string
	) {
		await this.knowledgeBasePage.goToCreateNewArticle();
		await this.titlePlaceholder.fill(title);
		await this.contentTextBox.fill(content);
		await this.publishButton.click();
		await this.publishMenuItem.waitFor();
		await this.publishMenuItem.click();
	}
}
