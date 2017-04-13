/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.function.Function;

import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.BasicJDBCOperation;

@FunctionalInterface
public interface QueryOneValue<T> extends BasicJDBCOperation<T> {

    default Optional<T> executeJDBCMethod(final Statement statement,
        final String sql)
        throws SQLException {

        final ResultSet resultSet = statement.executeQuery(createSQL());
        final boolean next = resultSet.next();
        if (next) {
            final Optional<T> firstElement = createMappingFunction()
                .apply(resultSet);
            if (resultSet.next()) {
                throw new RuntimeException(
                    "too many values are selected with query");
            } else {
                return firstElement;
            }
        } else {
            return Optional.empty();
        }
    }

    default Function<ResultSet, Optional<T>> createMappingFunction() {
        throw new RuntimeException(
            "createMappingFunction -> not yet implemented");
    }

}
