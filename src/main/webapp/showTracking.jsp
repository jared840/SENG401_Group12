<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shipping Update</title>
</head>
<body>


<%
String result=(String)request.getAttribute("ship_progress");
if(result.equals("wrong"))
{
	out.println("Something went wrong...");
	out.println("<br>");
	out.println("<img src=error.jpg alt=Trulli width=500 height=333>");
	
}

if(result.equals("true"))
{
	out.println("Your order has shipped!");
	out.println("<br>");
	out.println("<img src=shipping.jpg alt=Trulli width=500 height=333>");
}

if(result.equals("false"))
{
	out.println("Your order has not been shipped");
	out.println("<br>");
	out.println("<img src=not.jpg alt=Trulli width=500 height=333>");
	
}
%>

</body>
</html>