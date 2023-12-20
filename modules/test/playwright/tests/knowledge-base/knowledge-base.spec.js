/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
import {expect, mergeTests} from "@playwright/test";
import {test as apiHelpersTest} from '../../fixtures/apiHelpers.fixture';
import {
	test as knowledgeBaseTest
} from '../../fixtures/knowledgeBase/knowldegbeBase.fixure';

export const test = mergeTests(
	apiHelpersTest,
	knowledgeBaseTest
);

test('Create and delete a Knowledge Base Article', async ({
															  _apiHelpers, page
														  }) => {
	await page.goto('/');

	//this only affects that the portal-ext.properties has the FF active
	await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'false');

	const openProductMenuVisible = await page.getByLabel(
		'Open Product Menu').isVisible();

	if (openProductMenuVisible) {
		await page.getByLabel('Open Product Menu').click();
	}

	await page.getByRole('menuitem', {name: 'Content & Data'}).click();
	await page.getByRole('menuitem', {name: 'Knowledge Base'}).click();
	await page.getByLabel('New').click();
	await page.getByRole('menuitem', {name: 'Basic Article'}).click();
	await page.getByPlaceholder('Untitled Article').fill('Test Article');
	await page.frameLocator('iframe').getByRole('textbox').fill('test content');
	await page.getByRole('button', {name: 'Publish'}).click();

	await expect(page.locator(
		'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]').getByRole(
		'link', {name: 'Test Article'})).toBeVisible();
	await page.locator(
		'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]').getByLabel(
		'Show Actions').click();
	await page.once('dialog', dialog => {
		console.log(`Dialog message: ${dialog.message()}`);
		dialog.accept().catch(() => {
		});
	});
	await page.getByRole('menuitem', {name: 'Delete'}).click();

	await expect(page.getByRole(
		'heading',
		{name: 'Knowledge base is empty.'})).toBeVisible();
	await page.close();
});

test(
	'Publish and delete with schedule menu',
	async ({
			   _apiHelpers, _knowledgeBaseHelper, page
		   }) => {

		await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'true');

		await _knowledgeBaseHelper.openAdmin();

		await page.getByLabel('New').click();
		await page.getByRole('menuitem', {name: 'Basic Article'}).click();
		await page.getByPlaceholder('Untitled Article').fill('Test Article');
		await page.frameLocator('iframe').getByRole('textbox').fill(
			'test content');
		await page.getByRole('button', {name: 'Publish'}).click();
		await expect(
			page.getByRole('menuitem', {name: 'Publish'})).toBeVisible();
		await page.getByRole('menuitem', {name: 'Publish'}).click();

		await expect(page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]').getByRole(
			'link', {name: 'Test Article'})).toBeVisible();

		await page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjects_1"]').getByLabel(
			'Show Actions').click();
		await page.once('dialog', dialog => {
			console.log(`Dialog message: ${dialog.message()}`);
			dialog.accept().catch(() => {
			});
		});
		await page.getByRole('menuitem', {name: 'Delete'}).click();

		await expect(page.getByRole(
			'heading',
			{name: 'Knowledge base is empty.'})).toBeVisible();

		await _apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', 'false');

		await page.close();
	});
