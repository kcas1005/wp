<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import ="vo.*" %>
<%@ page import ="dao.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-sacle=1"charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<title>Insert title here</title>

</head>
<body>
<%
ArticleDao review = new ArticleDao();
ProductDao dao = new ProductDao();
MemberVo mem = new MemberVo();
ProductVo list = new ProductVo();
ArrayList<ArticleVo> article = new ArrayList<ArticleVo>();
ArrayList<ArticleVo> articlepage = new ArrayList<ArticleVo>();

String product_no = request.getParameter("product_no"); 
String reviewcategory =  request.getParameter("reviewcategory"); // 조회수 한번만 올리기
/* 리뷰,문의 */
article = review.Review_list(1 , product_no);
/* 리뷰문의 페이지번호 */
int page01 = article.size()/10;//문의모든 글
/* 페이징 */
int reviewpage =  Integer.parseInt(request.getParameter("reviewpage"));
if(reviewpage==1){
   reviewpage -= 1;
   articlepage = review.Review_page(1, product_no, reviewpage);
}else{
   reviewpage = (reviewpage*10)-11;
   articlepage = review.Review_page(1, product_no, reviewpage);
}


//조회수 한번만 올리기
list = dao.content_detail(product_no);//상품 리스트 1개 출력
int read_cnt = list.getRead_cnt();//조회수
if(reviewcategory.equals("1") || reviewcategory.equals("2") || reviewcategory.equals("3")){
   read_cnt-=1;
   dao.r_count(read_cnt, product_no);
}
String session_id = "";
if((String)session.getAttribute("id") == null || (String)session.getAttribute("id") ==""){
	session_id = "로그인 정보가 없습니다";
}else{
	session_id = (String)session.getAttribute("id");
}
BtnDao btn = new BtnDao();

%>
<jsp:include page="iframe.jsp"></jsp:include>
<jsp:include page="product.jsp"></jsp:include>



<div id="tabmenu">
   <table> 
      <tr><td><a href="content.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=1"  >상세정보</a></td>
      <td><a href="content1.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=2"style='background:#333; color:#fff;'>상품 리뷰</a></td>
      <td><a href="content2.jsp?reviewpage=1&product_no=<%=product_no %>&reviewcategory=3">상품 문의</a></td></tr>
   </table>
