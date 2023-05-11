package Unit_1;

import java.util.Scanner;

public class Unit1 {
    private static Drink drink;

    public static Drink getDrink() {
        return drink;
    }

    public static boolean prepareDrink() {

        // Set the drink to null
        Unit1.drink = null;

        Scanner scanner = new Scanner(System.in);

        // Ask for serving style
        Drink current = new Drink();
        System.out.println("How do you want your coffee to be served?");
        for (int i = 0; i < Enums.DrinkServingStyle.values().length; i++) {
            System.out.println(String.format("%d: %s", i + 1, Enums.DrinkServingStyle.values()[i]));
        }

        int selection = scanner.nextInt() - 1;
        if (selection < 0 || selection >= Enums.DrinkServingStyle.values().length) {
            System.out.println("Invalid selection.");
            scanner.close();
            return false;
        }
        current.setDrinkServingStyle(Enums.DrinkServingStyle.values()[selection]);

        // Ask for serving size
        System.out.println("Which size would you like?");
        for (int i = 0; i < Enums.DrinkSize.values().length; i++) {
            System.out.println(String.format("%d: %s", i + 1, Enums.DrinkSize.values()[i]));
        }

        selection = scanner.nextInt() - 1;
        if (selection < 0 || selection >= Enums.DrinkSize.values().length) {
            System.out.println("Invalid selection.");
            scanner.close();
            return false;
        }
        if (!current.setDrinkSize(Enums.DrinkSize.values()[selection])) {
            System.out.println("We cannot serve the selected size.");
            scanner.close();
            return false;
        }

        scanner.nextLine();

        // Ask for topping
        System.out.println("Would you like to add whipped cream topping (y/n)?");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            current.addTopping(new Topping("Whipped cream", 0.5));
        }

        scanner.close();
        Unit1.drink = current;
        return true;

    }

    public static double getTotalPrice() {
        return drink.calculatePrice1();
    }
}
