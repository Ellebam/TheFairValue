package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Data.CompanyFundamentalData;
import Data.CompanyOverviewData;


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
           ClientManager ClientManager = new ClientManager("JNJ","diggi");
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);

            System.out.println(dataContainerManager.getCompanyFundamentalData());
            System.out.println(DataExtractor.calculateRelativeChange(dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),90));
            System.out.println(DataExtractor.calculateRelativeChange(dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),365));
            System.out.println(DataExtractor.calculateRelativeChange(dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),1400));

        }catch (Exception ex){ex.printStackTrace();}



    }





    }

