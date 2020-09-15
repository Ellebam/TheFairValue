package sample;


import GUIBoxes.AnalysisVBox;

import Controllers.*;

import javafx.application.Application;

import javafx.stage.Stage;




public class Main extends Application {
    static SceneController sceneController = new SceneController();





   @Override
    public void start(Stage primaryStage) throws Exception{
       primaryStage.setTitle("The Fair Value");
       primaryStage.setHeight(800);
       primaryStage.setWidth(1000);

       sceneController.setStage(primaryStage);


       ClientManager clientManager = new ClientManager("AAPL",new KeyManager().getKey());
       DataContainerManager dataContainerManager = new DataContainerManager(clientManager);
       System.out.println(dataContainerManager.getFairValueAnalysisData());



       sceneController.setSceneContent(new AnalysisVBox(dataContainerManager));
       primaryStage.show();
       primaryStage.centerOnScreen();
    }



    public static void main(String[] args) throws Exception {
        launch(args);

    }

    public static SceneController getSceneController() {
        return sceneController;
    }
}

