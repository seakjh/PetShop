<%@page import="com.pet.controller.common.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.pet.domain.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Product> productList=(List)request.getAttribute("productList");
	Pager pager = (Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<title>Pet Shop</title>
<meta charset="utf-8">
<%@ include file="/include/head.jsp" %>
<style>
.pic{
	width:50%;
	height:250px;
	background:yellow;
	float:left;
	margin-top:50px;
}
.spec{
	width:50%;
	height:250px;
	background:green;
	float:left;
	margin-top:50px;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
  margin-top:50px;
}
th, td {
  text-align: left;
  padding: 16px;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>

<script type="text/javascript">
$(function(){

});

function edit(product_id, id) {
	var n = id.split("_")[1];
	var ea = document.getElementById("t_"+n).value;
	if(confirm(product_id+"상품의 갯수를 "+ea+"개로 수정할거?")) {
		location.href="/shop/cart/edit?product_id="+product_id+"&ea="+ea;
	}
	
}

function del(product_id) {
	if (confirm("상품코드 "+product_id+" 를 삭제 할거임?")) {
		location.href="/shop/cart/del?product_id="+product_id;
	}
}

function removeAll() {
	if (confirm("장바구니 비울거임?")) {
		location.href="/shop/cart/remove";
	}
}
function buy() {
	//결제 1단계 페이지 요청
	location.href="/shop/step1";
}
</script>
</head>
<body>
<div id="header"> 
	<%@ include file="/include/header.jsp" %>
</div>
<div id="body">
  <div id="content">
		<!-- 장바구니 표 -->
		<h2>상품목록</h2>
		<table>
  <tr>
    <th>No</th>
    <th>이미지</th>
    <th>카테고리</th>
    <th>상품명</th>
    <th>가격</th>
    <th>브랜드</th>
  </tr>
  
 <%int curPos=pager.getCurPos(); %>	
 <%int num=pager.getNum(); %>	
  <%for(int i=1;i<pager.getPageSize();i++){%>
 <%if(num<1)break;%>	
  <%Product product=productList.get(curPos++); %>
  <tr onClick="getDetail(<%=product.getProduct_id()%>)">
    <td><%=num-- %></td>
    <td><img src="/data/<%=product.getFilename()%>" width="35px"/></td>
    <td><%=product.getCategory().getCategory_name()%></td>
    <td><%=product.getProduct_name() %></td>
    <td><%=product.getPrice() %></td>
    <td><%=product.getBrand() %></td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="6" align="center">
  		<button onClick="location.href='/admin/product/registForm.jsp';">상품등록</button>
  	</td>
  </tr>
  <tr>
  	<td colspan="6" style="text-align:center">
  	
  		<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){%>
  		<%if(i>pager.getTotalPage())break; %>
		<a href="/product/list?currentPage=<%=i%>">[<%=i%>]</a>  		
		<%}%>
		
  	</td>
  </tr>
  
</table>
	  
  </div>
  <div class="featured">
    <ul>
      <li><a href="#"><img src="/images/organic-and-chemical-free.jpg" width="300" height="90" alt=""></a></li>
      <li><a href="#"><img src="/images/good-food.jpg" width="300" height="90" alt=""></a></li>
      <li class="last"><a href="#"><img src="/images/pet-grooming.jpg" width="300" height="90" alt=""></a></li>
    </ul>
  </div>
</div>
<div id="footer">
  <%@ include file="/include/footer.jsp" %>
</div>
</body>
</html>