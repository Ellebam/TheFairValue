package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class AreaLabel extends Label  {
    AreaLabel areaLabel;

    public AreaLabel (String labelText){

        areaLabel = this;
        areaLabel.setText(labelText);
        areaLabel.setFont(new Font("Verdana",25));
        areaLabel.setAlignment(Pos.BASELINE_CENTER);
    }


}
