import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

public class Prices {
    public static Properties prop = new Properties();
    public static Constants CONSTANTS = new Constants();

    public static MyUtils utils = new MyUtils();
    public static UrlConnectionReader WebGrabber = new UrlConnectionReader();

    public Prices() {
        //Properties prop = new Properties();

        // @TODO remove these manual prices. Implement loading and saving.
        setPrice("PHOENIX", 2.88);
        setPrice("FRACTURE", 0.27);
        setPrice("CHROMA 3", 1.20);
        setPrice("BRAVO", 65.66);
        setPrice("DREAMS AND NIGHTMARES", -1.06);
        setPrice("BREAKOUT", 6.32);
        setPrice("BROKEN FANG", 3.02);


        //fetch();
    }

    public static void setPrice(String caseName, double price) {
        prop.setProperty(caseName, doubleToString(price));
    }

        public double getPrice(String caseName) {
        try {
            String priceString = prop.getProperty(caseName);
            return Double.valueOf(priceString);
        }
        catch (Exception e) {
            // override case price to error code
            return CONSTANTS.PRICE_ERROR;
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

    public static void fetch(String[] caseNames) {
        int caseNumber = 0;
        //String dateString = DateHandler.format(Config.DEFAULT_DATE);

        //@ TODO implement a way for user to choose which date to calculate their inventory on

        //dateString = "Jan 15 2017";

        for (caseNumber = 0; caseNumber < caseNames.length; caseNumber++) {
            // iterate through for each case that our user holds at least one of
            double price = findPrice(caseNames[caseNumber], Config.DEFAULT_DATE);
            if (ErrorHandler.validateError(price)) {
                setPrice(caseNames[caseNumber], price);
                //utils.print("Price found for " + caseNames[caseNumber].toString().toLowerCase() + " case: $" + price);
            } else {
                setPrice(caseNames[caseNumber], CONSTANTS.PRICE_ERROR);
            }

        }


    }

    public static double findPrice(String caseName, Date date) {
        //Date date = Config.DEFAULT_DATE;
        String url = CaseURL.valueOf(caseName).getURL();
        utils.print("URL was found to be: " + url);
        String massData = WebGrabber.grabFromUrl(url);
        //int dateVariance = Config.DATE_VARANCE;
        //utils.print(date);


        int info = getInfoLocation(massData, date);

        if (info == CONSTANTS.NOT_FOUND) {
            return CONSTANTS.PRICE_ERROR;
        }

        massData = massData.substring(info);
        //utils.print(infoLocation);
        int start = CONSTANTS.NOT_FOUND;
        int end = CONSTANTS.NOT_FOUND;
        int i = 0;
        // we have now isolated a string in this format --> ["Feb 03 2023 03: +0",46.269,"9"]
        // but now we need to only grab the double thats surround by the 2 ","'s
        double casePrice = -1.0;
        for (i = 0; i < CONSTANTS.MAXIMUM_STEAM_DATA_RANGE; i++) {
            //utils.print("Char at [" + i + "]: " + massData.charAt(i));
            if (massData.charAt(i) == ',') {
                // this is the index of the first comma
                if (start == CONSTANTS.NOT_FOUND) {
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
            casePrice = CONSTANTS.PRICE_ERROR;
        } else {
            casePrice = Double.valueOf(massData.substring(start + 1, end));
            //casePrice = casePrice * Constants.CONVERSION_RATE;
            utils.print("Price for " + caseName + " was found to be: $" + utils.toTwoDeci(casePrice* Constants.CONVERSION_RATE));
        }
        return casePrice;
    }

    private static double getPriceOnDate(String date) {
        double price = 0.0;
        // grab our variance;


        return price;
    }

    private static int getInfoLocation(String massData, Date date) {
        int posVar = Config.POS_VAR;
        int negVar = Config.NEG_VAR;
        int posCount = 0;
        int negCount = 0;
        int infoLocation = CONSTANTS.NOT_FOUND;
        try {
            infoLocation = massData.lastIndexOf(DateHandler.format(date));
        } catch (Exception e) {

        }


        //getPriceOnDate(date);

        // try until we meet our variance or data point


        // @TODO make this check more sophisticated
        //utils.print("posVar: " + posVar);
        //utils.print("negVar: " + negVar);
        if (infoLocation == CONSTANTS.NOT_FOUND) {
            while (posCount < posVar || negCount < negVar) {
                //utils.print("pos: " + posCount);
                //utils.print("neg: " + negCount);
                //utils.print("Unable to get price data for day, going to use variance to start checking nearby");
                if (posCount < posVar) {
                    //utils.print("posCount: " + posCount);
                    posCount++;
                    try {
                        Date newDate = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, posCount);
                        newDate = cal.getTime();
                        infoLocation = massData.lastIndexOf(DateHandler.format(newDate));
                        if (infoLocation != CONSTANTS.NOT_FOUND) {
                            utils.print("Price found on date: " + DateHandler.format(newDate));
                            return infoLocation;
                        }
                    } catch (Exception ex) {

                    }
                }
                if (negCount < negVar) {
                    //utils.print("negCount: " + negCount);
                    negCount++;
                    try {

                        Date newDate = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, -1 * negCount);
                        newDate = cal.getTime();
                        infoLocation = massData.lastIndexOf(DateHandler.format(newDate));
                        if (infoLocation != CONSTANTS.NOT_FOUND) {
                            utils.print("Price found on date: " + DateHandler.format(newDate));
                            return infoLocation;
                        }
                    } catch (Exception ex2) {

                    }
                }
            }
        }
        utils.print("Price found on date: " + DateHandler.format(date));
        return infoLocation;
//        if (infoLocation == CONSTANTS.NOT_FOUND) {
//            return CONSTANTS.PRICE_ERROR;
//

    }
}
