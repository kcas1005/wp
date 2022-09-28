function revise(no){
	location.href="manage_product.jsp?category=2&no="+no;
}

function del(no,state){
	var cfrm = confirm("정말로 이 상품을 삭제하시겠습니까?(아직 배송 중인 재고가 있는 경우 삭제가 제한될 수 있습니다.)");
	if(cfrm)
		location.href="productDel.jsp?no="+no;
	else
		return false;
}

function reg(){
	if($("#name").val() == ""){
		alert("상품명을 입력하시오");
		return false;
	}
	if($("#img").val() == ""){
		alert("상품 이미지를 등록하시오");
		return false;
	}
	if($("#dec").val() == ""){
		alert("상품 설명을 등록하시오");
		return false;
	}
	if($("#price").val() == ""){
		alert("가격을 입력하시오");
		return false;
	}
	if($("#amount").val() == ""){
		alert("수량을 입력하시오");
		return false;
	}
	/*if($("#size").val() == ""){
		alert("사이즈를 입력하시오");
		return false;
	}*/
	document.getElementById("frm").submit();
}

function changeCategory(){
	var state = $("#selectCategory").val();
	if(state == "upload"){
		state = 2;
	}
	else if(state == "manage"){
		state = 1;
	}
	location.href="manage_product.jsp?category="+state;
}