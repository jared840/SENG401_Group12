<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList" %>
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
        <li><a href="SupplierHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="CreateProduct.jsp">Create Product</a></li>
        <li><a href=allProductsController>View Products</a></li> 
       <li><a href=stockItemsServlet>Stock Items</a></li>
        
        <li><a href="CreateReport.jsp">Create Report</a></li>
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>

<b>Stocking item ID: 
<%= request.getAttribute("prodid")
%>
</b>


<form method=get action=warehouseServ>

<label>Enter new stock number:</label>
<input type=text placeholder="Number" name=stockno required>

<br>
<label>Select Warehouse to stock: </label>
<br>
<select name="WareSelect">
<% 

ArrayList<Warehouse> wares=new ArrayList();
wares=(ArrayList)request.getAttribute("warehouses");

for(int i=0; i<wares.size();i++){
	out.println("<option>"+wares.get(i).getWarehouseID()+": "+wares.get(i).getAddress()+"</option>");
}

%>
</select>
<input type="hidden" name="prod_id" value=<%= request.getAttribute("prodid")%>>
<input type=submit value="SUBMIT">
</form>


</body>
</html>