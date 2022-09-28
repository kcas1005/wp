<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int category = Integer.parseInt(request.getParameter("cate"));
String id = (String)session.getAttribute("id");
CartDao cDao = new CartDao();
if(request.getParameterValues("pcheck") == null){
	%>
	<script type="text/javascript">
		alert("삭제할 물건을 골라 주세요");
		location.href = "MyCart.jsp";
	</script>	
	<%
}else{
	String[] a = request.getParameterValues("pcheck");

	for(int i  = 0 ; i < a.length ; i++){
		cDao.check(id,Integer.parseInt(a[i]),category);
		cDao.delseleCart(id,Integer.parseInt(a[i]),category);
	}
	response.sendRedirect("MyCart.jsp");
}
%>
</body>
</html>