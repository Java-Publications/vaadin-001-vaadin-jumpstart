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

package org.rapidpm.vaadin.jumpstart.gui.basics;

import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class RapidTopPanel implements TopPanel {


  private static final String APP_LOGO = "app.logo";
  private static final String APP_VERSION = "app.version";
  private final HorizontalLayout iconsLayout = new HorizontalLayout();

  @Inject
  public PropertyService propertyService;

  @Override
  public Component getComponent() {
    return iconsLayout;
  }

  @PostConstruct
  private void createIconsLayout() {
    iconsLayout.setWidth("100%");
    iconsLayout.setMargin(new MarginInfo(false, false, false, false));

    if (propertyService.hasKey(APP_LOGO)) {
      final String resourceName = propertyService.resolve(APP_LOGO);
      final Image iconLeft = new Image(null, new ClassResource(resourceName));
      final Image iconRight = new Image(null, new ClassResource(resourceName));

      iconsLayout.addComponent(iconLeft);
      iconsLayout.setComponentAlignment(iconLeft, Alignment.TOP_LEFT);

      if (propertyService.hasKey(APP_VERSION)) {
        final Label versionLabel = new Label(propertyService.resolve(APP_VERSION));
        versionLabel.setSizeUndefined();
        iconsLayout.addComponent(versionLabel);
        iconsLayout.setComponentAlignment(versionLabel, Alignment.MIDDLE_CENTER);
        iconsLayout.setExpandRatio(versionLabel, 1.0f);
      } else {
        logger().info("no value for key : " + APP_VERSION);
      }

      iconsLayout.addComponent(iconRight);
      iconsLayout.setComponentAlignment(iconRight, Alignment.TOP_RIGHT);
    } else {
      logger().info("no value for key : " + APP_LOGO);
    }
  }

}
