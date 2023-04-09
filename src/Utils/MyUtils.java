public class MyUtils {
    private final boolean PRINT_NEWLINE = true;
    public void print(Object object) {
        System.out.print(object);
        if (PRINT_NEWLINE) {
            System.out.println();
        }
    }

    public void print(Object object, boolean overrideNewline) {
        System.out.print(object);
        if (overrideNewline) {
            System.out.println();
        }
    }

    public static String toTwoDeci(double number) {
        return String.format("%.2f", number);
    }
    public static Boolean isInfinity(String val) {
        String varianceText = val.toUpperCase();
        if (varianceText.compareTo("*") == 0) {
            return true;
        }
        if (varianceText.compareTo("INFINITY") == 0) {
            return true;
        }
        if (varianceText.compareTo("INFINITE") == 0) {
            return true;
        }
        return false;
    }
}
