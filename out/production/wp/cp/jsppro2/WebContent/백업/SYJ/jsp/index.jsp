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
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<%
ProductDao dao = new ProductDao();
ArrayList<ProductVo> main = new ArrayList<ProductVo>();
main = dao.main_cnt();
String img_root = "../image/";

%>
<div id="visual">
	<ul class="mainvisual">
		<li><a href="#"><img src="../image/visual03.jpg"></a></li>
		<li><a href="#"><img src="../image/visual02.jpg"></a></li>
		<li><a href="#"><img src="../image/visual01.jpg"></a></li>
	</ul>
</div>
<div id="mvisual">
	<ul class="mainvisual">
		<li><a href="#"><img src="../image/visual03.jpg"></a></li>
		<li><a href="#"><img src="../image/visual02.jpg"></a></li>
		<li><a href="#"><img src="../image/visual01.jpg"></a></li>
	</ul>
</div>
<div id="content">
<h1>이번주 조회수 순위</h1>
	<div class="slidenum">
		<ul>
			<li>1위</li>
			<li>2위</li>
			<li>3위</li>
			<li>4위</li>
			<li>5위</li>
		</ul>
	</div>
	<ul class="slide_con">
		<%
		for(int i = 0; i<main.size();i++){
			out.print("<li><a href='content.jsp?product_no="
			+main.get(i).getProduct_no()+"&reviewpage=1&reviewcategory=0'><img src='"
			+img_root+main.get(i).getProduct_img()
			+"'><div class='pro_text'><p>"+main.get(i).getProduct_name()+"</p>"
			+"<b>"+main.get(i).getPrice()+"</b></div></a></li>");
		}
		%>
	</ul>
	
</div>
<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
<script>
function slide(){
	$("#visual .mainvisual").stop(true,true).animate({
		"margin-left" : "-=1920px"
	},function(){
		$("#visual ul.mainvisual li:first-child").appendTo("#visual .mainvisual"); //첫번째 li를 ul뒤로
		$(this).css("margin-left","0");
	})
} 
function p_slide(){
	
		$("#content ul").animate({"margin-left":"-=10px"},
			function(){
			$("#content ul li:first-child").add("#content ul");
			$(this).css("margin-left","0");
		});
	
	
}

$(document).ready(function(){
	/* $("#content ul li:last-child").appendTo("#content ul"); */
	auto = setInterval(slide, 3000);
	$(".mainvisual").hover(function(){
	clearInterval(auto);
	}, function(){
	auto = setInterval(slide, 2000);
	})
	//조회수순위
	 //첫번째 li를 ul뒤로
 	
	$(".slidenum li:nth-child(1)").click(function(){
		$("#content ul.slide_con").animate({"margin-left":"0"});
		$(".slidenum li").removeClass("selected");
		$(this).addClass("selected");
	})
	$(".slidenum li:nth-child(2)").click(function(){
		$("#content ul.slide_con").animate({"margin-left":"-100%"});
		$(".slidenum li").removeClass("selected");
		$(this).addClass("selected");
	})
	$(".slidenum li:nth-child(3)").click(function(){
		$("#content ul.slide_con").animate({"margin-left":"-200%"});
		$(".slidenum li").removeClass("selected");
		$(this).addClass("selected");
	})
	$(".slidenum li:nth-child(4)").click(function(){
		$("#content ul.slide_con").animate({"margin-left":"-300%"});
		$(".slidenum li").removeClass("selected");
		$(this).addClass("selected");
	})
	$(".slidenum li:last-child").click(function(){
		$("#content ul.slide_con").animate({"margin-left":"-400%"});
		$(".slidenum li").removeClass("selected");
		$(this).addClass("selected");
	})
	
	
	
	
	
})

$(document).ready(function(){
	li.length = $("#content ul li").length;
	$("#content ul li").hover(function(){
		 li_index = $(this).index(); //0 1 2 3 ..
	})
})

</script>

</body>
</html>