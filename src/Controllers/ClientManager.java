package Controllers;


/**
 * ClientManager represents the container object for all needed connections built by the AlphavantageAPIClient Class.
 * The final instance variables are named after the API endpoints.
 */
public class ClientManager {
    private String apiKey;
    private String symbol;
    private static final String apiFunctionOVERVIEW ="OVERVIEW";
    private static final String apiFunctionTIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    private static final String apiFunctionINCOME_STATEMENT= "INCOME_STATEMENT";
    private static final String apiFunctionBALANCE_SHEET = "BALANCE_SHEET";
    private static final String apiFunctionCASH_FLOW ="CASH_FLOW";

    private AlphavantageAPIClient overviewClient;
    private AlphavantageAPIClient dailyTimeSeriesClient;
    private AlphavantageAPIClient incomeStatementClient;
    private AlphavantageAPIClient balanceSheetClient;
    private AlphavantageAPIClient cashFlowClient;


    /**
     * The Constructor will build 5 different clientobjects which will be used for data retrieval by the DataExtractor class.
     * This process is run through for every request a user places through text input.
     * @param symbol stock ticker symbol of the searched company
     * @param apiKey key for API-Database
     * @throws Exception will be caught at UI level when no ClientManager can be built (network problems or maximum number
     * of user requests per minute exceeded
     */
    public ClientManager(String symbol, String apiKey) throws Exception{
        this.symbol = symbol;
        this.apiKey = apiKey;
        overviewClient = new AlphavantageAPIClient(apiFunctionOVERVIEW,symbol,apiKey);
        dailyTimeSeriesClient = new AlphavantageAPIClient(apiFunctionTIME_SERIES_DAILY_ADJUSTED,symbol,apiKey);
        incomeStatementClient = new AlphavantageAPIClient(apiFunctionINCOME_STATEMENT,symbol,apiKey);
        balanceSheetClient = new AlphavantageAPIClient(apiFunctionBALANCE_SHEET,symbol,apiKey);
        cashFlowClient = new AlphavantageAPIClient(apiFunctionCASH_FLOW,symbol,apiKey);
    }


    public AlphavantageAPIClient getOverviewClient() {
        return overviewClient;
    }

    public void setOverviewClient(AlphavantageAPIClient overviewClient) {
        this.overviewClient = overviewClient;
    }

    public AlphavantageAPIClient getDailyTimeSeriesClient() {
        return dailyTimeSeriesClient;
    }

    public void setDailyTimeSeriesClient(AlphavantageAPIClient dailyTimeSeriesClient) {
        this.dailyTimeSeriesClient = dailyTimeSeriesClient;
    }

    public AlphavantageAPIClient getIncomeStatementClient() {
        return incomeStatementClient;
    }

    public void setIncomeStatementClient(AlphavantageAPIClient incomeStatementClient) {
        this.incomeStatementClient = incomeStatementClient;
    }

    public AlphavantageAPIClient getBalanceSheetClient() {
        return balanceSheetClient;
    }

    public void setBalanceSheetClient(AlphavantageAPIClient balanceSheetClient) {
        this.balanceSheetClient = balanceSheetClient;
    }


    public AlphavantageAPIClient getCashFlowClient() {
        return cashFlowClient;
    }

    public void setCashFlowClient(AlphavantageAPIClient cashFlowClient) {
        this.cashFlowClient = cashFlowClient;
    }
}
