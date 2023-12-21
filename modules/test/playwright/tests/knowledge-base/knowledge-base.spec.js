/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {test as apiHelpersTest} from '../../fixtures/apiHelpers.fixture';
import {test as knowledgeBaseTest} from '../../fixtures/knowledgeBase/knowldegbeBase.fixure';

export const test = mergeTests(apiHelpersTest, knowledgeBaseTest);

async function _createSimpleKBArticle(
	_knowledgeBaseHelper,
	page,
	content,
	title,
) {
	await _knowledgeBaseHelper.openAdmin();

	await page.getByLabel('New').click();
	await page.getByRole('menuitem', {name: 'Basic Article'}).click();
	await page.getByPlaceholder('Untitled Article').fill(title);
	await page.frameLocator('iframe').getByRole('textbox').fill(content);
}

const kbArticleContent = 'KB Article Content';
const kbArticleTitle = 'KB Article Title';

test('Create and delete a Knowledge Base Article', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	page,
}) => {
	await page.goto('/');

	// this only affects that the portal-ext.properties has the FF active, but on this stage we can not know it the FF were enabled or not.

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'false');

	await _createSimpleKBArticle(
		_knowledgeBaseHelper,
		page,
		kbArticleContent,
		kbArticleTitle,
	);

	await page.getByRole('button', {name: 'Publish'}).click();

	const _firstKBArticle = page.locator(
		'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]'
	);

	await expect(
		_firstKBArticle.getByRole('link', {name: kbArticleTitle})
	).toBeVisible();

	await _firstKBArticle.getByLabel('Show Actions').click();

	await page.once('dialog', (dialog) => {
		console.log(`Dialog message: ${dialog.message()}`);
		dialog.accept().catch(() => {});
	});
	await page.getByRole('menuitem', {name: 'Delete'}).click();

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();
	await page.close();
});

test('Publish and delete with schedule menu', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	page,
}) => {
	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'true');

	await _createSimpleKBArticle(
		_knowledgeBaseHelper,
		page,
		kbArticleContent,
		kbArticleTitle,
	);

	await page.getByRole('button', {name: 'Publish'}).click();
	await expect(page.getByRole('menuitem', {name: 'Publish'})).toBeVisible();
	await expect(page.getByRole('menuitem', {name: 'Publish'})).toBeVisible();
	await page.getByRole('menuitem', {name: 'Publish'}).click();

	await expect(
		page
			.locator(
				'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]'
			)
			.getByRole('link', {name: kbArticleTitle})
	).toBeVisible();

	await page
		.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]'
		)
		.getByLabel('Show Actions')
		.click();

	await page.getByRole('menuitem', {name: 'Delete'}).click();

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'false');

	await page.close();
});
