package Enums;

public enum Beverage {
    Coffee(2),
    MilkTea(2.25);

    private double basePrice;

    public double getBasePrice() {
        return basePrice;
    }

    private Beverage(double basePrice) {
        this.basePrice = basePrice;
    }

}