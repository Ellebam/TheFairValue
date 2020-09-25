package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.Arrays;

/**
 * Class representing the data set for the Pitrovski F Score evaluation object.
 */
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
    private int[] PitrovskiFScoreList = new int[9]; //Array for storing single point positions
    private int PitrovskiFScore = 0; //int for updating sum of all points


    /**
     * The Constructor for PitrovskiFScoreData also uses a DataContainerManager object as a reference for calculating the
     * various instance variables used for determination of the Pitrovski F Score. Even though the single variables are
     * calculated the end score is not calculated by this object. That part is triggered before the UI is built by the
     * EvaluationVBox Class constructor.
     * @param dataContainerManager data reference object
     * @throws Exception
     */
    public PitrovskiFScoreData (DataContainerManager dataContainerManager) throws Exception {
    pitrovskiFScoreData = this;
    returnOnAssetsCurrentYear = DataExtractor.calculateReturnOnAssets(0,dataContainerManager);
    returnOnAssetsLastYear = DataExtractor.calculateReturnOnAssets(3,dataContainerManager);
    cashFlowReturnOnAssets = DataExtractor.calculateCashflowReturnOnAssets(dataContainerManager);
    changeInReturnOnAssets = returnOnAssetsCurrentYear-returnOnAssetsLastYear;
    qualityOfEarnings = cashFlowReturnOnAssets-returnOnAssetsCurrentYear;
    averageTotalAssets = DataExtractor.calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getTotalAssets(),0);
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

    public double getReturnOnAssetsCurrentYear() {
        return returnOnAssetsCurrentYear;
    }

    public void setReturnOnAssetsCurrentYear(double returnOnAssetsCurrentYear) {
        this.returnOnAssetsCurrentYear = returnOnAssetsCurrentYear;
    }

    public double getReturnOnAssetsLastYear() {
        return returnOnAssetsLastYear;
    }

    public void setReturnOnAssetsLastYear(double returnOnAssetsLastYear) {
        this.returnOnAssetsLastYear = returnOnAssetsLastYear;
    }

    public double getCashFlowReturnOnAssets() {
        return cashFlowReturnOnAssets;
    }

    public void setCashFlowReturnOnAssets(double cashFlowReturnOnAssets) {
        this.cashFlowReturnOnAssets = cashFlowReturnOnAssets;
    }

    public double getChangeInReturnOnAssets() {
        return changeInReturnOnAssets;
    }

    public void setChangeInReturnOnAssets(double changeInReturnOnAssets) {
        this.changeInReturnOnAssets = changeInReturnOnAssets;
    }

    public double getQualityOfEarnings() {
        return qualityOfEarnings;
    }

    public void setQualityOfEarnings(double qualityOfEarnings) {
        this.qualityOfEarnings = qualityOfEarnings;
    }

    public double getAverageTotalAssets() {
        return averageTotalAssets;
    }

    public void setAverageTotalAssets(double averageTotalAssets) {
        this.averageTotalAssets = averageTotalAssets;
    }

    public double getCurrentLongTermDebt() {
        return currentLongTermDebt;
    }

    public void setCurrentLongTermDebt(double currentLongTermDebt) {
        this.currentLongTermDebt = currentLongTermDebt;
    }

    public double getLastYearLongTermDebt() {
        return lastYearLongTermDebt;
    }

    public void setLastYearLongTermDebt(double lastYearLongTermDebt) {
        this.lastYearLongTermDebt = lastYearLongTermDebt;
    }

    public double getCurrentLeverage() {
        return currentLeverage;
    }

    public void setCurrentLeverage(double currentLeverage) {
        this.currentLeverage = currentLeverage;
    }

    public double getLastYearLeverage() {
        return lastYearLeverage;
    }

    public void setLastYearLeverage(double lastYearLeverage) {
        this.lastYearLeverage = lastYearLeverage;
    }

    public double getChangeInLeverage() {
        return changeInLeverage;
    }

    public void setChangeInLeverage(double changeInLeverage) {
        this.changeInLeverage = changeInLeverage;
    }

    public double getCurrentTotalAssets() {
        return currentTotalAssets;
    }

    public void setCurrentTotalAssets(double currentTotalAssets) {
        this.currentTotalAssets = currentTotalAssets;
    }

    public double getLastYearTotalAssets() {
        return lastYearTotalAssets;
    }

    public void setLastYearTotalAssets(double lastYearTotalAssets) {
        this.lastYearTotalAssets = lastYearTotalAssets;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(double currentRatio) {
        this.currentRatio = currentRatio;
    }

    public double getLastYearCurrentRatio() {
        return lastYearCurrentRatio;
    }

    public void setLastYearCurrentRatio(double lastYearCurrentRatio) {
        this.lastYearCurrentRatio = lastYearCurrentRatio;
    }

    public double getChangeInCurrentRatio() {
        return changeInCurrentRatio;
    }

    public void setChangeInCurrentRatio(double changeInCurrentRatio) {
        this.changeInCurrentRatio = changeInCurrentRatio;
    }

    public double getCurrentCommonSharesOutstanding() {
        return currentCommonSharesOutstanding;
    }

    public void setCurrentCommonSharesOutstanding(double currentCommonSharesOutstanding) {
        this.currentCommonSharesOutstanding = currentCommonSharesOutstanding;
    }

    public double getLastYearCommonSharesOutstanding() {
        return lastYearCommonSharesOutstanding;
    }

    public void setLastYearCommonSharesOutstanding(double lastYearCommonSharesOutstanding) {
        this.lastYearCommonSharesOutstanding = lastYearCommonSharesOutstanding;
    }

    public double getChangeInSharesIssues() {
        return changeInSharesIssues;
    }

    public void setChangeInSharesIssues(double changeInSharesIssues) {
        this.changeInSharesIssues = changeInSharesIssues;
    }

    public double getCurrentGrossMargin() {
        return currentGrossMargin;
    }

    public void setCurrentGrossMargin(double currentGrossMargin) {
        this.currentGrossMargin = currentGrossMargin;
    }

    public double getLastYearGrossMargin() {
        return lastYearGrossMargin;
    }

    public void setLastYearGrossMargin(double lastYearGrossMargin) {
        this.lastYearGrossMargin = lastYearGrossMargin;
    }

    public double getChangeInGrossMargin() {
        return changeInGrossMargin;
    }

    public void setChangeInGrossMargin(double changeInGrossMargin) {
        this.changeInGrossMargin = changeInGrossMargin;
    }

    public double getCurrentAssetTurnover() {
        return currentAssetTurnover;
    }

    public void setCurrentAssetTurnover(double currentAssetTurnover) {
        this.currentAssetTurnover = currentAssetTurnover;
    }

    public double getLastYearAssetTurnover() {
        return lastYearAssetTurnover;
    }

    public void setLastYearAssetTurnover(double lastYearAssetTurnover) {
        this.lastYearAssetTurnover = lastYearAssetTurnover;
    }

    public double getChangeInAssetTurnover() {
        return changeInAssetTurnover;
    }

    public void setChangeInAssetTurnover(double changeInAssetTurnover) {
        this.changeInAssetTurnover = changeInAssetTurnover;
    }

    public int[] getPitrovskiFScoreList() {
        return PitrovskiFScoreList;
    }

    public void setPitrovskiFScoreList(int[] pitrovskiFScoreList) {
        PitrovskiFScoreList = pitrovskiFScoreList;
    }

    public int getPitrovskiFScore() {
        return PitrovskiFScore;
    }

    public void setPitrovskiFScore(int pitrovskiFScore) {
        PitrovskiFScore = pitrovskiFScore;
    }

    @Override
    public String toString() {
        return "PitrovskiFScoreData{" +

                ", returnOnAssetsCurrentYear=" + returnOnAssetsCurrentYear +"\n" +
                ", returnOnAssetsLastYear=" + returnOnAssetsLastYear +"\n" +
                ", cashFlowReturnOnAssets=" + cashFlowReturnOnAssets +"\n" +
                ", changeInReturnOnAssets=" + changeInReturnOnAssets +"\n" +
                ", qualityOfEarnings=" +qualityOfEarnings +"\n" +
                ", averageTotalAssets="  +averageTotalAssets +"\n" +
                ", currentLongTermDebt=" +currentLongTermDebt +"\n" +
                ", lastYearLongTermDebt=" + lastYearLongTermDebt +"\n" +
                ", currentLeverage="  +currentLeverage +"\n" +
                ", lastYearLeverage="  +lastYearLeverage +"\n" +
                ", changeInLeverage="  + changeInLeverage +"\n" +
                ", currentTotalAssets="  +currentTotalAssets +"\n" +
                ", lastYearTotalAssets=" + lastYearTotalAssets +"\n" +
                ", currentRatio="  +currentRatio +"\n" +
                ", lastYearCurrentRatio="  +lastYearCurrentRatio +"\n" +
                ", changeInCurrentRatio="  +changeInCurrentRatio +"\n" +
                ", currentCommonSharesOutstanding="  + currentCommonSharesOutstanding +"\n" +
                ", lastYearCommonSharesOutstanding="  + lastYearCommonSharesOutstanding +"\n" +
                ", changeInSharesIssues="  + changeInSharesIssues +"\n" +
                ", currentGrossMargin="  + currentGrossMargin +"\n" +
                ", lastYearGrossMargin="  + lastYearGrossMargin +"\n" +
                ", changeInGrossMargin="  + changeInGrossMargin +"\n" +
                ", currentAssetTurnover="  + currentAssetTurnover +"\n" +
                ", lastYearAssetTurnover="  + lastYearAssetTurnover +"\n" +
                ", changeInAssetTurnover="  +changeInAssetTurnover +"\n" +
                ", PitrovskiFScoreList="  +Arrays.toString(PitrovskiFScoreList) +"\n" +
                ", PitrovskiFScore="  + PitrovskiFScore +"\n" +
                '}';
    }
}
