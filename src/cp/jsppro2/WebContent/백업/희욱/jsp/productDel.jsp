<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="shopping.dao.ProductDao" %>
<%@ page import="shopping.dao.RecordsDao" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">
alert("a");
</style>
</head>
<body>
<%
	int no = Integer.valueOf(request.getParameter("no"));
	ProductDao pd = new ProductDao();
	RecordsDao rd= new RecordsDao();

	if(rd.checkStateByProductNo(no) == 0){
		pd.delProduct(no);
		response.sendRedirect("manage_product.jsp");
	}
	else
		response.sendRedirect("manage_product.jsp?err=1");
%>
</body>
</html>