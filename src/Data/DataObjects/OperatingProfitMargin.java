package Data.DataObjects;

public class OperatingProfitMargin {

    private double value;
    private String date;

    public OperatingProfitMargin(OperatingIncome operatingIncome, TotalRevenue totalRevenue){

        this.value = operatingIncome.getValue()/totalRevenue.getValue();
        this.date = operatingIncome.getDate();
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
        return "Operating Profit Margin{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}'+ "\n" ;
    }
}
