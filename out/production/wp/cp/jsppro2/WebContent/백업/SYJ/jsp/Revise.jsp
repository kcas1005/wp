<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.Vo.*" %>
<%@ page import ="com.hk.th.Dao.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-sacle=1"charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<title>Insert title here</title>
<style>
</style>
</head>
<body>
<%
	ArticleDao review = new ArticleDao();
ProductDao dao = new ProductDao();
MemberVo mem = new MemberVo();
ProductVo list = new ProductVo();
ArticleVo arti = new ArticleVo();
ArrayList<ArticleVo> article = new ArrayList<ArticleVo>();
ArrayList<ArticleVo> articlepage = new ArrayList<ArticleVo>();
int article_no =  Integer.parseInt(request.getParameter("article_no")); 
String revise = request.getParameter("revise");
String product_no = request.getParameter("product_no"); 
/* 수정할 글 */
arti = review.select_list(article_no);
/* 아이디 */
String session_id = (String)session.getAttribute("id");
%>

<%
if(revise.equals("1")){ %>
<div id="tabmenu">
	<table> 
		<tr><td><a href="#" >상세정보</a></td>
		<td><a href="#"style='background:#333; color:#fff;'>상품 리뷰</a></td>
		<td><a href="#">상품 문의</a></td></tr>
	</table>
</div>
	<div id="qna"  class="re">
		<form action='Revise_pro.jsp' method="get" id='form' >
		<input type='hidden' name='review_category' value='1'>
		<input type='hidden' name='product_no' value='<%=product_no %>'>
		<input type='hidden' name='article_no' value='<%=arti.getArticle_no() %>'>
			<p class='review'> 회원님의 리뷰를 작성 해 주세요!! </p>
			<!-- 첫줄 -->	
				<ul class='review_txt'>
					<li class='star_table'>
						<select name ='star' >
							<option value='5' selected>★★★★★ 
							<option value='4'>★★★★☆
							<option value='3'>★★★☆☆
							<option value='2'>★★☆☆☆
							<option value='1'>★☆☆☆☆
						</select>
					</li>
					<li class='title_table'>
						<input type='text' name='title' style='padding-left:2%;'class='title' value='<%=arti.getTitle()%>'>
					</li>
					<li class='clear content_table'>
						<textarea style='resize: none; width:100%;' name='content'class='textarea' ><%=arti.getContent()%></textarea>
					</li>
					<li class='id_table'style='padding-bottom:10px;' >
						<input type='text' name='id' class='id' placeholder='id' value='<%=session_id%>' disabled>
					</li> 
					<li class='submit_table' style='padding-bottom:10px;'>
					<button type='button'class='create_table' value='' >수정완료</button>
					</li>
			</ul>
		</form>
	</div>
	<%
	}if(revise.equals("2")){
	%>
	<div id="tabmenu">
	<table> 
		<tr><td><a href="#" >상세정보</a></td>
		<td><a href="#">상품 리뷰</a></td>
		<td><a href="#"style='background:#333; color:#fff;'>상품 문의</a></td></tr>
	</table>
	</div>
	<div id="qna"  class="re">
		<form action='Revise_pro.jsp' method="get" id='form' >
		<input type='hidden' name='review_category' value='1'>
		<input type='hidden' name='product_no' value='<%=product_no %>'>
		<input type='hidden' name='article_no' value='<%=arti.getArticle_no() %>'>
			<p class='review'> 궁금하신 것 물어보세요! </p>
			<!-- 첫줄 -->	
				<ul class='review_txt'>
					<li  class='title_table title_table2'>
						<input type='text' name='title' class='title' style='padding-left:2%;' value='<%=arti.getTitle()%>' >
					</li>
					<li class='content_table'>
						<textarea style='resize: none; width:100%;' name='content' class='textarea' ><%=arti.getContent()%>'</textarea>
					</li>
					<li  class='id_table' style='padding-bottom:10px;' >
						<input type='text' name='id' class='id' style='padding-left:2%;' value="<%=session_id%>" disabled>
					</li>
					<li class='submit_table' style='padding-bottom:10px;'>
					<button type='button'class='create_table' value=''>문의수정</button>
					</li>
			</ul>
		</form>
	</div>
	
	
	
	<%
	}
	%>
		
		<script>
		$(document).ready(function(){
			$(".create_table").click(function(){
				if($(".title").val()==""){alert("제목을 입력해주세요");$(".title").focus(); }
				else if($(".textarea").val()==""){alert("내용을 입력해주세요");$(".textarea").focus();
				}else if($(".id").val()==""){alert("아이디를 입력해주세요");$(".textarea").focus();
				}else{
					$("#form").submit();
					}
				})
			
		})
		
		</script>
		
<script>


		
</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>