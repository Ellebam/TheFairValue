package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sample.Main;

import java.io.FileInputStream;

/**
 * This class is used to combine the AnalysisTabPane object used for analysis content management with a simple home button
 * to return to the OpeningVBox content-scene (Home pane of the application). This is possible through a simple button
 * added to the AnalysisVBox through the Stackpane.
 */
public class AnalysisStackpane extends StackPane {
    AnalysisStackpane analysisStackpane;
    AnalysisVBox analysisVBox;
    Button homeButton;

    public AnalysisStackpane (DataContainerManager dataContainerManager){
        analysisStackpane = this;
        analysisVBox = new AnalysisVBox(dataContainerManager);

        try{
            FileInputStream input = new FileInputStream("src/GUIElements/home-icon");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            homeButton = new Button("Home", imageView);

        }catch (Exception ex){
            ex.printStackTrace();
            homeButton = new Button("Home");
        }

        homeButton.setOnAction(value -> {
            OpeningVBox openingVBox = new OpeningVBox();
            Main.getSceneController().setSceneContent(openingVBox);
        });


        analysisStackpane.getChildren().add(analysisVBox);
        analysisStackpane.getChildren().add(homeButton);
        analysisStackpane.setAlignment(homeButton, Pos.TOP_RIGHT);
    }
}
