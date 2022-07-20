package Day46.example_20220720_API_hash_deepclone.exam01_equals;

public class question1_member {
    private String id;
    private int team;

    public question1_member(String id, int team){
        this.id = id;
        this.team = team;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setTeam(int team){
        this.team = team;
    }
    public int getTeam(){

        return team;
    }
    public String getId(){

        return id;
    }
    public boolean equals(Object obj){
        if(obj instanceof question1_member){
            question1_member qm1 = (question1_member) obj;
            if(team == qm1.getTeam()| id == qm1.getId()){
                System.out.println("아이디와 그룹이 같습니다.");
                return true;
            }else{
                System.out.println("아이디 또는 그룹이 다릅니다.");
            }
        } else{
            System.out.println("클래스가 다릅니다.");
        }
        return false;
    }

}

