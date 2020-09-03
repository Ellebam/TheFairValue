package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Data.FinancialDataObject;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class HistoricalStockPriceTabPane extends TabPane {
    HistoricalStockPriceTabPane historicalStockPriceTabPane;
    DataContainerManager dataContainerManager;
    Tab fiveDayStockPrice;
    Tab thirtyDayStockPrice;
    Tab ninetyDayStockPrice;
    Tab oneYearStockPrice;
    Tab threeYearsStockPrice;
    Tab fiveYearsStockPrice;
    Tab allTimeStockPrice;

    public HistoricalStockPriceTabPane(DataContainerManager dataContainerManager){
        historicalStockPriceTabPane = this;

        fiveDayStockPrice = new Tab("5 Days");
        thirtyDayStockPrice = new Tab("30 Days");
        ninetyDayStockPrice = new Tab("90 Days");
        oneYearStockPrice = new Tab("1 Year");
        threeYearsStockPrice = new Tab("3 Years");
        fiveYearsStockPrice = new Tab ("5 Years");
        allTimeStockPrice = new Tab ("All");

        ArrayList<FinancialDataObject> valueList = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice();
        String seriesName = "Historical Stock Price";
        String xAxisName = "Date";
        String yAxisName = "Stock Price [$]";



        historicalStockPriceTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        historicalStockPriceTabPane.setStyle("-fx-font-size:12;");

        historicalStockPriceTabPane.getTabs().add(fiveDayStockPrice);
        historicalStockPriceTabPane.getTabs().add(thirtyDayStockPrice);
        historicalStockPriceTabPane.getTabs().add(ninetyDayStockPrice);
        historicalStockPriceTabPane.getTabs().add(oneYearStockPrice);
        historicalStockPriceTabPane.getTabs().add(threeYearsStockPrice);
        historicalStockPriceTabPane.getTabs().add(fiveYearsStockPrice);
        historicalStockPriceTabPane.getTabs().add(allTimeStockPrice);

        fiveDayStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,5));
        thirtyDayStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,21));
        ninetyDayStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,63));
        oneYearStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,253));
        threeYearsStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,759));
        fiveYearsStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,1265));
        allTimeStockPrice.setContent(GraphBuilder.buildLineChart(seriesName,xAxisName,yAxisName,valueList,0));
    }

}