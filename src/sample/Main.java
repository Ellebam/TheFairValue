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
           ClientManager ClientManager = new ClientManager("NKE","diggi");
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);

            System.out.println(DataExtractor.calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().getFreeCashFlow(),0));
            System.out.println(DataExtractor.calculateTenYearDiscountedDCFSum(dataContainerManager));





        }catch (Exception ex){ex.printStackTrace();}



    }





    }

