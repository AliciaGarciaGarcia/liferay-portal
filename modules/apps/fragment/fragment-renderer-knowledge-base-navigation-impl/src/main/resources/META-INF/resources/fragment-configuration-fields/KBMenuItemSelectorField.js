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

import PropTypes from 'prop-types';
import React from 'react';

import ItemSelector from '@liferay/layout-content-page-editor-web/src/main/resources/META-INF/resources/page_editor/common/components/ItemSelector';
import {ConfigurationFieldPropTypes} from '@liferay/layout-content-page-editor-web/src/main/resources/META-INF/resources/page_editor/prop-types';
import {config} from '@liferay/layout-content-page-editor-web/src/main/resources/META-INF/resources/page_editor/app/config';
import itemSelectorValueToKBMenuItemSelectorItem from '../../utils/item-selector-value/itemSelectorValueToKBMenuItemSelectorItem';

export function KBMenuItemSelectorField({field, onValueSelect, value}) {
	const eventName = `${config.portletNamespace}selectKBMenuItemSelector`;

	const selectedValue = value
		? {
				...value,
				title:
					value.parentKBMenuItemSelectorItemId &&
					value.parentKBMenuItemSelectorItemId !== '0'
						? `... / ${value.title}`
						: value.title,
		  }
		: {
				title: Liferay.Language.get('knowledge-base-hierarchy'),
		  };

	return (
		<ItemSelector
			eventName={eventName}
			helpText={field.description}
			itemSelectorURL={config.kbMenuItemSelectorURL}
			label={field.label}
			modalProps={{height: '60vh', size: 'lg'}}
			onItemSelect={(kbMenuItemSelector) => {
				onValueSelect(field.name, kbMenuItemSelector);
			}}
			selectedItem={selectedValue}
			showMappedItems={false}
			transformValueCallback={itemSelectorValueToKBMenuItemSelectorItem}
		/>
	);
}

KBMenuItemSelectorField.propTypes = {
	field: PropTypes.shape(ConfigurationFieldPropTypes).isRequired,
	onValueSelect: PropTypes.func.isRequired,
	value: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
};
