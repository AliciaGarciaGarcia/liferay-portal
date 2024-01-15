/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpers.fixture';
import {knowledgeBaseTest} from '../../fixtures/knowledgeBase/knowldegbeBase.fixture';
import {getRandomString} from '../../utils/util';

export const test = mergeTests(apiHelpersTest, knowledgeBaseTest);

test('KBArticle - Create and delete', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	page,
}) => {
	await page.goto('/');

	// this only affects that the portal-ext.properties has the FF active, but on this stage we can not know it the FF were enabled or not.

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	const content = getRandomString();
	const title = getRandomString();

	await _knowledgeBaseHelper.createNewKnowledgeBaseArticle(content, title);

	await expect(page.getByRole('link', {name: title})).toBeVisible();

	const _firstKBArticle = page.locator(
		'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]'
	);

	await expect(
		_firstKBArticle.getByRole('link', {name: title})
	).toBeVisible();

	await _firstKBArticle.getByLabel('Show Actions').click();

	page.once('dialog', (dialog) => {
		console.log(`Dialog message: ${dialog.message()}`);
		dialog.accept().catch(() => {});
	});
	await page.getByRole('menuitem', {name: 'Delete'}).click();

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await page.close();
});

test('KBArticle - Publish and delete with schedule menu', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	page,
}) => {
	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', true);

	const content = getRandomString();
	const title = getRandomString();

	await _knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		content,
		title
	);

	await expect(page.getByRole('link', {name: title})).toBeVisible();

	await _knowledgeBaseHelper.deleteKnowledgeBaseArticle(title);

	await expect(
		page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_recycleBinAlert"]'
		)
	).toBeVisible();

	await expect(page.getByRole('link', {name: title})).toBeHidden();

	// await page.getByLabel(title+'\n\t\t\t\t\t\t\n\t\t\t\t\t\n\n\t\t\t\t\t\n\t\t\t\t\t\tTest Test, modified 0 Seconds ago.\n\t\t\t\t\t\n\n\t\t\t\t\t\n\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\t\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\n\n\t\n\n\t\n\t\t\n\n\t\t\n\t\t\tApproved').check();
	// await page.getByRole('button', { name: 'Delete' }).click();

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await page.close();
});


test('KBArticle - Delete all', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	 page}) => {

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	const content = getRandomString();
	const title = getRandomString();

	await _knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		content,
		title
	);

	const selectAll = page.getByLabel('Select All Items on the Page');

	const disabled = await selectAll.isDisabled();

	if (!disabled) {
		await selectAll.click();

		page.once('dialog', (dialog) => {
			console.log(`Dialog message: ${dialog.message()}`);
			dialog.accept().catch(() => {});
		});
		
		await page.getByRole('button', {name: 'Delete'}).click();
	}

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await page.close();
});

test('KBArticle - Delete all - recycle Bin', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	 page}) => {

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', true);

	await _knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		getRandomString(),
		getRandomString()
	);

	const selectAll = page.getByLabel('Select All Items on the Page');

	const disabled = await selectAll.isDisabled();

	if (!disabled) {
		await selectAll.click();
		
		await page.getByRole('button', {name: 'Delete'}).click();
	}

	await expect(
		page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_recycleBinAlert"]'
		)
	).toBeVisible();

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await page.close();
});


test.afterAll('Delete all', async ({
	_apiHelpers,
	_knowledgeBaseHelper,
	 page}) => {

	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await _knowledgeBaseHelper.openAdmin();

	const selectAll = page.getByLabel('Select All Items on the Page');

	const disabled = await selectAll.isDisabled();

	if (!disabled) {
		await selectAll.click();

		page.once('dialog', (dialog) => {
			console.log(`Dialog message: ${dialog.message()}`);
			dialog.accept().catch(() => {});
		});
		
		await page.getByRole('button', {name: 'Delete'}).click();
	}

	await page.close();
});
