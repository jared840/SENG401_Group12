<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form method=get action=shipServlet>

<label>Enter the order ID for the shipped order</label>
<br>
<input type=text placeholder="Order ID" name=order_ID required>
<br>


<input type=submit value="Submit">
</form>
</body>
</html>