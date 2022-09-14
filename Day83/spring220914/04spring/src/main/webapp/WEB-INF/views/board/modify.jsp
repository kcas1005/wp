<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<%@include file="../include/header.jsp"%>
  <div class="main">
  
<form role="form" method="post">

	<div class="box-body">

		<h2>BNO <input type="text"
				name='bno' style="width:100%" value="${boardVO.bno}"
				readonly="readonly">
	</h2>

		<h2>
		<input type="text"
			name='title'  style="width:100%" value="${boardVO.title}"
			></h2>
	
		<h2>
		Content
		<textarea  style="width:100%" name="content" rows="3"
			>${boardVO.content}</textarea>	</h2>
	
		<h2>
		Writer <input type="text"
			name="writer" style="width:100%" value="${boardVO.writer}"
			>
		<h2>
	</div>
	<!-- /.box-body -->
</form>
<div class="box-footer">
	<button type="submit" class="btn btn-primary">SAVE</button>
	<button type="submit" class="btn btn-warning">CANCEL</button>
</div>
<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/ex/board/listAll";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});

	});
</script>


</div>
		
<%@include file="../include/footer.jsp"%>