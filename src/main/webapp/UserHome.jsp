<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
 <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2023.1.117/styles/kendo.default-v2.min.css"/>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2023.1.117/js/kendo.all.min.js"></script>
</head>
<body>

<% 

User in=(User)request.getSession().getAttribute("currentUser");
out.println("Your name is: "+in.getName());

%>

 <!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="/">Home</a></li> <!-- Since index page all options are in nav bar but different for each users login page-->
        <li><a href="#about">Search</a></li>
        <li><a href="#services">My Orders</a></li> <!-- get different permissions depending on user type for all the rest of these-->
        <li><a href="#cart">View Cart</a></li>
        <li><a href="#orders">View Orders</a></li>
        <li><a href="inventory">Inventory</a></li>
        <li><a href="#report">Create Report</a></li>
        <li><a href="#article">Articles</a></li>
      </ul>

      <h1>Warehouse Website Placeholder</h1>
      <p>In future it will show popular products or recommended products</p>
    <div id="example">
    <div id="listView"></div>
    
    <script type="text/x-kendo-template" id="template">
        <div class="product">
            <img src="#:Src#" alt="Kendo UI for jQuery ListView #: ProductName #" />
            <h3>#:ProductName#</h3>
            <h5>#:kendo.toString(UnitPrice, "c")#</h5>
            <p>#:kendo.toString(UnitPrice, "c")#</p>        
        </div>
        
    </script>
    <script>
        let product = []
        for(let i = 0; i < 100; i++) {
            product.push({Src:`https://picsum.photos/id/${i}/200/300`,UnitPrice: i*9, ProductName:" - a name", ProductID : i })
        }
      
        $(function () {
            $("#listView").kendoListView({
                dataSource: {
                    data: product,
                    pageSize: 30
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
