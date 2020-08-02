package sample;

import Controllers.AlphavantageAPIClient;
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
            AlphavantageAPIClient client = new AlphavantageAPIClient("INCOME_STATEMENT","MCD","oida");
            System.out.println(DataExtractor.extractIncomeStatementData("totalRevenue",client));
        }catch (Exception ex){ex.printStackTrace();}



    }





    }

