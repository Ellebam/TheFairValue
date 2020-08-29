package Controllers;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
    Stage stage;

    public  void setStage(Stage stage){
        this.stage = stage;
    }

    public void setSceneContent(VBox vBox){
        Scene scene = new Scene(vBox);
        stage.setScene(scene);


    }
}
