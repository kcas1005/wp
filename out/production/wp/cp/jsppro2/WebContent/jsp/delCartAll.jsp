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
	String id = (String)session.getAttribute("id");
	CartDao mpDao = new CartDao();
	int re = 0;
	re = mpDao.delCartAll(id,1);

	if(re != 0){
		%>
		<script type="text/javascript">
			alert("삭제 되었습니다"); 
			location.href="MyCart.jsp";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("삭제 할 상품이 없습니다");
			location.href="MyCart.jsp";
		</script>
		<%
	}
%>

</body>
</html>