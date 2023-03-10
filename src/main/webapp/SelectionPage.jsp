<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
<center>
Welcome to our system
<br>
<form method=get action=credentialServlet>

<label>Enter username:</label>
<input type=text placeholder="Username" name=userin required>
<br>
<label>Enter password:</label>
<input type=password placeholder="Password" name=pswd_in required>

<br>
<select name="UserType">
<% 

ArrayList<String> users=new ArrayList();
users.add("Supplier");
users.add("WarehouseWorker");
users.add("Customer");

for(int i=0; i<users.size();i++){
	out.println("<option>"+users.get(i)+"</option>");
}

%>
</select>
<input type=submit value="Login">
</form>

<br>
<b>
New User? Register below according to your role:
</b>

<a href=SupplierReg.jsp>New Supplier</a>
<a href=CustomerReg.jsp>New Customer</a>
<a href=WarehouseWorkerReg.jsp>New Warehouse Employee</a>




</center>
</body>
</html>