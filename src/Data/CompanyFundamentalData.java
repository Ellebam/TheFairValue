package Data;

import Controllers.ClientManager;
import Controllers.DataContainerManager;
import Controllers.DataExtractor;

import java.util.ArrayList;


/**
 * This is the DataContainer-Class for creating and storing of fundamental data of a company.
 * Most of its instance variables represent ArrayLists which containing FinancialDataObjects of the data which they are
 * named to.
 */
public class CompanyFundamentalData {

    private CompanyFundamentalData companyFundamentalData;
    private ArrayList<FinancialDataObject> totalRevenue;
    private ArrayList<FinancialDataObject> incomeBeforeTax;
    private ArrayList<FinancialDataObject> netIncome;
    private ArrayList<FinancialDataObject> grossProfit;
    private ArrayList<FinancialDataObject> ebit;
    private ArrayList<FinancialDataObject> grossMargin;
    private ArrayList<FinancialDataObject> operatingIncome;
    private ArrayList<FinancialDataObject> operatingMargin;
    private ArrayList<FinancialDataObject> netMargin;
    private ArrayList<FinancialDataObject> depreciation;
    private ArrayList<FinancialDataObject> capitalExpenditures;
    private ArrayList<FinancialDataObject> totalAssets;
    private ArrayList<FinancialDataObject> totalLiabilities;
    private ArrayList<FinancialDataObject> workingCapital;
    private ArrayList<FinancialDataObject> interestExpense;
    private ArrayList<FinancialDataObject> taxProvision;
    private ArrayList<FinancialDataObject> netInterestIncome;
    private ArrayList<FinancialDataObject> extraordinaryItems;
    private ArrayList<FinancialDataObject> incomeTaxExpense;
    private ArrayList<FinancialDataObject> freeCashFlow;
    private ArrayList<FinancialDataObject> dividendPayout;
    private ArrayList<FinancialDataObject> commonStockSharesOutstanding;
    private ArrayList<FinancialDataObject> dividendsPerShare;
    private ArrayList<FinancialDataObject> netIncomeApplicableToCommonShares;
    private ArrayList<FinancialDataObject> earningsPerShare;
    private ArrayList<FinancialDataObject> dividendYield;
    private ArrayList<FinancialDataObject> payOutRatio;
    private ArrayList<FinancialDataObject> operatingCashflow;
    private ArrayList<FinancialDataObject> cash;
    private ArrayList<FinancialDataObject>  debtRatio;
    private ArrayList<FinancialDataObject>  longTermDebt;
    private ArrayList<FinancialDataObject>  shortTermDebt;
    private ArrayList<FinancialDataObject> longTermInvestments;
    private ArrayList<FinancialDataObject> shortTermInvestments;
    private ArrayList<FinancialDataObject> longDebtToInvestmentsRatio;
    private ArrayList<FinancialDataObject> shortDebtToInvestmentsRatio;




    private ClientManager clientManager;


