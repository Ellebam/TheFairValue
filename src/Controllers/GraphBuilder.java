package Controllers;

import Data.FinancialDataObject;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import sample.Main;

import java.net.URL;
import java.util.ArrayList;


/**
 * This Class contains methods for building several different graphs (e.g. line charts, stacked area charts, etc.)
 */
public class GraphBuilder {

    /**
     * Constructor for building a line chart. It's data base is an ArrayList containing FinancialDataObjects. The method
     * will determine how many data points will be used for rendering of the chart. This constructor is designed to set
     * the first items in the ArrayList as the last in the finished graph. Depending on the lists length, it will omit
     * a certain number of values to ensure optimization of the performance and the design of the graph.
     * @param seriesName name of the series shown in the graph
     * @param xAxisName name of the graphs x axis
     * @param yAxisName name of the graphs y axis
     * @param valueList list used as data base
     * @param graphRange Integer used for determining how many data points shall be taken
     * @return LineChart showing the built graph
     */
    public static LineChart buildLineChart (String seriesName, String xAxisName, String yAxisName, ArrayList<FinancialDataObject> valueList, int graphRange){

        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisName);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisName);
        yAxis.setForceZeroInRange(false);

        LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        XYChart.Series<String , Number> dataSeries = new XYChart.Series();
        dataSeries.setName(seriesName);

        //If the given database contains too many values, some will be omitted
        if (graphRange == 0 || graphRange >= valueList.size() || graphRange>1300) {
            for (int i = valueList.size() - 1; i >= 0; i -= 90) {
                dataSeries.getData().add(new XYChart.Data(
                        valueList.get(i).getDate(),
                        valueList.get(i).getValue()));


            }
            dataSeries.getData().add(new XYChart.Data<>(
                    valueList.get(0).getDate(),
                    valueList.get(0).getValue()));


        }
        else if (graphRange>253 && graphRange<1270) {
            for (int i = graphRange-1 ; i >= 0; i -= 20) {
                dataSeries.getData().add(new XYChart.Data(
                        valueList.get(i).getDate(),
                        valueList.get(i).getValue()));
            }
            dataSeries.getData().add(new XYChart.Data<>(
                    valueList.get(0).getDate(),
                    valueList.get(0).getValue()));

        }
        else if (graphRange<260 && graphRange>70){
            for (int i = graphRange-1 ; i >= 0; i -= 5) {
                dataSeries.getData().add(new XYChart.Data(
                        valueList.get(i).getDate(),
                        valueList.get(i).getValue()));
            }
            dataSeries.getData().add(new XYChart.Data<>(
                    valueList.get(0).getDate(),
                    valueList.get(0).getValue()));
        }
        else {
            for (int i = graphRange - 1; i >= 0; i--) {

                dataSeries.getData().add(new XYChart.Data(
                        valueList.get(i).getDate(),
                        valueList.get(i).getValue()));

            }
        }


        lineChart.getData().add(dataSeries);

        //this part adds tooltips to every data point in the dataseries containing the y and x value of the datapoint
                for (XYChart.Data<String, Number> entry : dataSeries.getData()) {
                    Tooltip tooltip = new Tooltip("Price: " +entry.getYValue().toString()+" $"+"\n"+"Date: "+entry.getXValue());
                    Main.fastenTooltipStartTiming(tooltip);
                    Tooltip.install(entry.getNode(), tooltip);
                    URL url = GraphBuilder.class.getResource("/GUIElements/chartStyle");
                    entry.getNode().setOnMouseEntered(event->entry.getNode().getStyleClass().add(url.toString()));


                }


        URL url = GraphBuilder.class.getResource("/GUIElements/chartStyle");
        lineChart.getStylesheets().add(url.toString());
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);

        return lineChart;
    }

    public static AreaChart buildAreaChart(String xAxisName, String yAxisName, ArrayList<ArrayList<FinancialDataObject>> dataBase){
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisName);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisName);
        yAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(true);

      AreaChart<String,Number> areaChart = new AreaChart<>(xAxis,yAxis);

        for (ArrayList<FinancialDataObject> dataList : dataBase){
            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(dataList.get(0).getName());
            for (int i = dataList.size()-1; i>=0;i--) {
                dataSeries.getData().add(new XYChart.Data(dataList.get(i).getDate(), dataList.get(i).getValue()));

        }
            areaChart.getData().add(dataSeries);
            }
        areaChart.setHorizontalGridLinesVisible(false);
        areaChart.setVerticalGridLinesVisible(false);




        return areaChart;
    }




}
