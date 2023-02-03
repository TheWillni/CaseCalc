import java.util.Set;

public class Driver {
    public static Prices prices = new Prices();
    public static Inventory inventory = new Inventory();
    public static Calculator calc = new Calculator(prices, inventory);
    public static MyUtils utils = new MyUtils();
    public static void main(String[] args) {
        // @TODO Grab recent price list
        // The idea is to use the price list from previous time program was run.


        // tests
        utils.print(prices.getPrice("PHOENIX"));



        // @TODO Update price list
        // Update our price list to most recent prices.
        // If we cannot fetch new prices, use old prices and notify user.
        // Perhaps add a manual price entry UI?

        // @TODO Load users case list
        // give them a UI to update their amounts too


        // tests
        utils.print(inventory.getQuantity("PHOENIX"));

        // @TODO Calculate total inventory sum for a specific case
        utils.print(calc.sumCaseValue("PHOENIX"));

        // @TODO Calculate total inventory value

        calc.sumInventoryValue();



        // @TODO Provide a detailed breakdown in a .txt file
        //print("hello");
    }


}
