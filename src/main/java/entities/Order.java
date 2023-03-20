package entities;
/*
 * order entity class that contains all the order information
 */

import java.util.ArrayList;
//import statements for the date object (refer to SQL)
import java.util.Date;
import java.util.GregorianCalendar;

public class Order {
	// private attributes
	private int Order_ID;
	private Date o_Date;
	private double o_Total;
	private String ship_Address;
	private ArrayList<OrderItemLine> productsOrdered;

	// OLD -- Remove?
	private ArrayList<OrderEvent> orderEvents = new ArrayList<>();
	// OLD -- Remove
	private ArrayList<Product> orderList;

	// constructor
	public Order(int o, Date d, double od, String a) {
		orderList = new ArrayList<Product>();

		this.Order_ID = o;
		this.o_Date = d;
		this.o_Total = od;
		this.ship_Address = a;
	}

	// OLD -- Remove
	// adding a product to the end of the order list
	public void add(Product p) {
		orderList.add(p);

	}

	// OLD -- Remove
	// getting all products
	public ArrayList<Product> getProducts() {
		return this.orderList;
	}

	// OLD -- Remove
	// returns specific product from order
	public Product getProduct(int index) {
		return this.orderList.get(index);
	}

	// OLD -- Remove
	// removes all products
	public void clearOrderList() {
		orderList.clear();
	}

	// OLD -- Remove
	// removes specific product from order
	public void deleteProduct(Product p) {
		orderList.remove(p);
	}

//setter and getters---------------------------
	public void setOrder_ID(int a) {
		this.Order_ID = a;
	}

	public void setO_Date(int y, int m, int d) {
		o_Date = new GregorianCalendar(y, m, d).getTime();
	}

	public void setO_Total(double d) {
		this.o_Total = d;
	}

	public void setShip_Address(String s) {
		this.ship_Address = s;
	}

	public int getOrder_ID() {
		return this.Order_ID;
	}

	public Date getO_Date() {
		return this.o_Date;
	}

	public double getO_Total() {
		return this.o_Total;
	}

	public String getShip_Address() {
		return this.ship_Address;
	}

	public ArrayList<OrderEvent> getOrderEvents() {
		return orderEvents;
	}

	public void setOrderEvents(ArrayList<OrderEvent> orderEvents) {
		this.orderEvents = orderEvents;
	}

	public ArrayList<OrderItemLine> getProductsOrdered() {
		return productsOrdered;
	}

	public void setProductsOrdered(ArrayList<OrderItemLine> productsOrdered) {
		this.productsOrdered = productsOrdered;
		// to ensure the value is correct
		this.setO_Total(0);
		initOrderTotal();
	}

	private void initOrderTotal() {
		for (OrderItemLine i : this.productsOrdered)
			this.o_Total += i.getsubTotal();

	}

	// end of setter and getters--------------------

}// end of order entity
