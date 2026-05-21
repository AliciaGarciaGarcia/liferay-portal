/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.depot.web.internal.util;

import com.liferay.depot.constants.DepotConstants;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.model.DepotEntryGroupRel;
import com.liferay.depot.search.DepotEntrySearch;
import com.liferay.depot.service.DepotEntryGroupRelLocalService;
import com.liferay.depot.service.DepotEntryService;
import com.liferay.item.selector.criteria.group.criterion.GroupItemSelectorCriterion;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.search.GroupSearch;

import jakarta.portlet.PortletRequest;
import jakarta.portlet.PortletResponse;
import jakarta.portlet.PortletURL;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(service = DepotEntryAdminSearchProvider.class)
public class DepotEntryAdminSearchProvider {

	public DepotEntrySearch getDepotEntrySearch(
			GroupItemSelectorCriterion groupItemSelectorCriterion,
			PortletRequest portletRequest, PortletResponse portletResponse,
			PortletURL portletURL)
		throws PortalException {

		if (!groupItemSelectorCriterion.isIncludeAllVisibleGroups()) {
			return _getGroupConnectedDepotEntrySearch(
				groupItemSelectorCriterion.getDepotEntryType(), portletRequest,
				portletResponse, portletURL);
		}

		return _getDepotEntrySearch(
			groupItemSelectorCriterion.getDepotEntryType(), portletRequest,
			portletResponse, portletURL);
	}

	public DepotEntrySearch getDepotEntrySearch(
			int depotEntryType, PortletRequest portletRequest,
			PortletResponse portletResponse, PortletURL portletURL)
		throws PortalException {

		return _getDepotEntrySearch(
			depotEntryType, portletRequest, portletResponse, portletURL);
	}

	public GroupSearch getGroupSearch(
			GroupItemSelectorCriterion groupItemSelectorCriterion,
			PortletRequest portletRequest, PortletResponse portletResponse,
			PortletURL portletURL)
		throws PortalException {

		DepotEntrySearch depotEntrySearch = getDepotEntrySearch(
			groupItemSelectorCriterion, portletRequest, portletResponse,
			portletURL);

		GroupSearch groupSearch = new GroupSearch(portletRequest, portletURL);

		groupSearch.setEmptyResultsMessage(
			depotEntrySearch.getEmptyResultsMessage());
		groupSearch.setResultsAndTotal(
			() -> TransformUtil.transform(
				depotEntrySearch.getResults(),
				depotEntry -> depotEntry.getGroup()),
			depotEntrySearch.getTotal());

		return groupSearch;
	}

	private BooleanClause[] _getBooleanClauses(long[] depotEntryIds) {
		BooleanQuery booleanQuery = new BooleanQuery();

		BooleanFilter booleanFilter = new BooleanFilter();

		if (ArrayUtil.isNotEmpty(depotEntryIds)) {
			TermsFilter entryClassPKTermsFilter = new TermsFilter(
				Field.ENTRY_CLASS_PK);

			entryClassPKTermsFilter.addValues(
				ArrayUtil.toStringArray(depotEntryIds));

			booleanFilter.add(entryClassPKTermsFilter, BooleanClauseOccur.MUST);
		}

		TermsFilter stagingGroupTermsFilter = new TermsFilter(
			Field.STAGING_GROUP);

		stagingGroupTermsFilter.addValue("false");

		booleanFilter.add(stagingGroupTermsFilter, BooleanClauseOccur.MUST);

		booleanQuery.setPreBooleanFilter(booleanFilter);

		return new BooleanClause[] {
			new BooleanClause<>(booleanQuery, BooleanClauseOccur.MUST)
		};
	}

	private DepotEntrySearch _getDepotEntrySearch(
			int depotEntryType, PortletRequest portletRequest,
			PortletResponse portletResponse, PortletURL portletURL)
		throws PortalException {

		DepotEntrySearch depotEntrySearch = new DepotEntrySearch(
			portletRequest, portletResponse, portletURL, "depotEntries");

		depotEntrySearch.setEmptyResultsMessage(
			_language.get(
				portletRequest.getLocale(),
				(depotEntryType == DepotConstants.TYPE_SPACE) ?
					"no-spaces-were-found" : "no-asset-libraries-were-found"));
		depotEntrySearch.setResultsAndTotal(
			() -> _getResults(
				null, depotEntrySearch, depotEntryType, portletRequest),
			_getTotal(null, depotEntryType, portletRequest));

		return depotEntrySearch;
	}

