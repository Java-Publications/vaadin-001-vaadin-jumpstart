package org.rapidpm.vaadin.jumpstart.bootstrap;

import javax.inject.Inject;

import org.rapidpm.vaadin.jumpstart.bootstrap.core.JumpstartUIComponentFactory;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;

/**
 * Created by svenruppert on 06.04.17.
 */
public class MyUIComponentFactory implements JumpstartUIComponentFactory {

    @Inject LoginScreenCustom loginScreen;

    @Override
    public ComponentContainer createComponentToSetAsContent(
        final VaadinRequest vaadinRequest) {
        return loginScreen;
    }
}
