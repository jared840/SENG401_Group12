package database;

import entities.*;
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

    // constructor
    public DBController(String url, String un, String pw) throws SQLException, ClassNotFoundException {
        DBURL = url;
        USERNAME = un;
        PASSWORD = pw;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
       /* try {
            connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            closeAll();
            System.err.print("Failed to connect to database with url " + DBURL);
            System.err.print(
                    ", username " + USERNAME + ", and password " + PASSWORD + "\n");
            e.printStackTrace();
            System.exit(1);
        }*/
    }

    // close all connections
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

    // all

    // check whether the submitted username & password correspond with an account,
    // returns the appropriate user object if login is valid
    // return (user type, user object) pair
    public String verifyLogin(String un, String pw) {
        String userType = "";
        Statement stmt = null;

        try {
            stmt = connect.createStatement();
            result = stmt.executeQuery(
                    "SELECT * FROM Login_Information WHERE username = '" +
                            un +
                            "' AND password = '" +
                            pw +
                            "'");

            userType = result.getString("user_type");
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }

        return userType;
    }

    // --------------------
    // Customers
    // --------------------

    // adds a new customer to the database
    public void newUser(User u) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Customer_Information (C_Name, C_Address, C_Card_Number, C_Username) VALUES (?, ?, ?, ?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUserAddress());
            stmt.setString(3, String.valueOf(u.getcardNumber()));
            stmt.setString(4, u.getuserEmail());
            stmt.executeUpdate();
            stmt.close();

            stmt = null;
            query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'c')";
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

    public User getUser(String username, String password) {
        Statement stmt = null;
        User u = null;

        try {
            String query = "SELECT * FROM Customer_Information WHERE C_Username = '" + username + "'";
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

            u = new User(result.getInt("Customer_ID"),
                    result.getString("C_Name"),
                    result.getString("C_Address"),
                    username,
                    password,
                    result.getInt("C_Card_Number"));
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }

        return u;
    }

    // adds a new order to the database
    public void newOrder(User u, Order o) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Order_Information (C_ID, O_Date, O_Total, Ship_Address) VALUES (?, ?, ?, ?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, String.valueOf(u.getUser_ID()));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String date = dateFormat.format(o.getO_Date());
            stmt.setString(2, date);
            stmt.setString(3, String.valueOf(o.getO_Total()));
            stmt.setString(4, o.getShip_Address());
            stmt.executeUpdate();

            // need a section to also add each item in the order

            stmt.close();
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }
    }
    
    
    //retrieves items from order
    public ArrayList<Product> getOrderItems(int ID) throws SQLException
    {
        
        String query="SELECT * FROM ORDER_ITEMS,ITEM_INFORMATION WHERE O_ID=ID AND I_ID=ITEM_ID";
          Statement stmt = connect.createStatement();
            result = stmt.executeQuery(query);
        
        ArrayList <Product> arr = new ArrayList<Product>();
        while(result.next())
        {
         int o_ID=result.getInt(1);
            int i_ID=result.getInt(2);
            String i_name=result.getString(3);
            int quantity=result.getInt(4);
            int location=result.getInt(5);
            boolean shipped=result.getBoolean(6);
            String description=result.getString(9);
            int supplier_ID=result.getInt(12);
            double cost=result.getDouble(10);
            String category=result.getString(11);
            int stock=0;
            
            Product p=new Product(i_ID,supplier_ID,i_name,description,cost,category,stock);
            arr.add(p);
            
            
        
        }
        return arr;
        
    }

    // cancels an existing order
    public void cancelOrder(Order o) {
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM Order_Information WHERE Order_ID = ?";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, String.valueOf(o.getOrder_ID()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in cancelOrder.");
            System.exit(1);
        }
    }

    // returns all items in the database that match a given search term
    public void searchItems(String searchTerm) {
        Statement stmt = null;
        try {
            String query = "SELECT * FROM Item_Information WHERE I_Name LIKE '" +
                    searchTerm +
                    "' OR I_Description LIKE '" +
                    searchTerm;
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

            ArrayList<Product> searchResults = new ArrayList<Product>();
            while (result.next()) {
                Product p = new Product(
                        result.getInt("Item_ID"),
                        result.getInt("S_ID"),
                        result.getString("I_Name"),
                        result.getString("I_Description"),
                        result.getDouble("I_Cost"),
                        result.getString("I_Category"),
                        0 // stock
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
    
    public ArrayList<Product> getAllProducts() {
        Statement stmt = null;
        ArrayList<Product> searchResults = new ArrayList<Product>();
        try {
        	// TODO
            String query = "SELECT * FROM Item_Information"; // WHERE STOCK > 0
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

           
            while (result.next()) {
                Product p = new Product(
                        result.getInt("Item_ID"),
                        result.getInt("S_ID"),
                        result.getString("I_Name"),
                        result.getString("I_Description"),
                        result.getDouble("I_Cost"),
                        result.getString("I_Category"),
                        0 // stock
                );
                searchResults.add(p);
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in searchItems.");
            System.exit(1);
        }
        return searchResults;
    }

    // --------------------
    // Suppliers
    // --------------------

    // adds a new supplier to the database
    public void newUser(Supplier s) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Supplier_Information (S_Name, S_Description) VALUES (?, ?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getDescription());
            stmt.executeUpdate();

            query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 's')";
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
    
    // returns the supplier object associated with a id
    public Supplier getSupplierBySupplierId(int id) {
        Statement stmt = null;
        Supplier s = null;

        try {
            String query = "SELECT * FROM Supplier_Information WHERE Supplier_ID  = " + id;
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
            s = new Supplier(result.getInt("Supplier_ID"),
                    result.getString("S_Name"),
                    result.getString("S_Description"),
                    result.getString("S_Username"),"");
            }
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }

        return s;
    }

    // returns the supplier object associated with a username and password
    public Supplier getSupplier(String username, String password) {
        Statement stmt = null;
        Supplier s = null;

        try {
            String query = "SELECT * FROM Supplier_Information WHERE S_Username = '" + username + "'";
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

            s = new Supplier(result.getInt("Supplier_ID"),
                    result.getString("S_Name"),
                    result.getString("S_Description"),
                    username,
                    password);
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }

        return s;
    }

    public Product getProductById(int id) {        
    	Product product = null;
    	try {
        	String query = "SELECT * FROM Item_Information WHERE Item_ID = " + id;
        	PreparedStatement stmt = connect.prepareStatement(query);
        	result = stmt.executeQuery();
            while (result.next()) {
                product = new Product(
                        result.getInt("Item_ID"),
                        result.getInt("S_ID"),
                        result.getString("I_Name"),
                        result.getString("I_Description"),
                        result.getDouble("I_Cost"),
                        result.getString("I_Category"),
                        0 // stock
                );
            }
    	}catch(Exception e) {
    		
    	}

    	return product;
    }
    // adds a new item to the database
    public void newItem(Product p, Supplier s) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Item_Information (I_Name, I_Description, I_Cost, I_Category, S_ID) VALUES (?, ?, ?, ?, ?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setString(3, String.valueOf(p.getPrice()));
            stmt.setString(4, p.getCategory());
            stmt.setString(5, String.valueOf(s.getSupplierID()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newItem.");
            System.exit(1);
        }
    }

    // changes the stock of an item
    public void restockItem(Warehouse w, Product p, int quantity) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Warehouse_Inventory (Item_ID, W_ID, I_Name, Quantity, S_ID) VALUES (" +
                    String.valueOf(p.getProductId()) +
                    ", " +
                    String.valueOf(w.getWarehouseID()) +
                    ", '" +
                    p.getName() +
                    "', " +
                    String.valueOf(quantity) +
                    ", " +
                    String.valueOf(p.getSupplierId()) +
                    ") ON DUPLICATE KEY UPDATE Quantity = " +
                    String.valueOf(quantity);
            stmt = connect.prepareStatement(query);
            stmt.setString(1, String.valueOf(quantity));
            stmt.setString(2, String.valueOf(p.getProductId()));
            stmt.setString(3, String.valueOf(w.getWarehouseID()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in searchItems.");
            System.exit(1);
        }
    }

    // removes an item from the database
    public void removeItem(Product p) {
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM Item_Information WHERE Item_ID = ?";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, String.valueOf(p.getProductId()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in removeItem.");
            System.exit(1);
        }
    }

    // --------------------
    // Warehouse Employees
    // --------------------

    // add a new warehouse employee to the database
    public void newUser(WarehouseWorkers ww) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO Warehouse_Employees (E_Name) VALUES (?)";
            stmt = connect.prepareStatement(query);
            stmt.setString(1, ww.getE_Name());
            stmt.executeUpdate();
            stmt.close();

            query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'w')";
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

    // returns a WarehouseWorkers object corresponding to a specified username and
    // password
    public WarehouseWorkers getWarehouseWorkers(String username, String password) throws SQLException{
        Statement stmt = null;
        WarehouseWorkers w = null;

      //  try {
            String query = "SELECT * FROM Warehouse_Employees WHERE E_Username = '" + username + "'";
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            result.next();
            w = new WarehouseWorkers(result.getInt("Employee_ID"),
                    result.getString("E_Name"),
                    username,
                    password);
     //   } catch (SQLException e) {
           /* closeAll();
            System.err.println(e.getMessage());
            System.exit(1);*/
      //  }

        return w;
    }

    // view all orders for a warehouse
    public void viewOrders(Warehouse w) {
        // need order information where the item is in stock in the warehouse
        // --> bypass order_info table?
        Statement stmt = null;

        try {
            stmt = connect.createStatement();
            result = stmt.executeQuery(
                    "SELECT * FROM Order_Items WHERE I_Location = " + w.getWarehouseID());
        } catch (SQLException e) {
            closeAll();
            System.err.println("SQLException in newOrder.");
            System.exit(1);
        }   
    }
    
    //getting ALL orders--------------------------
    public ArrayList<Order> viewAllOrders() throws SQLException 
    {
    Statement st = connect.createStatement();
        result = st.executeQuery(
            "SELECT * FROM Order_Information");
        public ArrayList <Order> OD= new ArrayList<Order>();
        
        while(result.next())
        {
         OD.add(new Order(result.getInt(1),result.getDate(3),result.getDouble(4),result.getString(5)));   
        }
        return OD;
    }
    //--------------------------------------------
    

    public void shipOrder(Order o) {
        // make ship item instead?
        // --> need to mark in db somehow
    }
}
