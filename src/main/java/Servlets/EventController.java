package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.OrderEvents.OrderEventCreaterUtil;
import Business.OrderEvents.OrderEventTypes;
import database.DBController;
import entities.Order;
import entities.OrderEvent;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			  DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root", "Admin");
			  OrderEventCreaterUtil eventCreator = new OrderEventCreaterUtil();
			  // TODO: OBS har hårdkodat värde för orderId
			  OrderEvent orderEvent = eventCreator.CreateOrderEventFromGUI(db, request.getParameterMap());
			  db.InsertOrderEvent(orderEvent);

			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
