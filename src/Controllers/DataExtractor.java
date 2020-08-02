package Controllers;


import Data.FinancialDataObject;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public  class DataExtractor {

    /**
     * Static method for extracting Data from the JSON Objects created by the AlphavantageAPIClient.
     * This method is used by the  CompanyOverviewData Class to access data from simple JSON objects
     *
     * @param APIManager       used APIMAnager containing the AlphavantageApiClient(OVERVIEW) object
     * @param variableName variable that needs to be searched for inside JSON Object
     * @return String countaining desired data
     */
    public static String extractOVERVIEWData(APIManager APIManager, String variableName) {
        String dataVariable = APIManager.getOverviewClient().getResponse().getBody().getObject().getString(variableName);
        return dataVariable;
    }

    /**
     * This constructor will take an AlphavantageAPIClient and search for the "Time Series (Daily)" key. After getting that key
     * it will iterate through all entries (dates) while saving the corresponding adjusted close price values to a double.
     * The date and the value are then used to create a StockPrice object which is the added to an ArrayList<StockPrice>
     *     which is also returned. This
     * @param APIManager used APIManager containing the  AlphavantageAPIClient used for this operation
     *                   (APIFunction has to be "TIME_SERIES_DAILY_ADJUSTED")
     * @return ArrayList containing all historical Stock Prices
     */
     public static ArrayList<FinancialDataObject> extractHistoricalStockPrices(APIManager APIManager) {
         ArrayList<FinancialDataObject> stockPriceList = new ArrayList<>();
         JSONObject jsonObject = APIManager.getDailyTimeSeriesClient().getResponse().getBody().getObject().getJSONObject("Time Series (Daily)");
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

     public static ArrayList<FinancialDataObject> extractIncomeStatementData(String keyName, APIManager APIManager){
         ArrayList<FinancialDataObject> IncomeStatementDataList = new ArrayList<>();
         JSONArray jsonArray= APIManager.getIncomeStatementClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
         for(int i=0; i<jsonArray.length();i++){
             String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

             Double value = jsonArray.getJSONObject(i).getDouble(keyName);
             FinancialDataObject dataObject = new FinancialDataObject(keyName,value,date);
             IncomeStatementDataList.add(dataObject);
         }
         return IncomeStatementDataList;
     }

    public static ArrayList<FinancialDataObject> extractBalanceSheetData(String keyName, APIManager APIManager){
        ArrayList<FinancialDataObject> BalanceSheetDataList = new ArrayList<>();
        JSONArray jsonArray= APIManager.getIncomeStatementClient().getResponse().getBody().getObject().getJSONArray("quarterlyReports");
        for(int i=0; i<jsonArray.length();i++){
            String date = jsonArray.getJSONObject(i).getString("fiscalDateEnding");

            Double value = jsonArray.getJSONObject(i).getDouble(keyName);
            FinancialDataObject dataObject = new FinancialDataObject(keyName,value,date);
            BalanceSheetDataList.add(dataObject);
        }
        return BalanceSheetDataList;
    }



    }

