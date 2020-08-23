package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Controllers.KeyManager;
import Data.EvaluationData;
import Data.FairValueAnalysisData;
import Data.PitrovskiFScoreData;


public class Main /*extends Application */{

  /*  @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/


    public static void main(String[] args) /*throws Exception */{
       // launch(args);

        try {
            KeyManager keyManager = new KeyManager();
            System.out.println(keyManager.getKey());
            ClientManager ClientManager = new ClientManager("FB",keyManager.getKey());
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
            EvaluationData evaluationData = new EvaluationData(dataContainerManager);


            System.out.println(evaluationData);







        }catch (Exception ex){ex.printStackTrace();}



    }





    }

