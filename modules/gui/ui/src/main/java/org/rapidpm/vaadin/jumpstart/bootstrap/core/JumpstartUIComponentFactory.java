package org.rapidpm.vaadin.jumpstart.bootstrap.core;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;

import java.io.Serializable;

/**
 * Created by svenruppert on 06.04.17.
 */
@FunctionalInterface
public interface JumpstartUIComponentFactory extends Serializable {
  ComponentContainer createComponentToSetAsContent(VaadinRequest vaadinRequest);
}
