<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.vo.*" %>
<%@ page import ="com.hk.th.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">

function moveCart(proNum,category){
	location.href = "MyCartPro.jsp?proNum="+parseInt(proNum)+"&categoty="+parseInt(category);
}

</script>

</head>
<body>

<h2>메인</h2>
<a href="MyCart.jsp">마이페이지</a><a href="login.jsp">로그인</a>
<br>
<%
ArrayList<ProductVo> parr = new ArrayList<ProductVo>();
ProductDao pDao = new ProductDao();
parr = pDao.selectProductAll();

for(int i = 0 ; i < parr.size(); i++){
	out.print(parr.get(i).getProduct_no() +","+parr.get(i).getProduct_name()+",");
	out.print("<input type = 'button' value = '장바구니' onclick='moveCart("+parr.get(i).getProduct_no()+",1)'> <input type = 'button' value = '관심목록' onclick='moveCart("+parr.get(i).getProduct_no()+",2)'> <input type = 'button' value = '구매하기' onclick='moveCart("+parr.get(i).getProduct_no()+",3)'><br>");
}

%>


</body>
</html>