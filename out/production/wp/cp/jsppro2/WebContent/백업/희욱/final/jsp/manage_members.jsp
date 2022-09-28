<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="dao.MemberDao" %>
<%@ page import="vo.MemberVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/manage_members.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/manage_members.js"></script>
<title>회원 관리</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
if(session.getAttribute("id") == null)//아이디가 없으면 접근 못함
	response.sendRedirect("main.jsp");
else{
	MemberDao md = new MemberDao();
	String uid = (String)session.getAttribute("id");
	if(!md.isAdmin(uid))//관리자  아이디가 아니면 돌아감
		response.sendRedirect("main.jsp");
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
				location.href="main.jsp";
			</script>
			<%
			}
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
<div class="box">
	<div class="index">
		<h2><span class="bar">|</span>관리자 모드</h2>
	</div>
	
	<div class="aside">
		<table>
			<tr>
				<td><a href="manage_members.jsp"><button class="blink" id="active">회원 관리</button></a></td>
			</tr>
			<tr>
				<td><a href="manage_article.jsp"><button class="blink">게시판 관리</button></a></td>
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
			<form action="manage_members.jsp" method="get" id="frm">
				<span class="tableTitle">회원정보 관리</span>
				<span class="searchBox">
					<select name="key">
						<option value="id">ID</option>
						<option value="name" <%
							if(key!= null)
								if(key.equals("name"))
									out.print("selected");
						%>>이름</option>
						<option value="phone" <%
							if(key!= null)
								if(key.equals("phone"))
									out.print("selected");
						%>>전화번호</option>
						<option value="email" <%
							if(key!= null)
								if(key.equals("email"))
									out.print("selected");
						%>>이메일</option>
					</select>
					
					<input type="text" name="info">
						<button type="submit">검색</button>
				</span>
			</form>
		</div>
		
		<table>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>포인트</th>
				<th class="blankl"></th>
				<th class="blankr"></th>
			</tr>
<%
	ArrayList<MemberVo> info_mem = new ArrayList<MemberVo>();
	
	//검색인지 비검색인지
	if(key != null && info != null){
		info_mem = md.searchMem(key,info,0);
		
		if(info_mem.size() == 0 || info.equals("")){
			out.print("<tr>");
				out.print("<td>");
				out.print("검색 결과가 없습니다.");
				out.print("</td>");
			out.print("</tr>");
			return;
		}
	}
	else
		info_mem = md.admin_selectAll(0);
	
	//페이징
	int pageCount = info_mem.size()/pageLimit;//페이징에 쓰여야할 숫자갯수
	if(pageCount * pageLimit < info_mem.size())//int형 나눗셈이므로 1을 더해야할 수 도 있음
		pageCount++;
	
	int limit = 0;
	//현재까지 index를 뺀 전체 리스트가 한 페이지에 출력될 수 있는 limit보다 작거나 같다면,
	if(info_mem.size() - (pageIndex-1)*pageLimit <= pageLimit) 
		limit = info_mem.size() - (pageIndex-1)*pageLimit;//나머지 다 출력
	else//limit을 넘는 양이 남았다면
		limit = pageLimit;//limit만큼 출력
		
	int start = (pageIndex-1)*pageLimit;
	int end = start+limit;
	
	//출력
	for(int i=start; i<end; i++){
		out.print("<tr>");
			out.print("<td>");
			out.print(info_mem.get(i).getId());
			out.print("</td>");
			out.print("<td>");
			out.print(info_mem.get(i).getName());
			out.print("</td>");
			out.print("<td>");
			out.print(info_mem.get(i).getPhone());
			out.print("</td>");
			out.print("<td>");
			out.print(info_mem.get(i).getEmail());
			out.print("</td>");
			out.print("<td>");
			out.print(info_mem.get(i).getPoint());
			out.print("</td>");
			out.print("<td>");
			out.print("<button onclick='reviseMem(\""+info_mem.get(i).getId()+"\")'>수정</button>");
			out.print("</td>");
			out.print("<td>");
			out.print("<button onclick='withdrawalMem(\""+info_mem.get(i).getId()+"\")'>탈퇴</button>");
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
						out.print("<a href='manage_members.jsp?pageIndex="+(left)+
								"&key="+key+"&info="+info+"'");
					}else{
						out.print("<a href='manage_members.jsp?pageIndex="+(left)+"'");
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
							out.print("<a href='manage_members.jsp?pageIndex="+(i/* +1 */)+
									"&key="+key+"&info="+info+"'");						
						}else{
							out.print("<a href='manage_members.jsp?pageIndex="+(i/* +1 */)+"'");
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
						out.print("<a href='manage_members.jsp?pageIndex="+(right)+
								"&key="+key+"&info="+info+"'");
					}else{
						out.print("<a href='manage_members.jsp?pageIndex="+(right)+"'");
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