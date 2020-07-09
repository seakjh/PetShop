<%@page import="com.pet.model.admin.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Admin admin = (Admin)session.getAttribute("Admin");
%>
<div class="topnav">
  <a class="active" href="/admin/index.jsp">Home</a>
  <a href="/admin/category/index.jsp">카테고리 관리</a>
  <a href="/admin/product/index.jsp">상품관리</a>
  <a href="/admin/member/index.jsp">회원정보</a>
  <a href="/admin/order/index.jsp">주문정보</a>
  <a href="/admin/cs/index.jsp">고객센터</a>
  <a><%=admin.getName() %>님 로그인중</a>
</div>