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
 <script src="js/triggerEventSourcing.js"></script> 
 <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<title>Product details</title>
</head>
<body>
<%
Product product = (Product)request.getAttribute("product");
String supplierName = (String) request.getAttribute("supplierName");
User currentUser=(User)request.getSession().getAttribute("currentUser");

%>


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
            <div id="addToCart" class="btn btn_primary">Add to cart</div>
            <input type="number" value=1 id="quantity" name="quantity" min="1" max="<%= product.getStock()%>">
    	</div>
</div>

</div>
<script>
$(window).load(function () {
	$("#addToCart").click(function(e){
		
		var event = {
						userId:<%= currentUser.getUser_ID() %>,
						page:"DetailedPage", 
						productId: '<%= product.getProductId() %>',
						quantity:$("#quantity").val(),
						eventType: EventTypes.Add
					}
	    e.preventDefault(); // avoid to execute the actual submit of the form.
		$.post("EventController", $.param(event), function(response) {
		    alert("Item(s) added to the cart")
		    $("#quantity").val(1)
		});
	}); 
});
</script>

</body>
</html>