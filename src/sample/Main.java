package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;


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
           ClientManager ClientManager = new ClientManager("JNJ","7382NJ201MJ23");
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);

            System.out.println(dataContainerManager.getCompanyOverviewData().getName());
            System.out.println(dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue());
            for (int i =0; i<dataContainerManager.getCompanyFundamentalData().getFreeCashFlow().size();i++) {
                System.out.println(DataExtractor.calculateDCFFairValuePerShare(dataContainerManager,i));
            }





        }catch (Exception ex){ex.printStackTrace();}



    }





    }

