<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- <% request.setCharacterEncoding("UTF-8"); %> <!-- post방식. 한글깨짐 방지 --> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">

</style>
</head>
<body>
<h1>PTest JSP</h1>
<hr>
<form action="purchase.jsp">
	<h2>1로 세션아이디 줌</h2>
	<input type="submit" value="구매 창으로 이동">
</form>
<hr>
<%
	session.setAttribute("id","1");
	if(request.getParameter("info")!= null && 
		request.getParameter("name")!=null && request.getParameter("post_num")!=null &&
		request.getParameter("address")!=null && request.getParameter("address2")!=null &&
		request.getParameter("phone1")!= null && request.getParameter("phone2")!=null &&
		request.getParameter("phone3")!=null && request.getParameter("email1")!=null &&
		request.getParameter("email1")!=null && request.getParameter("email2")!=null&&
		request.getParameter("message")!=null){
		
		String info = request.getParameter("info");
		String name = request.getParameter("name");
		String post_num = request.getParameter("post_num");
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String message = request.getParameter("message");
		
		out.print("info : "+info);
		out.print("<br><br>name : "+name);
		out.print("<br><br>post_num : "+post_num);
		out.print("<br>address : "+address);
		out.print("<br>address2 : "+address2);
		out.print("<br><br>phone1 : "+phone1);
		out.print("<br>phone2 : "+phone2);
		out.print("<br>phone3 : "+phone3);
		out.print("<br><br>email1 : "+email1);
		out.print("<br>email2 : "+email2);
		out.print("<br><br>message : "+message);
	}else{
		out.print("값을 전달받지 못했습니다.");
	}
		
%>
<h2>멤버 보기</h2>
<a href="manage_admin.jsp"><button>멤버 보러가기</button></a>

</body>
</html>