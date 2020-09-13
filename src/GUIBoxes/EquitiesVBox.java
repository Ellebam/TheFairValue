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
        HBox equitiesTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(equitiesList);
        AreaLabel assetsLabel = new AreaLabel("Assets & Liabilities");

        ArrayList<ArrayList<FinancialDataObject>> debtAndInvestmentsList = new ArrayList<>();
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermDebt());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getLongTermInvestments());
        debtAndInvestmentsList.add(dataContainerManager.getCompanyFundamentalData().getShortTermDebt());
        AreaChart debtAndInvestementsChart = GraphBuilder.buildAreaChart("","$",debtAndInvestmentsList);
        HBox debtAndInvestmentsTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(debtAndInvestmentsList);
        AreaLabel debtLabel = new AreaLabel("Debt & Investements");

        ArrayList<ArrayList<FinancialDataObject>> debtRatiosList = new ArrayList<>();
        debtRatiosList.add(dataContainerManager.getCompanyFundamentalData().getDebtRatio());
        debtRatiosList.add(dataContainerManager.getCompanyFundamentalData().getLongDebtToInvestmentsRatio());
        AreaChart debtRatiosChart = GraphBuilder.buildAreaChart("","%",debtRatiosList);
        HBox debtRatiosTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(debtRatiosList);
        AreaLabel debtRatiosLabel = new AreaLabel("Debt Ratios");

        equitiesContentVBox.getChildren().add(assetsLabel);
        equitiesContentVBox.getChildren().add(equitiesChart);
        equitiesContentVBox.getChildren().add(equitiesTableViewBox);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtLabel);
        equitiesContentVBox.getChildren().add(debtAndInvestementsChart);
        equitiesContentVBox.getChildren().add(debtAndInvestmentsTableViewBox);
        equitiesContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        equitiesContentVBox.getChildren().add(debtRatiosLabel);
        equitiesContentVBox.getChildren().add(debtRatiosChart);
        equitiesContentVBox.getChildren().add(debtRatiosTableViewBox);
        equitiesContentVBox.setAlignment(Pos.CENTER);

        scrollPane.setContent(equitiesContentVBox);
        equitiesContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        equitiesContentVBox.setPadding(new Insets(15));
        equitiesContentVBox.setSpacing(10);

        equitiesVBox.getChildren().add(scrollPane);
        equitiesVBox.setPadding(new Insets (1));

    }

}
