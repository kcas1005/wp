<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.RecordsDao" %>
<%@ page import="dao.CartDao" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="dao.MemberDao" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	CartDao cd = new CartDao();
	RecordsDao rd = new RecordsDao();
	ProductDao pd = new ProductDao();
	MemberDao md = new MemberDao();

	if(request.getParameterValues("chkbox") !=null){//하나라도 체크 되어있었다면
		String[] chkbox = request.getParameterValues("chkbox"),
				 product_no = request.getParameterValues("product_no"),
				 purchaseAmount = request.getParameterValues("purchaseAmount");
		
		for(int i=0;i<chkbox.length; i++){
			//check된 인덱스
			int idx = Integer.valueOf(chkbox[i]);
			//상품 번호와 해당 상품 구매 갯수 설정
			int no = Integer.valueOf(product_no[idx]),
				amount = Integer.valueOf(purchaseAmount[idx]);
			
			//포인트 차감
			md.usePoint(id,no*amount);
			
			//장바구니에서 구매한 경우에는 cart테이블에 선택된 row들 제거
			if(request.getParameter("direct") == null)
				cd.deletechked(id,no);
			
			//Records테이블에 구매 사항 입력
			rd.insert(id,no);
			
			//Product테이블에 해당 상품 수량 감소
			pd.minusAmount(no,amount);
		}
		
		/*  //받은 값 확인하는 부분  
		for(int i=0; i<chkbox.length; i++)
			out.print("chkbox["+i+"] : "+chkbox[i]+"<br>");
		out.print("-----------------------------------<br>");
		for(int i=0; i<product_no.length; i++)
			out.print("product_no["+i+"] : "+product_no[i]+"<br>");
		out.print("-----------------------------------<br>");
		for(int i=0; i<purchaseAmount.length; i++)
			out.print("purchaseAmount["+i+"] : "+purchaseAmount[i]+"<br>");
		out.print("-----------------------------------<br>");
		out.print("id : "+id);
		 */
	}
	
	//어떤 결과가 있든 메인으로 보냄
	response.sendRedirect("main.jsp");
%>
</body>
</html>