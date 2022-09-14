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
	</tr>
	<c:forEach items="${list }" var="customerDto">
	<tr>
		<td>${customerDto.id }</td>
		<td><a href='/ex/customer/read?id=${customerDto.id}'>
		${customerDto.name }</a></td>
		<td>${customerDto.height}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
		value="${customerDto.birthday }" /> </td>	
	</tr>
	</c:forEach>
</table>
<a href="/ex/customer/insert">고객추가</a>
</body>
</html>

