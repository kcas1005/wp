<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="com.hk.th.vo.*" %>
<%@ page import ="com.hk.th.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
*{
	text-align: center;
}
.articleIn{
	width: 80%;
	margin: 0 auto;
	margin-top: 120px;
}
.articlMain{
	margin-top : 200px;
	width: 100%;
}
.articlMain tr:first-child{
	background-color: #ff6e75;
}
.con{
	border-top: 1px solid black; 
	border-bottom: 1px solid black; 
}
.inTableUl li{
	display:inline-block;
	margin: 20px;
}
.articleLeft{
	float: left;
}
.articlerigth{
	float:right;
}
.countent{
	width: 70%;
	overflow: word-break:break-all;
	margin: 0 auto;
}
.countent p{
	text-align: left;
}
.arButton{
	width: 100%;
	background-color: #ff6e75;
	color : #feff97;
	border: 1px solid #feff97;
}
.Inbnt{
	width: 10%;
}
.mypageCountent{
	padding-top : 200px;
	margin-top: 200px;
	width: 100%;
	border-top: 1px solid black;
}
.mypageCountent table{
	width: 100%;
}

@media all and (max-width : 768px){
	.articleIn{margin-top: 100px;}
	.articlMain{margin-top: 10px;}
}

</style>
</head>
<body>

<%
int Num = Integer.parseInt(request.getParameter("arNum"));
NoticeVo noVo = new NoticeVo();
NoticeDao noDao = new NoticeDao();
noDao.pluseRe_cnt(Num,1);
noVo = noDao.selectNoticeIn(Num,1);


ArrayList<NoticeVo> arrNo = new ArrayList<NoticeVo>();
arrNo = noDao.selectNotice(1);

int maxNoPageNum = 0;
int maxNo = noDao.maxCountNotice(1);
maxNoPageNum = (maxNo/10) + 1;

//해당페이지
int NoPag = 1;
if(request.getParameter("page") != null){
	NoPag = Integer.parseInt(request.getParameter("page"));
}
int selPag = NoPag;
NoPag = ((NoPag - 1) * 10);


%>

<jsp:include page="header.jsp" flush="true"></jsp:include>

<div class = "articleIn">
		<table class = "articlMain">
			<tr><td><h3><%=noVo.getTitle() %></h3></td></tr>
			<tr><td>
					<ul class = "inTableUl">
						<li class = "articleLeft">고객센터 > 공지사항</li>
						<li class = "articlerigth">작성자 : <%=noVo.getId() %></li>
					</ul>
			</td></tr>
			<tr class = "con"><td><div class="countent"><%=noVo.getContent() %></div></td></tr>
			<tr><td>
					<ul class = "inTableUl">
						<li class = "articleLeft">작성일 : <%=noVo.getRegdate() %></li>
						<li class = "articlerigth">조회수 : <%=noVo.getRead_cnt() %></li>
					</ul>
			</td></tr>
			<tr><td>
					<ul class = "inTableUl">
						<li class = "articlerigth Inbnt"><input type="button" value = "삭제" class = "arButton"></li>
						<li class = "articlerigth Inbnt"><input type="button" value = "수정" class = "arButton"></li>
					</ul>
			</td></tr>
		</table>
		<div class = "mypageCountent" id="likeList">
			<table>
				<tr>
					<td>게시글번호</td>
					<td>게시글제목</td>
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
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"'>"+arNum+"</a></td>");
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"'>"+arTitle+"</a></td>");
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
					out.print("<a href='NoticeIn.jsp?page="+i+"&arNum="+Num+"'>"+i+"</a>");
				}
				%>
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>