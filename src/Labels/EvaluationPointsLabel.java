package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class EvaluationPointsLabel extends Label {
    EvaluationPointsLabel evaluationPointsLabel;

    public EvaluationPointsLabel(String labelText){
        evaluationPointsLabel = this;
        evaluationPointsLabel.setText("  "+labelText+"  ");
        evaluationPointsLabel.setFont(new Font("Verdana",16));
        evaluationPointsLabel.setAlignment(Pos.CENTER);
        evaluationPointsLabel.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        evaluationPointsLabel.setStyle("-fx-border-color: silver; -fx-border-width: 1; -fx-background-radius: 5 5 5 5;" +
                "    -fx-border-radius: 5 5 5 5;-fx-background-color: snow;");
    }

    public static EvaluationPointsLabel buildPointsLabel (Boolean pointsGranted){
        EvaluationPointsLabel pointsLabel = new EvaluationPointsLabel("");
        if (pointsGranted){
            pointsLabel.setText(" +1 ");
            pointsLabel.setStyle("-fx-background-color: limegreen; -fx-font-weight:bold; -fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        else{
            pointsLabel.setText(" +0 ");

        }
        return  pointsLabel;
    }

    public static EvaluationPointsLabel buildEndPointsLabel (String header, int maxPoints, int achievedPoints){
        EvaluationPointsLabel endPointLabel = new EvaluationPointsLabel("");
        endPointLabel.setText("  "+ header+  "  "+"\n"+"\n"+ "  "+achievedPoints+" / "+"  "+maxPoints+"  ");

        if (achievedPoints/maxPoints < 0.3){
            endPointLabel.setStyle("-fx-background-color: tomato; -fx-font-weight:bold; -fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        else if (achievedPoints/maxPoints>=0.3 && achievedPoints/maxPoints<0.5){
            endPointLabel.setStyle("-fx-background-color: gold; -fx-font-weight:bold; -fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        else {
            endPointLabel.setStyle("-fx-background-color: limegreen; -fx-font-weight:bold; -fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }


        return endPointLabel;
    }
}
