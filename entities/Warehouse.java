package entities;

import java.util.List;

//Warehouse entity class
public class Warehouse {

    //private entity variables
    private String address;
    private String name;
    private List<Order> orders;
    private List<Product> inventory;

    //Entity Constructor
    public Warehouse(String address, String name) {
        this.address=address;
        this.name=name;
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

}
