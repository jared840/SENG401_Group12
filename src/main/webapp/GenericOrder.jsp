

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
        <%@page import="entities.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Orders</title>

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

<%
ArrayList <Order> ODL=new ArrayList<Order>();
ODL= (ArrayList)request.getAttribute("OrderList");

out.println("<table class=GeneratedTable>");
out.println("<th>");
out.println("<tr>");

out.println("<th>Order ID</th>");
out.println("<th>Date</th>");
out.println("<th>Total Cost</th>");
out.println("<th>Shipping Address</th>");
out.println("<th>Order Information</th>");

out.println("</tr>");
out.println("</th");

out.println("<tbody>");

for(int i=0;i<ODL.size();i++)
{
	out.println("<tr>");
	out.println("<td>"+ODL.get(i).getOrder_ID()+"</td>");
	out.println("<td>"+ODL.get(i).getO_Date()+"</td>");
	out.println("<td>"+ODL.get(i).getO_Total()+"</td>");
	out.println("<td>"+ODL.get(i).getShip_Address()+"</td>");
	out.println("<td>"+"<a href=orderStuff?param="+ODL.get(i).getOrder_ID()+">view products</a>" + "</td>");
	out.println("<td>"+"<a href=shipServlet?param="+ODL.get(i).getOrder_ID()+">Ship This Order</a>" + "</td>");
	out.println("</tr");
		
}

out.println("</tbody>");
out.println("</table>");
%>





</body>
</html>
