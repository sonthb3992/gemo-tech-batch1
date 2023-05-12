package Unit_5;

import java.util.Scanner;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public class Bartender {
    private IMenuItem item;
    private Scanner scanner = new Scanner(System.in);



    public void prepareBreakfast() {
        if (!Breakfast_AskForItem())
            return;
        
        System.out.println(this.item.getDesc());
        System.out.println(this.item.getPrice());
    }

    private boolean Breakfast_AskForItem() {
        boolean stop = false;
        while (!stop) {
            System.out.println("Which Breakfast_AskForItem would you like?");
            System.out.println("0: Maybe next time");
            System.out.println("1: Sandwich");
            System.out.println("2: Bagel");

            int selected = scanner.nextInt();
            scanner.nextLine();
            
            switch (selected) {
                case 0:
                    return false;
                case 1:
                    this.item = new Sandwich();
                    Breakfast_AskForSandwichTopping();
                    return true;
                case 2:
                    this.item = new Bagel();
                    Breakfast_AskForBagelTopping();
                    return true;
                default:
            }
        }
        return false;
    }

    private void Breakfast_AskForBagelTopping() {
        System.out.println("Would you like to have your bagel with cheese cream (instead of butter) (y/n)?");
        String response = scanner.nextLine();   
        var bagel = new BagelDecorator(this.item);
        bagel.setHasCheese(response.equalsIgnoreCase("y"));
        this.item = bagel;
    }

    private void Breakfast_AskForSandwichTopping() {
        System.out.println("Would you like to have your sandwich with turkey (instead of egg) (y/n)?");
        String response = scanner.nextLine();   
        var sandwich = new SandwichDecorator(this.item);
        sandwich.setHasTurkey(response.equalsIgnoreCase("y"));
        this.item = sandwich;
    }

    public void prepareDrink() {

        if (!AskForItem_Drink())
            return;

        if (!AskForServingStyle_Drink())
            return;

        if (!AskForServingSize_Drink())
            return;

        this.AskForTopping_WhippedCream();
        this.AskForTopping_Milk();
        this.AskForTopping_ChocolateSauce();

        System.out.println(this.item.getDesc());
        System.out.println(this.item.getPrice());

    }

    private boolean AskForItem_Drink() {
        boolean stop = false;
        while (!stop) {
            System.out.println("Which drink would you like?");
            System.out.println("0: Maybe next time");
            System.out.println("1: Coffee");
            System.out.println("2: Milk tea");

            int selected = scanner.nextInt();
            switch (selected) {
                case 0:
                    return false;
                case 1:
                    this.item = new Coffee();
                    return true;
                case 2:
                    this.item = new MilkTea();
                    return true;
                default:
            }
        }
        return false;
    }

    private boolean AskForServingStyle_Drink() {
        if (!(this.item instanceof DrinkItem))
            return false;

        var drink = (DrinkItem)this.item;

        boolean stop = false;
        while (!stop) {
            System.out.println("How would you like your drink to be served?");
            System.out.println("0: I want to cancel my order");
            for (int i = 0; i < Enums.DrinkServingStyle.values().length; i++) {
                System.out.println(String.format("%d: %s", i + 1, DrinkServingStyle.values()[i]));
            }
            int selected = scanner.nextInt() - 1;
            scanner.nextLine();
            if (selected >= 0 && selected < Enums.DrinkServingStyle.values().length) {
                drink.setStyle(DrinkServingStyle.values()[selected]);
                return true;
            }
            if (selected == -1) {
                System.out.println("Customer don't want to buy :(");
                return false;
            }
        }
        scanner.close();
        return false;
    }

    private boolean AskForServingSize_Drink() {
        if (!(this.item instanceof DrinkItem))
        return false;

        var drink = (DrinkItem)this.item;

        boolean stop = false;
        while (!stop) {
            System.out.println("Which size would you like?");
            System.out.println("0: I want to cancel my order");
            for (int i = 0; i < DrinkSize.values().length; i++) {
                System.out.println(String.format("%d: %s", i + 1, DrinkSize.values()[i]));
            }
            int selected = scanner.nextInt() - 1;
            scanner.nextLine();
            if (selected >= 0 && selected < Enums.DrinkSize.values().length) {
                try {
                    drink.setSize(DrinkSize.values()[selected]);
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (selected == -1) {
                System.out.println("Customer don't want to buy :(");
                return false;
            }
        }
        scanner.close();
        return false;
    }

    private void AskForTopping_WhippedCream() {
        System.out.println("Would you like to add whipped cream topping (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            this.item = new WhipedCreamDecorator(this.item);
        }
    }

    private void AskForTopping_Milk() {
        System.out.println("Would you like to add milk (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            System.out.println("Would you like to add almond milk (y/n)?");
            response = scanner.nextLine();
            this.item = new MilkDecorator(this.item, response.equalsIgnoreCase("y"));
        }
    }

    private void AskForTopping_ChocolateSauce() {
        // if not hot drink, don't ask
        try {
            DrinkItem drink = (DrinkItem)this.item;
            if (drink.getStyle() != DrinkServingStyle.Hot) {
                return;
            }
        } catch (Exception e) {
            System.out.println((e.getMessage()));
            return;
        }
       
        System.out.println("Would you like to add Chocolate Sauce, 2 first pumps is free (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("How many pumps would you like, 1-6 pumps (y/n)?");
                int p = scanner.nextInt();
                if (p > 0 && p <= 6) {
                    this.item = new ChocolateSauceDecorator(item, p);
                    break;
                }
            }
        }
    }
}
