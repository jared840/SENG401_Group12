<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList" %>
    
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products Searched</title>
</head>

<style>
table.GeneratedTable {
  width: 100%;
  background-color: #ffffff;
  border-collapse: collapse;
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  color: #000000;
}
table.GeneratedTable td, table.GeneratedTable th {
  border-width: 2px;
  border-color: #ffcc00;
  border-style: solid;
  padding: 3px;
}
table.GeneratedTable thead {
  background-color: #ffcc00;
}
</style>

<body>

<%
ArrayList<Product> prod=new ArrayList<Product>();
prod=(ArrayList)request.getAttribute("p");
if(prod.size()==0)
{
	out.println("oops...there seems to be no product matching what you're looking for");
			
}
else
{
	out.println("<table class=GeneratedTable>");
	out.println("<th>");
	out.println("<tr>");
	
	out.println("<th>Product ID</th>");
	out.println("<th>Supplier ID</th>");
	out.println("<th>Product</th>");
	out.println("<th>Description</th>");
	out.println("<th>Price</th>");
	out.println("<th>Category</th>");
	out.println("<th>Stock</th>");
	
	out.println("</tr>");
	out.println("</th");
	out.println("<tbody>");
	
	for(int i=0;i<prod.size();i++)
	{
		out.println("<tr>");
		out.println("<td>"+prod.get(i).getProductId()+"</td>");
		out.println("<td>"+prod.get(i).getSupplierId()+"</td>");
		out.println("<td>"+prod.get(i).getName()+"</td>");
		out.println("<td>"+prod.get(i).getDescription()+"</td>");
		out.println("<td>"+prod.get(i).getPrice()+"</td>");
		out.println("<td>"+prod.get(i).getCategory()+"</td>");
		out.println("<td>"+prod.get(i).getStock()+"</td>");
		out.println("</tr");
			
	}
	out.println("</tbody>");
	out.println("</table>");
	
}

%>




</body>
</html>