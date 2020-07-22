<%@page import="com.pet.domain.Emp"%>
<%@page import="com.pet.domain.Dept"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Dept> deptList = (List)request.getAttribute("deptList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	margin: 0;
}
table, td {
	border-collapse: collapse;
}
ul li {
	list-style-type: none;
}
</style>
</head>
<body>

<table width="100%" border="1">

	<tr>
		<td>부서번호</td>
		<td>부서명</td>
		<td>위치</td>
		<td>사원정보</td>
	</tr>
	<%//jdk5에서 추가된 개선된 for문 (improved for loop) %>
	<%for (Dept dept : deptList) { %>
	<tr>
		<td><%=dept.getDeptno() %></td>
		<td><%=dept.getDname() %></td>
		<td><%=dept.getLoc() %></td>
		<td>
			<%List<Emp> empList=dept.getEmpList(); %>
			<%for (Emp emp : empList) { %>
			<ul>
				<li><%=emp.getEmpno() %></li>
				<li><%=emp.getEname() %></li>
				<li><%=emp.getJob() %></li>
				<li><%=emp.getSal() %></li>
				<li><%=emp.getHiredate() %></li>
				<li><%=emp.getMgr() %></li>
				<li><%=emp.getComm() %></li>
			</ul>
			<%} %>
		</td>
	</tr>
	<%} %>
</table>

</body>
</html>