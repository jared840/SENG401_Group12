<% @page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
ArrayList <Order> ODL=new ArrayList<Order>();
ODL= request.getAttribute("OrderList");
out.println("<table>");
out.println("<tr>Order ID</tr>");
for(int i=0;i<ODL.size();i++)
{
	out.println("<td>"+ODL.get(i).getOrderID()+"</td>");
	
}
out.println("<tr>Date</tr>");
for(int i=0;i<ODL.size();i++)
{
	out.println("<td>"+ODL.get(i).getO_Date()+"</td>");
	
}
out.println("<tr>Total Cost</tr>");
for(int i=0;i<ODL.size();i++)
{
	out.println("<td>"+ODL.get(i).getO_Total()+"</td>");
	
}
out.println("<tr>Ship Address</tr>");
for(int i=0;i<ODL.size();i++)
{
	out.println("<td>"+ODL.get(i).getShip_Address()+"</td>");
	
}
out.println("<tr>Order Info</tr>");
for(int i=0;i<ODL.size();i++)
{
	out.println("<td>"+"<a href=orderStuff?param="+ODL.getOrder_ID+"</a>" + "</td>");
	
}

out.println("</table>");
%>





</body>
</html>