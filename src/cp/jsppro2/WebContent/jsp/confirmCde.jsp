<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<style type="text/css">

</style>
</head>
<body>
<%
	MemberDao md = new MemberDao();
	if(session.getAttribute("id") == null)//아이디가 없으면 접근 못함
		response.sendRedirect("index.jsp");
	else{
		String uid = (String)session.getAttribute("id");
		if(!md.isAdmin(uid))//관리자 아이디가 아니면 돌아감
			response.sendRedirect("index.jsp");
		else{
			
			int start1 = Integer.valueOf(request.getParameter("start1")),
				start2 = Integer.valueOf(request.getParameter("start2")),
				end = Integer.valueOf(request.getParameter("end"));
			if(request.getParameter("cde")!=null){//보안코드를 입력했다면
				String cdeStr = request.getParameter("cde");
				if(!cdeStr.matches("[0-9]+")){//숫자 외의 것이 포함 되어있다면
					%>
					<script type="text/javascript">
						alert("보안코드가 맞지 않습니다.");
						location.href= "index.jsp";
					</script>
					<%
				}
				else{//숫자만 포함되어 있다면
				
					int[] inputCde = new int[cdeStr.length()];
					for(int i=0; i<cdeStr.length(); i++)
						inputCde[i] = Integer.valueOf(cdeStr.substring(i,i+1));
					
					if(md.isAdmCde(start1, start2, end, inputCde)){//보안코드가 맞으면
						session.setAttribute("admCode",uid);
						response.sendRedirect("manage_members.jsp");
						}
					else{//맞지 않으면
					%>
					<script type="text/javascript">
						alert("보안코드가 맞지 않습니다.");
						location.href= "index.jsp";
					</script>
					<%
						}
					}
			}
			else{//보안코드를 입력하지 않았다면
				%>
				<script type="text/javascript">
					alert("보안코드를 입력해주세요.");
					location.href= "index.jsp";
				</script>
				<%	
			}
		}
	}
%>
</body>
</html>