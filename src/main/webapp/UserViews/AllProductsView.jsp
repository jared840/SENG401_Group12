<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
        <%@page import="entities.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View all products</title>

    <link rel="stylesheet" type="text/css" href="navbar.css">
    <!-- script for table -->
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

%>

<!-- Navigation bar -->
      <ul id="navbar">
        
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="allProductsController">View products</a></li>
        <li><a href="SelectionPage.jsp">Logout</a></li>  
      </ul>
      

    <div id="example">
    <div id="listView"></div>

	
    <script type="text/x-kendo-template" id="template">
        <div class="product">

			<img src=#:Uri# alt="Sorry, we can not display the image"/>
            <h3>#:Name#</h3>
            <h4>#:kendo.toString(Price, "c")#</h4>  
			<h3>#:Stock# left</h3> 
			<button>BUY</button>
			<form action=DetailedProduct method="post" >
 				<input type="hidden" name=productId value=#:ProductId# />
				<button type="submit">View more</button>
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
    			"Uri": "http://via.placeholder.com/640x360"
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