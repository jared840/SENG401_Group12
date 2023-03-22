<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
<link rel="stylesheet" href="css/SelectionPage.css">
</head>
<body>
	<jsp:include page = "background.jsp"/>
	
	<h1>Welcome!</h1><br>
	
	<div class="login">
	<form method=get action=credentialServlet>
	
		<label>Enter Username:
		</label>
		<input type=text placeholder="Username" name=userin required class="box">
		<br><br>
		<label>Enter Password:
		</label>
		<input type=password placeholder="Password" name=pswd_in required class="box">
		<br><br>
		<select name="UserType" class="usertype">
		<% 
		
		ArrayList<String> users = new ArrayList();
		users.add("Supplier");
		users.add("WarehouseWorker");
		users.add("Customer");
		
		for(int i=0; i<users.size();i++){
			out.println("<option>"+users.get(i)+"</option>");
		}
		
		%>
		</select>
		<input type=submit value="Login" class="button">
	</form>
	<br>
	<h2>
	New User? Please register according to your role:
	</h2>
	
	<a href=SupplierReg.jsp class="a1">New Supplier</a>
	<a href=CustomerReg.jsp class="a2">New Customer</a>
	<a href=WarehouseWorkerReg.jsp class="a3">New Warehouse Employee</a>
	
	
	</div>
	

</body>
</html>