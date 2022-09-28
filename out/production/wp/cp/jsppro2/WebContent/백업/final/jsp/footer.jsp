<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style type="text/css">

footer {
	margin-top:10%;clear:both;
	text-align:center;
	min-width: 320px;
	
}
.info_company {
	padding: 6% 0%;
	height:15%;
	background: #f7f7f7;
}

.info_company ul {
	line-height: 12px;
}

.info_company li {
	font-size: 9px;
}

.info_company li.company {
	padding-bottom: 4px;
}

.info_company li.info {
	display: inline;
}

.info_company li.info_tel {
	display: inline;
	border-left: 1px solid #dfdfdf;
	padding-left: 4px;
}

.info_company {
	margin : 0px auto;
	float: center;
}
.info_company ul{
	padding : 10px;
	margin : 0px auto;
}
.info_company ul li {
	margin : 0px;
	padding : 0px;
	list-style:	none;
	font-size : 12px;
}
@media all and (min-width:768px){
.info_company {
	padding: 5% 0%;
	height:10%;
	background: #f7f7f7;
}

}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
	});
</script>
</head>
<body>
	<footer>
		<div class="info_company">
			<ul>
				<li class="company"><strong>COMPANY : 신식당(주) BUSINESS LICENSE : 201-86-24666 MALL-ORDER LICENSE : 제 2013-서울동대문-0499호</strong></li>
				<li class="info"> ADDRESS : 02438 서울특별시 동대문구 망우로21길 52 (휘경동) 춘태빌딩 C동 5층 TEL : 1899-0422 FAX : 02-2273-0870</li>
				<li class="info_tel">CPO : 신예진 (sinpig@naver.com) CONTACT US : sinpig@naver.com</li>
				<li>사업자등록번호 : 000-00-00000</li>
				<li>COPYRIGHT &#9400; 신식당 ALL RIGHTS RESERVED. HOSTING By SinRestaurant | DESIGNED By 신예진</li>
			</ul>
		</div>
	</footer>
</body>
</html>