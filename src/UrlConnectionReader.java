import java.net.*;
import java.io.*;
// class taken from javapoint.com/java-get-data-from-url
public class UrlConnectionReader
{
    public static String grabFromUrl(String url) {
        //url = "https://steamcommunity.com/market/listings/730/Fracture%20Case";
        //url = "https://steamcommunity.com/market/listings/730/Dreams%20%26%20Nightmares";
        String output  = getUrlContents(url);
        // @TODO remove these magic numbers and add as a constant
        int start = output.indexOf("var line1=") + 10;
        int end = output.indexOf("if ( line1 != false )") - 48;
        //System.out.println(output);
        String priceHistory = output.substring(start,end);
        //System.out.println(priceHistory);
        return output;
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        // Use try and catch to avoid the exceptions
        try
        {
            URL url = new URL(theUrl); // creating a url object
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

            // wrapping the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}  