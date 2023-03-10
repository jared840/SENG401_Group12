package Servlets;

import java.io.IOException;
import entities.*;
import database.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class credentialServlet
 */
@WebServlet("/credentialServlet")
public class credentialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public credentialServlet() {
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
		String user_type=request.getParameter("UserType");
		String username=request.getParameter("userin");
		String pswd = request.getParameter("pswd_in");
		
		request.setAttribute("userback",request.getParameter("userin"));
		
		
		//TODO dB functionality
		
		if(user_type.equals("Supplier")) {
			request.setAttribute("ParsedUser", user_type);
			Supplier s = new Supplier(1, "a", "b", "c", "d");
			request.getRequestDispatcher("SupplierHome.jsp").forward(request, response);
		}
		else if(user_type.equals("WarehouseWorker")) {
			try {
				DBController db=new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
				WarehouseWorkers loggedin = db.getWarehouseWorkers(username, pswd);
				
				//out.println("Welcome "+loggedin.getE_Name());
				request.setAttribute("currentWorker", loggedin);
				request.getRequestDispatcher("WarehouseWorkerHome.jsp").forward(request, response);
				
			} catch(Exception e) {
				out.println(e.getMessage());
			}
		}
		else if(user_type.equals("Customer")) {
			
		}
		
		/*
		request.setAttribute("Clinics", toSend);
		request.getRequestDispatcher("PatClins.jsp").forward(request, response);*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
