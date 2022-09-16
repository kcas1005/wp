<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table boarder="1" width="90%" id="customers">
	<tr>
	<th>id</th>
	<th>name</th>
	<th>height</th>
	<th>birthday</th>
	</tr>
	<tr>
		<td>${customerDto.id }</td>
		<td>
		${customerDto.name }</td>
		<td>${customerDto.height}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
		value="${customerDto.birthday }" /> </td>	
	</tr>
	
</table>
<a href='/ex/customer/selectAll'>목록가기</a>
<a href='/ex/customer/delete?id=${customerDto.id }'>삭제</a>
<a href='/ex/customer/modify?id=${customerDto.id }'>수정</a>
</body>
</html>









