package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBController;

/**
 * Servlet implementation class shipServlet
 */
@WebServlet("/shipServlet")
public class shipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public shipServlet() {
		super();
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
		/*
		 * int order=Integer.parseInt(request.getParameter("param"));
		 * 
		 * try {
		 * 
		 * DBController d= new
		 * DBController("jdbc:mysql://localhost:3306/SENG401Project",
		 * "root","vick-newton7.1"); ArrayList<Product> items = new
		 * ArrayList<Product>(); items=d.getOrderItems(order);
		 * request.setAttribute("items",items);
		 * request.getRequestDispatcher("DisplayItems.jsp").forward(request,response);
		 * 
		 * 
		 */

		try {
			int order_id = Integer.parseInt(request.getParameter("order_ID"));
			DBController d = new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root", "password");
			d.shipOrder(order_id);
			request.setAttribute("shipSuccess", "true");
			request.getRequestDispatcher("Shipped.jsp").forward(request, response);
		} catch (Exception e) {
			out.println(e.getMessage());
			request.setAttribute("shipSuccess", "false");
			request.getRequestDispatcher("Shipped.jsp").forward(request, response);

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
