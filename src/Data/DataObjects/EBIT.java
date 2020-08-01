package Data.DataObjects;

public class EBIT {


    private double value;
    private String date;

    public EBIT(double value, String date){
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
        return "EBIT{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}'+ "\n" ;
    }
}
