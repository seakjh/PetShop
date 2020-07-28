    <%@page import="com.pet.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Product> productList = (List)request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop</title>
<meta charset="utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
<style type="text/css">
#realTime{
	background: green;
	width: 300px;
	height: 300px;
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="header">
		
		<%@include file="/include/header.jsp"%>
	</div>
	<div id="body">
		<div class="banner">&nbsp;</div>
		<div id="content">
			<div class="content">
				<ul>
					<%for (int i=0; i<productList.size(); i++) { %>
					<%Product product = (Product)productList.get(i); %>
					<li>
						<a href="/shop/detail?product_id=<%=product.getProduct_id()%>"><img src="/data/<%=product.getFilename() %>" width="114"	height="160" alt=""></a>
						<h2><%=product.getProduct_name() %></h2>
						<p><%=product.getBrand() %></p>
					</li>
					<%} %>
				</ul>
			</div>
			
			<div id="sidebar">
				<div id="realTime">
					<iframe src="http://127.0.0.1:3000/basic/client.html" width="300px" height="300px" frameborder="0"></iframe>
				</div>
			</div>
			
		</div>
		<div class="featured">
			<ul>
				<li><a href="#"><img
						src="images/organic-and-chemical-free.jpg" width="300" height="90"
						alt=""></a></li>
				<li><a href="#"><img src="images/good-food.jpg" width="300"
						height="90" alt=""></a></li>
				<li class="last"><a href="#"><img
						src="images/pet-grooming.jpg" width="300" height="90" alt=""></a></li>
			</ul>
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>