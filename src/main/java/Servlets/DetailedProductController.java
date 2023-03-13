package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBController;
import entities.Product;
import entities.Supplier;

/**
 * Servlet implementation class DetailedProductController
 */
@WebServlet("/DetailedProduct")
public class DetailedProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailedProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String x= (String)request.getParameter("productId");
		request.setAttribute("test", x);
		request.getRequestDispatcher("UserViews/DetailedProductView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Integer id = Integer.parseInt((String)request.getParameter("productId"));
			DBController db = new DBController("jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root", "Admin");
			Product product = db.getProductById(1);
			request.setAttribute("product", product);		
			Supplier supplier = db.getSupplierBySupplierId(product.getSupplierId());
			request.setAttribute("supplierName", supplier.getName());	
			request.getRequestDispatcher("UserViews/DetailedProductView.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.print("");
		}
		
		
		
	}

}