    /**
     * The Constructor for this class will set up all instance variables using either one of the 3 class methods to get
     * data directly from the raw datasheet (Income-Statement, Balance-Sheet, cashflow statement from the APIClients in
     * ClientManager) or use one of the DataExtractor static methods to calculate certain values (e.g. gross margin).
     * @param clientManager Object in which all API-Connections are established an all raw data objects are created and stored
     * @param dataContainerManager Object containing all DataClasses (like this one)
     * @throws Exception might throw a nullpointer Exception when data limit of API is reached (max 5 calls per minute)
     */
    public CompanyFundamentalData(ClientManager clientManager, DataContainerManager dataContainerManager) throws Exception{
    companyFundamentalData = this;
    this.clientManager = clientManager;
    totalRevenue = getDataFromIncomeStatement(clientManager,"totalRevenue");
    incomeBeforeTax = getDataFromIncomeStatement(clientManager,"incomeBeforeTax");
    netIncome = getDataFromIncomeStatement(clientManager,"netIncome");
    grossProfit = getDataFromIncomeStatement(clientManager,"grossProfit");
    ebit = getDataFromIncomeStatement(clientManager,"ebit");
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
    interestExpense = getDataFromIncomeStatement(clientManager,"interestExpense");
    taxProvision =getDataFromIncomeStatement(clientManager,"taxProvision");
    netInterestIncome = getDataFromIncomeStatement(clientManager,"netInterestIncome");
    extraordinaryItems = getDataFromIncomeStatement(clientManager,"extraordinaryItems");
    incomeTaxExpense = getDataFromIncomeStatement(clientManager,"incomeTaxExpense");
    freeCashFlow = DataExtractor.subtractTwoValues("freeCashFlow",operatingCashflow,capitalExpenditures);
    dividendPayout = getDataFromCashFlowStatement(clientManager,"dividendPayout");
    commonStockSharesOutstanding = getDataFromBalanceSheet(clientManager,"commonStockSharesOutstanding");
    dividendsPerShare = DataExtractor.calculateDividendsPerShare("dividendsPerShare",dividendPayout,commonStockSharesOutstanding);
    netIncomeApplicableToCommonShares = getDataFromIncomeStatement(clientManager,"netIncomeApplicableToCommonShares");
    earningsPerShare = DataExtractor.calculateMargins("earningsPerShare",netIncomeApplicableToCommonShares,commonStockSharesOutstanding);
    payOutRatio =DataExtractor.calculateMargins_IN_PERCENT("payoutRatio",dividendsPerShare,earningsPerShare);
    dividendYield = DataExtractor.calculateDividendYield("dividendYield",dividendsPerShare,
            dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice());
    cash = getDataFromBalanceSheet(clientManager,"cash");
    debtRatio = DataExtractor.calculateMargins_IN_PERCENT("debtRatio",totalLiabilities,totalAssets);
    longTermDebt = getDataFromBalanceSheet(clientManager,"longTermDebt");
    shortTermDebt = getDataFromBalanceSheet(clientManager,"shortTermDebt");
    longTermInvestments = getDataFromBalanceSheet(clientManager,"longTermInvestments");
    shortTermInvestments = getDataFromBalanceSheet(clientManager,"shortTermInvestments");
    longDebtToInvestmentsRatio =DataExtractor.calculateMargins("longTermDebtToInvestmentsRatio",longTermDebt,
            longTermInvestments);
    shortDebtToInvestmentsRatio =DataExtractor.calculateMargins("shortTermDebtToInvestmentsRatio",shortTermDebt,
            shortTermInvestments);














}



    /**
     * Method for accessing data from the Balance sheet from the corresponding AlphavantageAPIClient
     * @param clientManager object containing all data clients (here we access the BalanceSheetClient)
     * @param variableName key for finding certain data (e.g. "totalAssets")
     * @return returns an ArrayList<FinancialDataObject> containing all searched data with values and dates
     */
    ArrayList<FinancialDataObject> getDataFromBalanceSheet(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractBalanceSheetData(variableName, clientManager);
    return variable;
}

    /**
     * Same method as getDataFromBalanceSheet but for the IncomeStatementClient
     * @param clientManager object containing all data clients (here we access the BalanceSheetClient)
     * @param variableName key for finding certain data (e.g. "totalAssets")
     * @return returns an ArrayList<FinancialDataObject> containing all searched data with values and dates
     */
    ArrayList<FinancialDataObject> getDataFromIncomeStatement(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractIncomeStatementData(variableName, clientManager);
        return variable;
    }

    /**
     * Same method as getDataFromBalanceSheet but for the CashFlowStatementClient
     * @param clientManager object containing all data clients (here we access the BalanceSheetClient)
     * @param variableName key for finding certain data (e.g. "totalAssets")
     * @return returns an ArrayList<FinancialDataObject> containing all searched data with values and dates
     */
    ArrayList<FinancialDataObject> getDataFromCashFlowStatement(ClientManager clientManager,String variableName){
        ArrayList<FinancialDataObject> variable = DataExtractor.extractCashFlowData(variableName, clientManager);
        return variable;
    }


