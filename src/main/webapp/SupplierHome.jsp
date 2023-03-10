<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello <%= request.getAttribute("ParsedUser") %> Username: <%= request.getAttribute("userback") %>

<%



Supplier in=(Supplier)request.getSession().getAttribute("currentSupplier");
out.println("Your name is: "+in.getName());

%>

</body>
</html>
