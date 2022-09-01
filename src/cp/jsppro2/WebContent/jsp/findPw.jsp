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
<link rel="stylesheet" type="text/css" href="../css/resultId.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

function moveLogin(){
	location.href = "login.jsp";
}

function checkInpo(){
	var pwPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{10,16}$/;
	var form = document.PwForm;
	
	if (pwPattern.test(form.pw.value) != true) {
		alert("영문, 숫자, 특수문자를 조합해서 입력하세요.");
		return false;
	}else{
		return true;
	}
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
		<form action="resultPw.jsp" method="post" class = "PwForm" name = "PwForm" onsubmit="return checkInpo()">
			<table>
				<tr>
					<td>새 비밀번호 : </td>
					<td><input type="password" name = "pw" id="">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)<input type="hidden" name = "id" value = "<%=id %>" ></td>
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