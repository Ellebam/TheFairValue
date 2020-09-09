package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Controllers.SceneController;
import Data.FinancialDataObject;
import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import sample.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class FundamentalsVBox extends VBox {
    FundamentalsVBox fundamentalsVBox;
    ScrollPane scrollPane;

    public FundamentalsVBox (DataContainerManager dataContainerManager){
        fundamentalsVBox = this;

        scrollPane = new ScrollPane();
        VBox fundamentalsContentVBox = new VBox();



        // Creation of Areachart of several income values.
        ArrayList<ArrayList<FinancialDataObject>> incomeValuesList = new ArrayList<>();

        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getTotalRevenue());
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getNetIncome());
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getOperatingIncome());
        AreaChart IncomesChart = GraphBuilder.buildAreaChart("Date","Dollars",incomeValuesList);
        IncomesChart.prefWidthProperty().bind(fundamentalsContentVBox.widthProperty());



        ArrayList<ArrayList<FinancialDataObject>> marginsValueList = new ArrayList<>();
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getNetMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getGrossMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getOperatingMargin());
        AreaChart marginsChart = GraphBuilder.buildAreaChart("Date","Percent",marginsValueList);
        marginsChart.prefWidthProperty().bind(IncomesChart.widthProperty());



        ArrayList<ArrayList<FinancialDataObject>> FCFAndPayoutList = new ArrayList<>();

        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getFreeCashFlow());
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getReversedSignDividendPayout());
        AreaChart FCFAndPayoutChart = GraphBuilder.buildAreaChart("Date","Dollars",FCFAndPayoutList);


        ArrayList<ArrayList<FinancialDataObject>> payoutRatioList = new ArrayList<>();
        payoutRatioList.add(dataContainerManager.getCompanyFundamentalData().getPayOutRatio());
        AreaChart payoutRatioAreaChart = GraphBuilder.buildAreaChart("Date", "Percent",payoutRatioList);





        fundamentalsContentVBox.getChildren().add(IncomesChart);
        fundamentalsContentVBox.getChildren().add(FCFAndPayoutChart);
        fundamentalsContentVBox.getChildren().add(marginsChart);
        fundamentalsContentVBox.getChildren().add(payoutRatioAreaChart);



        scrollPane.setContent(fundamentalsContentVBox);
        fundamentalsContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());



        fundamentalsVBox.getChildren().add(scrollPane);
        fundamentalsVBox.setPadding(new Insets(15));

    }
}
