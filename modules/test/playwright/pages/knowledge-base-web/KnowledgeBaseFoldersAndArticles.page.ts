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
	knowledgeBaseEditArticlePage: KnowledgeBaseEditArticlePage;
	knowledgeBaseViewArticlePage: KnowledgeBaseViewArticlePage;

	constructor(page: Page) {
		super(page);
		this.basicArticleMenuItem = page.getByRole('menuitem', {
			name: 'Basic Article',
		});
		this.newButton = page.getByLabel('New');
		this.knowledgeBaseEditArticlePage = new KnowledgeBaseEditArticlePage(
			page
		);
		this.knowledgeBaseViewArticlePage = new KnowledgeBaseViewArticlePage(
			page
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
		await this.newButton.click();
		await this.basicArticleMenuItem.click();
	}

	async deleteKnowledgeBaseArticle(page: Page, title: string) {

		// const regex: RegExp = /(?=.*${title})/g;
		// const regex: RegExp = new RegExp("/^\s*"+title+"\s*$/i", "g");
		// const regex: RegExp = new RegExp('/^'+title+'\s+$/i');
		//
		// //const article = page.getByLabel(/^\s*${title}\s*$/i);
		// const article = page.getByLabel(new RegExp(title+'\s+$/i'));
		//  if(await article.isVisible()){
		//  	await article.check();
		// 	 page.once('dialog', (dialog) => {
		// 		 console.log(`Dialog message: ${dialog.message()}`);
		// 		 dialog.accept().catch(() => {});
		// 	 });
		// 	await page.getByRole('button', { name: 'Delete' }).click();
		//  }

		await page.getByRole('link', {name: title}).click();

		await this.knowledgeBaseViewArticlePage.deleteKnowledgeBaseArticle();
	}

	async deleteAll(page: Page, recycleBin: boolean) {
		const selectAll = page.getByLabel('Select All Items on the Page');

		const disabled = await selectAll.isDisabled();

		if (!disabled) {
			await selectAll.click();

			if (!recycleBin) {
				page.once('dialog', (dialog) => {
					console.log(`Dialog message: ${dialog.message()}`);
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
