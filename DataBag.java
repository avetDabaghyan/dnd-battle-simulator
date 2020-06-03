import java.util.*;
//this is for carrying all the FightResults.


public class DataBag{
    String bag_id;
    List<FightResult> fight_list = new ArrayList<FightResult>();

    DataBag(){

    }

    //gonna also print all results from here. just to flex.
    public void printAllResults(){
        String round = "Rounds: \t\t";
        String team1 = fight_list.get(0).t1.name + "\t\t"; //We assume that all the FightResults in 1 DataBag all have the same 2 teams. Gotta write that better later.
        String team2 = fight_list.get(0).t2.name + "\t\t";

        for(FightResult fig : this.fight_list){
            round += fig.final_round + "\t";
            team1 += fig.t1_deaths + "\t";
            team2 += fig.t2_deaths + "\t";
        }

        System.out.println(round);
        System.out.println(team1);
        System.out.println(team2);
    }//end printAllResults()

}//end class DataBag
