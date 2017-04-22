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

package junit.org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.JDBCConnectionPools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public abstract class HsqlBaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HsqlBaseTest.class);

    private final String[] scripts = createSQLInitScriptArray();
    final JDBCConnectionPools pools = new JDBCConnectionPools();

    public JDBCConnectionPools pools() {
        return pools;
    }

    public String[] createSQLInitScriptArray() {
        return new String[] { "0000_CLEAR_SCHEMA.sql",
            "0001_CREATE_TABLE_CUSTOMER.sql",
            "0002_INSERT_DATA_TABLE_CUSTOMER.sql",
            "0003_CREATE_TABLE_LOGIN.sql",
            "0004_CREATE_REF_TABLE_LOGIN.sql",
            "0005_INSERT_DATA_TABLE_LOGIN.sql" };
    }

    private InMemoryHsqldbBuilder.ServerResult serverResult;

    @Before
    public void setUp()
        throws Exception {
        System.out.println("poolname = " + poolname());

        serverResult = InMemoryHsqldbBuilder.newBuilder().withDbName("testDB").withRandomPort().build();

        startPoolsAndConnect(poolname(), serverResult.getUrl());
        initSchema(poolname());
    }

    @After
    public void tearDown()
        throws Exception {
        pools().shutdownPool(poolname());

    }

    //TODO could be more dynamic
    public void initSchema(final String poolname)
        throws Exception {

        for (final String script : scripts) {
            final Class<? extends HsqlBaseTest> aClass = baseTestClass();
            LOGGER.info("baseTestClass -> " + aClass.getName());

            final URL resource = aClass.getResource(script);
            if (resource == null) {
                LOGGER.info("load ressource from production folder resources/sql/" + script);
                final URL aClassResource = aClass.getResource("/sql/" + script);
                LOGGER.debug("resource.toExternalForm() = " + aClassResource);
                executeSqlScript(poolname, aClassResource.getPath());

            } else {
                LOGGER.debug("resource.toExternalForm() = " + resource.toExternalForm());
                executeSqlScript(poolname, resource.getPath());
            }
        }

        final URL testSqlResource = getClass().getResource(getClass().getSimpleName() + ".sql");
        if (testSqlResource != null) {
            final String testSqlPath = testSqlResource.getPath();
            executeSqlScript(poolname, testSqlPath);
        } else {
            LOGGER.debug("No SQL for " + getClass().getSimpleName());
        }
    }

    public abstract Class baseTestClass();

    private void executeSqlScript(final String poolname, final String filePath) {
        LOGGER.debug("executeSqlScript-poolname = " + poolname);
        LOGGER.debug("executeSqlScript-filePath = " + filePath);

        final HikariDataSource dataSource = pools().getDataSource(poolname);
        try (final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(filePath))); final Connection connection = dataSource
            .getConnection(); final Statement statement = connection.createStatement()) {

            final String sql = buffer.lines().collect(Collectors.joining("\n"));
            statement.executeUpdate(sql);

            connection.commit();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void startPoolsAndConnect(final String poolname, final String url) {
        startPoolsAndConnect(poolname, url, username(), password());
    }

    protected void startPoolsAndConnect(final String poolname, final String url, final String username, final String passwd) {
        pools().addJDBCConnectionPool(poolname).withJdbcURL(url).withUsername(username).withPasswd(passwd).withTimeout(2000).withAutoCommit(true).done();
        pools().connectPool(poolname);
    }

    public String username() {
        return "SA";
    }

    public String password() {
        return "";
    }

    public String poolname() {
        return this.getClass().getName();
    }

}
