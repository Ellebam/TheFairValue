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
        AreaLabel assetsLabel = new AreaLabel("Assets & Liabilities");
        Accordion equitiesTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(equitiesList,assetsLabel.getText());

        ArrayList<ArrayList<FinancialDataObject>> debtAndInvestmentsList = new ArrayList<>();
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermDebt());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermInvestments());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getShortTermDebt());
        AreaChart debtAndInvestementsChart = GraphBuilder.buildAreaChart("","$",debtAndInvestmentsList);
        AreaLabel debtLabel = new AreaLabel("Debt & Investements");
        Accordion debtAndInvestmentsTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(debtAndInvestmentsList, debtLabel.getText());

        ArrayList<ArrayList<FinancialDataObject>> debtRatiosList = new ArrayList<>();
        debtRatiosList.add(dataContainerManager.getCompanyFundamentalData().getDebtRatio());
        debtRatiosList.add(dataContainerManager.getCompanyFundamentalData().getLongDebtToInvestmentsRatio());
        AreaChart debtRatiosChart = GraphBuilder.buildAreaChart("","%",debtRatiosList);
        AreaLabel debtRatiosLabel = new AreaLabel("Debt Ratios");
        Accordion debtRatiosTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(debtRatiosList, debtRatiosLabel.getText());

        equitiesContentVBox.getChildren().add(assetsLabel);
        equitiesContentVBox.getChildren().add(equitiesChart);
        equitiesContentVBox.getChildren().add(equitiesTableViewAccordion);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtLabel);
        equitiesContentVBox.getChildren().add(debtAndInvestementsChart);
        equitiesContentVBox.getChildren().add(debtAndInvestmentsTableViewAccordion);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtRatiosLabel);
        equitiesContentVBox.getChildren().add(debtRatiosChart);
        equitiesContentVBox.getChildren().add(debtRatiosTableViewAccordion);
        equitiesContentVBox.setAlignment(Pos.CENTER);

        scrollPane.setContent(equitiesContentVBox);
        equitiesContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        equitiesContentVBox.setPadding(new Insets(15));
        equitiesContentVBox.setSpacing(10);

        equitiesVBox.getChildren().add(scrollPane);
        equitiesVBox.setPadding(new Insets (1));

    }

}
