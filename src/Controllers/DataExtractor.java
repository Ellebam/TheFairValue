package Controllers;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public  class DataExtractor {

    /**
     * Static method for extracting Data from the JSON Objects created by the AlphavantageAPIClient.
     * This method is used by the various Data Classes (e.g. CompanyOverviewData Class)
     * @param client used AlphavantageApiClient object
     * @param variableName variable that needs to be searched for inside JSON Object
     * @return String countaining desired data
     */
    public static String extractData2String(AlphavantageAPIClient client,String variableName){
        String dataVariable = client.getResponse().getBody().getObject().getString(variableName);
        return dataVariable;
    }
}
