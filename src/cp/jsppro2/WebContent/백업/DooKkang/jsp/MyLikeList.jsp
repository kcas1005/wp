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
<%
if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
	response.sendRedirect("login.jsp");
}
String id = (String)session.getAttribute("id");
%>
<style type="text/css">
.list{
	text-align: left;	
}
</style>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">

function delList(proNum){
	location.href="delListPro.jsp?product="+proNum;
}

$(document).ready(function(){
	
	$(".pPoint").click(function(){
		location.href="plusPointPro.jsp"
	});
	
	$('.mypageManu li:eq(1)').css("background-color","#ff6e75");
	$('.mypageManu li:eq(1)').find("a").css("color","white");
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","#ff6e75");
		$(this).find("a").css("color","white");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
		$('.mypageManu li:eq(1)').css("background-color","#ff6e75");
		$('.mypageManu li:eq(1)').find("a").css("color","white");
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
CartDao mpDao = new CartDao();
ProductVo proVo = new ProductVo();
ProductDao proDao = new ProductDao();

MemberDao memDao = new MemberDao();
MemberVo memVo = new MemberVo();
memVo = memDao.selectMember(id);

ArrayList<CartVo> lvo = new ArrayList<CartVo>();
lvo = mpDao.selectCart(id, 2);

//페이지 수
int maxListPageNum = 0;
int listcount = mpDao.maxCartPage(id, 2);
maxListPageNum = (listcount/10) + 1;

//해당페이지
int ListPag = 1;
if(request.getParameter("lpage") != null){
	ListPag = Integer.parseInt(request.getParameter("lpage"));
}
ListPag = ((ListPag - 1) * 10);
%>


<!-- 해더 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>


<div class = "mypageIn">
	<h2 class = "mypageName">마이페이지</h2>
	<div class = "mySimpleInpo">
		<img src="../image/user.jpg" width="100px" class="userImg">
		<ul class = "InpoText">
			<li><h2> 어서오세요 "<%= memVo.getName() %>" 님</h2></li>
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
		<div class = "mypageCountent" id="likeList">
			<table>
				<tr>
					<td>상품번호</td>
					<td></td>
					<td>상품명</td>
					<td>금액</td>
					<td>삭제</td>
				</tr>
				<%
					int listar = 0;
					listar += ListPag;
					for(int i = 0 ; i < 10; i++){
						if(listar >= listcount){
							break;
						}
						int curpronum = lvo.get(listar).getProduct_no();
						proVo = proDao.selectProductId(curpronum);
						int proNum = proVo.getProduct_no();
						String proName = proVo.getProduct_name();
						int pri = proVo.getPrice();
						int selNum= lvo.get(listar).getPurchase();
						out.print("<tr>");
						out.print("<td>"+proNum+"</td>");
						out.print("<td><a href='#'><img src='../image/icon_1.jpg' width='100px'></a></td>");
						out.print("<td class = 'list'><a href='#'>"+proName+"</a></td>");
						out.print("<td>"+pri+"</td>");
						out.print("<td><input type='button' name='product' class = 'cartIn' value = '삭제' onclick='delList("+proNum+")'></td>");
						out.print("</tr>");
						listar++;
					}
				%>
			</table>
			<div class = "cartPaging">
				<%
				for(int i = 1 ; i <= maxListPageNum; i++)
				{
					out.print("<a href='MyLikeList.jsp?lpage="+i+"'>"+i+"</a>");
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