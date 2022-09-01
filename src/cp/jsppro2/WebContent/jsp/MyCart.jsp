<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-sacle=1"charset="utf-8">
<title>Insert title here</title>
<style>
header #header ul.menu>li:nth-child(3)>a{
background: #fff; color:#ff6e75;}
</style>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/myCart.css" type = "text/css" rel= "stylesheet">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<style type="text/css"> 
.list{
	text-align: left;	
}
</style>
<%
if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
	response.sendRedirect("login.jsp");
}
String id = (String)session.getAttribute("id");
    
	//선언
CartDao mpDao = new CartDao();
ProductVo proVo = new ProductVo();
ProductDao proDao = new ProductDao();

MemberDao memDao = new MemberDao();
MemberVo memVo = new MemberVo();
memVo = memDao.selectMember(id);

ArrayList<CartVo> cvo = new ArrayList<CartVo>();

cvo = mpDao.selectCart(id, 1);
//페이지 수
int maxCartPageNum = 0;
int cartcount = mpDao.maxCartPage(id, 1);
maxCartPageNum = (cartcount/10) + 1;

//해당페이지
int CartPag = 1;
if(request.getParameter("cpage") != null){
	CartPag = Integer.parseInt(request.getParameter("cpage"));
}
int selPag = CartPag;
CartPag = ((CartPag - 1) * 10);


%>
<script type="text/javascript">

$(document).ready(function(){
	var htext = 0;
	
	$(".delch").click(function(){
		conf = confirm("정말 삭제 하시겠습니까");
		if(conf == true){
			$(".CartForm").attr("action","deleteCheck.jsp?cate = 1");
			$(".CartForm").submit();
		}else{
			
		}
		
	});
	
	$(".pPoint").click(function(){
		   $(".ifff").fadeIn();
		   $(".close").fadeIn();
	       //location.href="plusPointPro.jsp"
	   });
	$("button.close").click(function(){
		$(".ifff").fadeOut();
		$(".close").fadeOut();
		location.reload();
	})
	
	$("#allCheck").click(function(){
		 if($("#allCheck").prop("checked")) {
			 htext = 0;
			 $("input[type=checkbox]").prop("checked",true);
			 $('input:checkbox[name="pcheck"]').each(function() {
			      if(this.checked){
			    	  htext += parseInt($(this).attr("value1"));
			      }
			 });
		 }else { 
			 $("input[type=checkbox]").prop("checked",false);
			 $('input:checkbox[name="pcheck"]').each(function() {
			      if(this.checked == false){
			    	  htext -= parseInt($(this).attr("value1"));
			      }
			 });
		 }
		 
		 $(".mypageCountentResult h3").html("총 금액 : " + htext);
	});
	
	
	$('.selCnt').change(function() {
		pronum = $(this).val();
		selcnt = $(this).children("option:selected").text();
		location.href="MyCart.jsp?selcnt="+selcnt+"&pronum="+pronum+"&cpage="+<%=selPag%>;
	});
	
	
	
	$('input:checkbox[name=pcheck]').change(function() {
		if($(this).is(":checked")){
			htext += parseInt($(this).attr("value1"));
		}else{
			htext -= parseInt($(this).attr("value1"));
			$("#allCheck").prop("checked",false);
		}
		$(".mypageCountentResult h3").html("총 금액 : " + htext);
	});
	
	
	$('.mypageManu li:eq(0)').css("background-color","#ff6e75");
	$('.mypageManu li:eq(0)').find("a").css("color","white");
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","#ff6e75");
		$(this).find("a").css("color","white");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#333");
		$('.mypageManu li:eq(0)').css("background-color","#ff6e75");
		$('.mypageManu li:eq(0)').find("a").css("color","white");
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
<iframe src="plusPoint.jsp" scrolling="no" width="500px" height="500px" class = "ifff"></iframe>
<button class='close'>닫기</button>
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
			<li><a href = "#">작성글</a></li>
		</ul>
		<div class = "mypageCountent" id="Cart">
			<form action="che.jsp" method="get" class = "CartForm">
				<table>
					<tr>
						<td><input type='checkbox' value='all' id="allCheck"></td>
						<td>상품리스트</td>
						<td></td>
						<td>금액</td>
						<td>개수</td>
					</tr>
					<%
						int Cartar = 0;
						Cartar += CartPag;
						for(int i = 0 ; i < 10; i++){
							if(Cartar >= cartcount){
								break;
							}
							int curpronum = cvo.get(Cartar).getProduct_no();
							proVo = proDao.selectProductId(curpronum);
							int proNum = proVo.getProduct_no();
							int pri = proVo.getPrice();
							String proName = proVo.getProduct_name();
							int selNum= cvo.get(Cartar).getPurchase();
							
							if(request.getParameter("selcnt") != null){
								int pron = Integer.parseInt(request.getParameter("pronum"));
								if(proNum == pron){
									selNum = Integer.parseInt(request.getParameter("selcnt"));
									int proAmount = proDao.selectAmountPro(pron);
									if(selNum > proAmount){
										selNum = proAmount;
									}
									mpDao.setPur(id,pron,selNum);
								}
							}
							
							int maxpri = pri * selNum;
							out.print("<tr>");
							if(proDao.selectAmountPro(proNum) != 0){
								out.print("<td><input type='checkbox' name='pcheck' class ='cartIn' value1='"+maxpri+"' value='"+proNum+"'></td>");
							}else{
								out.print("<td>품절</td>");
							}
							out.print("<td><a href='#'><img src='../image/"+proVo.getProduct_img()+"' width='100px'></a></td>");
							out.print("<td class='list'><a href='content.jsp?product_no="+proNum+"&reviewpage=1&reviewcategory=0'>"+proName+"</a></td>");
							out.print("<td>"+pri+"</td>");
							out.print("<td>");
							out.print("<select name='pu' class='selCnt'>");
							
							for(int j = 1; j < 10; j++){
								if(j == selNum){
									out.print("<option value='"+proNum+"'selected='selected'>"+j+"</option>");
									j++;
								}
								out.print("<option value='"+proNum+"'>"+j+"</option>");
							}
							out.print("</select>");
							out.print("</td>");
							out.print("</tr>");
							Cartar++;
						}
					%>
				</table>
				<input type="hidden" name="cate" value="1">
				<div class = "cartPaging">
					<%
					for(int i = 1 ; i <= maxCartPageNum; i++)
					{
						out.print("<a href='MyCart.jsp?cpage="+i+"'>"+i+"</a>");
					}
					%>
				</div>
				<div class = "mypageCountentResult">
					<h3>총 금액 : 0</h3><input type="submit" value = "구매"><input type='button' name='product' class = 'delch' value = '삭제'>
				</div>
			</form>
		</div>
	</div>
</div>




<!-- 푸터 -->
<jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
</html>