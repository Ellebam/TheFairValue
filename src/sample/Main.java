package sample;

import GUIBoxes.AnalysisTabPane;
import GUIBoxes.AnalysisVBox;

import Controllers.*;
import GUIBoxes.OpeningVBox;
import javafx.application.Application;

import javafx.stage.Stage;




public class Main extends Application {
    SceneController sceneController = new SceneController();





   @Override
    public void start(Stage primaryStage) throws Exception{
       primaryStage.setTitle("The Fair Value");
       primaryStage.setHeight(600);
       primaryStage.setWidth(800);
       sceneController.setStage(primaryStage);
       sceneController.setSceneContent(new OpeningVBox());

       primaryStage.show();
       primaryStage.centerOnScreen();
    }










    public static void main(String[] args) throws Exception {
        launch(args);

    }







    }

