<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.MemberVo" %>
<%@ page import="vo.CartVo" %>
<%@ page import="vo.ProductVo" %>

<%@ page import="dao.CartDao" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="dao.MemberDao" %>

<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구매</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/purchase.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="../js/purchase.js"></script>
</head>
<body>
<%
	
	/* //id가 null이거나 입력되지 않은 경우 돌려보냄.
	if(request.getParameter("id") == null){
		response.sendRedirect("ptest.jsp");
	}
	else if(request.getParameter("id").equals("")){
		response.sendRedirect("ptest.jsp");
	} 
	String id = request.getParameter("id"); 
	*/
	
	if(session.getAttribute("id")==null){//id가 null인 경우 돌려보냄
		response.sendRedirect("main.jsp");
	}
	else{
		String id = (String)session.getAttribute("id");
		MemberDao md = new MemberDao();
		MemberVo mem = new MemberVo();
		if(!md.isMem(id)){//아이디가 회원이 아닌 경우 돌려보냄
			response.sendRedirect("main.jsp");
		}
		else{
			mem = md.searchMemForPurchase(id);
			
			//email과 전화번호는 표기의 편의를 위해 따로 변수를 나눔.
			String phonefull = Integer.toString(mem.getPhone());//맨 앞 0은 지워짐을 주의
			
			int phone1=Integer.valueOf(phonefull.substring(0,2)),//int이므로 맨 앞 0은 지워짐을 주의
			phone2=Integer.valueOf(phonefull.substring(2,6)),
			phone3=Integer.valueOf(phonefull.substring(6,10));
			
			String emailfull = mem.getEmail(),
			
			email1 = emailfull.substring(0,emailfull.indexOf("@")),//@이전까지
			email2 = emailfull.substring(emailfull.indexOf("@")+1,emailfull.length());//@이후
					
			//결제할 상품 관련 설정
			ProductDao pd = new ProductDao();
			String dir_amount=null, dir_no=null;
			
			CartDao cd = new CartDao();
			ArrayList<CartVo> cartArr = new ArrayList<CartVo>();
			
			if(request.getParameter("product_check")!=null && request.getParameter("product_no")!=null){//직접 구매인 경우
				dir_amount = request.getParameter("product_check");
				dir_no = request.getParameter("product_no");
				CartVo dirCv = new CartVo();
				
				dirCv.setProduct_no(Integer.valueOf(dir_no));
				dirCv.setPurchase(Integer.valueOf(dir_amount));
				
				cartArr.add(dirCv);
			}
			else{//장바구니를 통한 구매일 경우
				cartArr = cd.selectById(id);
			}
		
%>
<jsp:include page="header.jsp"></jsp:include>

<div class="box">
	<form action="purchasepro.jsp" method="post" id="frm">
	<input type="hidden" name="id" value=<%=id %>>
	<h2 id="title">배송 정보</h2>
		<table>
			<tr class="roof">
				<td class="ltd">배송지 선택</td>
				<td>
					<div id="chkInfo">
						<input type="radio" name="info" value="mem" checked>회원 정보와 동일
						&nbsp; 
						<input type="radio" name="info" value="new">새로운 배송지
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="ltd">받으시는 분</td>
				<td>
					<input type="text" name="name" id="name" value="<%= mem.getName() %>">
				</td>
			</tr>
			
			<tr>
				<td class="ltd">주소</td>
				<td>
					<input type="text" id="postcode" name="post_num" readonly value="<%= mem.getPost_num() %>">
					<input type="button" onclick="Postcode()" value="우편번호 찾기"><br><br>
					<input type="text" id="address" name="address" value="<%= mem.getAddress() %>">기본주소<br><br>
					<input type="text" id="detailAddress" name="address2" value="<%= mem.getAddress2() %>">상세주소
				</td>
			</tr>
			
			<tr>
				<td class="ltd">휴대전화</td>
				<td>
					<select name="phone1" id="phone1">
						<option value="010"
							<%
							if(phone1==10)
								out.print("selected");
							%>
						>010</option>
	                    <option value="011"
	                    	<%
							if(phone1==11)
								out.print("selected");
							%>
	                    >011</option>
	                    <option value="016"
	                    	<%
							if(phone1==16)
								out.print("selected");
							%>
	                    >016</option>
	                    <option value="017"
	                    	<%
							if(phone1==17)
								out.print("selected");
							%>
	                    >017</option>
	                    <option value="018"
	                    	<%
							if(phone1==18)
								out.print("selected");
							%>
	                    >018</option>
	                    <option value="019"
	                    	<%
							if(phone1==19)
								out.print("selected");
							%>
	                    >019</option>
					</select>
					-
					<input type="text" name="phone2" id="phone2" value="<%= phone2 %>">
					-
					<input type="text" name="phone3" id="phone3" value="<%= phone3 %>">
				</td>
			</tr>
			
			<tr>
				<td class="ltd">이메일</td>
				<td>
					<input type="text" name="email1" id="email1" value="<%= email1 %>">
					@
					<input type="text" name="email2" id="email2" value=<%= email2 %>>
					<select id="mail">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="hotmail.com">hotmail.com</option>
						<option value="yahoo.com">yahoo.com</option>
						<option value="empas.com">empas.com</option>
						<option value="korea.com">korea.com</option>
						<option value="dreamwiz.com">dreamwiz.com</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="ltd">배송 메시지</td>
				<td>
						<!-- message는 null 가능하므로 null일 경우 공백처리 해줌. -->
					<% 
						if(mem.getMessage() != null){
					%>
					<textarea name="message" id="msg" cols="70" rows="4"><%= mem.getMessage() %></textarea>
					<% 
						}
						else{
					%>
						<textarea name="message"></textarea>
					<%
						}
					%>
					
				</td>
			</tr>
		</table>
		<br><br>
		<h2 id="title">결제 예정금액</h2>
		<%
			if(dir_amount!= null && dir_no != null)
				out.print("<input type='hidden' value='y' name='direct'>");
		%>
		<table id="table2">
			<tr class="roof">
				<th>선택</th>
				<th>상품 리스트</th>
				<th>금액</th>
				<th>개수</th>
				<th>총 금액</th>
			</tr>
			
			<%
				int wholePrice=0;
				for(int i=0; i<cartArr.size() ;i++){
					int no = cartArr.get(i).getProduct_no();
					int amount = cartArr.get(i).getPurchase();
			%>
			<tr>
				<td><input type="checkbox" checked name="chkbox"
					<%
						out.print(" value="+i);
					%>
				></td>
				<td><a href="content.jsp?reviewpage=1&reviewcategory=0&product_no=<%= no %>" target="_blank"> 
				<%= pd.selectNameByNo(no) %> </a></td>
				<td class="price"><%= pd.selectPriceByNo(no) %></td>
				<td>
					<input type="hidden" value=<%= pd.amount(no) %> class="amountLimit">
					<input type="hidden" value=<%= no %> name="product_no">
					<select class="amountSel" name="purchaseAmount">
						<option value="1"
							<%
								if(amount == 1)
									out.print(" selected");
							%>
						>1</option>
						<option value="2"
							<%
								if(amount == 2)
									out.print(" selected");
							%>
						>2</option>
						<option value="3"
							<%
								if(amount == 3)
									out.print(" selected");
							%>
						>3</option>
						<option value="4"
							<%
								if(amount == 4)
									out.print(" selected");
							%>
						>4</option>
						<option value="5"
							<%
								if(amount == 5)
									out.print(" selected");
							%>
						>5</option>
						<option value="6"
							<%
								if(amount == 6)
									out.print(" selected");
							%>
						>6</option>
						<option value="7"
							<%
								if(amount == 7)
									out.print(" selected");
							%>
						>7</option>
						<option value="8"
							<%
								if(amount == 8)
									out.print(" selected");
							%>
						>8</option>
						<option value="9"
							<%
								if(amount == 9)
									out.print(" selected");
							%>
						>9</option>
					</select>
				</td>
				<td class="mulPrice">
					<%
						wholePrice += amount * pd.selectPriceByNo(no);
						out.print(amount * pd.selectPriceByNo(no)); 
					%>
				</td>
				
			</tr>
			<%
				}
			%>
		</table>
		
		<div id="priceResult">
			<p>
				<!-- 3자리마다 , 표시하는 기능 있어도 좋을 듯 -->
				총 주문 금액 : <span id="finalPrice"><span id="finalMny"><%=wholePrice %></span>원</span>
			</p>
		</div>
		
		<div id="btns">
			<input type="button" onclick="reg()" value="최종 구매">
			&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="back()" value="취소">
		</div>
		
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- 구매 시
1. 해당 id에 해당 상품들에 해당하는 row들을 지운다.
2. records 테이블에 값을 추가해준다.(초기 배송상태는 주문완료?)
 -->
<script type="text/javascript">
$(function (){
	//배송지 선택 radio button 선택 시 input:text 변경
	$("input[name='info']").change(function(){

		if($(this).val() == "new"){
			$("#name").val("");//이름
			
			$("#postcode").val("");//우편번호
			$("#address").val("");//기본주소
			$("#detailAddress").val("");//상세주소
			
			$("#phone2").val("");//휴대전화2
			$("#phone3").val("");//휴대전화3
			
			$("#email1").val("");//이메일1
			$("#email2").val("");//이메일2
			
			$("#msg").val("");//배송 메시지 
		}

		else if($(this).val() == "mem"){
			$("#name").val("<%= mem.getName() %>");//이름
			
			$("#postcode").val("<%= mem.getPost_num() %>");//우편번호
			$("#address").val("<%= mem.getAddress() %>");//기본주소
			$("#detailAddress").val("<%= mem.getAddress2() %>");//상세주소
			
			$("#phone1").val("0"+<%=phone1%>).attr("selected","selected");//폰 셀렉트 박스 회원 정보에 맞게 변경
			$("#phone2").val(<%= phone2 %>);//휴대전화2
			$("#phone3").val(<%= phone3 %>);//휴대전화3
			
			$("#email1").val("<%= email1 %>");//이메일1
			$("#email2").val("<%= email2 %>");//이메일2
			$("#mail option:eq(0)").attr("selected","selected");//이메일 셀렉트 박스 '직접 입력'으로 변경
			
			// \n가 포함된 값일 경우 \n을 다른 값으로 바꾼 뒤 다시 바꾸는 작업이 필요하다. <br>로 바꾸자
			<% 
				String msg = mem.getMessage().replaceAll("\n","<br>");
				
			%>
			var msg = "<%=msg%>";
			
			msg = replaceAll(msg,"<br>","\n");
			$("#msg").val(msg);//배송 메시지 
		}
	});
});
</script>
<%
		}
	}
%>
</body>
</html>