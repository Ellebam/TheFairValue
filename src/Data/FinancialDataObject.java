package Data;

/**
 * This class represents the most basic object for storing financial data. It is used in the various other classes in
 * form of ArrayLists containing FinancialDataObjects for each data entry
 */
public class FinancialDataObject {
    private String name;
    private double value;
    private String date;

    /**
     * The three instance variable represent the main information of the data point which needs to be stored
     * @param name name of the data point (e.g. "totalRevenue", same as key used for the data search from the API)
     * @param value value of the data point as a double (e.g. 849202029842.929234)
     * @param date date of the data point entry as a string (YYYY-MM-dd)
     */
    public FinancialDataObject(String name, double value, String date){
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+"{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}'+ "\n" ;
    }
}
