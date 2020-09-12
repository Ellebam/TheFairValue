package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Controllers.GraphBuilder;
import Controllers.TableViewBuilder;
import Data.FinancialDataObject;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Main;

import java.util.ArrayList;

public class EvaluationVBox extends VBox {
    EvaluationVBox evaluationVBox;
    ScrollPane scrollPane;

    public EvaluationVBox(DataContainerManager dataContainerManager){
        evaluationVBox = this;
        scrollPane = new ScrollPane();
        VBox fairValueContentVBox = new VBox();




        ArrayList<ArrayList<FinancialDataObject>> fairValueList = new ArrayList<>();
        fairValueList.add(DataExtractor.fiveYearPseudoQuarterlyStockPrice(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),
                dataContainerManager.getFairValueAnalysisData().getHistoricalDCFFairValuePerShare()));
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getHistoricalDCFFairValuePerShare());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getPeterLynchFairValue());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getGrahamNumber());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getMeanFairValue());
        AreaChart fairValueChart = GraphBuilder.buildAreaChart("","Stock Price $",fairValueList);
        fairValueChart.prefWidthProperty().bind(fairValueContentVBox.widthProperty());
        HBox fairValueTableViewBox = TableViewBuilder.buildAnalysisTableViewBox(fairValueList);



        fairValueContentVBox.getChildren().add(fairValueChart);
        fairValueContentVBox.getChildren().add(fairValueTableViewBox);
        fairValueContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));

        scrollPane.setContent(fairValueContentVBox);
        fairValueContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        fairValueContentVBox.setPadding(new Insets(15));
        fairValueContentVBox.setSpacing(10);

        evaluationVBox.getChildren().add(scrollPane);
        evaluationVBox.setPadding(new Insets(1));
    }
}
