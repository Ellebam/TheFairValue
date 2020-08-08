package Data;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;

public class CompanyFundamentalData {

    private CompanyFundamentalData companyFundamentalData;
    private ArrayList<FinancialDataObject> totalRevenue;
    private ArrayList<FinancialDataObject> netIncome;
    private ArrayList<FinancialDataObject> grossProfit;
    private ArrayList<FinancialDataObject> grossMargin;
    private ArrayList<FinancialDataObject> operatingIncome;
    private ArrayList<FinancialDataObject> operatingMargin;
    private ArrayList<FinancialDataObject> netMargin;
    private ArrayList<FinancialDataObject>  depreciation;
    private ArrayList<FinancialDataObject> capitalExpenditures;
    private ArrayList<FinancialDataObject> totalAssets;
    private ArrayList<FinancialDataObject> totalLiabilities;
    private ArrayList<FinancialDataObject> workingCapital;
    private ArrayList<FinancialDataObject> freeCashFlow;
    private ArrayList<FinancialDataObject> dividendPayout;
    private ArrayList<FinancialDataObject> commonStockSharesOutstanding;
    private ArrayList<FinancialDataObject> dividendsPerShare;
    private ArrayList<FinancialDataObject> netIncomeApplicableToCommonShares;
    private ArrayList<FinancialDataObject> earningsPerShare;
    private ArrayList<FinancialDataObject> dividendYield;


    private ArrayList<FinancialDataObject> payOutRatio;






    private ArrayList<FinancialDataObject> operatingCashflow;


    private ClientManager clientManager;

    private Double averageTotalRevenue;


public CompanyFundamentalData(ClientManager clientManager, DataContainerManager dataContainerManager) throws Exception{
    companyFundamentalData = this;
    this.clientManager = clientManager;
    totalRevenue = getDataFromIncomeStatement(clientManager,"totalRevenue");
    netIncome = getDataFromIncomeStatement(clientManager,"netIncome");
    grossProfit = getDataFromIncomeStatement(clientManager,"grossProfit");
    grossMargin = DataExtractor.calculateMargins_IN_PERCENT("grossMargin",grossProfit,totalRevenue);
    operatingIncome = getDataFromIncomeStatement(clientManager,"operatingIncome");
    operatingMargin = DataExtractor.calculateMargins_IN_PERCENT("operatingMargin",operatingIncome,netIncome);
    netMargin = DataExtractor.calculateMargins_IN_PERCENT("netMargin",netIncome,totalRevenue);
    depreciation = getDataFromCashFlowStatement(clientManager,"depreciation");
    capitalExpenditures = getDataFromCashFlowStatement(clientManager,"capitalExpenditures");
    totalAssets = getDataFromBalanceSheet(clientManager,"totalAssets");
    totalLiabilities = getDataFromBalanceSheet(clientManager,"totalLiabilities");
    workingCapital = DataExtractor.subtractTwoValues("workingCapital",totalAssets,totalLiabilities);
    operatingCashflow = getDataFromCashFlowStatement(clientManager,"operatingCashflow");
    freeCashFlow = DataExtractor.subtractTwoValues("freeCashFlow",operatingCashflow,capitalExpenditures);
    dividendPayout = getDataFromCashFlowStatement(clientManager,"dividendPayout");
    commonStockSharesOutstanding = getDataFromBalanceSheet(clientManager,"commonStockSharesOutstanding");
    dividendsPerShare = DataExtractor.calculateDividendsPerShare("dividendsPerShare",dividendPayout,commonStockSharesOutstanding);
    netIncomeApplicableToCommonShares = getDataFromIncomeStatement(clientManager,"netIncomeApplicableToCommonShares");
    earningsPerShare = DataExtractor.calculateMargins("earningsPerShare",netIncomeApplicableToCommonShares,commonStockSharesOutstanding);
    payOutRatio =DataExtractor.calculateMargins_IN_PERCENT("payoutRatio",dividendsPerShare,earningsPerShare);
    dividendYield = DataExtractor.calclateDividendYield("dividendYield",dividendsPerShare,
            dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice());




    averageTotalRevenue = DataExtractor.calculateMean(totalRevenue);







}

    ArrayList<FinancialDataObject> getDataFromBalanceSheet(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractBalanceSheetData(variableName, clientManager);
    return variable;
}

    ArrayList<FinancialDataObject> getDataFromIncomeStatement(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractIncomeStatementData(variableName, clientManager);
        return variable;
    }

    ArrayList<FinancialDataObject> getDataFromCashFlowStatement(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractCashFlowData(variableName, clientManager);
        return variable;
    }


    @Override
    public String toString() {
        return "CompanyFundamentalData{" +
                "totalRevenue=" + totalRevenue + "\n"+
                ", netIncome=" + netIncome +"\n"+
                ", grossProfit=" + grossProfit +"\n"+
                ", grossMargin=" + grossMargin +"\n"+
                ", operatingIncome=" + operatingIncome +"\n"+
                ", operatingMargin=" + operatingMargin +"\n"+
                ", netMargin=" + netMargin +"\n"+
                ", depreciation=" + depreciation +"\n"+
                ", capitalExpenditures=" + capitalExpenditures +"\n"+
                ", totalAssets=" + totalAssets +"\n"+
                ", totalLiabilities=" + totalLiabilities +"\n"+
                ", workingCapital=" + workingCapital +"\n"+
                ", freeCashFlow=" + freeCashFlow +"\n"+
                ", dividendPayout=" + dividendPayout +"\n"+
                ", commonStockSharesOutstanding=" + commonStockSharesOutstanding +"\n"+
                ", dividendsPerShare=" + dividendsPerShare +"\n"+
                ", netIncomeApplicableToCommonShares=" + netIncomeApplicableToCommonShares +"\n"+
                ", earningsPerShare=" + earningsPerShare +"\n"+
                ", payOutRatio=" + payOutRatio +"\n"+
                ", dividendYield=" + dividendYield +"\n"+
                '}';
    }
}
