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

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public interface MainWindow extends Component {

  String WORKING_AREA_CONTAINER = "workingAreaContainer";

  static MainWindow getCurrent() {
    return (MainWindow) UI.getCurrent().getContent();
  }

  ComponentContainer getWorkingAreaContainer();

  void setWorkingAreaContainer(ComponentContainer componentContainer);
}
