import java.util.Set;

public class Driver {
    public static Inventory inventory = new Inventory();
    private static Config cfg = new Config();
    private static MyUtils utils = new MyUtils();
    private static PropertiesLoader propertiesLoader;

    public static void main(String[] args) {
        // load properties into cfg
        propertiesLoader = new PropertiesLoader(utils);
        propertiesLoader.setConfig("general.properties", cfg);

        GUI gui = new GUI(utils, inventory, cfg);
    }


}
