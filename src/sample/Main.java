package sample;


import GUIBoxes.AnalysisVBox;

import Controllers.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Field;


public class Main extends Application {
    static SceneController sceneController = new SceneController();





   @Override
    public void start(Stage primaryStage) throws Exception{
       primaryStage.setTitle("The Fair Value");
       primaryStage.setHeight(800);
       primaryStage.setWidth(1000);

       sceneController.setStage(primaryStage);


       ClientManager clientManager = new ClientManager("MMM",new KeyManager().getKey());
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

    public static void fastenTooltipStartTiming (Tooltip tooltip){
       try {
           Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
           fieldBehavior.setAccessible(true);
           Object objBehavior = fieldBehavior.get(tooltip);

           Field activationFieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
           activationFieldTimer.setAccessible(true);
           Timeline objActivationTimer = (Timeline) activationFieldTimer.get(objBehavior);

           objActivationTimer.getKeyFrames().clear();
           objActivationTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));

           Field durationFiledTimer = objBehavior.getClass().getDeclaredField("hideTimer");
           durationFiledTimer.setAccessible(true);
           Timeline objDurationTimer = (Timeline) durationFiledTimer.get(objBehavior);

           objDurationTimer.getKeyFrames().clear();
           objDurationTimer.getKeyFrames().add(new KeyFrame((new Duration(100000))));


       }catch(Exception e){
           e.printStackTrace();
       }
    }
}

