package GUIBoxes;

import Controllers.DataContainerManager;
import Labels.AreaLabel;
import Labels.EvaluationPointsLabel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EvaluationPointsVBox extends VBox {
    EvaluationPointsVBox evaluationPointsVBox;

    public EvaluationPointsVBox(DataContainerManager dataContainerManager){
        evaluationPointsVBox = this;
        AreaLabel headerLabel = new AreaLabel("Evaluation Points");

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

        evaluationPointsContentHBox.getChildren().add(fairValue2PricePoints);
        evaluationPointsContentHBox.getChildren().add(PFScorePoints);
        evaluationPointsContentHBox.getChildren().add(VolatilityAndPerformancePoints);
        evaluationPointsContentHBox.getChildren().add(DividendsPoints);

        evaluationPointsContentHBox.setSpacing(5);
        evaluationPointsContentHBox.setMinHeight(100);


        evaluationPointsVBox.getChildren().add(headerLabel);
        evaluationPointsVBox.getChildren().add(evaluationPointsContentHBox);
        evaluationPointsVBox.getChildren().add(summedUpPoints);
        evaluationPointsVBox.setSpacing(10);

    }
}
