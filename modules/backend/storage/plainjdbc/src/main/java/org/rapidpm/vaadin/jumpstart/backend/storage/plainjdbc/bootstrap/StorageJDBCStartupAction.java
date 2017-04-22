package org.rapidpm.vaadin.jumpstart.backend.storage.plainjdbc.bootstrap;

import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageBootstrap;
import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageStartupAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by svenruppert on 13.04.17.
 */
@StorageStartupAction
public class StorageJDBCStartupAction implements StorageBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageJDBCStartupAction.class);

    @Override
    public Result bootstrap() {
        LOGGER.info("bootstrapping = " + StorageJDBCStartupAction.class.getSimpleName());

        // ramp up DB connection based on external config....  or Service Locator
        // here we are starting an InMemoryDB to make it easy....






        return Result.OK;
    }


//    public List<String> createSQLInitScriptArray() {
//
//
//
//
//        return new String[] { "0000_CLEAR_SCHEMA.sql",
//            "0001_CREATE_TABLE_CUSTOMER.sql",
//            "0002_INSERT_DATA_TABLE_CUSTOMER.sql",
//            "0003_CREATE_TABLE_LOGIN.sql",
//            "0004_CREATE_REF_TABLE_LOGIN.sql",
//            "0005_INSERT_DATA_TABLE_LOGIN.sql" };
//    }


}
