package Unit_5;

public abstract class BreakfastItem implements IMenuItem {
    protected double basePrice;

    @Override
    public double getPrice() {
        return this.basePrice;
    }
    
}