</div>
   <div id="qna"  class="re">
      <form action='review_pro.jsp' method="get" id='form'>
      <input type='hidden' name='review_category' value='1'>
      <input type='hidden' name='product_no' value='<%=product_no %>'>
         <p class='review'> 회원님의 리뷰를 작성 해 주세요!! </p>
         <!-- 첫줄 -->   
            <ul class='review_txt'>
               <li class='star_table'>
                  <select name ='star' >
                     <option value='5' selected>★★★★★ 
                     <option value='4'>★★★★☆
                     <option value='3'>★★★☆☆
                     <option value='2'>★★☆☆☆
                     <option value='1'>★☆☆☆☆
                  </select>
               </li>
               <li class='title_table'>
                  <input type='text' name='title' style='padding-left:2%;'class='title' placeholder='제목을 입력해주세요!'>
               </li>
               <li class='clear content_table'>
                  <textarea style='resize: none; width:100%;' name='content'class='textarea' placeholder='<%=list.getProduct_name() %>!! 어떠셨나요? 리뷰를 작성해주세요!'></textarea>
               </li>
               <li class='id_table'style='padding-bottom:10px;' >
                  <input type='text' name='id' class='id' placeholder='id' value='<%=session_id%>' disabled>
               </li> 
               <li class='submit_table' style='padding-bottom:10px;'>
               <button type='button'class='create_table' value=''>리뷰등록</button>
               <!-- <input type='submit' class='create_table' onclick='review_form()' value='리뷰등록'> -->
               </li>
         </ul>
      </form>
      <script>
      $(document).ready(function(){
			$(".create_table").click(function(){
				if($(".id").val()=="로그인 정보가 없습니다"){alert("로그인을 해 주세요");}else{
					if($(".title").val()==""){alert("제목을 입력해주세요");$(".title").focus(); }
					else if($(".textarea").val()==""){alert("내용을 입력해주세요");$(".textarea").focus();
					}else{
						$("#form").submit();
						}
					}
				})
		})
         
         
      
      </script>
      
      <table class="review_table">
      <!-- 첫줄 -->
         <tr class='top'><td class='star'style='width:20%'>평가</td><td class='star' style='width:60%'>리뷰</td><td style='width:20%' class=''>작성자</td></tr>
         <%
         
         for(int i = 0; i<articlepage.size(); i++){
            int star = articlepage.get(i).getStar();
            int r_count = articlepage.get(i).getRead_cnt();
            String mod = articlepage.get(i).getModdate();
         %>    
            <tr style='border-bottom:1px solid #ccc;'>
            <td class='clear star' style='width:20%; color:#ff6e75'>
            <%
               if(star==1){out.print("★☆☆☆☆");}
               if(star==2){out.print("★★☆☆☆");}
               if(star==3){out.print("★★★☆☆");}
               if(star==4){out.print("★★★★☆");}
               if(star==5){out.print("★★★★★");}
             %>
            </td>
            <td class='text' style='width:60%'>
            <p class='title'> <%=articlepage.get(i).getTitle()%></p>
            <%
            if(session.getAttribute("id") != null){
            	if(session_id.equals(articlepage.get(i).getId())){
                    %>
                       <div>
                       <a href='Revise.jsp?article_no=<%=articlepage.get(i).getArticle_no()%>&product_no=<%=articlepage.get(i).getProduct_no()%>&revise=1' target='iframe' class='iframe_show'><button type='button' class='hidden_btn re'>수정하기</button></a>
                       <button type='button' class='hidden_btn over'>더보기</button>
                      
                       </div>
                    <%
                    }
            	
            }
            if(session.getAttribute("id") == null || !session_id.equals(articlepage.get(i).getId())){ 
                %>   
                <div><button type='button' class='hidden_btn over'>더보기</button></div>
             <%
             }
            
            %>
            <p class='text_hidden'><%=articlepage.get(i).getContent()%>
               <br><b style='color:pink;font-weight:100;'class='like_txt'>이 리뷰가 도움이 되셨나요?</b>
           		 <%
                if(session.getAttribute("id") == null){
            	 %>
                	 <i style='color:pink; font-weight:100;font-style:normal;cursor:pointer; ' onclick='like_btn();'>좋아요</i> 
              	<% 
              }if(session.getAttribute("id") != null){
            	%>
            	 <a href='review.jsp?article_no=<%=articlepage.get(i).getArticle_no()%>' target='test<%=i %>'  style='color:pink; font-weight:100;' class='heart_btn'>좋아요</a> 
            	<%
            	  
              }
                
                
           	 %>
               <%-- <b class='heart' ><%=articlepage.get(i).getRead_cnt()%> 개</b> --%>
               <iframe src='' name='test<%=i %>' scrolling = 'no'style='display:none;border:none; width:80px; height:19px; ' class='heart' ></iframe> 
               
               <%
               
	               int cnt = btn.btn_count(session_id,  Integer.toString(articlepage.get(i).getArticle_no())); 
	               if(cnt==0){
	                  out.print("<b style='color:red;width:80px;display:inline-block;text-align:left;' class='hide'>♡"+r_count+"</b>");
	               }if(cnt==1){ 
	                  out.print("<b style='color:red;width:80px;display:inline-block;text-align:left;' class='hide'>♥"+r_count+"</b>");
	               }else{}
               %>
            </p>
            <%
            if(mod==null){out.print("<p class='date'>"+articlepage.get(i).getRegdate()+"</p></td>");}
            else {out.print("<p class='date'>"+articlepage.get(i).getRegdate()+"&nbsp&nbsp&nbsp 수정일 : "+mod+"</p></td>");}
            %>
            <td  style='width:20%'>
            <%=articlepage.get(i).getId()%><br>
            
            <% 
             if(session.getAttribute("id") != null){
            	if(session_id.equals(articlepage.get(i).getId())){
            %>
           
            		<a href='delete.jsp?article_no=<%=articlepage.get(i).getArticle_no()%>' onclick='delete_article();'>
	            	<button type='button' class='hidden_btn delete'>삭제하기</button>
	            </a>
	        <% 
            	}
            }	
         
            %>
            	
	            
            
            </td> 
            </tr>
            
      		<!-- 삭제버튼 확인알리창. 확인시 삭제, 취소시 이벤트 일어나지 않음 -->
            
            <script>
				function delete_article(){
					var result = confirm('삭제하시겠습니까?');
					if(result==true){
						
					}else{
						event.preventDefault()
					}
				}
			</script>
            
         <% 
         }
         %>
      </table>
      <div id="link_number" class='link_number1' style="text-align:center;">
         <%
         //페이지 번호 자동증가
         out.print("<ul>");
         int pagenum = 1;
         for(int n = 0; n <=page01; n++){
            out.print("<li><a href='content1.jsp?reviewpage="+pagenum+"&product_no="+product_no+"' >"+pagenum+"</a></li>");
            pagenum +=1;
         }
         out.print("<ul>");
         %>
      
      </div>
      
   </div>
<script>
$(document).ready(function(){
   $("#product_box ul li button.cart").click(function(){ //전송시 value 값바꿔보내기
      $("#product_box form").attr({'action':'PrductInPro.jsp'});
      $(".category").val('1');
      $(this).submit();
   }) 
   $("#product_box ul li .wish").click(function(){
      $("#product_box form").attr({'action':'PrductInPro.jsp'});//전송시 value 값바꿔보내기
      $(".category").val('2');
      $(this).submit();
   })
})

$(document).ready(function(){
   
      $("#link_number a:nth-child(<%=reviewpage %>)").css({"color":"black" , "font-weight":"bold"});
      $("#product_list>ul li:nth-child(<%=reviewpage %>) a").addClass("selected");
      li = $(".link_number1 li").length;
      $("#link_number ul").css('width' , li*20);//페이징  번호나열 크기 자동 늘어나기
})
$(document).ready(function(){
   top_width = $(".re table.review_table tr.top td:eq(1)").width();
   $(".re p.text_hidden").css('width',top_width);
   $(".re button.over").click(function(){
      $(this).closest("div").next(".text_hidden").stop(true,true).slideToggle();
   })
   
      
})
  $(document).ready(function(){
   
   $(".heart_btn").click(function(){
      $(this).next(".heart").show();
      $(this).nextAll(".hide").hide();
   })
   
      
})  


function like_btn(){
	alert("로그인 해주세요");
}


</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>