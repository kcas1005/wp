<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
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
	/* 아이디저장 */

ArticleDao review = new ArticleDao();
ProductDao dao = new ProductDao();
ProductVo list = new ProductVo();
ArrayList<ArticleVo> article = new ArrayList<ArticleVo>();
ArrayList<ArticleVo> articlepage = new ArrayList<ArticleVo>();

String product_no = request.getParameter("product_no"); 
String reviewcategory =  request.getParameter("reviewcategory"); // 조회수 한번만 올리기
/* 리뷰,문의 */
article = review.Review_list(2 , product_no); 
/* 리뷰문의 페이지번호 */
int page01 = article.size()/10;//문의모든 글
/* 페이징 */
int reviewpage =  Integer.parseInt(request.getParameter("reviewpage"));
if(reviewpage==1){
	reviewpage -= 1;
	articlepage = review.Review_page(2, product_no, reviewpage);
}else{
	reviewpage = (reviewpage*10)-11;
	articlepage = review.Review_page(2, product_no, reviewpage);
}

// 조회수 한번만 올리기
list = dao.content_detail(product_no);//상품 리스트 1개 출력
int read_cnt = list.getRead_cnt();//조회수
if(reviewcategory.equals("1") || reviewcategory.equals("2") || reviewcategory.equals("3")){
	read_cnt-=1;
	dao.r_count(read_cnt, product_no);
}
String session_id = "";
if((String)session.getAttribute("id") == null || (String)session.getAttribute("id") ==""){
	session_id = "로그인 정보가 없습니다";
}else{
	session_id = (String)session.getAttribute("id");
}
%>
<jsp:include page="product.jsp"></jsp:include>

<jsp:include page="iframe.jsp"></jsp:include>

<div id="tabmenu">
	<table> 
		<tr><td><a href="content.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=1"  >상세정보</a></td>
		<td><a href="content1.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=2">상품 리뷰</a></td>
		<td><a href="content2.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=3"style='background:#333; color:#fff;'>상품 문의</a></td></tr>
	</table>
</div>
	<div id="qna"  class="re" >
	<form action='review_pro.jsp' method="get" id='form'>
		<input type='hidden' name='review_category' value='2'>
		<input type='hidden' name='product_no' value='<%=product_no %>'>
			<p class='review'> 궁금하신 것 물어보세요! </p>
			<!-- 첫줄 -->	
				<ul class='review_txt'>
					<li  class='title_table title_table2'>
						<input type='text' name='title' class='title' style='padding-left:2%;' placeholder='제목을 입력해주세요'>
					</li>
					<li class='content_table'>
						<textarea style='resize: none; width:100%;' name='content' class='textarea' placeholder='문의사항을 입력해주세요.'></textarea>
					</li>
					<li  class='id_table' style='padding-bottom:10px;' >
						<input type='text' name='id' class='id' style='padding-left:2%;' value="<%=session_id%>" disabled>
					</li>
					<li class='submit_table' style='padding-bottom:10px;'>
					<button type='button'class='create_table' value=''>문의등록</button>
					</li>
				</ul>
		</form>
		<script>
		$(document).ready(function(){
			$(".create_table").click(function(){
				if($(".id").val()=="로그인 정보가 없습니다"){alert("로그인을 해 주세요");}else{
					if($(".title").val()==""){alert("제목을 입력해주세요");$(".title").focus(); }
					else if($(".textarea").val()==""){alert("내용을 입력해주세요");$(".textarea").focus();
					}else{
						$("#form").submit();
						}
					}
				})
		})
			
			
		
		</script>
		<table class='review_table'>
			<tr class='top'><td class='star'style='width:20%'>글번호</td><td class='star' style='width:60%'>문의</td><td style='width:20%' class=''>작성자</td></tr>
			<%
			for(int i = 0; i<articlepage.size(); i++){
			%>	 
				<tr style='border-bottom:1px solid #ccc;'>
				<td class='clear star' style='width:20%'><%=articlepage.get(i).getArticle_no() %></td>
				<td class='text' style='width:60%'>
				<p class='title'> <%=articlepage.get(i).getTitle()%></p>
				<%if(session_id.equals(articlepage.get(i).getId())){%>
					<div>
					<a href='Revise.jsp?article_no=<%=articlepage.get(i).getArticle_no()%>&product_no=<%=articlepage.get(i).getProduct_no()%>&revise=2' target='iframe' class='iframe_show'>
					<button type='button' class='hidden_btn re'>수정하기</button></a>
					<button type='button' class='hidden_btn over'>더보기</button>
					</div>
					
				<%
				}else{
				%>	
					<div>
					<button type='button' class='hidden_btn over'>더보기</button>
					</div>
				<%
				}
				%>
				<p class='text_hidden'><%=articlepage.get(i).getContent()%>
				</p>
				<p class='date'><%=articlepage.get(i).getRegdate()%></p></td>
				<td  style='width:20%'><%=articlepage.get(i).getId()%></td> 
				</tr>
			<% 
			
			}
			%>
		</table>
		<div id="link_number" class='link_number2' style="text-align:center;">
			<%
			//페이지 번호 자동증가
			out.print("<ul>");
			int pagenum = 1;
			for(int n = 0; n <=page01; n++){
				out.print("<li><a href='content2.jsp?reviewpage="+pagenum+"&product_no="+product_no+"' >"+pagenum+"</a></li>");
				pagenum +=1;
			}
			out.print("<ul>");
			%>
		<script>
		$(document).ready(function(){
			$("#link_number a:nth-child(<%=reviewpage %>)").css({"color":"black" , "font-weight":"bold"});
			$("#product_list>ul li:nth-child(<%=reviewpage %>) a").addClass("selected");
			li = $(".link_number2 li").length;
			$("#link_number ul").css('width' , li*20);
		})
		</script>
		</div>
	</div>
<script>
$(document).ready(function(){
	$("#product_box ul li button.cart").click(function(){ //전송시 value 값바꿔보내기
		$("#product_box form").attr({'action':'PrductInPro.jsp'});
		$(".category").val('1');
		$(this).submit();
	}) 
	$("#product_box ul li .wish").click(function(){
		$("#product_box form").attr({'action':'PrductInPro.jsp'});//전송시 value 값바꿔보내기
		$(".category").val('2');
		$(this).submit();
	})
})

$(document).ready(function(){
	
		$("#link_number a:nth-child(<%=reviewpage %>)").css({"color":"black" , "font-weight":"bold"});
		$("#product_list>ul li:nth-child(<%=reviewpage %>) a").addClass("selected");
		li = $(".link_number2 li").length;
		$("#link_number ul").css('width' , li*20);//페이징  번호나열 크기 자동 늘어나기
})
$(document).ready(function(){
	top_width = $(".re table.review_table tr.top td:eq(1)").width();
	$(".re p.text_hidden").css('width',top_width);
	$(".re button.over").click(function(){
		$(this).closest("div").next(".text_hidden").stop(true,true).slideToggle();
	})
})

</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>