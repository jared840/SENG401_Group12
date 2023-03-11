package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import entities.*;
import database.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createProductServlet
 */
@WebServlet("/createProductServlet")
public class createProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//set content to html in case we want to spit out html (probably not)
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
		try {
			DBController db=new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");		//obtain parameters submitted via html form:
		String item_name=request.getParameter("itemName");
		String item_desc=request.getParameter("itemDesc");
		double item_cost=Double.parseDouble(request.getParameter("itemCost"));
		String item_category=request.getParameter("itemCategory");

		//retrieve logged in supplier's information:
		HttpSession mysession=request.getSession();
		Supplier loggedin = (Supplier)request.getSession().getAttribute("currentSupplier");
		loggedin = db.getSupplier(loggedin.getUsername(), loggedin.getPassword());
		
		//creating entity for item:
		//    public Product(int productId, int supplierID, String name, String description, double price, String category, int stock) {

		Product newproduct = new Product(0,loggedin.getSupplierID(),item_name,item_desc,item_cost,item_category,0);
		
		//use DBController to put into the database:
		db.newItem(newproduct, loggedin);
		
		db.closeAll();
		
		//forward user to jsp
		request.setAttribute("productSuccess", "true");
		mysession.setAttribute("currentProduct", newproduct);
		request.getRequestDispatcher("CreatedProduct.jsp").forward(request, response);
		
		
		}catch(Exception e) {
			request.setAttribute("productSuccess","false");
			request.getRequestDispatcher("CreatedProduct.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
