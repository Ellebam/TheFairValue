package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;

public class FairValueAnalysisData {

    private FairValueAnalysisData fairValueAnalysisData;
    private ArrayList<FinancialDataObject> HistoricalDCFFairValuePerShare;
    private ArrayList<FinancialDataObject> PERatio;
    private ArrayList<FinancialDataObject> PeterLynchFairValue;
    private ArrayList<FinancialDataObject> GrahamNumber;


    public FairValueAnalysisData (DataContainerManager dataContainerManager) throws Exception{
        fairValueAnalysisData = this;

        HistoricalDCFFairValuePerShare = DataExtractor.calculateHistoricalDCFFairValuePerShare(dataContainerManager);
        PERatio = DataExtractor.calculatePriceToEarningsRatio("PERatio",dataContainerManager);
        PeterLynchFairValue = DataExtractor.calculatePeterLynchFairValue(dataContainerManager);
        GrahamNumber = DataExtractor.calculateGrahamNumber(dataContainerManager);

    }

    @Override
    public String toString() {
        return "FairValueAnalysisData{" +"\n"+
                "HistoricalDCFFairValuePerShare="+"\n" +HistoricalDCFFairValuePerShare +"\n"+
                "PERatio="+"\n" +PERatio +"\n"+
                "PeterLynchFairValue="+"\n" +PeterLynchFairValue +"\n"+
                "GrahamNumber="+"\n" +GrahamNumber +"\n"+
                '}';
    }
}
