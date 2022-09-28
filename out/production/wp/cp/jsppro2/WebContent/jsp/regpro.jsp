<%@page import="sun.util.locale.ParseStatus"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 1. 넘어오는 값 한글 인코딩 처리한다. 2. 사용할 객체 useBean한다. 3. setXxx를 호출하여 넘어오는 값들
	저장한다. 4. dao쪽의 insert하는 메소드 호출하여 성공 여부를 리턴한후 성공하면 memberSelect.jsp 이동
	실패하면 뒤로 이동시킨다. -->

	<%
		request.setCharacterEncoding("UTF-8");
		// 			핸드폰 번호를 한 곳으로 묶기 위해 핸드폰 값을 받아옴
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phot = phone1 + phone2 + phone3;

		// 			핸드폰번호를  int phone 만듦
		int phone = Integer.parseInt(phot);

		// 			이메일을 한 곳으로 묶기 위해 이메일 값을 받아옴
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");

		// 			이메일을 String 이메일로 만듦
		String email = email1 + "@" + email2;
	%>
	<!-- 	자바빈으로 MemberVo에 입력했던 getter setter을 불러옴 -->
	<jsp:useBean id="vo" class="vo.MemberVo">
		<jsp:setProperty name="vo" property="*" />
	</jsp:useBean>

	<%
		vo.setPhone(phone);
		vo.setEmail(email);
		MemberDao mdao = new MemberDao();
		int rst = mdao.memberInsert(vo);

		if (rst == 1) {
			out.print("회원가입 성공"+"<br/>");
		} else {
			out.print("회원가입 실패"+"<br/>");
		}
	%>
	
	<script> 
	alert("메인페이지로 이동 하겠습니다.")
	location.href="index.jsp";
	</script>
</body>
</html>