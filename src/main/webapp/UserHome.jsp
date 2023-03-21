<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entities.*" %>
<!DOCTYPE html>
<html>
  <head>
    <title>My Website</title>
    <link rel="stylesheet" type="text/css" href="navbar.css">
    <link rel="stylesheet" type="text/css" href="products.css">
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2023.1.117/styles/kendo.default-v2.min.css"/>
      <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="https://kendo.cdn.telerik.com/2023.1.117/js/kendo.all.min.js"></script>
    <style>
      /* CSS styles for the navigation bar */
      ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: black;
      }
      li {
        float: left;
      }
      li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
      }
      li a:hover {
        background-color: #111;
      }
      .right {
        float: right;
      }
      .button {
        background-color: #333;
        color: white;
        border-radius: 20px;
        border: none;
        padding: 10px 20px;
        margin-left: 10px;
        font-size: 20px;
        cursor: pointer;
      }
      .button:hover {
        background-color: #111;
      }
      /* CSS styles for the home page */
      #search-bar {
        margin-top: 150px;
        text-align: center;
      }
      #search-box {
        padding: 10px;
        border-radius: 20px;
        border: none;
        width: 500px;
        font-size: 20px;
        outline: none;
      }
      #search-btn {
        background-color: #333;
        color: white;
        border-radius: 20px;
        border: none;
        padding: 10px 20px;
        margin-left: 10px;
        font-size: 20px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <jsp:include page = "background.jsp"/>

    <% 
    User in=(User)request.getSession().getAttribute("currentUser");
    out.println("Your name is: "+in.getName());
    %>
    <!-- Navigation bar -->
    <ul>
      <li><a href="#home">Home</a></li>
      <li><a href="#about">Search</a></li>
      <li><a href="#services">My Orders</a></li>
      <li><a href="#cart">View Cart</a></li>
      <li><a href="#orders">View Orders</a></li>
      <li><a href="#inventory">Inventory</a></li>
      <li><a href="#report">Create Report</a></li>
      <li><a href="#article">Articles</a></li>
    </ul>
    
    <!-- Page content -->
    <div id="search-bar">
      <h1>Welcome to our Warehouse Website</h1>
      <form>
        <input type="text" id="search-box" placeholder="Search for products...">
        <button type="submit" id="search-btn">Search</button>
      </form>
    </div>
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

