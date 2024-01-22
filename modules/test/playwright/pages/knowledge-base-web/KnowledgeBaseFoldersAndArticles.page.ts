/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {KnowledgeBasePage} from './KnowledgeBase.page';
import {KnowledgeBaseEditArticlePage} from './KnowledgeBaseEditArticle.page';
import {KnowledgeBaseViewArticlePage} from './KnowledgeBaseViewArticle.page';

export class KnowledgeBaseFoldersAndArticlesPage extends KnowledgeBasePage {
	readonly basicArticleMenuItem: Locator;
	readonly newButton: Locator;
	readonly page: Page;
	readonly selectAllCheckBox: Locator;
	knowledgeBaseEditArticlePage: KnowledgeBaseEditArticlePage;
	knowledgeBaseViewArticlePage: KnowledgeBaseViewArticlePage;

	constructor(page: Page) {
		super(page);
		this.basicArticleMenuItem = page.getByRole('menuitem', {
			name: 'Basic Article',
		});
		this.newButton = page.getByLabel('New', {exact: true});
		this.knowledgeBaseEditArticlePage = new KnowledgeBaseEditArticlePage(
			page
		);
		this.knowledgeBaseViewArticlePage = new KnowledgeBaseViewArticlePage(
			page
		);
		this.selectAllCheckBox = page.getByLabel(
			'Select All Items on the Page'
		);
	}

	async publishNewKnowledgeBaseArticle(content: string, title: string) {
		await this.goToCreateNewArticle();
		await this.knowledgeBaseEditArticlePage.publishNewKnowledgeBaseArticle(
			content,
			title
		);
	}

	async publishNewKnowledgeBaseArticleWithSchedule(
		content: string,
		title: string
	) {
		await this.goToCreateNewArticle();
		await this.knowledgeBaseEditArticlePage.publishNewKnowledgeBaseArticleWithSchedule(
			content,
			title
		);
	}

	private async goToCreateNewArticle() {
		await this.goToFoldersAndArticles();
		await this.newButton.waitFor();
		await this.newButton.click();
		await this.basicArticleMenuItem.waitFor();
		await this.basicArticleMenuItem.click();
	}

	async deleteKnowledgeBaseArticle(title: string) {
		await this.knowledgeBaseViewArticlePage.goto(title);
		await this.knowledgeBaseViewArticlePage.deleteKnowledgeBaseArticle();
	}

	async deleteAll(page: Page, recycleBin: boolean) {
		const disabled = await this.selectAllCheckBox.isDisabled();

		if (!disabled) {
			await page.waitForTimeout(2_000);
			await this.selectAllCheckBox.click();

			await page.getByRole('button', {name: 'Delete'}).waitFor();

			if (!recycleBin) {
				page.once('dialog', (dialog) => {
					dialog.accept().catch(() => {});
				});
				await page.getByRole('button', {name: 'Delete'}).click();
			}
			else {
				await page.getByRole('button', {name: 'Delete'}).click();
			}
		}
	}
}
