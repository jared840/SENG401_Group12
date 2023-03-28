<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    
    <%@page import="entities.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search For Product</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
  <link rel="stylesheet" href="css/shipItems.css">

</head>
<body>

<jsp:include page = "background.jsp"/>


<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="UserHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
         <li><a href="CartController">View your cart</a></li>
        <li><a href=allProductsController>View Products</a></li> 
         <li><a href=SearchProducts.jsp>Search</a></li>
         <li><a href="TrackOrder.jsp">Track an Order</a></li>
          <li><a href="SelectionPage.jsp">Logout</a></li> 
           
      </ul>


<form method=get action=SearchProduct>
<div class="contentbox">
<label style="font-size: 25px;">
Search Product
</label>
<br>
<br>
<br>
<input type=text placeholder="Enter Product Name" name=productName class="box">
<br>


<input type=submit value="Search" class="button">
</form>









</body>
</html>
