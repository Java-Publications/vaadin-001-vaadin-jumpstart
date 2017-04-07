package org.rapidpm.vaadin.jumpstart.bootstrap;

import com.vaadin.annotations.VaadinServletConfiguration;
import org.rapidpm.vaadin.jumpstart.bootstrap.core.DDIVaadinServlet;
import org.rapidpm.vaadin.jumpstart.bootstrap.core.JumpstartUI;

import javax.servlet.annotation.WebServlet;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by svenruppert on 06.04.17.
 */
@WebServlet(urlPatterns = "/*", name = "JumpstartServlet", asyncSupported = true, displayName = "JumpstartServlet")
@VaadinServletConfiguration(ui = JumpstartUI.class, productionMode = false)
public class JumpstartServlet extends DDIVaadinServlet {

  @Override
  public List<String> topLevelPackagesToActivate() {
    return singletonList("org.rapidpm");
  }
}