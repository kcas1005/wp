<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.hk.th.dao.*" %>
<%@ page import="com.hk.th.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<%
if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
	response.sendRedirect("login.jsp");
}
String id = (String)session.getAttribute("id");
%>
<script type="text/javascript">

function delOrder(proNum){
	location.href="delOrderPro.jsp?product="+proNum;
}

$(document).ready(function(){
	
	$(".pPoint").click(function(){
		location.href="plusPointPro.jsp"
	});
	
	$('.mypageManu li:eq(4)').css("background-color","#ff6e75");
	$('.mypageManu li:eq(4)').find("a").css("color","white");

	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","#ff6e75");
		$(this).find("a").css("color","white");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
		$('.mypageManu li:eq(4)').css("background-color","#ff6e75");
		$('.mypageManu li:eq(4)').find("a").css("color","white");
	});
	//소메뉴 클릭
	$(".mypageManu li:eq(0)").click(function(){
		location.href="MyCart.jsp";
	});
	$(".mypageManu li:eq(1)").click(function(){
		location.href="MyLikeList.jsp";
	});
	$(".mypageManu li:eq(2)").click(function(){
		location.href="MyMemberInpo.jsp";
	});
	$(".mypageManu li:eq(3)").click(function(){
		location.href="MyOrder.jsp";
	});
	$(".mypageManu li:eq(4)").click(function(){
		location.href="Myarticle.jsp";
	});
	
});

</script>
</head>
<body>

<%
//선언
MyArticleDao artDao = new MyArticleDao();
ArticleVo artVo = new ArticleVo();

MemberDao memDao = new MemberDao();
MemberVo memVo = new MemberVo();
memVo = memDao.selectMember(id);

ArrayList<ArticleVo> artvoli = new ArrayList<ArticleVo>();
artvoli = artDao.selectArticle(id);

//페이지 수
int maxartPageNum = 0;
int artcount = artDao.maxMyArticlePage(id);
maxartPageNum = (artcount/10) + 1;


//해당페이지
int ArticlePag = 1;
if(request.getParameter("apage") != null){
	ArticlePag = Integer.parseInt(request.getParameter("apage"));
}
ArticlePag = ((ArticlePag - 1) * 10);


%>


<!-- 해더 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>


<div class = "mypageIn">
	<h2 class = "mypageName">마이페이지</h2>
	<div class = "mySimpleInpo">
		<img src="../image/user.jpg" width="100px" class="userImg">
		<ul class = "InpoText">
			<li><h2> 어서오세요 "<%= memVo.getName() %>"  님</h2></li>
			<li><h4>포인트 : <%= memVo.getPoint()%> </h4><img src="../image/plus.png" width="20px" class = "pPoint"></li>
		</ul>
	</div>
	<div class = "mypageMain">
		<ul class = "mypageManu">
			<li><a href = "#">장바구니</a></li>
			<li><a href = "#">관심목록</a></li>
			<li><a href = "#">회원정보</a></li>
			<li><a href = "#">주문조회</a></li>
			<li><a href = "#">게시글</a></li>
		</ul>
		<div class = "mypageCountent" id="order">
			<table>
				<tr>
					<td>분류</td>
					<td>물품번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<%
					int articlear = 0;
					articlear += ArticlePag;
					for(int i = 0 ; i < 10; i++){
						if(articlear >= artcount){
							break;
						}
						int cate = artvoli.get(articlear).getCategory();
						String category = "";
						if(cate == 1){
							category = "물품 리뷰";
						}else{
							category = "문품 문의";
						}
						int proNum= artvoli.get(articlear).getProduct_no();
						String tile = artvoli.get(articlear).getTitle();
						String writeId = artvoli.get(articlear).getId();
						String date = artvoli.get(articlear).getRegdate();
						int re_cnt = artvoli.get(articlear).getRead_cnt();
						out.print("<tr>");
						out.print("<td>"+category+"</td>");
						out.print("<td>"+proNum+"</td>");
						out.print("<td><a href='#'>"+tile+"</a></td>");
						out.print("<td>"+writeId+"</td>");
						out.print("<td>"+date+"</td>");
						out.print("<td>"+re_cnt+"</td>");
						out.print("</tr>");
						articlear++;
					}
				%>
			</table>
			<div class = "cartPaging">
				<%
				for(int i = 1 ; i <= maxartPageNum; i++)
				{
					
					out.print("<a href='Myarticle.jsp?apage="+i+"'>"+i+"</a>");
				}
				%>
			</div>
		</div>
	</div>
</div>




<!-- 푸터 -->
<jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
</html>