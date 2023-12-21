/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {KnowledgeBasePage} from './KnowledgeBase.page';

export class KnowledgeBaseFoldersAndArticlesPage extends KnowledgeBasePage {
	constructor(page) {
		super(page);
		this.newButton = page.getByLabel('New');
		this.basicArticleMenuItem = page.getByRole('menuitem', {
			name: 'Basic Article',
		});
		this.titlePlaceholder = page.getByPlaceholder('Untitled Article');
		this.contentFrameLocator = page.frameLocator('iframe');
		this.contentTextbox = this.contentFrameLocator.getByRole('textbox');
	}

	async createNewKnowledgeBaseArticle(content, title) {
		await this.goToFoldersAndArticles();
		await this.newButton.click();
		await this.basicArticleMenuItem.click();
		await this.titlePlaceholder.fill(title);
		await this.contentTextbox.fill(content);
	}
}
