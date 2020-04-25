import java.util.*; //for Arrays.

////
/*
for future to-do items, search for "***" and find them in comments.
for more information, see Notes section below. (if available)

to-dos:
***sample to-do.
done - sample to-do 2.
*/
////




public class Team{
        String name;
        Character[] members;
        int deaths = 0;

        Team(String new_name){
            name = new_name;
        }

        public void addMember(Character new_member, int position){
            //maybe use appending, instead of position? idk.

            this.members[position] = new_member;
            new_member.team = this;
        }//end addMember()

        public int checkTeamStatus(){ //idk maybe combine this and setNextEnemy. 2am is killing me.
            //returns number of ALIVE people in Team.
            List<Character> team_survivors = new ArrayList<Character>();
            int result = 0;

            for(int i = 0; i < this.members.length; i++){
                if(this.members[i].life_status == "alive"){
                    result++;
                    team_survivors.add(this.members[i]);
                }
            }
            return result;
        }//end checkTeamStatus()

}//end class Team
