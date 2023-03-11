<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Creation</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
</head>
<body>

<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Cancel</a></li> 
        <li><a href="SelectionPage.jsp">Logout</a></li> 
        
      </ul>
      
 <b>Fill in the Information below to create a new product:</b>
 <br>
 <form method=get action=createProductServlet>
 <label>Item Name: </label>
 <input type=text placeholder="Item Name" name=itemName required>
 <br>
 <label>Item description: </label>
 <input type=text placeholder="Description" name=itemDesc required>
 <br>
 <label>Item Cost: $</label>
 <input type=text placeholder="Price" name=itemCost required>
 <br>
 <label>Item category (eg. electronics): </label>
 <input type=text placeholder="Category" name=itemCategory>
 <br>
 
 
 <input type=submit value="Create Product!">
 
 </form>

</body>
</html>