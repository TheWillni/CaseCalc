import java.util.Date;

public class DateHandler {
    public static String getToday() {
        //@TODO clean this
        Date date = new Date();
        date.getTime();
        //utils.print(date);
        String tempDateString = date.toString();
        String dateString = tempDateString.substring(4,10) + tempDateString.substring(tempDateString.length() -5);
        return dateString;
    }
    public static String format(String input) {
        if (input.compareTo("today") == 0) {
            return getToday();
        }
        return input;
    }
}
