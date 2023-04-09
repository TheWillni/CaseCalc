import java.util.*;
import java.util.regex.Pattern;

public class Prices {
    private static Properties priceInfo;

    private static MyUtils utils;
    private static UrlConnectionReader WebGrabber = new UrlConnectionReader();
    private static Config cfg;
    private Constants constants = new Constants();

    // we store all our data for our current cases in a map, where our case name links to its price at current date

    public Prices(MyUtils util, Config config) {
        //prop = new Properties()
        // @todo remove this
        utils = util;
        cfg = config;
        priceInfo = new Properties();
    }

    public static void setPrice(String caseName, double price) {
        priceInfo.setProperty(caseName, doubleToString(price));
    }

    public double getPrice(String caseName) {
        try {
            String priceString = priceInfo.getProperty(caseName);
            return Double.valueOf(priceString);
        }
        catch (Exception e) {
            // override case price to error code
            return constants.PRICE_ERROR;
        }

    }
    private static String doubleToString(double price) {
        return String.valueOf(price);
    }

    public boolean isValid(double price) {
        //simple helper function to check if a given price should be accepted
        if (price <= 0) {
            return false;
        }
        return true;
    }

    public void fetch(String[] caseNames, Config cfg) {
        int caseNumber = 0;
        //String dateString = DateHandler.format(Config.DEFAULT_DATE);

        for (caseNumber = 0; caseNumber < caseNames.length; caseNumber++) {
            String caseName = caseNames[caseNumber];
            // iterate through for each case that our user holds at least one of
            double price = findPrice(caseName, cfg.DATE);
            if (ErrorHandler.validateError(price)) {
                setPrice(caseNames[caseNumber], price);
                //utils.print("Price found for " + caseNames[caseNumber].toString().toLowerCase() + " case: $" + price);
            } else {
                setPrice(caseNames[caseNumber], constants.PRICE_ERROR);
            }

        }
    }

    public double findPrice(String caseName, Date date) {
        String url = CaseURL.valueOf(caseName).getURL();
        utils.print("URL was found to be: " + url);
        String massData = WebGrabber.grabFromUrl(url);

        // loop until we find a match
        int info = getInfoLocation(caseName, massData, date);

        if (info == constants.NOT_FOUND) {
            return constants.PRICE_ERROR;
        }

        massData = massData.substring(info);
        int start = constants.NOT_FOUND;
        int end = constants.NOT_FOUND;
        int i = 0;
        // we have now isolated a string in this format --> ["Feb 03 2023 03: +0",46.269,"9"]
        // but now we need to only grab the double thats surround by the 2 ","'s
        double casePrice = -1.0;
        for (i = 0; i < constants.MAXIMUM_STEAM_DATA_RANGE; i++) {
            //utils.print("Char at [" + i + "]: " + massData.charAt(i));
            if (massData.charAt(i) == ',') {
                // this is the index of the first comma
                if (start == constants.NOT_FOUND) {
                    start = i;
                } else {
                    // this is the index of the second comma
                    end = i;
                    break;
                }
            }
        }
        //@TODO remove
        // Validate that we did infact find both start and end comma's
        if (start == -1 || end == -1) {
            utils.print("\n\n\n\nITS FUCKED LOL\n\n\n\n\n");
            casePrice = constants.PRICE_ERROR;
        } else {
            casePrice = Double.valueOf(massData.substring(start + 1, end));
            //casePrice = casePrice * Constants.CONVERSION_RATE;
            utils.print("Price for " + caseName + ": $" + utils.toTwoDeci(casePrice* Constants.CONVERSION_RATE));
        }
        return casePrice;
    }


    private int getInfoLocation(String caseName, String massData, Date date) {
        int posCount = 0;
        int negCount = 0;
        int posVar = Config.POS_VAR;
        int negVar = Config.NEG_VAR;
        int infoLocation = constants.NOT_FOUND;
        Date forwardDate = date;
        Date backwardDate = date;
        try {
            infoLocation = massData.lastIndexOf(DateHandler.format(date));
        } catch (Exception e) {

        }


        //getPriceOnDate(date);

        // try until we meet our variance or data point


        // @TODO make this check more sophisticated
        if (infoLocation == constants.NOT_FOUND) {
            while (posCount < posVar || negCount < negVar) {
                if (posCount < posVar) {
                    // if we still have variance to look forward, check next date
                    posCount++;
                    try {
                        forwardDate = DateHandler.getTomorrow(forwardDate);
                        infoLocation = massData.lastIndexOf(DateHandler.format(forwardDate));
                        if (infoLocation != constants.NOT_FOUND) {
                            utils.print("Price found on date: " + DateHandler.format(forwardDate));
                            // update our stored date for our case to this new date
                            return infoLocation;
                        }
                    } catch (Exception ex) {
                    }
                }
                // if we still have
                if (negCount < negVar) {
                    utils.print("negCount: " + negCount);
                    negCount++;
                    try {
                        backwardDate = DateHandler.getYesterday(backwardDate);
                        infoLocation = massData.lastIndexOf(DateHandler.format(backwardDate));
                        if (infoLocation != constants.NOT_FOUND) {
                            utils.print("Price found on date: " + DateHandler.format(backwardDate));
                            // update our stored date for our case to this new date
                            return infoLocation;
                        }
                    } catch (Exception ex2) {

                    }
                }
            }
        }
        utils.print("1Price found on date: " + DateHandler.format(date));
        return infoLocation;
//        if (infoLocation == CONSTANTS.NOT_FOUND) {
//            return CONSTANTS.PRICE_ERROR;
//

    }
}
