package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Data.FinancialDataObject;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;


/**
 * Class representing the Graphs showing historical stock prices for several date ranges
 */
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

    /**
     * Constructor for the HistoricalStockPriceTabPane. It will construct a new TabPane with different
     * date ranges and matching new line charts built by the GraphBuilder Class.
     * @param dataContainerManager Object containing all data
     */
    public HistoricalStockPriceTabPane(DataContainerManager dataContainerManager){
        historicalStockPriceTabPane = this;
        this.dataContainerManager = dataContainerManager;

        fiveDayStockPrice = new Tab("5 Days");
        thirtyDayStockPrice = new Tab("30 Days");
        ninetyDayStockPrice = new Tab("90 Days");
        oneYearStockPrice = new Tab("1 Year");
        threeYearsStockPrice = new Tab("3 Years");
        fiveYearsStockPrice = new Tab ("5 Years");
        allTimeStockPrice = new Tab ("All");

        //Graph building variables
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
