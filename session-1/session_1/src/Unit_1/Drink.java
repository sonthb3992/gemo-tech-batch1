package Unit_1;

import java.util.ArrayList;
import java.util.List;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public class Drink {

    final int basePrice = 2;

    private Enums.DrinkServingStyle drinkServingStyle = DrinkServingStyle.Hot;

    public void setDrinkServingStyle(Enums.DrinkServingStyle drinkServingStyle) {
        this.drinkServingStyle = drinkServingStyle;
    }

    public Enums.DrinkServingStyle getDrinkServingStyle() {
        return drinkServingStyle;
    }

    private Enums.DrinkSize drinkSize = DrinkSize.S;

    public Enums.DrinkSize getDrinkSize() {
        return drinkSize;
    }

    public boolean setDrinkSize(Enums.DrinkSize drinkSize) {

        // Accept the drink size if the drink serving style not set, or is Cold or
        // Blended
        if (this.drinkServingStyle == null || this.drinkServingStyle.equals(DrinkServingStyle.Cold)
                || this.drinkServingStyle.equals(DrinkServingStyle.Blended)) {
            this.drinkSize = drinkSize;
            return true;
        }

        if (drinkSize.equals(DrinkSize.L) || drinkSize.equals(DrinkSize.XL)) {
            return false;
        }

        this.drinkSize = drinkSize;
        return true;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public Drink() {
        super();
    }

    private List<Topping> toppings = new ArrayList<Topping>();

    public String getDesc() {
        String desc = this.drinkServingStyle.toString() + " coffee, size ";
        desc += this.drinkSize.toString();
        if (this.toppings.size() > 0) {
            desc += ", with ";
            for (Topping topping : toppings) {
                desc += topping.getToppingName();
            }
        }
        return desc;
    }

    public double calculatePrice1() {
        double price = basePrice;
        price += this.drinkSize.getUpSizePrice();
        price += this.drinkServingStyle.getAddedPrice();
        for (Topping topping : toppings) {
            price += topping.getToppingPrice();
        }
        return price;
    }

    public Object calculatePrice2() {
        return null;
    }
}
