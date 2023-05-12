package Unit_5;

public class ChocolateSauceDecorator extends DrinkItemDecorator {

    private int pumps;

    public ChocolateSauceDecorator(IMenuItem item, int pumps) {
        super(item);
        this.pumps = pumps;
    }

    @Override
    public String getDesc() {
        return String.format("%s, %d pumps of chocolate sauce", this.item.getDesc(), this.pumps);
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + Math.max(0, pumps - 2) * 0.5;
    }

}
