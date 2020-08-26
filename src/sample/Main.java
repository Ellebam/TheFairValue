package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import Data.EvaluationData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


public class Main extends Application {

   @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Fair Value");
        /*primaryStage.setScene(new Scene(root, 300, 275));*/

       primaryStage.setX(100);
       primaryStage.setY(100);
       primaryStage.setWidth(600);
       primaryStage.setHeight(600);
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(new URL("file:/Users/Kara/IdeaProjects/TheFairValue/src/TestLayout.fxml"));
       VBox vbox = loader.<VBox>load();
       Scene scene = new Scene(vbox);

       primaryStage.setScene(scene);


       primaryStage.show();

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





    }

