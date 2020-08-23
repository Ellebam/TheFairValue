package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import javax.xml.crypto.Data;

public class EvaluationData {

    private EvaluationData evaluationData;
    private double currentMeanFairValue;
    private double currentStockPrice;
    private int pitrovskiFScore;
    private double meanHistoricalStockPrice;
    private double standardDeviation;
    private double stockPriceVolatility;
    private double stockPriceMomentum;
    private double dividendsPerShare;
    private double dividendYield;


    public EvaluationData (DataContainerManager dataContainerManager) throws Exception {
        evaluationData = this;
        currentMeanFairValue = DataExtractor.calculateMeanValueOverOneList
                (dataContainerManager.getFairValueAnalysisData().getMeanFairValue(),4);
        currentStockPrice = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue();
        pitrovskiFScore = dataContainerManager.getPitrovskiFScoreData().getPitrovskiFScore();
        meanHistoricalStockPrice = DataExtractor.calculateMeanValueOverOneList(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),0);
        standardDeviation = DataExtractor.calculateStandardDeviation(
                dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice(),0);
        stockPriceVolatility = DataExtractor.calculateMarginForTwoValuesInPercent(standardDeviation,meanHistoricalStockPrice);



    }

    @Override
    public String toString() {
        return "EvaluationData{" +
                "currentMeanFairValue=" + currentMeanFairValue +
                ", currentStockPrice=" + currentStockPrice +
                ", pitrovskiFScore=" + pitrovskiFScore +
                ", meanHistoricalStockPrice=" + meanHistoricalStockPrice +
                ", standardDeviation=" + standardDeviation +
                ", stockPriceVolatility=" + stockPriceVolatility +
                ", stockPriceMomentum=" + stockPriceMomentum +
                ", dividendsPerShare=" + dividendsPerShare +
                ", dividendYield=" + dividendYield +
                '}';
    }
}
