
////
///for future to-do items, search for words such as *** , ADD , note .
///for more information, see Notes section below. (if available)
////

//***please change fight_id to just id.

public class FightResult{
    String fight_id;        //*** where do I use this as a string, and where as an int? hmmm

    Team t1;
    Team t2;

    // int team1_size;
    int t1_deaths = 0;
    // int team2_size;
    int t2_deaths = 0;
    int final_round;



    FightResult(String id, Team t1, Team t2){
        fight_id = id;
        this.t1 = t1;
        this.t2 = t2;
        // team1_size = t1.members.length;   //with Array. might need to change with ArrayList.
        // team2_size = t2.members.length;

    }//end FightResult()

    void registerFight(Team t1, Team t2, int final_round){
        t1_deaths = t1.deaths;
        t1.deaths = 0; //oh, I already reset each team's death here. Good job.
        t2_deaths = t2.deaths;
        t2.deaths = 0;
        this.final_round = final_round;
    }

    void printResultv1(){
        System.out.println("ID: " + fight_id + "\t Ended at round " + Integer.toString(final_round) + ".\t Team 1: " + t1.name + ", Deaths: " + t1_deaths + "\t Team 2: " + t2.name + ", Deaths: " + t2_deaths);
    }

}//end class FightResult
