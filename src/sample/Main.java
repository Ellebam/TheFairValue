package sample;

import Boxes.OpeningVBox;
import Controllers.*;
import Data.EvaluationData;
import Data.FinancialDataObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


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

        /*try {
            KeyManager keyManager = new KeyManager();
            System.out.println(keyManager.getKey());
            ClientManager ClientManager = new ClientManager("JNJ",keyManager.getKey());
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);


            System.out.println(dataContainerManager.getCompanyOverviewData());
            System.out.println(dataContainerManager.getCompanyFundamentalData());
            System.out.println(dataContainerManager.getFairValueAnalysisData());
            System.out.println(dataContainerManager.getEvaluationData());

        }catch (Exception ex){ex.printStackTrace();}*/



    }

    public static void changeStageScene (Scene scene){

    }





    }

