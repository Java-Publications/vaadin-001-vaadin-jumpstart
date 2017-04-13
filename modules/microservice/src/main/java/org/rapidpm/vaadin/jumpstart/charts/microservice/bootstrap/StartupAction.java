package org.rapidpm.vaadin.jumpstart.charts.microservice.bootstrap;

import java.util.Optional;

import org.rapidpm.microservice.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by svenruppert on 04.04.17.
 */
public class StartupAction implements Main.MainStartupAction {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(StartupAction.class);

    @Override
    public void execute(Optional<String[]> args) {
        // Start here the MicroService bootstraping stuff
        LOGGER.info("Started with MicroService bootstrapping");

        LOGGER.info("Stopped with MicroService bootstrapping");
    }
}
