<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="navbar.css">
</head>
<body>

<%
User in=(User)request.getSession().getAttribute("currentUser");
 %>
<!-- Navigation bar -->
      <ul id="navbar">
        
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="allProductsController">View products</a></li>
        <li><a href="CartController">View your cart</a></li>
        <li><a href="SelectionPage.jsp">Logout</a></li>  
      </ul>
      
</body>
</html>