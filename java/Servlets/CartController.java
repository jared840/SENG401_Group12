package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBController;
import entities.Order;
import entities.User;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
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
					"vick-newton7.1");

			HttpSession mysession = request.getSession();
			User user = (User) mysession.getAttribute("currentUser");

			Order order = db.getOrderInCartStage(user.getUser_ID());
			if (order == null)
				order = db.createDefaultOrderInCartStage(user); // create new order
			request.setAttribute("order", order);

			request.getRequestDispatcher("UserViews/Cart.jsp").forward(request, response);

		} catch (Exception e) {
			//e.printStackTrace();
			response.sendRedirect("SelectionPage.jsp");
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
