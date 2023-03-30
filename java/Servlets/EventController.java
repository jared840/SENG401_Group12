package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.OrderEvents.OrderEventCreaterUtil;
import database.DBController;
import entities.OrderEvent;
import entities.User;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root",
					"vick-newton7.1");
			OrderEventCreaterUtil eventCreator = new OrderEventCreaterUtil();
			HttpSession mysession = request.getSession();
			User user = (User) mysession.getAttribute("currentUser");
			OrderEvent orderEvent = eventCreator.CreateOrderEventFromGUI(user, db, request.getParameterMap());
			db.InsertOrderEvent(orderEvent);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
