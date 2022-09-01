<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 중복확인</title>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = request.getParameter("id");
	MemberDao memdao = new MemberDao();
	//check 변수 선언 -> DAO의 confirmid함수에서 얻은 결과를 가져옴
	int check = memdao.confirmId(id);

	//confirmid함수에서 1을 반환하면 해당아이디가 있는 것이라 다른 아이디를 선택하도록 함
	if (check == 1) {
%>
<b><font color="red"><%=id%></font>는 이미 사용중인 아이디입니다.</b>
<form name="checkForm" method="post" action="confirmId.jsp">
	<b>다른 아이디를 선택하세요.</b><br /> <br /> <input type="text" name="id" /> <input
		type="submit" value="ID중복확인" />
</form>
<%
	//아이디가 없을때, 해당 아이디를 사용하도록 함
	} else {
%><center>
	<b>입력하신<font color="red"><%=id%></font>는<br /> 사용하실 수 있는 ID입니다.
	</b><br /> <br /> <input type="button" value="닫기" onclick="setid()">
</center>
<%}%>

<script language="javascript">
	function CloseWindow()
	{
		self.opener = self;
		window.close();
	}
	
	function setid()
	//자바스크립트의 opener메소드 
	//팝업창을 띄우고 나서 다시 부모 창으로 결과 값을 돌려줄때 사용
	{
		var form = document.joinForm;
		//reg.jsp에 id 폼에 id값을 입력
		opener.document.joinForm.id.value="<%=id%>";
		//reg.jsp에 sameId 폼에 1값을 입력
		opener.document.joinForm.sameId.value=1;
 		
		self.close();
		/* window.open('', '_self', '');
		window.close(); */
	}
</script>
</head>
<body>
</body>
</html>