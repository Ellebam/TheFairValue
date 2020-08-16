package Controllers;


import Data.FinancialDataObject;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public  class DataExtractor {

    /**
     * Static method for extracting Data from the JSON Objects created by the AlphavantageAPIClient.
     * This method is used by the  CompanyOverviewData Class to access data from simple JSON objects
     *
     * @param ClientManager       used APIMAnager containing the AlphavantageApiClient(OVERVIEW) object
     * @param variableName variable that needs to be searched for inside JSON Object
     * @return String countaining desired data
     */
    public static String extractOVERVIEWData(ClientManager ClientManager, String variableName) {
        String dataVariable = ClientManager.getOverviewClient().getResponse().getBody().getObject().getString(variableName);
        return dataVariable;
    }

    /**
     * This constructor will take an AlphavantageAPIClient and search for the "Time Series (Daily)" key. After getting that key
     * it will iterate through all entries (dates) while saving the corresponding adjusted close price values to a double.
     * The date and the value are then used to create a StockPrice object which is the added to an ArrayList<StockPrice>
     *     which is also returned. This
     * @param ClientManager used APIManager containing the  AlphavantageAPIClient used for this operation
     *                   (APIFunction has to be "TIME_SERIES_DAILY_ADJUSTED")
     * @return ArrayList containing all historical Stock Prices
     */
     public static ArrayList<FinancialDataObject> extractHistoricalStockPrices(ClientManager ClientManager) {
         ArrayList<FinancialDataObject> stockPriceList = new ArrayList<>();
         JSONObject jsonObject = ClientManager.getDailyTimeSeriesClient().getResponse().getBody().getObject().getJSONObject("Time Series (Daily)");
         Iterator<String> keys = jsonObject.keys();
         while (keys.hasNext()){
             String name = "Stock Price";
             String key = keys.next();
             JSONObject value = jsonObject.getJSONObject(key);
             String adjustedClose = value.getString("5. adjusted close");
             FinancialDataObject stockPrice = new FinancialDataObject(name,Double.parseDouble(adjustedClose),key);
             stockPriceList.add(stockPrice);
         }
         return stockPriceList;
     }

    /**
     * Main method used to extract Data from the generated Income Satement JSON-Object. It iterates through the JSON File
     * under the Subnode "quarterly reports" (more avaiable data than "yearly" reports) and will extract all found values
     * + their corresponding dates as a double (value) and a string (date) into a newly created FinancialDataObject inside
     * a container Arraylist (which it will return). If the result of the found value is "none" it will save a zero value.
     * @param keyName   key to find the searching value inside JSON File
     * @param ClientManager object for API connection
     * @return ArrayList containing all FinancialDataObjects containing requested data
     */
     public static ArrayList<FinancialDataObject> extractIncomeStatementData(String keyName, ClientManager ClientManager){
         ArrayList<FinancialDataObject> IncomeStatementDataList = new ArrayList<>();
         JSONArray jsonArray= ClientManager.getIncomeStatementClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
         for(int i=0; i<jsonArray.length();i++) {
             String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

             if (!(jsonArray.getJSONObject(i).getString(keyName).equals("None"))) {
                 Double value = jsonArray.getJSONObject(i).getDouble(keyName);
                 FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                 IncomeStatementDataList.add(dataObject);
             } else {
                 Double value = 0.00000;
                 FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                 IncomeStatementDataList.add(dataObject);
             }
         }
         return IncomeStatementDataList;
     }

    /**
     * Same method as extractIncomeStatementData() but with the "BalanceSheet" functionality as its source
     * @param keyName key to find the searching value inside JSON File
     * @param ClientManager object for API connection
     * @return ArrayList containing all FinancialDataObjects containing requested data
     */
    public static ArrayList<FinancialDataObject> extractBalanceSheetData(String keyName, ClientManager ClientManager){
        ArrayList<FinancialDataObject> BalanceSheetDataList = new ArrayList<>();
        JSONArray jsonArray= ClientManager.getBalanceSheetClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
        for(int i=0; i<jsonArray.length();i++) {
            String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

            if (!(jsonArray.getJSONObject(i).getString(keyName).equals("None"))) {
                Double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                BalanceSheetDataList.add(dataObject);
            }else{
                Double value = 0.00000;
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                BalanceSheetDataList.add(dataObject);
            }
        }
        return BalanceSheetDataList;
    }

    /**
     * Same method as extractIncomeStatementData() but with the "CashFlow" functionality as its source
     * @param keyName key to find the searching value inside JSON File
     * @param ClientManager object for API connection
     * @return ArrayList containing all FinancialDataObjects containing requested data
     */
    public static ArrayList<FinancialDataObject> extractCashFlowData(String keyName, ClientManager ClientManager){
        ArrayList<FinancialDataObject> CashFlowDataList = new ArrayList<>();
        JSONArray jsonArray= ClientManager.getCashFlowClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
        for(int i=0; i<jsonArray.length();i++) {
            String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

            if (!(jsonArray.getJSONObject(i).getString(keyName).equals("None"))) {
                Double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                CashFlowDataList.add(dataObject);
            }else{
                Double value = 0.00000;
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                CashFlowDataList.add(dataObject);
            }
        }
        return CashFlowDataList;
    }



    /**
     * Used for calculating the margin of two given values. It will take two ArrayLists containing FinancialDataObjects
     * and iterate through both while calculating the margins of the values in the same position of their corresponding
     * ArrayList.
     * @param marginName name of the new DataList
     * @param sampleProfit the dividend
     * @param sampleRevenue the divisor
     * @return ArrayList containing FinancialDataObjects with quotients of the divisions as values
     */
    public static ArrayList<FinancialDataObject> calculateMargins(String marginName, ArrayList<FinancialDataObject> sampleProfit,
                                                                             ArrayList<FinancialDataObject> sampleRevenue){
        ArrayList<FinancialDataObject> marginList = new ArrayList<>();
        if (sampleProfit.size() == sampleRevenue.size()){
            for (int i=0; i<sampleProfit.size();i++){
                double sampleProfitDataPoint = sampleProfit.get(i).getValue();
                double sampleRevenueDataPoint = sampleRevenue.get(i).getValue();
                if (!(sampleRevenueDataPoint==0.00000)) {
                    Double value = sampleProfitDataPoint / sampleRevenueDataPoint;
                    String date = sampleProfit.get(i).getDate();
                    FinancialDataObject marginObject = new FinancialDataObject(marginName, value, date);
                    marginList.add(marginObject);
                }
            }
        }else{JOptionPane.showMessageDialog(null,"Error!");}
        return  marginList;
    }

    /**
     * Same method as calculateMargins() but with percent values
     * @param marginName name of the new DataList
     * @param sampleProfit the dividend
     * @param sampleRevenue the divisor
     * @return ArrayList containing FinancialDataObjects with quotients of the divisions as values
     */
    public static ArrayList<FinancialDataObject> calculateMargins_IN_PERCENT(String marginName, ArrayList<FinancialDataObject> sampleProfit,
                                                                             ArrayList<FinancialDataObject> sampleRevenue){
        ArrayList<FinancialDataObject> marginList = new ArrayList<>();
        if (sampleProfit.size() == sampleRevenue.size()){
            for (int i=0; i<sampleProfit.size();i++){
                double sampleProfitDataPoint = sampleProfit.get(i).getValue();
                double sampleRevenueDataPoint = sampleRevenue.get(i).getValue();
                if (!(sampleRevenueDataPoint==0.00000)) {
                    Double value = (sampleProfitDataPoint / sampleRevenueDataPoint) * 100;
                    String date = sampleProfit.get(i).getDate();
                    FinancialDataObject marginObject = new FinancialDataObject(marginName, value, date);
                    marginList.add(marginObject);
                }
            }
        }else{JOptionPane.showMessageDialog(null,"Error!");}
        return  marginList;
    }

    /**
     * almost same method as calculateMargins() but with changed mathematical sign (data for dividend payout is negative)
     * @param marginName name for new Data
     * @param dividendPayout dividendPayout value
     * @param commonStockSharesOutstanding number of outstanding common stocks
     * @return ArrayList containing FinancialDataObjects with quotients of the divisions as values (positive)
     */
    public static ArrayList<FinancialDataObject> calculateDividendsPerShare(String marginName, ArrayList<FinancialDataObject> dividendPayout,
                                                                            ArrayList<FinancialDataObject> commonStockSharesOutstanding){
        ArrayList<FinancialDataObject> marginList = new ArrayList<>();
        if (dividendPayout.size() == commonStockSharesOutstanding.size()){
            for (int i=0; i<dividendPayout.size();i++){
                double dividendPayoutDataPoint = dividendPayout.get(i).getValue();
                double commonStockSharesOutstandingDataPoint = commonStockSharesOutstanding.get(i).getValue();
                if (!(commonStockSharesOutstandingDataPoint==0.00000)) {
                    double value = -(dividendPayoutDataPoint) / commonStockSharesOutstandingDataPoint;
                    String date = dividendPayout.get(i).getDate();
                    FinancialDataObject marginObject = new FinancialDataObject(marginName, value, date);
                    marginList.add(marginObject);
                }
            }
        }else{JOptionPane.showMessageDialog(null,"Error!");}
        return  marginList;
    }

    public static ArrayList<FinancialDataObject> extractCommonSharesOutstandingData(ClientManager ClientManager,String keyName,  DataContainerManager dataContainerManager){
        ArrayList<FinancialDataObject> BalanceSheetDataList = new ArrayList<>();
        JSONArray jsonArray= ClientManager.getBalanceSheetClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
        for(int i=0; i<jsonArray.length();i++) {
            String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

            if (!(jsonArray.getJSONObject(i).getString(keyName).equals("None"))) {
                Double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                BalanceSheetDataList.add(dataObject);
            }else{
                Double value = Double.parseDouble(dataContainerManager.getCompanyOverviewData().getSharesOutstanding());
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                BalanceSheetDataList.add(dataObject);
            }
        }
        return BalanceSheetDataList;
    }

    /**
     * Method to calculate dividend Yield when given two ArrayLists containing dividendPerShare and historicalStockPrices
     * Since the two lists have different  Data Point Date-Modulation (quarterley vs daily) the method is designed to
     * used the dividendPerShare Date (Year and month) to search for the first entry of the historicalStockPrice List date found (it will
     * be the last reported stock price for that particular month).
     * @param name name for new Data
     * @param dividendPerShare list containing dividendPerShare Data
     * @param historicalStockPrice list containing historicalStockPrices
     * @return list containing dividend Yield FinancialDataObjects
     * @throws Exception if found no values
     */
    public static ArrayList<FinancialDataObject> calculateDividendYield(String name, ArrayList<FinancialDataObject> dividendPerShare,
                                                                        ArrayList<FinancialDataObject> historicalStockPrice)
    throws Exception{
         ArrayList<FinancialDataObject> dividendYieldList = new ArrayList<>();
         int u = 0;
         for (int i = 0; i<dividendPerShare.size();i++){
             String date = dividendPerShare.get(i).getDate();
             for(int a = u; a<historicalStockPrice.size();u++){
                 SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                 Date dividendPerShareDate = dateFormat.parse(dividendPerShare.get(i).getDate());

                 Date historicalStockPriceDate = dateFormat.parse(historicalStockPrice.get(a).getDate());

                 if(dividendPerShareDate.getMonth() == historicalStockPriceDate.getMonth() &&
                         dividendPerShareDate.getYear()==historicalStockPriceDate.getYear()){
                     double dividendPerShareDataPoint = dividendPerShare.get(i).getValue();
                     double historicalStockPriceDataPoint = historicalStockPrice.get(a).getValue();
                     double value = (dividendPerShareDataPoint/historicalStockPriceDataPoint)*100;
                     FinancialDataObject dividendYieldObject = new FinancialDataObject(name,value,date);
                     dividendYieldList.add(dividendYieldObject);
                     break;
                 } else{a =u;}
             }

         }return dividendYieldList;
    }






    public static double calculateMean (ArrayList<FinancialDataObject> financialDataObject){
         double sum =0.0;
         double average;
         if(!financialDataObject.isEmpty()){
             for(int i=0; i<financialDataObject.size();i++){
                 double dataPoint =financialDataObject.get(i).getValue();
                 if (!(dataPoint == 0.00000)){
                     sum +=  dataPoint;
                 }
             }
              average = sum/ financialDataObject.size();
         }else{average =0.0;}
         return average;
    }

    /**
     * This method will take an ArrayList>FinancialDataObject> and calculate the relative change of its first value (last
     * reported value in the real world) with a value chosen by the user. For finding that value a int is used to determine
     * how far thet iteration to that value has to be.
     * @param financialDataObjectList List containing all values
     * @param timeFrameInArrayListUnits number determined to set the position of the searched value(+1 = one position
     *                                  further in the ArrayList)
     * @return relative change of the given values as a double in percent
     */
    public static double calculateRelativeChange (ArrayList<FinancialDataObject> financialDataObjectList,int timeFrameInArrayListUnits){
         double relativeChange = 0.0;
         if(!financialDataObjectList.isEmpty()) {
             double lastValue = financialDataObjectList.get(0).getValue();
             double firstValue = financialDataObjectList.get(timeFrameInArrayListUnits).getValue();
             relativeChange =((lastValue-firstValue)/lastValue)*100;
         }return  relativeChange;
    }

    /**
     * This method will calculate the mean change in values given any ArrayList containing Financial Data Objects. It
     * will take the Arraylist and also a int for deciding how far to traverse the ArrayList (if 0 is selected all values
     * are calculated for the mean relative change.
     * @param financialDataObjects
     * @param timeFrameInArrayListUnits
     * @return
     */
    public static double calculateMeanRelativeChange (ArrayList<FinancialDataObject> financialDataObjects, int timeFrameInArrayListUnits){
        double meanRelativeChange = 0.0;
        if (!financialDataObjects.isEmpty()){
            double sumOfValues = 0.0;
            int numOfValues;
            if (timeFrameInArrayListUnits == 0){
                numOfValues = financialDataObjects.size();
                for (int i = 0; i<financialDataObjects.size()-1;i++){
                    double currentValue = financialDataObjects.get(i).getValue();
                    double pastValue = financialDataObjects.get(i+1).getValue();
                    double relativeChange  = ((currentValue-pastValue)/currentValue)*100;
                    System.out.println(relativeChange);
                    sumOfValues += relativeChange;
                    numOfValues++;
                }
            }else{
                numOfValues = timeFrameInArrayListUnits + 1;
                for(int i = 0; i<timeFrameInArrayListUnits;i++){
                    double currentValue = financialDataObjects.get(i).getValue();
                    double pastValue = financialDataObjects.get(i+1).getValue();
                    double relativeChange  = ((currentValue-pastValue)/currentValue)*100;
                    sumOfValues += relativeChange;
                    numOfValues++;
                }
            }
            meanRelativeChange = sumOfValues/numOfValues;
        }return meanRelativeChange;
    }

    /**
     * Method used for subtracting of  all values from one ArrayList from the corresponding values of another ArrayList
     * (both containing FinancialDataObjects).
     * @param valueName name of new Data
     * @param minuend list containing the minuend values
     * @param subtrahend list containing the subtrahend values
     * @return new DataList for subtracted values
     */
    public static ArrayList<FinancialDataObject> subtractTwoValues(String valueName, ArrayList<FinancialDataObject> minuend,
                                                                   ArrayList<FinancialDataObject> subtrahend) {
        ArrayList<FinancialDataObject> difference = new ArrayList<>();
        if (minuend.size() == subtrahend.size()) {
            for (int i = 0; i < minuend.size(); i++) {
                double value = minuend.get(i).getValue() - subtrahend.get(i).getValue();
                String date = minuend.get(i).getDate();
                FinancialDataObject differenceObject = new FinancialDataObject(valueName, value, date);
                difference.add(differenceObject);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        return difference;

    }

    public static ArrayList<FinancialDataObject> addTwoValues(String valueName, ArrayList<FinancialDataObject> valueOne,
                                                                   ArrayList<FinancialDataObject> valueTwo) {
        ArrayList<FinancialDataObject> sum = new ArrayList<>();
        if (valueOne.size() == valueTwo.size()) {
            for (int i = 0; i < valueOne.size(); i++) {
                double value = valueOne.get(i).getValue() + valueTwo.get(i).getValue();
                String date = valueOne.get(i).getDate();
                FinancialDataObject sumObject = new FinancialDataObject(valueName, value, date);
                sum.add(sumObject);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        return sum;

    }

    public static ArrayList<FinancialDataObject> calculatePriceToEarningsRatio (String name, DataContainerManager dataContainerManager)
    throws Exception {
        ArrayList<FinancialDataObject> PERatio = new ArrayList<>();
        ArrayList<FinancialDataObject> earningsPerShare = dataContainerManager.getCompanyFundamentalData().getEarningsPerShare();
        ArrayList<FinancialDataObject> historicalStockPrice = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice();
        int u = 0;
        for (int i = 0; i < earningsPerShare.size(); i++) {
            String date = earningsPerShare.get(i).getDate();
            for (int a = u; a < historicalStockPrice.size(); u++) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                Date earningsPerShareDate = dateFormat.parse(earningsPerShare.get(i).getDate());

                Date historicalStockPriceDate = dateFormat.parse(historicalStockPrice.get(a).getDate());

                if (earningsPerShareDate.getMonth() == historicalStockPriceDate.getMonth() &&
                        earningsPerShareDate.getYear() == historicalStockPriceDate.getYear()) {
                    double earningsPerShareDataPoint = earningsPerShare.get(i).getValue();
                    double historicalStockPriceDataPoint = historicalStockPrice.get(a).getValue();
                    double value = (earningsPerShareDataPoint / historicalStockPriceDataPoint) * 100;
                    FinancialDataObject PERatioObject = new FinancialDataObject(name, value, date);
                    PERatio.add(PERatioObject);
                    break;
                } else {
                    a = u;
                }
            }

        }
        return PERatio;
    }

    public static ArrayList<FinancialDataObject> calculatePEGRatio (ArrayList<FinancialDataObject> PERatio, double CAGR)
    throws Exception{
        ArrayList<FinancialDataObject> PEGRatio = new ArrayList<>();
        for (int i = 0; i< PERatio.size();i++){
            String name = "PEGRatio";
            String date = PERatio.get(i).getDate();
            double value = PERatio.get(i).getValue()/CAGR;
            FinancialDataObject PEGRatioDataPoint = new FinancialDataObject(name,value,date);
            PEGRatio.add(PEGRatioDataPoint);
        }
        return PEGRatio;

    }

    public static  double calculateCAGRFromDailyData (ArrayList<FinancialDataObject> financialDataObjects,int timeFrameInArrayListUnits){
        double CAGR = 0.00;
        if (!financialDataObjects.isEmpty()){
            double endingValue = financialDataObjects.get(0).getValue();
            double beginningValue = financialDataObjects.get(timeFrameInArrayListUnits).getValue();
            double numOfYears = timeFrameInArrayListUnits/365;
            CAGR = Math.pow((endingValue/beginningValue),1.0/numOfYears)-1;
        }return CAGR;
    }

    /**
     * This method calculates the Compounded Annual Growth Rate of a company from its quarterly financial reports. It
     * will use the standard formula for calculating CAGRs for as many Years as given to the method through parameters.
     * If the calculated CAGR is negative, it will return a CAGR of 0.00
     * @param financialDataObjects ArrayList used for calculation of CAGR
     * @param timeFrameInArrayListUnits
     * @return double containing the CAGR
     */
    public static  double calculateCAGRFromQuarterlyData (ArrayList<FinancialDataObject> financialDataObjects,int timeFrameInArrayListUnits){
        double CAGR = 0.00;
        if (!financialDataObjects.isEmpty()) {
            if (!(timeFrameInArrayListUnits == 0)) { //used if CAGR of certain time frames should be calculated
                double beginningValue = financialDataObjects.get(financialDataObjects.size() - 1).getValue();
                double endingValue = financialDataObjects.get(timeFrameInArrayListUnits).getValue();
                double numOfYears = timeFrameInArrayListUnits / 4; //dividing by 4 will give the time in years since we are using quarterly data
                double endToBeginningRatio = endingValue / beginningValue;
                if (!(endToBeginningRatio <= 0)) {
                    double temporalCAGR = Math.pow(endToBeginningRatio, (1.0 / numOfYears)) - 1; //standard CAGR formula
                    if (!(temporalCAGR < 0)) {
                        CAGR = temporalCAGR;
                    }
                }

            } else if (timeFrameInArrayListUnits == 0) { //used if CAGR of whole time frame of the ArrayList should be calculated

                double endToBeginningRatio = 0.00;
                double numOfYears = 4.0;
                for (int i = 0; i < 16; i += 4) {
                    double beginningValue = financialDataObjects.get(i + 4).getValue();
                    double endingValue = financialDataObjects.get(i).getValue();
                    double endToBeginningRatioPoint = endingValue / beginningValue;
                    endToBeginningRatio += endToBeginningRatioPoint;

                }
                endToBeginningRatio = endToBeginningRatio / 4;
                if (!(endToBeginningRatio <= 0)) {
                    double temporalCAGR = Math.pow(endToBeginningRatio, (1.0 / numOfYears)) - 1;
                    if (!(temporalCAGR < 0)) {
                        CAGR = temporalCAGR;
                    }
                }
            }
        }
        return CAGR;
    }


    /**
     * calculation of the Discounted Cash Flow Fair Value per Share of a certain company. The method will utilize
     * various CAGRs of the Company for evaluation of the DCF Fair Value per share. This method takes the CAGR of the
     * whole time frame of the given data, but calculates the fair value only for one point in the data list (one date)
     * @param dataContainerManager container for all used data
     * @param ArrayListEntry point/date at which the fair value should be calculated
     * @return double containing the DCFFAirValue per share
     */
    public static double calculateDCFFairValuePerShare(DataContainerManager dataContainerManager, int ArrayListEntry){
        double DCFFairValuePerShare = 0.00;
        double lastDiscountedOperativeCashFlowValue = 0.00;
        double DCFSum = 0.00;
        double discountRate = 0.07;
        double longtimeGrowRate = 0.04;
        double discountFactor = 1+ discountRate;
        double currentoperativeCashFlow = dataContainerManager.getCompanyFundamentalData().getOperatingCashflow().get(ArrayListEntry).getValue()*4;

        double operativeCashflowCAGR = calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().
                getOperatingCashflow(),0);

        double totalRevenueCAGR = calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().
                getTotalRevenue(),0);
        ArrayList<FinancialDataObject> bookValuePlusDividends = addTwoValues("bookValuePlusDividends",
                dataContainerManager.getCompanyFundamentalData().getWorkingCapital(),
                dataContainerManager.getCompanyFundamentalData().getDividendPayout());
        double bookValuePlusDividendsCAGR = calculateCAGRFromQuarterlyData(bookValuePlusDividends,0);

        double totalCAGR = (operativeCashflowCAGR+bookValuePlusDividendsCAGR+totalRevenueCAGR)/3;

        for (int i =0; i<10;i++){
            double discountedOperativeCashFlow = currentoperativeCashFlow/discountFactor;
            DCFSum+=discountedOperativeCashFlow;
            discountFactor+= discountRate;
            currentoperativeCashFlow = currentoperativeCashFlow*(1+totalCAGR);
            if (i ==9){
                lastDiscountedOperativeCashFlowValue = discountedOperativeCashFlow;
            }

        }
        double terminalValue = lastDiscountedOperativeCashFlowValue*((1+longtimeGrowRate)/(discountRate-longtimeGrowRate));
        double outstandingShares = dataContainerManager.getCompanyFundamentalData().getCommonStockSharesOutstanding().get(ArrayListEntry).getValue();
        double Cash = dataContainerManager.getCompanyFundamentalData().getCash().get(ArrayListEntry).getValue();
        double shortTermDebt = dataContainerManager.getCompanyFundamentalData().getShortTermDebt().get(ArrayListEntry).getValue();
        double longTermDebt = dataContainerManager.getCompanyFundamentalData().getLongTermDebt().get(ArrayListEntry).getValue();
        double totalDebt = shortTermDebt+longTermDebt;
        double netCash =  Cash - totalDebt;
        double totalFairValue = DCFSum+terminalValue+netCash;
        if (outstandingShares==0.0){
            outstandingShares = dataContainerManager.getCompanyFundamentalData().getCommonStockSharesOutstanding().get(1).getValue();

         }
        DCFFairValuePerShare = totalFairValue/outstandingShares/4;


        return DCFFairValuePerShare;


    }

    /**
     * This method utilizes the calculateDCFFairValuePerShare() method for calculating all DCFFairValues per share of
     * an ArrayList containing Financial data objects. It will loop through the list and create a new
     * ArrayList<FinancialDataObject> for storing the new values
     * @param dataContainerManager container for all data
     * @return ArrayList containing all new values
     */
    public static ArrayList<FinancialDataObject> calculateHistoricalDCFFairValuePerShare (DataContainerManager dataContainerManager){

        ArrayList<FinancialDataObject> HistoricalDCFFairValuePerShare = new ArrayList<>();
        for (int i =0; i<dataContainerManager.getCompanyFundamentalData().getFreeCashFlow().size();i++) {
            String name = "DCFFairValuePerShare";
            String date = dataContainerManager.getCompanyFundamentalData().getFreeCashFlow().get(i).getDate();
            double value = DataExtractor.calculateDCFFairValuePerShare(dataContainerManager,i);
            FinancialDataObject DCFFairValuePerShareDataPoint = new FinancialDataObject(name,value,date);
            HistoricalDCFFairValuePerShare.add(DCFFairValuePerShareDataPoint);
        }
            return HistoricalDCFFairValuePerShare;
    }


    public static ArrayList<FinancialDataObject> calculatePeterLynchFairValue (DataContainerManager dataContainerManager)
    throws Exception{
        ArrayList<FinancialDataObject> PeterLynchFairValue = new ArrayList<>();
        double netIncomeCAGR = calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().getNetIncome(),0);
        double EBITDACAGR = calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().getEBITDA(),0);
        ArrayList<FinancialDataObject> PERatio = calculatePriceToEarningsRatio("PERatio",dataContainerManager);
        ArrayList<FinancialDataObject> PEGRatio = calculatePEGRatio(PERatio,netIncomeCAGR);
        for (int i = 0; i<PEGRatio.size(); i++){
            double earningsPerShareDataPoint = dataContainerManager.getCompanyFundamentalData().getEarningsPerShare().get(i).getValue();
            double PEGRatioDataPoint = PEGRatio.get(i).getValue();

            double value = PEGRatioDataPoint*EBITDACAGR*earningsPerShareDataPoint;
            String name = "PeterLynchFairValue";
            String date = PEGRatio.get(i).getDate();

            FinancialDataObject PeterLynchFairValueDataPoint = new FinancialDataObject(name,value,date);
            PeterLynchFairValue.add(PeterLynchFairValueDataPoint);

        }


        return PeterLynchFairValue;
    }









    }

