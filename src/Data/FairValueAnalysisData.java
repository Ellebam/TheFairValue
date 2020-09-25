package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;

/**
 * Class combining and bundling all Fair value data sets for a given company through DataContainerManager.
 */
public class FairValueAnalysisData {

    private FairValueAnalysisData fairValueAnalysisData;
    private ArrayList<FinancialDataObject> HistoricalDCFFairValuePerShare;
    private ArrayList<FinancialDataObject> PERatio;
    private ArrayList<FinancialDataObject> PeterLynchFairValue;
    private ArrayList<FinancialDataObject> GrahamNumber;
    private ArrayList<FinancialDataObject> meanFairValue;

    /**
     * Constructor for FairValueAnalysisData  will take a DataContainerManager object and use its Data to calculate several
     * different fair value  indicators with the help of DataExtractor.
     * @param dataContainerManager Data object used as reference
     * @throws Exception
     */
    public FairValueAnalysisData (DataContainerManager dataContainerManager) throws Exception{
        fairValueAnalysisData = this;

        HistoricalDCFFairValuePerShare = DataExtractor.calculateHistoricalDCFFairValuePerShare(dataContainerManager);
        PERatio = DataExtractor.calculatePriceToEarningsRatio("PERatio",dataContainerManager);
        PeterLynchFairValue = DataExtractor.calculatePeterLynchFairValue(dataContainerManager);
        GrahamNumber = DataExtractor.calculateGrahamNumber(dataContainerManager);
        meanFairValue = DataExtractor.calculateMeanOverThreeLists("meanFairValue",HistoricalDCFFairValuePerShare,
                PeterLynchFairValue,GrahamNumber);


    }


    public ArrayList<FinancialDataObject> getHistoricalDCFFairValuePerShare() {
        return HistoricalDCFFairValuePerShare;
    }

    public void setHistoricalDCFFairValuePerShare(ArrayList<FinancialDataObject> historicalDCFFairValuePerShare) {
        HistoricalDCFFairValuePerShare = historicalDCFFairValuePerShare;
    }

    public ArrayList<FinancialDataObject> getPERatio() {
        return PERatio;
    }

    public void setPERatio(ArrayList<FinancialDataObject> PERatio) {
        this.PERatio = PERatio;
    }

    public ArrayList<FinancialDataObject> getPeterLynchFairValue() {
        return PeterLynchFairValue;
    }

    public void setPeterLynchFairValue(ArrayList<FinancialDataObject> peterLynchFairValue) {
        PeterLynchFairValue = peterLynchFairValue;
    }

    public ArrayList<FinancialDataObject> getGrahamNumber() {
        return GrahamNumber;
    }

    public void setGrahamNumber(ArrayList<FinancialDataObject> grahamNumber) {
        GrahamNumber = grahamNumber;
    }

    public ArrayList<FinancialDataObject> getMeanFairValue() {
        return meanFairValue;
    }

    public void setMeanFairValue(ArrayList<FinancialDataObject> meanFairValue) {
        this.meanFairValue = meanFairValue;
    }

    @Override
    public String toString() {
        return "FairValueAnalysisData{" +"\n"+
                "HistoricalDCFFairValuePerShare="+"\n" +HistoricalDCFFairValuePerShare +"\n"+
                "PERatio="+"\n" +PERatio +"\n"+
                "PeterLynchFairValue="+"\n" +PeterLynchFairValue +"\n"+
                "GrahamNumber="+"\n" +GrahamNumber +"\n"+
                "meanFairValue="+"\n" +meanFairValue +"\n"+
                '}';
    }
}
