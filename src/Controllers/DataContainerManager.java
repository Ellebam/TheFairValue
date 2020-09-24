package Controllers;

import Data.*;

/**
 * This object bundles all the different Data-Classes that are built for data display and calculations. It is used in
 * almost all of the major classes of this application as a reference point.
 */
public class DataContainerManager {

    private DataContainerManager dataContainerManager;
    private ClientManager clientManager;
    private CompanyOverviewData companyOverviewData;
    private CompanyFundamentalData companyFundamentalData;
    private FairValueAnalysisData fairValueAnalysisData;
    private PitrovskiFScoreData pitrovskiFScoreData;
    private EvaluationData evaluationData;


    /**
     * The  constructor takes a ClientManager object and builds the data classes one after another and bundles them in a
     * newly created DataContainerManager object. After this all data for display and evaluation has been finished
     * @param clientManager object used for API-Connection
     * @throws Exception is thrown when object couldn't instantiate properly => data may be corupted
     */
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
