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
String pw = request.getParameter("pw");
String email1 = request.getParameter("email1");
String email2 = request.getParameter("email2");
int post_num = Integer.parseInt(request.getParameter("post_num"));
String address = request.getParameter("address");
String address2 = request.getParameter("address2");
int phon1 = Integer.parseInt(request.getParameter("phon1"));
int phon2 = Integer.parseInt(request.getParameter("phon2"));
int phon3 = Integer.parseInt(request.getParameter("phon3"));
String message = request.getParameter("message");

MemberDao memDao = new MemberDao();

String email = email1+"@"+email2;
int phon = Integer.parseInt(Integer.toString(phon1) + Integer.toString(phon2) + Integer.toString(phon3));
int re = 0;
re = memDao.changeInpo(pw,email,post_num,address,address2,phon,message,id);

if(re == 1){
	%>
	<script type="text/javascript">
		location.href = "MyMemberInpo.jsp"
	</script>
	<%
}


%>
</body>
</html>