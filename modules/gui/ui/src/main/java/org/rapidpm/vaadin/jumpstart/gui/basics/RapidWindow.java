package org.rapidpm.vaadin.jumpstart.gui.basics;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.inject.Inject;

public class RapidWindow extends Window {

  @Inject
  public PropertyService propertyService;

  private final VerticalLayout contentLayout = new VerticalLayout();

  public RapidWindow() {
    contentLayout.setSpacing(true);
    contentLayout.setMargin(true);
    setContent(contentLayout);
    setModal(true);
  }

  public void addComponent(final Component component) {
    contentLayout.addComponent(component);
  }

  public void removeAllComponents() {
    setContent(null);
  }

  public VerticalLayout getContentLayout() {
    return contentLayout;
  }
}
