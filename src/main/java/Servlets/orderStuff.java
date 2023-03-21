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
 * Servlet implementation class orderStuff
 */
@WebServlet("/orderStuff")
public class orderStuff extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public orderStuff() {
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
		
		
		int order=Integer.parseInt(request.getParameter("param"));
		
		try {
			
			DBController d= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
			ArrayList<Product> items = new ArrayList<Product>();
			items=d.getOrderItems(order);
			request.setAttribute("items",items);
			request.getRequestDispatcher("DisplayItems.jsp").forward(request,response);

			
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
