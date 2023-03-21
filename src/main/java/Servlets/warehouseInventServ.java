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

/**
 * Servlet implementation class warehouseInventServ
 */
@WebServlet("/warehouseInventServ")
public class warehouseInventServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public warehouseInventServ() {
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
		
		String warehouse=(String)request.getParameter("WareSelected");
		String x="";
		int i=0;
		while(warehouse.charAt(i)!=':') {
			x+=warehouse.charAt(i);
			i++;
		}
		int wid=Integer.parseInt(x);
		try {
			
			DBController db= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");

		ArrayList<String> returned = new ArrayList<String>();
		returned = db.getWarehouseInventory(wid);
		
request.setAttribute("returnedInventory", returned);
		
		request.getRequestDispatcher("InventoryReturn.jsp").forward(request,response);

	
	}catch(Exception e) {
		out.println(e.getMessage());
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
