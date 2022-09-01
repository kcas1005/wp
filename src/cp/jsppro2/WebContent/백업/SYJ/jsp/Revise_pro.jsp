<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.Vo.*" %>
<%@ page import ="com.hk.th.Dao.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style='background:#fff;'>
<%
	ArticleDao review = new ArticleDao();
String title = request.getParameter("title");
String content = request.getParameter("content");
String article_no = request.getParameter("article_no");
String product_no = request.getParameter("product_no");
int a = review.Revise(title, content, article_no); 
 
String link = "content1.jsp?reviewpage=1&product_no="+product_no+"&reviewcategory=2";
if(a==1){
%>
	<h2 class='revise_txt' style='width:70%; margin:40% auto;font-style:normal; text-align: center; color:#feff97;background:#ff6e75;'>수정되셨습니다.</h2>
	<% 
}else{
	%>
	<h2  class='revise_txt' style='width:70%; margin:40% auto;font-style:normal; text-align: center; color:#feff97;background:#ff6e75;'>다시 시도해주세요</h2>
	
	<% 
}
/* out.print(title);
out.print(content);
out.print(article_no); */
%>

</body>
</html>