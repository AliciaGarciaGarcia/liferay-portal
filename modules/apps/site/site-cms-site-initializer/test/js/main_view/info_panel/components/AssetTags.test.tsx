/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom';
import {cleanup, fireEvent, render, screen, waitFor} from '@testing-library/react';
import React from 'react';

import ApiHelper from '../../../../../src/main/resources/META-INF/resources/js/common/services/ApiHelper';
import AssetTags from '../../../../../src/main/resources/META-INF/resources/js/main_view/info_panel/components/AssetTags';

jest.mock('../../../../../src/main/resources/META-INF/resources/js/common/services/ApiHelper');

jest.mock('@liferay/frontend-js-item-selector-web', () => {
	const React = require('react');
	const mockItemSelector = ({children, onChange, primaryAction}: any) => (
		<div data-testid="item-selector">
			<input
				data-testid="item-selector-input"
				onChange={(e) => onChange(e.target.value)}
			/>
			{primaryAction && (
				<button data-testid="primary-action" onClick={primaryAction.onClick}>
					{primaryAction.label}
				</button>
			)}
			{typeof children === 'function' ? children({name: 'tag1'}) : children}
		</div>
	);

	mockItemSelector.Item = ({children}: any) => <div>{children}</div>;

	return {
		ItemSelector: mockItemSelector,
	};
});

jest.mock('@clayui/label', () => ({
	__esModule: true,
	default: ({children}: any) => <div>{children}</div>,
}));

jest.mock('@clayui/panel', () => {
	const React = require('react');
	const Title = ({children}: any) => <div>{children}</div>;
	const Body = ({children}: any) => <div>{children}</div>;
	const Panel = ({children}: any) => <div>{children}</div>;
	Panel.Title = Title;
	Panel.Body = Body;
	return {
		__esModule: true,
		default: Panel,
	};
});

const mockObjectEntry = {
	keywords: ['tag1'],
	scopeId: 123,
};

const defaultProps = {
	assetLibraryId: 123,
	cmsGroupId: 456,
	hasUpdatePermission: true,
	objectEntry: mockObjectEntry as any,
	updateObjectEntry: jest.fn(),
};

describe('AssetTags', () => {
	afterEach(() => {
		jest.resetAllMocks();
		cleanup();
	});

	it('should render primaryAction if hasCreatePermission is true and value is typed', async () => {
		(ApiHelper.get as jest.Mock).mockResolvedValue({
			data: {
				actions: {
					create: true,
				},
			},
			error: null,
		});

		render(<AssetTags {...defaultProps} />);

		await waitFor(() => expect(ApiHelper.get).toHaveBeenCalled());

		const input = screen.getByTestId('item-selector-input');

		fireEvent.change(input, {target: {value: 'new-tag'}});

		expect(screen.getByTestId('primary-action')).toBeInTheDocument();
		expect(screen.getByText('create-new-tag-x')).toBeInTheDocument();
	});

	it('should NOT render primaryAction if hasCreatePermission is false even if value is typed', async () => {
		(ApiHelper.get as jest.Mock).mockResolvedValue({
			data: {
				actions: {
					// No create action
				},
			},
			error: null,
		});

		render(<AssetTags {...defaultProps} />);

		await waitFor(() => expect(ApiHelper.get).toHaveBeenCalled());

		const input = screen.getByTestId('item-selector-input');

		fireEvent.change(input, {target: {value: 'new-tag'}});

		expect(screen.queryByTestId('primary-action')).not.toBeInTheDocument();
	});

	it('should NOT render primaryAction if the typed value is already in the keywords list', async () => {
		(ApiHelper.get as jest.Mock).mockResolvedValue({
			data: {
				actions: {
					create: true,
				},
			},
			error: null,
		});

		render(<AssetTags {...defaultProps} />);

		await waitFor(() => expect(ApiHelper.get).toHaveBeenCalled());

		const input = screen.getByTestId('item-selector-input');

		// 'tag1' is already in mockObjectEntry.keywords
		fireEvent.change(input, {target: {value: 'tag1'}});

		expect(screen.queryByTestId('primary-action')).not.toBeInTheDocument();
	});
});