	private DepotEntrySearch _getGroupConnectedDepotEntrySearch(
			int depotEntryType, PortletRequest portletRequest,
			PortletResponse portletResponse, PortletURL portletURL)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		DepotEntrySearch depotEntrySearch = new DepotEntrySearch(
			portletRequest, portletResponse, portletURL, "depotEntries");

		depotEntrySearch.setEmptyResultsMessage(
			_language.get(
				portletRequest.getLocale(),
				(depotEntryType == DepotConstants.TYPE_SPACE) ?
					"no-spaces-were-found" : "no-asset-libraries-were-found"));

		String keywords = ParamUtil.getString(portletRequest, "keywords");

		if (Validator.isNull(keywords)) {
			depotEntrySearch.setResultsAndTotal(
				() -> _depotEntryService.getGroupConnectedDepotEntries(
					themeDisplay.getScopeGroupId(), depotEntryType,
					depotEntrySearch.getStart(), depotEntrySearch.getEnd()),
				_depotEntryService.getGroupConnectedDepotEntriesCount(
					themeDisplay.getScopeGroupId(), depotEntryType));

			return depotEntrySearch;
		}

		long[] depotEntryIds = TransformUtil.transformToLongArray(
			_depotEntryGroupRelLocalService.getDepotEntryGroupRels(
				themeDisplay.getScopeGroupId(), depotEntryType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			DepotEntryGroupRel::getDepotEntryId);

		if (ArrayUtil.isEmpty(depotEntryIds)) {
			depotEntrySearch.setResultsAndTotal(Collections::emptyList, 0);

			return depotEntrySearch;
		}

		depotEntrySearch.setResultsAndTotal(
			() -> _getResults(
				depotEntryIds, depotEntrySearch, depotEntryType,
				portletRequest),
			_getTotal(depotEntryIds, depotEntryType, portletRequest));

		return depotEntrySearch;
	}

	private List<DepotEntry> _getResults(
			long[] depotEntryIds, DepotEntrySearch depotEntrySearch,
			int depotEntryType, PortletRequest portletRequest)
		throws PortalException {

		Indexer<Object> indexer = IndexerRegistryUtil.getIndexer(
			DepotEntry.class.getName());

		SearchContext searchContext = _getSearchContext(
			depotEntryIds, depotEntryType, portletRequest);

		searchContext.setEnd(depotEntrySearch.getEnd());
		searchContext.setSorts(new Sort(Field.NAME, false));
		searchContext.setStart(depotEntrySearch.getStart());

		Hits hits = indexer.search(searchContext);

		return TransformUtil.transformToList(
			hits.getDocs(),
			document -> _depotEntryService.getDepotEntry(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))));
	}

	private SearchContext _getSearchContext(
		long[] depotEntryIds, int depotEntryType,
		PortletRequest portletRequest) {

		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(Field.TYPE, depotEntryType);

		searchContext.setBooleanClauses(_getBooleanClauses(depotEntryIds));

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		searchContext.setCompanyId(themeDisplay.getCompanyId());

		searchContext.setIncludeStagingGroups(false);

		String keywords = ParamUtil.getString(portletRequest, "keywords");

		if (Validator.isNotNull(keywords)) {
			searchContext.setKeywords(keywords);
		}

		return searchContext;
	}

	private int _getTotal(
			long[] depotEntryIds, int depotEntryType,
			PortletRequest portletRequest)
		throws SearchException {

		Indexer<Object> indexer = IndexerRegistryUtil.getIndexer(
			DepotEntry.class.getName());

		return (int)indexer.searchCount(
			_getSearchContext(depotEntryIds, depotEntryType, portletRequest));
	}

	@Reference
	private DepotEntryGroupRelLocalService _depotEntryGroupRelLocalService;

	@Reference
	private DepotEntryService _depotEntryService;

	@Reference
	private Language _language;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}