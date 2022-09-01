<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
header #header ul.menu>li:nth-child(4)>a{
background: #fff; color:#ff6e75;}
</style>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>

<%
ArrayList<NoticeVo> arrNo = new ArrayList<NoticeVo>();
NoticeDao noDao = new NoticeDao();
arrNo = noDao.selectNotice(2);

int maxNoPageNum = 0;
int maxNo = noDao.maxCountNotice(2);
maxNoPageNum = (maxNo/10) + 1;

//해당페이지
int NoPag = 1;
if(request.getParameter("page") != null){
	NoPag = Integer.parseInt(request.getParameter("page"));
}
int selPag = NoPag;
NoPag = ((NoPag - 1) * 10);



%>

<script type="text/javascript">

$(document).ready(function(){
	
	$('.mypageManu li:eq(1)').css("background-color","#ff6e75");
	$('.mypageManu li:eq(1)').find("a").css("color","#fff");
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","#ff6e75");
		$(this).find("a").css("color","#fff");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#333");
		$('.mypageManu li:eq(1)').css("background-color","#ff6e75");
		$('.mypageManu li:eq(1)').find("a").css("color","#fff");
	});
	//소메뉴 클릭
	$(".mypageManu li:eq(0)").click(function(){
		location.href="Notice.jsp";
	});
	
});

function writeAr(){
	location.href="regQnA.jsp";
}

</script>
</head>
<body>

<!-- 해더 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>


<div class = "mypageIn">
	<h2 class = "mypageName mypageName2">고객센터</h2>
	<div class = "mypageMain">
		<ul class = "mypageManu">
			<li><a href = "#">공지사항</a></li>
			<li><a href = "#">Q & A</a></li>
		</ul>
		<div class = "mypageCountent mypageCountent2" id="likeList">
			<table>
				<tr>
					<td>글번호</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<%
					int Noticear = 0;
					Noticear += NoPag;
					for(int i = 1 ; i <= 10; i++){
						if(Noticear >= maxNo){
							break;
						}
						
						int arNum = arrNo.get(Noticear).getArticle_no();
						String arTitle = arrNo.get(Noticear).getTitle();
						String arId = arrNo.get(Noticear).getId();
						int arDate = arrNo.get(Noticear).getRegdate();
						int arReCnt = arrNo.get(Noticear).getRead_cnt();
						out.print("<tr>");
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"&category=2'>"+arNum+"</a></td>");
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"&category=2'>"+arTitle+"</a></td>");
						out.print("<td>"+arId+"</td>");
						out.print("<td>"+arDate+"</td>");
						out.print("<td>"+arReCnt+"</td>");
						out.print("</tr>");
						Noticear++;
					}
				%>
			</table>
			<div class = "cartPaging">
				<%
				for(int i = 1 ; i <= maxNoPageNum; i++)
				{
					out.print("<a href='Notice.jsp?page="+i+"'>"+i+"</a>");
				}
				%>
				<input type='button' value = '글쓰기' onclick='writeAr()'>
			</div>
		</div>
	</div>
</div>




<!-- 푸터 -->
<jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
</html>