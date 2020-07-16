<%@page import="com.pet.domain.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Member member = (Member) session.getAttribute("member");
%>
<div id="header">
	<a href="/" id="logo"><img src="/images/logo.gif" width="250"
		height="114" alt=""></a>
	<ul class="navigation">
		<li><a href="/product/list">쇼핑상품</a></li>
		<li><a href="/event/list">찜한상품</a></li>
		<%if(member == null){ %>
		<li><a href="javascript:alert('로그인하세요');">장바구니</a></li>
		<%}else { %>
		<li><a href="/shop/cart/list">장바구니</a></li>
		<%} %>

		<li><a href="/myPage/list">MyPage</a></li>
		<%if(member == null){ %>
			<li><a href="/member/login.jsp">로그인</a></li>
		<%}else { %>
			<li><a href="/member/logout">로그아웃</a></li>
		<%} %>
		<li><a href="/cs/main">1:1상담</a></li>
	</ul>
</div>