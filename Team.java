import java.util.*; //for Arrays.

////
///for future to-do items, search for words such as *** , ADD , note .
///for more information, see Notes section below. (if available)
////


//--------------------------------
//includes: an ArrayList of Characters. 
public class Team{
        String name;
        List<Character> members = new ArrayList<Character>();
        List<Character> alive = new ArrayList<Character>();
        int deaths = 0;

        Team(String new_name){
            name = new_name;
        }

        public void addMember(Character new_member){
            //maybe use appending, instead of position? idk.

            this.members.add(new_member);
            // this.alive.add(new_member); //doing the same thing for Alive Members list.
            new_member.team = this;
        }//end addMember()

        public int checkTeamStatus(){ //idk maybe combine this and setNextEnemy. 2am is killing me.
            //returns number of ALIVE people in Team.
            List<Character> alive = new ArrayList<Character>();
            for(int i = 0; i < members.size(); i++){
                if(members.get(i).life_status == "alive"){
                    alive.add(members.get(i));
                }
            }

            return alive.size();
        }//end checkTeamStatus()

        public void printMembers(){
            System.out.println("Team name: " + this.name);
            for(int i = 0; i < members.size(); i++){
                Character active = this.members.get(i);
                System.out.println(active.name + "\t Max HP: " + Integer.toString(active.max_hp));
            }
        }//end printMembers()

        public void resetTeam(){
            //maybe cycle and reset each member's HP and Next? or keep that in resetChain? hmm
            this.deaths = 0;
        }

}//end class Team
