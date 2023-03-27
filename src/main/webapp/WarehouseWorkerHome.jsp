<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Warehouse Worker HomePage</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
<link rel="stylesheet" type="text/css" href="mainwebBackground.css">
<link rel="stylesheet" href="css/shipItems.css">
 
</head>
<body>
	<jsp:include page = "background.jsp"/>
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
      
   <div class = "contentbox">   
	<%
	WarehouseWorkers currentUser=(WarehouseWorkers)request.getSession().getAttribute("currentWarehouseWorker");
	out.println("<span style=\"color: black; font-size: 25px;\">Welcome: "+currentUser.getE_Name());
	%>
	</div>

</body>
</html>
