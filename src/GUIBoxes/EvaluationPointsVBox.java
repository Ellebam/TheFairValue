package GUIBoxes;

import Controllers.DataContainerManager;
import Labels.AreaLabel;
import Labels.EvaluationPointsLabel;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EvaluationPointsVBox extends VBox {
    EvaluationPointsVBox evaluationPointsVBox;

    public EvaluationPointsVBox(DataContainerManager dataContainerManager){
        evaluationPointsVBox = this;
        String headerLabelTooltipText = "Evaluation of the profitability of an stock investment based on a companies business" +
                " metrics. Four areas are explored where a maximum of 25 points per area can be achieved: "+"\n"+"\n"+
                " - Mean fair value: Comparison of calculated mean fari value with the current stock price"+"\n"+"\n"+
                " - Pitrovski F Score: Valuation based on Pitrovski F Score"+"\n"+"\n"+
                " - Volatility & Performance: Highest points achievable for stocks with a high performance and low stock price volatility"+"\n"+"\n"+
                " - Dividend factors: Valuation of a companies dividend payout quality through different metrics. If no dividend is paid out to" +
                "shareholders a multiple of the stocks perfomrance will be used for valuation";
        AreaLabel headerLabel = new AreaLabel("Evaluation Points",headerLabelTooltipText);
        evaluationPointsVBox.setAlignment(Pos.CENTER);

       HBox evaluationPointsContentHBox = new HBox();

        EvaluationPointsLabel fairValue2PricePoints =  EvaluationPointsLabel.buildEndPointsLabel(" Mean Fair Value ",25,
                dataContainerManager.getEvaluationData().getFairValue2StockPricePoints());

        EvaluationPointsLabel PFScorePoints =  EvaluationPointsLabel.buildEndPointsLabel(" P F Score",25,
                dataContainerManager.getEvaluationData().getPitrovskiFScorePoints());

        EvaluationPointsLabel VolatilityAndPerformancePoints =  EvaluationPointsLabel.buildEndPointsLabel(" Volatility & Performance ",25,
                dataContainerManager.getEvaluationData().getVolatilityAndPerformancePoints());

        EvaluationPointsLabel DividendsPoints =  EvaluationPointsLabel.buildEndPointsLabel(" Dividend Factors ",25,
                dataContainerManager.getEvaluationData().getDividendFactorsPoints());


        EvaluationPointsLabel summedUpPoints =  EvaluationPointsLabel.buildEndPointsLabel(" Evaluation ",100,
                dataContainerManager.getEvaluationData().getSumOfEvaluationPoints());
        summedUpPoints.setFont(new Font("Verdana",24));

        evaluationPointsContentHBox.getChildren().add(fairValue2PricePoints);
        evaluationPointsContentHBox.getChildren().add(PFScorePoints);
        evaluationPointsContentHBox.getChildren().add(VolatilityAndPerformancePoints);
        evaluationPointsContentHBox.getChildren().add(DividendsPoints);
        evaluationPointsContentHBox.setAlignment(Pos.CENTER);
        evaluationPointsContentHBox.prefWidthProperty().bind(summedUpPoints.widthProperty());

        evaluationPointsContentHBox.setSpacing(5);
        evaluationPointsContentHBox.setMinHeight(100);


        evaluationPointsVBox.getChildren().add(headerLabel);
        evaluationPointsVBox.getChildren().add(evaluationPointsContentHBox);
        evaluationPointsVBox.getChildren().add(summedUpPoints);
        evaluationPointsVBox.setSpacing(10);

    }
}
