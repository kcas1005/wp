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
<form action="/ex/hobby/modify" method="post">
<input type=hidden name=id value= '${hobbyDto.id }'>
<input type=hidden name=hobby value= '${hobbyDto.hobby }'>
id: ${hobbyDto.id }
이름:<input type=text name=modifyHobby value= '${hobbyDto.hobby }'><br>
<input type="submit" value=전송 >
</form>
<a href="/ex/hobby/selectAll">목록가기</a>
</body>
</html>