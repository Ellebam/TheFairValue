package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.KeyManager;
import Data.EvaluationData;


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
            ClientManager ClientManager = new ClientManager("JNJ",keyManager.getKey());
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
            EvaluationData evaluationData = new EvaluationData(dataContainerManager);

            System.out.println(dataContainerManager.getCompanyOverviewData());
            System.out.println(dataContainerManager.getCompanyFundamentalData());
            System.out.println(dataContainerManager.getFairValueAnalysisData());
            System.out.println(evaluationData.getFairValue2StockPricePoints());







        }catch (Exception ex){ex.printStackTrace();}



    }





    }

