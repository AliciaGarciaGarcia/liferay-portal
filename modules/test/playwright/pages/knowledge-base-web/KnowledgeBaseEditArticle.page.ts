/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FrameLocator, Locator, Page} from '@playwright/test';

export class KnowledgeBaseEditArticlePage {
	readonly contentFrameLocator: FrameLocator;
	readonly contentTextBox: Locator;
	readonly page: Page;
	readonly publishButton: Locator;
	readonly publishMenuItem: Locator;
	readonly titlePlaceholder: Locator;

	constructor(page: Page) {
		this.contentFrameLocator = page.frameLocator('iframe');
		this.contentTextBox = this.contentFrameLocator.getByRole('textbox');
		this.page = page;
		this.titlePlaceholder = page.getByPlaceholder('Untitled Article');
		this.publishButton = page.getByRole('button', {name: 'Publish'});
		this.publishMenuItem = page.getByRole('menuitem', {name: 'Publish'});
	}

	async publishNewKnowledgeBaseArticle(content: string, title: string) {
		await this.titlePlaceholder.fill(title);
		await this.contentTextBox.fill(content);
		await this.publishButton.click();
	}

	async publishNewKnowledgeBaseArticleWithSchedule(
		content: string,
		title: string
	) {
		await this.titlePlaceholder.fill(title);
		await this.contentTextBox.fill(content);
		await this.publishButton.click();
		await this.publishMenuItem.isVisible();
		await this.publishMenuItem.click();
	}
}
