package Enums;

public enum DrinkSize {
    S(0),
    M(0.5),
    L(1),
    XL(1.5);

    private double upSizePrice;

    public double getUpSizePrice() {
        return upSizePrice;
    }

    private DrinkSize(double upsize_price) {
        upSizePrice = upsize_price;
    }

}
