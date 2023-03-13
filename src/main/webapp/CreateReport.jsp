<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Report</title>
<link rel="stylesheet" type="text/css" href="navbar.css">
 <link rel="stylesheet" type="text/css" href="products.css">
</head>
<body>
<!-- Navigation bar -->
      <ul id="navbar">
        <li><a href="SupplierHome.jsp">Cancel</a></li> 
        <li><a href="SelectionPage.jsp">Logout</a></li> 
        
      </ul>
      
<b>Fill out below fields to generate a Supplier Report:</b>
<br>
<form method=get action=createReportServlet>
 <label>Report Title: </label>
 <input type=text placeholder="Report Title" name=reportTitle required>
 <br>
 <label>Product name/Product #: </label>
 <input type=text placeholder="Report Product" name=reportProduct required>
 <br>
 <label>Report Transaction: </label>
 <select name="TransType">
<% 

ArrayList<String> trans=new ArrayList();
trans.add("Stocked Product");
trans.add("Created Product");
trans.add("Removed Product");
trans.add("Custom request");
trans.add("Invoice needed");


for(int i=0; i<trans.size();i++){
	out.println("<option>"+trans.get(i)+"</option>");
}

%>
</select>
<br><br><br>
<label>Details/Notes:</label>
<br><br>
<textarea id="notes" name=notes rows="8" cols="75">Enter details to include in report or miscellaneous notes</textarea>
 <br><br>
 
 
 <input type=submit value="Generate Supplier Report">
 
 </form>
</body>
</html>