package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Business.OrderEvents.OrderEventParserUtil;
import Business.OrderEvents.OrderEventProjectionUtil;
import entities.Order;
import entities.OrderEvent;
import entities.Product;
import entities.Supplier;
import entities.User;
import entities.Warehouse;
import entities.WarehouseWorkers;

//jdbc:mysql://localhost/SENG401Project

public class DBController {

	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;

	private Connection connect = null;
	private ResultSet result = null;
	private ResultSet result2 = null;
	private ResultSet result3 = null;

	// constructor
	public DBController(String url, String un, String pw) throws SQLException, ClassNotFoundException {
		DBURL = url;
		USERNAME = un;
		PASSWORD = pw;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		/*
		 * try { connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD); }
		 * catch (SQLException e) { closeAll();
		 * System.err.print("Failed to connect to database with url " + DBURL);
		 * System.err.print( ", username " + USERNAME + ", and password " + PASSWORD +
		 * "\n"); e.printStackTrace(); // System.exit(1); }
		 */
	}

	// close all connections
	public void closeAll() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.err.print("Failed to close connection to database.");
				// System.exit(1);
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				System.err.print("Failed to close ResultSet object.");
				// System.exit(1);
			}
		}
	}

	// all

	// check whether the submitted username & password correspond with an account,
	// returns the appropriate user object if login is valid
	// return (user type, user object) pair
	public String verifyLogin(String un, String pw) throws SQLException {
		String userType = "";
		Statement stmt = null;

		// try {
		stmt = connect.createStatement();

		result = stmt.executeQuery(
				"SELECT * FROM Login_Information WHERE username = '" + un + "' AND password = '" + pw + "'");
		result.next();
		userType = result.getString("user_type");
		/*
		 * } catch (SQLException e) { closeAll();
		 * System.err.println("SQLException in newOrder."); //System.exit(1); }
		 */

		return userType;
	}

	/*
	 * //retrieves items from order public ArrayList<Product> getOrderItems(int ID)
	 * throws SQLException { String
	 * query="SELECT * FROM ORDER_ITEMS,ITEM_INFORMATION WHERE O_ID="+ID+
	 * " AND I_ID=ITEM_ID"; Statement stmt = connect.createStatement(); result =
	 * stmt.executeQuery(query);
	 * 
	 * ArrayList <Product> arr = new ArrayList<Product>(); while(result.next()) {
	 * int o_ID=result.getInt(1); int i_ID=result.getInt(2); String
	 * i_name=result.getString(3); int quantity=result.getInt(4); int
	 * location=result.getInt(5); boolean shipped=result.getBoolean(6); String
	 * description=result.getString(9); int supplier_ID=result.getInt(12); double
	 * cost=result.getDouble(10); String category=result.getString(11); int stock=0;
	 * 
	 * Product p=new
	 * Product(i_ID,supplier_ID,i_name,description,cost,category,stock); arr.add(p);
	 * 
	 * 
	 * 
	 * } return arr;
	 * 
	 * }
	 */

	// --------------------
	// Customers
	// --------------------

	// adds a new customer to the database
	public void newUser(User u) throws SQLException{
		PreparedStatement stmt = null;
		
			String query = "INSERT INTO Customer_Information (C_Name, C_Address, C_Card_Number, C_Username) VALUES (?, ?, ?, ?)";
			stmt = connect.prepareStatement(query);
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getUserAddress());
			stmt.setString(3, String.valueOf(u.getcardNumber()));
			stmt.setString(4, u.getuserEmail());
			stmt.executeUpdate();
			stmt.close();

			PreparedStatement stmt2 = null;
			query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'c')";
			stmt2 = connect.prepareStatement(query);
			stmt2.setString(1, u.getuserEmail());
			stmt2.setString(2, u.getPassword());
			stmt2.executeUpdate();

			stmt2.close();
		
			
		
	}

	public User getUser(String username, String password) {
		Statement stmt = null;
		User u = null;

		try {
			String query = "SELECT * FROM Customer_Information WHERE C_Username = '" + username + "'";
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			result.next();
			u = new User(result.getInt("Customer_ID"), result.getString("C_Name"), result.getString("C_Address"),
					username, password, result.getInt("C_Card_Number"));
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in newOrder.");
			// System.exit(1);
		}

		return u;
	}

	// checks that the provided credit card number matches the one in the database
	public boolean verifyCardNumber(int userID, int card) {
		boolean match = false;
		Statement stmt = null;

		try {
			String query = "SELECT card_number FROM Customer_Information WHERE Customer_ID = " + String.valueOf(userID);
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);
			result.next();

			if (result.getInt("C_Card_Number") == card) {
				match = true;
			} else {
				match = false;
			}
		} catch (SQLException e) {
			System.err.println("SQLException in verifyCardNumber.");
		}

		return match;
	}

	// adds a new order to the database
	public void newOrder(User u, Order o) {
		PreparedStatement stmt = null;
		PreparedStatement getItemLoc = null;
		try {
			String query = "INSERT INTO Order_Information (C_ID, O_Date, O_Total, Ship_Address) VALUES (?, ?, ?, ?)";
			stmt = connect.prepareStatement(query);
			// stmt.setString(1, String.valueOf(u.getUser_ID()));
			stmt.setInt(1, u.getUser_ID());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String date = dateFormat.format(o.getO_Date());
			stmt.setString(2, date);
			// stmt.setString(3, String.valueOf(o.getO_Total()));
			stmt.setDouble(3, o.getO_Total());
			stmt.setString(4, o.getShip_Address());
			stmt.executeUpdate();

			stmt.close();
			// add each item in the order to Order_Items
			query = "INSERT INTO Order_Items (O_ID, I_ID, I_Name, I_Location) VALUES (?, ?, ?, ?)";
			stmt = connect.prepareStatement(query);
			ArrayList<Product> p = o.getProducts();

			getItemLoc = connect.prepareStatement("SELECT * FROM Item_Information WHERE Item_ID = ?");

			// need a section to also add each item in the order
			for (Product pr : p) {
				getItemLoc.setInt(1, pr.getProductId());
				result = getItemLoc.executeQuery();
				result.next();

				stmt.setInt(1, o.getOrder_ID());
				stmt.setInt(2, pr.getProductId());
				stmt.setString(3, pr.getName());
				stmt.setInt(4, result.getInt("I_Location"));
				stmt.executeUpdate();
			}

			stmt.close();
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in newOrder.");
			// System.exit(1);
		}
	}

	// retrieves the information for an existing order
	public Order getOrder(int orderID) {
		PreparedStatement stmt = null;
		Order o = null;

		try {
			String query = "SELECT * FROM Order_Information WHERE Order_ID = " + String.valueOf(orderID);
			stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			result.next();
			java.util.Date date = null;

			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("O_Date"));
			} catch (ParseException e) {
				System.err.println("ParseException in gtOrder");
				return o;
			}

			o = new Order(result.getInt("Order_ID"), date, result.getDouble("O_Total"),
					result.getString("Ship_Address"));

			query = "";

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in getOrder.");
		}

		return o;
	}

	//
	public Order getOrderInCartStage(int userID) {
		Order o = null;
		Statement stmt = null;

		try {
			String query = "SELECT * FROM order_information WHERE O_Status = 'cart' AND C_ID = "
					+ String.valueOf(userID);
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			result.next();

			java.util.Date date = null;

			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("O_Date"));
			} catch (ParseException e) {
				closeAll();
				System.err.println("ParseException in getOrderInCartStage.");
			}

			o = new Order(result.getInt("Order_ID"), date, result.getDouble("O_Total"),
					result.getString("Ship_Address"));

		} catch (SQLException e) {
			return null;
		}
		ArrayList<OrderEvent> parsedOrders = OrderEventParserUtil
				.ConvertOrderEventListFromClob(GetOrderEventByOrderId(o.getOrder_ID()));
		o.setOrderEvents(parsedOrders);
		o.setProductsOrdered(OrderEventProjectionUtil.ParseEventsToProducts(parsedOrders));

		return o;
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

			query = "DELETE FROM Order_Items WHERE I_ID = ? AND O_ID = ?";
			stmt = connect.prepareStatement(query);
			stmt.setInt(2, o.getOrder_ID());

			for (Product p : o.getProducts()) {
				stmt.setInt(1, p.getProductId());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in cancelOrder.");
			// System.exit(1);
		}
	}

	// returns all items in the database that match a given search term
	public ArrayList<Product> searchItems(String searchTerm) {
		Statement stmt = null;
		ArrayList<Product> searchResults = new ArrayList<Product>();
		try {
			String query = "SELECT * FROM Item_Information WHERE I_Name LIKE '%" + searchTerm
					+ "%' OR I_Description LIKE '%" + searchTerm + "%'";
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			// ArrayList<Product> searchResults = new ArrayList<Product>();
			while (result.next()) {
				Product p = new Product(result.getInt("Item_ID"), result.getInt("S_ID"), result.getString("I_Name"),
						result.getString("I_Description"), result.getDouble("I_Cost"), result.getString("I_Category"), 0 // stock
				);
				searchResults.add(p);
			}

			stmt.close();
			// return searchResults;
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			// System.exit(1);
		}
		return searchResults;
	}

	public ArrayList<Product> getAllProducts() {
		Statement stmt = null;
		ArrayList<Product> searchResults = new ArrayList<Product>();
		try {
			String query = "SELECT ite.Item_ID, ite.I_Name,ite.I_Description,ite.I_Cost,ite.I_Category,ite.S_ID,inv.Quantity FROM item_information as ite join warehouse_inventory as inv on ite.Item_ID = inv.Item_ID;";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				Product p = new Product(result.getInt("Item_ID"), result.getInt("S_ID"), result.getString("I_Name"),
						result.getString("I_Description"), result.getDouble("I_Cost"), result.getString("I_Category"),
						result.getInt("Quantity"));
				searchResults.add(p);
			}

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			// System.exit(1);
		}
		return searchResults;
	}

	// retrieves items from order
	public ArrayList<Product> getOrderItems(int ID) throws SQLException {

		String query = "SELECT * FROM ORDER_ITEMS,ITEM_INFORMATION WHERE O_ID=" + ID + " AND I_ID=ITEM_ID";
		Statement stmt = connect.createStatement();
		result = stmt.executeQuery(query);

		ArrayList<Product> arr = new ArrayList<Product>();
		while (result.next()) {
			int o_ID = result.getInt(1);
			int i_ID = result.getInt(2);
			String i_name = result.getString(3);
			// int quantity = result.getInt(4);
			int location = result.getInt(4);
			boolean shipped = result.getBoolean(5);
			String description = result.getString(8);
			int supplier_ID = result.getInt(11);
			double cost = result.getDouble(9);
			String category = result.getString(10);
			int stock = 0;

			Product p = new Product(i_ID, supplier_ID, i_name, description, cost, category, stock);
			arr.add(p);

		}
		return arr;

	}

	// --------------------
	// Suppliers
	// --------------------

	// adds a new supplier to the database
	public void newUser(Supplier s) throws SQLException{
		PreparedStatement stmt = null;
		
			String query = "INSERT INTO Supplier_Information (S_Name, S_Description, S_Username) VALUES (?, ?, ?)";
			stmt = connect.prepareStatement(query);
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getDescription());
			stmt.setString(3, s.getUsername());
			stmt.executeUpdate();

			query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 's')";
			stmt = connect.prepareStatement(query);
			stmt.setString(1, s.getUsername());
			stmt.setString(2, s.getPassword());
			stmt.executeUpdate();

			stmt.close();
		 
			
		
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
				s = new Supplier(result.getInt("Supplier_ID"), result.getString("S_Name"),
						result.getString("S_Description"), result.getString("S_Username"), "");
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

			result.next();

			s = new Supplier(result.getInt("Supplier_ID"), result.getString("S_Name"),
					result.getString("S_Description"), username, password);
		} catch (SQLException e) {
			closeAll();
			System.err.println(e.getMessage());
			// System.exit(1);
		}

		return s;
	}

	public Product getProductById(int id) {
		Product product = null;
		try {
			String query = "SELECT * FROM Item_Information WHERE Item_ID = " + id;
			// String query = "SELECT ite.Item_ID,
			// ite.I_Name,ite.I_Description,ite.I_Cost,ite.I_Category,ite.S_ID,inv.Quantity
			// FROM item_information as ite join warehouse_inventory as inv on ite.Item_ID =
			// inv.Item_ID where ite.Item_ID = "+ id;

			PreparedStatement stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				product = new Product(result.getInt("Item_ID"), result.getInt("S_ID"), result.getString("I_Name"),
						result.getString("I_Description"), result.getDouble("I_Cost"), result.getString("I_Category"), 0 // stock
				);
			}
		} catch (Exception e) {

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
			// System.exit(1);
		}
	}

	// changes the stock of an item
	public void restockItem(Warehouse w, Product p, int quantity) {
		PreparedStatement stmt = null;
		try {
			String query = "INSERT INTO Warehouse_Inventory (Item_ID, W_ID, I_Name, Quantity, S_ID) VALUES ("
					+ p.getProductId() + ", " + w.getWarehouseID() + ", '" + p.getName() + "', " + quantity + ", "
					+ p.getSupplierId() + ") ON DUPLICATE KEY UPDATE Quantity = " + String.valueOf(quantity);
			stmt = connect.prepareStatement(query);
			/*
			 * stmt.setString(1, String.valueOf(quantity)); stmt.setString(2,
			 * String.valueOf(p.getProductId())); stmt.setString(3,
			 * String.valueOf(w.getWarehouseID()));
			 */
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			closeAll();
			System.err.println(e.getMessage());
			// System.exit(1);
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
			// System.exit(1);
		}
	}

	// returns all items associated with a given supplier
	public ArrayList<Product> getSupplierItems(int s_id) {
		ArrayList<Product> products = new ArrayList<>();
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			String query = "SELECT * FROM Item_Information WHERE S_ID = " + s_id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				Product p = new Product(result.getInt("Item_ID"), result.getInt("S_ID"), result.getString("I_Name"),
						result.getString("I_Description"), result.getDouble("I_Cost"), result.getString("I_Category"), 0 // stock
				);
				products.add(p);
			}
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in getSupplierItems.");
		}

		return products;
	}

	// --------------------
	// Warehouse Employees
	// --------------------

	// add a new warehouse employee to the database
	public void newUser(WarehouseWorkers ww) throws SQLException {
		PreparedStatement stmt = null;
		
			String query = "INSERT INTO Warehouse_Employees (E_Name, E_Username) VALUES (?, ?)";
			stmt = connect.prepareStatement(query);
			stmt.setString(1, ww.getE_Name());
			stmt.setString(2, ww.getUsername());

			stmt.executeUpdate();
			// stmt.close();

			query = "INSERT INTO Login_Information (username, password, user_type) VALUES (?, ?, 'w')";
			stmt = connect.prepareStatement(query);
			stmt.setString(1, ww.getUsername());
			stmt.setString(2, ww.getPassword());
			stmt.executeUpdate();

			stmt.close();
		 
		
		
	}

	// returns a WarehouseWorkers object corresponding to a specified username and
	// password
	public WarehouseWorkers getWarehouseWorkers(String username, String password) throws SQLException {
		Statement stmt = null;
		WarehouseWorkers w = null;

		// try {
		String query = "SELECT * FROM Warehouse_Employees WHERE E_Username = '" + username + "'";
		stmt = connect.createStatement();
		result = stmt.executeQuery(query);
		result.next(); // added this
		w = new WarehouseWorkers(result.getInt("Employee_ID"), result.getString("E_Name"), username, password);
		// } catch (SQLException e) {
		/*
		 * closeAll(); System.err.println(e.getMessage()); System.exit(1);
		 */
		// }

		return w;
	}

	// view all orders for a warehouse
	public void viewOrders(Warehouse w) {
		// need order information where the item is in stock in the warehouse
		// --> bypass order_info table?
		Statement stmt = null;

		try {
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM Order_Items WHERE I_Location = " + w.getWarehouseID());
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in newOrder.");
			System.exit(1);
		}
	}

	// getting ALL orders--------------------------
	public ArrayList<Order> viewAllOrders() throws SQLException {
		Statement st = connect.createStatement();
		result = st.executeQuery("SELECT * FROM Order_Information");
		ArrayList<Order> OD = new ArrayList<Order>();

		while (result.next()) {
			OD.add(new Order(result.getInt(1), result.getDate(3), result.getDouble(4), result.getString(5)));
		}
		return OD;
	}

	public void shipOrder(int orderID) {
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			// get all items from the order
			String query = "SELECT * FROM Order_Items WHERE O_ID = " + orderID;
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				query = "UPDATE Order_Items SET Shipped = True WHERE O_ID = " + orderID + " AND I_ID = "
						+ result.getInt("I_ID");

				pstmt = connect.prepareStatement(query);
				pstmt.executeUpdate();
			}

			stmt.close();
			pstmt.close();
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in shipOrder.");
		}
	}

	public void shipItem(int orderID, int itemID) {
		PreparedStatement stmt = null;
		try {
			String query = "UPDATE Order_Items SET Shipped = True WHERE O_ID = " + orderID + " AND I_ID = " + itemID;
			stmt = connect.prepareStatement(query);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in shipItem.");
		}
	}

	public ArrayList<Warehouse> getWarehouses() throws SQLException {
		Statement st = connect.createStatement();
		result = st.executeQuery("SELECT * FROM warehouse_information");
		ArrayList<Warehouse> OD = new ArrayList<Warehouse>();

		while (result.next()) {
			OD.add(new Warehouse(result.getInt(1), result.getString(2), "x"));
		}
		return OD;
	}

	public Warehouse getWarehouse(int w_id) throws SQLException {
		Statement st = connect.createStatement();
		result = st.executeQuery("SELECT * FROM warehouse_information where Warehouse_id=" + w_id);

		result.next();
		Warehouse OD = new Warehouse(result.getInt(1), result.getString(2), "N/A");

		return OD;
	}

	public boolean getOrderStatus(int customer_ID, int order_ID) throws SQLException {
		/*Statement s = connect.createStatement();
		result = s.executeQuery("SELECT Shipped FROM ORDER_ITEMS,ORDER_INFORMATION WHERE O_ID=ORDER_ID AND C_ID="
				+ customer_ID + " AND ORDER_ID=" + order_ID + ";");
		result.next();
		boolean b = result.getBoolean(1);

		return b;*/
		Statement s = connect.createStatement();
		result = s.executeQuery("SELECT O_Status FROM ORDER_INFORMATION WHERE C_ID="
				+ customer_ID + " AND ORDER_ID=" + order_ID + ";");
	result.next();
	if(result.getString(1).equals("shipped")){
		return true;
	}
	else {
		return false;
	}
	}

	public void InsertOrderEvent(OrderEvent event) throws SQLException {
		PreparedStatement stmt = null;
		String query = "insert into order_event (orderId,timeStamp, eventInitiatedByUser,eventInitiatedOnPage, classType, jsonClob) values(?,?,?,?,?,?)";
		stmt = connect.prepareStatement(query);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		stmt.setString(1, Integer.toString(event.getOrderId()));
		stmt.setString(2, dateFormat.format(event.getTimeStamp()));
		stmt.setString(3, event.getEventInitiatedBy());
		stmt.setString(4, event.getEventInitiatedOnPage());
		stmt.setString(5, event.getClassType());
		stmt.setString(6, event.getjsonClob());
		stmt.executeUpdate();
		stmt.close();
	}

	public ArrayList<OrderEvent> GetOrderEventByOrderId(int orderId) {

		Statement stmt = null;
		ArrayList<OrderEvent> searchResults = new ArrayList<OrderEvent>();
		try {
			// TODO
			String query = "select * from Order_Event as o where orderid = " + orderId;
			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {

				OrderEvent event = new OrderEvent(result.getInt("eventId"), result.getInt("orderId"),
						result.getString("classType"), result.getDate("timeStamp"),
						result.getString("eventInitiatedByUser"), result.getString("eventInitiatedOnPage"),
						result.getString("jsonClob"));
				searchResults.add(event);
			}

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			System.exit(1);
		}
		return searchResults;
	}

	// retrieves the information for an existing order
	public Order getOrderById(int orderID) {
		PreparedStatement stmt = null;
		Order o = null;

		try {
			String query = "SELECT * FROM Order_Information WHERE Order_ID = " + String.valueOf(orderID);
			stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			result.next();
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("O_Date"));

			o = new Order(result.getInt("Order_ID"), date, result.getDouble("O_Total"),
					result.getString("Ship_Address"));

			query = "";
			ArrayList<OrderEvent> parsedOrders = OrderEventParserUtil
					.ConvertOrderEventListFromClob(GetOrderEventByOrderId(orderID));
			o.setOrderEvents(parsedOrders);
			o.setProductsOrdered(OrderEventProjectionUtil.ParseEventsToProducts(parsedOrders));

		} catch (Exception e) {
			closeAll();
			System.err.println("SQLException in getOrder.");
		}

		return o;
	}

	public ArrayList<Order> getAllOrdes() {
		PreparedStatement stmt = null;
		Order order = null;
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM Order_Information";// WHERE O_Status = XX OR ID == x
			stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			result.next();
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("O_Date"));
			while (result.next()) {
				order = new Order(result.getInt("Order_ID"), date, result.getDouble("O_Total"),
						result.getString("Ship_Address"));

				orders.add(order);
			}
			for (Order o : orders) {

				ArrayList<OrderEvent> parsedOrders = OrderEventParserUtil
						.ConvertOrderEventListFromClob(GetOrderEventByOrderId(o.getOrder_ID()));
				o.setOrderEvents(parsedOrders);
				o.setProductsOrdered(OrderEventProjectionUtil.ParseEventsToProducts(parsedOrders));
			}

		} catch (Exception e) {
			closeAll();
			System.err.println("SQLException in getOrder.");
		}

		return orders;
	}

	// adds a new order to the database
	public Order createDefaultOrderInCartStage(User u) {
		PreparedStatement stmt = null;
		try {
			String query = "INSERT INTO Order_Information (C_ID, O_Date, O_Total, Ship_Address,O_Status) VALUES (?, ?, ?, ?, ?)";
			stmt = connect.prepareStatement(query);

			stmt.setInt(1, u.getUser_ID());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());
			stmt.setString(2, date);
			// stmt.setString(3, String.valueOf(o.getO_Total()));
			stmt.setDouble(3, 0);
			stmt.setString(4, "");
			stmt.setString(5, "cart");
			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in newOrder.");
			// System.exit(1);
		}
		return getOrderInCartStage(u.getUser_ID());
	}

	public ArrayList<String> getWarehouseInventory(int wid) {
		String query = "SELECT * FROM WAREHOUSE_INFORMATION,WAREHOUSE_INVENTORY WHERE Warehouse_ID=W_ID AND W_ID="
				+ wid;
		PreparedStatement stmt = null;
		ArrayList<String> toReturn = new ArrayList<String>();
		try {
			stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();

			while (result.next()) {
				toReturn.add(result.getString(2));
				toReturn.add(String.valueOf(result.getInt(3)));
				toReturn.add(result.getString(5));
				toReturn.add(String.valueOf(result.getInt(6)));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return toReturn;
	}

	public Product getProductByIdWithStock(int id) {
		Product product = null;
		try {
			// String query = "SELECT * FROM Item_Information WHERE Item_ID = " + id;
			String query = "SELECT ite.Item_ID, ite.I_Name,ite.I_Description,ite.I_Cost,ite.I_Category,ite.S_ID,inv.Quantity FROM item_information as ite join warehouse_inventory as inv on ite.Item_ID = inv.Item_ID where ite.Item_ID = "
					+ id;

			PreparedStatement stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				product = new Product(result.getInt("Item_ID"), result.getInt("S_ID"), result.getString("I_Name"),
						result.getString("I_Description"), result.getDouble("I_Cost"), result.getString("I_Category"),
						result.getInt("Quantity") // stock
				);
			}
		} catch (Exception e) {

		}

		return product;
	}

	public boolean checkShipped(Order o) throws SQLException {
		int ID = o.getOrder_ID();

		try {
			String query = "SELECT O_Status FROM ORDER_INFORMATION  WHERE ORDER_ID=" + ID;
			PreparedStatement st = connect.prepareStatement(query);
			result = st.executeQuery();
			result.next();
			String b = result.getString(1);
			if (b.equalsIgnoreCase("shipped")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception E) {

		}
		return false;
	}

	public void shipOrder2(int orderID) throws SQLException {

		
		PreparedStatement stmt = null;
		
		String query = "SELECT COUNT(*) FROM ORDER_INFORMATION WHERE ORDER_ID="+orderID;
		stmt = connect.prepareStatement(query);
		result = stmt.executeQuery();
		result.next();
		String no_of = result.getString(1);
		if(no_of.equals("0")) {
			throw new SQLException();
		}

		 query = "UPDATE ORDER_INFORMATION SET O_STATUS= 'shipped' WHERE ORDER_ID="+orderID;
			stmt = connect.prepareStatement(query);
			stmt.executeUpdate();



			stmt.close();

		
	}

	public void updateOrderStatusByOrderId(int id, O_Status status) {
		PreparedStatement stmt = null;
		try {
			String query = "UPDATE ORDER_INFORMATION SET O_Status= '" + status.toString() + "' WHERE Order_ID=" + id;
			stmt = connect.prepareStatement(query);

			// stmt = connect.prepareStatement(query);
			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrderStatusProcessed(Order order, String address, O_Status status) {
		PreparedStatement stmt = null;
		try {
			String q = String.format(
					"UPDATE order_information SET O_total = ? , Ship_Address = ? , O_Status=? WHERE Order_ID = ?",
					order.getO_Total(), address, order.getOrder_ID());

			stmt = connect.prepareStatement(q);
			stmt.setString(3, status.toString());
			stmt.setDouble(1, order.getO_Total());
			stmt.setString(2, address);
			stmt.setInt(4, order.getOrder_ID());
			// stmt = connect.prepareStatement(query);
			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrderInventory(int quantity, int id) {
		PreparedStatement stmt = null;
		try {
			String query = "UPDATE warehouse_inventory SET Quantity= '" + quantity + "' WHERE Item_ID=" + id;
			stmt = connect.prepareStatement(query);
			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> prodWare(int sup_id) throws SQLException{
		PreparedStatement stmt = null;
		
		
ArrayList<String> ret = new ArrayList<String>();

			String query = "SELECT I_Name,Quantity,W_Address FROM Warehouse_information,Warehouse_inventory WHERE Warehouse_ID=W_ID AND S_ID="+sup_id;
			stmt = connect.prepareStatement(query);
			result = stmt.executeQuery();
			
			while(result.next()) {
				ret.add(result.getString("I_Name"));
				ret.add(String.valueOf(result.getInt("Quantity")));
				ret.add(result.getString("W_Address"));
			}
			
		
		
		
		
		return ret;
	}
}
