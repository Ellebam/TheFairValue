package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import sample.Main;
import java.io.FileInputStream;


/**
 * Class representing the Labels used for all data set visualizations.
 */
public class AreaLabel extends Label  {
    AreaLabel areaLabel;


    /**
     * The Constructor for AreaLabel sets up the labeltext and the tool tip text through the given parameters. Tool tip
     * is bound to the attached information icon
     * @param labelText String used for setting up the labels text
     * @param tooltipText String used for setting up the tool tips text
     */
    public AreaLabel (String labelText, String tooltipText){

        areaLabel = this;
        areaLabel.setText(labelText);


        try {
            FileInputStream input = new FileInputStream("src/GUIElements/information-icon.jpeg");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);

            Tooltip tooltip = new Tooltip(tooltipText);
            tooltip.setWrapText(true);
            tooltip.setMaxWidth(500);
            Main.fastenTooltipStartTiming(tooltip);
            Tooltip.install(areaLabel, tooltip);

            areaLabel.setGraphic(imageView);
        }catch (Exception ex){ex.printStackTrace();}


        areaLabel.setFont(new Font("Verdana",25));
        areaLabel.setAlignment(Pos.BASELINE_CENTER);
    }


}
