<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.ArticleDao" %>
<%@ page import="vo.ArticleVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="dao.MemberDao" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/manage_article.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/manage_article.js"></script>
<title>관리자 모드 템플릿</title>
<style>
header #header ul>li:nth-child(5)>a{background: #fff; color:#ff6e75;}
</style>
</head>
<body>
<%
	if(session.getAttribute("id") == null)//아이디가 없으면 접근 못함
		response.sendRedirect("index.jsp");
	else{
		MemberDao md = new MemberDao();
		String uid = (String)session.getAttribute("id");
		if(!md.isAdmin(uid))//관리자  아이디가 아니면 돌아감
			response.sendRedirect("index.jsp");
		else{
			if(session.getAttribute("admCode") == null){//관리자 코드 인증을 안 했을 시
				Random ranGen = new Random();
				int start1 = ranGen.nextInt(5)+1,//1~5
					start2 = ranGen.nextInt(3)+1,//1~3(번호 최소 3개 확인해야하므로)
					end = ranGen.nextInt(4-start2)+3;//3~5
				String txt = start1+"번 째 줄의 "+start2+"번 째 숫자부터 "+(start2+end-1)+"번 째 숫자까지 차레로 입력해주세요(공백없이 숫자만 입력해주세요)"; 
					%>
					
					<script type="text/javascript">
						var cde = prompt("<%= txt %>");
						location.href= "confirmCde.jsp?cde="+cde+"&start1="+<%=start1%>+"&start2="+<%=start2%>
						+"&end="+<%=end%>;
					</script>
				<%
			}else{//이미 한 걸로 확인이 된다면
				if(!uid.equals((String)session.getAttribute("admCode"))){//보안코드 인증했던 자와 현재 접속 관리자 아이디가 다르다면
				%>
				<script type="text/javascript">
					alert("보안코드 인증자와 현재 접속자가 같지 않습니다. 로그아웃 후 다시 접속해주세요");//로그아웃시관리자는보안코드에대한세션내용도삭제해줘야됨
					location.href="index.jsp";
				</script>
				<%
					}
				}
	int category = 1;

	if(request.getParameter("category")!= null){
		category = Integer.valueOf(request.getParameter("category"));
	}
	
	String key = null,
			info = null;
	if(request.getParameter("key") != null && request.getParameter("info") != null){
		key = request.getParameter("key");
		info = request.getParameter("info");
	}
	
	int pageIndex = 1;
	if(request.getParameter("pageIndex") != null){
		pageIndex=Integer.valueOf(request.getParameter("pageIndex"));
	}
	//한 페이지에 출력되는 글 수
	int pageLimit = 20;//20개로 설정 예정
	//한 페이지에 출력되는 index 수
	int pagingLimit = 10;//10개로 설정 예정
%>
<jsp:include page="header.jsp"></jsp:include>

<div class="box">
	<div class="index">
		<h2>관리자 모드</h2>
	</div>
	
	<div class="aside">
		<table>
			<tr>
				<td><a href="manage_members.jsp"><button class="blink">회원 관리</button></a></td>
			</tr>
			<tr>
				<td><a href="manage_article.jsp"><button class="blink" id="active">게시판 관리</button></a></td>
			</tr>
			<tr>
				<td><a href="manage_product.jsp"><button class="blink">상품 관리</button></a></td>
			</tr>
			<tr>
				<td><a href="manage_admin.jsp"><button class="blink">관리자 관리</button></a></td>
			</tr>
		</table>
	</div>
	
	<div class="content">
		
		<div class="tableRoof">
			<form action="manage_article.jsp" method="get" id="frm">
				<span class="tableTitle">게시판 관리</span>
				<span class="searchBox">
					<select name="key">
						<option value="title">제목</option>
						<option value="id" <%
							if(key!= null)
								if(key.equals("id"))
									out.print("selected");
						%>>아이디</option>
						<option value="article_no" <%
							if(key!= null)
								if(key.equals("no"))
									out.print("selected");
						%>>번호</option>
					</select>
					<input type="text" name="info">
					<input type="hidden" name="category" value=<%=category %>>
						<button type="submit">검색</button>
				</span>
			</form>
			<span class="selectBox">
				<select id="selectCategory" onchange="changeCategory()">
				<% 
					out.print("<option value='article1'");
					if(category == 1)
						out.print("selected='selected'");
					out.print(">리뷰</option>");
					
					out.print("<option value='article2'");
					if(category == 2)
						out.print("selected='selected'");
					out.print(">문의</option>");
					
					out.print("<option value='article3'");
					if(category == 3)
						out.print("selected='selected'");
					out.print(">공지사항</option>");
					out.print("<option value='article4'");
					if(category == 4)
						out.print("selected='selected'");
					out.print(">QnA</option>");
				%>
				</select>
			</span>
		</div>
		
		<table>
		
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>아이디</th>
				<th>등록일</th>
				<th>추천수</th>
				<th>상태</th>
				<th class="blankl"></th>
				<th class="blankr"></th>
			</tr>
<%
	ArticleDao ad = new ArticleDao();
	ArrayList<ArticleVo> info_article = new ArrayList<ArticleVo>();
	
	if(key != null && info != null){
		info_article = ad.searchArticle(key,info,category);
		
		if(info_article.size() == 0 || info.equals("")){
			out.print("<tr>");
				out.print("<td>");
				out.print("검색 결과가 없습니다.");
				out.print("</td>");
			out.print("</tr>");
			return;
		}
	}
	else
		info_article = ad.admin_selectAll(category);
	
	//페이징
	int pageCount = info_article.size()/pageLimit;//페이징에 쓰여야할 숫자갯수
	if(pageCount * pageLimit < info_article.size())//int형 나눗셈이므로 1을 더해야할 수 도 있음
		pageCount++;
	
	int limit = 0;
	//현재까지 index를 뺀 전체 리스트가 한 페이지에 출력될 수 있는 limit보다 작거나 같다면,
	if(info_article.size() - (pageIndex-1)*pageLimit <= pageLimit) 
		limit = info_article.size() - (pageIndex-1)*pageLimit;//나머지 다 출력
	else//limit을 넘는 양이 남았다면
		limit = pageLimit;//limit만큼 출력
		
	int start = (pageIndex-1)*pageLimit;
	int end = start+limit;
	
	//출력
	for(int i=start; i<end; i++){
		out.print("<tr>");
			out.print("<td>");
			out.print(info_article.get(i).getArticle_no());
			out.print("</td>");
			out.print("<td>");
			if(category == 1)//리뷰
				out.print("<a href='content1.jsp?reviewpage=1&reviewcategory=2"
				+"&product_no="+info_article.get(i).getProduct_no()+"'>");
			else if(category == 2)//문의
				out.print("<a href='content2.jsp?reviewpage=1&reviewcategory=3"
						+"&product_no="+info_article.get(i).getProduct_no()+"'>");
			else if(category == 3)//공지사항
				out.print("<a href='NoticeIn.jsp?category=1&arNum="+info_article.get(i).getArticle_no()+"'>");
				
			else if(category == 4)//QnA
				out.print("<a href='NoticeIn.jsp?category=2&arNum="+info_article.get(i).getArticle_no()+"'>");
			
			out.print(info_article.get(i).getTitle());
			out.print("</a>");
			out.print("</td>");
			out.print("<td>");
			out.print(info_article.get(i).getId());
			out.print("</td>");
			out.print("<td>");
			out.print(info_article.get(i).getRegdate());
			out.print("</td>");
			out.print("<td>");
			out.print(info_article.get(i).getRead_cnt());
			out.print("</td>");
			out.print("<td>");
			if(info_article.get(i).getHide() == 1)
				out.print("숨김");
			out.print("</td>");
			
			out.print("<td>");
			if(info_article.get(i).getHide() == 1)
				out.print("<button onclick='showArticle(\""+info_article.get(i).getArticle_no()+
						"\",\""+category+" \")'>숨기기 취소</button>");				
			else
				out.print("<button onclick='hideArticle(\""+info_article.get(i).getArticle_no()+
						"\",\""+category+" \")'>숨기기</button>");
			out.print("</td>");
			out.print("<td>");
			out.print("<button onclick='delArticle(\""+info_article.get(i).getArticle_no()+
					"\",\""+category+" \")'>삭제</button>");
			out.print("</td>");
		out.print("</tr>");
	}
%>
		</table>
	
		<div id ="page">
		
			<ul class="pagination">
				<%
					//페이징 관련 변수 설정
					//현재 하단 페이징에 띄울 숫자 시작과 끝
					int istart = pageIndex/pagingLimit;
					/* if(istart == 0)
						istart = 1;
					else  if(pageIndex == pagingLimit)*/
					if(pageIndex == pagingLimit)
						istart = 1;
					else if(pageIndex % pagingLimit == 0)
						istart = (istart-1)*pagingLimit+1;
					else
						istart = istart*pagingLimit+1;
					
					int iend = istart+pagingLimit-1;
					if(iend>pageCount)
						iend=pageCount;
					//양쪽 끝 버튼들이 가리키는 index
					int left = istart==1 ? 1 : istart-pagingLimit ,
						right= iend==pageCount ? iend : iend+1;
				%>
				<!-- < 버튼 -->
				<li class="page-item sideIdx">
				<%
					//검색 중이었을 경우
					if(key != null && info != null){	
						out.print("<a href='manage_article.jsp?pageIndex="+(left)+
								"&key="+key+"&info="+info+"&category="+category+"'");
					}else{
						out.print("<a href='manage_article.jsp?pageIndex="+(left)+"&category="+category+"'");
					}
					out.print("<button class='btn btn-outline-primary'> < </button>");
					out.print("</a>");
				%>
				</li>
				<%
					for(int i=istart; i<=iend; i++){
						
						//현재 index면 active
						if((pageIndex/* -1 */) == i)
							out.print("<li class='page-item active'>");
						else
							out.print("<li class='page-item'>");
						
						//검색 중이었을 경우
						if(key != null && info != null){
							out.print("<a href='manage_article.jsp?pageIndex="+(i/* +1 */)+
									"&key="+key+"&info="+info+"&category="+category+"'");						
						}else{
							out.print("<a href='manage_article.jsp?pageIndex="+(i/* +1 */)+"&category="+category+"'");
						}
						out.print("class='page-link'>"+(i/* +1 */)+"</a>");
					
						out.print("</li>");
					}
				%>
				<!-- > 버튼 -->
				<li class="page-item sideIdx">
				<%
					//검색 중이었을 경우
					if(key != null && info != null){	
						out.print("<a href='manage_article.jsp?pageIndex="+(right)+
								"&key="+key+"&info="+info+"&category="+category+"'");
					}else{
						out.print("<a href='manage_article.jsp?pageIndex="+(right)+"&category="+category+"'");
					}
					out.print("<button class='btn btn-outline-primary'> > </button>");
					out.print("</a>");
				%>
				</li>
			</ul>
			
		</div>
	
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<%
		}
	}
%>
</body>
</html>