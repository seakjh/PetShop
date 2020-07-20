 <%@page import="com.pet.domain.EventProduct"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8"%>
 <%
 	Map<String, String> titleMap = (Map)request.getAttribute("titleMap");
 	Map<String, List> listMap = (Map)request.getAttribute("listMap");
 	
 	Iterator titleKey = titleMap.keySet().iterator(); 
 	Iterator listKey = listMap.keySet().iterator(); 
 %>
 <!DOCTYPE html>
<html>
<head>
<title>Pet Shop | PetMart</title>
<meta charset="UTF-8">
<%@include file="/include/head.jsp" %>
<!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
</head>
<body>
<%@include file="/include/header.jsp" %>

<div id="body">

<div id="content">
  
    <div class="search">
      <input type="text" name="s" value="Find">
      <button>&nbsp;</button>
      <label for="articles">
        <input type="radio" id="articles">
        Articles</label>
      <label for="products">
        <input type="radio" id="products" checked>
        PetMart Products</label>
    </div>

	<div class="content">
		<ul>
			<%while(titleKey.hasNext()) {%>
			<%String key = (String)titleKey.next(); %>
			<%String title = titleMap.get(key); %>
			
			<%
				String key2 = (String)listKey.next();
				List<EventProduct> list = listMap.get(key2);
			%>
			<li>
				<a href="#">
					<img src="/images/koi2.jpg" width="140" height="250" alt="">
				</a>
				<h2><%=title %></h2>
				<%for (int i=0; i<list.size(); i++) { %>
				<%EventProduct ep = list.get(i); %>
				<span>
					<a href="/shop/detail?product_id=<%=ep.getProduct().getProduct_id()%>">
						<%=ep.getProduct().getProduct_name() %>
					</a>
				</span>
				<%} %>
				<span>
					<a class="more" href="#">View all</a>
				</span>
			</li>
			<%} %>
		</ul>
	</div>

<div id="sidebar"> <a href="#"><img src="/images/discount.jpg" width="300" height="790" alt=""></a> </div>

</div>
  
  <div class="featured">
    <ul>
      <li><a href="#"><img src="/images/organic-and-chemical-free.jpg" width="300" height="90" alt=""></a></li>
      <li><a href="#"><img src="/images/good-food.jpg" width="300" height="90" alt=""></a></li>
      <li class="last"><a href="#"><img src="/images/pet-grooming.jpg" width="300" height="90" alt=""></a></li>
    </ul>
  </div>
</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>
