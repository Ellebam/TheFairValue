package Data;

import Controllers.APIManager;
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
    private ArrayList<FinancialDataObject> historicalStockPrice;
    private APIManager APIManager;

    /**
     * The Constructor will repeatedly call the extractData2String() method from DataExtractor to
     * update all its instance variables with the values found in the given API Function
     * @throws Exception
     */
    public CompanyOverviewData(APIManager APIManager) throws Exception{
        this.APIManager = APIManager;

        Name = getData(APIManager,"Name");
        Country = getData(APIManager,"Country");
        Currency = getData(APIManager,"Currency");
        Description = getData(APIManager,"Description");
        MarketCap = getData(APIManager, "MarketCapitalization");
        EBITDA = getData(APIManager, "EBITDA");
        price2EarningsRatio = getData(APIManager,"PERatio");
        earningsPerShare = getData(APIManager,"EPS");
        dividendsPerShare = getData(APIManager, "DividendPerShare");
        dividendYield = getData(APIManager,"DividendYield");
        payoutRatio = getData(APIManager, "PayoutRatio");
        fiftytwoWeekHigh = getData(APIManager, "52WeekHigh");
        fiftytwoWeekLow = getData(APIManager, "52WeekLow");
        bookValue = getData(APIManager, "BookValue");
        Sector = getData(APIManager, "Sector");


        historicalStockPrice = DataExtractor.extractHistoricalStockPrices(APIManager);

    }



    public String getData(APIManager APIManager, String variableName){
        String variable =DataExtractor.extractOVERVIEWData(APIManager,variableName);
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

    public ArrayList<FinancialDataObject> getHistoricalStockPrice() {
        return historicalStockPrice;
    }

    public void setHistoricalStockPrice(ArrayList<FinancialDataObject> historicalStockPrice) {
        this.historicalStockPrice = historicalStockPrice;
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
