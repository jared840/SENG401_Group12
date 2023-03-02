package database;

import entities.*;
import java.lang.StringBuilder;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//jdbc:mysql://localhost/SENG401Project

public class DBController {

  public final String DBURL;
  public final String USERNAME;
  public final String PASSWORD;

  private Connection connect = null;
  private ResultSet result = null;

  //constructor
  public DBController(String url, String un, String pw) {
    DBURL = url;
    USERNAME = un;
    PASSWORD = pw;

    try {
      connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      closeAll();
      System.err.print("Failed to connect to database with url " + DBURL);
      System.err.print(
        ", username " + USERNAME + ", and password " + PASSWORD + "\n"
      );
      e.printStackTrace();
      System.exit(1);
    }
  }

  //close all connections
  public void closeAll() {
    if (connect != null) {
      try {
        connect.close();
      } catch (SQLException e) {
        System.err.print("Failed to close connection to database.");
        System.exit(1);
      }
    }
    if (result != null) {
      try {
        result.close();
      } catch (SQLException e) {
        System.err.print("Failed to close ResultSet object.");
        System.exit(1);
      }
    }
  }

  //all

  //check whether the submitted username & password correspond with an account,
  //returns the appropriate user object if login is valid
  // return (user type, user object) pair
  public void verifyLogin(String un, String pw) {
    Statement stmt = null;

    try {
      stmt = connect.createStatement();
      result =
        stmt.executeQuery(
          "SELECT * FROM Login_Information WHERE username = '" + un + "' AND password = '" + pw "'";
        );
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  //customers

  //adds a new customer to the database
  public void newUser(User u) {
    PreparedStatement stmt = null;
    try {
      String query =
        "INSERT INTO Customer_Information (C_Name, C_Address) VALUES (?, ?)";
      stmt = connect.prepareStatement(query);
      stmt.setString(1, u.getName());
      stmt.setString(2, u.getUserAddress());
      stmt.executeUpdate();

      query =
        "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'c')";
      stmt = connect.prepareStatement(query);
      stmt.setString(3, u.getuserEmail());
      stmt.setString(4, u.getPassword());
      stmt.executeUpdate();

      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  //adds a new order to the database
  public void newOrder(User u, Order o) {
    PreparedStatement stmt = null;
    try {
      String query =
        "INSERT INTO Order_Information (C_ID, O_Date, O_Total, Ship_Address) VALUES (?, ?, ?, ?)";
      stmt.setString(1, u.getUser_ID());
      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      String date = dateFormat.format(o.getO_Date());
      stmt.setString(2, date);
      stmt.setString(3, String.valueOf(o.getO_Total()));
      stmt.setString(4, o.getShip_Address());
      stmt = connect.prepareStatement(query);
      stmt.executeUpdate();

      //need a section to also add each item in the order

      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  public void cancelOrder(Order o) {
    PreparedStatement stmt = null;
    try {
      String query = "DELETE FROM Order_Information WHERE Order_ID = ?";
      stmt.setString(1, String.valueOf(o.getOrder_ID()));
      stmt = connect.prepareStatement(query);
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in cancelOrder.");
      System.exit(1);
    }
  }

  public void searchItems(String searchTerm) {
    Statement stmt = null;
    try {
      String query =
        "SELECT * FROM Item_Information WHERE I_Name LIKE '" +
        searchTerm +
        "' OR I_Description LIKE '" +
        searchTerm;
      stmt = connect.createStatement();
      result = stmt.executeQuery(query);

      ArrayList<Product> searchResults = new ArrayList<Product>();
      while (result.next()) {
        Product p = new Product(
          result.getInt("Item_ID"),
          result.getString("I_Name"),
          result.getString("I_Description"),
          result.getDouble("I_Cost"),
          result.getString("I_Category"),
          result.getInt("S_ID"),
          0 //stock
        );
        searchResults.add(p);
      }

      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in searchItems.");
      System.exit(1);
    }
  }

  //suppliers

  //adds a new supplier to the database
  public void newUser(Supplier s) {
    PreparedStatement stmt = null;
    try {
      String query =
        "INSERT INTO Supplier_Information (S_Name, S_Description) VALUES (?, ?)";
      stmt = connect.prepareStatement(query);
      stmt.setString(1, s.getName());
      stmt.setString(2, s.getDescription());
      stmt.executeUpdate();

      query =
        "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 's')";
      stmt = connect.prepareStatement(query);
      stmt.setString(3, s.getUsername());
      stmt.setString(4, s.getPassword());
      stmt.executeUpdate();

      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  //adds a new item to the database
  public void newItem(Product p, Supplier s) {
    PreparedStatement stmt = null;
    try {
      String query =
        "INSERT INTO Item_Information (I_Name, I_Description, I_Cost, I_Category, S_ID) VALUES (?, ?, ?, ?, ?)";
      stmt.setString(1, p.getName());
      stmt.setString(2, p.getDescription());
      stmt.setString(3, p.getPrice());
      stmt.setString(4, p.getCategory());
      stmt.setString(5, s.getSupplierID());
      stmt = connect.prepareStatement(query);
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newItem.");
      System.exit(1);
    }
  }

  public void restockItem(Warehouse w, Product p, int quantity) {
    PreparedStatement stmt = null;
    try {
      String query =
<<<<<<< HEAD
        "UPDATE Warehouse_Inventory SET Quantity = ? WHERE Item_ID = ? AND W_ID = ?";
      stmt.setString(1, quantity);
      stmt.setString(2, p.getId);
      stmt.setString(3, w.getID);
=======
        "INSERT INTO Warehouse_Inventory (Item_ID, W_ID, I_Name, Quantity, S_ID) VALUES (" +
        String.valueOF(p.getID()) +
        ", " +
        String.valueOf(w.getID()) +
        ", '" +
        p.getName() +
        "', " +
        String.valueOf(quantity) +
        ", " +
        String.valueOf(p.getSupplierID()) +
        ") ON DUPLICATE KEY UPDATE Quantity = " +
        String.valueOf(quantity);
      stmt.setString(1, String.valueOf(quantity));
      stmt.setString(2, String.valueOf(p.getID()));
      stmt.setString(3, String.valueOf(w.getID()));
>>>>>>> 6a6e6f59af2f0ed20cc7e1f45b681ced98787449
      stmt = connect.prepareStatement(query);
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in searchItems.");
      System.exit(1);
    }
  }

  public void removeItem(Product p) {
    PreparedStatement stmt = null;
    try {
      String query = "DELETE FROM Item_Information WHERE Item_ID = ?";
      stmt.setString(1, p.getProductId);
      stmt = connect.prepareStatement(query);
      stmt.executeUpdate();
      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in removeItem.");
      System.exit(1);
    }
  }

  //warehouse employees

  //add a new warehouse employee to the database
  public void newUser(WarehouseWorkers ww) {
    PreparedStatement stmt = null;
    try {
      String query = "INSERT INTO Warehouse_Employees (E_Name) VALUES (?)";
      stmt = connect.prepareStatement(query);
      stmt.setString(1, ww.getName());
      stmt.executeUpdate();

      query =
        "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'w')";
      stmt = connect.prepareStatement(query);
      stmt.setString(3, ww.getUsername());
      stmt.setString(4, ww.getPassword());
      stmt.executeUpdate();

      stmt.close();
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  //view all orders for a warehouse
  public void viewOrders(Warehouse w) {
    //need order information where the item is in stock in the warehouse
    //--> bypass order_info table?
    Statement stmt = null;

    try {
      stmt = connect.createStatement();
      result =
        stmt.executeQuery(
          "SELECT * FROM Order_Items WHERE I_Location = " + w.getID()
        );
    } catch (SQLException e) {
      closeAll();
      System.err.println("SQLException in newOrder.");
      System.exit(1);
    }
  }

  public void shipOrder(Order o) {
    //make ship item instead?
    //--> need to mark in db somehow
  }
}
