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
header #header ul.menu>li:nth-child(3)>a{
background: #fff; color:#ff6e75;}
</style>
<link href = "../css/common.css" type = "text/css" rel= "stylesheet">
<link href = "../css/mypage.css" type = "text/css" rel= "stylesheet">
<%
if (session.getAttribute("id") == null || session.getAttribute("id") == ""){
   response.sendRedirect("login.jsp");
}
String id = (String)session.getAttribute("id");
%>
<style type="text/css">
.list{
   text-align: left;   
}
</style>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">

$(document).ready(function(){
   
	$(".pPoint").click(function(){
		   $(".ifff").fadeIn();
		   $(".close").fadeIn();
	       //location.href="plusPointPro.jsp"
	   });
	$("button.close").click(function(){
		$(".ifff").fadeOut();
		$(".close").fadeOut();
		location.reload();
	})
   
   $('.mypageManu li:eq(1)').css("background-color","#ff6e75");
   $('.mypageManu li:eq(1)').find("a").css("color","white");
   
   //소메뉴 오버
   $('.mypageManu li').hover(function(){
      $(this).css("background-color","#ff6e75");
      $(this).find("a").css("color","white");
   },function(){
      $(this).css("background-color","white");
      $(this).find("a").css("color","#333");
      $('.mypageManu li:eq(1)').css("background-color","#ff6e75");
      $('.mypageManu li:eq(1)').find("a").css("color","white");
   });
   //소메뉴 클릭
   $(".mypageManu li:eq(0)").click(function(){
      location.href="MyCart.jsp";
   });
   $(".mypageManu li:eq(1)").click(function(){
      location.href="MyLikeList.jsp";
   });
   $(".mypageManu li:eq(2)").click(function(){
      location.href="MyMemberInpo.jsp";
   });
   $(".mypageManu li:eq(3)").click(function(){
      location.href="MyOrder.jsp";
   });
   $(".mypageManu li:eq(4)").click(function(){
      location.href="Myarticle.jsp";
   });
   
   
   $("#allCheck").click(function(){
      if($("#allCheck").prop("checked")) {
          //alert("1");
          $("input:checkbox[name=pcheck]").prop("checked",true);
       }else { 
          //alert("0");
          $("input:checkbox[name=pcheck]").prop("checked",false);
       }
       
   });
   
   $('input:checkbox[name=pcheck]').change(function() {
      if($(this).is(":checked")){
      }else{
         $("#allCheck").prop("checked",false);
      }
   });
});

function confi(){
	conf = confirm("정말 삭제 하시겠습니까");
	if(conf == true){
		
	}else{
		return false;
	}
}

</script>
</head>
<body>

<%
   //선언
CartDao mpDao = new CartDao();
ProductVo proVo = new ProductVo();
ProductDao proDao = new ProductDao();

MemberDao memDao = new MemberDao();
MemberVo memVo = new MemberVo();
memVo = memDao.selectMember(id);

ArrayList<CartVo> lvo = new ArrayList<CartVo>();
lvo = mpDao.selectCart(id, 2);

//페이지 수
int maxListPageNum = 0;
int listcount = mpDao.maxCartPage(id, 2);
maxListPageNum = (listcount/10) + 1;

//해당페이지
int ListPag = 1;
if(request.getParameter("lpage") != null){
   ListPag = Integer.parseInt(request.getParameter("lpage"));
}
ListPag = ((ListPag - 1) * 10);
%>
<script type="text/javascript">

   $(document).ready(function(){
      
      
});

</script>
<iframe src="plusPoint.jsp" scrolling="no" width="500px" height="500px" class = "ifff"></iframe>
<button class='close'>닫기</button>
<!-- 해더 -->
<jsp:include page="header.jsp" flush="true"></jsp:include>


<div class = "mypageIn">
   <h2 class = "mypageName">마이페이지</h2>
   <div class = "mySimpleInpo">
      <img src="../image/user.jpg" width="100px" class="userImg">
      <ul class = "InpoText">
         <li><h2> 어서오세요 "<%= memVo.getName() %>" 님</h2></li>
         <li><h4>포인트 : <%= memVo.getPoint()%> </h4><img src="../image/plus.png" width="20px" class = "pPoint"></li>
      </ul>
   </div>
   <div class = "mypageMain">
      <ul class = "mypageManu">
         <li><a href = "#">장바구니</a></li>
         <li><a href = "#">관심목록</a></li>
         <li><a href = "#">회원정보</a></li>
         <li><a href = "#">주문조회</a></li>
         <li><a href = "#">작성글</a></li>
      </ul>
      <div class = "mypageCountent like" id="likeList">
      <form action="deleteCheck.jsp" method="get" class = "CartForm" onsubmit="return confi()">
         <table>
            <tr>
               <td><input type='checkbox' value='all' id="allCheck"></td>
               <td>상품번호</td>
               <td colspan="2">상품명</td>
               <td>금액</td>
            </tr>
            <%
               int listar = 0;
               listar += ListPag;
               for(int i = 0 ; i < 10; i++){
                  if(listar >= listcount){
                     break;
                  }
                  int curpronum = lvo.get(listar).getProduct_no();
                  proVo = proDao.selectProductId(curpronum);
                  int proNum = proVo.getProduct_no();
                  String proName = proVo.getProduct_name();
                  int pri = proVo.getPrice();
                  int selNum= lvo.get(listar).getPurchase();
                  out.print("<tr>");
                  out.print("<td><input type='checkbox' name='pcheck' class ='' value='"+proNum+"'></td>");
                  out.print("<td>"+proNum+"</td>");
                  out.print("<td><a href='#'><img src='../image/"+proVo.getProduct_img()+"' width='100px'></a></td>");
                  out.print("<td class = 'list'><a href='content.jsp?product_no="+proNum+"&reviewpage=1&reviewcategory=0'>"+proName+"</a></td>");
                  out.print("<td>"+pri+"</td>");
                  out.print("</tr>");
                  listar++;
               }
            %>
         </table>
         <input type="hidden" name="cate" value="2">
         <div class = "cartPaging">
            <%
            for(int i = 1 ; i <= maxListPageNum; i++)
            {
               out.print("<a href='MyLikeList.jsp?lpage="+i+"'>"+i+"</a>");
            }
            %>
         </div>
         <input type='submit' name='product' class = 'cartIn' value = '삭제' onclick='delList()'>
         </form>
      </div>
   </div>
</div>




<!-- 푸터 -->
<jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
</html>