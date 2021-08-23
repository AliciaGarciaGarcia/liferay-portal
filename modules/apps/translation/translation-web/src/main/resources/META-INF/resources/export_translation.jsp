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
	<aui:form action="<%= exportTranslationDisplayContext.getExportTranslationURL() %>" cssClass="translation-export" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= exportTranslationDisplayContext.getRedirect() %>" />
		<aui:input name="portletResource" type="hidden" value='<%= ParamUtil.getString(request, "portletResource") %>' />

		<clay:container-fluid
			cssClass="container-view"
		>
			<clay:sheet>
				<div>
					<aui:select label="export-file-format" name="exportMimeType" wrapperCssClass="w-50"></aui:select>
					<aui:select label="original-language" name="sourceLanguageId"></aui:select>
					<div class="form-group">
						<label class="mb-2"><liferay-ui:message key="languages-to-translate-to" /></label>

						<div class="row">

							<%
							for (Locale aviableLocale : LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId())) {
							%>

								<div class="col-md-4 py-2">
									<div class="custom-checkbox custom-control">
										<label>
											<input class="custom-control-input" name="targetLanguageIds" type="checkbox" />

											<span class="custom-control-label">
												<span class="custom-control-label-text"><%= aviableLocale.getDisplayName(locale) %></span>
											</span>
										</label>
									</div>
								</div>

							<%
							}
							%>

						</div>
					</div>

					<%
					List<Map<String, String>> experiences = exportTranslationDisplayContext.getExperiences();
					%>

					<c:if test="<%= Validator.isNotNull(experiences) %>">
						<div class="form-group">
							<label class="mb-2"><liferay-ui:message key="select-experiences" /></label>

							<ul class="list-group">

								<%
								for (Map<String, String> experience : experiences) {
								%>

									<li class="list-group-item">
										<div class="custom-checkbox custom-control">
											<label>
												<input class="custom-control-input" name="targetLanguageIds" type="checkbox" />

												<span class="custom-control-label">
													<span class="custom-control-label-text"><%= experience.get("label") %></span>
												</span>
											</label>
										</div>
									</li>

								<%
								}
								%>

							</ul>
						</div>
					</c:if>

					<div class="btn-group">
						<div class="btn-group-item">
							<aui:button name="cancel" type="cancel" />
						</div>

						<div class="btn-group-item">
							<aui:button disabled="<%= true %>" type="submit" value="export" />
						</div>
					</div>

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