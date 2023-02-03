import java.util.Properties;

public class Prices {
    Properties prop = new Properties();
    Constants CONSTANTS = new Constants();

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
}
