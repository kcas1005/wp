//특정 입력사항이 입력되어 있지 않거나 상품 아무것도 체크 되어 있지 않으면 실행x
function reg(){
	if($("#name").val() == ""){
		alert("받으시는 분의 성함을 입력해주세요");
		return false;
	}
	if($("#postcode").val() == ""){
		alert("우편번호를 입력해주세요");
		return false;
	}
	if($("#address").val() == ""){
		alert("주소를 입력해주세요");
		return false;
	}
	
	if($("input:checkbox:checked").length==0){
		alert("구매하실 상품을 체크해주세요");
		return false;
	}
	
	document.getElementById("frm").submit();
}
window.onbeforeunload = function() {
	
	return;
}

function replaceAll(str, searchStr, replaceStr) {
	  return str.split(searchStr).join(replaceStr);
	}

function back(){
	location.href="purchaseCancelpro.jsp?id=";
}

$(function (){
	//메일 주소 선택시 text에 자동 추가
	var box = $("#email2");
	$("#mail").change(function(){
		var selected = $(this).find("option:selected");
		var content = selected.attr("value");
		box.val(content);
	});
	
	//셀렉트 박스 값 변화 시 각 상품당 총 금액 변화 + 체크 되어 있는 경우 하단 총 주문금액도 변화
	$(".amountSel").change(function(){
		
		indexTr = $(this).parents("tr").index();
		
		//변경된 값이 해당 상품의 수량을 뛰어넘으면 alert창 표시하고 최대 값으로 돌려보냄.
		amountLimit = $(".amountLimit").eq(indexTr-1).val();
		amountSel = $(".amountSel").eq(indexTr-1).val();
		if(amountLimit<amountSel){
			alert("해당 상품의 현재 수량은 "+amountLimit+"개입니다.\n"+amountLimit+"개 이하로 선택해주시기 바랍니다.");
			amountSel = $(".amountSel").eq(indexTr-1).val(amountLimit);
		}
			
		selected = $(".amountSel").eq(indexTr-1).find("option:selected");
		amount = parseInt(selected.attr("value"));
		price = parseInt($(".price").eq(indexTr-1).text());
		result = price*amount;
		preResult=$(".mulPrice").eq(indexTr-1).text();
		$(".mulPrice").eq(indexTr-1).text(result);
		
		if($("input:checkbox").eq(indexTr-1).is(":checked")){//해당 row의 체크박스가 선택 되었다면
			preMny = parseInt($("#finalMny").text());
			
			$("#finalMny").text(preMny-preResult+result);
		}
	});
	
	//체크박스 클릭 시 총 가격 변화	
	$("input:checkbox").change(function(){
		if($(this).is(":checked")){//check할 때
			indexTr=$(this).parents("tr").index();
			
			selected = $(".amountSel").eq(indexTr-1).find("option:selected");
			amount = parseInt(selected.attr("value"));
			price = parseInt($(".price").eq(indexTr-1).text());
			
			result = price*amount;
			preMny = parseInt($("#finalMny").text());
			
			$("#finalMny").text(preMny+result);
		}
		else{//check 풀 때
			indexTr=$(this).parents("tr").index();
			
			selected = $(".amountSel").eq(indexTr-1).find("option:selected");
			amount = parseInt(selected.attr("value"));
			price = parseInt($(".price").eq(indexTr-1).text());
			
			result = price*amount;
			preMny = parseInt($("#finalMny").text());
			
			$("#finalMny").text(preMny-result<0?0:preMny-result);
		}
	});
});

function Postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                /* if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr; */
            
            } else {
                /* document.getElementById("extraAddress").value = ''; */
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
