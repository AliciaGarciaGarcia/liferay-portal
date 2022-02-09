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

package com.liferay.document.library.kernel.service;

import com.liferay.document.library.kernel.model.DLFileEntryFriendlyURL;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for DLFileEntryFriendlyURL. This utility wraps
 * <code>com.liferay.portlet.documentlibrary.service.impl.DLFileEntryFriendlyURLLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryFriendlyURLLocalService
 * @generated
 */
public class DLFileEntryFriendlyURLLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.documentlibrary.service.impl.DLFileEntryFriendlyURLLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the dl file entry friendly url to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DLFileEntryFriendlyURLLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 * @return the dl file entry friendly url that was added
	 */
	public static DLFileEntryFriendlyURL addDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return getService().addDLFileEntryFriendlyURL(dlFileEntryFriendlyURL);
	}

	/**
	 * Creates a new dl file entry friendly url with the primary key. Does not add the dl file entry friendly url to the database.
	 *
	 * @param fileEntryFriendlyURLId the primary key for the new dl file entry friendly url
	 * @return the new dl file entry friendly url
	 */
	public static DLFileEntryFriendlyURL createDLFileEntryFriendlyURL(
		long fileEntryFriendlyURLId) {

		return getService().createDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the dl file entry friendly url from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DLFileEntryFriendlyURLLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 */
	public static DLFileEntryFriendlyURL deleteDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return getService().deleteDLFileEntryFriendlyURL(
			dlFileEntryFriendlyURL);
	}

	/**
	 * Deletes the dl file entry friendly url with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DLFileEntryFriendlyURLLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url that was removed
	 * @throws PortalException if a dl file entry friendly url with the primary key could not be found
	 */
	public static DLFileEntryFriendlyURL deleteDLFileEntryFriendlyURL(
			long fileEntryFriendlyURLId)
		throws PortalException {

		return getService().deleteDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.documentlibrary.model.impl.DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.documentlibrary.model.impl.DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static DLFileEntryFriendlyURL fetchDLFileEntryFriendlyURL(
		long fileEntryFriendlyURLId) {

		return getService().fetchDLFileEntryFriendlyURL(fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url matching the UUID and group.
	 *
	 * @param uuid the dl file entry friendly url's UUID
	 * @param groupId the primary key of the group
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL
		fetchDLFileEntryFriendlyURLByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchDLFileEntryFriendlyURLByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the dl file entry friendly url with the primary key.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws PortalException if a dl file entry friendly url with the primary key could not be found
	 */
	public static DLFileEntryFriendlyURL getDLFileEntryFriendlyURL(
			long fileEntryFriendlyURLId)
		throws PortalException {

		return getService().getDLFileEntryFriendlyURL(fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url matching the UUID and group.
	 *
	 * @param uuid the dl file entry friendly url's UUID
	 * @param groupId the primary key of the group
	 * @return the matching dl file entry friendly url
	 * @throws PortalException if a matching dl file entry friendly url could not be found
	 */
	public static DLFileEntryFriendlyURL
			getDLFileEntryFriendlyURLByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getDLFileEntryFriendlyURLByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the dl file entry friendly urls.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.documentlibrary.model.impl.DLFileEntryFriendlyURLModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @return the range of dl file entry friendly urls
	 */
	public static List<DLFileEntryFriendlyURL> getDLFileEntryFriendlyURLs(
		int start, int end) {

		return getService().getDLFileEntryFriendlyURLs(start, end);
	}

	/**
	 * Returns all the dl file entry friendly urls matching the UUID and company.
	 *
	 * @param uuid the UUID of the dl file entry friendly urls
	 * @param companyId the primary key of the company
	 * @return the matching dl file entry friendly urls, or an empty list if no matches were found
	 */
	public static List<DLFileEntryFriendlyURL>
		getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of dl file entry friendly urls matching the UUID and company.
	 *
	 * @param uuid the UUID of the dl file entry friendly urls
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of dl file entry friendly urls
	 * @param end the upper bound of the range of dl file entry friendly urls (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching dl file entry friendly urls, or an empty list if no matches were found
	 */
	public static List<DLFileEntryFriendlyURL>
		getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<DLFileEntryFriendlyURL> orderByComparator) {

		return getService().getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of dl file entry friendly urls.
	 *
	 * @return the number of dl file entry friendly urls
	 */
	public static int getDLFileEntryFriendlyURLsCount() {
		return getService().getDLFileEntryFriendlyURLsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the dl file entry friendly url in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DLFileEntryFriendlyURLLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param dlFileEntryFriendlyURL the dl file entry friendly url
	 * @return the dl file entry friendly url that was updated
	 */
	public static DLFileEntryFriendlyURL updateDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return getService().updateDLFileEntryFriendlyURL(
			dlFileEntryFriendlyURL);
	}

	public static DLFileEntryFriendlyURLLocalService getService() {
		return _service;
	}

	private static volatile DLFileEntryFriendlyURLLocalService _service;

}