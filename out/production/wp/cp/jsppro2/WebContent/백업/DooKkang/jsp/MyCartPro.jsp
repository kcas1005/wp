<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import = "com.hk.th.dao.*" %>
<%@page import = "com.hk.th.vo.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<%
int proNum = 0;
int category = 0;
String id = (String)session.getAttribute("id");
proNum = Integer.parseInt(request.getParameter("proNum"));
category = Integer.parseInt(request.getParameter("categoty"));

CartDao cDao = new CartDao();
int re = 0;
re = cDao.CheckProduct(id, proNum, category);

if(re == 0 ){
	cDao.setProduct(id, proNum, category);
}else{
	%>
	<script type="text/javascript">
	alert("이미 있습니다");
	window.history.back();
	</script>
	<%
}

switch(category){

case 1:
	%>
	<script type="text/javascript">
	location.href = "MyCart.jsp";
	</script>
	<%
	break;
case 2:
	%>
	<script type="text/javascript">
	location.href = "MyLikeList.jsp";
	</script>
	<%
	break;
case 3:
	%>
	<script type="text/javascript">
	location.href = "purchase.jsp";
	</script>
	<%
	break;
}

%>



</body>
</html>