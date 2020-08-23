package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

public class EvaluationData {

    private EvaluationData evaluationData;
    private double currentMeanFairValue;
    private double currentStockPrice;
    private double meanHistoricalStockPrice;
    private double standardDeviation;
    private double stockPriceVolatility;
    private double tenYearStockPriceCAGR;
    private double dividendsPerShare;
    private double dividendYield;
    private int fairValue2StockPricePoints;
    private int pitrovskiFScorePoints;
    private int volatilityAndPerformancePoints;
    private int dividendFactorsPoints;
    private int sumOfEvaluationPoints;


    public EvaluationData (DataContainerManager dataContainerManager) throws Exception {
        evaluationData = this;
        currentMeanFairValue = DataExtractor.calculateMeanValueOverOneList
                (dataContainerManager.getFairValueAnalysisData().getMeanFairValue(),4);

        currentStockPrice = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue();


        meanHistoricalStockPrice = DataExtractor.calculateMeanValueOverOneList(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),0);

        standardDeviation = DataExtractor.calculateStandardDeviation(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),0);

        stockPriceVolatility = DataExtractor.calculateMarginForTwoValuesInPercent(standardDeviation,meanHistoricalStockPrice);

        tenYearStockPriceCAGR = DataExtractor.calculateCAGRFromDailyData(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),3650);

        dividendsPerShare = DataExtractor.extractSumOfDataValues(
                dataContainerManager.getCompanyFundamentalData().getDividendsPerShare(),4);

        dividendYield = DataExtractor.extractSumOfDataValues(
                dataContainerManager.getCompanyFundamentalData().getDividendYield(),4);

        fairValue2StockPricePoints = DataExtractor.calculatePoints(25,meanHistoricalStockPrice,currentMeanFairValue);



    }

    public double getCurrentMeanFairValue() {
        return currentMeanFairValue;
    }

    public void setCurrentMeanFairValue(double currentMeanFairValue) {
        this.currentMeanFairValue = currentMeanFairValue;
    }

    public double getCurrentStockPrice() {
        return currentStockPrice;
    }

    public void setCurrentStockPrice(double currentStockPrice) {
        this.currentStockPrice = currentStockPrice;
    }


    public double getMeanHistoricalStockPrice() {
        return meanHistoricalStockPrice;
    }

    public void setMeanHistoricalStockPrice(double meanHistoricalStockPrice) {
        this.meanHistoricalStockPrice = meanHistoricalStockPrice;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getStockPriceVolatility() {
        return stockPriceVolatility;
    }

    public void setStockPriceVolatility(double stockPriceVolatility) {
        this.stockPriceVolatility = stockPriceVolatility;
    }

    public double getTenYearStockPriceCAGR() {
        return tenYearStockPriceCAGR;
    }

    public void setTenYearStockPriceCAGR(double tenYearStockPriceCAGR) {
        this.tenYearStockPriceCAGR = tenYearStockPriceCAGR;
    }

    public double getDividendsPerShare() {
        return dividendsPerShare;
    }

    public void setDividendsPerShare(double dividendsPerShare) {
        this.dividendsPerShare = dividendsPerShare;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public int getFairValue2StockPricePoints() {
        return fairValue2StockPricePoints;
    }

    public void setFairValue2StockPricePoints(int fairValue2StockPricePoints) {
        this.fairValue2StockPricePoints = fairValue2StockPricePoints;
    }

    public int getPitrovskiFScorePoints() {
        return pitrovskiFScorePoints;
    }

    public void setPitrovskiFScorePoints(int pitrovskiFScorePoints) {
        this.pitrovskiFScorePoints = pitrovskiFScorePoints;
    }

    public int getVolatilityAndPerformancePoints() {
        return volatilityAndPerformancePoints;
    }

    public void setVolatilityAndPerformancePoints(int volatilityAndPerformancePoints) {
        this.volatilityAndPerformancePoints = volatilityAndPerformancePoints;
    }

    public int getDividendFactorsPoints() {
        return dividendFactorsPoints;
    }

    public void setDividendFactorsPoints(int dividendFactorsPoints) {
        this.dividendFactorsPoints = dividendFactorsPoints;
    }

    public int getSumOfEvaluationPoints() {
        return sumOfEvaluationPoints;
    }

    public void setSumOfEvaluationPoints(int sumOfEvaluationPoints) {
        this.sumOfEvaluationPoints = sumOfEvaluationPoints;
    }


    @Override
    public String toString() {
        return "EvaluationData{" +"\n"+
                "currentMeanFairValue=" + currentMeanFairValue +"\n"+
                ", currentStockPrice=" + currentStockPrice +"\n"+
                ", meanHistoricalStockPrice=" + meanHistoricalStockPrice +"\n"+
                ", standardDeviation=" + standardDeviation +"\n"+
                ", stockPriceVolatility=" + stockPriceVolatility +"\n"+
                ", tenYearStockPriceCAGR=" + tenYearStockPriceCAGR +"\n"+
                ", dividendsPerShare=" + dividendsPerShare +"\n"+
                ", dividendYield=" + dividendYield +"\n"+
                ", fairValue2StockPricePoints=" + fairValue2StockPricePoints +"\n"+
                ", pitrovskiFScorePoints=" + pitrovskiFScorePoints +"\n"+
                ", volatilityAndPerformancePoints=" + volatilityAndPerformancePoints +"\n"+
                ", dividendFactorsPoints=" + dividendFactorsPoints +"\n"+
                ", sumOfEvaluationPoints=" + sumOfEvaluationPoints +"\n"+
                '}';
    }
}
