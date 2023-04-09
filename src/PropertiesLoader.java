import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PropertiesLoader {
    private MyUtils utils;
    PropertiesLoader (MyUtils util) {
        utils = util;
    }
    public void setConfig(String propertiesFile, Config config) {
        final Properties properties = loadPropertiesFile(propertiesFile);
        // default date
        Date def = new Date();
        def.getTime();
        config.DATE = safeGetDate(properties, "chosenDate", def);
        config.POS_VAR = safeGetVariance(properties, "positiveVariance", 1);
        config.NEG_VAR = safeGetVariance(properties, "negativeVariance", 1);
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
        if (properties == null) {
            return def;
        }
        String val = properties.getProperty(key);
        try {
            if (val != null) {
                return Integer.parseInt(val);
            } else return def;
        } catch (NumberFormatException e) {
            System.err.println("Error deserializing: " + val);
            return def;
        }
    }

    private Integer safeGetVariance(Properties properties, String key, int def) {
        if (properties == null) {
            return def;
        }
        String val = properties.getProperty(key);
        try {
            if (val != null) {
                if (utils.isInfinity(val)) {
                    return Constants.INFINITY;
                }
                return Integer.parseInt(val);
            } else return def;
        } catch (NumberFormatException e) {
            System.err.println("Error deserializing: " + val);
            return def;
        }
    }

    private static Date safeGetDate(Properties properties, String key, Date def) {
        if(properties == null) {
            return def;
        }
        String val = properties.getProperty(key);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(val != null) {
                Date newDate = sdf.parse(val);
                return newDate;
            } else return def;
        } catch(ParseException e) {
            System.err.println("Error parsing date from properties: " + val);
            return def;
        }
    }
//    private static boolean isValidDateFormat(String val) {
//
//        //utils.print(day + "/" + month+ "/" + year);
//        try {
//            String day = val.substring(4,6);
//            String year = val.substring(7,11);
//            String month = getMonthNumber(val.substring(0,3));
//            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            formatter.setLenient(false);
//            Date date = formatter.parse(day + "/" + month+ "/" + year);
//            return true;
//        }
////        catch (ParseException e) {
////            return false;
////        }
////        catch (ExceptionInInitializerError e) {
////            return false;
////        }
//        catch (Exception e) {
//            return false;
//        }
//    }
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

    public static String getMonthName(String input) {
        //utils.print(input);
        switch (input) {
            case "01": return "Jan";
            case "02": return "Feb";
            case "03": return "Mar";
            case "04": return "Apr";
            case "05": return "May";
            case "06": return "Jun";
            case "07": return "Jul";
            case "08": return "Aug";
            case "09": return "Sep";
            case "10": return "Oct";
            case "11": return "Nov";
            case "12": return "Dec";
        }
        return "empty";
    }
}
