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

        System.out.println("\n--------\n");
    }//end printAllResults()

    public void calculations(){

        int temp_size1 = fight_list.get(0).t1.members.size();
        int[] t1_counter = new int[temp_size1 + 1];  //counter for deaths: 0, 1, 2..., party size.
        float t1_avg = 0; //Average number of deaths.

        //We don't need to calculate for t2 right now.
        //temp_size =  fight_list.get(0).t2.members.size();
        //int[] t2_counter = new int[temp_size + 1];

        //ADD HERE calculate this.
        for(FightResult fig : this.fight_list){
            int i = 0;
            while(i != fig.t1_deaths){
                i++;
            }
            t1_counter[i]++;
            t1_avg += fig.t1_deaths;
        }//end for

        System.out.println(t1_avg);
        System.out.println(Float.toString(t1_avg/fight_list.size()) + " / " + Integer.toString(temp_size1));
        String axis = "# of deaths: \t";
        int axis_temp = 0;
        String t1_results = "Happened: \t";
        for(int i : t1_counter){
            axis += Integer.toString(axis_temp++) + "\t";
            t1_results += Integer.toString(i) + "\t";
        }
        System.out.println(axis);
        System.out.println(t1_results);
        float tpk_amount = t1_counter[temp_size1];
        tpk_amount = (tpk_amount/fight_list.size())*100;
        System.out.println("Probability of TPK: " + Float.toString(tpk_amount) + " %.");

    }//end calculations()

}//end class DataBag
