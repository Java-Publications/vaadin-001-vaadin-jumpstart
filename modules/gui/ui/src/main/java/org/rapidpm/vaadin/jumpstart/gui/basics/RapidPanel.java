package org.rapidpm.vaadin.jumpstart.gui.basics;

import com.vaadin.ui.VerticalLayout;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.inject.Inject;

public class RapidPanel extends VerticalLayout {

  @Inject
  PropertyService propertyService;

  public RapidPanel() {
    this.setSpacing(true);
    this.setMargin(true);
  }
}
