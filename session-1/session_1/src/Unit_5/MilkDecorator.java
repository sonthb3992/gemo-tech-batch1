package Unit_5;

public class MilkDecorator extends DrinkItemDecorator {

    private boolean isAlmond;

    public MilkDecorator(IMenuItem item, boolean isAlmond) {
        super(item);
        this.isAlmond = isAlmond;
    }

    @Override
    public String getDesc() {
        String milk = this.isAlmond ? "almond milk" : "milk";
        return String.format("%s, %s", this.item.getDesc(), milk);
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + (this.isAlmond ? 0.5 : 0);
    }

}
