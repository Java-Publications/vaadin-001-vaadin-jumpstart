package org.rapidpm.vaadin.jumpstart.gui.basics;

import javax.inject.Inject;

import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RapidWindow extends Window {

    @Inject public PropertyService propertyService;

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
