/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export class ProductMenuPage {
	constructor(page) {
		this.productMenuButton = page.getByLabel(
			'Open Product Menu'
		);
		this.page = page;
	}

	async goto() {
		await this.page.goto('/');
	}

	async goToProductMenu() {
		await this.goto();
		const openProductMenuVisible = await this.productMenuButton.isVisible();

		if (openProductMenuVisible) {
			await this.productMenuButton.click();
		}
	}

	async goToKnowledgeBase() {
		await this.goToContentAndData();
		await this.page.getByRole('menuitem', { name: 'Knowledge Base' }).click();
	}

	async goToContentAndData() {
		await this.goToProductMenu();
		await this.page.getByRole('menuitem', { name: 'Content & Data' }).click();
	}

}
