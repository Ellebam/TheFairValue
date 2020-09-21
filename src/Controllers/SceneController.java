package Controllers;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    Stage stage;
    Parent contentBox;

    public  void setStage(Stage stage){
        this.stage = stage;
    }



    public void setSceneContent(Parent contentBox){
        Scene scene = new Scene(contentBox);
        stage.setScene(scene);
        this.contentBox = contentBox;


    }

    public Stage getStage() {
        return stage;
    }

    public Parent getContentBox() {
        return contentBox;
    }
}
