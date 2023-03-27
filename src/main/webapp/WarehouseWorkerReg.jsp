<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Warehouse Worker Registration</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" href="css/registration.css">
</head>
<body>

	<jsp:include page = "background.jsp"/>
	<h1>Warehouse Worker SignUp!</h1><br>
	<div class="Signup">
	<form method=get action=WarehouseWorker>
	
	<label>Please enter the required fields</label>
			<br>
			<br>
			<label>Name:</label>
			<input type = text placeholder="Name" name=nameww class="box">
			<br>
			<br>
			<label>Username:</label>
			<input type=text placeholder="Username" name=usernameww required class="box">
			<br>
			<br>
			<label>Password:</label>
			<input type=password placeholder="Password" name=passwordww class="box">
			<br>
			<br>
	
	<input type=submit value="Register" class="button">
	</form>
	</div>

</body>
</html>
