<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
 <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2023.1.117/styles/kendo.default-v2.min.css"/>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2023.1.117/js/kendo.all.min.js"></script>
</head>



<body>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="CreateProduct.jsp">Create Product</a></li>
        <li><a href=allProductsController>View Products</a></li> 
       <li><a href=stockItemsServlet>Stock Items</a></li>
        
        <li><a href="CreateReport.jsp">Create Report</a></li>
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>



 Hello <%= request.getAttribute("ParsedUser") %> Username: <%= request.getAttribute("userback") %> 

<%



Supplier in=(Supplier)request.getSession().getAttribute("currentSupplier");
out.println("Welcome: "+in.getName());

%>




</body>
</html>
