package entities;
/*
 * order entity class that contains all the order information
 */

 import java.util.ArrayList;
//import statements for the date object (refer to SQL)
import java.util.Date;
import java.util.GregorianCalendar;

public class Order {
    //private attributes
    private int Order_ID;
    private Date o_Date;
    private double o_Total;
    private String ship_Address;
    private ArrayList<Product> orderList;

    //constructor
    public Order(int o, Date d, double od, String a)
    {
        orderList=new ArrayList<Product>();

        this.Order_ID=o;
        this.o_Date=d;
        this.o_Total=od;
        this.ship_Address=a;


    }

    //adding a product to the end of the order list
    public void add(Product p)
    {
        orderList.add(p);

    }

    //getting all products
    public ArrayList<Product> getProducts()
    {
        return this.orderList;
    }
    //returns specific product from order
    public Product getProduct(int index)
    {
        return this.orderList.get(index);
    }

    //removes all products
    public void clearOrderList()
    {
        orderList.clear();
    }
    //removes specific product from order
    public void deleteProduct(Product p)
    {
        orderList.remove(p);
    }

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
