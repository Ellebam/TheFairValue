package Data;

import Controllers.ClientManager;
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
    private  ArrayList<FinancialDataObject> capitalExpenditures;
    private ArrayList<FinancialDataObject> totalAssets;
    private ArrayList<FinancialDataObject> totalLiabilities;
    private ArrayList<FinancialDataObject> workingCapital;
    private ArrayList<FinancialDataObject> freeCashFlow;
    private ArrayList<FinancialDataObject> payOutRatio;
    private ArrayList<FinancialDataObject> dividendPerShare;
    private ArrayList<FinancialDataObject> dividendYield;





    private ArrayList<FinancialDataObject> operatingCashflow;


    private ClientManager clientManager;

    private Double averageTotalRevenue;


public CompanyFundamentalData(ClientManager clientManager) throws Exception{
    companyFundamentalData = this;
    this.clientManager = clientManager;
    totalRevenue = getDataFromIncomeStatement(clientManager,"totalRevenue");
    netIncome = getDataFromIncomeStatement(clientManager,"netIncome");
    grossProfit = getDataFromIncomeStatement(clientManager,"grossProfit");
    grossMargin = DataExtractor.calculateMargins("grossMargin",grossProfit,totalRevenue);
    operatingIncome = getDataFromIncomeStatement(clientManager,"operatingIncome");
    operatingMargin = DataExtractor.calculateMargins("operatingMargin",operatingIncome,netIncome);
    netMargin = DataExtractor.calculateMargins("netMargin",netIncome,totalRevenue);
    depreciation = getDataFromCashFlowStatement(clientManager,"depreciation");
    capitalExpenditures = getDataFromCashFlowStatement(clientManager,"capitalExpenditures");
    totalAssets = getDataFromBalanceSheet(clientManager,"totalAssets");
    totalLiabilities = getDataFromBalanceSheet(clientManager,"totalLiabilities");
    workingCapital = DataExtractor.subtractValues("workingCapital",totalAssets,totalLiabilities);

    averageTotalRevenue = DataExtractor.calculateMean(totalRevenue);


    operatingCashflow = getDataFromCashFlowStatement(clientManager,"operatingCashflow");




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
        return "CompanyFundamentalData{" + depreciation+"\n"+ capitalExpenditures+"\n"+ totalAssets+"\n"+ totalLiabilities+"\n"+workingCapital;



    }
}
