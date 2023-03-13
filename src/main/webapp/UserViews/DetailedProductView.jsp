<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
        <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="css/detailedProductCss.css">
<title>Product details</title>
</head>
<body>
<%
Product product = (Product)request.getAttribute("product");
String supplierName = (String) request.getAttribute("supplierName");

%>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="allProductsController">View products</a></li>
        <li><a href="SelectionPage.jsp">Logout</a></li>  
      </ul>

<div id="product">
    <div class="product_images">
    	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG8Ogg1R2WkRRVnsxMlr_WqdNwhVzo9vvW_42SZm8&s" alt="No image found"/>
    </div>
    <div class="product_details">

        <% out.print("<h2>"+product.getName()+"</h2>");%>
        <% out.print("<h3>$"+product.getPrice()+"</h3>");%>
        

        <div class="about">          
            <% out.print("<p>Availability :<span>"+product.getStock()+"</span></p>");%>
            <% out.print("<p>Category: <span>"+product.getCategory()+"</span> </p>");%>  
            <% out.print("<p>Seller: <span>"+supplierName+"</span> </p>");%> 
        </div>

       <% out.print("<p>"+product.getDescription()+"</p>");%>
        
        <div class="cta">
            <div class="btn btn_primary">Add to cart</div>
    	</div>
</div>

</div>

</body>
</html>