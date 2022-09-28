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
<link rel="stylesheet" type="text/css" href="../css/login.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.loginTable tr td{
	text-align: left;
}
.loginTable tr:last-child td{
	text-align: center;
}
.trinp{
	text-align: right;
}
</style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"></jsp:include>

<div class = "login">
	<div class = "loginMain">
		<form action="loginPro.jsp" method="get" class = "loginForm">
		<img src="../image/logo.png" class = "logoImg">
			<table class = "loginTable">
				<tr>
					<td>아이디</td>
					<td><input type="text" placeholder="ID" name="id" class = "loginInput"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" placeholder="PASSWORD" name="pw" class = "loginInput"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value = "로그인" class = "loginButton"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><p class = "trinp"><input type="checkbox" name="secure" class ="sCheck">보안 접속</p></td>
				</tr>
				<tr>
					<td></td>
					<td class = "lasttd"><a href="findInpo.jsp">아이디 찾기</a> | <a href="findInpo.jsp">비밀번호 찾기</a> | <a href="#">회원가입</a></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>