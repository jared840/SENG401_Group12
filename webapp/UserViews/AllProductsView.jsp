<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
        <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View all products</title>
	 <script src="js/triggerEventSourcing.js"></script> 
    <link rel="stylesheet" type="text/css" href="css/products.css">
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2023.1.117/styles/kendo.default-v2.min.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2023.1.117/js/kendo.all.min.js"></script>
    <link rel="stylesheet" type="text/css" href="navbar.css">
    <!-- END script for table -->
</head>
<body>

<%

ArrayList<Product> data = (ArrayList<Product>)request.getAttribute("orders");
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
	$(".addToCart").click(function(e){
		
		var event = {
						userId:'<%= currentUser.getUser_ID() %>',
						page:"ViewAll", 
						productId: e.target.id,
						quantity:1,
						eventType: EventTypes.Add
					}
	    e.preventDefault(); // avoid to execute the actual submit of the form.
		$.post("EventController", $.param(event), function(response) {
		    // handle response here if you have one
		});
	}); 
});

</script>
      

    <div id="example">
    <div id="listView"></div>

	
    <script type="text/x-kendo-template" id="template">
        <div class="product">

			<img src=#:Uri# alt="Sorry, we can not display the image"/>
            <h3>#:Name#</h3>
            <h4>#:kendo.toString(Price, "c")#</h4>  
			<h3>#:Stock# left</h3> 
			
			<form action=DetailedProduct method="post" >
 				<input type="hidden" name=productId value=#:ProductId# />
				<button type="submit">View details</button>
			</form>
			
        </div>
    </script>
    
    
    
    <script>
    var colArray = []

	console.log(x)
    <% for (int i=0; i<data.size(); i++) { %>
    	var x = {
    			"ProductId":<%= data.get(i).getProductId() %>,
    			"Description":"<%= data.get(i).getDescription() %>",
    			"Category":"<%= data.get(i).getCategory() %>",
    			"Name":"<%= data.get(i).getName().length() > 35 ? data.get(i).getName().substring(0,35)+"...":data.get(i).getName() %>",
    			"Price":<%= data.get(i).getPrice() %>,
    			"Stock":"<%= data.get(i).getStock()%>",
    			"Uri": "https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
    			}
    	
    	colArray[<%= i %>] = x
    <% } %>

        $(function () {
            $("#listView").kendoListView({
                dataSource: {
                    data: colArray,
                    pageSize: 22
                },
                template: kendo.template($("#template").html()),
                pageable: true,
                navigatable: true
            });
        });
    </script>
</div>


</body>
</html>