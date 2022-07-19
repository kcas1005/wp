package ExceptionExam;

import java.util.Arrays;

public class main {
    public static void main(String[] arg) throws PartException {
        TeamPeople Lee = new TeamPeople("lee", "front");
        TeamPeople Park = new TeamPeople("Park", "Back");
        TeamPeople song = new TeamPeople("kim", "Back");
        TeamPeople kim = new TeamPeople("you", "front");
        TeamPeople sol = new TeamPeople("jin", "DB");
        TeamPeople kim1 = new TeamPeople("you", "maneger");
        TeamPeople kim2 = new TeamPeople("you", "front");




        TeamPeople loo = new TeamPeople("loo", "rr");
        TeamPeople[] teamPeoples = new TeamPeople[7];


        teamPeoples[0] = Lee;
        teamPeoples[1] = Park;
        teamPeoples[2] = kim;
        teamPeoples[3] = song;
        teamPeoples[4] = sol;
        teamPeoples[5] = kim1;
        teamPeoples[6] = kim2;

        System.out.println(teamPeoples[0].part);

        try {
            loo.teamSelect(teamPeoples);
            loo.selectException(loo.bcnt, loo.fcnt, loo.mcnt, loo.dcnt);
            loo.NewTeamPeopleException(teamPeoples);

        }
        catch (PartException e) {
            String message = e.getMessage();
            System.out.println(message);
            System.out.println();
            e.printStackTrace();
        } catch (NewTeamPeopleException e) {
            String message = e.getMessage();
            System.out.println(message);
            System.out.println();
            e.printStackTrace();
        }
        finally {
            try{
                loo.MaxException(teamPeoples);
            }

            catch (MaxException e) {
                String message = e.getMessage();
                System.out.println(message);
                System.out.println();
                e.printStackTrace();
            }
        }
    }


}

