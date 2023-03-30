package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBController;
import entities.Order;
import entities.User;

/**
 * Servlet implementation class trackOrderServlet
 */
@WebServlet("/trackOrderServlet")
public class trackOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trackOrderServlet() {
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
		
		HttpSession mysession=request.getSession();
		
		User inuser= (User)mysession.getAttribute("currentUser");
		int oid = Integer.parseInt(request.getParameter("order_ID"));
		
		// User customerloggedin=db.getUser(username, pswd);
		try {
		DBController db= new DBController("jdbc:mysql://localhost:3306/SENG401Project", "root","vick-newton7.1");
		inuser=db.getUser(inuser.getuserEmail(),inuser.getPassword());
		boolean status = db.getOrderStatus(inuser.getUser_ID(),oid);
		request.setAttribute("o__id", oid);
		if(status) {
			request.setAttribute("ship_progress", "true");
		}
		else {
			request.setAttribute("ship_progress", "false");
		}
		request.getRequestDispatcher("showTracking.jsp").forward(request,response);

		}catch(Exception e) {
			//System.out.println(e.getMessage());
			request.setAttribute("ship_progress", "wrong");
			request.getRequestDispatcher("showTracking.jsp").forward(request,response);

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
