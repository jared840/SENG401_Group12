<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
        <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="navbar.css">
<title>Product details</title>
</head>
<body>
<%
Product product = (Product)request.getAttribute("product");
String supplierName = (String) request.getAttribute("supplierName");

%>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="allProductsController">View products</a></li>
        <li><a href="SelectionPage.jsp">Logout</a></li>  
      </ul>

<% out.print("Name:"+product.getName());%>
<br>
<% out.print("Desc:"+product.getDescription());%>
<br>
<% out.print("Price:"+product.getPrice());%>
<br>
<% out.print("Items in stock:"+product.getStock());%>
<br>
<% out.print("Seller:"+supplierName);%>

</body>
</html>