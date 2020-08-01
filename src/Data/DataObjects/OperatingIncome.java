package Data.DataObjects;

public class OperatingIncome //netIncomeFromContinuingOperations
 {

    private double value;
    private String date;

    public OperatingIncome(double value, String date){
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operating Income{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}'+ "\n" ;
    }
}
