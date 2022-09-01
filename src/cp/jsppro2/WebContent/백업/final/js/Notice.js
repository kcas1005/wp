$(document).ready(function(){
	
	$('.mypageManu li:eq(0)').css("background-color","pink");
	$('.mypageManu li:eq(0)').find("a").css("color","black");
	
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
	$(".mypageManu li:eq(1)").click(function(){
		location.href="QA.html";
	});
	
});