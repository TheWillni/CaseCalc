import java.util.Properties;

public class Prices {
    Properties prop = new Properties();
    public Constants c = new Constants();
    public double[] list = new double[c.DEFAULT_PRICE_SIZE];

    public Prices() {
        //Properties prop = new Properties();
        prop.setProperty("PHOENIX", "2.16");

    }
    public void setPrice(String caseName, double price) {
        prop.setProperty(caseName, doubleToString(price));
    }

    public double getPrice(String caseName) {
        String priceString = prop.getProperty(caseName);
        return Double.valueOf(priceString);
    }
    private String doubleToString( double price) {
        return String.valueOf(price);
    }
}
