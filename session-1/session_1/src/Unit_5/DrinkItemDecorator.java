package Unit_5;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public abstract class DrinkItemDecorator implements IDrinkItem {

    protected IDrinkItem item;

    public DrinkItemDecorator(IDrinkItem item) {
        super();
        this.item = item;
    }

    @Override
    public DrinkSize getSize() {
        return this.item.getSize();
    }

    @Override
    public DrinkServingStyle getStyle() {
        return this.item.getStyle();
    }


}
