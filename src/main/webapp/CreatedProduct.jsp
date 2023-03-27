<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Creation</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
   <link rel="stylesheet" type="text/css" href="mainwebBackground.css">
 
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
	out.println("<b>Successfully created Product!</b><br>");
	Product createdProduct=(Product)request.getSession().getAttribute("currentProduct");
	out.println("<br>Product name: "+createdProduct.getName());
	out.println("<br>Product price: "+createdProduct.getPrice());
	out.println("<br>Product description: "+createdProduct.getDescription());
	out.println("<br>Product category: "+createdProduct.getCategory());
//TODO allow option to stock the product they just made

	
	out.println("<br><b>STEP 2: Stock this Product:</b><br> Look for this product in your unique product listing with the below link:");

out.println("<br><a href=stockItemsServlet>Stock this Product</a>"); 


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
