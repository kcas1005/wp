function reviseMem(id){
	location.href="MemberInpo.jsp?id="+id;

}
function withdrawalMem(id){
	var cfrm = confirm("정말로 이 회원을 탈퇴시키겠습니까? 탈퇴 시 해당 회원에 대한 정보는 지워집니다.");
	if(cfrm)
		location.href="delMember.jsp?id="+id;
	else
		return false;
}
