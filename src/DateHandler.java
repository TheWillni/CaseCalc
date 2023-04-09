import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DateHandler {
    private static MyUtils utils = new MyUtils();
    public static String getToday() {
        //@TODO clean this
        Date date = new Date();
        date.getTime();

        //utils.print(date);
        String tempDateString = date.toString();
        String dateString = tempDateString.substring(4,10) + tempDateString.substring(tempDateString.length() -5);
        return dateString;
    }
    public static String format(Date date) {
        String info = date.toString();
        //System.out.println(date.toString());;
//        String month = info.substring(4,8);
//        String day = info.substring(8,11);
//        String year ;
//        String year = info.substring(25,29);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        //cal.setTime(date);
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        //cal.setTime(date);
        String day = String.valueOf(cal.get(Calendar.DATE));

        // fix up lost zeroes.
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        int i;
        String zeroes = "";
        for (i=0; i< (4 - year.length()); i++) {
            zeroes += "0";
        }
        year = zeroes + year;


        String formatted = PropertiesLoader.getMonthName(month) + " " + day + " " + year;
        //utils.print("Formatted: " + formatted);
        return formatted;
    }
    public static Date getTomorrow(Date date) {
        Date newDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        newDate = cal.getTime();
        return newDate;
    }

    public static Date getYesterday(Date date) {
        Date newDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        newDate = cal.getTime();
        return newDate;
    }
}
