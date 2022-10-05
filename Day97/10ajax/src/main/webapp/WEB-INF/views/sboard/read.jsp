<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<%@include file="../include/header.jsp"%>
<style>
	#modDiv{
		width:400px;
		height:150px;
		background-color:gray;
		position:fixed;
		top:20%;
		left:50%;
		margin-top:-50px;
		magin-left:-150px;
		padding:10px;
		z-index:1000; 
	}
</style>
<script>
var bno="${boardDTO.bno}";
var page=1;

$(document).ready(function() {
		
	getPageList(page);
	
	$("#replyModBtn").on("click",function(){
		
		var rno=$(".modal-title").html();
		var replytext = $("#replytext").val();
	
		$.ajax({
			type:'put',
			url:'/ex/replies/'+rno,
			headers:{
				"Content-Type":"application/json"
			},
			data:JSON.stringify({replytext:replytext}),
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					alert("수정 되었습니다.");
					$("#modDiv").hide("slow");
					getPageList(page);
				}
			}
		})
	})
	
	
	$("#replyDelBtn").on("click",function(){
		var rno=$(".modal-title").html();
		$.ajax({
			type:'delete',
			url:'/ex/replies/'+rno,
			headers:{
				"Content-Type":"application/json"
			},
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					getPageList(page);
				}
			}
			
		})
		
		
		
	})
	
	$("#closeBtn").on("click",function(){
		$("#modDiv").hide("slow");
	})
	$("#replies").on("click",".replyLi button",function(){
		var rno=$(this).parent().attr("data-rno");
		var replytext=$(this).parent().text();
		$(".modal-title").html(rno);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	})
	
	
	var fObject=$(".form");
	$(".pagination").on("click","a",function(event){
		event.preventDefault();
		page=$(this).attr("href");
		getPageList(page);
		
	})
	
	$(".btnList").on("click",function(){

		fObject.attr("method","get");
		fObject.attr("action","/ex/sboard/list");
		fObject.submit();
		
	})			
	$(".btnRemove").on("click",function(){
		fObject.attr("action","/ex/sboard/remove");
		fObject.submit();
	})
	$(".btnModify").on("click",function(){
		fObject.attr("method","get");
		fObject.attr("action","/ex/sboard/modify");
		fObject.submit();
	})
	
	$("#replyAddBtn").on("click",function(){		
		var replyer=$("#newReplyeWriter").val();
		var replytext=$("#newReplyText").val();
		
		$.ajax({
			type:'post',
			url:'/ex/replies',
			headers:{
				"Content-Type":"application/json"
			},
			dataType:'text',
			data:JSON.stringify({
				bno:bno,
				replyer:replyer,
				replytext:replytext
			}),
			success:function(result){
				if(result == 'SUCCESS'){
					alert("등록 되었습니다.");
					//화면에 찍는 작업
					getPageList(page);
				}
			}
		})
	})
});

function getPageList(page){
	var str="";
	
	$.getJSON("/ex/replies/"+bno+"/"+page,function(data){
		console.log(data);
		$(data.list).each(function(){
			str+="<li data-rno='"+this.rno+"' class='replyLi'>"
			+this.rno+":"+this.replytext+" <button>Mod</button></li>"
			
		})
		$("#replies").html(str);
		str="";
		/* console.log(data.pageMaker)
		console.log(data)
		alert(data.pageMaker.prev); */
		if(data.pageMaker.prev){
			str+="<a href='"+(data.pageMaker.startPage-1)+"'> << </a>";
		}
		
		for(var i=data.pageMaker.startPage;i<data.pageMaker.endPage;i++){
			var strClass=data.pageMaker.page==i?'class=active':'';
			
			str+="<a "+strClass+" href='"+i+"'>"+i+"</a>";
		}
		if(data.pageMaker.next){
			str+="<a href='"+(data.pageMaker.endPage+1)+"'> << </a>";
		}
		//alert(str);
		$(".pagination").html(str);
		
	})
	
	
}
</script>
			
<div class="main">
  
<form class="form" method="post">

					<input type='hidden' name='bno' value="${boardDTO.bno}"> <input
						type='hidden' name='page' value="${pageMaker.page}"> <input
						type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
					<input type='hidden' name='searchType' value="${pageMaker.searchType}">
					<input type='hidden' name='keyword' value="${pageMaker.keyword}">


</form>
	<h1>read</h1>
	<h2>
		<input type="text"
			name='title'  style="width:100%" value="${boardDTO.title}"
			readonly="readonly"></h2>
	
		<h2>
		Content
		<textarea  style="width:100%" name="content" rows="3"
			readonly="readonly">${boardDTO.content}</textarea>	</h2>
	
		<h2>
		Writer <input type="text"
			name="writer" style="width:100%" value="${boardDTO.writer}"
			readonly="readonly">
		<h2>

<!-- /.box-body -->
<div class="box-footer">
	<button type="submit" class="btnModify">Modify</button>
	<button type="submit" class="btnRemove">REMOVE</button>
	<button type="submit" class="btnList">LIST ALL</button>
</div>
<div id='modDiv' style="display:none">
	<div class="modal-title"></div>
	<div>
		<input type='text' id='replytext'>
	</div>
	<div>
		<button type="button" id="replyModBtn">Modify</button>
		<button type="button" id="replyDelBtn">delete</button>
		<button type="button" id="closeBtn">close</button>
	</div>
</div>
<h2>Ajax Test Page</h2>
<div>
	<div>replayer<input type="text" name="replyer" id="newReplyeWriter"/></div>
	<div>reply text<input type="text" name="replytext" id="newReplyText"/></div>
	<button id="replyAddBtn">AddReply</button>
</div>
<ul id="replies">
</ul>
<div class="pagination">
</div>

<%@include file="../include/footer.jsp"%>












