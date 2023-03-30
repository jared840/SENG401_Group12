package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBController;
import entities.Product;
import entities.Warehouse;

/**
 * Servlet implementation class warehouseServ
 */
@WebServlet("/warehouseServ")
public class warehouseServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public warehouseServ() {
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
		try {
			int prodid = Integer.parseInt(request.getParameter("prod_id"));
			int stock = Integer.parseInt(request.getParameter("stockno"));
			String warehouse = (String) request.getParameter("WareSelect");
			// String numeral=(Strwarehouse.charAt(0);
			int w_id = Integer.parseInt(Character.toString(warehouse.charAt(0)));
			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root", "vick-newton7.1"); // obtain
																													// parameters
																													// submitted
																													// via
																													// html
																													// form:

			Warehouse w = db.getWarehouse(w_id);
			Product p = db.getProductById(prodid);
			// TODO continue here, but the data transferred shouldve been objects!
			// TODO make db return warehouse by id, use product by id method to pass to
			// below method
			db.restockItem(w, p, stock);

			request.setAttribute("stockSuccess", "true");
			request.getRequestDispatcher("stockedOutcome.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("stockSuccess", "false");
			request.getRequestDispatcher("stockedOutcome.jsp").forward(request, response);

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
