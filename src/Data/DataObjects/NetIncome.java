package Data.DataObjects;

public class NetIncome {

    private double value;
    private String date;

    public NetIncome(double value, String date){
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
        return "Net Income{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}'+ "\n" ;
    }
}
