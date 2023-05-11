package Unit_5;

public abstract class DrinkItem implements IMenuItem {
    protected double basePrice;

    @Override
    public double getPrice() {
        return this.basePrice;
    }

}
