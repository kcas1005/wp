<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-sacle=1"charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/iframe.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<title>Insert title here</title>
</head>
<body style='background:#fff;'>

<%
	String img_root = "../image/"; //썸네일 이미지 루트

ArticleDao review = new ArticleDao();
ArticleVo article = new ArticleVo();
BtnDao btn = new BtnDao();
BtnVo btn_vo = new BtnVo();
MemberVo memvo = new MemberVo();
MemberDao mem = new MemberDao();

String article_no01 = request.getParameter("article_no");
int article_no =  Integer.parseInt(request.getParameter("article_no"));
/* artcle 게시글 정보 출력 */
article = review.select_list(article_no); 
int r_count = article.getRead_cnt();
String title = article.getTitle(); 
String id = article.getId();
String date = article.getRegdate();
String moddte = article.getModdate();
String contnet = article.getContent();
int star = article.getStar();
String memid = (String)session.getAttribute("id");

/* r_count +=1;
review.r_count(r_count, article_no);  */


/* 아이디로 값 출력 */

int cnt = btn.btn_count(memid, article_no01); 


if(cnt==0){
	   btn.btn_set(memid, article_no01);
	   r_count+=1;
	   review.r_count(r_count, article_no);
	   out.println("<b style='color:red;line-height:40px;'>♥</b>"+r_count);
	   
	}if(cnt==1){
	   int a = btn.btn_delete(memid, article_no01);
	   r_count-=1;
	   review.r_count(r_count, article_no);
	   out.println("<b style='color:red;line-height:40px;'>♡</b>"+r_count);
	}
%>
<script>
</script>


</body>
</html>
