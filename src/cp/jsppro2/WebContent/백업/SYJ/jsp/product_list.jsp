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
ArrayList<ProductVo> list = new ArrayList<ProductVo>();
ArrayList<ProductVo> all_list = new ArrayList<ProductVo>();
all_list = dao.All_list();
String product_img  = "<img src='../image/custom_88.gif'alt='' title=''>";
String img_root = "../image/"; //썸네일 이미지 루트
int listsize = all_list.size()/12;
int pageoffset = Integer.parseInt(request.getParameter("pageoffset"));//정렬방식 지정번호
int pagenumber = Integer.parseInt(request.getParameter("pagenum"));//페이지 넘버
int pageindex = 12;//보여줄 개체 수
int pageoffset2 = (pagenumber*10)-9;//페이지 2부터 시작점 설정

//기본페이지정렬
if(pageoffset == 1 && pagenumber == 1){list = dao.list(pageoffset-1, pageindex);}
if(pageoffset == 1 && pagenumber > 1){list = dao.list(pageoffset2, pageindex);}
//조회수 많은 순 정렬
if(pageoffset ==2 && pagenumber == 1){list = dao.read_list_dasc2(pagenumber-1, pageindex);}
if(pageoffset ==2 && pagenumber > 1){list = dao.read_list_dasc2(pageoffset2, pageindex);}
//가격 낮은 순 정렬
if(pageoffset ==3 && pagenumber == 1){list = dao.pricelist_asc3(pagenumber-1, pageindex);}
if(pageoffset ==3 && pagenumber > 1){list = dao.pricelist_asc3(pageoffset2, pageindex);}
//가격 높은 순 정렬
if(pageoffset ==4 && pagenumber == 1){list = dao.pricelist_desc4(pagenumber-1, pageindex);}
if(pageoffset ==4  && pagenumber > 1){list = dao.pricelist_desc4(pageoffset2, pageindex);}

	




%>
<script>
function  Sold_out(){
	alert("상품은 품절 되었습니다.");
}
</script>
<div id = "product_list">
<h1>여성구두</h1>
	<ul>
		<li><a href="product_list.jsp?pageoffset=1&pagenum=1" >최신 순</a></li>
		<li><a href="product_list.jsp?pageoffset=2&pagenum=1" >많은 조회 순</a></li>
		<li><a href="product_list.jsp?pageoffset=3&pagenum=1">낮은 가격 순</a></li>
		<li><a href="product_list.jsp?pageoffset=4&pagenum=1">높은 가격 순</a></li>
	</ul>
	<div class="product">
		
		<%
		out.print("<ul>");
		 for(int i = 0; i<list.size();i++){
			 int read_cnt = list.get(i).getRead_cnt();
			 int amount = list.get(i).getAmount();
			 String date = list.get(i).getRegdate();
			 String Price = "<b>"+list.get(i).getPrice()+" 원</b>";
			 String link_a ="<a style=display:inline-block;'' href=";
			 String href_a = "'content.jsp?product_no="+list.get(i).getProduct_no()+"&reviewpage=1&reviewcategory=0'>";
			 String link_img = "<img src='";
					 
			 if(read_cnt>=50){ Price = "<b>"+list.get(i).getPrice()+" 원</b>"+product_img;} //조회수 50이상 페이스북 인기상품 이미지 띄우기
			 if(amount==0){
				 //재고 없을 시 취소선
				Price ="<b style='text-decoration:line-through;'>"+list.get(i).getPrice()+" 원</b><span>재입고예정</span>";
				link_a ="<a style='background:#666; display:inline-block; height:100%;'onclick='Sold_out();' href=";
				href_a = "'#'>";
				link_img = "<img style='opacity:0.3;' src='";
			 }
			 if(amount==0 && read_cnt>=50){
				 Price ="<b style='text-decoration:line-through;'>"+list.get(i).getPrice()+" 원</b><span>Sold out</span>"+product_img;
			 }
			 
			 
			 out.print("<li class='pro_text'>"+link_a+href_a
						+link_img
						+img_root+list.get(i).getProduct_img() 
						+"'></a><p>"+list.get(i).getProduct_name()+"</p>"
						+Price+"</li>"); 
		 }
		 out.print("</ul>");
		 
		 
		 
		%>
		
		
	</div>
	<div id="link_number" style="text-align:center;">
			<%
			//페이지 번호 자동증가
			int pagenum = 1;
			for(int n = 0; n <=listsize; n++){
				out.print("<a href='product_list.jsp?pageoffset="+pageoffset+"&pagenum="+pagenum+"' >"+pagenum+"</a>");
				pagenum +=1;
			}
			%>
		<script>
		$(document).ready(function(){
			$("#link_number a:nth-child(<%=pagenumber %>)").css({"color":"black" , "font-weight":"bold"});
			$("#product_list>ul li:nth-child(<%=pageoffset %>) a").addClass("selected");
		})
		</script>
			
		
		</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>