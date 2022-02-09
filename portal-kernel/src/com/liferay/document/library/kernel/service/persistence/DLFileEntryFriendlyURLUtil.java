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

package com.liferay.document.library.kernel.service.persistence;

import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the dl file entry friendly url service. This utility wraps <code>com.liferay.portlet.documentlibrary.service.persistence.impl.DLFileEntryFriendlyURLPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryFriendlyURLPersistence
 * @generated
 */
public class DLFileEntryFriendlyURLUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		getPersistence().clearCache(dlFileEntryFriendlyURL);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, DLFileEntryFriendlyURL> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DLFileEntryFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DLFileEntryFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DLFileEntryFriendlyURL> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DLFileEntryFriendlyURL update(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return getPersistence().update(dlFileEntryFriendlyURL);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DLFileEntryFriendlyURL update(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL,
		ServiceContext serviceContext) {

		return getPersistence().update(dlFileEntryFriendlyURL, serviceContext);
	}

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
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
	public static List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByUuid_First(
			String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUuid_First(
		String uuid,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByUuid_Last(
			String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUuid_Last(
		String uuid,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByUuid_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_PrevAndNext(
			fileEntryFriendlyURLId, uuid, orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByUUID_G(String uuid, long groupId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the dl file entry friendly url where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dl file entry friendly url that was removed
	 */
	public static DLFileEntryFriendlyURL removeByUUID_G(
			String uuid, long groupId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
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
	public static List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
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
	public static DLFileEntryFriendlyURL findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
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
	public static DLFileEntryFriendlyURL findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByUuid_C_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByUuid_C_PrevAndNext(
			fileEntryFriendlyURLId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
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
	public static List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByGroupId_First(
			long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByGroupId_First(
		long groupId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByGroupId_Last(
			long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByGroupId_Last(
		long groupId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByGroupId_PrevAndNext(
			long fileEntryFriendlyURLId, long groupId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByGroupId_PrevAndNext(
			fileEntryFriendlyURLId, groupId, orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
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
	public static List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByCompanyId_First(
			long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByCompanyId_First(
		long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByCompanyId_Last(
			long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByCompanyId_Last(
		long companyId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByCompanyId_PrevAndNext(
			long fileEntryFriendlyURLId, long companyId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByCompanyId_PrevAndNext(
			fileEntryFriendlyURLId, companyId, orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId) {

		return getPersistence().findByFileEntryId(fileEntryId);
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
	public static List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end) {

		return getPersistence().findByFileEntryId(fileEntryId, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByFileEntryId(
			fileEntryId, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFileEntryId(
			fileEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByFileEntryId_First(
			long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByFileEntryId_First(
			fileEntryId, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByFileEntryId_First(
		long fileEntryId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByFileEntryId_First(
			fileEntryId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByFileEntryId_Last(
			long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByFileEntryId_Last(
			fileEntryId, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByFileEntryId_Last(
		long fileEntryId,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByFileEntryId_Last(
			fileEntryId, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByFileEntryId_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByFileEntryId_PrevAndNext(
			fileEntryFriendlyURLId, fileEntryId, orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	public static void removeByFileEntryId(long fileEntryId) {
		getPersistence().removeByFileEntryId(fileEntryId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByFileEntryId(long fileEntryId) {
		return getPersistence().countByFileEntryId(fileEntryId);
	}

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the matching dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL) {

		return getPersistence().findByF_F(fileEntryId, friendlyURL);
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
	public static List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end) {

		return getPersistence().findByF_F(fileEntryId, friendlyURL, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByF_F(
			fileEntryId, friendlyURL, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByF_F(
			fileEntryId, friendlyURL, start, end, orderByComparator,
			useFinderCache);
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
	public static DLFileEntryFriendlyURL findByF_F_First(
			long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByF_F_First(
			fileEntryId, friendlyURL, orderByComparator);
	}

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByF_F_First(
		long fileEntryId, String friendlyURL,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByF_F_First(
			fileEntryId, friendlyURL, orderByComparator);
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
	public static DLFileEntryFriendlyURL findByF_F_Last(
			long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByF_F_Last(
			fileEntryId, friendlyURL, orderByComparator);
	}

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByF_F_Last(
		long fileEntryId, String friendlyURL,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().fetchByF_F_Last(
			fileEntryId, friendlyURL, orderByComparator);
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
	public static DLFileEntryFriendlyURL[] findByF_F_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId, String friendlyURL,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByF_F_PrevAndNext(
			fileEntryFriendlyURLId, fileEntryId, friendlyURL,
			orderByComparator);
	}

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 */
	public static void removeByF_F(long fileEntryId, String friendlyURL) {
		getPersistence().removeByF_F(fileEntryId, friendlyURL);
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByF_F(long fileEntryId, String friendlyURL) {
		return getPersistence().countByF_F(fileEntryId, friendlyURL);
	}

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
	public static List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId) {

		return getPersistence().findByF_L(fileEntryIds, languageId);
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
	public static List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end) {

		return getPersistence().findByF_L(fileEntryIds, languageId, start, end);
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
	public static List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findByF_L(
			fileEntryIds, languageId, start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByF_L(
			fileEntryIds, languageId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL findByF_L(
			long fileEntryId, String languageId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByF_L(fileEntryId, languageId);
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId) {

		return getPersistence().fetchByF_L(fileEntryId, languageId);
	}

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId, boolean useFinderCache) {

		return getPersistence().fetchByF_L(
			fileEntryId, languageId, useFinderCache);
	}

	/**
	 * Removes the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the dl file entry friendly url that was removed
	 */
	public static DLFileEntryFriendlyURL removeByF_L(
			long fileEntryId, String languageId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().removeByF_L(fileEntryId, languageId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and languageId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByF_L(long fileEntryId, String languageId) {
		return getPersistence().countByF_L(fileEntryId, languageId);
	}

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public static int countByF_L(long[] fileEntryIds, String languageId) {
		return getPersistence().countByF_L(fileEntryIds, languageId);
	}

	/**
	 * Caches the dl file entry friendly url in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 */
	public static void cacheResult(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		getPersistence().cacheResult(dlFileEntryFriendlyURL);
	}

	/**
	 * Caches the dl file entry friendly urls in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURLs the dl file entry friendly urls
	 */
	public static void cacheResult(
		List<DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs) {

		getPersistence().cacheResult(dlFileEntryFriendlyURLs);
	}

	/**
	 * Creates a new dl file entry friendly url with the primary key. Does not add the dl file entry friendly url to the database.
	 *
	 * @param fileEntryFriendlyURLId the primary key for the new dl file entry friendly url
	 * @return the new dl file entry friendly url
	 */
	public static DLFileEntryFriendlyURL create(long fileEntryFriendlyURLId) {
		return getPersistence().create(fileEntryFriendlyURLId);
	}

	/**
	 * Removes the dl file entry friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public static DLFileEntryFriendlyURL remove(long fileEntryFriendlyURLId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().remove(fileEntryFriendlyURLId);
	}

	public static DLFileEntryFriendlyURL updateImpl(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return getPersistence().updateImpl(dlFileEntryFriendlyURL);
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public static DLFileEntryFriendlyURL findByPrimaryKey(
			long fileEntryFriendlyURLId)
		throws com.liferay.document.library.kernel.exception.
			NoSuchFileEntryFriendlyURLException {

		return getPersistence().findByPrimaryKey(fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url, or <code>null</code> if a dl file entry friendly url with the primary key could not be found
	 */
	public static DLFileEntryFriendlyURL fetchByPrimaryKey(
		long fileEntryFriendlyURLId) {

		return getPersistence().fetchByPrimaryKey(fileEntryFriendlyURLId);
	}

	/**
	 * Returns all the dl file entry friendly urls.
	 *
	 * @return the dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> findAll() {
		return getPersistence().findAll();
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
	public static List<DLFileEntryFriendlyURL> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		OrderByComparator<DLFileEntryFriendlyURL> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the dl file entry friendly urls from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of dl file entry friendly urls.
	 *
	 * @return the number of dl file entry friendly urls
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DLFileEntryFriendlyURLPersistence getPersistence() {
		return _persistence;
	}

	private static volatile DLFileEntryFriendlyURLPersistence _persistence;

}