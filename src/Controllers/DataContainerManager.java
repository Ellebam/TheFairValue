package Controllers;

import Data.*;

public class DataContainerManager {
    private DataContainerManager dataContainerManager;
    private ClientManager clientManager;
    private CompanyOverviewData companyOverviewData;
    private CompanyFundamentalData companyFundamentalData;
    private FairValueAnalysisData fairValueAnalysisData;
    private PitrovskiFScoreData pitrovskiFScoreData;
    private EvaluationData evaluationData;




    public DataContainerManager (ClientManager clientManager)throws Exception{
        dataContainerManager = this;
        this.clientManager = clientManager;
        companyOverviewData = new CompanyOverviewData(clientManager);
        companyFundamentalData = new CompanyFundamentalData(clientManager,dataContainerManager);
        fairValueAnalysisData = new FairValueAnalysisData(dataContainerManager);
        pitrovskiFScoreData = new PitrovskiFScoreData(dataContainerManager);
        DataExtractor.calculatePitrovskiFScore(dataContainerManager);
        evaluationData = new EvaluationData(dataContainerManager);
        DataExtractor.calculateEvaluationPoints(dataContainerManager);




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
}
