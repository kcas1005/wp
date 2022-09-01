<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.vo.*" %>
<%@ page import ="com.hk.th.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");

MemberDao memDao = new MemberDao();
int re = memDao.changePW(pw,id);

if(re == 1){
	%>
	<script type="text/javascript">
	alert("비밀번호가 변경 되었습니다.");
	location.href = "login.jsp"
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert("비밀번호가 변경에 실패했습니다. 다시시도 해 주세요.");
	location.href = "login.jsp"
	</script>
	<%
}


%>
</body>
</html>