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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<%
String img_root = "../image/"; //썸네일 이미지 루트

ProductDao dao = new ProductDao();
ProductVo list = new ProductVo();
String product_no = request.getParameter("product_no"); 

list = dao.content_detail(product_no);//상품 리스트 1개 출력
int price = list.getPrice();//상품 리스트 가격
int read_cnt = list.getRead_cnt();//조회수

read_cnt+=1;
dao.r_count(read_cnt, product_no);


%>

<div id="product_box">
	<form action="" method="get">
		<p><img src = '<%=img_root+list.getProduct_img()%>'></p>
		<input type="hidden" name = "proNum" value="<%=product_no%>">
		<ul> 
			<li><h5 class="product_name"><%=list.getProduct_name() %></h5></li>
			<li><h6>판매가</h6><h6 class="product_price"><%=price %>원</h6></li>
			<li><span>사이즈 </span>
			<select id="size" name="size">
				<option value='' selected>사이즈를 선택해주세요
				<option value='220'>220
				<option value='225'>225
				<option value='230'>230
				<option value='235'>235
				<option value='240'>240
				<option value='245'>245
				<option value='250'>250
			</select>
			</li>
			<li><span>구매갯수 </span> <select id="product_check" name="product_check">
					<option value=''selected>구매수량:필수사항
			<%
			for(int i =1; i<10; i++){
				out.print("<option value='"+i+"'>"+i);
				
			}
			%>
			</select></li>
			<li><span class="amount">현재 재고 </span><input type='text' value='<%=list.getAmount() %>'class='amount' readonly></li>
			<li class='li_price'><span>총 가격 </span><input type='text' value=''class='price' readonly><b></b></li>
			
			<script>
			 $(document).ready(function(){
				$('#product_check').change(function(){
				 $("#product_check option:selected").each(function () {
					if($(this).val()== ''){ //바뀌지않는 기본 틀
					     $(".price").val('');//값 초기화
					     $("input.amount").val('<%=list.getAmount() %>');
						 $(".price").attr("disabled",true); //입력제한활성화 
					 }
					else{ //값 출력
						maxAmount = <%= list.getAmount()%>
						count = parseInt($(this).text());
						if(count > maxAmount){
							count = maxAmount;
						}
						price = count*<%=price %>;
						amount = <%=list.getAmount() %>-count;
						$(".price").val(price); //선택값 입력
						$("input.amount").val(amount); //선택값 입력
						$(".price").attr("disabled",true); //비활성화
						$("#product_box form ul li b").text(" 원");
					}
					});
				 
				 if($("#size").val()!="" && $("#product_check").val()!=""){
					 $(".product_btn").css("display","block");
				}
				 if($("#size").val()=="" || $("#product_check").val()==""){
					 $(".product_btn").css("display","none");
				}
				})
				$("#product_check option").addClass('selectclass');
				$('#size').change(function(){
					b =$("#product_check option:selected").val();
					a =$("#size option:selected").val();
					select=b =$("#product_check option:eq(0)").val();
				if(a!=''){
					$("#product_check option").removeClass('selectclass');
					
				}else{
					$("#product_check option").addClass('selectclass');
					$("#product_check option:eq(0)").attr({'selected':'selected'});
					 $(".product_btn").css("display","none");
				}
				}) 

			})
			</script>
			<li>
				<input type="hidden" name='category' value='1' class="category">
				<input type="hidden" name='product_no' value='<%=list.getProduct_no() %>'>
				<button class="cart product_btn" >장바구니</button>
				<button class="wish product_btn" >관심목록</button>
			</li>
			<li>
				<p><input type="submit" value="구매하기"  class="submit product_btn"></p>
			</li>
			<li>
			<p style='text-align:right;'>조회수 : <%=read_cnt %></p>
			</li>
			
		</ul>
		
	</form>
</div>

