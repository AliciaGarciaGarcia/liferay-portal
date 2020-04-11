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

package com.liferay.blogs.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Roberto Díaz
 */
@ExtendedObjectClassDefinition(category = "blogs")
@Meta.OCD(
	id = "com.liferay.blogs.configuration.BlogsFileUploadsConfiguration",
	localization = "content/Language",
	name = "blogs-file-uploads-configuration-name"
)
public interface BlogsFileUploadsConfiguration {

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #supportedMimeTypes()}
	 */
	@Deprecated
	@Meta.AD(
		deflt = ".gif,.jpeg,.jpg,.png",
		description = "blogs-allowed-image-file-extensions-description",
		name = "blogs-allowed-image-file-extensions", required = false
	)
	public String[] imageExtensions();

	/**
	 * Sets the supported mime types that generate adaptive media images.
	 */
	@Meta.AD(
		deflt = "image/gif|image/jpeg|image/pjpeg|image/png|image/x-citrix-jpeg|image/x-citrix-png|image/x-ms-bmp|image/x-png",
		description = "blogs-allowed-image-file-mime-types-description",
		name = "blogs-allowed-image-file-mime-types", required = false
	)
	public String[] supportedMimeTypes();

	@Meta.AD(
		deflt = "5242880",
		description = "blogs-image-maximum-file-size-description",
		name = "blogs-image-maximum-file-size", required = false
	)
	public long imageMaxSize();

}