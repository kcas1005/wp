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
	<th>hobby</th>
	</tr>
	<tr>
		<td>${hobbyDto.id }</td>
		<td>
		${hobbyDto.hobby}</td>
	</tr>
	
</table>
<a href='/ex/hobby/selectAll'>목록가기</a>
<a href='/ex/hobby/delete?id=${hobbyDto.id }&hobby=${hobbyDto.hobby }'>삭제</a>
<a href='/ex/hobby/modify?id=${hobbyDto.id }&hobby=${hobbyDto.hobby }'>수정</a>
</body>
</html>









