package Data;

import Controllers.ClientManager;
import Controllers.DataExtractor;


import java.util.ArrayList;

/**
 * This Class contains all Data on a Companies overview for the OverviewPanel
 */
public class CompanyOverviewData {

    private CompanyOverviewData companyOverviewData;
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
    private String SharesOutstanding;
    private ArrayList<FinancialDataObject> historicalStockPrice;
    private ClientManager clientManager;


    /**
     * The Constructor will repeatedly call the extractData2String() method from DataExtractor to
     * update all its instance variables with the values found in the given API Function
     * @throws Exception
     */
    public CompanyOverviewData(ClientManager ClientManager) throws Exception{
        companyOverviewData = this;
        this.clientManager = ClientManager;

        Name = getData(ClientManager,"Name");
        Country = getData(ClientManager,"Country");
        Currency = getData(ClientManager,"Currency");
        Description = getData(ClientManager,"Description");
        MarketCap = getData(ClientManager, "MarketCapitalization");
        EBITDA = getData(ClientManager, "EBITDA");
        price2EarningsRatio = getData(ClientManager,"PERatio");
        earningsPerShare = getData(ClientManager,"EPS");
        dividendsPerShare = getData(ClientManager, "DividendPerShare");
        dividendYield = getData(ClientManager,"DividendYield");
        payoutRatio = getData(ClientManager, "PayoutRatio");
        fiftytwoWeekHigh = getData(ClientManager, "52WeekHigh");
        fiftytwoWeekLow = getData(ClientManager, "52WeekLow");
        bookValue = getData(ClientManager, "BookValue");
        Sector = getData(ClientManager, "Sector");
        SharesOutstanding = getData(clientManager,"SharesOutstanding");


        historicalStockPrice = DataExtractor.extractHistoricalStockPrices(ClientManager);

    }



    public String getData(ClientManager ClientManager, String variableName){
        String variable =DataExtractor.extractOVERVIEWData(ClientManager,variableName);
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

    public String getSharesOutstanding() {
        return SharesOutstanding;
    }

    public void setSharesOutstanding(String sharesOutstanding) {
        SharesOutstanding = sharesOutstanding;
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
                '}'+ historicalStockPrice+"\n";
    }

}
