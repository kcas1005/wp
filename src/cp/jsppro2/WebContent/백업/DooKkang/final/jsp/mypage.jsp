<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">

function changeCount(){
	var maxVal = 
	cartIn.val() = cartIn.val()*$(this).val();
	$(document).ready(function(){
		$("mypageCountentResult h3").html() = "총 금액 : "+ cartIn.val();
	})
}

function delCart(proNum){
	location.href="delCartPro.jsp?product="+proNum;
}
function delList(proNum){
	location.href="delListPro.jsp?product="+proNum;
}
function delOrder(proNum){
	location.href="delOrderPro.jsp?product="+proNum;
}

$(document).ready(function(){
	$("#Cart").css("display","none");
	$("#likeList").css("display","none");
	$("#order").css("display","none");
	$("#memInpo").css("display","none");
	<%
		String a = "";
		if(request.getParameter("pagename") != null){
			a = request.getParameter("pagename");
		}else{
			a = "Cart";
		}
	%>
	$("#<%=a%>").css("display","inline-block");	
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","pink");
		$(this).find("a").css("color","black");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
	});
	//소메뉴 클릭
	$(".mypageManu li a:eq(0)").click(function(){
		$("#Cart").css("display","inline-block");
		$("#likeList").css("display","none");
		$("#order").css("display","none");
		$("#memInpo").css("display","none");
	});
	$(".mypageManu li a:eq(1)").click(function(){
		$("#Cart").css("display","none");
		$("#likeList").css("display","inline-block");
		$("#order").css("display","none");
		$("#memInpo").css("display","none");
	});
	$(".mypageManu li a:eq(2)").click(function(){
		$("#Cart").css("display","none");
		$("#likeList").css("display","none");
		$("#order").css("display","none");
		$("#memInpo").css("display","inline-block");
	});
	$(".mypageManu li a:eq(3)").click(function(){
		$("#Cart").css("display","none");
		$("#likeList").css("display","none");
		$("#order").css("display","inline-block");
		$("#memInpo").css("display","none");
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
memVo = memDao.selectMember("kjo4202");

OrderDao ordDao = new OrderDao();
OrderVo ordVo = new OrderVo();


ArrayList<CartVo> cvo = new ArrayList<CartVo>();
ArrayList<CartVo> lvo = new ArrayList<CartVo>();
ArrayList<OrderVo> ovo = new ArrayList<OrderVo>();
cvo = mpDao.selectCart("kjo4202", 1);
lvo = mpDao.selectCart("kjo4202", 2);
ovo = ordDao.selectOrder("kjo4202");

//페이지 수
int maxCartPageNum = 0;
int cartcount = mpDao.maxCartPage("kjo4202", 1);
maxCartPageNum = (cartcount/10) + 1;

int maxListPageNum = 0;
int listcount = mpDao.maxCartPage("kjo4202", 2);
maxListPageNum = (listcount/10) + 1;

int maxOrderPageNum = 0;
int ordercount = ordDao.maxOrderPage("kjo4202");
maxOrderPageNum = (ordercount/10) + 1;


//해당페이지
int CartPag = 1;
if(request.getParameter("cpage") != null){
	CartPag = Integer.parseInt(request.getParameter("cpage"));
}
CartPag = ((CartPag - 1) * 10);

int ListPag = 1;
if(request.getParameter("lpage") != null){
	ListPag = Integer.parseInt(request.getParameter("lpage"));
}
ListPag = ((ListPag - 1) * 10);

int OrderPag = 1;
if(request.getParameter("opage") != null){
	OrderPag = Integer.parseInt(request.getParameter("opage"));
}
OrderPag = ((OrderPag - 1) * 10);
%>


<!-- 해더 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>


<div class = "mypageIn">
	<h2 class = "mypageName">마이페이지</h2>
	<div class = "mySimpleInpo">
		<p>어서오세요 "회원아이디" 님!</p>
		<p>포인트 : 12030123 <img src = "../image/plus.png"></p>
	</div>
	<div class = "mypageMain">
		<ul class = "mypageManu">
			<li><a href = "#">장바구니</a></li>
			<li><a href = "#">관심목록</a></li>
			<li><a href = "#">회원정보</a></li>
			<li><a href = "#">주문조회</a></li>
		</ul>
		<div class = "mypageCountent" id="Cart">
			<table>
				<tr>
					<td>선택</td>
					<td>상품리스트</td>
					<td>금액</td>
					<td>개수</td>
					<td>삭제</td>
				</tr>
				<tr>
					<td>전체 선택<input type='checkbox' name='product' value='all' class = "cartCheckall"></td>
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
						int selNum= cvo.get(Cartar).getPurchase();
						out.print("<tr>");
						out.print("<td><input type='checkbox' name='product' class = 'cartIn' value='"+pri+"'></td>");
						out.print("<td>"+proNum+"</td>");
						out.print("<td>"+pri+"</td>");
						out.print("<td>");
						out.print("<select name='pu' class='count' onchange='changeCount()'>");
						
						for(int j = 1; j < 10; j++){
							if(j == selNum){
								out.print("<option value='"+j+"'  selected='selected'>"+j+"</option>");
								j++;
							}
							out.print("<option value='"+j+"'>"+j+"</option>");
						}
						out.print("</select>");
						out.print("</td>");
						out.print("<td><input type='button' name='product' class = 'cartIn' value = '삭제' onclick='delCart("+proNum+")'></td>");
						out.print("</tr>");
						Cartar++;
					}
				%>
			</table>
			<div class = "cartPaging">
				<%
				for(int i = 1 ; i <= maxCartPageNum; i++)
				{
					out.print("<a href='mypage.jsp?pagename=Cart&cpage="+i+"'>"+i+"</a>");
				}
				%>
			</div>
			<div class = "mypageCountentResult">
				<h3>총 금액 : 0</h3><input type="button" value = "구매">
			</div>
		</div>
		<div class = "mypageCountent" id="likeList">
			<table>
				<tr>
					<td>상품번호</td>
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
						out.print("<td>"+proName+"</td>");
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
					out.print("<a href='mypage.jsp?pagename=likeList&lpage="+i+"'>"+i+"</a>");
				}
				%>
			</div>
		</div>
		<div class = "mypageCountent" id="memInpo">
			<form action="#" method="get">
				<table>
					<%
					String name = memVo.getName();
					String id = memVo.getId();
					String pw = memVo.getPw();
					String email = memVo.getEmail();
					int post_num = memVo.getPost_num();
					String address = memVo.getAddress();
					String address2 = memVo.getAddress2();
					int phone = memVo.getPhone();
					String message = memVo.getMessage();
					%>
					<tr>
						<td>이름 : </td>
						<td><%= name %></td>
					</tr>
					<tr>
						<td>아이디 : </td>
						<td><%= id %></td>
					</tr>
					<tr>
						<td>비밀번호 : </td>
						<td><input type="text" name = "pw" value = "<%= pw %>"></td>
					</tr>
					<tr>
						<td>이메일 : </td>
						<td><input type="text" name = "pw" value = "<%= email %>"></td>
					</tr>
					<tr>
						<td>우편번호 : </td>
						<td><input type="text" name = "pw" value = "<%= post_num %>"></td>
					</tr>
					<tr>
						<td>주소 : </td>
						<td><input type="text" name = "pw" value = "<%= address %>"></td>
					</tr>
					<tr>
						<td>상세주소 : </td>
						<td><input type="text" name = "pw" value = "<%= address2 %>"></td>
					</tr>
					<tr>
						<td>핸드폰번호 : </td>
						<td><input type="text" name = "pw" value = "<%= phone %>"></td>
					</tr>
					<tr>
						<td>배송메시지 : </td>
						<td><input type="text" name = "pw" value = "<%= message %>"></td>
					</tr>
				</table>
				<input type='submit' name='product' value = '수정'>
			</form>
		</div>
		<div class = "mypageCountent" id="order">
			<table>
				<tr>
					<td>상품번호</td>
					<td>상품명</td>
					<td>배송상태</td>
					<td>주문일</td>
					<td>삭제</td>
				</tr>
				<%
					int orderar = 0;
					orderar += OrderPag;
					for(int i = 0 ; i < 10; i++){
						if(orderar >= ordercount){
							break;
						}
						int curpronum = ovo.get(orderar).getProduct_no();
						proVo = proDao.selectProductId(curpronum);
						int proNum = proVo.getProduct_no();
						String proName = proVo.getProduct_name();
						String p_state = ovo.get(orderar).getState();
						String p_date = ovo.get(orderar).getP_date();
						out.print("<tr>");
						out.print("<td>"+proNum+"</td>");
						out.print("<td>"+proName+"</td>");
						out.print("<td>"+p_state+"</td>");
						out.print("<td>"+p_date+"</td>");
						out.print("<td><input type='button' name='product' class = 'cartIn' value = '삭제' onclick='delOrder("+proNum+")'></td>");
						out.print("</tr>");
						orderar++;
					}
				%>
			</table>
			<div class = "cartPaging">
				<%
				for(int i = 1 ; i <= maxOrderPageNum; i++)
				{
					
					out.print("<a href='mypage.jsp?pagename=order&opage="+i+"'>"+i+"</a>");
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