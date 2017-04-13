package org.rapidpm.vaadin.jumpstart.gui.screens.analytics.github.organizations;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.rapidpm.vaadin.jumpstart.gui.design.analytics.github.organizations.GithubOrganizationsScreen;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.properties.PropertyService;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.ui.Button;

/**
 * Created by svenruppert on 10.04.17.
 */
public class GithubOrganizationsScreenCustom extends GithubOrganizationsScreen {

    public static final String PREFIX = "analytics.github.orga.";

    public static final String BUTTON_CLEAR_ID = PREFIX + "clear";
    public static final String BUTTON_LOAD_ID = PREFIX + "load";

    @Inject PropertyService propertyService;

    @PostConstruct
    public void postConstruct() {

        Chart chart = createTheChart();

        panelChart.setContent(chart);

        btnClear.setId(BUTTON_CLEAR_ID);
        btnClear.setCaption(resolve("clear"));
        btnClear.addClickListener((Button.ClickListener) event -> {
            ///
        });

        btnLoad.setId(BUTTON_LOAD_ID);
        btnLoad.setCaption(resolve("load"));
        btnLoad.addClickListener((Button.ClickListener) event -> {
            ///load data from storage
            System.out.println("event = " + event);
        });

        //    add manually the chart into the component created from the designer

    }

    private Chart createTheChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        final Configuration conf = chart.getConfiguration();
        conf.setTitle(resolve("chart.title"));

        conf.getLegend().setEnabled(true);
        final RangeSelector rangeSelector = new RangeSelector(true);
        conf.setRangeSelector(rangeSelector);

        final CallbackDataProvider<Integer,Integer> dataProvider = new CallbackDataProvider<>(
            (CallbackDataProvider.FetchCallback<Integer, Integer>) query -> IntStream
                .range(0, 20)
                .peek(value -> System.out.println("dataProvider - fetch - value = " + value))
                .boxed(),
            (CallbackDataProvider.CountCallback<Integer, Integer>) query -> 20);

        final DataProviderSeries<Integer> providerSeries = new DataProviderSeries<>(dataProvider, String::valueOf);

        providerSeries.setAutomaticChartUpdateEnabled(true);
        providerSeries.setName("Stream generated data");

        conf.setSeries(providerSeries);




        return chart;
    }

    public String resolve(String key) {
        return propertyService.resolve(PREFIX + key);
    }
}
