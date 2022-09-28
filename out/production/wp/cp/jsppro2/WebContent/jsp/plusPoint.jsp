<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.ifH2{
	font-size: 50px;
	padding: 15px;
	text-align: center;
}
.ifUl{
	padding: 15px;
}
.ifLi{
	font-size: 20px;
	color : #ff6e75;
	margin: 10px;
	padding: 10px;
	text-indent: 10px;
}
.ifIput{
	
}
.ifP{
text-align: center;
}
.ifBnt{
	width :100px;
	height : 40px;
	margin: 0 auto;
	background-color:#333;
	border: none;
	color: white;
}

</style>

<script type="text/javascript">

function check(){
	var BuyWay = document.getElementsByName('BuyWay');
	for(i = 0 ; i < BuyWay.length ; i++){
		if(BuyWay[i].checked == true){
			if(BuyWay[i].value != "easy"){
				return false;
			}
		}
	}
}

</script>
</head>
<body>
<form action="plusPointPro.jsp" onsubmit="return check()">
	<h2 class ="ifH2">결제수단 선택</h2>
	<ul class ="ifUl">
		<li class = "ifLi"><input type="radio" name="BuyWay" class = "ifIput" value = "cradit" checked="checked"> 신용/체크 카드</li>
		<li class = "ifLi"><input type="radio" name="BuyWay" class = "ifIput" value = "phone"> 휴대폰</li>
		<li class = "ifLi"><input type="radio" name="BuyWay" class = "ifIput" value = "ge"> 계좌이체</li>
		<li class = "ifLi"><input type="radio" name="BuyWay" class = "ifIput" value = "easy"> 간편(테스트용)</li>
	</ul>
	<p class = "ifP"><input type="submit" value = "다음" class = "ifBnt"></p>
</form>

	
</body>
</html>