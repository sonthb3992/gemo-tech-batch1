package Unit_5;

import Enums.DrinkServingStyle;

public class ServingStyleDecorator extends MenuItemDecorator {

    private DrinkServingStyle style;

    public DrinkServingStyle getStyle() {
        return style;
    }

    public ServingStyleDecorator(IMenuItem item, DrinkServingStyle style) {
        super(item);
        this.style = style;
    }

    @Override
    public String getDesc() {
        return String.format("%s, %s", this.item.getDesc(), this.style.toString());
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + this.style.getAddedPrice();
    }

}
