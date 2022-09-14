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
<h1>하비</h1>
<table border="1" width="90%" id="hobbyDto">
	<tr>
	<th>id</th>
	<th>name</th>
	<th>select</th>
	</tr>
	<c:forEach items="${list }" var="hobbyDto">
	<tr>
		<td>${hobbyDto.id }</td>
		<td>${hobbyDto.hobby }</td>
		<td><a href='/ex/hobby/read?id=${hobbyDto.id}&hobby=${hobbyDto.hobby}'>보기</a>
		 </td>	
	</tr>
	</c:forEach>
</table>
<a href="/ex/hobby/insert">취미추가</a>
</body>
</html>

