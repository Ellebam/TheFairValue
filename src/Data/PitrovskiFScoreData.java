package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.Arrays;

public class PitrovskiFScoreData {

    private PitrovskiFScoreData pitrovskiFScoreData;
    private double returnOnAssetsCurrentYear;
    private double returnOnAssetsLastYear;
    private double cashFlowReturnOnAssets;
    private double changeInReturnOnAssets;
    private double qualityOfEarnings;
    private double averageTotalAssets;
    private double currentLongTermDebt;
    private double lastYearLongTermDebt;
    private double currentLeverage;
    private double lastYearLeverage;
    private double changeInLeverage;
    private double currentTotalAssets;
    private double lastYearTotalAssets;
    private double currentRatio;
    private double lastYearCurrentRatio;
    private double changeInCurrentRatio;
    private double currentCommonSharesOutstanding;
    private double lastYearCommonSharesOutstanding;
    private double changeInSharesIssues;
    private double currentGrossMargin;
    private double lastYearGrossMargin;
    private double changeInGrossMargin;
    private double currentAssetTurnover;
    private double lastYearAssetTurnover;
    private double changeInAssetTurnover;
    private int[] PitrovskiFScoreList = new int[9];
    private int PitrovskiFScore = 0;


public PitrovskiFScoreData (DataContainerManager dataContainerManager) throws Exception {
    pitrovskiFScoreData = this;
    returnOnAssetsCurrentYear = DataExtractor.calculateReturnOnAssets(0,dataContainerManager);
    returnOnAssetsLastYear = DataExtractor.calculateReturnOnAssets(3,dataContainerManager);
    cashFlowReturnOnAssets = DataExtractor.calculateCashflowReturnOnAssets(dataContainerManager);
    changeInReturnOnAssets = returnOnAssetsCurrentYear-returnOnAssetsLastYear;
    qualityOfEarnings = cashFlowReturnOnAssets-returnOnAssetsCurrentYear;
    averageTotalAssets = DataExtractor.calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getTotalAssets());
    currentLongTermDebt = dataContainerManager.getCompanyFundamentalData().getLongTermDebt().get(0).getValue();
    lastYearLongTermDebt = dataContainerManager.getCompanyFundamentalData().getLongTermDebt().get(3).getValue();
    currentLeverage = DataExtractor.divideTwoValues(currentLongTermDebt,averageTotalAssets);
    lastYearLeverage = DataExtractor.divideTwoValues(lastYearLongTermDebt,averageTotalAssets);
    changeInLeverage = currentLeverage-lastYearLeverage;
    currentTotalAssets = dataContainerManager.getCompanyFundamentalData().getTotalAssets().get(0).getValue();
    lastYearTotalAssets = dataContainerManager.getCompanyFundamentalData().getTotalAssets().get(3).getValue();
    currentRatio = dataContainerManager.getCompanyFundamentalData().getCurrentRatio().get(0).getValue();
    lastYearCurrentRatio = dataContainerManager.getCompanyFundamentalData().getCurrentRatio().get(3).getValue();
    changeInCurrentRatio = currentRatio-lastYearCurrentRatio;
    currentCommonSharesOutstanding = dataContainerManager.getCompanyFundamentalData().getCommonStockSharesOutstanding().get(0).getValue();
    lastYearCommonSharesOutstanding= dataContainerManager.getCompanyFundamentalData().getCommonStockSharesOutstanding().get(3).getValue();
    changeInSharesIssues = currentCommonSharesOutstanding-lastYearCommonSharesOutstanding;
    currentGrossMargin = dataContainerManager.getCompanyFundamentalData().getGrossMargin().get(0).getValue();
    lastYearGrossMargin = dataContainerManager.getCompanyFundamentalData().getGrossMargin().get(3).getValue();
    changeInGrossMargin = currentGrossMargin-lastYearGrossMargin;
    currentAssetTurnover = dataContainerManager.getCompanyFundamentalData().getAssetTurnover().get(0).getValue();
    lastYearAssetTurnover = dataContainerManager.getCompanyFundamentalData().getAssetTurnover().get(3).getValue();
    changeInAssetTurnover = currentAssetTurnover-lastYearAssetTurnover;






}


    @Override
    public String toString() {
        return "PitrovskiFScoreData{" +"\n" +

                ", returnOnAssetsCurrentYear=" + "\n" +returnOnAssetsCurrentYear +"\n" +
                ", returnOnAssetsLastYear=" + "\n" +returnOnAssetsLastYear +"\n" +
                ", cashFlowReturnOnAssets=" +"\n" + cashFlowReturnOnAssets +"\n" +
                ", changeInReturnOnAssets=" + "\n" +changeInReturnOnAssets +"\n" +
                ", qualityOfEarnings=" + "\n" +qualityOfEarnings +"\n" +
                ", averageTotalAssets=" +"\n" + averageTotalAssets +"\n" +
                ", currentLongTermDebt=" + "\n" +currentLongTermDebt +"\n" +
                ", lastYearLongTermDebt=" +"\n" + lastYearLongTermDebt +"\n" +
                ", currentLeverage=" +"\n" + currentLeverage +"\n" +
                ", lastYearLeverage=" +"\n" + lastYearLeverage +"\n" +
                ", changeInLeverage=" + "\n" +changeInLeverage +"\n" +
                ", currentTotalAssets=" + "\n" +currentTotalAssets +"\n" +
                ", lastYearTotalAssets=" + "\n" +lastYearTotalAssets +"\n" +
                ", currentRatio=" + "\n" +currentRatio +"\n" +
                ", lastYearCurrentRatio=" + "\n" +lastYearCurrentRatio +"\n" +
                ", changeInCurrentRatio=" + "\n" +changeInCurrentRatio +"\n" +
                ", changeInSharesIssues=" +"\n" + changeInSharesIssues +"\n" +
                ", changeInGrossMargin=" + "\n" +changeInGrossMargin +"\n" +
                ", changeInAssetTurnover=" +"\n" + changeInAssetTurnover +"\n" +

                '}';
    }
}
