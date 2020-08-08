package Controllers;

import Data.CompanyFundamentalData;
import Data.CompanyOverviewData;
import Data.FairValueAnalysisData;

public class DataContainerManager {
    private DataContainerManager dataContainerManager;
    private ClientManager clientManager;
    private CompanyOverviewData companyOverviewData;
    private CompanyFundamentalData companyFundamentalData;
    private FairValueAnalysisData fairValueAnalysisData;




    public DataContainerManager (ClientManager clientManager)throws Exception{
        dataContainerManager = this;
        this.clientManager = clientManager;
        companyOverviewData = new CompanyOverviewData(clientManager);
        companyFundamentalData = new CompanyFundamentalData(clientManager,dataContainerManager);
        fairValueAnalysisData = new FairValueAnalysisData();


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
}
