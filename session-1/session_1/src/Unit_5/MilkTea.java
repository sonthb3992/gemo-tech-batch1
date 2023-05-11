package Unit_5;

public class MilkTea extends DrinkItem {

    public MilkTea() {
        this.basePrice = 2.25;
    }

    @Override
    public String getDesc() {
        return "Milk tea";
    }

}
