package Data;

public class FinancialDataObject {
    private String name;
    private double value;
    private String date;

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
