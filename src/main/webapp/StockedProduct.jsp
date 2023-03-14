<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stock Items</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
<style>
table.mytable {
  width: 100%;
  background-color: #ffffff;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  color: #000000;
}

table.mytable td, table.mytable th {
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  padding: 3px;
}

table.mytable thead {
  background-color: #ffcc00;
}
</style>
</head>
<body>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Cancel</a></li> 
        <li><a href="SelectionPage.jsp">Logout</a></li> 
        
      </ul>
<%
ArrayList<Product> prods=(ArrayList)request.getAttribute("supplierProds");
out.println("<table class=mytable border=1 width=50% height=50% ><tr class=header><th>Item_ID</th>");
out.println("<th>Item Name</th><th>Description</th><th>Cost</th><th>Category</th><th>Supplier ID</th><th>Stock Item</th></tr>");

for(int i=0; i<prods.size();i++){
	out.println("<tr>");
	out.println("<td>" +prods.get(i).getProductId()+" </td>");

	out.println("<td>" +prods.get(i).getName() +" </td>");
	out.println("<td>" +prods.get(i).getDescription() +" </td>");
	out.println("<td>" +prods.get(i).getPrice() +" </td>");
	out.println("<td>" +prods.get(i).getCategory() +" </td>");
	out.println("<td>" +prods.get(i).getSupplierId() +" </td>");
	out.println("<td><a href=stockItembyID?param=" +prods.get(i).getProductId() +">Stock Item</a> </td>");

	




	out.println("</tr>");
}


out.println("</table");
%>
</body>
</html>