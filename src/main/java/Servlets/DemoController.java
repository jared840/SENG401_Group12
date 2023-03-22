package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBController;
import entities.Order;
import entities.OrderItemLine;
import entities.Product;

/**
 * Servlet implementation class DemoController
 */
@WebServlet("/DemoController")
public class DemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root",
					"password");
			Order o = db.getOrderById(1);
			for (OrderItemLine e : o.getProductsOrdered()) {
				System.out.println(e.getProduct().getName() + " q: " + e.getQuantity());
			}
			ArrayList<Product> x = db.XXXgetAllProducts();
			request.setAttribute("order", o);
			request.getRequestDispatcher("UserViews/tempDemoView.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
