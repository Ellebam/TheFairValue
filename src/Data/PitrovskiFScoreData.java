package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

public class PitrovskiFScoreData {

    private PitrovskiFScoreData pitrovskiFScoreData;
    private double returnOnAssetsCurrentYear;
    private double returnOnAssetsLastYear;
    private double cashFlowReturnOnAssets;
    private double changeInReturnOnAssets;
    private double qualityOfEarnings;
    private double changeInLeverage;
    private double changeInWorkingCapital;
    private double changeInSharesIssues;
    private double changeInGrossMargin;
    private double changeInAssetTurnover;
    private int[] PitrovskiFScoreList = new int[9];
    private int PitrovskiFScore = 0;


public PitrovskiFScoreData (DataContainerManager dataContainerManager) throws Exception {
    pitrovskiFScoreData = this;
    returnOnAssetsCurrentYear = DataExtractor.calculateReturnOnAssets(0,dataContainerManager);
    returnOnAssetsLastYear = DataExtractor.calculateReturnOnAssets(4,dataContainerManager);
    cashFlowReturnOnAssets = DataExtractor.calculateCashflowReturnOnAssets(dataContainerManager);
    changeInReturnOnAssets = returnOnAssetsCurrentYear-returnOnAssetsLastYear;




}

}
