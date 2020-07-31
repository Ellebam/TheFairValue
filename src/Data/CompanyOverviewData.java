package Data;

import Controllers.AlphavantageAPIClient;
import Controllers.DataExtractor;
import sun.security.krb5.internal.crypto.Des;

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
    private static final String apiFunction = "OVERVIEW";

    /**
     * The Constructor will repeatedly call the extractData2String() method from DataExtractor to
     * update all its instance variables with the values found in the given API Function
     * @param symbol ticker symbol for desired company
     * @param apiKey user apiKey
     * @throws Exception
     */
    public CompanyOverviewData(String symbol, String apiKey) throws Exception{
        AlphavantageAPIClient client = new AlphavantageAPIClient(apiFunction,symbol,apiKey);
        Name = getData(client,"Name");
        Country = getData(client,"Country");
        Currency = getData(client,"Currency");
        Description = getData(client,"Description");
        MarketCap = getData(client, "MarketCapitalization");
        EBITDA = getData(client, "EBITDA");
        price2EarningsRatio = getData(client,"PERatio");
        earningsPerShare = getData(client,"EPS");
        dividendsPerShare = getData(client, "DividendPerShare");
        dividendYield = getData(client,"DividendYield");
        payoutRatio = getData(client, "PayoutRatio");
        fiftytwoWeekHigh = getData(client, "52WeekHigh");
        fiftytwoWeekLow = getData(client, "52WeekLow");
        bookValue = getData(client, "BookValue");
        Sector = getData(client, "Sector");



        /*Name = DataExtractor.extractData2String(client,"Name");
        Country = DataExtractor.extractData2String(client,"Country");
        Currency = DataExtractor.extractData2String(client,"Currency");
        Description = DataExtractor.extractData2String(client,"Description");
        MarketCap = DataExtractor.extractData2String(client,"MarketCapitalization");
        EBITDA = DataExtractor.extractData2String(client,"MarketCapitalization");*/


        /*Currency = client.getResponse().getBody().getObject().getString("Currency");
        Country = client.getResponse().getBody().getObject().getString("Country");
        sector = client.getResponse().getBody().getObject().getString("Sector");*/



    }



    public String getData(AlphavantageAPIClient client, String variableName){
        String variable =DataExtractor.extractData2String(client,variableName);
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
