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
    private ArrayList<FinancialDataObject> freeCashFlow;
    private ArrayList<FinancialDataObject> payOutRatio;
    private ArrayList<FinancialDataObject> dividendPerShare;
    private ArrayList<FinancialDataObject> dividendYield;
    private ClientManager clientManager;


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



}

    ArrayList<FinancialDataObject> getDataFromBalanceSheet(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractBalanceSheetData(variableName, clientManager);
    return variable;
}

    ArrayList<FinancialDataObject> getDataFromIncomeStatement(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractIncomeStatementData(variableName, clientManager);
        return variable;
    }


    @Override
    public String toString() {
        return "CompanyFundamentalData{" + "\n"+
                "totalRevenue=" + totalRevenue +"\n"+
                ", netIncome=" + netIncome +"\n"+
                ", grossProfit=" + grossProfit +"\n"+
                ", grossMargin=" + grossMargin +
                '}';
    }
}
