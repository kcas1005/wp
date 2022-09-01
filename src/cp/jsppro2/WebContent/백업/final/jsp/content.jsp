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
String img_root = "../image/"; //썸네일 이미지 루트
ProductDao dao = new ProductDao();
ProductVo list = new ProductVo();
String product_no = request.getParameter("product_no"); 
String reviewcategory =  request.getParameter("reviewcategory"); // 조회수 한번만 올리기

list = dao.content_detail(product_no);
int price = list.getPrice();

//조회수 한번만 올리기
int read_cnt = list.getRead_cnt();//조회수
if(reviewcategory.equals("1") || reviewcategory.equals("2") || reviewcategory.equals("3")){
	int read = read_cnt-=1;
	dao.r_count(read_cnt, product_no);
}
%>
<jsp:include page="product.jsp"></jsp:include>
<div id="tabmenu">
	<table> 
		<tr><td><a href="content.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=1" style='background:#333; color:#fff;' >상세정보</a></td>
		<td><a href="content1.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=2">상품 리뷰</a></td>
		<td><a href="content2.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=3">상품 문의</a></td></tr>
	</table>
</div>
<div id="view">
	<div id="product_detail">
		<p><img src="<%=img_root+list.getProduct_dec() %>"title='' alt=''></p>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#product_box ul li button.cart").click(function(){
		$("#product_box form").attr({'action':'PrductInPro.jsp'});
		$(".category").val('1');
		$(this).submit();
	}) 
	$("#product_box ul li .wish").click(function(){
		$("#product_box form").attr({'action':'PrductInPro.jsp'});
		$(".category").val('2');
		$(this).submit();
	})
	$("#product_box ul li .submit").click(function(){
		$("#product_box form").attr({'action':'PrductInPro.jsp'});
		$(".category").val('3');
		$(this).submit();
	})
})


</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>