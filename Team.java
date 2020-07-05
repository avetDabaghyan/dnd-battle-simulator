import java.util.*; //for Arrays.


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
            this.alive.add(new_member); //doing the same thing for Alive Members list.
            new_member.team = this;
        }//end addMember()

        //This is for removing a team member from the Alive list.
        public void killMember(Character target){
            int i = 0;
            while(alive.get(i).name != target.name){
                // System.out.println(alive.get(i).name);
                i++;
            }//end while
            alive.remove(i);

            deaths++; //this will be passed to FightResult at the end. ***don't know if this is the correct way to handle this.
        }//end killMember

        public int checkTeamStatus(){ //idk maybe combine this and setNextEnemy. 2am is killing me.
            //returns number of ALIVE people in Team.
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

            //Going to manually clone members to alive.
            alive.clear();
            for(int i = 0; i < (members.size()); i++){
                Character current = members.get(i);
                current.hp = current.max_hp;
                current.life_status = "alive";
                current.next = null;
                alive.add(current);
            }

            // for(int i = 0; i < alive.size(); i++){
            //     System.out.println("I am alive: " + alive.get(i).name);
            // }
        }//end resetTeam()

        // //unused right now.
        // void setEnemyTeam(Team target){
        //     for (int i = 0; i < members.size(); i++){
        //         members.get(i).enemy_team = target;
        //     }
        // }//end setEnemyTeam()


}//end class Team
