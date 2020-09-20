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
import javafx.scene.control.Accordion;
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
        String incomesLabelTooltipText = "Data representing several incomes of a company:"+"\n"+"\n"+" " +
                "- Total revenue: Total amount of income generated by the sale of goods or services related to the company's " +
                "primary operations. Revenue, also known as gross sales, is often referred to as the \"top line\" because " +
                "it sits at the top of the income statement. "+"\n"+"\n"+
                " - Operating income: Operating profit is a company's profit after all expenses are taken out except for " +
                "the cost of debt, taxes, and certain one-off items. Operating profit helps to separate a company's profit" +
                " by showing the earnings from running the business."+"\n"+"\n"+
                " - Net income : Profit remaining after all costs incurred in the period have been subtracted from " +
                "revenue generated from sales. Net income is important because it includes all revenues and costs and is" +
                " used to calculate earnings per share. ";
        AreaLabel incomesLabel = new AreaLabel("Revenue & Income",incomesLabelTooltipText);
        Accordion incomeTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(incomeValuesList, incomesLabel.getText());




        ArrayList<ArrayList<FinancialDataObject>> marginsValueList = new ArrayList<>();
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getGrossMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getOperatingMargin());
        marginsValueList.add(dataContainerManager.getCompanyFundamentalData().getNetMargin());
        AreaChart marginsChart = GraphBuilder.buildAreaChart("","%",marginsValueList);
        marginsChart.prefWidthProperty().bind(IncomesChart.widthProperty());
        String marginsLabelTooltipText = "Comparison of different profit margins of a company: "+"\n"+"\n"+" " +
                " - Gross margin: Difference between revenue and cost of goods sold (COGS) divided by revenue. "+"\n"+"\n"+
                " - Operating margin: Profitability ratio that shows how much profit a company makes from its core operations " +
                "in relation to the total revenues it brings in. Operating margin helps investors understand how a business makes money; " +
                "if it is generating income primarily from core operations, or other means, such as investments."+"\n"+"\n"+
                " - Net margin : Equal to how much net income is generated as a percentage of revenue. Net profit margin " +
                "helps investors assess if a company's management is generating enough profit from its sales and whether " +
                "operating costs and overhead costs are being contained.";
        AreaLabel marginsLabel = new AreaLabel("Profit Margins",marginsLabelTooltipText);
        Accordion marginsTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(marginsValueList, marginsLabel.getText());



        ArrayList<ArrayList<FinancialDataObject>> FCFAndPayoutList = new ArrayList<>();
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getFreeCashFlow());
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getOperatingCashflow());
        FCFAndPayoutList.add(dataContainerManager.getCompanyFundamentalData().getReversedSignDividendPayout());
        AreaChart FCFAndPayoutChart = GraphBuilder.buildAreaChart("","$",FCFAndPayoutList);
        String FCFLabelTooltipText = "Comparison of a companies cash flows with the dividend payout: "+"\n"+"\n"+" " +
                " - Free cash flow: Cash that a company generates from its business operations after subtracting capital " +
                "expenditures. Free cash flow tells investors and creditors that there's enough cash remaining to pay back" +
                " creditors, pay dividends, and buyback shares. "+"\n"+"\n"+
                " - Operating cash flow: Operating cash flow measures cash generated by a company's business operations." +
                "Operating cash flow tells investors whether a company has enough cash flow to pay their bills."+"\n"+"\n"+
                " - Dividend payout : Total amount of dividends paid out to shareholders .";
        AreaLabel FCFLabel = new AreaLabel("Cash Flows",FCFLabelTooltipText);
        Accordion FCFAndPayoutTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(FCFAndPayoutList, FCFLabel.getText());


        ArrayList<ArrayList<FinancialDataObject>> payoutRatioList = new ArrayList<>();
        payoutRatioList.add(dataContainerManager.getCompanyFundamentalData().getPayOutRatio());
        AreaChart payoutRatioAreaChart = GraphBuilder.buildAreaChart("", "%",payoutRatioList);
        String payoutRatioLabelTooltipText = "Display of the companies current dividend payout ratio: "+"\n"+"\n"+" " +
                " - Payout ratio: The dividend payout ratio is the ratio of the total amount of dividends paid out to shareholders " +
                "relative to the net income of the company. It is the percentage of earnings paid to shareholders in dividends. " +
                "The amount that is not paid to shareholders is retained by the company to pay off debt or to reinvest in core operations." +
                " It is sometimes simply referred to as the 'payout ratio." +
                " The dividend payout ratio provides an indication of how much money a company is returning to shareholders " +
                "versus how much it is keeping on hand to reinvest in growth, pay off debt, or add to cash reserves (retained earnings). "+"\n"+"\n";
        AreaLabel payoutRatioLabel = new AreaLabel("Payout Ratio",payoutRatioLabelTooltipText);
        Accordion payoutRatioTableViewAccordion  = TableViewBuilder.buildAnalysisTableViewBox(payoutRatioList, payoutRatioLabel.getText());





        fundamentalsContentVBox.getChildren().add(incomesLabel);
        fundamentalsContentVBox.getChildren().add(IncomesChart);
        fundamentalsContentVBox.getChildren().add(incomeTableViewAccordion);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(FCFLabel);
        fundamentalsContentVBox.getChildren().add(FCFAndPayoutChart);
        fundamentalsContentVBox.getChildren().add(FCFAndPayoutTableViewAccordion);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(marginsLabel);
        fundamentalsContentVBox.getChildren().add(marginsChart);
        fundamentalsContentVBox.getChildren().add(marginsTableViewAccordion);
        fundamentalsContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        fundamentalsContentVBox.getChildren().add(payoutRatioLabel);
        fundamentalsContentVBox.getChildren().add(payoutRatioAreaChart);
        fundamentalsContentVBox.getChildren().add(payoutRatioTableViewAccordion);
        fundamentalsContentVBox.setAlignment(Pos.CENTER);




        scrollPane.setContent(fundamentalsContentVBox);
        fundamentalsContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        fundamentalsContentVBox.setPadding(new Insets(15));
        fundamentalsContentVBox.setSpacing(10);



        fundamentalsVBox.getChildren().add(scrollPane);
        fundamentalsVBox.setPadding(new Insets(1));

    }
}
