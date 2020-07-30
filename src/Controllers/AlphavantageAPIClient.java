package Controllers;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class AlphavantageAPIClient {



    private         String        apikey;
    private final String        host        =       "https://www.alphavantage.co/";
    private       String        function;
    private       String        symbol;
    private       HttpResponse<JsonNode>    response;



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


    public void establishConnectinToAPI() throws Exception{
        //Json response
        response = Unirest.get(host+"query?").
                queryString("function",function).queryString("symbol",symbol).
                queryString("apikey",apikey).asJson();

    }

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



}
