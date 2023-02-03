import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PropertiesLoader {
    private static MyUtils utils = new MyUtils();
    PropertiesLoader () {
        setProperties("general.properties");
    }
    public static void setProperties(String propertiesFile) {
        final Properties properties = loadPropertiesFile(propertiesFile);
        Config.DEFAULT_DATE = safeGetString(properties, "defaultDate", "today");
    }


    private static Properties loadPropertiesFile(String propertiesFile) {
        try (InputStream input = new FileInputStream(propertiesFile)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            return prop;
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + propertiesFile + "!");
            ex.printStackTrace();
        }
        return null;
    }

    private static Integer safeGetInt(Properties properties, String key, int def) {
        if(properties == null) {
            return def;
        }
        String val = properties.getProperty(key);
        try {
            if(val != null) {
                return Integer.parseInt(val);
            } else return def;
        } catch(NumberFormatException e) {
            System.err.println("Error deserializing: " + val);
            return def;
        }
    }

    private static String safeGetString(Properties properties, String key, String def) {
        if(properties == null) {
            return def;
        }
        String val = properties.getProperty(key);
        try {

            if(val != null && isValidDateFormat(val)) {
                return val;
            } else return def;
        } catch(NumberFormatException e) {
            System.err.println("Error deserializing: " + val);
            return def;
        }
    }
    private static boolean isValidDateFormat(String val) {

        //utils.print(day + "/" + month+ "/" + year);
        try {
            String day = val.substring(4,6);
            String year = val.substring(7,11);
            String month = getMonthNumber(val.substring(0,3));
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.setLenient(false);
            Date date = formatter.parse(day + "/" + month+ "/" + year);
            return true;
        }
//        catch (ParseException e) {
//            return false;
//        }
//        catch (ExceptionInInitializerError e) {
//            return false;
//        }
        catch (Exception e) {
            return false;
        }
    }
    private static String getMonthNumber(String input) {
        //utils.print(input);
        switch (input) {
            case "Jan": return "01";
            case "Feb": return "02";
            case "Mar": return "03";
            case "Apr": return "04";
            case "May": return "05";
            case "Jun": return "06";
            case "Jul": return "07";
            case "Aug": return "08";
            case "Sep": return "09";
            case "Oct": return "10";
            case "Nov": return "11";
            case "Dec": return "12";
        }
        return "empty";
    }
}
