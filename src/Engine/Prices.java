import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

public class Prices {
    Properties prop = new Properties();
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


        fetch();
    }
    public void setPrice(String caseName, double price) {
        prop.setProperty(caseName, doubleToString(price));
    }

    public double getPrice(String caseName) {
        // @TODO add a try/catch to filter cases that we have no information on
        try {
            String priceString = prop.getProperty(caseName);
            return Double.valueOf(priceString);
        }
        catch (Exception e) {
            // override case price to error code
            return CONSTANTS.PRICE_ERROR;
        }

    }
    private String doubleToString( double price) {
        return String.valueOf(price);
    }
    public boolean isValid(double price){
        //simple helper function to check if a given price should be accepted
        if (price <= 0) {
            return false;
        }
        return true;
    }

    public static void fetch() {
        //@TODO implement website grabber
        String massData = WebGrabber.grabFromUrl("");
        Date date = new Date();
        //@ TODO implement a way for user to choose which date to calculate their inventory on
        date.getTime();
        utils.print(date);
        String tempDateString = date.toString();
        String dateString = tempDateString.substring(4,10) + tempDateString.substring(tempDateString.length() -5);
        utils.print(dateString);
        int infoLocation =massData.lastIndexOf(dateString);
        massData = massData.substring(infoLocation);
        utils.print(infoLocation);
        int start = -1;
        int end = -1;
        int i=0;
        // we have now isolated a string in this format --> ["Feb 03 2023 03: +0",46.269,"9"]
        // but now we need to only grab the double thats surround by the 2 ","'s
        for (i=0;i<CONSTANTS.MAXIMUM_STEAM_DATA_RANGE; i++) {
            utils.print("Char at [" + i + "]: " + massData.charAt(i));
            if (massData.charAt(i) == ',') {
                // this is the index of the first comma
                if (start == -1) {
                    start = i;
                }
                else {
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
        }
        else {
            double casePrice = Double.valueOf(massData.substring(start + 1, end));
            utils.print("CasePrice was found to be: $" + casePrice);

        }

    }
}
