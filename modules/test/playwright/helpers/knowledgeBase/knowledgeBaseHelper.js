/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ProductMenuPage} from '../../pages/product-navigation-product-menu/productMenu.page';

export class KnowledgeBaseHelper {

	constructor(page) {
		this.productMenuPage = new ProductMenuPage(page);
		this.page = page;
	}

	async openAdmin() {
		await this.productMenuPage.goToKnowledgeBase();
	}

}