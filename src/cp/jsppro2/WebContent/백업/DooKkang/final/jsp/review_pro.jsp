<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ArticleDao review = new ArticleDao();
String product_no = request.getParameter("product_no");
String review_category = request.getParameter("review_category");
String title = request.getParameter("title");
String content = request.getParameter("content");
String id = request.getParameter("id");
String session_id = (String)session.getAttribute("id");
String star = request.getParameter("star");
review.review_upload(review_category, title, content, session_id, star, product_no);
/*  out.print(review_category);
out.print(title); 
out.print(content);
out.print(session_id);  */
%>
<script>
location.href = document.referrer;
</script>
</body>
</html>