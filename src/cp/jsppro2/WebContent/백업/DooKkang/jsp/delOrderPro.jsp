<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.hk.th.dao.*" %>
<%@ page import="com.hk.th.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id = (String)session.getAttribute("id");
	int proNum = 0;
	proNum = Integer.parseInt(request.getParameter("product"));
	OrderDao OrdDao = new OrderDao();
	String state = "";
	state = OrdDao.selectCurrentSate(id, proNum);
	
	if(state.equals("배송완료")){
		int re = 0;
		re = OrdDao.delOrder(id, proNum);
		if(re == 1){
			response.sendRedirect("MyOrder.jsp");
		} 
	}else{
		%>
		<script type="text/javascript">
			alert("배송이 완료된 물품만 삭제 할 수 있습니다");
			location.href="MyOrder.jsp";
		</script>
		<%		
	}
	
%>

</body>
</html>