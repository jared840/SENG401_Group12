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
		
		boolean correctLogin=true;
		
		request.setAttribute("userback",request.getParameter("userin"));
		
		
		try {
			DBController db=new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","vick-newton7.1");
		
		//---------------------------------------------------------------------------------
		//First: Lets verify the login credentials using DBController's verifyLogin():
		//----------------------------------------------------------------------------------
			try {
				db.verifyLogin(username, pswd);
				out.println("Success");
				correctLogin=true;
			}catch(Exception e) {
				out.println("<center><b>Incorrect login information!<\b>"+"<br> Please Try Again with correct credentials: "
			+ "<a href=SelectionPage.jsp>TRY AGAIN</a>");
				//request.getRequestDispatcher("IncorrectLogin.jsp").forward(request,response);
				correctLogin=false;
				
			}
			
			
			request.setAttribute("ParsedUser", user_type);
		
		if(user_type.equals("Supplier")&&correctLogin==true) {
			
			//Supplier s = new Supplier(1, "a", "b", "c", "d");
			//request.getRequestDispatcher("SupplierHome.jsp").forward(request, response);//
			
			
				Supplier supplierloggedin=db.getSupplier(username, pswd);
				
				request.setAttribute("currentSupplier", supplierloggedin);
				request.getRequestDispatcher("SupplierHome.jsp").forward(request, response);
				
			
		}
		else if(user_type.equals("WarehouseWorker")&&correctLogin==true) {
			try {
				
				WarehouseWorkers loggedin = db.getWarehouseWorkers(username, pswd);
				
				//out.println("Welcome "+loggedin.getE_Name());
				request.setAttribute("currentWorker", loggedin);
				request.getRequestDispatcher("WarehouseWorkerHome.jsp").forward(request, response);
				
			} catch(Exception e) {
				out.println(e.getMessage());
			}
		}
		else if(user_type.equals("Customer")&&correctLogin==true) {
			
		 User customerloggedin=db.getUser(username, pswd);
			
			request.setAttribute("currentUser", customerloggedin);
			request.getRequestDispatcher("UserHome.jsp").forward(request, response);
			
		}
		else if(correctLogin==false) {
			request.getRequestDispatcher("IncorrectLogin.jsp").forward(request,response);
		}
		
		
		}catch(Exception e) {
			out.println("Error present!" + "<a href=SelectionPage.jsp>Go Back</a>");
		}
	}
		/*
		request.setAttribute("Clinics", toSend);
		request.getRequestDispatcher("PatClins.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
