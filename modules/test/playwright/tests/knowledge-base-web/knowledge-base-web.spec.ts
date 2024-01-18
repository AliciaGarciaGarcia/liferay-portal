/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {knowledgeBaseTest} from '../../fixtures/knowledgeBase/knowldegbeBaseTest';
import {getRandomString} from '../../utils/util';

export const test = mergeTests(apiHelpersTest, knowledgeBaseTest);

test('KBArticle - Publish and delete', async ({
	apiHelpers,
	knowledgeBaseHelper,
	page,
}) => {

	// this only affects that the portal-ext.properties has the FF active, but on this stage we can not know it the FF were enabled or not.

	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	const content = getRandomString();
	const title = getRandomString();

	await knowledgeBaseHelper.createNewKnowledgeBaseArticle(content, title);

	await expect(page.getByRole('link', {name: title})).toBeVisible();

	const kbArticle = await page
		.locator(
			'#_com_liferay_knowledge_base_web_portlet_AdminPortlet_kbObjectsSearchContainer .list-group-item'
		)
		.filter({hasText: title});

	await kbArticle.getByLabel('Show Actions').click();

	page.once('dialog', (dialog) => {
		dialog.accept().catch(() => {});
	});
	await page.getByRole('menuitem', {name: 'Delete'}).click();

	await expect(kbArticle).toBeHidden();

	await page.close();
});

test('KBArticle - Publish and delete - Schedule menu', async ({
	apiHelpers,
	knowledgeBaseHelper,
	page,
}) => {
	
	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', true);

	const content = getRandomString();
	const title = getRandomString();

	await knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		content,
		title
	);

	await expect(page.getByRole('link', {name: title})).toBeVisible();

	await knowledgeBaseHelper.deleteKnowledgeBaseArticle(page, title);

	await expect(
		page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_recycleBinAlert"]'
		)
	).toBeVisible();

	await expect(page.getByRole('link', {name: title})).toBeHidden();

	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await page.close();
});

test('KBArticle - Delete all', async ({
	apiHelpers,
	knowledgeBaseHelper,
	page,
}) => {
	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		getRandomString(),
		getRandomString()
	);

	await knowledgeBaseHelper.deleteAll(page, false);

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await page.close();
});

test('KBArticle - Delete all - recycle Bin', async ({
	apiHelpers,
	knowledgeBaseHelper,
	page,
}) => {
	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', true);

	await knowledgeBaseHelper.createNewKnowledgeBaseArticleWithSchedule(
		getRandomString(),
		getRandomString()
	);

	await knowledgeBaseHelper.deleteAll(page, false);

	await expect(
		page.locator(
			'[id="_com_liferay_knowledge_base_web_portlet_AdminPortlet_recycleBinAlert"]'
		)
	).toBeVisible();

	await expect(
		page.getByRole('heading', {name: 'Knowledge base is empty.'})
	).toBeVisible();

	await apiHelpers.featureFlag.updateFeatureFlag('LPS-188058', false);

	await page.close();
});
