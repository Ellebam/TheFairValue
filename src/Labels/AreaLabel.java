package Labels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.net.URL;

public class AreaLabel extends Label  {
    AreaLabel areaLabel;

    public AreaLabel (String labelText){

        areaLabel = this;
        areaLabel.setText(labelText);

        URL url = AreaLabel.class.getResource("/GUIElements/information-icon.png");
        try {
            FileInputStream input = new FileInputStream(url.toString());
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);

            areaLabel.setGraphic(imageView);
        }catch (Exception ex){ex.printStackTrace();}


        areaLabel.setFont(new Font("Verdana",25));
        areaLabel.setAlignment(Pos.BASELINE_CENTER);
    }


}
