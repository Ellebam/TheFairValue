package Data;

import Controllers.ClientManager;
import Controllers.DataExtractor;


import java.time.LocalDate;
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
    private FinancialDataObject MarketCap;
    private FinancialDataObject EBITDA;
    private FinancialDataObject price2EarningsRatio;
    private FinancialDataObject earningsPerShare;
    private FinancialDataObject dividendsPerShare;
    private FinancialDataObject dividendYield;
    private FinancialDataObject payoutRatio;
    private FinancialDataObject fiftytwoWeekHigh;
    private FinancialDataObject fiftytwoWeekLow;
    private FinancialDataObject bookValue;
    private FinancialDataObject AnalystTargetPrice;
    private FinancialDataObject PriceToBookRatio;
    private FinancialDataObject PercentInsiders;
    private FinancialDataObject PercentInstitutions;
    private String Industry;
    private FinancialDataObject SharesOutstanding;
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

        Name = getDataString(ClientManager,"Name");
        Country = getDataString(ClientManager,"Country");
        Currency = getDataString(ClientManager,"Currency");
        Description = getDataString(ClientManager,"Description");
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
        Industry = getDataString(ClientManager, "Industry");
        SharesOutstanding = getData(clientManager,"SharesOutstanding");
        AnalystTargetPrice = getData(clientManager,"AnalystTargetPrice");
        PriceToBookRatio = getData(clientManager,"PriceToBookRatio");
        PercentInsiders = getData(clientManager,"PercentInsiders");
        PercentInstitutions = getData(clientManager,"PercentInstitutions");



        historicalStockPrice = DataExtractor.extractHistoricalStockPrices(ClientManager);

    }



    public FinancialDataObject getData(ClientManager ClientManager, String variableName){
        double value = Double.parseDouble(DataExtractor.extractOVERVIEWData(ClientManager,variableName));
         String date = LocalDate.now().toString();
        FinancialDataObject financialDataObject = new FinancialDataObject(variableName,value,date);
        return financialDataObject;
    }

    public String getDataString (ClientManager ClientManager, String variableName){
        String variable = DataExtractor.extractOVERVIEWData(ClientManager, variableName);
        return  variable;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public FinancialDataObject getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(FinancialDataObject marketCap) {
        MarketCap = marketCap;
    }

    public FinancialDataObject getEBITDA() {
        return EBITDA;
    }

    public void setEBITDA(FinancialDataObject EBITDA) {
        this.EBITDA = EBITDA;
    }

    public FinancialDataObject getPrice2EarningsRatio() {
        return price2EarningsRatio;
    }

    public void setPrice2EarningsRatio(FinancialDataObject price2EarningsRatio) {
        this.price2EarningsRatio = price2EarningsRatio;
    }

    public FinancialDataObject getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(FinancialDataObject earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public FinancialDataObject getDividendsPerShare() {
        return dividendsPerShare;
    }

    public void setDividendsPerShare(FinancialDataObject dividendsPerShare) {
        this.dividendsPerShare = dividendsPerShare;
    }

    public FinancialDataObject getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(FinancialDataObject dividendYield) {
        this.dividendYield = dividendYield;
    }

    public FinancialDataObject getPayoutRatio() {
        return payoutRatio;
    }

    public void setPayoutRatio(FinancialDataObject payoutRatio) {
        this.payoutRatio = payoutRatio;
    }

    public FinancialDataObject getFiftytwoWeekHigh() {
        return fiftytwoWeekHigh;
    }

    public void setFiftytwoWeekHigh(FinancialDataObject fiftytwoWeekHigh) {
        this.fiftytwoWeekHigh = fiftytwoWeekHigh;
    }

    public FinancialDataObject getFiftytwoWeekLow() {
        return fiftytwoWeekLow;
    }

    public void setFiftytwoWeekLow(FinancialDataObject fiftytwoWeekLow) {
        this.fiftytwoWeekLow = fiftytwoWeekLow;
    }

    public FinancialDataObject getBookValue() {
        return bookValue;
    }

    public void setBookValue(FinancialDataObject bookValue) {
        this.bookValue = bookValue;
    }

    public String getIndustry() {
        return Industry;
    }

    public FinancialDataObject getSharesOutstanding() {
        return SharesOutstanding;
    }

    public void setSharesOutstanding(FinancialDataObject sharesOutstanding) {
        SharesOutstanding = sharesOutstanding;
    }

    public ArrayList<FinancialDataObject> getHistoricalStockPrice() {
        return historicalStockPrice;
    }


    public FinancialDataObject getAnalystTargetPrice() {
        return AnalystTargetPrice;
    }

    public void setAnalystTargetPrice(FinancialDataObject analystTargetPrice) {
        AnalystTargetPrice = analystTargetPrice;
    }

    public FinancialDataObject getPriceToBookRatio() {
        return PriceToBookRatio;
    }

    public void setPriceToBookRatio(FinancialDataObject priceToBookRatio) {
        PriceToBookRatio = priceToBookRatio;
    }

    public FinancialDataObject getPercentInsiders() {
        return PercentInsiders;
    }

    public void setPercentInsiders(FinancialDataObject percentInsiders) {
        PercentInsiders = percentInsiders;
    }

    public FinancialDataObject getPercentInstitutions() {
        return PercentInstitutions;
    }

    public void setPercentInstitutions(FinancialDataObject percentInstitutions) {
        PercentInstitutions = percentInstitutions;
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
                ", Sector='" + Industry + '\'' +"\n"+
                '}'+ historicalStockPrice+"\n";
    }

}
