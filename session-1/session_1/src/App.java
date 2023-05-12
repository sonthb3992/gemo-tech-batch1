// import Unit_1.Unit1;
// import Unit_2.Unit2;

import Unit_5.Bartender;

public class App {
    public static void main(String[] args) throws Exception {

        // Unit 1
        // System.out.println("Preparing drink for the 1st requirement!");
        // if (Unit1.prepareDrink()) {
        // System.out.println("Prepare drink completed!");
        // System.out.println(String.format("Your order: %s, the total price is: %.1f",
        // Unit1.getDrink().getDesc(),
        // Unit1.getDrink().calculatePrice1()));
        // }

        // Unit 2
        // System.out.println("Preparing drink for the 2nd requirement!");
        // if (Unit2.prepareDrink()) {
        // System.out.println("Prepare drink completed!");
        // System.out.println(String.format("Your order: %s, the total price is: %.1f",
        // Unit2.getDrink().getDesc(),
        // Unit2.getDrink().calculatePrice2()));
        // }

        Bartender bartender = new Bartender();
        bartender.TakingOrder();
        bartender.PrintingBill();
    }
}
