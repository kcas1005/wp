package ExceptionExam;

import java.util.Arrays;

public class TeamPeople {
    String Name;
    String part;
    int bcnt = 0;
    int fcnt = 0;
    int mcnt = 0;
    int dcnt = 0;
    int csum = 0;

    public TeamPeople(String Name1, String part1) {
        this.Name = Name1;
        this.part = part1;
    }

    public String Getfront(String part) {
        return part;
    }

    public void teamSelect(TeamPeople teamPeoples[]) {
        for (int i = 0; i <= teamPeoples.length - 1; i++) {
            if (teamPeoples[i].part == "back") {
                bcnt++;
            } else if (teamPeoples[i].part == "front") {
                fcnt++;
            } else if (teamPeoples[i].part == "maneger") {
                mcnt++;
            } else if (teamPeoples[i].part == "DB") {
                dcnt++;
            }
         csum = mcnt+bcnt+dcnt+fcnt;
        }

    }

    public void selectException(int bcnt, int fcnt, int mcnt, int dcnt) throws PartException {
        if (fcnt > 2) {
            throw new PartException("프론트엔드는 2명까지 받을 수 없습니다");
        }
        if (bcnt > 2) {
            throw new PartException("백엔드 는 2명까지 받을 수 없습니다");
        }
        if (mcnt > 1) {
            throw new PartException("매니저는 1명만 받을 수 있습니다");

        }
        if (dcnt > 1) {
            throw new PartException("DB는 1명 만 받을 수 있습니다");

        }
    }

    public void NewTeamPeopleException(TeamPeople teamPeoples[]) throws NewTeamPeopleException {
        for (int i = 0; i <= teamPeoples.length - 1; i++) {
            if (!Arrays.asList(teamPeoples).contains(teamPeoples[i].part)) {
                throw new NewTeamPeopleException("너가 할일은 읍다 색갸");
            }
        }

    }
    public void MaxException(TeamPeople teamPeople[]) throws MaxException {
        if(teamPeople.length > 6) {
            throw new MaxException("그만좀 들어와라 시발");
        }

    }
}
