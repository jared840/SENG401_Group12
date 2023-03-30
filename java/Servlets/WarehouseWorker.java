package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBController;
import entities.WarehouseWorkers;

/**
 * Servlet implementation class WarehouseWorker
 */
@WebServlet("/WarehouseWorker")
public class WarehouseWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public WarehouseWorker() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("usernameww");
		String password = request.getParameter("passwordww");
		String name = request.getParameter("nameww");

		try {

			DBController d = new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root", "vick-newton7.1");
			WarehouseWorkers w = new WarehouseWorkers(1, name, username, password);
			d.newUser(w);

			HttpSession mysession = request.getSession();
			mysession.setAttribute("currentWarehouseWorker", w);

		
			mysession.setAttribute("user_log", "WarehouseWorker");
			request.getRequestDispatcher("WarehouseWorkerHome.jsp").forward(request, response);

		} catch (Exception E) {
			request.getRequestDispatcher("SelectionPage.jsp").forward(request, response);

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
