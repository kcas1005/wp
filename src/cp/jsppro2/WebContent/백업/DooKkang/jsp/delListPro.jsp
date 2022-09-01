<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.hk.th.dao.*" %>
<%@ page import="com.hk.th.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id = (String)session.getAttribute("id");
	int proNum = 0;
	proNum = Integer.parseInt(request.getParameter("product"));
	CartDao mpDao = new CartDao();
	int re = 0;
	re = mpDao.delCart(id, proNum,2);

	if(re == 1){
		response.sendRedirect("MyLikeList.jsp");
	}
%>

</body> 
</html>