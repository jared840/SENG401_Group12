<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Shipping Status</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
</head>
<body>

<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="WarehouseWorkerHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="CreateProduct.jsp">Create Product</a></li>
        <li><a href=allProductsController>View Products</a></li> 
        <li><a href="shipItems.jsp">Ship Orders</a></li>
        <li><a href="#cart">View Cart</a></li>
        <li><a href=GenericViewOrder>View Orders</a></li>
        <li><a href="inventory">Inventory</a></li>
      
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>



<%
String result=(String)request.getAttribute("shipSuccess");
if(result.equals("true"))
		{
	out.println("Success!");
	out.println("<br>");
	out.println("<img src=success.jpg alt=Trulli width=500 height=333>");
		}
if(result.equals("false"))
{
	out.println("Not successful");
	out.println("<br>");
	out.println("<img src=failure.jpg alt=Trulli width=500 height=333>");
}


%>






</body>
</html>
