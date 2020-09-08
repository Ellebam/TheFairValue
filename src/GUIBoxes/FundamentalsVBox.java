package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Controllers.SceneController;
import Data.FinancialDataObject;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import sample.Main;

import java.util.ArrayList;
import java.util.Collections;


public class FundamentalsVBox extends VBox {
    FundamentalsVBox fundamentalsVBox;
    ScrollPane scrollPane;

    public FundamentalsVBox (DataContainerManager dataContainerManager){
        fundamentalsVBox = this;

        scrollPane = new ScrollPane();
        VBox fundamentalsContentVBox = new VBox();



        /* Creation of Areachart of several income values. The first List is reversed to ensure correct Date placement on the x-axis
        * This part creates the Graph showing different Incomes*/
        ArrayList<ArrayList<FinancialDataObject>> incomeValuesList = new ArrayList<>();
        ArrayList<FinancialDataObject> reversedDateTotalRevenue = dataContainerManager.getCompanyFundamentalData().getTotalRevenue();
        Collections.reverse(reversedDateTotalRevenue);
        incomeValuesList.add(reversedDateTotalRevenue);
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getNetIncome());
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getOperatingIncome());
        AreaChart IncomesChart = GraphBuilder.buildAreaChart("Date","Dollars",incomeValuesList);
        IncomesChart.prefWidthProperty().bind(fundamentalsContentVBox.widthProperty());



        ArrayList<ArrayList<FinancialDataObject>> marginsValueList = new ArrayList<>();
        ArrayList<FinancialDataObject> reversedDateNetMargin = dataContainerManager.getCompanyFundamentalData().getNetMargin();
        Collections.reverse(reversedDateNetMargin);
        marginsValueList.add(reversedDateNetMargin);
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getGrossMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getOperatingMargin());
        AreaChart marginsChart = GraphBuilder.buildAreaChart("Date","Percent",marginsValueList);
        marginsChart.prefWidthProperty().bind(IncomesChart.widthProperty());


        fundamentalsContentVBox.getChildren().add(IncomesChart);
        fundamentalsContentVBox.getChildren().add(marginsChart);


        scrollPane.setContent(fundamentalsContentVBox);
        fundamentalsContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());



        fundamentalsVBox.getChildren().add(scrollPane);
        fundamentalsVBox.setPadding(new Insets(15));

    }
}
