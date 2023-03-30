<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credit Card Payment</title>
<link rel="icon" href="image/logo.jpg">
</head>
<body>
    <h1>Credit Card Payment</h1>
    <form action=paymentServlet method=get>
        <label for="cardNumber">Credit Card Number:</label>
        <input type="text" id="cardNumber" name="cardNumber" required><br>
        <label for="expDate">Expiration Date:</label>
        <input type="text" id="expDate" name="expDate" placeholder="MM/YY" required><br>
        <label for="pin">CVV:</label>
        <input type="password" id="pin" name="pin" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
