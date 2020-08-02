package sample;

import Controllers.APIManager;
import Controllers.AlphavantageAPIClient;
import Controllers.DataExtractor;
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
           APIManager APIManager = new APIManager("MCD","ey");
            CompanyOverviewData overviewData = new CompanyOverviewData(APIManager);
            System.out.println(overviewData);
            System.out.println(overviewData.getHistoricalStockPrice());
        }catch (Exception ex){ex.printStackTrace();}



    }





    }

