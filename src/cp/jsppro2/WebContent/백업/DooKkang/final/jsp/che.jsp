<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
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
String id = (String)session.getAttribute("id");
CartDao cDao = new CartDao();
if(request.getParameterValues("pcheck") == null){
	%>
	<script type="text/javascript">
		alert("물건을 골라 주세요");
		location.href = "MyCart.jsp";
	</script>	
	<%
}else{
	String[] a = request.getParameterValues("pcheck");

	for(int i  = 0 ; i < a.length ; i++){
		cDao.check(id,Integer.parseInt(a[i]));
	}
	response.sendRedirect("purchase.jsp");
}


%>


</body>
</html>