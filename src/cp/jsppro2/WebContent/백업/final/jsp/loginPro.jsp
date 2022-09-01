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
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<%
MemberDao memDao = new MemberDao();
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String secure = null;
if(request.getParameter("secure")!=null){
	secure = request.getParameter("secure");
	if(secure.equals("on")){
		int lid = Secure.lengthStr(id),
			lpw = Secure.lengthStr(pw);
		String nid = Secure.changeStr(id),
				npw = Secure.changeStr(pw);
		if(memDao.slogin(nid,npw, lid, lpw)){
			session.setAttribute("id",id);
			response.sendRedirect("main.jsp");
		}
		else{
		%>
		<script type="text/javascript">
			alert("아이디와 비밀번호가 일치하지 않습니다.");
			location.href = "login.jsp";
		</script>
		<%
		}
	}
}
else{
	int re = 0;
	re = memDao.login(id,pw);
	
	if(re == 1){
		session.setAttribute("id",id);
		 
		%>
	<script type="text/javascript">
		location.href = "index.jsp";
		</script> 
		<%
	 }else{
		%>
		<script type="text/javascript">
		alert("아이디와 비밀번호가 일치하지 않습니다.");
		location.href = "login.jsp";
		</script>
		<%
	 } 
}
%>

</body>
</html>