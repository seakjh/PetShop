<%@page import="com.pet.model.admin.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>
<link rel="stylesheet" href="/css/admin.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
select {
	width: 200px;
	height: 150px;
}
</style>
<script type="text/javascript">
$(function() {
	$($("button")[0]).click(function() { //등록
		regist();
	});
	$($("button")[1]).click(function() { // 수정
		edit();
	});
	$($("button")[2]).click(function() {// 삭제
		del();
	});
});
//jquery가 ajax도 지원한다!
//기존의 순수자바스크립트 이용하는것보다 코드량이 현저히 줄어든다
function regist() {
	$.ajax({
		"url":"/category/regist",
		"type":"post",
		"data":{
			category_name:$("input[name='category_name']").val()
		},
		success:function(data) {
			alert("서버에서 온 응답 메세지는 "+data);
		}
	});
}
</script>
</head>
<body bgcolor="#ffc">
<%@include file="/admin/inc/main_navi.jsp" %>
<div>
	<input type="text" name="category_name" placeholder="카테고리 입력">
	<button>등록</button>
	<p>
		<select multiple="multiple"></select>
	</p>
	<button>수정</button>
	<button>삭제</button>
</div>
</body>
</html>