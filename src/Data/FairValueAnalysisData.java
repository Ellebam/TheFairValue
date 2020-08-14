package Data;

import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;

public class FairValueAnalysisData {

    private FairValueAnalysisData fairValueAnalysisData;
    private ArrayList<FinancialDataObject> HistoricalDCFFairValuePerShare;

    public FairValueAnalysisData (DataContainerManager dataContainerManager){
        fairValueAnalysisData = this;

        HistoricalDCFFairValuePerShare = DataExtractor.calculateHistoricalDCFFairValuePerShare(dataContainerManager);

    }

    @Override
    public String toString() {
        return "FairValueAnalysisData{" +
                "HistoricalDCFFairValuePerShare=" + "\n"+HistoricalDCFFairValuePerShare +
                '}';
    }
}
