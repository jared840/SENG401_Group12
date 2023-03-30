package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBController;
import entities.Product;
import entities.Supplier;

/**
 * Servlet implementation class viewSupplierProds
 */
@WebServlet("/viewSupplierProds")
public class viewSupplierProds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewSupplierProds() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
try {
	DBController db=new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","vick-newton7.1");		//obtain parameters submitted via html form:


//retrieve logged in supplier's information:
HttpSession mysession=request.getSession();
Supplier loggedin = (Supplier)request.getSession().getAttribute("currentSupplier");
loggedin = db.getSupplier(loggedin.getUsername(), loggedin.getPassword());

ArrayList<String> toFwd = new ArrayList<String>();
toFwd = db.prodWare(loggedin.getSupplierID());

request.setAttribute("supPods", toFwd);
request.getRequestDispatcher("supPods.jsp").forward(request, response);




}catch(Exception e) {
	
	request.getRequestDispatcher("SupplierHome.jsp").forward(request, response);
	
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
