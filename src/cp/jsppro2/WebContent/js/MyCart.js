function delCart(proNum){
	location.href="delCartPro.jsp?product="+proNum;
}
function delAll(){
	location.href="delCartPro.jsp?product="+proNum;
}

$(document).ready(function(){
	var htext = 0;
	
	$("#allCheck").click(function(){
		 if($("#allCheck").prop("checked")) {
			 htext = 0;
			 $("input[type=checkbox]").prop("checked",true);
			 $('input:checkbox[name="product"]').each(function() {
			      if(this.checked){
			    	  htext += parseInt($(this).val());
			      }
			 });
		 }else { 
			 $("input[type=checkbox]").prop("checked",false);
			 $('input:checkbox[name="product"]').each(function() {
			      if(this.checked == false){
			    	  htext -= parseInt($(this).val());
			      }
			 });
		 }
		 
		 $(".mypageCountentResult h3").html("총 금액 : " + htext);
	});
	
	
	$('input:checkbox[name=product]').change(function() {
		if($(this).is(":checked")){
			htext += parseInt($(this).val());
		}else{
			htext -= parseInt($(this).val());
			$("#allCheck").prop("checked",false);
		}
		$(".mypageCountentResult h3").html("총 금액 : " + htext);
	});
	
	
	$('.mypageManu li:eq(0)').css("background-color","pink");
	$('.mypageManu li:eq(0)').find("a").css("color","black");
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","pink");
		$(this).find("a").css("color","black");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
		$('.mypageManu li:eq(0)').css("background-color","pink");
		$('.mypageManu li:eq(0)').find("a").css("color","black");
	});
	//소메뉴 클릭
	$(".mypageManu li:eq(0)").click(function(){
		location.href="MyCart.html";
	});
	$(".mypageManu li:eq(1)").click(function(){
		location.href="MyLikeList.html";
	});
	$(".mypageManu li:eq(2)").click(function(){
		location.href="MyMemberInpo.html";
	});
	$(".mypageManu li:eq(3)").click(function(){
		location.href="MyOrder.html";
	});
	
});
