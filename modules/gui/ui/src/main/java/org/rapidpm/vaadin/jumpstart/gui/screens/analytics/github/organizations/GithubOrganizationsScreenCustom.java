package org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.ui.Button;
import org.rapidpm.vaadin.jumpstart.gui.design.analytics.github.organizations.GithubOrganizationsScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by svenruppert on 10.04.17.
 */
public class GithubOrganizationsScreenCustom extends GithubOrganizationsScreen {

  private static final String PREFIX = "analytics.github.orga";

  @Inject
  PropertyService propertyService;


  @PostConstruct
  public void postConstruct() {

    btnClear.setCaption(resolve("clear"));
    btnClear.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent event) {
        ///
      }
    });


    btnLoad.setCaption(resolve("load"));
    btnLoad.addClickListener(
        new Button.ClickListener() {
          @Override
          public void buttonClick(Button.ClickEvent event) {
            ///load data from storage
          }
        }
    );


//    add manually the chart into the component created from the designer

    Chart chart = new Chart(ChartType.COLUMN);


    panelChart.setContent(chart);


  }

  public String resolve(String key) {
    return propertyService.resolve(PREFIX + "." + key);
  }
}
