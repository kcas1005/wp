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
<form action="/ex/customer/modify" method="post">
<input type=hidden name=id value= '${customerDto.id }'>
id: ${customerDto.id }
이름:<input type=text name=name value= '${customerDto.name }'><br>
키:<input type=text name=height value= '${customerDto.height }' ><br>
생일:<input type="datetime-local" name=birthday
 value='<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value= "${customerDto.birthday }"/>'><br>
<input type="submit" value=전송 >
</form>
<a href="/ex/customer/selectAll">목록가기</a>
</body>
</html>