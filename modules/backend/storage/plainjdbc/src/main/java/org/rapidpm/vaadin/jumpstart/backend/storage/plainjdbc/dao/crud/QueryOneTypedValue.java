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
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface QueryOneTypedValue<T> extends QueryOneValue<T> {

//  @FunctionalInterface
//  interface QueryOneOfType<T> extends QueryOneTypedValue<T> {
//    default Function<ResultSet, Optional<T>> createMappingFunction() {
//      throw new RuntimeException("not yet implemented");
//    }
//  }

  @FunctionalInterface
  interface QueryOneInteger extends QueryOneTypedValue<Integer> {
    default Function<ResultSet, Optional<Integer>> createMappingFunction() {
      return (resultSet) -> {
        try {
          return Optional.of(resultSet.getInt(1));
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return Optional.empty();
      };
    }
  }

  @FunctionalInterface
  interface QueryOneFloat extends QueryOneTypedValue<Float> {
    default Function<ResultSet, Optional<Float>> createMappingFunction() {
      return (resultSet) -> {
        try {
          return Optional.of(resultSet.getFloat(1));
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return Optional.empty();
      };
    }
  }

  @FunctionalInterface
  interface QueryOneString extends QueryOneTypedValue<String> {
    default Function<ResultSet, Optional<String>> createMappingFunction() {
      return (resultSet) -> {
        try {
          return Optional.of(resultSet.getString(1));
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return Optional.empty();
      };
    }
  }



}
