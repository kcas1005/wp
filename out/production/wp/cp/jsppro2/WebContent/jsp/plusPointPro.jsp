<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
h1{
	text-align: center;		
}
</style>
</head>
<body>

<%
	String id = (String)session.getAttribute("id");
	MemberDao memDao = new MemberDao();
	int re = 0;
	int point = 0;
	point = memDao.selectPoint(id);
	point += 500000;
	re = memDao.changePoint(point,id);
	
	if(re == 1){
		out.print("<h1>포인트가 추가 되었습니다</h1>");
	}
%>

</body>
</html>