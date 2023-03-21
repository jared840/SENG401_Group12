<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
<link rel="stylesheet" type="text/css" href="mainwebBackground.css">
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
        <li><a href="CreateReport.jsp">Create Report</a></li>
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>
      
      <%
      ArrayList<String> r=(ArrayList<String>)request.getAttribute("returnedInventory");
        		//out.println(r.size());
        		
        		out.println("<table class=GeneratedTable>");
        		out.println("<th>");
        		out.println("<tr>");
        		
        		out.println("<th>Warehouse Address</th>");
        		out.println("<th>Item ID</th>");
        		out.println("<th>Item Name</th>");
        		out.println("<th>Quantity</th>");
        		
        		
        		out.println("</tr>");
        		out.println("</th");
        		out.println("<tbody>");
        		
        		for(int i=0;i+3<r.size()&&i<r.size();i+=4)
        		{
        			out.println("<tr>");
        			out.println("<td>"+r.get(i)+"</td>");
        			out.println("<td>"+r.get(i+1)+"</td>");
        			out.println("<td>"+r.get(i+2)+"</td>");
        			out.println("<td>"+r.get(i+3)+"</td>");
        			
        			out.println("</tr");
        				
        		}
        		out.println("</tbody>");
        		out.println("</table>");
      
      
      %>
</body>
</html>