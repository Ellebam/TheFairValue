package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Controllers.GraphBuilder;
import Controllers.TableViewBuilder;
import Data.FinancialDataObject;
import Labels.AreaLabel;
import Labels.EvaluationPointsLabel;
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

public class EvaluationVBox extends VBox {
    EvaluationVBox evaluationVBox;
    ScrollPane scrollPane;

    public EvaluationVBox(DataContainerManager dataContainerManager){
        evaluationVBox = this;
        scrollPane = new ScrollPane();
        VBox evaluationContentVBox = new VBox();




        ArrayList<ArrayList<FinancialDataObject>> fairValueList = new ArrayList<>();
        fairValueList.add(DataExtractor.pseudoQUarterlyStockprice(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),
                dataContainerManager.getFairValueAnalysisData().getHistoricalDCFFairValuePerShare()));
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getHistoricalDCFFairValuePerShare());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getPeterLynchFairValue());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getGrahamNumber());
        fairValueList.add(dataContainerManager.getFairValueAnalysisData().getMeanFairValue());
        AreaChart fairValueChart = GraphBuilder.buildAreaChart("","Stock Price $",fairValueList);
        fairValueChart.prefWidthProperty().bind(evaluationContentVBox.widthProperty());
        String fairValueLabelTooltipText = "Comparison of a companies fair value calculated by different formulas:"+"\n"+"\n"+" " +
                " - Stock price: Price of ashare issue. "+"\n"+"\n"+
                " - DCF Fair value: Fair value calculated by the discounted cash flow valuation model."+"\n"+"\n"+
                " - Peter Lynch fair value: Fair value calculated by Peter Lynchs P/E valuation model." +"\n"+"\n"+
                " - Graham number: Fair value calculated with Graham valuation approach."+"\n"+"\n"+
                " - Mean fair value: Mean value of calculated fair values for comparison to current stock price.";
        AreaLabel fairValueLabel = new AreaLabel("Fair Value",fairValueLabelTooltipText);
        Accordion fairValueTableViewAccordion = TableViewBuilder.buildAnalysisTableViewBox(fairValueList,fairValueLabel.getText());


        //Pitrovski F Score Calculation is triggered at this point
        HBox pitrovskiEvaluationHBox = new HBox();
        pitrovskiEvaluationHBox.setSpacing(20);
        PitrovskiFScoreVBox pitrovskiFScoreVBox =new PitrovskiFScoreVBox(dataContainerManager);
        pitrovskiEvaluationHBox.getChildren().add(pitrovskiFScoreVBox);
        EvaluationPointsLabel PitrovskiPointsLabel = EvaluationPointsLabel.buildEndPointsLabel(
                "Endscore",9,dataContainerManager.getPitrovskiFScoreData().getPitrovskiFScore());
        PitrovskiPointsLabel.setMaxHeight(150);
        PitrovskiPointsLabel.setPrefWidth(200);

        PitrovskiPointsLabel.setAlignment(Pos.CENTER);
        pitrovskiEvaluationHBox.getChildren().add(PitrovskiPointsLabel);
        pitrovskiEvaluationHBox.setAlignment(Pos.CENTER);

        EvaluationPointsVBox evaluationPointsVBox = new EvaluationPointsVBox(dataContainerManager);




        evaluationContentVBox.getChildren().add(fairValueLabel);
        evaluationContentVBox.getChildren().add(fairValueChart);
        evaluationContentVBox.getChildren().add(fairValueTableViewAccordion);
        evaluationContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        evaluationContentVBox.getChildren().add(pitrovskiEvaluationHBox);
        evaluationContentVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        evaluationContentVBox.getChildren().add(evaluationPointsVBox);
        evaluationContentVBox.setAlignment(Pos.CENTER);

        scrollPane.setContent(evaluationContentVBox);
        evaluationContentVBox.prefWidthProperty().bind(Main.getSceneController().getStage().widthProperty());
        evaluationContentVBox.setPadding(new Insets(15));
        evaluationContentVBox.setSpacing(10);

        evaluationVBox.getChildren().add(scrollPane);
        evaluationVBox.setPadding(new Insets(1));
    }
}
