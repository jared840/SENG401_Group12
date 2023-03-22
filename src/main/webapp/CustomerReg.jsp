<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet" href="css/registration.css">
</head>
<body>

	<jsp:include page = "background.jsp"/>
	<h1>Customer SignUp!</h1><br>
	<div class="Signup">
	<form method=get action=Customerreg>
		<label>Please enter the required fields</label>
		<br>
		<br>
		<label>Name:</label>
		<input type = text placeholder="Name" name=nameuser class="box">
		<br>
		<br>
		<label>Username:</label>
		<input type=text placeholder="Username" name=usernameuser required class="box">
		<br>
		<br>
		<label>Password:</label>
		<input type=password placeholder="Password" name=passworduser class="box">
		<br>
		<br>
		<label>Email:</label>
		<input type=text placeholder="Email" name=emailuser class="box">
		<br>
		<br>
		<label>Address:</label>
		<input type=text placeholder="Address" name=addressuser class="box">
		<br>
		<br>
		<label>Credit Card Number:</label>
		<input type=text placeholder="Credit Card Number" name=creditcarduser class="box">
		<br>
		<br>
		
		<input type=submit value="Register" class="button">
	</form>
	</div>


</body>
</html>