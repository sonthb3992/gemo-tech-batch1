package Unit_5;

public abstract class DrinkItemDecorator extends DrinkItem {

    protected IMenuItem item;
    public DrinkItemDecorator(IMenuItem item) {
        super();
        this.item = item;
    }
}
