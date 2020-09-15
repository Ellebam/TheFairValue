package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

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
        endPointLabel.setText("  "+ header+  "  "+"\n"+ "  "+achievedPoints+" / "+maxPoints+"  "+"  "+"Points  "+"\n");
        double MaxPoints = Double.valueOf(maxPoints);
        double AchievedPoints = Double.valueOf(achievedPoints);

        if (AchievedPoints/MaxPoints < 0.3){
            endPointLabel.setStyle("-fx-background-color: tomato; -fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        else if (AchievedPoints/MaxPoints>=0.3 && AchievedPoints/MaxPoints<=0.6){
            endPointLabel.setStyle("-fx-background-color: gold;-fx-text-fill: black; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        else {
            endPointLabel.setStyle("-fx-background-color: limegreen;-fx-text-fill:whitesmoke; " +
                    "-fx-border-radius: 5 5 5 5;-fx-background-radius: 5 5 5 5;");
        }
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD,18);
        endPointLabel.setFont(font);
        endPointLabel.setTextAlignment(TextAlignment.CENTER);



        return endPointLabel;
    }
}
