<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.hk.th.dao.*" %>
<%@ page import="com.hk.th.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/MyMemberInPo.css" type = "text/css" rel= "stylesheet">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<%
if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
	response.sendRedirect("login.jsp");
}
String id = (String)session.getAttribute("id");
%>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

function update(){
	
}


$(document).ready(function(){
	
	$(".pPoint").click(function(){
		location.href="plusPointPro.jsp"
	});
	
	$('.mypageManu li:eq(2)').css("background-color","#ff6e75");
	$('.mypageManu li:eq(2)').find("a").css("color","white");

	
	$('.selCnt').change(function() {
		selBack = $(this).children("option:selected").val();
		$("#emBack").val(selBack);
	});
	
	
	
	
	
	
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","#ff6e75");
		$(this).find("a").css("color","white");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
		$('.mypageManu li:eq(2)').css("background-color","#ff6e75");
		$('.mypageManu li:eq(2)').find("a").css("color","white");
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
function a(){
	document.getElementById("post").value = "2323";
}
function openDaumZipAddress() {

	new daum.Postcode({

		oncomplete:function(data) {
			jQuery("#zonecode").val(data.zonecode);
			jQuery("#address").val(data.address);
			jQuery("#address_etc").focus();
			
			console.log(data);
			
			$("#post").val(data.zonecode);
			$("#address").val(data.address);
			$("#address2").val("");
			
		}

	}).open();

}


</script>
</head>
<body>

<%
//선언
MemberDao memDao = new MemberDao();
MemberVo memVo = new MemberVo();
memVo = memDao.selectMember(id);

%>


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
			<li><a href = "#">게시글</a></li>
		</ul>
		<div class = "mypageCountent Inpo" id="memInpo">
		<form action="changeInpo.jsp" method="get">
			<table>
				<%
				String name = memVo.getName();
				String mid = memVo.getId();
				String pw = memVo.getPw();
				String email = memVo.getEmail();
				int post_num = memVo.getPost_num();
				String address = memVo.getAddress();
				String address2 = memVo.getAddress2();
				int phone = memVo.getPhone();
				String message = memVo.getMessage();
				%>
				<tr>
					<td> </td>
					<td></td>
				</tr>
				<tr>
					<td>이름 : </td>
					<td><%= name %></td>
				</tr>
				<tr>
					<td>아이디 : </td>
					<td><%= mid %></td>
				</tr>
				<tr>
					<td>비밀번호 : </td>
					<td><input type="text" name = "pw" value = "<%= pw %>"></td>
				</tr>
				<tr>
					<td>이메일 : </td>
					<td>
						<input type="text" name = "email1" value = "<%= email.substring(0,email.indexOf("@")) %>">@
						<input type="text" id = "emBack"name = "email2" value = "<%=email.substring(email.indexOf("@")+1,email.length()) %>">
						<select name='em' class="selCnt">
							<option value=" ">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="nate.com" >nate.com</option>
							<option value="dreamwiz.com" >dreamwiz.com</option>
							<option value="yahoo.co.kr" >yahoo.co.kr</option>
							<option value="empal.com" >empal.com</option>
							<option value="unitel.co.kr" >unitel.co.kr</option>
							<option value="gmail.com" >gmail.com</option>
							<option value="korea.com" >korea.com</option>
							<option value="chol.com" >chol.com</option>
							<option value="paran.com" >paran.com</option>
							<option value="freechal.com" >freechal.com</option>
							<option value="hanmail.net" >hanmail.net</option>
							<option value="hotmail.com" >hotmail.com</option> 
						</select>
					</td>
				</tr>
				<tr>
					<td>우편번호 : </td>
					<td><input type="text" name = "post_num" id="post" value = "<%= post_num %>"><input type="button" class = "adrbnt" value= "주소찾기"onclick="openDaumZipAddress()"></td>
				</tr>
				<tr>
					<td>주소 : </td>
					<td><input type="text" style="width:500px" name = "address" id="address" value = "<%= address %>"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" style="width:500px" placeholder="상세입력" name = "address2" id="address2" value = "<%= address2 %>"></td>
				</tr>
				<tr>
					<td>핸드폰번호 : </td>
					<td><input type="text" style="width:50px" maxlength="3" name = "phon1" value = "0<%= Integer.toString(phone).substring(0,2) %>" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"> - 
					<input type="text" style="width:50px" maxlength="8" name = "phon2" value = "<%= Integer.toString(phone).substring(2,6) %>" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"> - 
					<input type="text" style="width:50px" maxlength="8" name = "phon3" value = "<%= Integer.toString(phone).substring(6,10) %>" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
				</tr>
				<tr>
					<td>배송메시지 : </td>
					<td><textarea name = "message" style="width:400px" ><%= message %></textarea></td>
				</tr>
			</table>
			<div class = "InpoButton">
				<input type='submit' value = '수정' onclick="update()" class="subButton"><input type='button' value = '탈퇴' onclick="deleteMember()" class="delButton">
			</div>
		</form>
		</div>
	</div>
</div>




<!-- 푸터 -->
<jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
</html>