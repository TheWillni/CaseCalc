public class Driver {
    public static MyUtils utils = new MyUtils();
    public static void main(String[] args) {
        // @TODO Grab recent price list
        // The idea is to use the price list from previous time program was run.
        Prices prices = new Prices();

        // tests
        double priceTest = prices.getPrice("PHOENIX");
        String caseName = "PHOENIX";
        int id = CaseID.valueOf(caseName).getId();
        utils.print("ID is: " + id);
        utils.print(priceTest);


        // @TODO Update price list
        // Update our price list to most recent prices.
        // If we cannot fetch new prices, use old prices and notify user.
        // Perhaps add a manual price entry UI?

        // @TODO Load users case list
        // give them a UI to update their amounts too

        // @TODO Calculate total inventory value

        // @TODO Provide a detailed breakdown in a .txt file
        //print("hello");
    }

}
