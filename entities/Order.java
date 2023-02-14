package entities;
/*
 * order entity class that contains all the order information
 */

 //import statements for the date object (refer to SQL)
import java.util.Date;
import java.util.GregorianCalendar;

public class Order {
    //private attributes
    private int Order_ID;
    private Date o_Date;
    private double o_Total;
    private String ship_Address;

//setter and getters---------------------------
    public void setOrder_ID(int a)
    {
        this.Order_ID=a;
    }

    public void setO_Date(int y, int m, int d)
    {
        o_Date = new GregorianCalendar(y,m,d).getTime();
    }

    public void setO_Total(double d)
    {
        this.o_Total=d;
    }

    public void setShip_Address(String s)
    {
        this.ship_Address=s;
    }

    public int getOrder_ID()
    {
        return this.Order_ID;
    }

    public Date getO_Date()
    {
        return this.o_Date;
    }

    public double getO_Total()
    {
        return this.o_Total;
    }

    public String getShip_Address()
    {
        return this.ship_Address;
    }
//end of setter and getters--------------------


}//end of order entity 
