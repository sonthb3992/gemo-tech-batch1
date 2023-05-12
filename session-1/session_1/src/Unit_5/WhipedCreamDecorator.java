package Unit_5;

public class WhipedCreamDecorator extends DrinkItemDecorator {

    public WhipedCreamDecorator(IDrinkItem item) {
        super(item);
    }

    @Override
    public String getDesc() {
        return String.format("%s, whiped cream", this.item.getDesc());
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + 0.5;
    }
    
}
