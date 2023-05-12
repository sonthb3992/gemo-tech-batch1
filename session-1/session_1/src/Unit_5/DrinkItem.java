package Unit_5;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public abstract class DrinkItem implements IDrinkItem {
    protected double basePrice;

    protected Enums.DrinkServingStyle style;
    @Override
    public Enums.DrinkServingStyle getStyle() {
        return style;
    }

    public void setStyle(Enums.DrinkServingStyle style) {
        this.style = style;
    }

    protected Enums.DrinkSize size;

    @Override
    public Enums.DrinkSize getSize() {
        return size;
    }

    public void setSize(Enums.DrinkSize value) throws Exception {

        if (style == DrinkServingStyle.Cold || style == DrinkServingStyle.Blended || value == DrinkSize.S
                || value == DrinkSize.M) {
            this.size   = value;
            return;
        }
        throw new Exception("Invalid Drink Size");
    }

    @Override
    public double getPrice() {
        return this.basePrice + this.size.getUpSizePrice() + this.style.getAddedPrice();
    }
}
