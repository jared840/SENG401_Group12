package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBController;
import database.O_Status;
import entities.Order;
import entities.OrderItemLine;
import entities.User;

/**
 * Servlet implementation class ProcessOrderController
 */
@WebServlet("/ProcessOrderController")
public class ProcessOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessOrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// response.sendRedirect("UserViews/OrderComp.jsp");

			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root",
					"password");
			HttpSession mysession = request.getSession();
			User user = (User) mysession.getAttribute("currentUser");
			Order order = db.getOrderInCartStage(user.getUser_ID());
			if (order == null)
				order = db.createDefaultOrderInCartStage(user); // create new order
			request.setAttribute("order", order);

			request.getRequestDispatcher("UserViews/ProcessOrderView.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("SelectionPage.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int orderId = Integer.parseInt(request.getParameterMap().get("orderId")[0]);
			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root",
					"password");
			Order order = db.getOrderById(orderId);
			HttpSession mysession = request.getSession();
			User user = (User) mysession.getAttribute("currentUser");

			db.updateOrderStatusProcessed(order, user.getUserAddress(), O_Status.TRANSACTION_PROCESSED);

			for (OrderItemLine o : order.getProductsOrdered()) {
				db.updateOrderInventory(
						db.getProductByIdWithStock(o.getProduct().getProductId()).getStock() - o.getQuantity(),
						o.getProduct().getProductId());
			}

		} catch (Exception e) {

			response.sendRedirect("SelectionPage.jsp");
			e.printStackTrace();
		}
		// doGet(request, response);
	}

}
