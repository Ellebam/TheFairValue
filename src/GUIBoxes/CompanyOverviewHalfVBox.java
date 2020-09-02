package GUIBoxes;

import Controllers.DataContainerManager;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class CompanyOverviewHalfVBox extends VBox {
    CompanyOverviewHalfVBox companyOverviewHalfVBox;
    String companyName;
    String companyDescription;
    String companySector;

    public CompanyOverviewHalfVBox (DataContainerManager dataContainerManager){
        companyOverviewHalfVBox = this;


        companyName = dataContainerManager.getCompanyOverviewData().getName();
        Label companyNameLabel = new Label(companyName);
        companyNameLabel.setFont(new Font("Amble.CN",20));

        companySector = "Sector: "+dataContainerManager.getCompanyOverviewData().getSector();
        Label companySectorLabel = new Label(companySector);
        companySectorLabel.setFont(new Font("Amble.CN",16));




        companyDescription = dataContainerManager.getCompanyOverviewData().getDescription();
        Label companyDescriptionLabel = new Label(companyDescription);
        companyDescriptionLabel.setWrapText(true);
        companyDescriptionLabel.setMaxWidth(800);
        companyDescriptionLabel.setFont(new Font("Amble.CN",14));


        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Price [$]");

        LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        XYChart.Series stockPriceDataSeries = new XYChart.Series();
        stockPriceDataSeries.setName("Historical Stock Price");



        for(int i = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().size()-1; i>=0;i-=20){
            stockPriceDataSeries.getData().add(new XYChart.Data(
                            dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(i).getDate(),
                    dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(i).getValue()));
        }
        stockPriceDataSeries.getData().add(new XYChart.Data(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getDate(),
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue()));



        lineChart.getData().add(stockPriceDataSeries);


        companyOverviewHalfVBox.getChildren().add(companyNameLabel);
        companyOverviewHalfVBox.getChildren().add(companySectorLabel);
        companyOverviewHalfVBox.getChildren().add(companyDescriptionLabel);
        companyOverviewHalfVBox.getChildren().add(lineChart);
    }
}
