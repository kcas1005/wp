<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.ProductDao" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Calendar" %>
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
<style type="text/css">

</style>
</head>
<body>
<%
	String saveFolder="../upload";
	int fileMaxSize = 2000 * 10 * 10;
	MultipartRequest multi 	= new MultipartRequest(
		request, saveFolder, fileMaxSize, "utf-8", 
		new DefaultFileRenamePolicy());
	
	String name = multi.getParameter("name"),
			img = multi.getFilesystemName("img"),
			dec = multi.getFilesystemName("dec"),
			price = multi.getParameter("price"),
			amount = multi.getParameter("amount"),
			size = multi.getParameter("size");
	
	//파일 이름 변경1
	String oldFileName = img;
	Calendar cal = Calendar.getInstance();
	String today = ""+cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE);
	String newFileName = name + today+"_img.jpg";
	File oldFile = new File(saveFolder +"/"+ oldFileName);
	File newFile = new File(saveFolder + "/"+newFileName);
	
	if(!oldFile.renameTo(newFile))
		System.out.print("파일명 변경 실패");
	else
		System.out.print("파일명 변경 성공");
	img = newFileName;
	
	//파일 이름 변경2
	oldFileName = dec;
	newFileName = name + today+"_dec.jpg";
	oldFile = new File(saveFolder +"/"+ oldFileName);
	newFile = new File(saveFolder + "/"+newFileName);
	
	if(!oldFile.renameTo(newFile))
		System.out.print("파일명 변경 실패");
	else
		System.out.print("파일명 변경 성공");
	dec = newFileName;
	
	ProductDao md = new ProductDao();
	
	if(md.regProduct(name,img,dec,price,amount,size))
		response.sendRedirect("manage_product.jsp");
	else
		out.print("상품등록 실패");
%>
</body>
</html>