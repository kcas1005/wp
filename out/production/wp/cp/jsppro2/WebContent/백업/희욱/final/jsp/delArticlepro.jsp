<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.ArticleDao" %>
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
<%
	String id = (String)session.getAttribute("id");
	int category = Integer.valueOf(request.getParameter("category")),
		no = Integer.valueOf(request.getParameter("article_no"));
	ArticleDao ad = new ArticleDao();
	if(id.equals(ad.selectIdByNo(category, no)))//아이디가 맞으면 실행
		ad.delArticle(category,no);
%>
<script type="text/javascript">history.go(-1);</script>
</body>
</html>