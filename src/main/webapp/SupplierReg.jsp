<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>New supplier</title>
<link rel="stylesheet" href="css/SelectionPage.css">

</head>
<body>
	<jsp:include page = "background.jsp"/>
<div class = "login">
	<form method=get action=Homeloginreg>
	
	
		<label>
		<b>Please complete the form</b>
		</label>
		<br>
		<input type=text placeholder="name" name=namesupplier required class="box">
		<br>
		<input type=text placeholder="Username" name=usernamesupplier required class="box">
		<br>
		<input type=password placeholder="password" name=passwordsupplier required class="box">
		<br><br><br>
		<label>
		<b>Please tell us about yourself</b>
		</label>
		<br>
		<input type=text placeholder=description name=descriptionsupplier required class="box">
		<br><br>
		<input type= submit value="register" required class="box">
	</form>



</div>
</body>
</html>
