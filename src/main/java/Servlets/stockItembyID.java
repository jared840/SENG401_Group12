package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import database.*;
import entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class stockItembyID
 */
@WebServlet("/stockItembyID")
public class stockItembyID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stockItembyID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		//get the product ID:
		int prod_id = Integer.parseInt(request.getParameter("param"));
	try {
		DBController db= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","vick-newton7.1");
		//return all of the warehouses back to the user to select:
		
		ArrayList<Warehouse> ware = new ArrayList<Warehouse>();
		ware = db.getWarehouses();
		
		request.setAttribute("warehouses", ware);
		request.setAttribute("prodid", prod_id);
		request.getRequestDispatcher("SelectWarehouses.jsp").forward(request,response);

	}catch(Exception e) {
		out.print(e.getMessage());
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
