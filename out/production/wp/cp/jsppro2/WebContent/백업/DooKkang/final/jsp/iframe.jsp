<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-sacle=1"charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<!-- 아이프레임 -->
<div id='iframe' style='display:none;' >
	<div class='iframe_bg'></div>
	<iframe name = 'iframe' class='iframe' src='revise.jsp' ></iframe>
	<button class='close'>닫기</button>
	
</div>
<script>
$(document).ready(function(){
	$(".iframe_show").click(function(){
		$("#iframe").fadeIn();
	})
	$("#iframe button.close").click(function(){
		$("#iframe").fadeOut();
		location.reload();
	})
})

</script>
</body>
</html>