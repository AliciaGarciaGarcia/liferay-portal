<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
portletDisplay.setShowBackIcon(true);
ExportTranslationDisplayContext exportTranslationDisplayContext = (ExportTranslationDisplayContext)request.getAttribute(ExportTranslationDisplayContext.class.getName());

portletDisplay.setURLBack(exportTranslationDisplayContext.getRedirect());

renderResponse.setTitle(exportTranslationDisplayContext.getTitle());
%>

<div class="translation">
	<aui:form action="" name="fm">
		<aui:input name="redirect" type="hidden" value="" />
		<aui:input name="portletResource" type="hidden" value='<%= ParamUtil.getString(request, "portletResource") %>' />

		<clay:container-fluid
			cssClass="container-view"
		>
			<clay:sheet
				cssClass="translation-import-body-form"
			>
				<div>
					<react:component
						module="js/ExportTranslation"
						props="<%=
							exportTranslationDisplayContext.getExportTranslationData()
						%>"
					/>
				</div>
			</clay:sheet>
		</clay:container-fluid>
	</aui:form>
</div>