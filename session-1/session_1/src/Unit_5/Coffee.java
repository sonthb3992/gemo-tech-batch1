package Unit_5;

public class Coffee extends DrinkItem {

    public Coffee() {
        this.basePrice = 2;
    }

    @Override
    public String getDesc() {
        return String.format("Coffee, %s, size %s", this.style, this.size);
    }
}
