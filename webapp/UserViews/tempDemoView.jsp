<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="entities.*" %>
     <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>This is the event flow for order 1</h1>
<%
Order data = (Order)request.getAttribute("order");
User currentUser=(User)request.getSession().getAttribute("currentUser");

for (OrderEvent e : data.getOrderEvents()) {
	out.println("<p>"+e.toString()+"</p>");
}
%>
<h1>This is the result of the event flow for order 1</h1>
<%

for (OrderItemLine e : data.getProductsOrdered()) {
	out.println("<p>"+e.toString()+"</p>");
}
%>
<h3>Which makes the total sum of the cart:<%=data.getO_Total() %></h3>
</body>
</html>