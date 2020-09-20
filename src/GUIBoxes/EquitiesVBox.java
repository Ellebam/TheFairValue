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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Main;

import java.util.ArrayList;

public class EquitiesVBox extends VBox {

    EquitiesVBox equitiesVBox;
    ScrollPane scrollPane;

    public EquitiesVBox(DataContainerManager dataContainerManager){
        equitiesVBox = this;
        scrollPane = new ScrollPane();
        VBox equitiesContentVBox = new VBox();

        ArrayList<ArrayList<FinancialDataObject>> equitiesList = new ArrayList<>();
        equitiesList.add(dataContainerManager.getCompanyFundamentalData().getTotalAssets());
        equitiesList.add(dataContainerManager.getCompanyFundamentalData().getTotalLiabilities());
        equitiesList.add(dataContainerManager.getCompanyFundamentalData().getShortTermInvestments());
        equitiesList.add(dataContainerManager.getCompanyFundamentalData().getCash());
        AreaChart equitiesChart = GraphBuilder.buildAreaChart("", "$",equitiesList);
        String assetsLabelTooltipText = "Presentation of the companies current assets and liabilities:"+"\n"+"\n"+" " +
                "- Total Assets: Sum of all ressources with economic value which are owned or controlled, with the " +
                "expectation that it will provide a future benefit. "+"\n"+"\n"+
                " - Total liabilities: Combined debts and obligations that an individual or company owes to outside parties."+"\n"+"\n"+
                " - Short-term investments: Also known as marketable securities or temporary investments, are those which " +
                "can easily be converted to cash, typically within 5 years."+"\n"+"\n"+
                " - Cash: Current cash reserves on all bank accounts of a company. ";
        AreaLabel assetsLabel = new AreaLabel("Assets & Liabilities",assetsLabelTooltipText);
        Accordion equitiesTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(equitiesList,assetsLabel.getText());

        ArrayList<ArrayList<FinancialDataObject>> debtAndInvestmentsList = new ArrayList<>();
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermDebt());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermInvestments());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getShortTermDebt());
        AreaChart debtAndInvestementsChart = GraphBuilder.buildAreaChart("","$",debtAndInvestmentsList);
        String debtLabelTooltipText = "Comparison of a companies debts to its long term investments:"+"\n"+"\n"+" " +
                " - Long term debt: Debt that matures in more than one year. "+"\n"+"\n"+
                " - Short term debt: Also called current liabilities, is a firm's financial obligations that are expected to be paid off within a year."+"\n"+"\n"+
                " - Long term investments: A long-term investment is an account on the asset side of a company's " +
                "balance sheet that represents the company's investments, including stocks, bonds, real estate, and cash." +
                " Long-term investments are assets that a company intends to hold for more than a year.";
        AreaLabel debtLabel = new AreaLabel("Debt & Investements",debtLabelTooltipText);
        Accordion debtAndInvestmentsTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(debtAndInvestmentsList, debtLabel.getText());

        ArrayList<ArrayList<FinancialDataObject>> debtRatioList = new ArrayList<>();
        debtRatioList.add(dataContainerManager.getCompanyFundamentalData().getDebtRatio());
        AreaChart debtRatioChart = GraphBuilder.buildAreaChart("","%",debtRatioList);
        String debtRatioLabelTooltipText = "Display of the companies current debt situation: "+"\n"+"\n"+" " +
                " - Debt ratio: The debt ratio is a financial ratio that measures the extent of a company’s leverage. " +
                "The debt ratio is defined as the ratio of total debt to total assets, expressed as a decimal or percentage. " +
                "It can be interpreted as the proportion of a company’s assets that are financed by debt. "+
                "A ratio greater than 100 shows that a considerable portion of debt is funded by assets. " +
                "In other words, the company has more liabilities than assets. A high ratio also indicates that a company " +
                "may be putting itself at a risk of default on its loans if interest rates were to rise suddenly. " +
                "A ratio below 100 translates to the fact that a greater portion of a company's assets is funded by equity."+"\n"+"\n";

        AreaLabel debtRatioLabel = new AreaLabel("Debt Ratio",debtRatioLabelTooltipText);
        Accordion debtRatioTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(debtRatioList, debtRatioLabel.getText());

        equitiesContentVBox.getChildren().add(assetsLabel);
        equitiesContentVBox.getChildren().add(equitiesChart);
        equitiesContentVBox.getChildren().add(equitiesTableViewAccordion);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtLabel);
        equitiesContentVBox.getChildren().add(debtAndInvestementsChart);
        equitiesContentVBox.getChildren().add(debtAndInvestmentsTableViewAccordion);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtRatioLabel);
        equitiesContentVBox.getChildren().add(debtRatioChart);
        equitiesContentVBox.getChildren().add(debtRatioTableViewAccordion);
        equitiesContentVBox.setAlignment(Pos.CENTER);

        scrollPane.setContent(equitiesContentVBox);
        equitiesContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        equitiesContentVBox.setPadding(new Insets(15));
        equitiesContentVBox.setSpacing(10);

        equitiesVBox.getChildren().add(scrollPane);
        equitiesVBox.setPadding(new Insets (1));

    }

}
