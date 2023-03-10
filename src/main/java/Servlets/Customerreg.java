package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.*;
import database.*;
/**
 * Servlet implementation class Customerreg
 */
@WebServlet("/Customerreg")
public class Customerreg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Customerreg() {
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
		out.println("<body style = 'background-colour: #dedede; '>");

		String username=request.getParameter("usernameuser");
		String password=request.getParameter("passworduser");
		String name=request.getParameter("nameuser");
		String address=request.getParameter("addressuser");
		String email=request.getParameter("emailuser");
		int creditCard=Integer.parseInt(request.getParameter("creditcarduser"));
		
		out.println(username+ password+ name+ name+ address+ email+ creditCard);
	//out.println("<body style = 'background-colour: #dedede; '>");
	
	try {
		
		DBcontroller d= new DBcontroller("jdbc:mysql://localhost:3306/SENG401Project", "root","password");
		User u=new user(1,name,address,email,password,creditCard);
		d.newUser(u);
		
	}catch(exception e)
	{
		out.println("user registration in servlet error");
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
