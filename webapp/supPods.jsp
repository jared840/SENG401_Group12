

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
        <%@page import="entities.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Orders</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
  <link rel="stylesheet" type="text/css" href="mainwebBackground.css">

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


</head>
<body>

<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="CreateProduct.jsp">Create Product</a></li>
        <li><a href=viewSupplierProds>View Your Products</a></li> 
       <li><a href=stockItemsServlet>Stock Items</a></li>
               <li><a href=SearchProducts.jsp>Search</a></li>
        
        <li><a href="CreateReport.jsp">Create Report</a></li>
        <li><a href="SelectionPage.jsp">Log Out</a></li>
      </ul>
      <center><b>These are your stocked products:</b></center>

<%
ArrayList <String> ODLL=new ArrayList<String>();
ODLL= (ArrayList)request.getAttribute("supPods");
        		

out.println("<table class=GeneratedTable>");
out.println("<th>");
out.println("<tr>");


out.println("<th>Product Name</th>");
out.println("<th>Quantity</th>");
out.println("<th>Warehouse Stocked Address</th>");


out.println("</tr>");
out.println("</th");

out.println("<tbody>");

for(int i=0;i<=ODLL.size()-3;i+=3){
	out.println("<tr>");
	out.println("<td>"+ODLL.get(i)+"</td>");
	out.println("<td>"+ODLL.get(i+1)+"</td>");
	out.println("<td>"+ODLL.get(i+2) +"</td>");

	out.println("</tr");
}

out.println("</tbody>");
out.println("</table>");
%>





</body>
</html>
