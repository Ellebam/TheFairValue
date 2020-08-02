package Controllers;

public class ClientManager {
    private String apiKey;
    private String symbol;
    private static final String apiFunctionOVERVIEW ="OVERVIEW";
    private static final String apiFunctionTIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    private static final String apiFunctionINCOME_STATEMENT= "INCOME_STATEMENT";
    private static final String apiFunctionBALANCE_SHEET = "BALANCE_SHEET";
    private static final String apiFunctionSECTOR = "SECTOR";
    private AlphavantageAPIClient overviewClient;
    private AlphavantageAPIClient dailyTimeSeriesClient;
    private AlphavantageAPIClient incomeStatementClient;
    private AlphavantageAPIClient balanceSheetClient;
    private AlphavantageAPIClient sectorPerformanceClient;


    public ClientManager(String symbol, String apiKey) throws Exception{
        this.symbol = symbol;
        this.apiKey = apiKey;
        overviewClient = new AlphavantageAPIClient(apiFunctionOVERVIEW,symbol,apiKey);
        dailyTimeSeriesClient = new AlphavantageAPIClient(apiFunctionTIME_SERIES_DAILY_ADJUSTED,symbol,apiKey);
        incomeStatementClient = new AlphavantageAPIClient(apiFunctionINCOME_STATEMENT,symbol,apiKey);
        balanceSheetClient = new AlphavantageAPIClient(apiFunctionBALANCE_SHEET,symbol,apiKey);
        sectorPerformanceClient = new AlphavantageAPIClient(apiFunctionSECTOR,symbol,apiKey);
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

    public AlphavantageAPIClient getSectorPerformanceClient() {
        return sectorPerformanceClient;
    }

    public void setSectorPerformanceClient(AlphavantageAPIClient sectorPerformanceClient) {
        this.sectorPerformanceClient = sectorPerformanceClient;
    }
}
