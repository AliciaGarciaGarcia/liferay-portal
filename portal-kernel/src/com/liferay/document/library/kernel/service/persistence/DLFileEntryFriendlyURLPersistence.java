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

import com.liferay.document.library.kernel.exception.NoSuchFileEntryFriendlyURLException;
import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the dl file entry friendly url service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryFriendlyURLUtil
 * @generated
 */
@ProviderType
public interface DLFileEntryFriendlyURLPersistence
	extends BasePersistence<DLFileEntryFriendlyURL>,
			CTPersistence<DLFileEntryFriendlyURL> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DLFileEntryFriendlyURLUtil} to access the dl file entry friendly url persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByUuid(String uuid);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where uuid = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL[] findByUuid_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the dl file entry friendly url where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the dl file entry friendly url where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the dl file entry friendly url that was removed
	 */
	public DLFileEntryFriendlyURL removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public DLFileEntryFriendlyURL[] findByUuid_C_PrevAndNext(
			long fileEntryFriendlyURLId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of dl file entry friendly urls where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByGroupId(long groupId);

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
	public java.util.List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where groupId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL[] findByGroupId_PrevAndNext(
			long fileEntryFriendlyURLId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of dl file entry friendly urls where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId);

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
	public java.util.List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where companyId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL[] findByCompanyId_PrevAndNext(
			long fileEntryFriendlyURLId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of dl file entry friendly urls where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId);

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
	public java.util.List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByFileEntryId(
		long fileEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByFileEntryId_First(
			long fileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByFileEntryId_First(
		long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByFileEntryId_Last(
			long fileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByFileEntryId_Last(
		long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the dl file entry friendly urls before and after the current dl file entry friendly url in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the current dl file entry friendly url
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL[] findByFileEntryId_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	public void removeByFileEntryId(long fileEntryId);

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByFileEntryId(long fileEntryId);

	/**
	 * Returns all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the matching dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_F(
		long fileEntryId, String friendlyURL, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByF_F_First(
			long fileEntryId, String friendlyURL,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the first dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByF_F_First(
		long fileEntryId, String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByF_F_Last(
			long fileEntryId, String friendlyURL,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the last dl file entry friendly url in the ordered set where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByF_F_Last(
		long fileEntryId, String friendlyURL,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public DLFileEntryFriendlyURL[] findByF_F_PrevAndNext(
			long fileEntryFriendlyURLId, long fileEntryId, String friendlyURL,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Removes all the dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 */
	public void removeByF_F(long fileEntryId, String friendlyURL);

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and friendlyURL = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param friendlyURL the friendly url
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByF_F(long fileEntryId, String friendlyURL);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findByF_L(
		long[] fileEntryIds, String languageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL findByF_L(long fileEntryId, String languageId)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId);

	/**
	 * Returns the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public DLFileEntryFriendlyURL fetchByF_L(
		long fileEntryId, String languageId, boolean useFinderCache);

	/**
	 * Removes the dl file entry friendly url where fileEntryId = &#63; and languageId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the dl file entry friendly url that was removed
	 */
	public DLFileEntryFriendlyURL removeByF_L(
			long fileEntryId, String languageId)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = &#63; and languageId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByF_L(long fileEntryId, String languageId);

	/**
	 * Returns the number of dl file entry friendly urls where fileEntryId = any &#63; and languageId = &#63;.
	 *
	 * @param fileEntryIds the file entry IDs
	 * @param languageId the language ID
	 * @return the number of matching dl file entry friendly urls
	 */
	public int countByF_L(long[] fileEntryIds, String languageId);

	/**
	 * Caches the dl file entry friendly url in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 */
	public void cacheResult(DLFileEntryFriendlyURL dlFileEntryFriendlyURL);

	/**
	 * Caches the dl file entry friendly urls in the entity cache if it is enabled.
	 *
	 * @param dlFileEntryFriendlyURLs the dl file entry friendly urls
	 */
	public void cacheResult(
		java.util.List<DLFileEntryFriendlyURL> dlFileEntryFriendlyURLs);

	/**
	 * Creates a new dl file entry friendly url with the primary key. Does not add the dl file entry friendly url to the database.
	 *
	 * @param fileEntryFriendlyURLId the primary key for the new dl file entry friendly url
	 * @return the new dl file entry friendly url
	 */
	public DLFileEntryFriendlyURL create(long fileEntryFriendlyURLId);

	/**
	 * Removes the dl file entry friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL remove(long fileEntryFriendlyURLId)
		throws NoSuchFileEntryFriendlyURLException;

	public DLFileEntryFriendlyURL updateImpl(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL);

	/**
	 * Returns the dl file entry friendly url with the primary key or throws a <code>NoSuchFileEntryFriendlyURLException</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws NoSuchFileEntryFriendlyURLException if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL findByPrimaryKey(long fileEntryFriendlyURLId)
		throws NoSuchFileEntryFriendlyURLException;

	/**
	 * Returns the dl file entry friendly url with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url, or <code>null</code> if a dl file entry friendly url with the primary key could not be found
	 */
	public DLFileEntryFriendlyURL fetchByPrimaryKey(
		long fileEntryFriendlyURLId);

	/**
	 * Returns all the dl file entry friendly urls.
	 *
	 * @return the dl file entry friendly urls
	 */
	public java.util.List<DLFileEntryFriendlyURL> findAll();

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
	public java.util.List<DLFileEntryFriendlyURL> findAll(int start, int end);

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
	public java.util.List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator);

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
	public java.util.List<DLFileEntryFriendlyURL> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DLFileEntryFriendlyURL>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the dl file entry friendly urls from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of dl file entry friendly urls.
	 *
	 * @return the number of dl file entry friendly urls
	 */
	public int countAll();

}