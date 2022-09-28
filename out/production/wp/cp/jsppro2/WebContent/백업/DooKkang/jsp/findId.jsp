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
<link rel="stylesheet" type="text/css" href="../css/resultId.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

function moveLogin(){
	location.href = "login.jsp"
}

</script>
<style type="text/css">
</style>
</head>
<body>
<%
String name = request.getParameter("name");
int brith = Integer.parseInt(request.getParameter("brith"));
MemberDao memDao = new MemberDao(); 
String id = memDao.selectId(name,brith);
String text = "";
if(id != ""){
	text = "회원님의 아이디는 '"+ id +"' 입니다.";
}else{
	text = "회원님의 아이디는 찾을 수 없습니다.";
}

%>
<jsp:include page="header.jsp" flush="true"></jsp:include>

<div class = "resultPage">
	<h2 class = "resultPageName">아이디 찾기</h2>
	<div class = "resultPageMain">
		<p><%=text %></p>
		<br>
		<input type="button" value = "로그인" class= "resultPageButton" onclick="moveLogin()">
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>