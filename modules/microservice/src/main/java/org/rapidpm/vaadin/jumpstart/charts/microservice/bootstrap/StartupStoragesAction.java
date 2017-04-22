package org.rapidpm.vaadin.jumpstart.charts.microservice.bootstrap;

import java.util.Optional;

import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;
import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageBootstrap;
import org.rapidpm.vaadin.jumpstart.persistence.bootstrap.StorageStartupAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by svenruppert on 13.04.17.
 */
public class StartupStoragesAction implements Main.MainStartupAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupStoragesAction.class);

    @Override
    public void execute(Optional<String[]> args) {
        LOGGER.info("Started with MicroService bootstrapping - " + this.getClass().getSimpleName());
        DI
            .getTypesAnnotatedWith(StorageStartupAction.class)
            .stream()
            .map(DI::activateDI)
            .map(o -> (StorageBootstrap) o)
            .forEach(StorageBootstrap::bootstrap);
        LOGGER.info("Stopped with MicroService bootstrapping" + this.getClass().getSimpleName());
    }
}
