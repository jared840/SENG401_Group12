package database;

import java.sql.*;
import java.lang.StringBuilder;
import entities.*;

//jdbc:mysql://localhost/SENG401Project


public class DBController {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private Connection connect = null; 
    private ResultSet result = null;

    public DBController (String url, String un, String pw) {
        DBURL = url;
        USERNAME = un;
        PASSWORD = pw;

        try {
            connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }
        catch (SQLException e) {
            closeAll();
            System.err.print("Failed to connect to database with url " + DBURL);
            System.err.print(", username " + USERNAME + ", and password " + PASSWORD + "\n");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void closeAll() {
        if (connect != null) {
            try {
                connect.close();
            }
            catch (SQLException e) {
                System.err.print("Failed to close connection to database.");
                System.exit(1);
            }
        }
        if (result != null) {
            try {
                result.close();
            }
            catch (SQLException e) {
                System.err.print("Failed to close ResultSet object.");
                System.exit(1);
            }
        }
    }





    //all 
   
    public void verifyLogin(String un, String pw) {

    }


    //customers

    public void newUser(User u) {

    }

    public void newOrder(Order o) {

    }

    public void cancelOrder(Order o) {

    }

    public void searchItems(String searchTerm) {

    }



    //suppliers 

    public void newUser(Supplier s) {

    }

    public void newItem(Product p) {

    }

    public void restockItem(Product p, int quantity) {

    }

    public void removeItem(Product p) {

    }




    //warehouse employees 

    public void newUser(WarehouseWorkers ww) {
        
    }

    public void viewOrders(Warehouse w) {

    }

    public void shipOrder(Order o) {
        //make ship item instead? 
    }
}