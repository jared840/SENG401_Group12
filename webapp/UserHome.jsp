<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Website</title>
<link rel="icon" href="image/logo.jpg">
 <link rel="stylesheet" type="text/css" href="navbar.css">
 <!--   <link rel="stylesheet" type="text/css" href="products.css"> -->
  <link rel="stylesheet" href="css/SelectionPage.css">
 
 <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2023.1.117/styles/kendo.default-v2.min.css"/>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2023.1.117/js/kendo.all.min.js"></script>
<style>


.center{
height: 20vh;
display: flex;
justify-content: center;
align-items: center;
font-family: 'Piazzolla', serif;
font-weight: bold;
font-sie: 41px;
text-align:center;

}



</style>


</head>
<body>

<jsp:include page = "background.jsp"/>

 <!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="UserHome.jsp">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
         <li><a href="CartController">View your cart</a></li>
        <li><a href=allProductsController>View Products</a></li> 
         <li><a href=SearchProducts.jsp>Search</a></li>
         <li><a href="TrackOrder.jsp">Track an Order</a></li>
          <li><a href="SelectionPage.jsp">Logout</a></li> 
           
      </ul>
       <div class="login">
       <center>
      <% 

User in=(User)request.getSession().getAttribute("currentUser");
out.println("Welcome: "+in.getName());

%>

      <h1>Warehouse Website</h1></center>
     <div class='fullscreen'>
     <div class="center">
     <br>
     <br>
     <p class="smallfirst">
     
     <dl>
     <dt><b>Navigation bar</b></dt>
     <dd>- Have a look around using the navigation bar. </dd>
     <br>
     <dt><b>View Products</b></dt>
     <dd>- Select View Products to see all products and start your shopping experience.
     </dd>
     <br>
     <dt><b>View Cart</b></dt>
     <dd>-Select View Your Cart to see your items and edit your cart.</dd>
     <br>
     <dt><b>Search Products</b></dt>
     <dd>-Select Search to search for the availability of a product by name.</dd>
     <br>
     <dt>Track Order</dt>
     <dd>-If you want to track the progress of your order(s), select Track an Order.</dd>
     </p>
     </dl>
     </div>
     </div>
    
</div>
</body>
</html>