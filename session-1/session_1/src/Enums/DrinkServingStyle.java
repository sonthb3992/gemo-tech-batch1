package Enums;

public enum DrinkServingStyle {
    Hot(0),
    Cold(0),
    Blended(1);

    private double addedPrice;

    public double getAddedPrice() {
        return addedPrice;
    }

    private DrinkServingStyle(double added_price) {
        addedPrice = added_price;
    }
}