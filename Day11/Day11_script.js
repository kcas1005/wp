id=prompt('아이디 입력');
if(id=='admin'){
    password=prompt('비밀번호 입력');
    if(password=='123456'){
        location.href="./Day11_login.html"
    }
    else {
        location.href="./Day11_error.html"
    }
}
else{
    location.href="./Day11_error.html"
}