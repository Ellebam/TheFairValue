package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 * Class used for building different labels which show certain values based on points given.
 */
public class EvaluationPointsLabel extends Label {
    EvaluationPointsLabel evaluationPointsLabel;

    /**
     * Constructor used for creation of point labels used in the PitrovskiFScoreVBox. This label is only used for
     * description purposes.
     * @param labelText String containing description of points achieved for Pitrovski F Score
     */
    public EvaluationPointsLabel(String labelText){
        evaluationPointsLabel = this;
        evaluationPointsLabel.setText("  "+labelText+"  ");
        evaluationPointsLabel.setFont(new Font("Verdana",16));
        evaluationPointsLabel.setAlignment(Pos.CENTER);
        evaluationPointsLabel.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        evaluationPointsLabel.setStyle("-fx-border-color: silver; -fx-border-width: 1; -fx-background-radius: 5 5 5 5;" +
                "    -fx-border-radius: 5 5 5 5;-fx-background-color: snow;");
    }

    /**
     * Constructor used for creation of point labels used in the PitrovskiFScoreVBox. This label is used to show wether
     * a point has been achieved in conjunction with a label created by the EvaluationPointsLabel() constructor. It takes
     * a Boolean parameter to determine whether to show "+0" or "+1".
     * @param pointsGranted boolean variable for determining if point has been granted
     * @return
     */
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

    /**
     * This constructor will build a label that shows how many points have been achieved based on the input. It will change
     * the colour (from red to yellow to green) based on the amount of points achieved.
     * @param header String containing the labels description
     * @param maxPoints int describing maximum number ov achievable points
     * @param achievedPoints int describing actual number of achieved points
     * @return EvaluationPointsLabel showing number of achieved points
     */
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
        Font font = Font.font("Verdana", FontWeight.SEMI_BOLD,18);
        endPointLabel.setFont(font);
        endPointLabel.setTextAlignment(TextAlignment.CENTER);
        endPointLabel.setWrapText(true);



        return endPointLabel;
    }
}
