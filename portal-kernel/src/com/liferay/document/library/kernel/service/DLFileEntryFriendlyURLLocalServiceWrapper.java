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
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.change.tracking.CTPersistence;

/**
 * Provides a wrapper for {@link DLFileEntryFriendlyURLLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryFriendlyURLLocalService
 * @generated
 */
public class DLFileEntryFriendlyURLLocalServiceWrapper
	implements DLFileEntryFriendlyURLLocalService,
			   ServiceWrapper<DLFileEntryFriendlyURLLocalService> {

	public DLFileEntryFriendlyURLLocalServiceWrapper() {
		this(null);
	}

	public DLFileEntryFriendlyURLLocalServiceWrapper(
		DLFileEntryFriendlyURLLocalService dlFileEntryFriendlyURLLocalService) {

		_dlFileEntryFriendlyURLLocalService =
			dlFileEntryFriendlyURLLocalService;
	}

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
	@Override
	public DLFileEntryFriendlyURL addDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return _dlFileEntryFriendlyURLLocalService.addDLFileEntryFriendlyURL(
			dlFileEntryFriendlyURL);
	}

	/**
	 * Creates a new dl file entry friendly url with the primary key. Does not add the dl file entry friendly url to the database.
	 *
	 * @param fileEntryFriendlyURLId the primary key for the new dl file entry friendly url
	 * @return the new dl file entry friendly url
	 */
	@Override
	public DLFileEntryFriendlyURL createDLFileEntryFriendlyURL(
		long fileEntryFriendlyURLId) {

		return _dlFileEntryFriendlyURLLocalService.createDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public DLFileEntryFriendlyURL deleteDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return _dlFileEntryFriendlyURLLocalService.deleteDLFileEntryFriendlyURL(
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
	@Override
	public DLFileEntryFriendlyURL deleteDLFileEntryFriendlyURL(
			long fileEntryFriendlyURLId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.deleteDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _dlFileEntryFriendlyURLLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _dlFileEntryFriendlyURLLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dlFileEntryFriendlyURLLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _dlFileEntryFriendlyURLLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _dlFileEntryFriendlyURLLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _dlFileEntryFriendlyURLLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _dlFileEntryFriendlyURLLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _dlFileEntryFriendlyURLLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public DLFileEntryFriendlyURL fetchDLFileEntryFriendlyURL(
		long fileEntryFriendlyURLId) {

		return _dlFileEntryFriendlyURLLocalService.fetchDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url matching the UUID and group.
	 *
	 * @param uuid the dl file entry friendly url's UUID
	 * @param groupId the primary key of the group
	 * @return the matching dl file entry friendly url, or <code>null</code> if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL fetchDLFileEntryFriendlyURLByUuidAndGroupId(
		String uuid, long groupId) {

		return _dlFileEntryFriendlyURLLocalService.
			fetchDLFileEntryFriendlyURLByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _dlFileEntryFriendlyURLLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the dl file entry friendly url with the primary key.
	 *
	 * @param fileEntryFriendlyURLId the primary key of the dl file entry friendly url
	 * @return the dl file entry friendly url
	 * @throws PortalException if a dl file entry friendly url with the primary key could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL getDLFileEntryFriendlyURL(
			long fileEntryFriendlyURLId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.getDLFileEntryFriendlyURL(
			fileEntryFriendlyURLId);
	}

	/**
	 * Returns the dl file entry friendly url matching the UUID and group.
	 *
	 * @param uuid the dl file entry friendly url's UUID
	 * @param groupId the primary key of the group
	 * @return the matching dl file entry friendly url
	 * @throws PortalException if a matching dl file entry friendly url could not be found
	 */
	@Override
	public DLFileEntryFriendlyURL getDLFileEntryFriendlyURLByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.
			getDLFileEntryFriendlyURLByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<DLFileEntryFriendlyURL> getDLFileEntryFriendlyURLs(
		int start, int end) {

		return _dlFileEntryFriendlyURLLocalService.getDLFileEntryFriendlyURLs(
			start, end);
	}

	/**
	 * Returns all the dl file entry friendly urls matching the UUID and company.
	 *
	 * @param uuid the UUID of the dl file entry friendly urls
	 * @param companyId the primary key of the company
	 * @return the matching dl file entry friendly urls, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<DLFileEntryFriendlyURL>
		getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			String uuid, long companyId) {

		return _dlFileEntryFriendlyURLLocalService.
			getDLFileEntryFriendlyURLsByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<DLFileEntryFriendlyURL>
		getDLFileEntryFriendlyURLsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<DLFileEntryFriendlyURL> orderByComparator) {

		return _dlFileEntryFriendlyURLLocalService.
			getDLFileEntryFriendlyURLsByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of dl file entry friendly urls.
	 *
	 * @return the number of dl file entry friendly urls
	 */
	@Override
	public int getDLFileEntryFriendlyURLsCount() {
		return _dlFileEntryFriendlyURLLocalService.
			getDLFileEntryFriendlyURLsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _dlFileEntryFriendlyURLLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _dlFileEntryFriendlyURLLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dlFileEntryFriendlyURLLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dlFileEntryFriendlyURLLocalService.getPersistedModel(
			primaryKeyObj);
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
	@Override
	public DLFileEntryFriendlyURL updateDLFileEntryFriendlyURL(
		DLFileEntryFriendlyURL dlFileEntryFriendlyURL) {

		return _dlFileEntryFriendlyURLLocalService.updateDLFileEntryFriendlyURL(
			dlFileEntryFriendlyURL);
	}

	@Override
	public CTPersistence<DLFileEntryFriendlyURL> getCTPersistence() {
		return _dlFileEntryFriendlyURLLocalService.getCTPersistence();
	}

	@Override
	public Class<DLFileEntryFriendlyURL> getModelClass() {
		return _dlFileEntryFriendlyURLLocalService.getModelClass();
	}

	@Override
	public <R, E extends Throwable> R updateWithUnsafeFunction(
			UnsafeFunction<CTPersistence<DLFileEntryFriendlyURL>, R, E>
				updateUnsafeFunction)
		throws E {

		return _dlFileEntryFriendlyURLLocalService.updateWithUnsafeFunction(
			updateUnsafeFunction);
	}

	@Override
	public DLFileEntryFriendlyURLLocalService getWrappedService() {
		return _dlFileEntryFriendlyURLLocalService;
	}

	@Override
	public void setWrappedService(
		DLFileEntryFriendlyURLLocalService dlFileEntryFriendlyURLLocalService) {

		_dlFileEntryFriendlyURLLocalService =
			dlFileEntryFriendlyURLLocalService;
	}

	private DLFileEntryFriendlyURLLocalService
		_dlFileEntryFriendlyURLLocalService;

}