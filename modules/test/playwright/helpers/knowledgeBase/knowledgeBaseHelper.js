/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
export class KnowledgeBaseHelper {

	constructor(page) {
		this.page = page;
	}

	async openAdmin() {
		await this.page.goto('/');

		const openProductMenuVisible = await this.page.getByLabel('Open Product Menu').isVisible();

		if (openProductMenuVisible) {
			await this.page.getByLabel('Open Product Menu').click();
		}

		await this.page.getByRole('menuitem', { name: 'Content & Data' }).click();
		await this.page.getByRole('menuitem', { name: 'Knowledge Base' }).click();
	}

}