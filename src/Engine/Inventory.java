import java.util.Properties;
import java.util.Set;

public class Inventory {
    Properties prop = new Properties();

    public Inventory() {
        //Properties prop = new Properties();

        // @TODO remove these manual quantities. Implement loading and saving.
        setQuantity("PHOENIX", 46);
        setQuantity("FRACTURE", 3299);
        setQuantity("CHROMA_3", 340);
        setQuantity("BRAVO", 1);
        setQuantity("DREAMS_NIGHTMARES", -1);
        setQuantity("BREAKOUT", 3);
        setQuantity("BROKEN_FANG", 15);

    }
    public void setQuantity(String caseName, int quantity) {
        prop.setProperty(caseName, intToString(quantity));
    }

    public int getQuantity(String caseName) {
        // @TODO add a try/catch to filter cases that we have no information on
        String quantityString = prop.getProperty(caseName);
        return Integer.valueOf(quantityString);
    }
    private String intToString(int quantity) {
        return String.valueOf(quantity);
    }

    public Set<Object> getKeySet() {
        return prop.keySet();
    }
    public boolean isValid(int quantity){
        //simple helper function to check if a given quantity in the inventory should be accepted
        if (quantity <= 0) {
            return false;
        }
        return true;
    }
}
