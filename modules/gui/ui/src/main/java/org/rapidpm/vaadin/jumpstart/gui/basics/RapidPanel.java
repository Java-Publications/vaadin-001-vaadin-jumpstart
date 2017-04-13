package org.rapidpm.vaadin.jumpstart.gui.basics;

import javax.inject.Inject;

import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import com.vaadin.ui.VerticalLayout;

public class RapidPanel extends VerticalLayout {

    @Inject PropertyService propertyService;

    public RapidPanel() {
        this.setSpacing(true);
        this.setMargin(true);
    }
}
