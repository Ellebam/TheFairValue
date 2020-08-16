package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;

public class FairValueAnalysisData {

    private FairValueAnalysisData fairValueAnalysisData;
    private ArrayList<FinancialDataObject> HistoricalDCFFairValuePerShare;
    private ArrayList<FinancialDataObject> PERatio;
    private ArrayList<FinancialDataObject> PeterLynchFairValue;


    public FairValueAnalysisData (DataContainerManager dataContainerManager) throws Exception{
        fairValueAnalysisData = this;

        HistoricalDCFFairValuePerShare = DataExtractor.calculateHistoricalDCFFairValuePerShare(dataContainerManager);
        PERatio = DataExtractor.calculatePriceToEarningsRatio("PERatio",dataContainerManager);
        PeterLynchFairValue = DataExtractor.calculatePeterLynchFairValue(dataContainerManager);

    }

    @Override
    public String toString() {
        return "FairValueAnalysisData{" +"\n"+
                "HistoricalDCFFairValuePerShare=" +HistoricalDCFFairValuePerShare +"\n"+
                "PERatio=" +PERatio +"\n"+
                "PeterLynchFairValue=" +PeterLynchFairValue +"\n"+
                '}';
    }
}
