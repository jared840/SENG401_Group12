<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Creation</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
 <link rel="stylesheet" href="css/SelectionPage.css">
</head>
<body>
	<jsp:include page = "background.jsp"/>

<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Cancel</a></li> 
        <li><a href="SelectionPage.jsp">Logout</a></li> 
        
      </ul>
<div class="login">
      
	 <b>Fill in the Information below to create a new product:</b>
	 <form method=get action=createProductServlet>
	 <label>Item Name: </label>
	 <input type=text placeholder="Item Name" name=itemName required class="box">
	 <br><br>
	 <label>Item description: </label>
	 <input type=text placeholder="Description" name=itemDesc required class="box">
	 <br><br>
	 <label>Item Cost: $</label>
	 <input type=text placeholder="Price" name=itemCost required class="box">
	 <br><br>
	 <label>Item category: </label>
	 <input type=text placeholder="Category" name=itemCategory required class="box">
	 <br><br>
	 
	 
	 <input type=submit value="Create Product!" required class="box">
 
 </form>
 </div>

</body>
</html>
