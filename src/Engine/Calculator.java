import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public class Calculator {
    private MyUtils utils;
    private static Prices prices;
    private static Constants CONSTANTS = new Constants();
    private static ErrorHandler errorHandler;
    private static Config cfg;
    public Calculator(MyUtils util, Config config) {
        // makes our new Price object
        utils = util;
        cfg = config;
        prices =  new Prices(utils, cfg);
        errorHandler = new ErrorHandler(CONSTANTS, utils);
    }
    public double sumCaseValue(String caseName, Inventory inventory){

        // get and validate quantity
        int quantity = inventory.getQuantity(caseName);
        if (!inventory.isValid(quantity)) {
            return CONSTANTS.QUANTITY_ERROR;
        }

        // get and validate the price
        double price = prices.getPrice(caseName);
        if (!prices.isValid(price)){
            return CONSTANTS.PRICE_ERROR;
        }

        // calculate total value for this particular case.
        double conversionRate = Constants.CONVERSION_RATE;
        double value = price*quantity*conversionRate;

        utils.print("Total value for " + caseName +" was $" + utils.toTwoDeci(value));
        return value;
    }

    public double sumInventoryValue(Inventory inventory) {
        double totalSum = 0.0;
        utils.print("Attempting to calculate total inventory value...");
        String[] casesNames = getCaseNames(inventory);
        prices.fetch(casesNames, cfg);
        // reading from array
        for (String caseName : casesNames) {
            double caseValue = sumCaseValue(caseName, inventory);
            if (errorHandler.validateError(caseValue)) {
                totalSum += caseValue;
                //@todo currency converter
                utils.print("Inventory value is now $" + utils.toTwoDeci(totalSum));
            };

        }
        // @TODO remove this
        //utils.print(Config.DEFAULT_DATE);
        return totalSum;
    }

    public static String[] getCaseNames(Inventory inventory) {

        // reading keyset into an array
        Set<Object> casesSet = inventory.getKeySet();
        String[] casesNames = new String[casesSet.size()];
        int i = 0;
        for (Object caseName : casesSet) {
            casesNames[i++] = String.valueOf(caseName);
        }
        return casesNames;
    }


}
