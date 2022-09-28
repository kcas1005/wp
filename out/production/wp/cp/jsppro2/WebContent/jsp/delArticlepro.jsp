<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.ArticleDao" %>
<%@ page import="dao.MemberDao" %>
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
	if(category != 3){
		if(id.equals(ad.selectIdByNo(category, no))){//아이디가 맞으면 실행
			ad.delArticle(category,no);
		%>
		<script type="text/javascript">alert("삭제가 성공적으로 진행되었습니다.")</script>
		<%
		}
		else{
			%>
			<script type="text/javascript">alert("본인이 작성한 글이 아닙니다.")</script>
			<%		
		}
	}
	else{
		MemberDao md = new MemberDao();
		if(md.isAdmin(id)){//관리자이면
			ad.delArticle(category,no);
		%>
		<script type="text/javascript">alert("삭제가 성공적으로 진행되었습니다.")</script>
		<%
		}
		else{
			%>
			<script type="text/javascript">alert("공지사항은 관리자만 삭제할 수 있습니다.")</script>
			<%		
		}
	}
%>
<%-- category : <%= category %><br>
article_no : <%= no  %> --%>
<script type="text/javascript">location.href="Notice.jsp";</script>
</body>
</html>