



public class FightResult{
    String fight_id;        //*** where do I use this as a string, and where as an int? hmmm

    Team t1;
    Team t2;

    // int team1_size;
    int t1_deaths = 0;
    // int team2_size;
    int t2_deaths = 0;
    int final_round; //need to add this later.



    FightResult(String id, Team t1, Team t2){
        fight_id = id;
        this.t1 = t1;
        this.t2 = t2;
        // team1_size = t1.members.length;   //with Array. might need to change with ArrayList.
        // team2_size = t2.members.length;

    }//end FightResult()

    void registerFight(Team t1, Team t2, int final_round){
        t1_deaths = t1.deaths;
        t1.deaths = 0;
        t2_deaths = t2.deaths;
        t2.deaths = 0;

        this.final_round = final_round;
    }

}//end class FightResult
