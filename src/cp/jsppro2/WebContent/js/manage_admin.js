function grantAdmin(id){
	var cfrm = confirm("이 회원에게 관리자 권한 주겠습니까?");
	if(cfrm)
		location.href="grantAdminpro.jsp?id="+id;
	else
		return false;
	
}
function remAdmin(id){
	var cfrm = confirm("이 회원의 관리자 권한을 삭제하시겠습니까?");
	if(cfrm)
		location.href="remAdminpro.jsp?id="+id;
	else
		return false;
}
