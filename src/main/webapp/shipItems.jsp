<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ship Items</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
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
        
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>

	<form method=get action=shipServlet>
		
		<div class = "contentbox"> 
	
		<label>Enter the order ID for the shipped order</label>
		<br>
		<br>
		<input type=text placeholder="Order ID" name=order_ID required class="box">
		<br>
		<br>
		<input type=submit value="Submit" class="button">
		
		</div>
	</form>
</body>
</html>
