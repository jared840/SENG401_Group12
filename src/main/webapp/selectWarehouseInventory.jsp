<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList" %>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select Warehouse to View Inventory</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
<link rel="stylesheet" type="text/css" href="mainwebBackground.css">
</head>
<body>

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
<form method=get action=warehouseInventServ>


<br>
<label>Select Warehouse to view inventory: </label>
<br>
<select name="WareSelected">
<% 

ArrayList<Warehouse> wares=new ArrayList();
wares=(ArrayList)request.getAttribute("all_warehouses");

for(int i=0; i<wares.size();i++){
	out.println("<option>"+wares.get(i).getWarehouseID()+": "+wares.get(i).getAddress()+"</option>");
}

%>
</select>

<input type=submit value="SUBMIT">
</form>

</body>
</html>