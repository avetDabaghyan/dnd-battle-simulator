import java.util.*; //for Arrays. also Random.

//replaced "next_turn" with "next".
////
//for future to-do items, search for "***" and find them in comments.
//for more information, see Notes section below. (if available)

//to-dos:
//***sample to-do.
//***should add a way for characters to rest/revive/return after fights.
//done - sample to-do 2.
//done - should add a way to end the loop, for example when combat is over but no one is dead.
////


//includes: FightLoop class, which is a separate environment for setting up fights.
public class FightLoop{
    //decided to just edit Character class to have "enemy" and "next" fields.


    //making a fight1v1() method, where 2 people roll initiative and take turns
    //hitting each other until one of them is dead.
    //or the timer runs out.
    public void fight1v1(Character p1, Character p2){

        Character active = setup1v1(p1, p2);

        System.out.println("\n" + active.name);
        System.out.println("VS");
        System.out.println(active.enemy.name);
        System.out.println("FIGHT! \n");

        //start fight block.
        int turn = 1;
        int round = 1;
        Character first_turn = active;  //save who was first in initiative.
        //***maybe use "String first_turn = active.name" ???
        int max_round = 10;
        boolean fight_over = false;

        while(!fight_over){

            //Active takes its turn.
            active.attack(active.enemy); //what is "active"? it's a Character!
            if(active.enemy.hp <= 0){   //if that last attack was lethal, ...
                System.out.println("     ..." + active.name + " pwned " + active.enemy.name);
                //***maybe return the winning character??
                System.out.println("     ...After " + Integer.toString(round) + " rounds, " + active.name + " wins! With " + Integer.toString(active.hp) + " HP left.");
                fight_over = true;  //end fight with this boolean switch.
            }else{ //if was not lethal, continue. Move to next turn.

                    active = active.next; //change active character. like a LinkedList!
                    turn++;
                    if(active == first_turn){  //if the fight looped back to the first character,
                        round++;                //go to next round.
                    }

                    if(round >= max_round){   //if the fight has gone too long, end it.
                        System.out.println("     ---Time out! It's round " + Integer.toString(round));    //"Loop over" all fighters and announce their health.
                        System.out.println("     ..." + active.name + " finished with " + Integer.toString(active.hp) + " HP remaining.");
                        System.out.println("     ..." + active.enemy.name + " finished with " + Integer.toString(active.enemy.hp) + " HP remaining.");
                        fight_over = true; //switch for ending the fight.
                    }//end max round
                }//end not lethal


        }//end while(!fight_over)
        //end fight block

    }//end fight1v1(p1, p2)

    public void fight2Teams(Team t1, Team t2){

        //set up block
        ///Initiative   DONE
        Character chain_head = setup1stTeam(t1, t2);    //vobshm, add MANY comments. please I KNOW you will need them later.
        chain_head = setupNextTeam(chain_head, t2, t1);
        Character active = chain_head;
        Character first_turn = active;  //save who was first in initiative. might be the same as chain_head, idk.

        ///enemies  DONE
        System.out.println("\n\n Initiatives: ");
        while(active != null){
            System.out.println(active.name + "\t\t init: " + Integer.toString(active.initiative) + "\t\t fighting: " + active.enemy.name);
            active = active.next;
        }//end while
        active = first_turn;

        //fight block
        int turn = 1;
        int round = 1;
        int max_round = 5;
        boolean fight_over = false;

        System.out.println("\n\n\t\t FIGHT!");
        while(!fight_over){

            ////////////// START OF TURN
            int enemy_team_status = checkTeamStatus(active.enemy_team);
            if(enemy_team_status == 0){
                System.out.println("VSYO WE WIN");  //mera arden ara
                fight_over = true;
                break;
            }else{
            // if(true){
                if(active.life_status == "dead"){
                    // System.out.println("___i skip. i iz "+active.name + " and "+ active.life_status);
                }else{
                    //////////ACTION        //could have all of this as an action function, not a block of code. whatever, let's try first.
                    if(active.enemy.life_status == "dead"){
                        active = setNextEnemy(active, active.enemy_team);
                        // System.out.println("    im "+ active.name + ", i am " + active.life_status + " and i changed target.");
                    }//end if(enemy.life_status == "dead")
                    if(active.enemy != null){   //NOW this should not be necessary. because if enemy_team_status == 0, no one is left alive anyway. so this is a precaution???
                        active.attack(active.enemy);
                        if(active.enemy.hp <= 0){
                            // System.out.println("r"+round + ":\t "+active.name + " Kills " + active.enemy.name);
                            active.enemy.life_status = "dead";
                        }//if enemy.hp <= 0
                    }//if enemy != null
                }//end if(alive)
            }

/*
            // //START OF TURN
            //if(all enemies dead. checkTeamStatus <= 0){
                skip. idk. just pass?
            }else{
                if(active is dead){
                    skip. maybe pass.
                }else{
                    if(enemy is dead){
                        change enemy to someone who is alive.
                    }//end if (enemy is dead)
                    if(has an enemy. enemy != null){
                        now, attack.
                        if(attack is lethal){
                            set them to be dead.
                        }//end if(attack is lethal)
                    }//end if (has an enemy)
                }//end if (active is alive)
            }//end if (at least 1 enemy alive)

*/
            //////////END OF TURN
            active = active.next;
            turn++;

            if(active == null){
                // System.out.println("\tI am " + first_turn.name + ", start of initiative.");
                active = first_turn;
                round++;
            }
            if(round >= max_round){
                System.out.println("VSYO TIME OUT ARA"); //2am coding vibes
                fight_over = true;
            }

        }//end while(!fight_over)

        System.out.println("\n\n Round: " + round + ". Fight results:");
        active = first_turn;
        //need a function here. checkWinningTeam or something like that.
        if(checkTeamStatus(t2) == 0){
            System.out.println("Winners: " + t1.name);
        }else if(checkTeamStatus(t1)==0){
            System.out.println("Winners: " + t2.name);
        }
        while(active != null){
            System.out.println(active.name + "\t\t init: " + Integer.toString(active.initiative) + "\t\t HP: " + active.hp);
            active = active.next;
        }//end while
        active = first_turn;


        //if enemies all dead, win and end fight.
        //if active is dead, skip turn.
        //else if active.enemy dead, change enemy
        //then, active hits enemy.
        //then, turn++ and round++

    }//end fight2Teams(t1, t2)

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

