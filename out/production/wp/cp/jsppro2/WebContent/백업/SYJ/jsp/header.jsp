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
<link rel="stylesheet" type="text/css" href="../css/header.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<header>
	<div id="header">
	<a href='index.jsp' class='logo'></a>
	<button class='menubutton'>MENU</button>
	<ul class="menu">
		<li class="m_form">
			<form action="search.jsp" method="get" >
				<input type="hidden" name="pageoffset" value="1">
				<input type="hidden" name="pagenum" value="1">
				<input type="search" name="search" class="search">
				<input type="submit" value="검색" class="search_btn">
			</form>
		</li>
		<li><a href="product_list.jsp?pageoffset=1&pagenum=1">여성신발</a></li>
		<li><a href="#">마이페이지</a>
			<ul class="sub">
				<li><a href="#">장바구니</a></li>
				<li><a href="#">관심목록</a></li>
				<li><a href="#">회원정보</a></li>
				<li><a href="#">주문조회</a></li>
			</ul>
		</li>
		<li><a href="#">고객센터</a>
			<ul class="sub">
				<li><a href="#">공지사항</a></li>
				<li><a href="#">문의사항</a></li>
			</ul>
		
		</li>
		<li class="admin"><a href="#">관리자모드</a>
			<ul class="sub">
				<li><a href="#">회원관리</a></li>
				<li><a href="#">게시판관리</a></li>
			</ul>
		</li>
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
		<li><a href='index.jsp'>LOGIN</a></li>
	</ul>
<div id="menu_bg"></div>
</header>
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
	$("#header ul.menu li:not(.m_form)").hover(function(){
		$("ul.sub").slideDown();
		$("#menu_bg").slideDown();
	})
	$("header").mouseleave(function(){
		$("ul.sub").slideUp();
		$("#menu_bg").slideUp();
	})
	
})

</script>




</body>
</html>