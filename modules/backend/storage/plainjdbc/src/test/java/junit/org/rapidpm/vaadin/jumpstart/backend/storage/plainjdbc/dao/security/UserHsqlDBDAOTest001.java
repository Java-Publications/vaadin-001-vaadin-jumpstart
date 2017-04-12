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

package junit.org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security;


import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.vaadin.jumpstart.api.model.security.User;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPool;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.dao.security.UserHsqlDBDAO;

import java.util.Optional;

public class UserHsqlDBDAOTest001 extends UserHsqlDBDAOBaseTest {


  @Test
  public void test001() throws Exception {
    final Optional<JDBCConnectionPool> connectionPoolOptional = pools().getPool(poolname());
    final JDBCConnectionPool connectionPool = connectionPoolOptional.get();
    final UserHsqlDBDAO userDAO = new UserHsqlDBDAO();
    userDAO.workOnPool(connectionPool);

    final User user = new User(999, "jon", "doe", "jon.d@yahooo.com");
    userDAO.write(user);

    final Optional<User> resultUser = userDAO.read(999);
    Assert.assertNotNull(resultUser);
    Assert.assertTrue(resultUser.isPresent());
    Assert.assertEquals(user.getCustomerID(), resultUser.get().getCustomerID());
    Assert.assertEquals(user.getFirstname(), resultUser.get().getFirstname());

    final User user02 = new User(998, "jane", "doe", "jane.d@yahooo.com");
    userDAO.write(user02);

    final Optional<String> resultMail = userDAO.readMailAddress(998);
    Assert.assertNotNull(resultMail);
    Assert.assertTrue(resultMail.isPresent());
    Assert.assertEquals("jane.d@yahooo.com", resultMail.get());
  }


}
