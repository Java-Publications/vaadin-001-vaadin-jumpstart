package org.rapidpm.vaadin.jumpstart.bootstrap.core;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import javax.inject.Inject;

/**
 * Created by svenruppert on 06.04.17.
 */
@Theme("valo")
@Push
public class JumpstartUI extends UI {

  @Inject JumpstartUIComponentFactory jumpstartUIComponentFactory;

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    final ComponentContainer mainComponent =
        jumpstartUIComponentFactory.createComponentToSetAsContent(vaadinRequest);
    setContent(mainComponent);
  }
}
