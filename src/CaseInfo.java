import java.util.Date;

public class CaseInfo {
    private double price;
    private Date dateForPrice;

    public void setPrice(double price){
        this.price = price;
    }
    public void setDate (Date date){
        this.dateForPrice = date;
    }

    public void preview(){
        System.out.println("Price was found on " + DateHandler.format(dateForPrice) + " : $" + price);
    }
}
