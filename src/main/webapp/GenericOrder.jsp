

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
        <%@page import="entities.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Orders</title>

<style>



table.GeneratedTable {
  width: 100%;
  background-color: #ffffff;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  color: #000000;
}

table.GeneratedTable td, table.GeneratedTable th {
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  padding: 3px;
}

table.GeneratedTable thead {
  background-color: #ffcc00;
}
</style>


</head>
<body>

	
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="WarehouseWorkerHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
      <!--  <li><a href="CreateProduct.jsp">Create Product</a></li> -->
        <li><a href=allProductsController>View Products</a></li> 
        <li><a href="shipItems.jsp">Ship Orders</a></li>
     <!--    <li><a href="#cart">View Cart</a></li>	-->
                    <li><a href=SearchProducts.jsp>Search</a></li>
     
        <li><a href=GenericViewOrder>View Orders</a></li>
        <li><a href=findAllWarehouses>Inventory</a></li>
       
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>	
	
	
	
	
	
<%
ArrayList <Order> ODL=new ArrayList<Order>();
ODL= (ArrayList)request.getAttribute("OrderList");

out.println("<table class=GeneratedTable>");
out.println("<th>");
out.println("<tr>");

out.println("<th>Order ID</th>");
out.println("<th>Date</th>");
out.println("<th>Total Cost</th>");
out.println("<th>Shipping Address</th>");
out.println("<th>Order Information</th>");
out.println("<th>Ship This Order</th>");

out.println("</tr>");
out.println("</th");

out.println("<tbody>");

for(int i=0;i<ODL.size();i++)
{
	out.println("<tr>");
	out.println("<td>"+ODL.get(i).getOrder_ID()+"</td>");
	out.println("<td>"+ODL.get(i).getO_Date()+"</td>");
	out.println("<td>"+ODL.get(i).getO_Total()+"</td>");
	out.println("<td>"+ODL.get(i).getShip_Address()+"</td>");
	out.println("<td>"+"<a href=orderStuff?param="+ODL.get(i).getOrder_ID()+">view products</a>" + "</td>");
	out.println("<td>"+"<a href=shipServlet?order_ID="+ODL.get(i).getOrder_ID()+">Ship This Order</a>" + "</td>");
	out.println("</tr");
		
}

out.println("</tbody>");
out.println("</table>");
%>





</body>
</html>
