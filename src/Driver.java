public class Driver {
    //public Constants c = new Constants;
    public static void main(String[] args) {
        // @TODO Grab recent price list
        // The idea is to use the price list from previous time program was run.
        Prices prices = new Prices();
        String caseName = "BREAKOUT";
        int id = CaseID.valueOf(caseName).getId();
        System.out.println("ID is: " + id + "\n");


        // @TODO Update price list
        // Update our price list to most recent prices.
        // If we cannot fetch new prices, use old prices and notify user.
        // Perhaps add a manual price entry UI?

        // @TODO Load users case list
        // give them a UI to update their amounts too

        // @TODO Calculate total inventory value

        // @TODO Provide a detailed breakdown in a .txt file
    }

}
