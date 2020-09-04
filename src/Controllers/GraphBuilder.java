package Controllers;

import Data.FinancialDataObject;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import javax.tools.Tool;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    public static LineChart buildLineChart (String seriesName, String xAxisName, String yAxisName, ArrayList<FinancialDataObject> valueList, int graphRange){

        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xAxisName);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisName);
        yAxis.setForceZeroInRange(false);

        LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        XYChart.Series<String , Number> dataSeries = new XYChart.Series();
        dataSeries.setName(seriesName);


        if (graphRange == 0 || graphRange >= valueList.size() || graphRange>1300) {
            for (int i = valueList.size() - 1; i >= 0; i -= 20) {
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
        for (XYChart.Data <String, Number> entry : dataSeries.getData()){
            Tooltip tooltip = new Tooltip(entry.getYValue().toString());
            Tooltip.install(entry.getNode(),tooltip);

        }

        /*URL url = GraphBuilder.class.getResource("/GUIElements/chartStyle");
        lineChart.getStylesheets().add(url.toString());
        //lineChart.setCreateSymbols(false);*/






        return lineChart;
    }
}
