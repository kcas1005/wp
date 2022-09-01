
function delList(proNum){
	location.href="delListPro.jsp?product="+proNum;
}

$(document).ready(function(){
	
	$('.mypageManu li:eq(1)').css("background-color","pink");
	$('.mypageManu li:eq(1)').find("a").css("color","black");
	
	//소메뉴 오버
	$('.mypageManu li').hover(function(){
		$(this).css("background-color","pink");
		$(this).find("a").css("color","black");
	},function(){
		$(this).css("background-color","white");
		$(this).find("a").css("color","#ccc");
		$('.mypageManu li:eq(1)').css("background-color","pink");
		$('.mypageManu li:eq(1)').find("a").css("color","black");
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