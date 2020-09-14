package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PitrovskiFScoreGridLabel extends Label {
    PitrovskiFScoreGridLabel pitrovskiFScoreGridLabel;

    public PitrovskiFScoreGridLabel(String labelText){
        pitrovskiFScoreGridLabel = this;
        pitrovskiFScoreGridLabel.setText("  "+labelText+"  ");
        pitrovskiFScoreGridLabel.setFont(new Font("Verdana",16));
        pitrovskiFScoreGridLabel.setAlignment(Pos.CENTER);
        pitrovskiFScoreGridLabel.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        pitrovskiFScoreGridLabel.setStyle("-fx-border-color: lightslategray; -fx-border-width: 1; -fx-background-radius: 5 5 5 5;" +
                "    -fx-border-radius: 5 5 5 5;-fx-background-color: gainsboro;");
    }
}
