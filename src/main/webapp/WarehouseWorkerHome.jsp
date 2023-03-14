<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
</head>
<body>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="WarehouseWorkerHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="CreateProduct.jsp">Create Product</a></li>
        <li><a href=allProductsController>View Products</a></li> 
       
        <li><a href="#cart">View Cart</a></li>
        <li><a href=GenericViewOrder>View Orders</a></li>
        <li><a href="inventory">Inventory</a></li>
        <li><a href="CreateReport.jsp">Create Report</a></li>
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>
<%

//WarehouseWorkers currentUser=(WarehouseWorkers)request.getAttribute("currentWorker");
//out.println("Welcome to the system: " //+ currentUser.getE_Name());


WarehouseWorkers currentUser=(WarehouseWorkers)request.getSession().getAttribute("currentWarehouseWorker");
out.println("Your name is: "+currentUser.getE_Name());


%>


</body>
</html>
