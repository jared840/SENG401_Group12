<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shipping Update</title>
<link rel="icon" href="image/logo.jpg">
 <link rel="stylesheet" type="text/css" href="navbar.css">
   <link rel="stylesheet" type="text/css" href="mainwebBackground.css">
 
</head>
<body>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="UserHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
         <li><a href="CartController">View your cart</a></li>
        <li><a href=allProductsController>View Products</a></li> 
         <li><a href=SearchProducts.jsp>Search</a></li>
         <li><a href="TrackOrder.jsp">Track an Order</a></li>
          <li><a href="SelectionPage.jsp">Logout</a></li> 
           
      </ul>

<%
String result=(String)request.getAttribute("ship_progress");
if(result.equals("wrong"))
{
	out.println("Something went wrong...");
	out.println("<br>");
	out.println("<img src=error.jpg alt=Trulli width=500 height=333>");
	
}

if(result.equals("true"))
{
	out.println("Your order has shipped!");
	out.println("<br>");
	out.println("<img src=shipping.jpg alt=Trulli width=500 height=333>");
}

if(result.equals("false"))
{
	out.println("Order placed!!!<br>Thanks you for your order. <br>Ship Progress: Your order has not been shipped");
	out.println("<br>");
	out.println("<img src=not.jpg alt=Trulli width=500 height=333>");
	
}
%>

</body>
</html>
