/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export class ProductMenuPage {
	constructor(page) {
		this.closeProductMenuButton = page.getByLabel('Close Product Menu');
		this.contentAndDataMenuItem = page.getByRole('menuitem', {
			exact: true,
			name: 'Content & Data',
		});
		this.knowledgeBaseMenuItem = page.getByRole('menuitem', {
			exact: true,
			name: 'Knowledge Base',
		});
		this.openProductMenuButton = page.getByLabel('Open Product Menu');
		this.page = page;
	}

	async goto() {
		await this.page.goto('/');
	}

	async openToProductMenu() {
		await this.goto();
		let openProductMenuVisible = await this.openProductMenuButton.isVisible();

		if (openProductMenuVisible) {
			await this.openProductMenuButton.click();
		}
	}

	async closeToProductMenu() {
		await this.goto();
		let closeProductMenuVisible = await this.closeProductMenuButton.isVisible();

		if (closeProductMenuVisible) {
			await this.closeProductMenuButton.click();
		}
	}

	async goToKnowledgeBaseMenuItem() {
		await this.goToContentAndData();
		await this.knowledgeBaseMenuItem.click();
	}

	async goToContentAndData() {
		await this.openToProductMenu();
		await this.contentAndDataMenuItem.click();
	}
}
