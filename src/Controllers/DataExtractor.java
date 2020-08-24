package Controllers;


import Data.FinancialDataObject;
import Data.PitrovskiFScoreData;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public  class DataExtractor {

    /**
     * Static method for extracting Data from the JSON Objects created by the AlphaVantageAPIClient.
     * This method is used by the  CompanyOverviewData Class to access data from simple JSON objects
     *
     * @param ClientManager       used APIManager containing the AlphaVantageApiClient(OVERVIEW) object
     * @param variableName variable that needs to be searched for inside JSON Object
     * @return String containing desired data
     */
    public static String extractOVERVIEWData(ClientManager ClientManager, String variableName) {
        return  ClientManager.getOverviewClient().getResponse().getBody().getObject().getString(variableName);

    }

    /**
     * This constructor will take an AlphaVantageAPIClient and search for the "Time Series (Daily)" key. After getting that key
     * it will iterate through all entries (dates) while saving the corresponding adjusted close price values to a double.
     * The date and the value are then used to create a StockPrice object which is the added to an ArrayList<StockPrice>
     *     which is also returned. This
     * @param ClientManager used APIManager containing the  AlphaVantageAPIClient used for this operation
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
     * Main method used to extract Data from the generated Income Statement JSON-Object. It iterates through the JSON File
     * under the Sub-Node "quarterly reports" (more available data than "yearly" reports) and will extract all found values
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
                 double value = jsonArray.getJSONObject(i).getDouble(keyName);
                 FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                 IncomeStatementDataList.add(dataObject);
             } else {
                 double value = 0.00000;
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
                double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                BalanceSheetDataList.add(dataObject);
            }else{
                double value = 0.00000;
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
                double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                CashFlowDataList.add(dataObject);
            }else{
                double value = 0.00000;
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
                                                                             ArrayList<FinancialDataObject> sampleRevenue)
    throws Exception{
        ArrayList<FinancialDataObject> marginList = new ArrayList<>();

            for (int i=0; i<sampleProfit.size();i++){
                double value;
                double sampleProfitDataPoint = sampleProfit.get(i).getValue();
                double sampleRevenueDataPoint = extractMatchingValue(i,sampleProfit,sampleRevenue);
                if (!(sampleRevenueDataPoint==0.00000)) {
                     value = sampleProfitDataPoint / sampleRevenueDataPoint;
                    String date = sampleProfit.get(i).getDate();
                    FinancialDataObject marginObject = new FinancialDataObject(marginName, value, date);
                    marginList.add(marginObject);
                }
            }

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
                                                                             ArrayList<FinancialDataObject> sampleRevenue)
    throws Exception{
        ArrayList<FinancialDataObject> marginList = new ArrayList<>();

        for (int i=0; i<sampleProfit.size();i++){
            double value;
            double sampleProfitDataPoint = sampleProfit.get(i).getValue();
            double sampleRevenueDataPoint = extractMatchingValue(i,sampleProfit,sampleRevenue);
            if (!(sampleRevenueDataPoint==0.00000)) {
                value = sampleProfitDataPoint / sampleRevenueDataPoint;
                String date = sampleProfit.get(i).getDate();
                FinancialDataObject marginObject = new FinancialDataObject(marginName, value, date);
                marginList.add(marginObject);
            }
        }

        return  marginList;
    }



    /**
     * almost same method as calculateMargins() but with changed mathematical sign (data for dividend payout is negative)
     * @param name name for new Data
     * @param dividendPayout dividendPayout value
     * @param commonStockSharesOutstanding number of outstanding common stocks
     * @return ArrayList containing FinancialDataObjects with quotients of the divisions as values (positive)
     */
    public static ArrayList<FinancialDataObject> calculateDividendsPerShare
    (String name, ArrayList<FinancialDataObject> dividendPayout,
     ArrayList<FinancialDataObject> commonStockSharesOutstanding) throws Exception{

        ArrayList<FinancialDataObject> DividendsPerShareList = new ArrayList<>();

            for (int i=0; i<dividendPayout.size();i++){
                double value = 0.0;
                double dividendPayoutDataPoint = dividendPayout.get(i).getValue();
                double commonStockSharesOutstandingDataPoint = extractMatchingValue(i,dividendPayout,commonStockSharesOutstanding);
                if (!(dividendPayoutDataPoint ==0)){
                     value = -(dividendPayoutDataPoint) / commonStockSharesOutstandingDataPoint;
                }
                    String date = dividendPayout.get(i).getDate();
                    FinancialDataObject DividendPerShareDataPoint = new FinancialDataObject(name, value, date);
                    DividendsPerShareList.add(DividendPerShareDataPoint);

            }

        return  DividendsPerShareList;
    }

    /**
     * method to extract data regarding outstanding common shares of a company from the client manager. This method will
     * try to get the exact number of outstanding common shares from the quarterly reports. If it is not able to (e.g.
     * if there is no value saved in one or more of the quarterly reports from the json files) it will instead take
     * the value saved in the CompanyOverviewData object.
     * @param ClientManager client used for establishing api connection
     * @param keyName String used for searching of certain keywords in the json file
     * @param dataContainerManager data object used for data storage of whole script
     * @return returns a list containing all found or replaced numbers for outstanding common shares at certain dates
     */
    public static ArrayList<FinancialDataObject> extractCommonSharesOutstandingData
    (ClientManager ClientManager, String keyName,  DataContainerManager dataContainerManager)throws Exception{
        ArrayList<FinancialDataObject> CommonSharesOutstandingDataList = new ArrayList<>();
        JSONArray jsonArray= ClientManager.getBalanceSheetClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
        for(int i=0; i<jsonArray.length();i++) {
            String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

            if (!(jsonArray.getJSONObject(i).getString(keyName).equals("None"))) {
                double value = jsonArray.getJSONObject(i).getDouble(keyName);
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                CommonSharesOutstandingDataList.add(dataObject);
            }else{
                double value = Double.parseDouble(dataContainerManager.getCompanyOverviewData().getSharesOutstanding());
                FinancialDataObject dataObject = new FinancialDataObject(keyName, value, date);
                CommonSharesOutstandingDataList.add(dataObject);
            }
        }
        return CommonSharesOutstandingDataList;
    }

    private static double extractMatchingValue (int listOnePosition, ArrayList<FinancialDataObject> baseList,
                                                ArrayList<FinancialDataObject> list2Iterate) throws Exception {
        double matchingValue = 0.0;
        LocalDate baseListDate = LocalDate.parse(baseList.get(listOnePosition).getDate());
        if (!list2Iterate.isEmpty()) {
            for (int i = 0; i < list2Iterate.size(); i++) {

                LocalDate list2IterateDate = LocalDate.parse(list2Iterate.get(i).getDate());

                if (baseListDate.getMonthValue() == list2IterateDate.getMonthValue() &&
                        baseListDate.getYear() == list2IterateDate.getYear()) {
                    matchingValue = list2Iterate.get(i).getValue();
                }
            }
        }  return matchingValue;
    }

    public static double extractSumOfDataValues (ArrayList<FinancialDataObject> dataList, int timeFrameUnit){
        double sumOfDataPoints = 0.0;
        if (!(dataList.isEmpty())){
            for (int i = 0; i<timeFrameUnit; i++){
                sumOfDataPoints += dataList.get(i).getValue();
            }
        }
        return sumOfDataPoints;
    }
    /**
     * Method to calculate dividend Yield when given two ArrayLists containing dividendPerShare and historicalStockPrices
     * Since the two lists have different  Data Point Date-Modulation (quarterly vs daily) the method is designed to
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


                 LocalDate dividendPerShareDate = LocalDate.parse(dividendPerShare.get(i).getDate());
                 LocalDate historicalStockPriceDate = LocalDate.parse(historicalStockPrice.get(a).getDate());

                 if(dividendPerShareDate.getMonthValue() == historicalStockPriceDate.getMonthValue() &&
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


    /**
     * short method for extracting a certain value from an ArrayList<FinancialDataObject> if given a certain date
     * @param DataList list from which the values need to be extracted
     * @param key key to search for (date)
     * @return returns extracted value
     * @throws Exception
     */
    public static double extractValueByDate (ArrayList<FinancialDataObject> DataList, String key)throws Exception{
        double extractedValue = 0.0;
        for(int i=0;i<DataList.size();i++){


            LocalDate keyDate = LocalDate.parse(key);
            LocalDate DataListDate = LocalDate.parse(DataList.get(i).getDate());
            if(keyDate.getMonthValue() == DataListDate.getMonthValue() && keyDate.getYear() == DataListDate.getYear()){
                extractedValue = DataList.get(i).getValue();
                break;
            }
            
        }return extractedValue;
        
    }


    /**
     * Calculation of mean values of an ArrayList containing Financial Data Objects.
     * @param dataList ArrayList containing all data values that need to be calculated as mean
     * @return mean value as a double
     */
    public static double calculateMeanValueOverOneList (ArrayList<FinancialDataObject> dataList, int timeFrameInArrayListUnits ){
         double sum =0.0;
         double average = 0.0;
         if(!dataList.isEmpty()){
             if (timeFrameInArrayListUnits==0){
             for(int i=0; i<dataList.size();i++){
                 double dataPoint =dataList.get(i).getValue();
                 sum +=  dataPoint;
             }
              average = sum/ dataList.size();
             } else {
                 for(int i=0; i<timeFrameInArrayListUnits;i++){
                     double dataPoint =dataList.get(i).getValue();
                     sum +=  dataPoint;
                 }
                 if (!(sum==0)) {
                     average = sum / timeFrameInArrayListUnits;
                 }
             }
         }
         return average;
    }

    public static double calculateMarginForTwoValuesInPercent(double dataPointOne, double dataPointTwo){
        double mean = 0.0;
        if (!(dataPointOne==0.0)&&!(dataPointTwo==0.0)){
            mean = dataPointOne/dataPointTwo*100;
        }
        return mean;
    }

    public static double calculateStandardDeviation (ArrayList<FinancialDataObject> dataList, int timeFrameInListUnits) throws Exception{
        double sum = 0.0;
        double average = calculateMeanValueOverOneList(dataList,timeFrameInListUnits);
        double standardDeviation = 0.0;
        if(!dataList.isEmpty()) {
            if (timeFrameInListUnits == 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    double dataPoint = dataList.get(i).getValue();
                    double squaredDataPoint = Math.pow((dataPoint - average),2);
                    sum += squaredDataPoint;
                }
                if (!(sum == 0.0)) {
                    double variance = sum / dataList.size();
                    standardDeviation = Math.pow(variance,0.5);
                }
            } else {
                for (int i = 0; i < timeFrameInListUnits; i++) {
                    double dataPoint = dataList.get(i).getValue();
                    double squaredDataPoint = Math.pow((dataPoint - average),2);
                    sum += squaredDataPoint;
                }
                if (!(sum == 0.0)) {
                    double variance = sum / dataList.size();
                    standardDeviation = Math.pow(variance,0.5);
                }
            }
        }
        return standardDeviation;
    }

    public static double calculateDivergence (double benchmarkValue, double actualValue){
        double divergence = (benchmarkValue-actualValue)/benchmarkValue;
        return divergence;

    }

    public static int calculatePoints (double pointsNumber, double benchmarkValue, double actualValue){
        double divergence = calculateDivergence(benchmarkValue,actualValue);
        int endPoints;
        if (!(divergence<=0)){
            endPoints =(int) Math.round (pointsNumber*divergence);

        }else{
            endPoints = (int) pointsNumber;
        }
        return endPoints;
    }


    /**
     * Method to calculate the mean values of three distinctive ArrayLists containing Financial Data Objects. The method
     * will NOT calculated the mean over all lists values but only from the values of the list of the same date. It
     * will then return a new ArrayList containing new Financial Data Objects but with the means as new values.
     * @param nameOfMean name String for the new data objects
     * @param listOne first list
     * @param listTwo second list
     * @param listThree third list
     * @return new ArrayList containing new Financial Data Objects with mean values
     * @throws Exception
     */
    public static ArrayList<FinancialDataObject> calculateMeanOverThreeLists(String nameOfMean,ArrayList<FinancialDataObject> listOne, ArrayList<FinancialDataObject> listTwo,
                                                     ArrayList<FinancialDataObject> listThree) throws Exception{
        double sum;
        double average;
        ArrayList<FinancialDataObject> meanList = new ArrayList<>();
        if(!(listOne.isEmpty())&&!(listTwo.isEmpty())&&!(listThree.isEmpty())){
            for(int i = 0; i<listOne.size();i++){
                sum =0.0;
                String date = listOne.get(i).getDate();
                double dataPointOne = listOne.get(i).getValue();
                double dataPointTwo = extractMatchingValue(i,listOne,listTwo);
                double dataPointThree = extractMatchingValue(i,listOne,listThree);
                sum += dataPointOne+dataPointTwo+dataPointThree;
                if (!(sum==0.0)){
                    average = sum/3;
                }else{
                    average=0.0;
                }
                FinancialDataObject meanValueDataPoint = new FinancialDataObject(nameOfMean,average,date);
                meanList.add(meanValueDataPoint);
            }

        }
        return meanList;

    }

    /**
     * This method will take an ArrayList>FinancialDataObject> and calculate the relative change of its first value (last
     * reported value in the real world) with a value chosen by the user. For finding that value a int is used to determine
     * how far that iteration to that value has to be.
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
    public static ArrayList<FinancialDataObject> subtractTwoListValues(String valueName, ArrayList<FinancialDataObject> minuend,
                                                                       ArrayList<FinancialDataObject> subtrahend)
    throws Exception{
        ArrayList<FinancialDataObject> difference = new ArrayList<>();
        if (!(minuend.isEmpty())) {
            for (int i = 0; i < minuend.size(); i++) {
                double value = 0.0;
                double minuendValue = minuend.get(i).getValue();
                double subtrahendValue = extractMatchingValue(i,minuend,subtrahend);
                value = minuendValue-subtrahendValue;
                String date = minuend.get(i).getDate();
                FinancialDataObject differenceObject = new FinancialDataObject(valueName, value, date);
                difference.add(differenceObject);
            }
        }
        return difference;

    }

    /**
     * Method for adding two values from every Financial Data Object of two distinctive ArrayLists. Method will return
     * a new ArrayList containing Financial Data Objects containing the summed values with the dates taken from either one
     * of the lists.
     * @param valueName name of the value
     * @param listOne first list
     * @param listTwo second list
     * @return ArrayList<FinancialDataObject> with summed values
     */
    public static ArrayList<FinancialDataObject> addTwoListValues(String valueName, ArrayList<FinancialDataObject> listOne,
                                                                  ArrayList<FinancialDataObject> listTwo) throws Exception{
        ArrayList<FinancialDataObject> sumList = new ArrayList<>();
        if (!(listOne.isEmpty())) {
            for (int i = 0; i < listOne.size(); i++) {
                double value = 0.0;
                double valueOne = listOne.get(i).getValue();
                double valueTwo = extractMatchingValue(i,listOne,listTwo);
                value = valueOne+valueTwo;
                String date = listOne.get(i).getDate();
                FinancialDataObject sumObject = new FinancialDataObject(valueName, value, date);
                sumList.add(sumObject);
            }

        }
        return sumList;

    }

    public static double divideTwoValues (double valueOne, double valueTwo){
        double endValue = 0.0;
        if(!(valueOne==0.0)&&!(valueTwo==0.0)) {
            endValue = valueOne / valueTwo;
        }
        return endValue;
    }

    /**
     * Method to calculate the Ratio between stock price and earnings per share of a company. It will scan the dataContainerManager
     * for earningsPerShare values and try to find matching stock prices from the CompanyOverviewData object in the
     * same dataContainerManager. The used loop only searches for matching months and years, since certain days are missing
     * in the database for stock prices. The first found stock price of the month is taken for this calculation.
     * @param name
     * @param dataContainerManager
     * @return
     * @throws Exception
     */
    public static ArrayList<FinancialDataObject> calculatePriceToEarningsRatio (String name, DataContainerManager dataContainerManager)
    throws Exception {
        ArrayList<FinancialDataObject> PERatio = new ArrayList<>();
        ArrayList<FinancialDataObject> earningsPerShare = dataContainerManager.getCompanyFundamentalData().getEarningsPerShare();
        ArrayList<FinancialDataObject> historicalStockPrice = dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice();
        int u = 0;
        for (int i = 0; i < earningsPerShare.size(); i++) {
            String date = earningsPerShare.get(i).getDate();
            for (int a = u; a < historicalStockPrice.size(); u++) {

                LocalDate earningsPerShareDate = LocalDate.parse(earningsPerShare.get(i).getDate());

                LocalDate historicalStockPriceDate = LocalDate.parse(historicalStockPrice.get(a).getDate());

                if (earningsPerShareDate.getMonthValue() == historicalStockPriceDate.getMonthValue() &&
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

    /**
     * This method calculates the Price to Earnings growth ratio. It will need an ArrayList containing FinancialDataObjects
     * with Price to Earning values inside and also a double representing the Compounded Annual Growth Rate. It will
     * use these values to calculate the PEGRatio and return a new ArrayList with the same dates but new values.
     * @param PERatio Price to Earnings Ratio needed to calculate PEGRatio
     * @param CAGR growth rate
     * @return new ArrayList containing desired values
     * @throws Exception
     */
    public static ArrayList<FinancialDataObject> calculatePEGRatio (ArrayList<FinancialDataObject> PERatio, double CAGR)
    throws Exception{
        ArrayList<FinancialDataObject> PEGRatio = new ArrayList<>();
        if (CAGR == 0.0){
            CAGR =1.0; /* if given CAGR is exactly 0 the CAGR is changed to 1 to make the division possible (1 means
                        neither positive nor negative growth */
        }
        for (int i = 0; i< PERatio.size();i++){
            String name = "PEGRatio";
            String date = PERatio.get(i).getDate();
            double value = PERatio.get(i).getValue()/CAGR;
            FinancialDataObject PEGRatioDataPoint = new FinancialDataObject(name,value,date);
            PEGRatio.add(PEGRatioDataPoint);
        }
        return PEGRatio;

    }

    /**
     * calculation of the Compounded Annual Growth rate for ArrayLists containing FinancialDataObjects which represent
     * DAILY stock price Data.
     * @param financialDataObjects Data List needed for calculation
     * @param timeFrameInArrayListUnits int needed to set the point to which the calculation should go (higher number
     *                                  means it will go further back in time for the calculation
     * @return CAGR of daily Stock Prices
     */
    public static  double calculateCAGRFromDailyData (ArrayList<FinancialDataObject> financialDataObjects,int timeFrameInArrayListUnits){
        double CAGR = 0.00;
        double numOfYears =1;
        if (!financialDataObjects.isEmpty()){
            double endingValue = financialDataObjects.get(0).getValue();
            double beginningValue;
            if (financialDataObjects.size()<timeFrameInArrayListUnits){
                 beginningValue = financialDataObjects.get(financialDataObjects.size()-1).getValue();
                numOfYears = financialDataObjects.size()/365;
            }else{
             beginningValue = financialDataObjects.get(timeFrameInArrayListUnits).getValue();
             numOfYears = timeFrameInArrayListUnits/365;} //calculation differs here from the normal calculateCAGR() method
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
    public static double calculateDCFFairValuePerShare(DataContainerManager dataContainerManager, int ArrayListEntry)
    throws Exception{
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
        ArrayList<FinancialDataObject> bookValuePlusDividends = addTwoListValues("bookValuePlusDividends",
                dataContainerManager.getCompanyFundamentalData().getWorkingCapital(),
                dataContainerManager.getCompanyFundamentalData().getDividendPayout());
        double bookValuePlusDividendsCAGR = calculateCAGRFromQuarterlyData(bookValuePlusDividends,0);

        double totalCAGR = (operativeCashflowCAGR+bookValuePlusDividendsCAGR+totalRevenueCAGR)/3;
        if (totalCAGR==0.0){
            totalCAGR =1.0;
        }

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
    public static ArrayList<FinancialDataObject> calculateHistoricalDCFFairValuePerShare (DataContainerManager dataContainerManager)
    throws Exception{

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
        double EBITDACAGR = calculateCAGRFromQuarterlyData(dataContainerManager.getCompanyFundamentalData().getEBITDA(),0)*100;

        if (netIncomeCAGR ==0.00){
            netIncomeCAGR = 1.0;
        }

        ArrayList<FinancialDataObject> PERatio = calculatePriceToEarningsRatio("PERatio",dataContainerManager);
        ArrayList<FinancialDataObject> PEGRatio = calculatePEGRatio(PERatio,netIncomeCAGR);
        for (int i = 0; i<PEGRatio.size(); i++){
            double earningsPerShareDataPoint = dataContainerManager.getCompanyFundamentalData().getEarningsPerShare().get(i).getValue();
            double PEGRatioDataPoint = PEGRatio.get(i).getValue();

            String date = PEGRatio.get(i).getDate();
            String name = "PeterLynchFairValue";
            double value;
            double growthFactor = PEGRatioDataPoint+EBITDACAGR;

            if ((growthFactor>16 && growthFactor<50) || (growthFactor >0 && growthFactor<16)){
                value = ((growthFactor) / 2) * earningsPerShareDataPoint * 4;
            }else if (growthFactor>50) {
                growthFactor = 25;
                value = growthFactor * earningsPerShareDataPoint * 4;

            }else {
                value =earningsPerShareDataPoint*4;
            }



            FinancialDataObject PeterLynchFairValueDataPoint = new FinancialDataObject(name,value,date);
            PeterLynchFairValue.add(PeterLynchFairValueDataPoint);

        }


        return PeterLynchFairValue;
    }

    public static ArrayList<FinancialDataObject> calculateGrahamNumber (DataContainerManager dataContainerManager)
        throws Exception{
        ArrayList<FinancialDataObject> GrahamNumber = new ArrayList<>();
        ArrayList<FinancialDataObject> earningsPerShare = dataContainerManager.getCompanyFundamentalData().getEarningsPerShare();
        ArrayList<FinancialDataObject> bookValuePerShare = dataContainerManager.getCompanyFundamentalData().getBookValuePerShare();
        for (int i = 0; i<earningsPerShare.size();i++){
            String name = "GrahamNumber";
            String date = earningsPerShare.get(i).getDate();
            double value = 0.0;
            double earningsPerShareValue = earningsPerShare.get(i).getValue()*4;
            double bookValuePerShareValue = bookValuePerShare.get(i).getValue()*4;
            double EPSAndBVSMultiple =  earningsPerShareValue*bookValuePerShareValue;
            if(!(EPSAndBVSMultiple <0)) {
                 value = Math.pow((22.5 * EPSAndBVSMultiple), 0.5);
            }
            FinancialDataObject GrahamNumberDataPoint = new FinancialDataObject(name, value, date);
            GrahamNumber.add(GrahamNumberDataPoint);
        }

        return GrahamNumber;
    }


    public static double calculateReturnOnAssets (int timeFactor, DataContainerManager dataContainerManager)throws Exception{
        double returnOnAssets = 0.0;
        double netIncome = dataContainerManager.getCompanyFundamentalData().getNetIncome().get(timeFactor).getValue();
        double totalAssets = dataContainerManager.getCompanyFundamentalData().getTotalAssets().get(timeFactor).getValue();

        if (!(netIncome==0.0) && !(totalAssets==0.0)) {
             returnOnAssets = netIncome / totalAssets;
        }
        return  returnOnAssets;
    }

    public static double calculateCashflowReturnOnAssets (DataContainerManager dataContainerManager)throws Exception{
        double returnOnAssets = 0.0;
        double operatingCashflow = dataContainerManager.getCompanyFundamentalData().getOperatingCashflow().get(0).getValue();
        double totalAssets = dataContainerManager.getCompanyFundamentalData().getTotalAssets().get(0).getValue();

        if (!(operatingCashflow==0.0) && !(totalAssets==0.0)) {
            returnOnAssets = operatingCashflow / totalAssets;
        }
        return  returnOnAssets;
    }

    public static void calculatePitrovskiFScore (DataContainerManager dataContainerManager) throws Exception{
        PitrovskiFScoreData pitrovskiFScoreData = dataContainerManager.getPitrovskiFScoreData();

        if (pitrovskiFScoreData.getReturnOnAssetsCurrentYear()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[0] = 1;
        }
        if (pitrovskiFScoreData.getCashFlowReturnOnAssets()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[1] = 1;
        }
        if (pitrovskiFScoreData.getChangeInReturnOnAssets()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[2] = 1;
        }
        if (pitrovskiFScoreData.getCashFlowReturnOnAssets()>pitrovskiFScoreData.getReturnOnAssetsCurrentYear()){
            pitrovskiFScoreData.getPitrovskiFScoreList()[3] = 1;
        }
        if (pitrovskiFScoreData.getChangeInLeverage()<0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[4] = 1;
        }
        if (pitrovskiFScoreData.getChangeInCurrentRatio()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[5] = 1;
        }
        if (pitrovskiFScoreData.getChangeInSharesIssues()<=0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[6] = 1;
        }

        if (pitrovskiFScoreData.getChangeInGrossMargin()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[7] = 1;
        }

        if (pitrovskiFScoreData.getChangeInAssetTurnover()>0){
            pitrovskiFScoreData.getPitrovskiFScoreList()[8] = 1;
        }
        int sum=0;
        for (int i = 0; i<pitrovskiFScoreData.getPitrovskiFScoreList().length;i++){
            sum  += pitrovskiFScoreData.getPitrovskiFScoreList()[i];
        }
        pitrovskiFScoreData.setPitrovskiFScore(sum);
    }

    public static void calculateEvaluationPoints (DataContainerManager dataContainerManager) throws Exception{

         int fairValue2StockPricePoints = calculatePoints(
                 25,dataContainerManager.getCompanyOverviewData().getHistoricalStockPrice().get(0).getValue(),
                 dataContainerManager.getEvaluationData().getCurrentMeanFairValue());
         dataContainerManager.getEvaluationData().setFairValue2StockPricePoints(fairValue2StockPricePoints); //HERE TSLA!!

         int pitrovskiFScorePoints = calculatePoints(25,9.0,
                 dataContainerManager.getPitrovskiFScoreData().getPitrovskiFScore());
         dataContainerManager.getEvaluationData().setPitrovskiFScorePoints(pitrovskiFScorePoints);

        /* in this line the calculatePoints() methods arguments benchmarkValue and actualValue are switched, since we
        * want to measure a value that has to be lower than the benchmark*/
         int volatilityPoints = calculatePoints(10,
                 dataContainerManager.getEvaluationData().getStockPriceVolatility(),10);

         int performancePoints = calculatePoints(
                 15,0.3,dataContainerManager.getEvaluationData().getTenYearStockPriceCAGR());

         int volatilityAndPerformancePoints = volatilityPoints+performancePoints;
         dataContainerManager.getEvaluationData().setVolatilityAndPerformancePoints(volatilityAndPerformancePoints);

         int dividendIsTruePoints =0;
         if (dataContainerManager.getCompanyFundamentalData().getDividendsPerShare().get(0).getValue()>0){
             dividendIsTruePoints = 4;
         }

         int dividendYieldPoints = calculatePoints(7,3,
                 calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getDividendYield(),4));

         int payoutRatioPoints = calculatePoints(7,
                 calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getPayOutRatio(),4),0.1);

         int dividendPayoutToFCFPoints = calculatePoints(7,
                 (-calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getDividendPayout(),4)),
                 (calculateMeanValueOverOneList(dataContainerManager.getCompanyFundamentalData().getFreeCashFlow(),4))/2);

         int dividendFactorPoints = dividendIsTruePoints+dividendYieldPoints+payoutRatioPoints+dividendPayoutToFCFPoints;

         dataContainerManager.getEvaluationData().setDividendFactorsPoints(dividendFactorPoints);

         int evaluationPoints = fairValue2StockPricePoints+pitrovskiFScorePoints+volatilityAndPerformancePoints+dividendFactorPoints;
         dataContainerManager.getEvaluationData().setSumOfEvaluationPoints(evaluationPoints);



    }









    }

