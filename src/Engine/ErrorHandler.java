public class ErrorHandler {
    private static MyUtils utils;
    private static Constants CONSTANTS;
    ErrorHandler(Constants c, MyUtils u) {
        CONSTANTS = c;
        utils = u;
    }
    public static boolean validateError(double doubleInput) {
        boolean valid = true;
        if (doubleInput == CONSTANTS.PRICE_ERROR) {
            utils.print("PRICE ERROR");
            valid = false;
        }
        if (doubleInput == CONSTANTS.QUANTITY_ERROR) {
            utils.print("QUANTITY ERROR");
            valid = false;
        }
        return valid;
    }
}

