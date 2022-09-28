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
<script src="https://cdn.ckeditor.com/4.12.1/standard/ckeditor.js"></script>
</head>
<body>
<% session.setAttribute("id","1"); %>
<h2>아이디 : <%= session.getAttribute("id") %></h2>
<h2>보안코드 누가 했는지 : <%= session.getAttribute("admCode") %></h2>
<form method="get" action="manage_admin.jsp">
	관리자모드로
	<!-- <textarea name="editor1"></textarea> -->

	<input type="submit" value="관리자모드">
</form>
<form method="get" action="regReview.jsp">
	상품번호<input type="text" name="product_no">
	<br>
	<!-- <textarea name="editor1"></textarea> -->

	<input type="submit" value="리뷰">
</form>
<form method="get" action="regQuestion.jsp">
	상품번호<input type="text" name="product_no">
	<br>
	<!-- <textarea name="editor1"></textarea> -->

	<input type="submit" value="문의">
</form>
<form method="get" action="regNotice.jsp">
	
	<!-- <textarea name="editor1"></textarea> -->

	<input type="submit" value="공지사항">
</form>
<form method="get" action="regQnA.jsp">
	
	<!-- <textarea name="editor1"></textarea> -->

	<input type="submit" value="QnA">
</form>
<script>
       CKEDITOR.replace( 'editor1' );
</script>
</body>
</html>