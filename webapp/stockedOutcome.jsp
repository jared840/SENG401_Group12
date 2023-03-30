<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stocked</title>
<link rel="icon" href="image/logo.jpg">
<link rel="stylesheet" type="text/css" href="navbar.css">
  <link rel="stylesheet" href="css/SelectionPage.css">

</head>
<body>

<jsp:include page = "background.jsp"/>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Home</a></li> 
        <li><a href="CreateProduct.jsp">Create New Product</a></li>
        <li><a href="viewSupplierProds">View products</a></li>
        <li><a href="SelectionPage.jsp">Logout</a></li>  
      </ul>
      
      <div class="login">
     <%
     
     out.println("<center><b>Outcome of your stock request</b></center>");
     if(request.getAttribute("stockSuccess").equals("true")){
    	 out.println("Stocked item successfully");
     }
     else{
    	 out.println("Unable to stock item");
     }
     
     %>
     </div>
</body>
</html>