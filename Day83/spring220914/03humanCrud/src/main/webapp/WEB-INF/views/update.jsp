<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
update
<form action="/ex/update" method="post">
이름<input type="text" name=name value="${humanDto.name }" readonly>
나이<input type="text" name=age value="${humanDto.age }">
키<input type="text" name=height value="${humanDto.height }">

생일<input type="datetime-local" name=birthday
 value='<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value= "${humanDto.birthday }"/>'>

<!-- 생일<input type="datetime-local" name=birthday value="2000-01-01T12:13:22"> -->
<input type="submit" value="수정">
</form>
</body>
</html>
