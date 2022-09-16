<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--3자리 마다 컴마로 표시한 숫자  -->
<fmt:formatNumber value="1234567.89" />
<!--groupingUsed="false" 부분을 추가하면 3자리 마다 컴마로 표시 하지 않음 -->
<fmt:formatNumber value="1234567.89" groupingUsed="false"/>
<!--숫자를 퍼센트로 출력  1이 100% 이다. -->    
<fmt:formatNumber value="0.5" type="percent" />
<!-- 돈단위를 넣어서 출력  -->
<fmt:formatNumber value="10000" type="currency" />
<!-- 원하는 돈표시 선택  -->
<fmt:formatNumber value="10000" type="currency" currencySymbol="$"/>

<!--
 패턴대로 출력하는 방식 
#해당 자리가 없으면 출력하지 않음 0 해당 자리가 없으면 0으로 출력
  -->
<fmt:formatNumber value="1234567.8912345" pattern="#,#00.0#"/>
<fmt:formatNumber value="1234567.8912345" pattern=".000"/>
<fmt:formatNumber value="1234567.10" pattern=".00"/>
<fmt:formatNumber value="1234567.10" pattern=".0#"/>
<fmt:formatNumber value="1234567.0" pattern=".0"/>
<fmt:formatNumber value="1234567.0" pattern=".#"/>

<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${now }" pattern="yyyy"/>
<fmt:formatDate value="${now }" pattern="yyyy년 MM월 dd일  HH시 mm ss"/>

</body>
</html>












