import java.util.Set;

public class Calculator {
    private static MyUtils utils = new MyUtils();


    private static Prices prices;
    private static Inventory inventory;
    private static Constants CONSTANTS = new Constants();
    private static ErrorHandler errorHandler = new ErrorHandler(CONSTANTS, utils);
    public Calculator(Prices p, Inventory i) {
        prices = p;
        inventory = i;
    }
    public static double sumCaseValue(String caseName){

        // get and validate the price
        double price = prices.getPrice(caseName);
        if (!prices.isValid(price)){
            return CONSTANTS.PRICE_ERROR;
        }

        // get and validate quantity
        int quantity = inventory.getQuantity(caseName);
        if (!inventory.isValid(quantity)) {
            return CONSTANTS.QUANTITY_ERROR;
        }

        // calculate total value for this particular case.
        double value = price*quantity;
        return value;
    }

    public static double sumInventoryValue() {
        double totalSum = 0.0;
        utils.print("Attempting to calculate total inventory value...");

        // reading keyset into an array
        Set<Object> casesSet = inventory.getKeySet();
        String[] casesNames = new String[casesSet.size()];
        int i = 0;
        for (Object caseName : casesSet) {
            casesNames[i++] = String.valueOf(caseName);
        }

        // reading from array
        for (String caseName : casesNames) {
            double value = sumCaseValue(caseName);
            if (errorHandler.validateError(value)) {
                totalSum += value;
                utils.print("");
            };

        }
        return 0.0;
    }
}
