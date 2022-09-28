<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String sid = (String)session.getAttribute("id");

String id = request.getParameter("id");
MemberDao memDao = new MemberDao();
int re = memDao.delMember(id);


if(re==1){
	if(memDao.isAdmin(sid) != true){
		session.invalidate();
	%>
	<script type="text/javascript">
	alert("정상적으로 탈퇴 되었습니다");
	location.href="manage_members.jsp";
	</script>
	<%
	}else{
		%>
		<script type="text/javascript">
		alert("정상적으로 탈퇴 되었습니다");
		location.href="index.jsp";
		</script>
		<%
	}
}
%>

</body>
</html>