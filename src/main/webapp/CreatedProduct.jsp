<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.*" %>
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
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="SelectionPage.jsp">Logout</a></li> 
        
      </ul>

<%
try{
if(request.getAttribute("productSuccess").equals("true")){
	out.println("<b>Successfully created Product!</b>");
	Product createdProduct=(Product)request.getSession().getAttribute("currentProduct");
	out.println("Product name: "+createdProduct.getName());
	out.println("Product price: "+createdProduct.getPrice());
	out.println("Product description: "+createdProduct.getDescription());
	out.println("Product category: "+createdProduct.getCategory());
//TODO allow option to stock the product they just made

	/*
	out.println("Would you like to stock this product?")
out.println("<a href=stockItemServlet?param=createdProduct>Stock this Product</a>"); */


}
else{
	out.println("<b>Unable to create product!</b>");
	out.println("Select Create New Product from the navigation bar to try again");
}
}catch(Exception e){
	out.println(e.getMessage());
}
	

%>



</body>
</html>