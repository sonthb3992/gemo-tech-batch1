package Unit_5;

import java.util.Scanner;

import Enums.DrinkServingStyle;
import Enums.DrinkSize;

public class Bartender {
    private IMenuItem item;
    private Scanner scanner = new Scanner(System.in);

    public void prepareItem() {

        if (!AskForItem())
            return;

        if (!AskForServingStyle())
            return;

        if (!AskForServingSize())
            return;

        this.AskForTopping_WhippedCream();
        this.AskForTopping_Milk();
        this.AskForTopping_ChocolateSauce();

        System.out.println(this.item.getDesc());
        System.out.println(this.item.getPrice());

    }

    private boolean AskForItem() {
        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
        return false;
    }

    private boolean AskForServingStyle() {
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
                this.item = new ServingStyleDecorator(this.item, DrinkServingStyle.values()[selected]);
                return true;
            }
            if (selected == -1) {
                System.out.println("Customer don't1 want to buy :(");
                return false;
            }
        }
        scanner.close();
        return false;
    }

    private boolean AskForServingSize() {
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
                    this.item = new DrinkSizeDecorator(this.item, DrinkSize.values()[selected]);
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
            ServingStyleDecorator s = (ServingStyleDecorator) this.item;
            if (s.getStyle() != DrinkServingStyle.Hot) {
                return;
            }
        } catch (Exception e) {
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
