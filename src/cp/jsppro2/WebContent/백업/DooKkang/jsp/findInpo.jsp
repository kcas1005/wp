<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.vo.*" %>
<%@ page import ="com.hk.th.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/findInpo.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.findInpoTable tr td{
	text-align: left;
}
.findInpoTable tr:last-child td{
	text-align: center;
}
</style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"></jsp:include>
 
<div class = "findInpo">
	<div class = "findInpoMain">
		<form action="findId.jsp" method="get" class = "findInpoForm">
		<h2 class = "pageName">아이디 찾기</h2>
			<table class = "findInpoTable">
				<tr>
					<td>이름 : </td>
					<td><input type="text" placeholder="NAME" name="name"></td>
				</tr>
				<tr>
					<td>생년월일 : </td>
					<td><input type="text" placeholder="BRITH" name="brith"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value = "아이디 찾기" class = "findInpoButton"></td>
				</tr>
			</table>
		</form>
		<form action="findPw.jsp" method="get" class = "findInpoForm">
		<h2 class = "pageName">비밀번호 찾기</h2>
			<table class = "findInpoTable">
				<tr>
					<td>아이디 : </td>
					<td><input type="text" placeholder="ID" name="id"></td>
				</tr>
				<tr>
					<td>이름 : </td>
					<td><input type="text" placeholder="NAME" name="name"></td>
				</tr>
				<tr>
					<td>생년월일 : </td>
					<td><input type="text" placeholder="BRITH" name="brith"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value = "비밀번호 찾기" class = "findInpoButton"></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>