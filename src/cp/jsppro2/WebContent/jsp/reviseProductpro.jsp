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
<<<<<<< .mine
	String saveFolder="D:/Workspace/jsppro2/WebContent/image";
	int fileMaxSize = 2000 * 10 * 10;
||||||| .r285
=======
	//String saveFolder="D:/eclipseWorkspace/jsppro2/WebContent/image";
	/* server.xml 에 아래 코드를 추가 */
	/* <Context docBase="D:/jsppro2_image" path="/image" reloadable="true" /> */
			
	String saveFolder="D:/jsppro2_image";
	int fileMaxSize = 1024 * 1024 * 10;//10MB 뒤에 숫자만 바꾸면됨.
>>>>>>> .r318
	MultipartRequest multi 	= new MultipartRequest(
		request, saveFolder, fileMaxSize, "utf-8", 
		new DefaultFileRenamePolicy());
	
	String name = multi.getParameter("name"),
			img = multi.getFilesystemName("img"),
			dec = multi.getFilesystemName("dec"),
			price = multi.getParameter("price"),
			amount = multi.getParameter("amount"),
			size = multi.getParameter("size"),
			no = multi.getParameter("no");
	
	

	
	//파일 이름 변경1
	String oldFileName = img;
	Calendar cal = Calendar.getInstance();
	String today = ""+cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE);
	String newFileName = today+"_"+no+"_img.jpg";
	File oldFile = new File(saveFolder +"/"+ oldFileName);
	File newFile = new File(saveFolder + "/"+newFileName);
	
	//기존 파일 삭제
	if(newFile.exists()){
		if(newFile.delete()){
			out.print("기존 이미지 파일삭제 성공");
		}
		else{
			out.print("기존 이미지 파일삭제 실패");
		}
	}
	else{
		out.print("기존 이미지 파일 존재하지 않음.");
	}
	
	if(!oldFile.renameTo(newFile))
		System.out.print("파일명 변경 실패");
	else
		System.out.print("파일명 변경 성공");
	img = newFileName;
	
	//파일 이름 변경2
	oldFileName = dec;
	newFileName = today+"_"+no+"_dec.jpg";
	oldFile = new File(saveFolder +"/"+ oldFileName);
	newFile = new File(saveFolder + "/"+newFileName);
	
	//기존 파일 삭제
	if(newFile.exists()){
		if(newFile.delete()){
			out.print("기존 설명 파일삭제 성공");
		}
		else{
			out.print("기존 설명 파일삭제 실패");
		}
	}
	else{
		out.print("기존 설명 파일 존재하지 않음.");
	}
	
	if(!oldFile.renameTo(newFile))
		System.out.print("파일명 변경 실패");
	else
		System.out.print("파일명 변경 성공");
	dec = newFileName;
	
	//상품 수정
	ProductDao pd = new ProductDao();
	
	if(pd.reviseProduct(no,name,img,dec,price,amount,size)){
			%>
		<script type="text/javascript">
			alert("상품 수정이 성공하였습니다.");
			location.href= "manage_product.jsp";
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
			alert("상품 수정이 실패하였습니다.");
			location.href= "manage_product.jsp";
		</script>
		<%
	}
%>
</body>
</html>