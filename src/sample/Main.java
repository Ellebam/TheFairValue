package sample;

import Controllers.AlphavantageAPIClient;
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
            CompanyOverviewData data = new CompanyOverviewData("MCD", "nahDude");
        }catch (Exception ex){ex.printStackTrace();}



    }





    }

