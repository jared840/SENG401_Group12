package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;
import entities.*;


/**
 * Servlet implementation class stockItemsServlet
 */
@WebServlet("/stockItemsServlet")
public class stockItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stockItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
try {
	DBController db=new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");		//obtain parameters submitted via html form:


//retrieve logged in supplier's information:
HttpSession mysession=request.getSession();
Supplier loggedin = (Supplier)request.getSession().getAttribute("currentSupplier");
loggedin = db.getSupplier(loggedin.getUsername(), loggedin.getPassword());
int s_id=loggedin.getSupplierID();

ArrayList<Product> itemsList = new ArrayList<Product>();
itemsList=db.getSupplierItems(s_id);
request.setAttribute("supplierProds", itemsList);
request.getRequestDispatcher("StockedProduct.jsp").forward(request, response);


}catch(Exception e) {
	out.println(e.getMessage());
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
