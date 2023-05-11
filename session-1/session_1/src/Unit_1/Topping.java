package Unit_1;

public class Topping {
    private String toppingName;

    public String getToppingName() {
        return toppingName;
    }

    private double toppingPrice;

    public double getToppingPrice() {
        return toppingPrice;
    }

    public Topping(String toppingName, double toppingPrice) {
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }
}
