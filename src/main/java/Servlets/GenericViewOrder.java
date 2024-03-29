package Servlets;
import entities.*;
import database.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenericViewOrder
 */
@WebServlet("/GenericViewOrder")
public class GenericViewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GenericViewOrder() {
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
		
		try
		{
			DBController d= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
			ArrayList<Order> OD = new ArrayList <Order>();
			ArrayList<Boolean> bools = new ArrayList<Boolean>();
			OD=d.viewAllOrders();
			//check the status of every order in OD:
			for(int i=0; i<OD.size(); i++) {
				if(d.checkShipped(OD.get(i))==true) {
					bools.add(true);
				}
				else {
					bools.add(false);
				}
			}
			request.setAttribute("shippedHash", bools);
			request.setAttribute("OrderList",OD);
			request.getRequestDispatcher("GenericOrder.jsp").forward(request,response);
			
		}catch(Exception E)
		{
			out.println(E.getMessage());
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
