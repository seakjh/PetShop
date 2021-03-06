<%@page import="com.pet.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Product product = (Product)request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop</title>
<meta charset="utf-8">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
<style type="text/css">
.pic {
	width: 50%;
	height: 250px;
	background: #ffc;
	float: left;
	margin-top: 50px;	
}
.spec {
	width: 50%;
	height: 250px;
	background: #fcf;	
	float: left;
	margin-top: 50px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//장바구니 담기
	$($("button")[0]).click(function() {
		goCart();
	});
	
/* 	//바로구매
	$($("button")[1]).click(function() {
		
	}); */
});

function goCart() {
	$("form").attr({
		"action":"/shop/cart/regist",
		"method":"post"
	});
	$("form").submit();
}

function buy() {
	$("form").attr({
		"action":"/shop/buy",
		"method":"post"
	});
	$("form").submit();	
}
</script>
</head>
<body>
	<div id="header">
		<%@include file="/include/header.jsp"%>
	</div>
	<div id="body">
		<div id="content">
			<!-- 상세보기 -->
			<div class="pic">
				<img src="/data/<%=product.getFilename()%>" width="50%">
			</div>
			<div class="spec">
				<form action="">
					<input type="hidden" name="product_id" value="<%=product.getProduct_id()%>">
					<input type="hidden" name="category.category_id" value="<%=product.getCategory().getCategory_id()%>">
					<input type="hidden" name="category.category_name" value="<%=product.getCategory().getCategory_name()%>">
					<input type="hidden" name="product_name" value="<%=product.getProduct_name()%>">
					<input type="hidden" name="price" value="<%=product.getPrice()%>">
					<input type="hidden" name="brand" value="<%=product.getBrand()%>">
					<input type="hidden" name="filename" value="<%=product.getFilename()%>">		
					<ul>
						<li>카테고리 : <%=product.getCategory().getCategory_name() %></li>
						<li>상품명 : <%=product.getProduct_name() %></li>
						<li>가격 : <%=product.getPrice() %></li>
						<li>브랜드 : <%=product.getBrand() %></li>
					</ul>
				</form>
				<button>장바구니</button>
				<button onclick="buy()">바로구매</button>
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
	<div id="footer">
		<%@include file="/include/footer.jsp"%>
	</div>
</body>
</html>