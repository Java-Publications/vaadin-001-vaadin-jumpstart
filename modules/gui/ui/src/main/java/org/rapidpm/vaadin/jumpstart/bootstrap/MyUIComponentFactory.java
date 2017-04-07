package org.rapidpm.vaadin.jumpstart.bootstrap;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import org.rapidpm.vaadin.jumpstart.bootstrap.core.JumpstartUIComponentFactory;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;

import javax.inject.Inject;

/**
 * Created by svenruppert on 06.04.17.
 */
public class MyUIComponentFactory implements JumpstartUIComponentFactory {

  @Inject
  LoginScreenCustom loginScreen;

  @Override
  public ComponentContainer createComponentToSetAsContent(final VaadinRequest vaadinRequest) {
    return loginScreen;
  }
}
