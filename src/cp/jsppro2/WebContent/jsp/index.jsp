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
<link rel="stylesheet" type="text/css" href="../css/index.css">
<!-- <link rel="shortcut icon" href="../image/favicon.png">
<title>러블리슈즈</title> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>


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

<div id='slide_buttin'>
	<ul>
		<li>1</li>
		<li>2</li>
		<li>3</li>
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
			int read_cnt = main.get(i).getRead_cnt();
			String product_img  = "<img src='../image/custom_88.gif'alt='' title='' style='width:73px; height:17px;margin:0 7px;vertical-align: middle;'>";
			String price = "<b>"+ main.get(i).getPrice()+" 원</b>";
			if(read_cnt>=50){ price = price+product_img; } 
			out.print("<li><a href='content.jsp?product_no="
			+main.get(i).getProduct_no()+"&reviewpage=1&reviewcategory=0'><img src='"
			+img_root+main.get(i).getProduct_img()
			+"'><div class='pro_text'><p>"+main.get(i).getProduct_name()+"</p>"
			+price+"</b></div></a></li>");
		}
		%>
	</ul>
	
</div>
<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
<script>
$(document).ready(function(){
		//슬라이드 버튼
	slide = 1;
	auto =  setInterval(function(){
		$("#visual .mainvisual").stop(true,true).animate({"margin-left" : -(slide*100)+"%"});
		slide++;
		if(slide == 3){
			slide = 0;
		}
		//슬라이드 버튼 현재위치
		$("#slide_buttin ul li").css("background" , "#ccc");
		$("#slide_buttin ul li").eq(slide-1).css("background" , "#000");
		//슬라이드 버튼 클릭시 해당 슬라이드 이동, 현재위치 활성화
		
	},2000);
	
	//슬라이드 마우스오버시 슬라이드멈춤
	$(".mainvisual").hover(function(){
		clearInterval(auto);
		}, function(){
		auto = setInterval(function(){
			$("#visual .mainvisual").stop(true,true).animate({"margin-left" : -(slide*100)+"%"});
			slide++;
			if(slide == 3){
				slide = 0;
			}
			//슬라이드 버튼 현재위치
			$("#slide_buttin ul li").css("background" , "#ccc");
			$("#slide_buttin ul li").eq(slide-1).css("background" , "#000");
			
		},2000);
	})
	$("#slide_buttin ul li").click(function(){
		btn_index = $(this).index();
		$("#visual .mainvisual").stop(true,true).animate({"margin-left" : -(btn_index*100)+"%"});
		$("#slide_buttin ul li").css("background" , "#ccc");
		$("#slide_buttin ul li").eq(btn_index).css("background" , "#000");
		slide = btn_index;
		
	})
	
	
	
})
function p_slide(){
	
		$("#content ul").animate({"margin-left":"-=10px"},
			function(){
			$("#content ul li:first-child").add("#content ul");
			$(this).css("margin-left","0");
		});
	
	
}

$(document).ready(function(){
	/* $("#content ul li:last-child").appendTo("#content ul"); */
	
	
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



</script>

</body>
</html>