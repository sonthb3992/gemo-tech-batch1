package Unit_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public class Bartender {
    private List<IMenuItem> orderedItems = new ArrayList<IMenuItem>();
    private Scanner scanner = new Scanner(System.in);
    private final double taxRate = 0.0725;

    //Counter works

    public void TakingOrder() {
        boolean stop = false;
        while (!stop) {
            System.out.println("What would you like to order?");
            System.out.println("0: I've completed my order.");
            System.out.println("1: Drink");
            System.out.println("2: Breakfast");

            int selected = scanner.nextInt();
            scanner.nextLine();
            
            switch (selected) {
                case 0:
                    stop = true;
                    break;
                case 1:
                    var drink = this.prepareDrink();
                    if (drink != null)
                        this.orderedItems.add(drink);
                    break;
                case 2:
                    var breakfast = this.prepareBreakfast();
                    if (breakfast != null)
                        this.orderedItems.add(breakfast);
                    break;
                default:
            }
            System.out.println(String.format("You have %d item(s) ordered.", this.orderedItems.size()));
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println(String.format("%-60s %5d", "Number of item(s) in your order:", this.orderedItems.size()));
    }

    public void PrintingBill () {
        double sum = 0;
        System.out.println("YOUR ORDER");
        System.out.println(String.format("%-5s %-65s %10s","#", "Item", "Price"));
        for (int i = 0; i < this.orderedItems.size(); i++) {
            var item = this.orderedItems.get(i);
            System.out.println(String.format("%-5d %-65s %10.2f", i+1, item.getDesc(), item.getPrice()));
            sum+=item.getPrice();
        }

        System.out.println();
        System.out.println(String.format("%-70s %10.2f", "Total before tax", sum));
        System.out.println(String.format("%-70s %10.2f", "Tax", sum * this.taxRate));
        System.out.println(String.format("%-70s %10.2f", "GRAND TOTAL", sum * (1+this.taxRate)));

    }

    //==============================================================================//
    public IMenuItem prepareBreakfast() {
        return Breakfast_AskForItem();
    }

    private IMenuItem Breakfast_AskForItem() {
        boolean stop = false;
        while (!stop) {
            System.out.println("Which Breakfast would you like?");
            System.out.println("0: Maybe next time");
            System.out.println("1: Sandwich");
            System.out.println("2: Bagel");

            int selected = scanner.nextInt();
            scanner.nextLine();
            
            switch (selected) {
                case 0:
                    return null;
                case 1:
                    var sandwich = new Sandwich();
                    return Breakfast_AskForSandwichTopping(sandwich);
                case 2:
                    var bagel = new Bagel();
                    return Breakfast_AskForBagelTopping(bagel); 
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
        return null;
    }

    private IMenuItem Breakfast_AskForBagelTopping(IMenuItem item) {
        System.out.println("Would you like to have your bagel with cheese cream (instead of butter) (y/n)?");
        String response = scanner.nextLine();   
        var bagel = new BagelDecorator(item);
        bagel.setHasCheese(response.equalsIgnoreCase("y"));
        return bagel;
    }

    private IMenuItem Breakfast_AskForSandwichTopping(IMenuItem item) {
        System.out.println("Would you like to have your sandwich with turkey (instead of egg) (y/n)?");
        String response = scanner.nextLine();   
        var sandwich = new SandwichDecorator(item);
        sandwich.setHasTurkey(response.equalsIgnoreCase("y"));
        return sandwich;
    }

    //==================================================================================//

    public IMenuItem prepareDrink() {

        var drink = Drink_AskForItem();
        if (drink == null)
            return null;
        
        drink = Drink_AskForServingStyle(drink);
        if (drink == null)
            return null;

        drink = Drink_AskForServingSize(drink);
        if (drink == null)
            return null;

        drink = this.Drink_AskForTopping_WhippedCream(drink);

        drink = this.Drink_AskForTopping_Milk(drink);

        drink = this.Drink_AskForTopping_ChocolateSauce(drink);
        
        return drink;
    }

    private IDrinkItem Drink_AskForItem() {
        boolean stop = false;
        while (!stop) {
            System.out.println("Which drink would you like?");
            System.out.println("0: Maybe next time");
            System.out.println("1: Coffee");
            System.out.println("2: Milk tea");

            int selected = scanner.nextInt();
            scanner.nextLine();
            
            switch (selected) {
                case 0:
                    return null;
                case 1:
                    return new Coffee();
                case 2:
                    return new MilkTea();
                default:
                    System.out.println("Invalid selection, please try again.");
            }
        }
        return null;
    }

    private IDrinkItem Drink_AskForServingStyle(IDrinkItem item) {
        if (!(item instanceof DrinkItem))
            return null;

        var drink = (DrinkItem)item;

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
                return drink;
            }
            if (selected == -1) {
                System.out.println("Customer don't want to buy :(");
                return null;
            }
        }
        scanner.close();
        return null;
    }

    private IDrinkItem Drink_AskForServingSize(IDrinkItem item) {
        if (!(item instanceof DrinkItem))
            return null;

        var drink = (DrinkItem)item;

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
                    return drink;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (selected == -1) {
                System.out.println("Customer don't want to buy :(");
                return null;
            }
        }
        scanner.close();
        return null;
    }

    private IDrinkItem Drink_AskForTopping_WhippedCream(IDrinkItem item) {
        System.out.println("Would you like to add whipped cream topping (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            return new WhipedCreamDecorator(item);
        }
        return item;
    }

    private IDrinkItem Drink_AskForTopping_Milk(IDrinkItem item) {
        System.out.println("Would you like to add milk (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            System.out.println("Would you like to add almond milk (y/n)?");
            response = scanner.nextLine();
            return new MilkDecorator(item, response.equalsIgnoreCase("y"));
        }
        return item;
    }

    private IDrinkItem Drink_AskForTopping_ChocolateSauce(IDrinkItem item) {
        // if not hot drink, don't ask
        try {
            if (item.getStyle() != DrinkServingStyle.Hot) {
                return item;
            }
        } catch (Exception e) {
            System.out.println((e.getMessage()));
            return null;
        }
       
        System.out.println("Would you like to add Chocolate Sauce, 2 first pumps is free (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            while (true) {            
                System.out.println("How many pumps would you like, 1-6 pumps (y/n)?");
                int p = scanner.nextInt();
                if (p > 0 && p <= 6) {
                    return new ChocolateSauceDecorator(item, p);
                }
            }
        }
        return item;
    }

    
}
