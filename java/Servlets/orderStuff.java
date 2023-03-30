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
			
			DBController d= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","vick-newton7.1");
			ArrayList<Product> items = new ArrayList<Product>();

			ArrayList<OrderItemLine> items2 = new ArrayList<OrderItemLine>();
			//items=d.getOrderItems(order);
			ArrayList<Order> orders = new ArrayList<Order>();
			orders=d.getAllOrdes();
			for(int i=0;i<orders.size(); i++) {
				if(orders.get(i).getOrder_ID()==order) {
					
					items2=orders.get(i).getProductsOrdered();
					
				}
			}
			for(int i=0;i<items2.size();i++) {
				items.add(items2.get(i).getProduct());
			}
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
