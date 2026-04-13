/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.rest.internal.resource.v1_0;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.rest.internal.security.permission.LiberalPermissionChecker;
import com.liferay.object.rest.resource.v1_0.InvitedCollaboratorResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.core.UriInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Alicia García
 */
public class InvitedCollaboratorResourceFactoryImpl
	implements InvitedCollaboratorResource.Factory {

	public InvitedCollaboratorResourceFactoryImpl(
		CompanyLocalService companyLocalService,
		Supplier<InvitedCollaboratorResourceImpl>
			invitedCollaboratorResourceImplSupplier,
		PermissionCheckerFactory defaultPermissionCheckerFactory,
		ExpressionConvert<Filter> expressionConvert,
		FilterParserProvider filterParserProvider,
		GroupLocalService groupLocalService,
		Map<Long, ObjectDefinition> objectDefinitions,
		ResourceActionLocalService resourceActionLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService,
		SortParserProvider sortParserProvider,
		UserLocalService userLocalService) {

		_companyLocalService = companyLocalService;
		_invitedCollaboratorResourceImplSupplier =
			invitedCollaboratorResourceImplSupplier;
		_defaultPermissionCheckerFactory = defaultPermissionCheckerFactory;
		_expressionConvert = expressionConvert;
		_filterParserProvider = filterParserProvider;
		_groupLocalService = groupLocalService;
		_objectDefinitions = objectDefinitions;
		_resourceActionLocalService = resourceActionLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
		_sortParserProvider = sortParserProvider;
		_userLocalService = userLocalService;
	}

	@Override
	public InvitedCollaboratorResource.Builder create() {
		return new InvitedCollaboratorResource.Builder() {

			@Override
			public InvitedCollaboratorResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				Function<InvocationHandler, InvitedCollaboratorResource>
					invitedCollaboratorResourceProxyProviderFunction =
						ResourceProxyProviderFunctionHolder.
							_invitedCollaboratorResourceProxyProviderFunction;

				return invitedCollaboratorResourceProxyProviderFunction.apply(
					(proxy, method, arguments) -> _invoke(
						method, arguments, _checkPermissions,
						_httpServletRequest, _httpServletResponse,
						_preferredLocale, _uriInfo, _user));
			}

			@Override
			public InvitedCollaboratorResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public InvitedCollaboratorResource.Builder httpServletRequest(
				HttpServletRequest httpServletRequest) {

				_httpServletRequest = httpServletRequest;

				return this;
			}

			@Override
			public InvitedCollaboratorResource.Builder httpServletResponse(
				HttpServletResponse httpServletResponse) {

				_httpServletResponse = httpServletResponse;

				return this;
			}

			@Override
			public InvitedCollaboratorResource.Builder preferredLocale(
				Locale preferredLocale) {

				_preferredLocale = preferredLocale;

				return this;
			}

			@Override
			public InvitedCollaboratorResource.Builder uriInfo(
				UriInfo uriInfo) {

				_uriInfo = uriInfo;

				return this;
			}

			@Override
			public InvitedCollaboratorResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private HttpServletRequest _httpServletRequest;
			private HttpServletResponse _httpServletResponse;
			private Locale _preferredLocale;
			private UriInfo _uriInfo;
			private User _user;

		};
	}

	private static Function<InvocationHandler, InvitedCollaboratorResource>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			InvitedCollaboratorResource.class.getClassLoader(),
			InvitedCollaboratorResource.class);

		try {
			Constructor<InvitedCollaboratorResource> constructor =
				(Constructor<InvitedCollaboratorResource>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private Object _invoke(
			Method method, Object[] arguments, boolean checkPermissions,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Locale preferredLocale,
			UriInfo uriInfo, User user)
		throws Throwable {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (checkPermissions) {
			PermissionThreadLocal.setPermissionChecker(
				_defaultPermissionCheckerFactory.create(user));
		}
		else {
			PermissionThreadLocal.setPermissionChecker(
				new LiberalPermissionChecker(user));
		}

		InvitedCollaboratorResourceImpl invitedCollaboratorResourceImpl =
			_invitedCollaboratorResourceImplSupplier.get();

		invitedCollaboratorResourceImpl.setContextAcceptLanguage(
			new AcceptLanguageImpl(httpServletRequest, preferredLocale, user));

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		invitedCollaboratorResourceImpl.setContextCompany(company);

		invitedCollaboratorResourceImpl.setContextHttpServletRequest(
			httpServletRequest);
		invitedCollaboratorResourceImpl.setContextHttpServletResponse(
			httpServletResponse);
		invitedCollaboratorResourceImpl.setContextUriInfo(uriInfo);
		invitedCollaboratorResourceImpl.setContextUser(user);
		invitedCollaboratorResourceImpl.setExpressionConvert(
			_expressionConvert);
		invitedCollaboratorResourceImpl.setFilterParserProvider(
			_filterParserProvider);
		invitedCollaboratorResourceImpl.setGroupLocalService(
			_groupLocalService);
		invitedCollaboratorResourceImpl.setObjectDefinition(
			_objectDefinitions.get(company.getCompanyId()));
		invitedCollaboratorResourceImpl.setResourceActionLocalService(
			_resourceActionLocalService);
		invitedCollaboratorResourceImpl.setResourcePermissionLocalService(
			_resourcePermissionLocalService);
		invitedCollaboratorResourceImpl.setRoleLocalService(_roleLocalService);
		invitedCollaboratorResourceImpl.setSortParserProvider(
			_sortParserProvider);

		try {
			return method.invoke(invitedCollaboratorResourceImpl, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
		finally {
			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final PermissionCheckerFactory _defaultPermissionCheckerFactory;
	private final ExpressionConvert<Filter> _expressionConvert;
	private final FilterParserProvider _filterParserProvider;
	private final GroupLocalService _groupLocalService;
	private final Supplier<InvitedCollaboratorResourceImpl>
		_invitedCollaboratorResourceImplSupplier;
	private final Map<Long, ObjectDefinition> _objectDefinitions;
	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;
	private final SortParserProvider _sortParserProvider;
	private final UserLocalService _userLocalService;

	private static class ResourceProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, InvitedCollaboratorResource>
				_invitedCollaboratorResourceProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private class AcceptLanguageImpl implements AcceptLanguage {

		public AcceptLanguageImpl(
			HttpServletRequest httpServletRequest, Locale preferredLocale,
			User user) {

			_httpServletRequest = httpServletRequest;
			_preferredLocale = preferredLocale;
			_user = user;
		}

		@Override
		public List<Locale> getLocales() {
			return Arrays.asList(getPreferredLocale());
		}

		@Override
		public String getPreferredLanguageId() {
			return LocaleUtil.toLanguageId(getPreferredLocale());
		}

		@Override
		public Locale getPreferredLocale() {
			if (_preferredLocale != null) {
				return _preferredLocale;
			}

			if (_httpServletRequest != null) {
				Locale locale = (Locale)_httpServletRequest.getAttribute(
					WebKeys.LOCALE);

				if (locale != null) {
					return locale;
				}
			}

			return _user.getLocale();
		}

		@Override
		public boolean isAcceptAllLanguages() {
			return false;
		}

		private final HttpServletRequest _httpServletRequest;
		private final Locale _preferredLocale;
		private final User _user;

	}

}