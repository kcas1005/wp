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
<h1>regReviewPro</h1>
<%
	String title = request.getParameter("title"),
			content = request.getParameter("content"),
			id = (String)session.getAttribute("id");
	int no = 0,
		category = Integer.valueOf(request.getParameter("category")),
		star = 0;
	if(request.getParameter("star") != null)
		star = Integer.valueOf(request.getParameter("star"));
	
	if(request.getParameter("no") != null)
		no = Integer.valueOf(request.getParameter("no"));
	
	int revise=0, article_no =0;
	
	if(request.getParameter("revise")!= null)
		revise = Integer.valueOf(request.getParameter("revise"));
	if(request.getParameter("article_no")!= null)
		article_no = Integer.valueOf(request.getParameter("article_no"));
	
	ArticleDao ad = new ArticleDao();
	if(revise == 0)
		ad.regArticle(category, id, title, content, no, star);
	else
		ad.reviseArticle(article_no, category, id, title, content, no, star);
	
%>
<script type="text/javascript">history.go(-2);</script>
	<%-- title : <%= title %><br>
	no : <%= no %><br>
	category : <%= category %><br>
	id : <%= id %><br>
	star : <%= star %><br>
	content: <%= content %><br> --%>
	
</body>
</html>