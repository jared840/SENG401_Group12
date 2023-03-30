<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="navbar.css">
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
   <script src="js/triggerEventSourcing.js"></script> 
   <link rel="stylesheet" type="text/css" href="css/checkout.css">
   <link rel="stylesheet" type="text/css" href="css/table.css"> 
<title>Process order</title>
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

<%
User currentUser=(User)request.getSession().getAttribute("currentUser");
Order data = (Order)request.getAttribute("order");
%>

</script>

	<table id="order-items">
  <thead>
    <tr>
      <th>Product name</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Total</th>
    </tr>
  </thead>
  <tbody>
  <% for (int i=0; i<data.getProductsOrdered().size(); i++) { %>
    <tr >
      <td><%= data.getProductsOrdered().get(i).getProduct().getName() %></td>
      <td><%= data.getProductsOrdered().get(i).getQuantity() %></td>
      <td>
		<%= data.getProductsOrdered().get(i).getsubTotal() %>
      </td>
      <td class="item_total">$<%=data.getProductsOrdered().get(i).getProduct().getPrice() %></td>
    </tr>
    <%} %>

  </tbody>
  <tfoot>
    <tr>
      <td colspan="4" align="right">
        Sub Total: <strong id="sub_total">$<%=data.getO_Total() %></strong>
      </td>
    </tr>
  </tfoot>
</table>

<div id="panel2" class="card">
		<div class="order-wrap">
			<div>
				<div class="heading-order">
					Shipping Address
				</div>
				<div class="address">
					<div class="form-group">
						<label>Name</label>
						<input type="text" class="form-control" value="<%=currentUser.getName() %>" disabled>
					</div>
					<div class="form-group">
						<label>Address</label>
						<input type="text" class="form-control" value="<%=currentUser.getUserAddress() %>" disabled>
					</div>
					<div class="form-group">
						<label>Email</label>
						<input type="text" class="form-control" value="<%=currentUser.getuserEmail() %>" disabled>
					</div>
				</div>
				<div class="heading-order">
					Payment Method
				</div>
				<div class="radio-wrap">
					<div>
						<input type="radio" checked> Credit Card
						<img src="http://clients.cylindo.com/Viewer/3.x/latest/documentation/img/creditcard.gif" alt="">
						<br>
						<label>Enter your credit card number <input id="num" placeholder="Hint:<%=currentUser.getcardNumber() %>" type='text' onkeypress='validate(event)' /></label>
					</div>
				</div>
			</div>
			<br>
			<button id="place-order" type="button" class="btn btn-success">PLACE MY ORDER</button>
		</div>
	</div>
	



<script type="text/javascript">

function validate(evt) {
	  var theEvent = evt || window.event;

	  // Handle paste
	  if (theEvent.type === 'paste') {
	      key = event.clipboardData.getData('text/plain');
	  } else {
	  // Handle key press
	      var key = theEvent.keyCode || theEvent.which;
	      key = String.fromCharCode(key);
	  }
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}
	
</script>

<script type="text/javascript">


$( "#place-order" ).click(function(e) {
	e.preventDefault()
	var correctCardNo = '<%=currentUser.getcardNumber()%>'

	if(correctCardNo != parseInt($("#num").val())){
		alert("Wrong credit card number")
		return;
	}
	
	
	var event = {
					userId:<%= currentUser.getUser_ID() %>,
					page:"PlaceOrder", 	
					eventType: "OrderPlaced", 
					creditCardNo: '<%= currentUser.getcardNumber()%>'
				}
	console.log(EventTypes.PlaceOrder)
	console.log(EventTypes.Remove)
    e.preventDefault(); // avoid to execute the actual submit of the form.
	$.post("EventController", $.param(event), function(response) {	
		$.post("ProcessOrderController", $.param({'orderId':'<%=data.getOrder_ID()%>'}), function(response) {
			window.location=`trackOrderServlet?order_ID=<%=data.getOrder_ID()%>`
		});
	});
    


});
</script>

</body>
</html>