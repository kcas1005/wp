<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#cje").click(function(){
		if($("#cje").prop("checked")){
			alert($("#cje").val());
			alert($("#cje").attr("value1"));
		}
	});
});

</script>
</head>
<body>

<form action="testPro.jsp" method="get">
<input type='checkbox' value = '0' value1 = '1' value2 = '2' id = 'cje' name='ccc'>
<input type="submit" value = "전송">
</form>


</body>
</html>