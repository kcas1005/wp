<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var result='${msg}';
if(result=='success'){
	alert('처리가 완료되었습니다.');
}
</script>
</head>
<body>
<h1>고객 정보</h1>
<table boarder="1" width="90%" id="customers">
	<tr>
	<th>id</th>
	<th>name</th>
	<th>height</th>
	<th>birthday</th>
	<th>hobbys</th>
	</tr>
	<c:forEach items="${list }" var="customerInfoVo">
	<tr>
		<td>${customerInfoVo.id }</td>
		<td><a href='/ex/customInfo/delete?id=${customerInfoVo.id}'>
		${customerInfoVo.name }</a></td>
		<td>${customerInfoVo.height}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
		value="${customerInfoVo.birthday }" /> </td>
		<td>
		<c:forEach items="${customerInfoVo.hobbys }" var="hobby">
		<a href='/ex/customInfo/hobbyDelete?id=${customerInfoVo.id}&hobby=${hobby}'>${hobby} </a>
		</c:forEach>
		</td>
	</tr>
	</c:forEach>
</table> 
<a href="/ex/customInfo/insert">고객추가</a>
</body>
</html>

