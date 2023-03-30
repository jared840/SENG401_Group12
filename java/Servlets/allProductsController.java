package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import database.DBController;
import entities.Order;
import entities.Product;
import entities.WarehouseWorkers;
import entities.User;


/**
 * Servlet implementation class productsController
 */
@WebServlet("/allProductsController")
public class allProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public allProductsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter(); 
		try {

			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root", "vick-newton7.1");
			
			/*// FOR TEST
						User u = db.getUser("E", "E");
						HttpSession mysession = request.getSession();
						mysession.setAttribute("currentUser", u);*/
			HttpSession mysession = request.getSession();
			mysession.setAttribute("currentUser", (User) mysession.getAttribute("currentUser"));
						response.setHeader("Access-Control-Allow-Origin", "*");
						response.setHeader("Access-Control-Allow-Headers",
								"Origin, X-Requested-With, Content-Type, Accept, Authorization");
						response.setHeader("Access-Control-Allow-Credentials", "true");
						response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
						// END FOR TEST
						
						
			
			
			ArrayList<Product> products = db.getAllProducts();


			request.setAttribute("orders", products);
			request.getRequestDispatcher("UserViews/AllProductsView.jsp").forward(request, response);

		}catch (Exception e){
			e.printStackTrace();
			//out.println(e.getMessage());
			response.sendRedirect("SelectionPage.jsp");
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