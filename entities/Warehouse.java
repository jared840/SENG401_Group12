package entities;

import java.util.List;
import java.util.ArrayList;

//Warehouse entity class
public class Warehouse {

    //private entity variables
    private int warehouseID;
    private String address;
    private String name;
    private ArrayList<Order> orders;
    private ArrayList<Product> inventory;

    //Entity Constructor
    public Warehouse(int warehouseID, String address, String name) {
        this.warehouseID = warehouseID;
        this.address=address;
        this.name=name;
        this.orders=new ArrayList<Order>();
        this.inventory=new ArrayList<Product>();
    }

    //______________________________________________
    //Setters and Getters
    //______________________________________________

    //Address
    public void setAddress(String addr){
        this.address=addr;
    }
    public String getAddress() {
        return this.address;
    }

    //Name:
    public void setFirstName(String name){
        this.name=name;
    }
    public String getFirstName(){
        return this.name;
    }


    //Inventory:
    public void addInventory(Product item){
        inventory.add(item);
    }
    public void removeInventory(Product item){
        inventory.remove(item);
    }
    public List<Product> getInventory(){
        return this.inventory;
    }

    //Order:
    public void addOrder(Order order){
        orders.add(order);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }
    public List<Order> getOrders(){
        return this.orders;
    }

    //ID:
    public void setWarehouseID(int warehouseID){
        this.warehouseID = warehouseID;
    }
    public int getWarehouseID(){
        return warehouseID;
    }

}
