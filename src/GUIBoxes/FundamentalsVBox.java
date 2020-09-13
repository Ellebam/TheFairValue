package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.GraphBuilder;
import Controllers.TableViewBuilder;
import Data.FinancialDataObject;
import Labels.AreaLabel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.Main;
import java.util.ArrayList;



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
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getOperatingIncome());
        incomeValuesList.add(dataContainerManager.getCompanyFundamentalData().getNetIncome());
        AreaChart IncomesChart = GraphBuilder.buildAreaChart("","$",incomeValuesList);
        IncomesChart.prefWidthProperty().bind(fundamentalsContentVBox.widthProperty());
        HBox incomeTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(incomeValuesList);
        AreaLabel incomesLabel = new AreaLabel("Revenue & Income");




        ArrayList<ArrayList<FinancialDataObject>> marginsValueList = new ArrayList<>();
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getGrossMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getOperatingMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getNetMargin());
        AreaChart marginsChart = GraphBuilder.buildAreaChart("","%",marginsValueList);
        marginsChart.prefWidthProperty().bind(IncomesChart.widthProperty());
        HBox marginsTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(marginsValueList);
        AreaLabel marginsLabel = new AreaLabel("Profit Margins");



        ArrayList<ArrayList<FinancialDataObject>> FCFAndPayoutList = new ArrayList<>();
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getFreeCashFlow());
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getReversedSignDividendPayout());
        AreaChart FCFAndPayoutChart = GraphBuilder.buildAreaChart("","$",FCFAndPayoutList);
        HBox FCFAndPayoutTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(FCFAndPayoutList);
        AreaLabel FCFLabel = new AreaLabel("Free Cash Flow");


        ArrayList<ArrayList<FinancialDataObject>> payoutRatioList = new ArrayList<>();
        payoutRatioList.add(dataContainerManager.getCompanyFundamentalData().getPayOutRatio());
        AreaChart payoutRatioAreaChart = GraphBuilder.buildAreaChart("", "%",payoutRatioList);
        HBox payoutRatioTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(payoutRatioList);
        AreaLabel payoutRatioLabel = new AreaLabel("Payout Ratio");





        fundamentalsContentVBox.getChildren().add(incomesLabel);
        fundamentalsContentVBox.getChildren().add(IncomesChart);
        fundamentalsContentVBox.getChildren().add(incomeTableViewBox);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(FCFLabel);
        fundamentalsContentVBox.getChildren().add(FCFAndPayoutChart);
        fundamentalsContentVBox.getChildren().add(FCFAndPayoutTableViewBox);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(marginsLabel);
        fundamentalsContentVBox.getChildren().add(marginsChart);
        fundamentalsContentVBox.getChildren().add(marginsTableViewBox);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(payoutRatioLabel);
        fundamentalsContentVBox.getChildren().add(payoutRatioAreaChart);
        fundamentalsContentVBox.getChildren().add(payoutRatioTableViewBox);
        fundamentalsContentVBox.setAlignment(Pos.CENTER);




        scrollPane.setContent(fundamentalsContentVBox);
        fundamentalsContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        fundamentalsContentVBox.setPadding(new Insets(15));
        fundamentalsContentVBox.setSpacing(10);



        fundamentalsVBox.getChildren().add(scrollPane);
        fundamentalsVBox.setPadding(new Insets(1));

    }
}
