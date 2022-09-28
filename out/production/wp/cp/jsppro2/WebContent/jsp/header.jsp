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
<link rel="stylesheet" type="text/css" href="../css/header.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>러블리슈즈</title>
<link rel="shortcut icon" href="../image/favicon.png">
</head>
<body>
<%
String sid = (String)session.getAttribute("id");
MemberDao memDao = new MemberDao();
%>
<header >
	<div id="header">
	<a href='index.jsp' class='logo'></a>
	<button class='menubutton'>MENU</button>
	<ul class="menu pc_menu">
		<li class="m_form">
			<form action="search.jsp" method="get" >
				<input type="hidden" name="pageoffset" value="1">
				<input type="hidden" name="pagenum" value="1">
				<input type="search" name="search" class="search">
				<input type="submit" value="검색" class="search_btn">
			</form>
		</li>
		<li class=''><a href="product_list.jsp?pageoffset=1&pagenum=1">여성신발</a></li>
		<li><a href="MyCart.jsp">마이페이지</a>
			<ul class="sub">
				<li><a href="MyCart.jsp">장바구니</a></li>
				<li><a href="MyLikeList.jsp">관심목록</a></li>
				<li><a href="MyMemberInpo.jsp">회원정보</a></li>
				<li><a href="MyOrder.jsp">주문조회</a></li>
				<li><a href="Myarticle.jsp">작성글</a></li>
			</ul>
		</li>
		<li><a href="Notice.jsp">고객센터</a>
			<ul class="sub">
				<li><a href="Notice.jsp">공지사항</a></li>
				<li><a href="QA.jsp">Q & A</a></li>
			</ul>
		
		</li>
		<%
		if(memDao.isAdmin(sid) == true){
			out.print("<li class='admin'><a href='manage_members.jsp'>관리자모드</a>"
					+"<ul class='sub'>"
					+"<li><a href='manage_members.jsp'>회원관리</a></li>"
					+"<li><a href='manage_article.jsp'>게시판관리</a></li>"
					+"<li><a href='manage_product.jsp'>상품관리</a></li>"
					+"<li><a href='manage_admin.jsp'>관리자관리</a></li>"
					+"</ul>"
					+"</li>");
		}
		%>
	</ul>
	
	<ul class="menu m_menu">
		<li class="m_form">
			<form action="search.jsp" method="get" >
				<input type="hidden" name="pageoffset" value="1">
				<input type="hidden" name="pagenum" value="1">
				<input type="search" name="search" class="search">
				<input type="submit" value="검색" class="search_btn">
			</form>
		</li>
		<li class=''><a href="product_list.jsp?pageoffset=1&pagenum=1">여성신발</a></li>
		<li><a>마이페이지</a>
			<ul class="sub">
				<li><a href="MyCart.jsp">장바구니</a></li>
				<li><a href="MyLikeList.jsp">관심목록</a></li>
				<li><a href="MyMemberInpo.jsp">회원정보</a></li>
				<li><a href="MyOrder.jsp">주문조회</a></li>
				<li><a href="Myarticle.jsp">작성글</a></li>
			</ul>
		</li>
		<li><a>고객센터</a>
			<ul class="sub">
				<li><a href="Notice.jsp">공지사항</a></li>
				<li><a href="QA.jsp">Q & A</a></li>
			</ul>
		
		</li>
		<%
		if(memDao.isAdmin(sid) == true){
			out.print("<li class='admin'><a>관리자모드</a>"
					+"<ul class='sub'>"
					+"<li><a href='manage_members.jsp'>회원관리</a></li>"
					+"<li><a href='manage_article.jsp'>게시판관리</a></li>"
					+"<li><a href='manage_product.jsp'>상품관리</a></li>"
					+"<li><a href='manage_admin.jsp'>관리자관리</a></li>"
					+"</ul>"
					+"</li>");
		}
		%>
	</ul>
<form action="search.jsp" method="get" class="p_form">
	<input type="hidden" name="pageoffset" value="1">
	<input type="hidden" name="pagenum" value="1">
	<input type="search" name="search" class="search">
	<input type="submit" value="검색" class="search_btn">
</form>
	</div>
	<ul class='home_login'>
		<li><a href='index.jsp'  style='border-right:1px solid #fff;'>HOME</a></li>
		<%
		if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
			out.print("<li><a href='login.jsp'>LOGIN</a></li>");
		}else{
			out.print("<li><a href='logout.jsp'>LOGOUT</a></li>");
		}
		%>
	</ul>
<div id="menu_bg"></div>
</header>


<!-- 고정 탑 버튼 -->
<div id="scroll_btn" style=''>
		<div><a href="#top" class='scroll_top1' onclick='scroll_top1();'>^</a></div>
</div>


<script>
$(document).ready(function(){
	a = true; 
	$("button.menubutton").click(function(){
	if ( a == true) { 
		$("ul.menu").animate({"right" : "0%"});
		$("ul.menu .m_form form").animate({"display" : "block"});
		a = false 
		} else {
		$("ul.menu").animate({"right" : "100%"});
		$("ul.menu .m_form form").animate({"display" : "block"});
		a = true
		}
	})
	
})
$(document).ready(function(){
	$("#header ul.pc_menu li:not(.m_form)").hover(function(){
		$("ul.sub").slideDown();
		$("#menu_bg").slideDown();
	})
	$("header").mouseleave(function(){
		$("ul.sub").slideUp();
		$("#menu_bg").slideUp();
	})
	
})

$(document).ready(function(){
	$("#header ul.m_menu li:not(.m_form)").click(function(){
		$(this).find(">ul.sub").slideToggle();
		$("#menu_bg").slideToggle();
	})
	
})
function scroll_top1(){
		$("body, html").animate({scrollTop : 0})
}


</script>




</body>
</html>