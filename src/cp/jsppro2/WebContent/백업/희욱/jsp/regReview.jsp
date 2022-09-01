<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="shopping.dao.ProductDao" %>
<%@ page import="shopping.vo.ProductVo" %>
<%@ page import="shopping.dao.ArticleDao" %>
<%@ page import="shopping.vo.ArticleVo" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/regArticle.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/[version.number]/[distribution]/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/12.3.0/classic/ckeditor.js"></script>
<style type="text/css">

</style>
</head>
<body>
<%
	int no = Integer.valueOf(request.getParameter("product_no"));
	String id = (String)session.getAttribute("id");
	ProductDao pd = new ProductDao();
	ProductVo pv = new ProductVo();
	pv = pd.selectOneByProductNo(no);

	int revise=0;
	int category=1;
	int article_no=0;
	
	if(request.getParameter("revise")!= null)
		revise = Integer.valueOf(request.getParameter("revise"));
	ArticleVo av = new ArticleVo();
	if(revise == 1){//수정인 경우 원래 게시글의 값들을 가져옴.
		if(request.getParameter("article_no")!= null)
			article_no = Integer.valueOf(request.getParameter("article_no"));
		
		ArticleDao ad = new ArticleDao();
		av = ad.getArticleInfo(category,article_no);
}
%>
<jsp:include page="header.jsp"></jsp:include>
<div class="box">

	<form method="get" action="regArticlepro.jsp">
		<input type="hidden" name="no" value=<%= no %>>
		<p>상품명&nbsp; : &nbsp;<%= pv.getProduct_name() %></p>
		<p>제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;<input type="text" name="title" 
		<%
			if(revise == 1)
				out.print("palceholder='"+av.getTitle()+"'");
		%>></p>
		<p>별점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;
		<select name="star">
			<option 
			<%
				if(av.getStar() == 1)
					out.print("selected");
			%>
			>1</option>
			<option 
			<%
				if(av.getStar() == 1)
					out.print("selected");
			%>>2</option>
			<option 
			<%
				if(av.getStar() == 1)
					out.print("selected");
			%>>3</option>
			<option 
			<%
				if(av.getStar() == 1)
					out.print("selected");
			%>>4</option>
			<option 
			<%
				if(av.getStar() == 1)
					out.print("selected");
			%>>5</option>
		</select>
		<p><textarea name="content" id="editor" <%
			if(revise == 1)
				out.print("palceholder='"+av.getContent()+"'");
		%>></textarea></p>
		<input type="hidden" name="revise" value=<%=revise %>>
		<input type="hidden" name="article_no" value=<%=article_no %>>
		<input type="hidden" name="category" value=<%= category %>>
	<input class="btn btn-primary" type="submit" value="등록" id="submit">
</form>

</div>
<script type="text/javascript">
ClassicEditor
	.create( document.querySelector( '#editor' ), {
        // 제거 하고싶은 플러그인 (배열)
        removePlugins: [ 'ImageUpload' ]
   } )
   /* .catch( error => {
       console.error( error );
   } ) */;
</script>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>