<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order done</title>
 <link rel="stylesheet" type="text/css" href="navbar.css">
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div id="navbar"></div>
<script>
$( document ).ready(function() {
	$.ajax({
	    type: 'GET',
	    url: 'NavbarController',
	    // other settings
	    success: function (result) {
	        $('#navbar').html(result);
	    }
	});

});
</script>
<h1>Thanks you for your order</h1>
<h3><a href="UserHome.jsp">Click here to go back to the homepage.</a></h3>

</body>
</html>