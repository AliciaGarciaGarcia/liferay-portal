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

package com.liferay.portlet.documentlibrary.service.persistence.impl;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryFriendlyURLException;
import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURLTable;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryFriendlyURLPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryFriendlyURLUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.change.tracking.CTColumnResolutionType;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.change.tracking.helper.CTPersistenceHelperUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryFriendlyURLImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryFriendlyURLModelImpl;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the dl file entry friendly url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileEntryFriendlyURLPersistenceImpl
	extends BasePersistenceImpl<DLFileEntryFriendlyURL>
	implements DLFileEntryFriendlyURLPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DLFileEntryFriendlyURLUtil</code> to access the dl file entry friendly url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DLFileEntryFriendlyURLImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (!uuid.equals(dlFileEntryFriendlyURL.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByUuid_First(
			String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByUuid_First(
			uuid, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUuid_First(
		String uuid,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByUuid_Last(
			String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByUuid_Last(
			uuid, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUuid_Last(
		String uuid,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByUuid_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		uuid = Objects.toString(uuid, "");

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, dlFileEntryFriendlyURL, uuid, orderByComparator, true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByUuid_PrevAndNext(
				session, dlFileEntryFriendlyURL, uuid, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByUuid_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		String uuid,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByUuid;

			finderArgs = new Object[] {uuid};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"dlFileEntryFriendlyURL.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(dlFileEntryFriendlyURL.uuid IS NULL OR dlFileEntryFriendlyURL.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByUUID_G(
			uuid, groupId);

		if (dlFileEntryFriendlyURL == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFileEntryFriendlyURLException(sb.toString());
		}

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		Object[] finderArgs = null;

		if (useFinderCache && productionMode) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache && productionMode) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByUUID_G, finderArgs);
		}

		if (result instanceof DLFileEntryFriendlyURL) {
			DLFileEntryFriendlyURL dlFileEntryFriendlyURL =
				(DLFileEntryFriendlyURL)result;

			if (!Objects.equals(uuid, dlFileEntryFriendlyURL.getUuid()) ||
				(groupId != dlFileEntryFriendlyURL.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<DLFileEntryFriendlyURL> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache && productionMode) {
						FinderCacheUtil.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					DLFileEntryFriendlyURL dlFileEntryFriendlyURL = list.get(0);

					result = dlFileEntryFriendlyURL;

					cacheResult(dlFileEntryFriendlyURL);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DLFileEntryFriendlyURL)result;
		}
	}

	/**
	 * Removes the dl file entry friendly url where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dl file entry friendly url that was removed
	 */
	@Override
	public DLFileEntryFriendlyURL removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByUUID_G(
			uuid, groupId);

		return remove(dlFileEntryFriendlyURL);
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByUUID_G;

			finderArgs = new Object[] {uuid, groupId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"dlFileEntryFriendlyURL.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(dlFileEntryFriendlyURL.uuid IS NULL OR dlFileEntryFriendlyURL.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"dlFileEntryFriendlyURL.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (!uuid.equals(dlFileEntryFriendlyURL.getUuid()) ||
						(companyId != dlFileEntryFriendlyURL.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByUuid_C_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		uuid = Objects.toString(uuid, "");

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, dlFileEntryFriendlyURL, uuid, companyId,
				orderByComparator, true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByUuid_C_PrevAndNext(
				session, dlFileEntryFriendlyURL, uuid, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByUuid_C_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		String uuid, long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByUuid_C;

			finderArgs = new Object[] {uuid, companyId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"dlFileEntryFriendlyURL.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(dlFileEntryFriendlyURL.uuid IS NULL OR dlFileEntryFriendlyURL.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"dlFileEntryFriendlyURL.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (groupId != dlFileEntryFriendlyURL.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByGroupId_First(
			long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByGroupId_First(
			groupId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByGroupId_First(
		long groupId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByGroupId_Last(
			long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByGroupId_Last(
		long groupId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByGroupId_PrevAndNext(
			long fileEntryFriendlyURLId, long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, dlFileEntryFriendlyURL, groupId, orderByComparator,
				true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByGroupId_PrevAndNext(
				session, dlFileEntryFriendlyURL, groupId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByGroupId_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		long groupId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByGroupId(long groupId) {
		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByGroupId;

			finderArgs = new Object[] {groupId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"dlFileEntryFriendlyURL.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (companyId != dlFileEntryFriendlyURL.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByCompanyId_First(
			long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByCompanyId_First(
		long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByCompanyId_Last(
			long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByCompanyId_PrevAndNext(
			long fileEntryFriendlyURLId, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, dlFileEntryFriendlyURL, companyId, orderByComparator,
				true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByCompanyId_PrevAndNext(
				session, dlFileEntryFriendlyURL, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByCompanyId_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByCompanyId(long companyId) {
		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByCompanyId;

			finderArgs = new Object[] {companyId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"dlFileEntryFriendlyURL.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByFileEntryId;
	private FinderPath _finderPathWithoutPaginationFindByFileEntryId;
	private FinderPath _finderPathCountByFileEntryId;

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByFileEntryId(long fileEntryId) {
		return findByFileEntryId(
			fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end) {

		return findByFileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByFileEntryId(
			fileEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByFileEntryId;
				finderArgs = new Object[] {fileEntryId};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByFileEntryId;
			finderArgs = new Object[] {
				fileEntryId, start, end, orderByComparator
			};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (fileEntryId !=
							dlFileEntryFriendlyURL.getFileEntryId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByFileEntryId_First(
			long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL =
			fetchByFileEntryId_First(fileEntryId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByFileEntryId_First(
		long fileEntryId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByFileEntryId(
			fileEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByFileEntryId_Last(
			long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByFileEntryId_Last(
			fileEntryId, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByFileEntryId_Last(
		long fileEntryId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByFileEntryId(fileEntryId);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByFileEntryId(
			fileEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByFileEntryId_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByFileEntryId_PrevAndNext(
				session, dlFileEntryFriendlyURL, fileEntryId, orderByComparator,
				true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByFileEntryId_PrevAndNext(
				session, dlFileEntryFriendlyURL, fileEntryId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByFileEntryId_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		long fileEntryId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fileEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	@Override
	public void removeByFileEntryId(long fileEntryId) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByFileEntryId(
					fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByFileEntryId(long fileEntryId) {
		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByFileEntryId;

			finderArgs = new Object[] {fileEntryId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 =
		"dlFileEntryFriendlyURL.fileEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByF_F;
	private FinderPath _finderPathWithoutPaginationFindByF_F;
	private FinderPath _finderPathCountByF_F;

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL) {

		return findByF_F(
			fileEntryId, friendlyURL, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end) {

		return findByF_F(fileEntryId, friendlyURL, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByF_F(
			fileEntryId, friendlyURL, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		friendlyURL = Objects.toString(friendlyURL, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindByF_F;
				finderArgs = new Object[] {fileEntryId, friendlyURL};
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindByF_F;
			finderArgs = new Object[] {
				fileEntryId, friendlyURL, start, end, orderByComparator
			};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if ((fileEntryId !=
							dlFileEntryFriendlyURL.getFileEntryId()) ||
						!friendlyURL.equals(
							dlFileEntryFriendlyURL.getFriendlyURL())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_F_F_FILEENTRYID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByF_F_First(
			long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByF_F_First(
			fileEntryId, friendlyURL, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByF_F_First(
		long fileEntryId, String friendlyURL,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = findByF_F(
			fileEntryId, friendlyURL, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByF_F_Last(
			long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByF_F_Last(
			fileEntryId, friendlyURL, orderByComparator);

		if (dlFileEntryFriendlyURL != null) {
			return dlFileEntryFriendlyURL;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fileEntryId=");
		sb.append(fileEntryId);

		sb.append(", friendlyURL=");
		sb.append(friendlyURL);

		sb.append("}");

		throw new NoSuchFileEntryFriendlyURLException(sb.toString());
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByF_F_Last(
		long fileEntryId, String friendlyURL,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		int count = countByF_F(fileEntryId, friendlyURL);

		if (count == 0) {
			return null;
		}

		List<DLFileEntryFriendlyURL> list = findByF_F(
			fileEntryId, friendlyURL, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL[] findByF_F_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException {

		friendlyURL = Objects.toString(friendlyURL, "");

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByPrimaryKey(
			fileEntryFriendlyURLId);

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL[] array = new DLFileEntryFriendlyURLImpl[3];

			array[0] = getByF_F_PrevAndNext(
				session, dlFileEntryFriendlyURL, fileEntryId, friendlyURL,
				orderByComparator, true);

			array[1] = dlFileEntryFriendlyURL;

			array[2] = getByF_F_PrevAndNext(
				session, dlFileEntryFriendlyURL, fileEntryId, friendlyURL,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileEntryFriendlyURL getByF_F_PrevAndNext(
		Session session, DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		long fileEntryId, String friendlyURL,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		sb.append(_FINDER_COLUMN_F_F_FILEENTRYID_2);

		boolean bindFriendlyURL = false;

		if (friendlyURL.isEmpty()) {
			sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_3);
		}
		else {
			bindFriendlyURL = true;

			sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fileEntryId);

		if (bindFriendlyURL) {
			queryPos.add(friendlyURL);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						dlFileEntryFriendlyURL)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DLFileEntryFriendlyURL> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 */
	@Override
	public void removeByF_F(long fileEntryId, String friendlyURL) {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				findByF_F(
					fileEntryId, friendlyURL, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByF_F(long fileEntryId, String friendlyURL) {
		friendlyURL = Objects.toString(friendlyURL, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByF_F;

			finderArgs = new Object[] {fileEntryId, friendlyURL};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_F_F_FILEENTRYID_2);

			boolean bindFriendlyURL = false;

			if (friendlyURL.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_3);
			}
			else {
				bindFriendlyURL = true;

				sb.append(_FINDER_COLUMN_F_F_FRIENDLYURL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				if (bindFriendlyURL) {
					queryPos.add(friendlyURL);
				}

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_F_FILEENTRYID_2 =
		"dlFileEntryFriendlyURL.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_F_FRIENDLYURL_2 =
		"dlFileEntryFriendlyURL.friendlyURL = ?";

	private static final String _FINDER_COLUMN_F_F_FRIENDLYURL_3 =
		"(dlFileEntryFriendlyURL.friendlyURL IS NULL OR dlFileEntryFriendlyURL.friendlyURL = '')";

	private FinderPath _finderPathWithPaginationFindByF_L;
	private FinderPath _finderPathWithoutPaginationFindByF_L;
	private FinderPath _finderPathFetchByF_L;
	private FinderPath _finderPathCountByF_L;
	private FinderPath _finderPathWithPaginationCountByF_L;

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId) {

		return findByF_L(
			fileEntryIds, languageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end) {

		return findByF_L(fileEntryIds, languageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findByF_L(
			fileEntryIds, languageId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls where fileEntryId = &#63; and languageId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		if (fileEntryIds == null) {
			fileEntryIds = new long[0];
		}
		else if (fileEntryIds.length > 1) {
			fileEntryIds = ArrayUtil.sortedUnique(fileEntryIds);
		}

		languageId = Objects.toString(languageId, "");

		if (fileEntryIds.length == 1) {
			DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByF_L(
				fileEntryIds[0], languageId);

			if (dlFileEntryFriendlyURL == null) {
				return Collections.emptyList();
			}
			else {
				return Collections.singletonList(dlFileEntryFriendlyURL);
			}
		}

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderArgs = new Object[] {
					StringUtil.merge(fileEntryIds), languageId
				};
			}
		}
		else if (useFinderCache && productionMode) {
			finderArgs = new Object[] {
				StringUtil.merge(fileEntryIds), languageId, start, end,
				orderByComparator
			};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				_finderPathWithPaginationFindByF_L, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : list) {
					if (!ArrayUtil.contains(
							fileEntryIds,
							dlFileEntryFriendlyURL.getFileEntryId()) ||
						!languageId.equals(
							dlFileEntryFriendlyURL.getLanguageId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			try {
				if ((start == QueryUtil.ALL_POS) &&
					(end == QueryUtil.ALL_POS) &&
					(databaseInMaxParameters > 0) &&
					(fileEntryIds.length > databaseInMaxParameters)) {

					list = new ArrayList<DLFileEntryFriendlyURL>();

					long[][] fileEntryIdsPages = (long[][])ArrayUtil.split(
						fileEntryIds, databaseInMaxParameters);

					for (long[] fileEntryIdsPage : fileEntryIdsPages) {
						list.addAll(
							_findByF_L(
								fileEntryIdsPage, languageId, start, end,
								orderByComparator));
					}

					Collections.sort(list, orderByComparator);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = _findByF_L(
						fileEntryIds, languageId, start, end,
						orderByComparator);
				}

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationFindByF_L, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return list;
	}

	private List<DLFileEntryFriendlyURL> _findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		List<DLFileEntryFriendlyURL> list = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

		if (fileEntryIds.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_F_L_FILEENTRYID_7);

			sb.append(StringUtil.merge(fileEntryIds));

			sb.append(")");

			sb.append(")");

			sb.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_2);
		}

		sb.setStringAt(
			removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

		if (orderByComparator != null) {
			appendOrderByComparator(
				sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
		}
		else {
			sb.append(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			if (bindLanguageId) {
				queryPos.add(languageId);
			}

			list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
				query, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return list;
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByF_L(long fileEntryId, String languageId)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByF_L(
			fileEntryId, languageId);

		if (dlFileEntryFriendlyURL == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("fileEntryId=");
			sb.append(fileEntryId);

			sb.append(", languageId=");
			sb.append(languageId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFileEntryFriendlyURLException(sb.toString());
		}

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId) {

		return fetchByF_L(fileEntryId, languageId, true);
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId, boolean useFinderCache) {

		languageId = Objects.toString(languageId, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		Object[] finderArgs = null;

		if (useFinderCache && productionMode) {
			finderArgs = new Object[] {fileEntryId, languageId};
		}

		Object result = null;

		if (useFinderCache && productionMode) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByF_L, finderArgs);
		}

		if (result instanceof DLFileEntryFriendlyURL) {
			DLFileEntryFriendlyURL dlFileEntryFriendlyURL =
				(DLFileEntryFriendlyURL)result;

			if ((fileEntryId != dlFileEntryFriendlyURL.getFileEntryId()) ||
				!Objects.equals(
					languageId, dlFileEntryFriendlyURL.getLanguageId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_F_L_FILEENTRYID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				if (bindLanguageId) {
					queryPos.add(languageId);
				}

				List<DLFileEntryFriendlyURL> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache && productionMode) {
						FinderCacheUtil.putResult(
							_finderPathFetchByF_L, finderArgs, list);
					}
				}
				else {
					DLFileEntryFriendlyURL dlFileEntryFriendlyURL = list.get(0);

					result = dlFileEntryFriendlyURL;

					cacheResult(dlFileEntryFriendlyURL);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DLFileEntryFriendlyURL)result;
		}
	}

	/**
	 * Removes the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the dl file entry friendly url that was removed
	 */
	@Override
	public DLFileEntryFriendlyURL removeByF_L(
			long fileEntryId, String languageId)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = findByF_L(
			fileEntryId, languageId);

		return remove(dlFileEntryFriendlyURL);
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and languageId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByF_L(long fileEntryId, String languageId) {
		languageId = Objects.toString(languageId, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderPath = _finderPathCountByF_L;

			finderArgs = new Object[] {fileEntryId, languageId};

			count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs);
		}

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

			sb.append(_FINDER_COLUMN_F_L_FILEENTRYID_2);

			boolean bindLanguageId = false;

			if (languageId.isEmpty()) {
				sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_3);
			}
			else {
				bindLanguageId = true;

				sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fileEntryId);

				if (bindLanguageId) {
					queryPos.add(languageId);
				}

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	@Override
	public int countByF_L(long[] fileEntryIds, String languageId) {
		if (fileEntryIds == null) {
			fileEntryIds = new long[0];
		}
		else if (fileEntryIds.length > 1) {
			fileEntryIds = ArrayUtil.sortedUnique(fileEntryIds);
		}

		languageId = Objects.toString(languageId, "");

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		Object[] finderArgs = null;

		Long count = null;

		if (productionMode) {
			finderArgs = new Object[] {
				StringUtil.merge(fileEntryIds), languageId
			};

			count = (Long)FinderCacheUtil.getResult(
				_finderPathWithPaginationCountByF_L, finderArgs);
		}

		if (count == null) {
			try {
				if ((databaseInMaxParameters > 0) &&
					(fileEntryIds.length > databaseInMaxParameters)) {

					count = Long.valueOf(0);

					long[][] fileEntryIdsPages = (long[][])ArrayUtil.split(
						fileEntryIds, databaseInMaxParameters);

					for (long[] fileEntryIdsPage : fileEntryIdsPages) {
						count += Long.valueOf(
							_countByF_L(fileEntryIdsPage, languageId));
					}
				}
				else {
					count = Long.valueOf(_countByF_L(fileEntryIds, languageId));
				}

				if (productionMode) {
					FinderCacheUtil.putResult(
						_finderPathWithPaginationCountByF_L, finderArgs, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
		}

		return count.intValue();
	}

	private int _countByF_L(long[] fileEntryIds, String languageId) {
		Long count = null;

		StringBundler sb = new StringBundler();

		sb.append(_SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE);

		if (fileEntryIds.length > 0) {
			sb.append("(");

			sb.append(_FINDER_COLUMN_F_L_FILEENTRYID_7);

			sb.append(StringUtil.merge(fileEntryIds));

			sb.append(")");

			sb.append(")");

			sb.append(WHERE_AND);
		}

		boolean bindLanguageId = false;

		if (languageId.isEmpty()) {
			sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_3);
		}
		else {
			bindLanguageId = true;

			sb.append(_FINDER_COLUMN_F_L_LANGUAGEID_2);
		}

		sb.setStringAt(
			removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			QueryPos queryPos = QueryPos.getInstance(query);

			if (bindLanguageId) {
				queryPos.add(languageId);
			}

			count = (Long)query.uniqueResult();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_L_FILEENTRYID_2 =
		"dlFileEntryFriendlyURL.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_L_FILEENTRYID_7 =
		"dlFileEntryFriendlyURL.fileEntryId IN (";

	private static final String _FINDER_COLUMN_F_L_LANGUAGEID_2 =
		"dlFileEntryFriendlyURL.languageId = ?";

	private static final String _FINDER_COLUMN_F_L_LANGUAGEID_3 =
		"(dlFileEntryFriendlyURL.languageId IS NULL OR dlFileEntryFriendlyURL.languageId = '')";

	public DLFileEntryFriendlyURLPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(DLFileEntryFriendlyURL.class);

		setModelImplClass(DLFileEntryFriendlyURLImpl.class);
		setModelPKClass(long.class);

		setTable(DLFileEntryFriendlyURLTable.INSTANCE);
	}

	/**
	 * Caches the dl file entry friendly url in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 */
	@Override
	public void cacheResult(DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {
		if (dlFileEntryFriendlyURL.getCtCollectionId() != 0) {
			return;
		}

		EntityCacheUtil.putResult(
			DLFileEntryFriendlyURLImpl.class,
			dlFileEntryFriendlyURL.getPrimaryKey(), dlFileEntryFriendlyURL);

		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				dlFileEntryFriendlyURL.getUuid(),
				dlFileEntryFriendlyURL.getGroupId()
			},
			dlFileEntryFriendlyURL);

		FinderCacheUtil.putResult(
			_finderPathFetchByF_L,
			new Object[] {
				dlFileEntryFriendlyURL.getFileEntryId(),
				dlFileEntryFriendlyURL.getLanguageId()
			},
			dlFileEntryFriendlyURL);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the dl file entry friendly urls in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURLs the dl file entry friendly urls
	 */
	@Override
	public void cacheResult(
		List<DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (dlFileEntryFriendlyURLs.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				dlFileEntryFriendlyURLs) {

			if (dlFileEntryFriendlyURL.getCtCollectionId() != 0) {
				continue;
			}

			if (EntityCacheUtil.getResult(
					DLFileEntryFriendlyURLImpl.class,
					dlFileEntryFriendlyURL.getPrimaryKey()) == null) {

				cacheResult(dlFileEntryFriendlyURL);
			}
		}
	}

	/**
	 * Clears the cache for all dl file entry friendly urls.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(DLFileEntryFriendlyURLImpl.class);

		FinderCacheUtil.clearCache(DLFileEntryFriendlyURLImpl.class);
	}

	/**
	 * Clears the cache for the dl file entry friendly url.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {
		EntityCacheUtil.removeResult(
			DLFileEntryFriendlyURLImpl.class, dlFileEntryFriendlyURL);
	}

	@Override
	public void clearCache(
		List<DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs) {

		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
				dlFileEntryFriendlyURLs) {

			EntityCacheUtil.removeResult(
				DLFileEntryFriendlyURLImpl.class, dlFileEntryFriendlyURL);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(DLFileEntryFriendlyURLImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(
				DLFileEntryFriendlyURLImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DLFileEntryFriendlyURLModelImpl dlFileEntryFriendlyURLModelImpl) {

		Object[] args = new Object[] {
			dlFileEntryFriendlyURLModelImpl.getUuid(),
			dlFileEntryFriendlyURLModelImpl.getGroupId()
		};

		FinderCacheUtil.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1));
		FinderCacheUtil.putResult(
			_finderPathFetchByUUID_G, args, dlFileEntryFriendlyURLModelImpl);

		args = new Object[] {
			dlFileEntryFriendlyURLModelImpl.getFileEntryId(),
			dlFileEntryFriendlyURLModelImpl.getLanguageId()
		};

		FinderCacheUtil.putResult(_finderPathCountByF_L, args, Long.valueOf(1));
		FinderCacheUtil.putResult(
			_finderPathFetchByF_L, args, dlFileEntryFriendlyURLModelImpl);
	}

	/**
	 * Creates a new dl file entry friendly url with the primary key. Does not add the dl file entry friendly url to the database.
	 *
	 * @param fileEntryFriendlyURLId the primary key for the new dl file entry friendly url
	 * @return the new dl file entry friendly url
	 */
	@Override
	public DLFileEntryFriendlyURL create(long fileEntryFriendlyURLId) {
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL =
			new DLFileEntryFriendlyURLImpl();

		dlFileEntryFriendlyURL.setNew(true);
		dlFileEntryFriendlyURL.setPrimaryKey(fileEntryFriendlyURLId);

		String uuid = PortalUUIDUtil.generate();

		dlFileEntryFriendlyURL.setUuid(uuid);

		dlFileEntryFriendlyURL.setCompanyId(CompanyThreadLocal.getCompanyId());

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Removes the dl file entry friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL remove(long fileEntryFriendlyURLId)
		throws NoSuchFileEntryFriendlyURLException {

		return remove((Serializable)fileEntryFriendlyURLId);
	}

	/**
	 * Removes the dl file entry friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL remove(Serializable primaryKey)
		throws NoSuchFileEntryFriendlyURLException {

		Session session = null;

		try {
			session = openSession();

			DLFileEntryFriendlyURL dlFileEntryFriendlyURL =
				(DLFileEntryFriendlyURL)session.get(
					DLFileEntryFriendlyURLImpl.class, primaryKey);

			if (dlFileEntryFriendlyURL == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileEntryFriendlyURLException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(dlFileEntryFriendlyURL);
		}
		catch (NoSuchFileEntryFriendlyURLException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DLFileEntryFriendlyURL removeImpl(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dlFileEntryFriendlyURL)) {
				dlFileEntryFriendlyURL = (DLFileEntryFriendlyURL)session.get(
					DLFileEntryFriendlyURLImpl.class,
					dlFileEntryFriendlyURL.getPrimaryKeyObj());
			}

			if ((dlFileEntryFriendlyURL != null) &&
				CTPersistenceHelperUtil.isRemove(dlFileEntryFriendlyURL)) {

				session.delete(dlFileEntryFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dlFileEntryFriendlyURL != null) {
			clearCache(dlFileEntryFriendlyURL);
		}

		return dlFileEntryFriendlyURL;
	}

	@Override
	public DLFileEntryFriendlyURL updateImpl(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		boolean isNew = dlFileEntryFriendlyURL.isNew();

		if (!(dlFileEntryFriendlyURL instanceof
				DLFileEntryFriendlyURLModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(dlFileEntryFriendlyURL.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					dlFileEntryFriendlyURL);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in dlFileEntryFriendlyURL proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DLFileEntryFriendlyURL implementation " +
					dlFileEntryFriendlyURL.getClass());
		}

		DLFileEntryFriendlyURLModelImpl dlFileEntryFriendlyURLModelImpl =
			(DLFileEntryFriendlyURLModelImpl)dlFileEntryFriendlyURL;

		if (Validator.isNull(dlFileEntryFriendlyURL.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			dlFileEntryFriendlyURL.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (dlFileEntryFriendlyURL.getCreateDate() == null)) {
			if (serviceContext == null) {
				dlFileEntryFriendlyURL.setCreateDate(date);
			}
			else {
				dlFileEntryFriendlyURL.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!dlFileEntryFriendlyURLModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				dlFileEntryFriendlyURL.setModifiedDate(date);
			}
			else {
				dlFileEntryFriendlyURL.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (CTPersistenceHelperUtil.isInsert(dlFileEntryFriendlyURL)) {
				if (!isNew) {
					session.evict(
						DLFileEntryFriendlyURLImpl.class,
						dlFileEntryFriendlyURL.getPrimaryKeyObj());
				}

				session.save(dlFileEntryFriendlyURL);
			}
			else {
				dlFileEntryFriendlyURL = (DLFileEntryFriendlyURL)session.merge(
					dlFileEntryFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dlFileEntryFriendlyURL.getCtCollectionId() != 0) {
			if (isNew) {
				dlFileEntryFriendlyURL.setNew(false);
			}

			dlFileEntryFriendlyURL.resetOriginalValues();

			return dlFileEntryFriendlyURL;
		}

		EntityCacheUtil.putResult(
			DLFileEntryFriendlyURLImpl.class, dlFileEntryFriendlyURLModelImpl,
			false, true);

		cacheUniqueFindersCache(dlFileEntryFriendlyURLModelImpl);

		if (isNew) {
			dlFileEntryFriendlyURL.setNew(false);
		}

		dlFileEntryFriendlyURL.resetOriginalValues();

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileEntryFriendlyURLException {

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByPrimaryKey(
			primaryKey);

		if (dlFileEntryFriendlyURL == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileEntryFriendlyURLException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL findByPrimaryKey(long fileEntryFriendlyURLId)
		throws NoSuchFileEntryFriendlyURLException {

		return findByPrimaryKey((Serializable)fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url, or <code>null</code> if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByPrimaryKey(Serializable primaryKey) {
		if (CTPersistenceHelperUtil.isProductionMode(
				DLFileEntryFriendlyURL.class)) {

			return super.fetchByPrimaryKey(primaryKey);
		}

		DLFileEntryFriendlyURL dlFileEntryFriendlyURL = null;

		Session session = null;

		try {
			session = openSession();

			dlFileEntryFriendlyURL = (DLFileEntryFriendlyURL)session.get(
				DLFileEntryFriendlyURLImpl.class, primaryKey);

			if (dlFileEntryFriendlyURL != null) {
				cacheResult(dlFileEntryFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return dlFileEntryFriendlyURL;
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url, or <code>null</code> if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchByPrimaryKey(
		long fileEntryFriendlyURLId) {

		return fetchByPrimaryKey((Serializable)fileEntryFriendlyURLId);
	}

	@Override
	public Map<Serializable, DLFileEntryFriendlyURL> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (CTPersistenceHelperUtil.isProductionMode(
				DLFileEntryFriendlyURL.class)) {

			return super.fetchByPrimaryKeys(primaryKeys);
		}

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DLFileEntryFriendlyURL> map =
			new HashMap<Serializable, DLFileEntryFriendlyURL>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DLFileEntryFriendlyURL dlFileEntryFriendlyURL = fetchByPrimaryKey(
				primaryKey);

			if (dlFileEntryFriendlyURL != null) {
				map.put(primaryKey, dlFileEntryFriendlyURL);
			}

			return map;
		}

		if ((databaseInMaxParameters > 0) &&
			(primaryKeys.size() > databaseInMaxParameters)) {

			Iterator<Serializable> iterator = primaryKeys.iterator();

			while (iterator.hasNext()) {
				Set<Serializable> page = new HashSet<>();

				for (int i = 0;
					 (i < databaseInMaxParameters) && iterator.hasNext(); i++) {

					page.add(iterator.next());
				}

				map.putAll(fetchByPrimaryKeys(page));
			}

			return map;
		}

		StringBundler sb = new StringBundler((primaryKeys.size() * 2) + 1);

		sb.append(getSelectSQL());
		sb.append(" WHERE ");
		sb.append(getPKDBName());
		sb.append(" IN (");

		for (Serializable primaryKey : primaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL :
					(List<DLFileEntryFriendlyURL>)query.list()) {

				map.put(
					dlFileEntryFriendlyURL.getPrimaryKeyObj(),
					dlFileEntryFriendlyURL);

				cacheResult(dlFileEntryFriendlyURL);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the dl file entry friendly urls.
	 *
	 * @return the dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the dl file entry friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of dl file entry friendly urls
	 */
	@Override
	public List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache && productionMode) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache && productionMode) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<DLFileEntryFriendlyURL> list = null;

		if (useFinderCache && productionMode) {
			list = (List<DLFileEntryFriendlyURL>)FinderCacheUtil.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DLFILEENTRYFRIENDLYURL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DLFILEENTRYFRIENDLYURL;

				sql = sql.concat(DLFileEntryFriendlyURLModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DLFileEntryFriendlyURL>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache && productionMode) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the dl file entry friendly urls from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DLFileEntryFriendlyURL dlFileEntryFriendlyURL : findAll()) {
			remove(dlFileEntryFriendlyURL);
		}
	}

	/**
	 * Returns the number of dl file entry friendly urls.
	 *
	 * @return the number of dl file entry friendly urls
	 */
	@Override
	public int countAll() {
		boolean productionMode = CTPersistenceHelperUtil.isProductionMode(
			DLFileEntryFriendlyURL.class);

		Long count = null;

		if (productionMode) {
			count = (Long)FinderCacheUtil.getResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY);
		}

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_DLFILEENTRYFRIENDLYURL);

				count = (Long)query.uniqueResult();

				if (productionMode) {
					FinderCacheUtil.putResult(
						_finderPathCountAll, FINDER_ARGS_EMPTY, count);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "fileEntryFriendlyURLId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DLFILEENTRYFRIENDLYURL;
	}

	@Override
	public Set<String> getCTColumnNames(
		CTColumnResolutionType ctColumnResolutionType) {

		return _ctColumnNamesMap.getOrDefault(
			ctColumnResolutionType, Collections.emptySet());
	}

	@Override
	public List<String> getMappingTableNames() {
		return _mappingTableNames;
	}

	@Override
	public Map<String, Integer> getTableColumnsMap() {
		return DLFileEntryFriendlyURLModelImpl.TABLE_COLUMNS_MAP;
	}

	@Override
	public String getTableName() {
		return "DLFileEntryFriendlyURL";
	}

	@Override
	public List<String[]> getUniqueIndexColumnNames() {
		return _uniqueIndexColumnNames;
	}

	private static final Map<CTColumnResolutionType, Set<String>>
		_ctColumnNamesMap = new EnumMap<CTColumnResolutionType, Set<String>>(
			CTColumnResolutionType.class);
	private static final List<String> _mappingTableNames =
		new ArrayList<String>();
	private static final List<String[]> _uniqueIndexColumnNames =
		new ArrayList<String[]>();

	static {
		Set<String> ctControlColumnNames = new HashSet<String>();
		Set<String> ctIgnoreColumnNames = new HashSet<String>();
		Set<String> ctStrictColumnNames = new HashSet<String>();

		ctControlColumnNames.add("mvccVersion");
		ctControlColumnNames.add("ctCollectionId");
		ctStrictColumnNames.add("uuid_");
		ctStrictColumnNames.add("groupId");
		ctStrictColumnNames.add("companyId");
		ctStrictColumnNames.add("userId");
		ctStrictColumnNames.add("userName");
		ctStrictColumnNames.add("createDate");
		ctIgnoreColumnNames.add("modifiedDate");
		ctStrictColumnNames.add("fileEntryId");
		ctStrictColumnNames.add("friendlyURL");
		ctStrictColumnNames.add("languageId");
		ctStrictColumnNames.add("lastPublishDate");

		_ctColumnNamesMap.put(
			CTColumnResolutionType.CONTROL, ctControlColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.IGNORE, ctIgnoreColumnNames);
		_ctColumnNamesMap.put(
			CTColumnResolutionType.PK,
			Collections.singleton("fileEntryFriendlyURLId"));
		_ctColumnNamesMap.put(
			CTColumnResolutionType.STRICT, ctStrictColumnNames);

		_uniqueIndexColumnNames.add(new String[] {"uuid_", "groupId"});

		_uniqueIndexColumnNames.add(new String[] {"fileEntryId", "languageId"});
	}

	/**
	 * Initializes the dl file entry friendly url persistence.
	 */
	public void afterPropertiesSet() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathWithPaginationFindByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId"}, true);

		_finderPathWithoutPaginationFindByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			true);

		_finderPathCountByFileEntryId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileEntryId",
			new String[] {Long.class.getName()}, new String[] {"fileEntryId"},
			false);

		_finderPathWithPaginationFindByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId", "friendlyURL"}, true);

		_finderPathWithoutPaginationFindByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "friendlyURL"}, true);

		_finderPathCountByF_F = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_F",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "friendlyURL"}, false);

		_finderPathWithPaginationFindByF_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"fileEntryId", "languageId"}, true);

		_finderPathWithoutPaginationFindByF_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "languageId"}, true);

		_finderPathFetchByF_L = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByF_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "languageId"}, true);

		_finderPathCountByF_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "languageId"}, false);

		_finderPathWithPaginationCountByF_L = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByF_L",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"fileEntryId", "languageId"}, false);

		_setDLFileEntryFriendlyURLUtilPersistence(this);
	}

	public void destroy() {
		_setDLFileEntryFriendlyURLUtilPersistence(null);

		EntityCacheUtil.removeCache(DLFileEntryFriendlyURLImpl.class.getName());
	}

	private void _setDLFileEntryFriendlyURLUtilPersistence(
		DLFileEntryFriendlyURLPersistence dlFileEntryFriendlyURLPersistence) {

		try {
			Field field = DLFileEntryFriendlyURLUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, dlFileEntryFriendlyURLPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	private static final String _SQL_SELECT_DLFILEENTRYFRIENDLYURL =
		"SELECT dlFileEntryFriendlyURL FROM DLFileEntryFriendlyURL dlFileEntryFriendlyURL";

	private static final String _SQL_SELECT_DLFILEENTRYFRIENDLYURL_WHERE =
		"SELECT dlFileEntryFriendlyURL FROM DLFileEntryFriendlyURL dlFileEntryFriendlyURL WHERE ";

	private static final String _SQL_COUNT_DLFILEENTRYFRIENDLYURL =
		"SELECT COUNT(dlFileEntryFriendlyURL) FROM DLFileEntryFriendlyURL dlFileEntryFriendlyURL";

	private static final String _SQL_COUNT_DLFILEENTRYFRIENDLYURL_WHERE =
		"SELECT COUNT(dlFileEntryFriendlyURL) FROM DLFileEntryFriendlyURL dlFileEntryFriendlyURL WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"dlFileEntryFriendlyURL.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DLFileEntryFriendlyURL exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DLFileEntryFriendlyURL exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryFriendlyURLPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return FinderCacheUtil.getFinderCache();
	}

}