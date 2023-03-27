<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track Order</title>
<link rel="icon" href="image/logo.jpg">
 <link rel="stylesheet" type="text/css" href="navbar.css">
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


<form method=get action=trackOrderServlet>
<label>Enter your order ID</label>
<input type = text placeholder="Order ID" name=order_ID>




<input type=submit value="submit">
</form>

</body>
</html>
