package Controllers;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
 * The AlphavantageAPIClient Class builds a connection to the Database of Alpha Vantage to retrieve
 * real time market data for the various Data Classes. Its constructor needs the API-Function,
 * a ticker symbol of the desired stock (e.g. MCD) and the users APIKey.
 */
public class AlphavantageAPIClient {



    private             String                      apikey;
    private final       String                      host        =       "https://www.alphavantage.co/";
    private final       String                      outputSize  =       "full";
    private             String                      function;
    private             String                      symbol;
    private             HttpResponse<JsonNode>      response;


    /**
     * creates an AlphavantageAPIClient object for other classes to access JSON data of the called function
     * @param function based on Alpha Vantage API Documentatio. Used for different database entries
     *                 (e.g. COmpany Overview or annual reports)
     * @param symbol    used for finding the desired company
     * @param apikey    user key for accessing the database
     * @throws Exception    might throw timeout Exception, want to catch it at Front-End level
     */
    public AlphavantageAPIClient(String function, String symbol, String apikey) throws Exception{
        this.symbol = symbol;
        this.function = function;
        this.apikey = apikey;
        establishConnectinToAPI();
    }


    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    /**
     * builds up a connection to the Alpha Vantage API
     * @throws Exception for downphases of the server
     */
    public void establishConnectinToAPI() throws Exception{
        //Json response
        response = Unirest.get(host+"query?").
                queryString("function",function).queryString("symbol",symbol).queryString("outputsize",outputSize).
                queryString("apikey",apikey).asJson();

    }

    /**
     * used for testing
     * @return returns the JSON response as a nicely formatted String
     */
    public String getJSONResponseAsString(){

        //parsing to Json String
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(response.getBody().toString());
        String prettyJsonString = gson.toJson(jsonElement);


        return prettyJsonString;
    }




    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public HttpResponse<JsonNode> getResponse() {
        return response;
    }

    public void setResponse(HttpResponse<JsonNode> response) {
        this.response = response;
    }
}
