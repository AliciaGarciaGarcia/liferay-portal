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

package com.liferay.dynamic.data.mapping.internal.upgrade.v5_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alicia García
 */
public class DDMFieldUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				StringBundler.concat(
					"select DDMStorageLink.classPK, ",
					"DDMStorageLink.structureVersionId from DDMStorageLink ",
					"inner join DDMStructure on DDMStorageLink.structureId = ",
					"DDMStructure.structureId where DDMStructure.structureKey ",
					"like ? "));
			PreparedStatement updatePreparedStatement =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update DDMField set parentFieldId = ? where ",
						"DDMField.storageId = ? and ",
						"DDMField.structureVersionId = ? and ",
						"DDMField.fieldName like ? "))) {

			preparedStatement.setString(1, "CUSTOM-META-TAGS");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long storageId = resultSet.getLong("classPK");
				long structureVersionId = resultSet.getLong(
					"structureVersionId");

				try (PreparedStatement preparedStatement2 =
						connection.prepareStatement(
							StringBundler.concat(
								"select DDMField.fieldId from DDMField where ",
								"DDMField.storageId = ? and ",
								"DDMField.structureVersionId = ? and ",
								"DDMField.fieldName like ? "))) {

					preparedStatement2.setLong(1, storageId);
					preparedStatement2.setLong(2, structureVersionId);
					preparedStatement2.setString(3, "property");

					try (ResultSet resultSet2 =
							preparedStatement2.executeQuery()) {

						while (resultSet2.next()) {
							updatePreparedStatement.setLong(
								1, resultSet2.getLong("fieldId"));
							updatePreparedStatement.setLong(2, storageId);
							updatePreparedStatement.setLong(
								3, structureVersionId);
							updatePreparedStatement.setString(4, "content");

							updatePreparedStatement.addBatch();
						}
					}
				}
			}

			updatePreparedStatement.executeBatch();
		}
	}

}