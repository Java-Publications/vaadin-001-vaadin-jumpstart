package org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.Button;
import org.rapidpm.vaadin.jumpstart.gui.design.analytics.github.organizations.GithubOrganizationsScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.stream.IntStream;

/**
 * Created by svenruppert on 10.04.17.
 */
public class GithubOrganizationsScreenCustom extends GithubOrganizationsScreen {

  public static final String PREFIX = "analytics.github.orga.";

  public static final String BUTTON_CLEAR_ID = PREFIX + "clear";
  public static final String BUTTON_LOAD_ID = PREFIX + "load";


  @Inject
  PropertyService propertyService;


  @PostConstruct
  public void postConstruct() {


    Chart chart = new Chart(ChartType.COLUMN);

    final Configuration conf = chart.getConfiguration();
    conf.setTitle(resolve("chart.title"));

    conf.getLegend().setEnabled(true);
    final RangeSelector rangeSelector = new RangeSelector(true);
    conf.setRangeSelector(rangeSelector);

//DEmo DAta

//    new DataProviderSeries<>()
    DataSeries dataSeries = new DataSeries();
    IntStream.range(0,1_000).forEach(i -> {
      final DataSeriesItem item = new DataSeriesItem();
      item.setX(i);
      item.setY(System.currentTimeMillis());
      dataSeries.add(item);
    });

    conf.setSeries(dataSeries);

    panelChart.setContent(chart);


    btnClear.setId(BUTTON_CLEAR_ID);
    btnClear.setCaption(resolve("clear"));
    btnClear.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent event) {
        ///
      }
    });


    btnLoad.setId(BUTTON_LOAD_ID);
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


  }

  public String resolve(String key) {
    return propertyService.resolve(PREFIX + key);
  }
}
