<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.ArrayList" %>
    
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Items</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
  <link rel="stylesheet" type="text/css" href="mainwebBackground.css">

</head>

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



<body>

<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="WarehouseWorkerHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
      <!--  <li><a href="CreateProduct.jsp">Create Product</a></li> -->
       
        <li><a href="shipItems.jsp">Ship Orders</a></li>
     <!--    <li><a href="#cart">View Cart</a></li>	-->
                    <li><a href=SearchProducts.jsp>Search</a></li>
     
        <li><a href=GenericViewOrder>View Orders</a></li>
        <li><a href=findAllWarehouses>Inventory</a></li>
     <!--   <li><a href="CreateReport.jsp">Create Report</a></li> -->
        <li><a href="SelectionPage.jsp">Log Out</a></li>
        
      </ul>

<%
ArrayList <Product> item=new ArrayList<Product>();
item= (ArrayList)request.getAttribute("items");

out.println("<table class=GeneratedTable>");
out.println("<th>");
out.println("<tr>");

out.println("<th>Product ID</th>");
out.println("<th>Supplier ID</th>");
out.println("<th>Product</th>");
out.println("<th>Description</th>");
out.println("<th>Price</th>");
out.println("<th>Category</th>");
out.println("<th>Info</th>");

out.println("</tr>");
out.println("</th");

out.println("<tbody>");

for(int i=0;i<item.size();i++)
{
	out.println("<tr>");
	out.println("<td>"+item.get(i).getProductId()+"</td>");
	out.println("<td>"+item.get(i).getSupplierId()+"</td>");
	out.println("<td>"+item.get(i).getName()+"</td>");
	out.println("<td>"+item.get(i).getDescription()+"</td>");
	out.println("<td>"+item.get(i).getPrice()+"</td>");
	out.println("<td>"+item.get(i).getCategory()+"</td>");
	out.println("<td>"+"<a href=SearchProduct?productName="+item.get(i).getName()+">See Item</a>"+"</td>");
	out.println("</tr");
		
}

out.println("</tbody>");
out.println("</table>");
%>

</body>
</html>
