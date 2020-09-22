package Controllers;

import Data.*;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataContainerManager {
    private DataContainerManager dataContainerManager;
    private ClientManager clientManager;
    private CompanyOverviewData companyOverviewData;
    private CompanyFundamentalData companyFundamentalData;
    private FairValueAnalysisData fairValueAnalysisData;
    private PitrovskiFScoreData pitrovskiFScoreData;
    private EvaluationData evaluationData;
    private int loadingCounter;




    public DataContainerManager (ClientManager clientManager)throws Exception{
        dataContainerManager = this;
        this.clientManager = clientManager;
        loadingCounter = 0;

        companyOverviewData = new CompanyOverviewData(clientManager);
        loadingCounter++;
        companyFundamentalData = new CompanyFundamentalData(clientManager,dataContainerManager);
        loadingCounter++;
        fairValueAnalysisData = new FairValueAnalysisData(dataContainerManager);
        loadingCounter++;
        pitrovskiFScoreData = new PitrovskiFScoreData(dataContainerManager);
        loadingCounter++;
        DataExtractor.calculatePitrovskiFScore(dataContainerManager);
        evaluationData = new EvaluationData(dataContainerManager);
        loadingCounter++;
        DataExtractor.calculateEvaluationPoints(dataContainerManager);
        loadingCounter++;

        System.out.println(loadingCounter);




    }
    public CompanyOverviewData getCompanyOverviewData() {
        return companyOverviewData;
    }

    public void setCompanyOverviewData(CompanyOverviewData companyOverviewData) {
        this.companyOverviewData = companyOverviewData;
    }

    public CompanyFundamentalData getCompanyFundamentalData() {
        return companyFundamentalData;
    }

    public void setCompanyFundamentalData(CompanyFundamentalData companyFundamentalData) {
        this.companyFundamentalData = companyFundamentalData;
    }

    public FairValueAnalysisData getFairValueAnalysisData() {
        return fairValueAnalysisData;
    }

    public void setFairValueAnalysisData(FairValueAnalysisData fairValueAnalysisData) {
        this.fairValueAnalysisData = fairValueAnalysisData;
    }

    public PitrovskiFScoreData getPitrovskiFScoreData() {
        return pitrovskiFScoreData;
    }

    public void setPitrovskiFScoreData(PitrovskiFScoreData pitrovskiFScoreData) {
        this.pitrovskiFScoreData = pitrovskiFScoreData;
    }

    public EvaluationData getEvaluationData() {
        return evaluationData;
    }

    public void setEvaluationData(EvaluationData evaluationData) {
        this.evaluationData = evaluationData;
    }

    public int getLoadingCounter() {
        return loadingCounter;
    }
}
