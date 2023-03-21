package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBController;
import entities.Warehouse;

/**
 * Servlet implementation class findAllWarehouses
 */
@WebServlet("/findAllWarehouses")
public class findAllWarehouses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findAllWarehouses() {
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
		
		
	try {
		DBController db= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
		//return all of the warehouses back to the user to select:
		
		ArrayList<Warehouse> ware = new ArrayList<Warehouse>();
		ware = db.getWarehouses();
		
		request.setAttribute("all_warehouses", ware);
		
		request.getRequestDispatcher("selectWarehouseInventory.jsp").forward(request,response);

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
