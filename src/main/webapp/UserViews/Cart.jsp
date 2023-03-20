<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
        <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="navbar.css">
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
   <script src="js/triggerEventSourcing.js"></script> 
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Your cart</title>
</head>
<body>
<%
Order data = (Order)request.getAttribute("order");
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

$(window).load(function () {
	$(document).on("click", ".removeAll", function (e) {	
		e.preventDefault(); 
		console.log(e.target.id)
	    <% for (int i=0; i<data.getProductsOrdered().size(); i++) { %>
	    var prod = '<%=data.getProductsOrdered().get(i).getProduct().getProductId()%>'
	    	
	    	if(prod == e.target.id)
	    		var quantity = '<%=data.getProductsOrdered().get(i).getQuantity()%>'
	    <% } %>

		var event = {
				userId:<%= currentUser.getUser_ID() %>,
				page:"Cart", 
				productId: e.target.id,
				quantity:quantity,
				eventType: EventTypes.Remove
			}
    		
		if (confirm("Are you sure you want to delete this item(s)") == false) return;
    	$.post("EventController", $.param(event), function(response) {
		    location.reload()
		});
	}); 
	
	$(document).on("click", ".changeInput", function (e) {	
		e.preventDefault();
	    <% for (int i=0; i<data.getProductsOrdered().size(); i++) { %>
	    var prod = '<%=data.getProductsOrdered().get(i).getProduct().getProductId()%>'
	    	
	    	if(prod == e.target.id)
	    		var quantity = '<%=data.getProductsOrdered().get(i).getQuantity()%>'
	    <% } %>
	    var spinnerQuant = parseInt($("#spinner"+e.target.id).val());
	    var quant = parseInt(quantity);
	    if(spinnerQuant ==quant )return;

		var event = {
				userId:<%= currentUser.getUser_ID() %>,
				page:"Cart", 
				productId: e.target.id,
				quantity:spinnerQuant-quant,
				eventType: spinnerQuant > parseInt(quantity) ? EventTypes.Add : EventTypes.Remove
			}
    		
    	$.post("EventController", $.param(event), function(response) {
		    location.reload()
		}); 
	});
});

</script>
<div class="m-4">
  <h3>Your cart</h3>
  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th width="200">Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
        <th width="150">Action</th>
      </tr>
    </thead>
    <tbody>
    
      <%
      for(OrderItemLine item : data.getProductsOrdered()){
    	  String productName = item.getProduct().getName();
    	  String quantity = String.valueOf(item.getQuantity());
    	  String price = String.valueOf(item.getsubTotal());
    	  String max = String.valueOf("asd");
    	  String total = String.valueOf(item.getProduct().getPrice());
		  String stockLeft = String.valueOf(item.getProduct().getStock());
		  String productId = String.valueOf(item.getProduct().getProductId());
     // String tableRow = String.format("<tr >         <td class=\"align-middle\">           %s         <//td>         <td class=\"align-middle\">           %s             <//td>         <td class=\"align-middle\">          %s         <//td>         <td class=\"align-middle\">%s<//td>         <td> HERE IS TD <button id=\"%s\" class=\"removeAll\">Remove all<// button          <//tr>       <tr>         <td class=\"align-middle\" colspan=\"3\"><//td>         <td class=\"align-middle\" >           %s                     <//td>         <td><//td>       <//tr>        <tr>         <td class=\"align-middle\" colspan=\"3\">Total Price<//td>         <td class=\"align-middle\" >           %s          <//td>         <td><//td>       <//tr>", productName, quantity,total,price,productId,stockLeft,quantity,"11","11");
	  //out.println(tableRow);
      }
      %>
      <% for (int i=0; i<data.getProductsOrdered().size(); i++) { %>
      
		<tr >
        <td class="align-middle">
          <%= data.getProductsOrdered().get(i).getProduct().getName() %>
        </td>
        <td class="align-middle">
          <%= data.getProductsOrdered().get(i).getQuantity() %>
        </td>
        <td class="align-middle">
         <%= data.getProductsOrdered().get(i).getProduct().getPrice() %>
        </td>
        <td class="align-middle">
        <%= data.getProductsOrdered().get(i).getsubTotal() %>
        </td>
        <td>
        <div>
        <form>
         	<button id='<%= data.getProductsOrdered().get(i).getProduct().getProductId() %>' type="submit" class="btn btn-success changeInput" >Add or remove</button>
        	<input id='spinner<%= data.getProductsOrdered().get(i).getProduct().getProductId()%>' type="number" class="form-control" min="0" max='<%= data.getProductsOrdered().get(i).getProduct().getStock()%>' value='<%= data.getProductsOrdered().get(i).getQuantity()%>'>
        </form>
		                  
         
		</div>
          <button id='<%= data.getProductsOrdered().get(i).getProduct().getProductId() %>' type="button" class="btn btn-danger removeAll" >
            <i class="fa fa-trash"></i>
            Remove all
          </button>
        </td>
      </tr>
      <tr>
        <td class="align-middle" colspan="3"></td>
        <td class="align-middle" >
        </td>
        <td></td>
      </tr>		
      <% } %>
            <tr>
        <td class="align-middle" colspan="3">Total price</td>
        <td class="align-middle" >
         
        </td>
        <td><%= data.getO_Total() %></td>
      </tr>
    </tbody>
  </table>
  <p>
    <button type="button" class="btn btn-primary"><i class="fa fa-plus"></i>Place order</button>
  </p>
</div>
</body>
</html>