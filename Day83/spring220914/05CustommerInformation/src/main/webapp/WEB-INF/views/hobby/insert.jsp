<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/ex/hobby/insert" method="post">
<c:forEach items="${list }" var="id">
id:<input type=radio name=id value='${id }'>${id }<br>
</c:forEach>
hobby:<input type=text name=hobby ><br>
<input type="submit" value=전송 >
</form>

</body>
</html>