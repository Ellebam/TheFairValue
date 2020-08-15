package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Data.FairValueAnalysisData;


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
           ClientManager ClientManager = new ClientManager("AAPL","bumsi");
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);

            System.out.println(dataContainerManager.getCompanyOverviewData().getName());
            System.out.println(dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue());


            System.out.println(dataContainerManager.getFairValueAnalysisData());





        }catch (Exception ex){ex.printStackTrace();}



    }





    }

