package sample;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;
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
           ClientManager ClientManager = new ClientManager("MSFT","capisamrah");
            DataContainerManager dataContainerManager = new DataContainerManager(ClientManager);
            PitrovskiFScoreData pitrovskiFScoreData = new PitrovskiFScoreData(dataContainerManager);


            System.out.println(dataContainerManager.getCompanyFundamentalData());
            System.out.println(dataContainerManager.getFairValueAnalysisData());
            System.out.println(pitrovskiFScoreData);






        }catch (Exception ex){ex.printStackTrace();}



    }





    }

