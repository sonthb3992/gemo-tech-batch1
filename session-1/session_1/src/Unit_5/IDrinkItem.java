package Unit_5;

public interface IDrinkItem extends IMenuItem {

    Enums.DrinkServingStyle getStyle();

    Enums.DrinkSize getSize();

}