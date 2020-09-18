package GUIBoxes;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;
import Labels.AreaLabel;
import Labels.EvaluationPointsLabel;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PitrovskiFScoreVBox extends VBox {
    PitrovskiFScoreVBox pitrovskiFScoreVBox;
    GridPane gridpane;

    public PitrovskiFScoreVBox (DataContainerManager dataContainerManager){
        pitrovskiFScoreVBox = this;
        AreaLabel headerLabel = new AreaLabel("Pitrovski F Score");




        gridpane = new GridPane();

        EvaluationPointsLabel C0R0 = new EvaluationPointsLabel("Return on assets");
        EvaluationPointsLabel C0R1 = new EvaluationPointsLabel("Cash flow return on assets");
        EvaluationPointsLabel C0R2 = new EvaluationPointsLabel("Change in return on assets");
        EvaluationPointsLabel C0R3 = new EvaluationPointsLabel("Quality of earnings");
        EvaluationPointsLabel C0R4 = new EvaluationPointsLabel("Change in leverage");
        EvaluationPointsLabel C0R5 = new EvaluationPointsLabel("Change in liquidity");
        EvaluationPointsLabel C0R6 = new EvaluationPointsLabel("Change in shares issue");
        EvaluationPointsLabel C0R7 = new EvaluationPointsLabel("Change in gross margin");
        EvaluationPointsLabel C0R8 = new EvaluationPointsLabel("Change in asset turnover");



        EvaluationPointsLabel C1R0 = new EvaluationPointsLabel("Return on assets");
        EvaluationPointsLabel C2R0 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear()>0){
            C1R0.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear())+"% , ROA positive  " );
            C2R0 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R0.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear())+"% , ROA not positive  ");
        }


        EvaluationPointsLabel C1R1 = new EvaluationPointsLabel("Cash flow return on assets");
        EvaluationPointsLabel C2R1 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear()>0){
            C1R1.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getCashFlowReturnOnAssets())+"% , CFROA positive  " );
            C2R1= EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R1.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getCashFlowReturnOnAssets())+"% , CFROA not positive  ");
        }

        EvaluationPointsLabel C1R2 = new EvaluationPointsLabel("Change in Return on assets");
        EvaluationPointsLabel C2R2 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInReturnOnAssets()>0){
            C1R2.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInReturnOnAssets())+"% , Change in ROA positive  " );
            C2R2= EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R2.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInReturnOnAssets())+"% , Change in ROA not positive  ");
        }

        EvaluationPointsLabel C1R3 = new EvaluationPointsLabel("Quality of earnings");
        EvaluationPointsLabel C2R3 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getCashFlowReturnOnAssets() >
                dataContainerManager.getPitrovskiFScoreData().getReturnOnAssetsCurrentYear()){
            C1R3.setText(" CFROA higher than ROA " );
            C2R3= EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R3.setText(" CFROA not higher than ROA " );
        }

        EvaluationPointsLabel C1R4 = new EvaluationPointsLabel("Change in leverage");
        EvaluationPointsLabel C2R4 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInLeverage()<0){
            C1R4.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInLeverage())+"% , Leverage decreased  " );
            C2R4 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R4.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInLeverage())+"% , Leverage not decreased  ");
        }


        EvaluationPointsLabel C1R5 = new EvaluationPointsLabel("Change in liquidity");
        EvaluationPointsLabel C2R5 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInCurrentRatio()>0){
            C1R5.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInCurrentRatio())+"% , Liquidity increased  " );
            C2R5 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R5.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInCurrentRatio())+"% , Liquidity not increased  ");
        }


        EvaluationPointsLabel C1R6 = new EvaluationPointsLabel("Change in shares issues");
        EvaluationPointsLabel C2R6 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInSharesIssues()<=0){
            C1R6.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInSharesIssues())+" , Shares issues not increased  " );
            C2R6 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R6.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInSharesIssues())+" , Shares issues increased  ");
        }


        EvaluationPointsLabel C1R7 = new EvaluationPointsLabel("Change in gross margin");
        EvaluationPointsLabel C2R7 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInGrossMargin()>0){
            C1R7.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInGrossMargin())+"% , Gross margin increased  " );
            C2R7 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R7.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInGrossMargin())+"% , Gross margin not increased  ");
        }


        EvaluationPointsLabel C1R8 = new EvaluationPointsLabel("Change in asset turnover");
        EvaluationPointsLabel C2R8 = EvaluationPointsLabel.buildPointsLabel(false);
        if (dataContainerManager.getPitrovskiFScoreData().getChangeInAssetTurnover()>0){
            C1R8.setText(" = " + DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInAssetTurnover())+"% , Asset turnover increased  " );
            C2R8 = EvaluationPointsLabel.buildPointsLabel(true);
        }
        else{
            C1R8.setText(" = " +DataExtractor.formatNumbers(
                    dataContainerManager.getPitrovskiFScoreData().getChangeInAssetTurnover())+"% , Asset turnover not increased  ");
        }







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


        gridpane.add(C2R0,2,0);
        gridpane.add(C2R1,2,1);
        gridpane.add(C2R2,2,2);
        gridpane.add(C2R3,2,3);
        gridpane.add(C2R4,2,4);
        gridpane.add(C2R5,2,5);
        gridpane.add(C2R6,2,6);
        gridpane.add(C2R7,2,7);
        gridpane.add(C2R8,2,8);



        gridpane.setHgap(5);
        gridpane.setVgap(15);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().add(columnConstraints);


        pitrovskiFScoreVBox.getChildren().add(headerLabel);
        pitrovskiFScoreVBox.getChildren().add(gridpane);
        pitrovskiFScoreVBox.setAlignment(Pos.CENTER);
        pitrovskiFScoreVBox.setSpacing(10);
    }

    public GridPane getGridpane() {
        return gridpane;
    }
}
