<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<body>

<form method=get action=Customerreg>

<label>Please enter the required fields</label>
<br>
<label>enter your name</label>
<input type = text placeholder="name" name=nameuser>
<br>
<label>enter a username</label>
<input type=text placeholder="Username" name=usernameuser required>
<br>
<label>enter a password</label>
<br>
<input type=password placeholder="password" name=passworduser>
<br>

<label>enter your email</label>
<br>
<input type=text placeholder="email" name=emailuser>
<br>

<label>enter your address</label>
<br>
<input type=text placeholder="address" name=addressuser>
<br>


<label>enter your credit card number</label>
<br>
<input type=text placeholder="credit card number" name=creditcarduser>
<br>

<input type=submit value="register">
</form>




</body>
</html>