    public void battlefield1v1(Character p1, Character p2){
        //p1.setLocations(range);
        //p2.setLocations(range);
        Character active = setup1v1(p1, p2);
    }//end battlefield1v1(p1, p2)

    public void setLocations(Character p1){

    }//end setLocations(p1)

    public Character setup1v1(Character p1, Character p2){
        int init1 = p1.rollInitiative();
        int init2 = p2.rollInitiative();

        p1.enemy = p2;
        p2.enemy = p1;

        p1.next = p2;
        p2.next = p1;

        Character active;
        if(init1 >= init2){
            active = p1;
        }else{
            active = p2;
        }
        return active;
    }//end setup1v1(p1, p2)


    public Character setup1stTeam(Team t1, Team enemy_team){
        Character chain_head = t1.members[0];
        chain_head.rollInitiative();

        // System.out.println(chain_head.name + "   " + Integer.toString(chain_head.initiative));
        chain_head = set1stEnemy(chain_head, enemy_team);

        for(int i = 1; i < t1.members.length; i++){
            Character current = t1.members[i];
            chain_head = placeCurrentInChain(chain_head, current);
            current = set1stEnemy(current, enemy_team);
        }//end for

        return chain_head;
    }//end setup1Team(Team t1)

    Character setupNextTeam(Character chain_head, Team t2, Team enemy_team){
        for(int i = 0; i < t2.members.length; i++){
            Character current = t2.members[i];
            chain_head = placeCurrentInChain(chain_head, current);
            current = set1stEnemy(current, enemy_team);
        }//end for

        return chain_head;
    }//end setupNextTeam

    Character placeCurrentInChain(Character chain_head,  Character current){
        // Character current = t1.members[i]
        current.rollInitiative();
        // System.out.println(current.name + "    " + Integer.toString(current.initiative));

        Character temp = chain_head;
        if(current.initiative > chain_head.initiative){
            current.next = chain_head;
            chain_head = current;
        }else{
            while(temp.next != null){
                if(temp.initiative >= current.initiative && current.initiative > temp.next.initiative){
                    current.next = temp.next;
                    temp.next = current;
                    break;
                }
                temp = temp.next;   //step to next.
            }//end while
            if(temp.initiative >= current.initiative){  //add comments here. how does it get here, to this IF?
                temp.next = current;
            }//end if
        }//end else
        //by this point, should have Initiative set in chain.


        return chain_head;    //THIS guy's name should! be head or chain_head.
    }//end placeCurrentInChain

    Character set1stEnemy(Character character, Team enemy_team){
        character.enemy_team = enemy_team;
        Random ran = new Random();

        character.enemy = enemy_team.members[ran.nextInt(enemy_team.members.length)];
        return character;
    }//end set1stEnemy

    Character setNextEnemy(Character character, Team enemy_team){   //we supply Team enemy_team again, in case the enemy team needs to be changed later idk.
        //character.enemy_team = enemy_team;
        Random ran = new Random();
        List<Character> enemy_choices = new ArrayList<Character>(); //i have no idea how to use ArrayLists. guess time to learn!

        for(int i = 0; i < enemy_team.members.length; i++){
            if(enemy_team.members[i].life_status == "alive"){
                enemy_choices.add(enemy_team.members[i]);
            }
        }

        if(enemy_choices.size() > 0){
            character.enemy = enemy_choices.get(ran.nextInt(enemy_choices.size()));
            return character;
        }
        else{
            return null;
        }
    }//end setNextEnemy

    int checkTeamStatus(Team team){ //idk maybe combine this and setNextEnemy. 2am is killing me.
        List<Character> team_survivors = new ArrayList<Character>();
        int result = 0;

        for(int i = 0; i < team.members.length; i++){
            if(team.members[i].life_status == "alive"){
                result++;
                team_survivors.add(team.members[i]);
            }
        }

        return result;
    }

}//end class FightLoop


    // public static void main(String[] args){
    //     Integer[] arr = {7,5,11,8};
    //     Arrays.sort(arr, Collections.reverseOrder());
    //     System.out.println(Arrays.toString(arr));
    //
    // }//end main()



    /////////////////////////////////////////////////////////////////
    //Notes
    //

    //below commented code is old attempt
    // //decided to make a separate nested Class, Combatant for each combatant.
    // class Combatant extends Character{
    //     Character self;
    //     Character enemy;
    //     Character next;//*** ??? should this be a Character?
    //     Combatant(String newName){
    //         super(newName);
    //     }//end Combatant constructor
    //     void setValues(Character self_var, Character enemy_var, Character next_var){
    //         self = self_var;
    //         enemy = enemy_var;
    //         next = next_var;
    //     }//end setValues()
    // }//end class Combatant



    //
//end class FightLoop
