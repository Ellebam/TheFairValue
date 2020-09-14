package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Labels.AreaLabel;
import Labels.PitrovskiFScoreGridLabel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PitrovskiFScoreVBox extends VBox {
    PitrovskiFScoreVBox pitrovskiFScoreVBox;

    public PitrovskiFScoreVBox (DataContainerManager dataContainerManager){
        pitrovskiFScoreVBox = this;
        AreaLabel headerLabel = new AreaLabel("Pitrovski F Score");




        GridPane gridpane = new GridPane();

        PitrovskiFScoreGridLabel C0R0 = new PitrovskiFScoreGridLabel("Return on assets");
        PitrovskiFScoreGridLabel C0R1 = new PitrovskiFScoreGridLabel("Cash FLow return on assets");
        PitrovskiFScoreGridLabel C0R2 = new PitrovskiFScoreGridLabel("Change in return on assets");
        PitrovskiFScoreGridLabel C0R3 = new PitrovskiFScoreGridLabel("Quality of earnings");
        PitrovskiFScoreGridLabel C0R4 = new PitrovskiFScoreGridLabel("Change in leverage");
        PitrovskiFScoreGridLabel C0R5 = new PitrovskiFScoreGridLabel("Change in liquidity");
        PitrovskiFScoreGridLabel C0R6 = new PitrovskiFScoreGridLabel("Change in shares issue");
        PitrovskiFScoreGridLabel C0R7 = new PitrovskiFScoreGridLabel("Change in gross margin");
        PitrovskiFScoreGridLabel C0R8 = new PitrovskiFScoreGridLabel("Change in asset turnover");



        PitrovskiFScoreGridLabel C1R0 = new PitrovskiFScoreGridLabel("");
        if (dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear()>0){
            C1R0.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear())+" , ROA positive  " );
        }
        else{
            C1R0.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear())+" , ROA negative  ");
        }


        PitrovskiFScoreGridLabel C1R1 = new PitrovskiFScoreGridLabel("");
        if (dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear()>0){
            C1R1.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getCashFlowReturnOnAssets())+" , CFROA positive  " );
        }
        else{
            C1R1.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getCashFlowReturnOnAssets())+" , CFROA negative  ");
        }

        PitrovskiFScoreGridLabel C1R2 = new PitrovskiFScoreGridLabel("Change in return on assets");
        PitrovskiFScoreGridLabel C1R3 = new PitrovskiFScoreGridLabel("Quality of earnings");
        PitrovskiFScoreGridLabel C1R4 = new PitrovskiFScoreGridLabel("Change in leverage");
        PitrovskiFScoreGridLabel C1R5 = new PitrovskiFScoreGridLabel("Change in liquidity");
        PitrovskiFScoreGridLabel C1R6 = new PitrovskiFScoreGridLabel("Change in shares issue");
        PitrovskiFScoreGridLabel C1R7 = new PitrovskiFScoreGridLabel("Change in gross margin");
        PitrovskiFScoreGridLabel C1R8 = new PitrovskiFScoreGridLabel("Change in asset turnover");


        gridpane.add(C0R0,0,0);
        gridpane.add(C0R1,0,1);
        gridpane.add(C0R2,0,2);
        gridpane.add(C0R3,0,3);
        gridpane.add(C0R4,0,4);
        gridpane.add(C0R5,0,5);
        gridpane.add(C0R6,0,6);
        gridpane.add(C0R7,0,7);
        gridpane.add(C0R8,0,8);

        gridpane.add(C1R0,1,0);
        gridpane.add(C1R1,1,1);
        gridpane.add(C1R2,1,2);
        gridpane.add(C1R3,1,3);
        gridpane.add(C1R4,1,4);
        gridpane.add(C1R5,1,5);
        gridpane.add(C1R6,1,6);
        gridpane.add(C1R7,1,7);
        gridpane.add(C1R8,1,8);


        gridpane.setHgap(5);
        gridpane.setVgap(15);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().add(columnConstraints);


        pitrovskiFScoreVBox.getChildren().add(headerLabel);
        pitrovskiFScoreVBox.getChildren().add(gridpane);
        pitrovskiFScoreVBox.setAlignment(Pos.CENTER);
    }
}
