import java.util.*; //for Arrays. also Random.

////
//for future to-do items, search for "***" and find them in comments.
//for more information, see Notes section below. (if available)

//to-dos:
//none
////


//includes: FightLoop class, which is a separate environment for setting up fights.
public class FightLoop{
    //decided to just edit Character class to have "enemy" and "next" fields.



    public DataBag fight2Teams(Team t1, Team t2, int number_of_fights){
        DataBag data_bag = new DataBag();

        //set up block
        for(int i = 0; i < number_of_fights; i++){
            Character chain_head = setup1stTeam(t1, t2);    //vobshm, add MANY comments. please I KNOW you will need them later.
            chain_head = setupNextTeam(chain_head, t2, t1);
            Character active = chain_head;

            // initiativesPrint(active);
            FightResult fight = fightProcessLoop(chain_head, t1, t2, "00" + i);
            //toggle the print with comment:
            //fightEndPrint(chain_head, fight.final_round, t1, t2, fight.fight_id);
            data_bag.fight_list.add(fight);
            resetChain(chain_head);

            t1.resetTeam(); //new addition. gotta reset death count for next fight.
            t2.resetTeam();
        }//end for

        //if enemies all dead, win and end fight.
        //if active is dead, skip turn.
        //else if active.enemy dead, change enemy
        //then, active hits enemy.
        //then, if kills enemy, make it dead and add to deaths.
        //then, turn++ and round++

        return data_bag;
    }//end fight2Teams(t1, t2)

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
    //////
    public Character setup1stTeam(Team t1, Team enemy_team){
        Character chain_head = t1.members.get(0);
        chain_head.rollInitiative();
        // System.out.println(chain_head.name + "   " + Integer.toString(chain_head.initiative));
        chain_head = set1stEnemy(chain_head, enemy_team);


        for(int i = 1; i < t1.members.size(); i++){
            Character current = t1.members.get(i);
            chain_head = placeCurrentInChain(chain_head, current);
            current = set1stEnemy(current, enemy_team);
        }//end for

        return chain_head;
    }//end setup1Team(Team t1)

    Character setupNextTeam(Character chain_head, Team t2, Team enemy_team){
        for(int i = 0; i < t2.members.size(); i++){
            Character current = t2.members.get(i);
            chain_head = placeCurrentInChain(chain_head, current);
            current = set1stEnemy(current, enemy_team);
        }//end for

        return chain_head;
    }//end setupNextTeam

    Character placeCurrentInChain(Character chain_head,  Character current){
        // Character current = t1.members[i]
        current.rollInitiative();
        // System.out.println(current.name + "    " + Integer.toString(current.initiative));

        //Here I use a linked-list iteration to fit this new (current) character in the already made chain.
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

        return chain_head;    //THIS guy's name should be head or chain_head.
    }//end placeCurrentInChain

    Character set1stEnemy(Character character, Team enemy_team){
        character.enemy_team = enemy_team;
        Random ran = new Random();

        character.enemy = enemy_team.members.get(ran.nextInt(enemy_team.members.size()));
        return character;
    }//end set1stEnemy

    Character setNextEnemy(Character character, Team enemy_team){   //we supply Team enemy_team again? in case the enemy team needs to be changed later??? idk.
        //***ADD: Make enemy_choices or (t1_choices/t2_choices) a variable to pass around during the fight. So that you don't calculate it again and again.

        //character.enemy_team = enemy_team; //set1stEnemy already set the enemy team.
        Random ran = new Random();
        List<Character> enemy_choices = new ArrayList<Character>(); //i have no idea how to use ArrayLists. guess time to learn!

        for(int i = 0; i < enemy_team.members.size(); i++){
            if(enemy_team.members.get(i).life_status == "alive"){
                enemy_choices.add(enemy_team.members.get(i));
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
    //////

    FightResult fightProcessLoop(Character chain_head, Team t1, Team t2, String id){
        //fight block
        Character active = chain_head;
        int turn = 1;
        int round = 1;
        int max_round = 5;
        boolean fight_over = false;
        FightResult result = new FightResult(id, t1, t2);

        // System.out.println("\n\n\t\t FIGHT!");
        while(!fight_over){
        ////could summarise this with 3-4 functions: startOfTurn, action, endOfTurn, etc.
            ////////////// START OF TURN
            if(active.enemy_team.checkTeamStatus() == 0){
                System.out.println("VSYO WE WIN");  //mera arden ara
                fight_over = true;
                break;
                //System.out.println("You should NOT see this - fight should be break'd already.");
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
                            active.enemy.team.deaths++; //this will be passed to FightResult at the end. *** don't know if this is the correct way to handle this.
                        }//end(if enemy.hp <= 0)
                    }//end(if enemy != null)
                }//end if(alive)
            }//end if(fight not over)


            //////////END OF TURN
            active = active.next;
            turn++;
            if(active == null){ //important: if active == null, then it's the end of chain.
                // System.out.println("\tI am " + chain_head.name + ", start of initiative.");
                active = chain_head; //therefore, we switch to the 1st turn in initiative.
                round++;    //also means that 1 round has passed.
            }

            if(round >= max_round){
                System.out.println("VSYO TIME OUT ARA! Final round."); //2am coding vibes
                fight_over = true;
            }
        }//end while(!fight_over)

        ////don't toggle this here. use in primary method: fight2teams.
        ////fightEndPrint(chain_head, round, t1, t2);

        result.registerFight(t1, t2, round);
        return result;
    }//end fightProcessLoop

    //////
    void initiativesPrint(Character active){
        System.out.println("\n\n Initiatives: ");
        while(active != null){
            System.out.println(active.name + "\t\t init: " + Integer.toString(active.initiative) + "\t\t fighting: " + active.enemy.name);
            active = active.next;
        }//end while
        // active = chain_head;
    }

    void fightEndPrint(Character active, int round, Team t1, Team t2, String id){
        System.out.println("\n\n Fight ID: " + id);
        System.out.println("Ended at Round: " + round + "\n Fight results:");

        //need a function here. checkWinningTeam or something like that.
        if(t2.checkTeamStatus() == 0) { System.out.println("Winners: " + t1.name + "\n");
        }else if(t1.checkTeamStatus()==0) { System.out.println("Winners: " + t2.name + "\n");
        }else { System.out.println("Draw!\n"); }

        while(active != null){
            System.out.println(active.name + "\t\t init: " + Integer.toString(active.initiative) + "\t\t HP: " + active.hp);
            active = active.next;
        }//end while
    }//end fightEndPrint
    //////

    void resetChain(Character current){ //resets everyone's HP and initiative order.
        if(current.next != null){
            resetChain(current.next);
        }
        //yknow, this could be in Character class.
        current.hp = current.max_hp;    //reset back to full HP
        current.life_status = "alive";
        current.next = null;            //break the initiatives, to be set again next fight.
    }//end resetChain(Character current)

    ////Here we start 1v1 methods.      ----------------------------------------------------------

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
            Character chain_head = active;  //save who was first in initiative.
            //***maybe use "String chain_head = active.name" ???
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
                        if(active == chain_head){  //if the fight looped back to the first character,
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

    ////Here we start battlefield location methods.         ------------------------------------------

    public void battlefield1v1(Character p1, Character p2){
        //p1.setLocations(range);
        //p2.setLocations(range);
        Character active = setup1v1(p1, p2);
    }//end battlefield1v1(p1, p2)

    public void setLocations(Character p1){

    }//end setLocations(p1)


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
