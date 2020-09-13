package GUIBoxes;

import Controllers.DataContainerManager;
import Labels.AreaLabel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PitrovskiFScoreVBox extends VBox {
    PitrovskiFScoreVBox pitrovskiFScoreVBox;

    public PitrovskiFScoreVBox (DataContainerManager dataContainerManager){
        pitrovskiFScoreVBox = this;
        AreaLabel headerLabel = new AreaLabel("Pitrovski F Score");


        pitrovskiFScoreVBox.getChildren().add(headerLabel);
    }
}
