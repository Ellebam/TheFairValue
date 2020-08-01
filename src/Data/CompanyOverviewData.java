package Data;

import Controllers.AlphavantageAPIClient;
import Controllers.DataExtractor;

import java.util.ArrayList;

/**
 * This Class contains all Data on a Companies overview for the OverviewPanel
 */
public class CompanyOverviewData {

    private String Name;
    private String Country;
    private String Currency;
    private String Description;
    private String MarketCap;
    private String EBITDA;
    private String price2EarningsRatio;
    private String earningsPerShare;
    private String dividendsPerShare;
    private String dividendYield;
    private String payoutRatio;
    private String fiftytwoWeekHigh;
    private String fiftytwoWeekLow;
    private String bookValue;
    private String Sector;
    private ArrayList<StockPrice> historicalStockPrice;
    private static final String apiFunctionOVERVIEW = "OVERVIEW";
    private static final String apiFunctionTIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";

    /**
     * The Constructor will repeatedly call the extractData2String() method from DataExtractor to
     * update all its instance variables with the values found in the given API Function
     * @param symbol ticker symbol for desired company
     * @param apiKey user apiKey
     * @throws Exception
     */
    public CompanyOverviewData(String symbol, String apiKey) throws Exception{
        /*AlphavantageAPIClient OverviewFunctionClient = new AlphavantageAPIClient(apiFunctionOVERVIEW,symbol,apiKey);
        Name = getData(OverviewFunctionClient,"Name");
        Country = getData(OverviewFunctionClient,"Country");
        Currency = getData(OverviewFunctionClient,"Currency");
        Description = getData(OverviewFunctionClient,"Description");
        MarketCap = getData(OverviewFunctionClient, "MarketCapitalization");
        EBITDA = getData(OverviewFunctionClient, "EBITDA");
        price2EarningsRatio = getData(OverviewFunctionClient,"PERatio");
        earningsPerShare = getData(OverviewFunctionClient,"EPS");
        dividendsPerShare = getData(OverviewFunctionClient, "DividendPerShare");
        dividendYield = getData(OverviewFunctionClient,"DividendYield");
        payoutRatio = getData(OverviewFunctionClient, "PayoutRatio");
        fiftytwoWeekHigh = getData(OverviewFunctionClient, "52WeekHigh");
        fiftytwoWeekLow = getData(OverviewFunctionClient, "52WeekLow");
        bookValue = getData(OverviewFunctionClient, "BookValue");
        Sector = getData(OverviewFunctionClient, "Sector");*/

        AlphavantageAPIClient daylyTimeSeriesClient = new AlphavantageAPIClient(apiFunctionTIME_SERIES_DAILY_ADJUSTED
        , symbol,apiKey);

        String test = getData(daylyTimeSeriesClient,"2020-07-31");







    }



    public String getData(AlphavantageAPIClient client, String variableName){
        String variable =DataExtractor.extractOVERVIEWData(client,variableName);
        return variable;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        MarketCap = marketCap;
    }

    public String getEBITDA() {
        return EBITDA;
    }

    public void setEBITDA(String EBITDA) {
        this.EBITDA = EBITDA;
    }

    public String getPrice2EarningsRatio() {
        return price2EarningsRatio;
    }

    public void setPrice2EarningsRatio(String price2EarningsRatio) {
        this.price2EarningsRatio = price2EarningsRatio;
    }

    public String getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(String earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public String getDividendsPerShare() {
        return dividendsPerShare;
    }

    public void setDividendsPerShare(String dividendsPerShare) {
        this.dividendsPerShare = dividendsPerShare;
    }

    public String getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.dividendYield = dividendYield;
    }

    public String getPayoutRatio() {
        return payoutRatio;
    }

    public void setPayoutRatio(String payoutRatio) {
        this.payoutRatio = payoutRatio;
    }

    public String getFiftytwoWeekHigh() {
        return fiftytwoWeekHigh;
    }

    public void setFiftytwoWeekHigh(String fiftytwoWeekHigh) {
        this.fiftytwoWeekHigh = fiftytwoWeekHigh;
    }

    public String getFiftytwoWeekLow() {
        return fiftytwoWeekLow;
    }

    public void setFiftytwoWeekLow(String fiftytwoWeekLow) {
        this.fiftytwoWeekLow = fiftytwoWeekLow;
    }

    public String getBookValue() {
        return bookValue;
    }

    public void setBookValue(String bookValue) {
        this.bookValue = bookValue;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    @Override
    public String toString() {
        return "CompanyOverviewData{" +
                "Name='" + Name + '\'' + "\n"+
                ", Country='" + Country + '\'' +"\n"+
                ", Currency='" + Currency + '\'' +"\n"+
                ", Description='" + Description + '\'' +"\n"+
                ", MarketCap='" + MarketCap + '\'' +"\n"+
                ", EBITDA='" + EBITDA + '\'' +"\n"+
                ", price2EarningsRatio='" + price2EarningsRatio + '\'' +"\n"+
                ", earningsPerShare='" + earningsPerShare + '\'' +"\n"+
                ", dividendsPerShare='" + dividendsPerShare + '\'' +"\n"+
                ", dividendYield='" + dividendYield + '\'' +"\n"+
                ", payoutRatio='" + payoutRatio + '\'' +"\n"+
                ", fiftytwoWeekHigh='" + fiftytwoWeekHigh + '\'' +"\n"+
                ", fiftytwoWeekLow='" + fiftytwoWeekLow + '\'' +"\n"+
                ", bookValue='" + bookValue + '\'' +"\n"+
                ", Sector='" + Sector + '\'' +"\n"+
                '}';
    }
}
