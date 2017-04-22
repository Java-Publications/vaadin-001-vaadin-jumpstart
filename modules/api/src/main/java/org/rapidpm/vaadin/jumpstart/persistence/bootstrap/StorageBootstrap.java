package org.rapidpm.vaadin.jumpstart.persistence.bootstrap;

/**
 * Created by svenruppert on 13.04.17.
 */
@FunctionalInterface
public interface StorageBootstrap {
    Result bootstrap();

    enum Result {
        OK, ERROR
    }
}
