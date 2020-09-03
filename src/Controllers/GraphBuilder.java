package Controllers;

import Data.FinancialDataObject;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName(seriesName);


        if (graphRange == 0 || graphRange >= valueList.size()) {
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





        return lineChart;
    }
}
