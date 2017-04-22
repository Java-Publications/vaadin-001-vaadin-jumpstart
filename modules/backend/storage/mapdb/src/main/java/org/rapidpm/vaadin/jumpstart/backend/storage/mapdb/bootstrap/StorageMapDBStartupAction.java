package org.rapidpm.vaadin.jumpstart.backend.storage.mapdb.bootstrap;

import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageBootstrap;
import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageStartupAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by svenruppert on 13.04.17.
 */

@StorageStartupAction
public class StorageMapDBStartupAction implements StorageBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageMapDBStartupAction.class);

    @Override
    public Result bootstrap() {
        LOGGER.info("bootstrapping = " + StorageMapDBStartupAction.class.getSimpleName());

        // ramp up DB connection based on external config....  or Service Locator
        // here we are starting an InMemoryDB to make it easy....

        return Result.OK;
    }
}