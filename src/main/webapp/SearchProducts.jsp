<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    
    <%@page import="entities.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search For Product</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
<link rel="stylesheet" href="css/shipItems.css">
</head>
<body>

<jsp:include page = "background.jsp"/>

	<form method=get action=SearchProduct>
		<div class="contentbox">
		<label style="font-size: 25px;">
		Search Product
		</label>
		<br>
		<br>
		<br>
		<input type=text placeholder="Enter Product Name" name=productName class="box">
		<br>
		<br>
		<input type=submit value="Search" class="button">
	</div>
	
	</form>

</body>
</html>
