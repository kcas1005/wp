<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.vo.*" %>
<%@ page import ="com.hk.th.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/resultId.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

function moveLogin(){
	location.href = "login.jsp";
}

</script>
<style type="text/css">
</style>
</head>
<body>
<%
String id = request.getParameter("id");
String name = request.getParameter("name");
int brith = Integer.parseInt(request.getParameter("brith"));
MemberDao memDao = new MemberDao(); 
int re = memDao.selectPw(id,name,brith);
if(re == 1){

}else{
	%>
	<script type="text/javascript">
		alert("입력 하신 정보를 다시 확인 해 주시기 바랍니다");
		location.href = "login.jsp"
	</script>
	<%
}

%>
<jsp:include page="header.jsp" flush="true"></jsp:include>

<div class = "resultPage">
	<h2 class = "resultPageName">아이디 찾기</h2>
	<div class = "resultPageMain">
		<form action="resultPw.jsp" method="get" class = "PwForm">
			<table>
				<tr>
					<td>새 비밀번호 : </td>
					<td><input type="text" name = "pw"><input type="hidden" name = "id" value = "<%=id %>" ></td>
				</tr>
			</table>
			<br>
			<input type="submit" value = "비밀번호 변경" class= "resultPageButton">
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>