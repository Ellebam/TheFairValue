package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Data.FinancialDataObject;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;


public class FundamentalsVBox extends VBox {
    FundamentalsVBox fundamentalsVBox;
    ScrollPane scrollPane;

    public FundamentalsVBox (DataContainerManager dataContainerManager){
        fundamentalsVBox = this;

        scrollPane = new ScrollPane();




        ArrayList<ArrayList<FinancialDataObject>> list = new ArrayList<>();
        ArrayList<FinancialDataObject> copy1 = dataContainerManager.getCompanyFundamentalData().getTotalRevenue();
        Collections.reverse(copy1);
        list.add(copy1);
        list.add(dataContainerManager.getCompanyFundamentalData().getNetIncome());
        list.add(dataContainerManager.getCompanyFundamentalData().getOperatingIncome());
        AreaChart chart = GraphBuilder.buildStackedAreaChart("Date","Dollerz",list);

        VBox fundamentalsContentVBox = new VBox();
        fundamentalsContentVBox.getChildren().add(chart);


        scrollPane.setContent(fundamentalsContentVBox);


        fundamentalsVBox.getChildren().add(scrollPane);
        fundamentalsVBox.setPadding(new Insets(15));

    }
}
