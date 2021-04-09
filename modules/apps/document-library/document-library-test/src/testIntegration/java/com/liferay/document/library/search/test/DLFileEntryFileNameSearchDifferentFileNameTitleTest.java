/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.test.util.search.FileEntryBlueprint;
import com.liferay.document.library.test.util.search.FileEntrySearchFixture;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Wade Cao
 */
@RunWith(Arquillian.class)
@Sync
public class DLFileEntryFileNameSearchDifferentFileNameTitleTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		WorkflowThreadLocal.setEnabled(false);

		_fileEntrySearchFixture = new FileEntrySearchFixture(dlAppLocalService);

		_fileEntrySearchFixture.setUp();

		_userSearchFixture = new UserSearchFixture();

		_userSearchFixture.setUp();

		_assetTags = _userSearchFixture.getAssetTags();
		_groups = _userSearchFixture.getGroups();
		_users = _userSearchFixture.getUsers();
		_group = _userSearchFixture.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		_userSearchFixture.tearDown();
		_fileEntrySearchFixture.tearDown();
	}

	@Test
	public void testDifferentTitleFileName() throws Exception {
		_createFileEntryFileNameTitle(
			_group, "document_4.jpg",
			"this is the title of the document number 4");

		assertSearch(
			"document_4",
			Collections.singletonList(
				"this is the title of the document number 4"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName2() throws Exception {
		_createFileEntryFileNameTitle(
			_group, "important document.txt",
			"this is the title of the document");
		assertSearch(
			"important",
			Collections.singletonList("this is the title of the document"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName3() throws Exception {
		_createFileEntryFileNameTitle(
			_group, "important.doc", "this is the title of the document");
		_createFileEntryFileNameTitle(_group, "other.doc", "other document");
		_createFileEntryFileNameTitle(_group, "image.jpg", "image");

		assertSearch(
			"important",
			Collections.singletonList("this is the title of the document"));
		assertSearch("image.jpg", Collections.singletonList("image"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName5() throws Exception {
		_createFileEntryFileNameTitle(_group, "document.jpg", "draft 1");
		_createFileEntryFileNameTitle(_group, "document(1).jpg", "draft 2");
		_createFileEntryFileNameTitle(_group, "document(2).jpg", "draft 3");

		assertSearch(
			"document", Arrays.asList("draft 1", "draft 2", "draft 3"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName6() throws Exception {
		_createFileEntryFileNameTitle(_group, "document 1.jpg", "Title 1");
		_createFileEntryFileNameTitle(_group, "document 2.jpg", "Title 2");
		_createFileEntryFileNameTitle(_group, "document 3.jpg", "Title 3");

		assertSearch(
			"document 1", Arrays.asList("Title 1", "Title 2", "Title 3"));
		assertSearch("\"document 1\"", Collections.singletonList("Title 1"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName8() throws Exception {
		_createFileEntryFileNameTitle(_group, "filename1", "document 1");
		_createFileEntryFileNameTitle(_group, "filename2", "document 2");
		_createFileEntryFileNameTitle(_group, "filename3", "document 3");

		assertSearch(
			"document 1",
			Arrays.asList("document 1", "document 2", "document 3"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testDifferentTitleFileName9() throws Exception {
		_createFileEntryFileNameTitle(_group, "filename1", "document 1");
		_createFileEntryFileNameTitle(_group, "filename2", "document 2");
		_createFileEntryFileNameTitle(_group, "filename3", "document 3");

		assertSearch("\"document 1\"", Collections.singletonList("document 1"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testExtensionAloneMatchesPathAndExtensionFields()
		throws Exception {

		_createFileEntryFileNameTitle(_group, "One.jpg", "One");
		_createFileEntryFileNameTitle(_group, "Two.jpg", "Two");

		assertSearch("jpg", Arrays.asList("One", "Two"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testExtensionAloneSubstringMatchesExtensionAndPathFields()
		throws Exception {

		_createFileEntryFileNameTitle(_group, "One.jpg", "One");
		_createFileEntryFileNameTitle(_group, "Two.JPG", "Two");

		assertSearch("jp", Collections.emptyList());
		System.out.println("----END TEST----");
	}

	@Test
	public void testExtensionDoesNotSplitFromPlainBaseName() throws Exception {
		_createFileEntryFileNameTitle(_group, "Document_1.jpg", "Document_1");
		_createFileEntryFileNameTitle(_group, "Document_2.jpg", "Document_2");
		_createFileEntryFileNameTitle(_group, "Memorandum.jpg", "Memorandum");

		assertSearch("Letter.jpg", Collections.emptyList());
		assertSearch("Memorandum", Collections.singletonList("Memorandum"));
		assertSearch("Memorandum.jpg", Collections.singletonList("Memorandum"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testExtensionSplitsFromUnusualBaseNameMatchingTitleField()
		throws Exception {

		_createFileEntryFileNameTitle(_group, "Document_1.png", "Document_1");
		_createFileEntryFileNameTitle(
			_group, "Document_2345.png", "Document_2345");

		assertSearch("asdf.png", Collections.emptyList());
		System.out.println("----END TEST----");
	}

	@Test
	public void testLPS73013() throws Exception {
		_createFileEntryFileNameTitle(_group, "myfile.txt", "myfile");
		_createFileEntryFileNameTitle(_group, "MyFile (1).txt", "MyFile (1)");
		_createFileEntryFileNameTitle(_group, "MYFILE (2).txt", "MYFILE (2)");

		assertSearch(
			"myfile", Arrays.asList("myfile", "MyFile (1)", "MYFILE (2)"));
		assertSearch("my", Arrays.asList("myfile", "MyFile (1)", "MYFILE (2)"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testLPS82588() throws Exception {
		_createFileEntryFileNameTitle(
			_group, "Document_1.jpg", "Document_1 jpg");
		_createFileEntryFileNameTitle(
			_group, "Document_1.png", "Document_1 png");
		_createFileEntryFileNameTitle(_group, "Document_2.jpg", "Document_2");
		_createFileEntryFileNameTitle(_group, "Document_3.png", "Document_3");

		assertSearch(
			"Document_1", Arrays.asList("Document_1 jpg", "Document_1 png"));
		assertSearch("asd.jpg", Collections.emptyList());
		assertSearch(
			"Document_1.jpg",
			Arrays.asList("Document_1 jpg", "Document_1 png", "Document_2"));
		assertSearch(
			"\"Document_1.jpg\"", Collections.singletonList("Document_1 jpg"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testLPS82588Relevance() throws Exception {
		String keyword = "Document_3.jpg";
		String title = "Document_3 jpg";

		_createFileEntryFileNameTitle(
			_group, "Document_1.jpg", "Document_1 jpg");
		_createFileEntryFileNameTitle(
			_group, "Document_1.png", "Document_1 png");
		_createFileEntryFileNameTitle(_group, "Document_2.jpg", "Document_2");
		_createFileEntryFileNameTitle(_group, keyword, title);
		_createFileEntryFileNameTitle(
			_group, "Document_3.png", "Document_3 png");

		Indexer<DLFileEntry> indexer = indexerRegistry.getIndexer(
			DLFileEntry.class);

		SearchContext searchContext = getSearchContext(keyword);

		Hits hits = indexer.search(searchContext);

		Document[] docs = hits.getDocs();

		Document topHit = docs[0];

		String actualTitle = topHit.get(Field.TITLE);

		Assert.assertEquals(
			(String)searchContext.getAttribute("queryString"), title,
			actualTitle);
		System.out.println("----END TEST----");
	}

	@Test
	public void testUnusualBaseNameSplitsExtensionMatchingTitleFieldAsPhraseExact()
		throws Exception {

		_createFileEntryFileNameTitle(_group, "Document_1.jpg", "Document_1");

		assertSearch("Document_1.jpg", Collections.singletonList("Document_1"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testUnusualBaseNameSplitsExtensionMatchingTitleFieldAsPhrasePrefix()
		throws Exception {

		_createFileEntryFileNameTitle(_group, "Document_1.docx", "Document_1");

		assertSearch("Document_1.doc", Collections.singletonList("Document_1"));
		System.out.println("----END TEST----");
	}

	@Test
	public void testUnusualBaseNameSplitsExtensionMismatchingTitleField()
		throws Exception {

		_createFileEntryFileNameTitle(
			_group, "Document_1234.jpg", "Document_1234");

		assertSearch("Document_1.PNG", Collections.emptyList());

		System.out.println("----END TEST----");
	}

	protected void assertSearch(String keyword, List<String> titles)
		throws Exception {

		System.out.println("");
		System.out.println(keyword);
		System.out.println(titles);
		Indexer<DLFileEntry> indexer = indexerRegistry.getIndexer(
			DLFileEntry.class);

		SearchContext searchContext = getSearchContext(keyword);

		Hits hits = indexer.search(searchContext);

		DocumentsAssert.assertValuesIgnoreRelevance(
			(String)searchContext.getAttribute("queryString"), hits.getDocs(),
			Field.TITLE, titles);
	}

	protected long getAdminUserId(Group group) throws Exception {
		User user = UserTestUtil.getAdminUser(group.getCompanyId());

		return user.getUserId();
	}

	protected SearchContext getSearchContext(String keyword) throws Exception {
		SearchContext searchContext = _userSearchFixture.getSearchContext(
			keyword);

		Group currentGroup = _groups.get(0);

		searchContext.setGroupIds(new long[] {currentGroup.getGroupId()});

		return searchContext;
	}

	@Inject
	protected static DLAppLocalService dlAppLocalService;

	@Inject
	protected static IndexerRegistry indexerRegistry;

	private void _createFileEntryFileNameTitle(
			Group group, String fileName, String title)
		throws Exception {

		System.out.print(fileName);
		System.out.print(",");
		System.out.print(title);
		System.out.print(",");

		_fileEntrySearchFixture.addFileEntry(
			new FileEntryBlueprint() {
				{
					setFileName(fileName);
					setGroupId(group.getGroupId());
					setTitle(title);
					setUserId(getAdminUserId(group));
				}
			});
	}

	@DeleteAfterTestRun
	private List<AssetTag> _assetTags;

	private FileEntrySearchFixture _fileEntrySearchFixture;
	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<User> _users;

	private UserSearchFixture _userSearchFixture;

}