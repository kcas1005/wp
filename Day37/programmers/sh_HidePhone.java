package Day37.programmers;

class sh_HidePhone {
    public String solution(String phone_number) {
        String sb = "";
        String sb1 = "";
        String answer = "";

        sb = phone_number.substring(phone_number.length()-4, phone_number.length());
        sb1 = phone_number.substring(0, phone_number.length()-4);

        sb1 = sb1.replaceAll("[0-9]", "*");
        answer = sb1 + sb;

        return answer;
    }
}
