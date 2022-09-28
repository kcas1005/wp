<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<link href = "../css/NoticeIn.css" type = "text/css" rel= "stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
*{
   text-align: center;
}
.articleIn{
   width: 60%;
   margin: 0 auto;
   margin-top: 120px;
}
.articlMain{
   margin-top : 100px;
   width: 100%;
}
.articlMain tr:first-child{
   background-color: #fff;
}
.articlMain tr td{
   text-align: center;
}
.articlMain tr td h3{
   font-size:1.3em;
   text-align: left;
   text-indent:20px;
}
.con{
   border-top: 1px solid #ccc; 
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
   width: 80%;
   word-break: break-all;
   margin: 0 auto;
   padding:30px; 
   min-height:300px;
}
.countent p{
   text-align: left;
}
.arButton{
   width: 100%;
   background-color: #333;
   color : #fff;
   border-style:none;
   
}
.Inbnt{
   width: 10%;
}
.mypageCountent{
   margin-top: 50px;
   padding-top:50px;
   width: 100%;
   border-top: 1px solid #ccc;
}
.mypageCountent table{
   width: 100%;
}

@media all and (max-width : 768px){
   .articleIn{margin-top: 100px;}
   .articlMain{margin-top: 10px;}
   .articleIn{
   width: 95%;
   margin: 0 auto;
   margin-top: 80px;
}
   
   .articlMain tr td h3{
   font-size:1em;
   text-align: left;
   text-indent:3px;
}
   .inTableUl li{
   width:50%;margin:0;
   padding:3px 0;
}
.articleLeft{
   float: left;
}
.articlerigth{
   float:right;
}
   .countent{
   width: 80%;
   word-break: break-all;
   margin: 0 auto;
   padding:10px; 
   min-height:200px;
}
   .arButton{
   width: 100%;
   background-color: #333;
   color : #fff;
   border-style:none;
   border:1px solid #fff;
   
}   
   .mypageCountent3 table tr td{padding:0;text-align: center;}
   .mypageCountent3 table tr td:nth-child(1){width: 20%;}
   .mypageCountent3 table tr td:nth-child(2){width: 40%;}
   .mypageCountent3 table tr td:nth-child(3){width: 20%;}
   .mypageCountent3 table tr td:nth-child(4){width: 10%;}
   .mypageCountent3 table tr td:nth-child(5){width: 10%;}
   
}


</style>
<script type="text/javascript">

function reWrite(cate,proNum){
	if(cate == 3){
		location.href="regNotice.jsp?revise=1&article_no="+proNum;
	}else{
		location.href="regQnA.jsp?revise=1&article_no="+proNum;
	}
}
function del(cate,proNum){
	location.href="delArticlepro.jsp?category="+cate+"&article_no="+proNum;
}

</script>
</head>
<body>

<%
int Num = Integer.parseInt(request.getParameter("arNum"));
int cate = Integer.parseInt(request.getParameter("category"));
NoticeVo noVo = new NoticeVo();
NoticeDao noDao = new NoticeDao();
noDao.pluseRe_cnt(Num,cate);
noVo = noDao.selectNoticeIn(Num,cate);


ArrayList<NoticeVo> arrNo = new ArrayList<NoticeVo>();
arrNo = noDao.selectNotice(cate);

int maxNoPageNum = 0;
int maxNo = noDao.maxCountNotice(cate);
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
						<li class = "articlerigth Inbnt"><input type="button" value = "삭제" class = "arButton" onclick="del(<%=cate+2%>,<%=Num%>)"></li>
						<li class = "articlerigth Inbnt"><input type="button" value = "수정" class = "arButton" onclick="reWrite(<%=cate+2%>,<%=Num%>)"></li>
					</ul>
			</td></tr>
		</table>
		<div class = "mypageCountent mypageCountent3" id="likeList">
		<%
			if(cate == 1){
				out.print("<h2>공지사항</h2>");
			}else if(cate ==2 ){
				out.print("<h2>Q & A</h2>");
			}
		%>
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
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"&category="+cate+"'>"+arNum+"</a></td>");
						out.print("<td><a href='NoticeIn.jsp?arNum="+arNum+"&category="+cate+"'>"+arTitle+"</a></td>");
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
					out.print("<a href='NoticeIn.jsp?page="+i+"&arNum="+Num+"&category="+cate+"'>"+i+"</a>");
				}
				%>
			</div>
		</div>
	</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>