    public ArrayList<FinancialDataObject> getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(ArrayList<FinancialDataObject> totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public ArrayList<FinancialDataObject> getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(ArrayList<FinancialDataObject> netIncome) {
        this.netIncome = netIncome;
    }

    public ArrayList<FinancialDataObject> getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(ArrayList<FinancialDataObject> grossProfit) {
        this.grossProfit = grossProfit;
    }

    public ArrayList<FinancialDataObject> getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(ArrayList<FinancialDataObject> grossMargin) {
        this.grossMargin = grossMargin;
    }

    public ArrayList<FinancialDataObject> getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(ArrayList<FinancialDataObject> operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public ArrayList<FinancialDataObject> getOperatingMargin() {
        return operatingMargin;
    }

    public void setOperatingMargin(ArrayList<FinancialDataObject> operatingMargin) {
        this.operatingMargin = operatingMargin;
    }

    public ArrayList<FinancialDataObject> getNetMargin() {
        return netMargin;
    }

    public void setNetMargin(ArrayList<FinancialDataObject> netMargin) {
        this.netMargin = netMargin;
    }

    public ArrayList<FinancialDataObject> getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(ArrayList<FinancialDataObject> depreciation) {
        this.depreciation = depreciation;
    }

    public ArrayList<FinancialDataObject> getCapitalExpenditures() {
        return capitalExpenditures;
    }

    public void setCapitalExpenditures(ArrayList<FinancialDataObject> capitalExpenditures) {
        this.capitalExpenditures = capitalExpenditures;
    }

    public ArrayList<FinancialDataObject> getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(ArrayList<FinancialDataObject> totalAssets) {
        this.totalAssets = totalAssets;
    }

    public ArrayList<FinancialDataObject> getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(ArrayList<FinancialDataObject> totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public ArrayList<FinancialDataObject> getWorkingCapital() {
        return workingCapital;
    }

    public void setWorkingCapital(ArrayList<FinancialDataObject> workingCapital) {
        this.workingCapital = workingCapital;
    }

    public ArrayList<FinancialDataObject> getFreeCashFlow() {
        return freeCashFlow;
    }

    public void setFreeCashFlow(ArrayList<FinancialDataObject> freeCashFlow) {
        this.freeCashFlow = freeCashFlow;
    }

    public ArrayList<FinancialDataObject> getDividendPayout() {
        return dividendPayout;
    }

    public void setDividendPayout(ArrayList<FinancialDataObject> dividendPayout) {
        this.dividendPayout = dividendPayout;
    }

    public ArrayList<FinancialDataObject> getCommonStockSharesOutstanding() {
        return commonStockSharesOutstanding;
    }

    public void setCommonStockSharesOutstanding(ArrayList<FinancialDataObject> commonStockSharesOutstanding) {
        this.commonStockSharesOutstanding = commonStockSharesOutstanding;
    }

    public ArrayList<FinancialDataObject> getDividendsPerShare() {
        return dividendsPerShare;
    }

    public void setDividendsPerShare(ArrayList<FinancialDataObject> dividendsPerShare) {
        this.dividendsPerShare = dividendsPerShare;
    }

    public ArrayList<FinancialDataObject> getNetIncomeApplicableToCommonShares() {
        return netIncomeApplicableToCommonShares;
    }

    public void setNetIncomeApplicableToCommonShares(ArrayList<FinancialDataObject> netIncomeApplicableToCommonShares) {
        this.netIncomeApplicableToCommonShares = netIncomeApplicableToCommonShares;
    }

    public ArrayList<FinancialDataObject> getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(ArrayList<FinancialDataObject> earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public ArrayList<FinancialDataObject> getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(ArrayList<FinancialDataObject> dividendYield) {
        this.dividendYield = dividendYield;
    }

    public ArrayList<FinancialDataObject> getPayOutRatio() {
        return payOutRatio;
    }

    public void setPayOutRatio(ArrayList<FinancialDataObject> payOutRatio) {
        this.payOutRatio = payOutRatio;
    }

    public ArrayList<FinancialDataObject> getOperatingCashflow() {
        return operatingCashflow;
    }

    public void setOperatingCashflow(ArrayList<FinancialDataObject> operatingCashflow) {
        this.operatingCashflow = operatingCashflow;
    }

    public ArrayList<FinancialDataObject> getCash() {
        return cash;
    }

    public void setCash(ArrayList<FinancialDataObject> cash) {
        this.cash = cash;
    }

    public ArrayList<FinancialDataObject> getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(ArrayList<FinancialDataObject> debtRatio) {
        this.debtRatio = debtRatio;
    }

    public ArrayList<FinancialDataObject> getLongTermDebt() {
        return longTermDebt;
    }

    public void setLongTermDebt(ArrayList<FinancialDataObject> longTermDebt) {
        this.longTermDebt = longTermDebt;
    }

    public ArrayList<FinancialDataObject> getShortTermDebt() {
        return shortTermDebt;
    }

    public void setShortTermDebt(ArrayList<FinancialDataObject> shortTermDebt) {
        this.shortTermDebt = shortTermDebt;
    }

    public ArrayList<FinancialDataObject> getLongTermInvestments() {
        return longTermInvestments;
    }

    public void setLongTermInvestments(ArrayList<FinancialDataObject> longTermInvestments) {
        this.longTermInvestments = longTermInvestments;
    }

    public ArrayList<FinancialDataObject> getShortTermInvestments() {
        return shortTermInvestments;
    }

    public void setShortTermInvestments(ArrayList<FinancialDataObject> shortTermInvestments) {
        this.shortTermInvestments = shortTermInvestments;
    }

    public ArrayList<FinancialDataObject> getLongDebtToInvestmentsRatio() {
        return longDebtToInvestmentsRatio;
    }

    public void setLongDebtToInvestmentsRatio(ArrayList<FinancialDataObject> longDebtToInvestmentsRatio) {
        this.longDebtToInvestmentsRatio = longDebtToInvestmentsRatio;
    }

    public ArrayList<FinancialDataObject> getShortDebtToInvestmentsRatio() {
        return shortDebtToInvestmentsRatio;
    }

    public void setShortDebtToInvestmentsRatio(ArrayList<FinancialDataObject> shortDebtToInvestmentsRatio) {
        this.shortDebtToInvestmentsRatio = shortDebtToInvestmentsRatio;
    }


    public ArrayList<FinancialDataObject> getInterestExpense() {
        return interestExpense;
    }

    public void setInterestExpense(ArrayList<FinancialDataObject> interestExpense) {
        this.interestExpense = interestExpense;
    }

    public ArrayList<FinancialDataObject> getTaxProvision() {
        return taxProvision;
    }

    public void setTaxProvision(ArrayList<FinancialDataObject> taxProvision) {
        this.taxProvision = taxProvision;
    }

    public ArrayList<FinancialDataObject> getNetInterestIncome() {
        return netInterestIncome;
    }

    public void setNetInterestIncome(ArrayList<FinancialDataObject> netInterestIncome) {
        this.netInterestIncome = netInterestIncome;
    }

    public ArrayList<FinancialDataObject> getExtraordinaryItems() {
        return extraordinaryItems;
    }

    public void setExtraordinaryItems(ArrayList<FinancialDataObject> extraordinaryItems) {
        this.extraordinaryItems = extraordinaryItems;
    }

    public ArrayList<FinancialDataObject> getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    public void setIncomeTaxExpense(ArrayList<FinancialDataObject> incomeTaxExpense) {
        this.incomeTaxExpense = incomeTaxExpense;
    }

    @Override
    public String toString() {
        return "CompanyFundamentalData{" +"\n"+
                "totalRevenue=" + totalRevenue +"\n"+
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
                ", interestExpense=" + interestExpense +"\n"+
                ", taxProvision=" + taxProvision +"\n"+
                ", netInterestIncome=" + netInterestIncome +"\n"+
                ", extraordinaryItems=" + extraordinaryItems +"\n"+
                ", incomeTaxExpense=" + incomeTaxExpense +"\n"+
                ", freeCashFlow=" + freeCashFlow +"\n"+
                ", dividendPayout=" + dividendPayout +"\n"+
                ", commonStockSharesOutstanding=" + commonStockSharesOutstanding +"\n"+
                ", dividendsPerShare=" + dividendsPerShare +"\n"+
                ", netIncomeApplicableToCommonShares=" + netIncomeApplicableToCommonShares +"\n"+
                ", earningsPerShare=" + earningsPerShare +"\n"+
                ", dividendYield=" + dividendYield +"\n"+
                ", payOutRatio=" + payOutRatio +"\n"+
                ", operatingCashflow=" + operatingCashflow +"\n"+
                ", cash=" + cash +"\n"+
                ", debtRatio=" + debtRatio +"\n"+
                ", longTermDebt=" + longTermDebt +"\n"+
                ", shortTermDebt=" + shortTermDebt +"\n"+
                ", longTermInvestments=" + longTermInvestments +"\n"+
                ", shortTermInvestments=" + shortTermInvestments +"\n"+
                ", longDebtToInvestmentsRatio=" + longDebtToInvestmentsRatio +"\n"+
                ", shortDebtToInvestmentsRatio=" + shortDebtToInvestmentsRatio +"\n"+
                '}';
    }
}
