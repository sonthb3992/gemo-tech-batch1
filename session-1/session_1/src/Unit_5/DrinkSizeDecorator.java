package Unit_5;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public class DrinkSizeDecorator extends MenuItemDecorator {

    private DrinkSize drinkSize;

    public void setDrinkSize(DrinkSize drinkSize) throws Exception {

        if (this.item instanceof ServingStyleDecorator) {
            ServingStyleDecorator decorator = (ServingStyleDecorator) this.item;
            Enums.DrinkServingStyle style = decorator.getStyle();
            if (style == DrinkServingStyle.Cold || style == DrinkServingStyle.Blended || drinkSize == DrinkSize.S
                    || drinkSize == DrinkSize.M) {
                this.drinkSize = drinkSize;
                return;
            }
            throw new Exception("Invalid size selected.");
        }
        throw new Exception("Serving style not found.");
    }

    public DrinkSizeDecorator(IMenuItem item, DrinkSize size) throws Exception {
        super(item);
        this.setDrinkSize(size);
    }

    @Override
    public String getDesc() {
        return String.format("%s, size %s", this.item.getDesc(), this.drinkSize.toString());
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + this.drinkSize.getUpSizePrice();
    }

}
