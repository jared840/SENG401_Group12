package Servlets;
import entities.*;
import database.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Homeloginreg
 */
@WebServlet("/Homeloginreg")
public class Homeloginreg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Homeloginreg() {
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
	
		
		String username=request.getParameter("usernamesupplier");
		String password=request.getParameter("passwordsupplier");
		String name=request.getParameter("namesupplier");
		String description=request.getParameter("descriptionsupplier");
	
		//TODO database functionality
				try {
			
			DBController d= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
			Supplier s=new Supplier(1,name,description,username,password);
			d.newUser(s);
					
					HttpSession mysession=request.getSession();
		mysession.setAttribute("currentSupplier", s);
		request.getRequestDispatcher("SupplierHome.jsp").forward(request,response);
			
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
