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

package com.liferay.portlet.documentlibrary.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryFriendlyURLException;
import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.document.library.kernel.service.DLFileEntryFriendlyURLLocalServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryFriendlyURLPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryFriendlyURLUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DLFileEntryFriendlyURLPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DLFileEntryFriendlyURLUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DLFileEntryFriendlyURL> iterator =
			_dlFileEntryFriendlyURLs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = _persistence.create(pk);

		Assert.assertNotNull(dlFileEntryFriendlyURL);

		Assert.assertEquals(dlFileEntryFriendlyURL.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		_persistence.remove(newDLFileEntryFriendlyURL);

		DLFileEntryFriendlyURL existingDLFileEntryFriendlyURL =
			_persistence.fetchByPrimaryKey(
				newDLFileEntryFriendlyURL.getPrimaryKey());

		Assert.assertNull(existingDLFileEntryFriendlyURL);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDLFileEntryFriendlyURL();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL = _persistence.create(
			pk);

		newDLFileEntryFriendlyURL.setMvccVersion(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setCtCollectionId(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setUuid(RandomTestUtil.randomString());

		newDLFileEntryFriendlyURL.setGroupId(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setCompanyId(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setUserId(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setUserName(RandomTestUtil.randomString());

		newDLFileEntryFriendlyURL.setCreateDate(RandomTestUtil.nextDate());

		newDLFileEntryFriendlyURL.setModifiedDate(RandomTestUtil.nextDate());

		newDLFileEntryFriendlyURL.setFileEntryId(RandomTestUtil.nextLong());

		newDLFileEntryFriendlyURL.setFriendlyURL(RandomTestUtil.randomString());

		newDLFileEntryFriendlyURL.setLanguageId(RandomTestUtil.randomString());

		newDLFileEntryFriendlyURL.setLastPublishDate(RandomTestUtil.nextDate());

		_dlFileEntryFriendlyURLs.add(
			_persistence.update(newDLFileEntryFriendlyURL));

		DLFileEntryFriendlyURL existingDLFileEntryFriendlyURL =
			_persistence.findByPrimaryKey(
				newDLFileEntryFriendlyURL.getPrimaryKey());

		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getMvccVersion(),
			newDLFileEntryFriendlyURL.getMvccVersion());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getCtCollectionId(),
			newDLFileEntryFriendlyURL.getCtCollectionId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getUuid(),
			newDLFileEntryFriendlyURL.getUuid());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getFileEntryFriendlyURLId(),
			newDLFileEntryFriendlyURL.getFileEntryFriendlyURLId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getGroupId(),
			newDLFileEntryFriendlyURL.getGroupId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getCompanyId(),
			newDLFileEntryFriendlyURL.getCompanyId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getUserId(),
			newDLFileEntryFriendlyURL.getUserId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getUserName(),
			newDLFileEntryFriendlyURL.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingDLFileEntryFriendlyURL.getCreateDate()),
			Time.getShortTimestamp(newDLFileEntryFriendlyURL.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingDLFileEntryFriendlyURL.getModifiedDate()),
			Time.getShortTimestamp(
				newDLFileEntryFriendlyURL.getModifiedDate()));
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getFileEntryId(),
			newDLFileEntryFriendlyURL.getFileEntryId());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getFriendlyURL(),
			newDLFileEntryFriendlyURL.getFriendlyURL());
		Assert.assertEquals(
			existingDLFileEntryFriendlyURL.getLanguageId(),
			newDLFileEntryFriendlyURL.getLanguageId());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingDLFileEntryFriendlyURL.getLastPublishDate()),
			Time.getShortTimestamp(
				newDLFileEntryFriendlyURL.getLastPublishDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByFileEntryId() throws Exception {
		_persistence.countByFileEntryId(RandomTestUtil.nextLong());

		_persistence.countByFileEntryId(0L);
	}

	@Test
	public void testCountByF_F() throws Exception {
		_persistence.countByF_F(RandomTestUtil.nextLong(), "");

		_persistence.countByF_F(0L, "null");

		_persistence.countByF_F(0L, (String)null);
	}

	@Test
	public void testCountByF_L() throws Exception {
		_persistence.countByF_L(RandomTestUtil.nextLong(), "");

		_persistence.countByF_L(0L, "null");

		_persistence.countByF_L(0L, (String)null);
	}

	@Test
	public void testCountByF_LArrayable() throws Exception {
		_persistence.countByF_L(
			new long[] {RandomTestUtil.nextLong(), 0L},
			RandomTestUtil.randomString());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		DLFileEntryFriendlyURL existingDLFileEntryFriendlyURL =
			_persistence.findByPrimaryKey(
				newDLFileEntryFriendlyURL.getPrimaryKey());

		Assert.assertEquals(
			existingDLFileEntryFriendlyURL, newDLFileEntryFriendlyURL);
	}

	@Test(expected = NoSuchFileEntryFriendlyURLException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DLFileEntryFriendlyURL> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DLFileEntryFriendlyURL", "mvccVersion", true, "ctCollectionId",
			true, "uuid", true, "fileEntryFriendlyURLId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "fileEntryId", true, "friendlyURL",
			true, "languageId", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		DLFileEntryFriendlyURL existingDLFileEntryFriendlyURL =
			_persistence.fetchByPrimaryKey(
				newDLFileEntryFriendlyURL.getPrimaryKey());

		Assert.assertEquals(
			existingDLFileEntryFriendlyURL, newDLFileEntryFriendlyURL);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileEntryFriendlyURL missingDLFileEntryFriendlyURL =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDLFileEntryFriendlyURL);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL1 =
			addDLFileEntryFriendlyURL();
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL2 =
			addDLFileEntryFriendlyURL();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileEntryFriendlyURL1.getPrimaryKey());
		primaryKeys.add(newDLFileEntryFriendlyURL2.getPrimaryKey());

		Map<Serializable, DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, dlFileEntryFriendlyURLs.size());
		Assert.assertEquals(
			newDLFileEntryFriendlyURL1,
			dlFileEntryFriendlyURLs.get(
				newDLFileEntryFriendlyURL1.getPrimaryKey()));
		Assert.assertEquals(
			newDLFileEntryFriendlyURL2,
			dlFileEntryFriendlyURLs.get(
				newDLFileEntryFriendlyURL2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dlFileEntryFriendlyURLs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileEntryFriendlyURL.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dlFileEntryFriendlyURLs.size());
		Assert.assertEquals(
			newDLFileEntryFriendlyURL,
			dlFileEntryFriendlyURLs.get(
				newDLFileEntryFriendlyURL.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dlFileEntryFriendlyURLs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDLFileEntryFriendlyURL.getPrimaryKey());

		Map<Serializable, DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dlFileEntryFriendlyURLs.size());
		Assert.assertEquals(
			newDLFileEntryFriendlyURL,
			dlFileEntryFriendlyURLs.get(
				newDLFileEntryFriendlyURL.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DLFileEntryFriendlyURLLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<DLFileEntryFriendlyURL>() {

				@Override
				public void performAction(
					DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

					Assert.assertNotNull(dlFileEntryFriendlyURL);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLFileEntryFriendlyURL.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fileEntryFriendlyURLId",
				newDLFileEntryFriendlyURL.getFileEntryFriendlyURLId()));

		List<DLFileEntryFriendlyURL> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DLFileEntryFriendlyURL existingDLFileEntryFriendlyURL = result.get(0);

		Assert.assertEquals(
			existingDLFileEntryFriendlyURL, newDLFileEntryFriendlyURL);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLFileEntryFriendlyURL.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fileEntryFriendlyURLId", RandomTestUtil.nextLong()));

		List<DLFileEntryFriendlyURL> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLFileEntryFriendlyURL.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fileEntryFriendlyURLId"));

		Object newFileEntryFriendlyURLId =
			newDLFileEntryFriendlyURL.getFileEntryFriendlyURLId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fileEntryFriendlyURLId",
				new Object[] {newFileEntryFriendlyURLId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFileEntryFriendlyURLId = result.get(0);

		Assert.assertEquals(
			existingFileEntryFriendlyURLId, newFileEntryFriendlyURLId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLFileEntryFriendlyURL.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fileEntryFriendlyURLId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fileEntryFriendlyURLId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newDLFileEntryFriendlyURL.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		DLFileEntryFriendlyURL newDLFileEntryFriendlyURL =
			addDLFileEntryFriendlyURL();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLFileEntryFriendlyURL.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fileEntryFriendlyURLId",
				newDLFileEntryFriendlyURL.getFileEntryFriendlyURLId()));

		List<DLFileEntryFriendlyURL> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		Assert.assertEquals(
			dlFileEntryFriendlyURL.getUuid(),
			ReflectionTestUtil.invoke(
				dlFileEntryFriendlyURL, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(dlFileEntryFriendlyURL.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				dlFileEntryFriendlyURL, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));

		Assert.assertEquals(
			Long.valueOf(dlFileEntryFriendlyURL.getFileEntryId()),
			ReflectionTestUtil.<Long>invoke(
				dlFileEntryFriendlyURL, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "fileEntryId"));
		Assert.assertEquals(
			dlFileEntryFriendlyURL.getLanguageId(),
			ReflectionTestUtil.invoke(
				dlFileEntryFriendlyURL, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "languageId"));
	}

	protected DLFileEntryFriendlyURL addDLFileEntryFriendlyURL()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = _persistence.create(pk);

		dlFileEntryFriendlyURL.setMvccVersion(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setCtCollectionId(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setUuid(RandomTestUtil.randomString());

		dlFileEntryFriendlyURL.setGroupId(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setCompanyId(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setUserId(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setUserName(RandomTestUtil.randomString());

		dlFileEntryFriendlyURL.setCreateDate(RandomTestUtil.nextDate());

		dlFileEntryFriendlyURL.setModifiedDate(RandomTestUtil.nextDate());

		dlFileEntryFriendlyURL.setFileEntryId(RandomTestUtil.nextLong());

		dlFileEntryFriendlyURL.setFriendlyURL(RandomTestUtil.randomString());

		dlFileEntryFriendlyURL.setLanguageId(RandomTestUtil.randomString());

		dlFileEntryFriendlyURL.setLastPublishDate(RandomTestUtil.nextDate());

		_dlFileEntryFriendlyURLs.add(
			_persistence.update(dlFileEntryFriendlyURL));

		return dlFileEntryFriendlyURL;
	}

	private List<DLFileEntryFriendlyURL> _dlFileEntryFriendlyURLs =
		new ArrayList<DLFileEntryFriendlyURL>();
	private DLFileEntryFriendlyURLPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}