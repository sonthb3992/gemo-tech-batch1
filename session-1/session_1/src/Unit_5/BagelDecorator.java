package Unit_5;

public class BagelDecorator extends BreakfastItemDecorator {

    private boolean hasCheese;

    public void setHasCheese(boolean hasCheese) {
        this.hasCheese = hasCheese;
    }

    public BagelDecorator(IMenuItem item) {
        super(item);
        this.basePrice = 3;
    }

    @Override
    public String getDesc() {
        String topping = this.hasCheese ? "cream cheese" : "butter";
        return String.format("%s, with %s", this.item.getDesc(), topping);
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + (this.hasCheese ? 0.5 : 0);
    }

}
