/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.rapidpm.vaadin.jumpstart.gui.menubar;

import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.MenuBar;
import org.rapidpm.ddi.DI;
import org.rapidpm.vaadin.jumpstart.gui.basics.MainWindow;
import org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations.GithubOrganizationsScreenCustom;
import org.rapidpm.vaadin.jumpstart.gui.screens.info.ContactScreen;
import org.rapidpm.vaadin.jumpstart.gui.screens.info.DisclaimerScreen;
import org.rapidpm.vaadin.jumpstart.gui.screens.info.ImpressumScreen;
import org.rapidpm.vaadin.jumpstart.gui.screens.info.SupportScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static com.vaadin.ui.UI.getCurrent;
import static org.rapidpm.ddi.DI.activateDI;

public class RapidMenuBar extends MenuBar {

  public static final String MENUBAR = "menubar";

  @Inject
  PropertyService propertyService;

  @PostConstruct
  public void initMenuBar() {

    setId(MENUBAR);

    addItem(resolve("menue.default.main"), null, null)
        .addItem(resolve("menue.default.main.logout"), menuItem -> {
          getSession().close();
          final Page page = getCurrent().getPage();
          page.setLocation("/");
        });
    final MenuItem menuItemAnalytics = addItem(resolve("menue.default.analytics"), null, null);

    final MenuItem menuItemAnalyticsGithub = menuItemAnalytics.addItem(resolve("menue.default.analytics.github"), null, null);
    menuItemAnalyticsGithub.addItem(resolve("menue.default.analytics.github.orga"), null, (Command) selectedItem -> {
      final Component content = getCurrent().getContent();
      final MainWindow mainWindow = (MainWindow) content;
      final ComponentContainer workingAreaContainer = mainWindow.getWorkingAreaContainer();
      workingAreaContainer.removeAllComponents();
      final GithubOrganizationsScreenCustom custom = DI.activateDI(GithubOrganizationsScreenCustom.class);
      workingAreaContainer.addComponent(custom);
    });

    menuItemAnalyticsGithub.addItem(resolve("menue.default.analytics.github.follower"), null, null);

    final MenuItem menuItemHelp = addItem(resolve("menue.default.help"), null, null);
    menuItemHelp.addItem(resolve("menue.default.help.contact"), menuItem -> getCurrent().addWindow(activateDI(ContactScreen.class)));
    menuItemHelp.addItem(resolve("menue.default.help.support"), menuItem -> getCurrent().addWindow(activateDI(SupportScreen.class)));
    menuItemHelp.addItem(resolve("menue.default.help.impressum"), menuItem -> getCurrent().addWindow(activateDI(ImpressumScreen.class)));
    menuItemHelp.addItem(resolve("menue.default.help.disclaimer"), menuItem -> getCurrent().addWindow(activateDI(DisclaimerScreen.class)));

  }

  private String resolve(String key) {
    return propertyService.resolve(key);
  }

}
