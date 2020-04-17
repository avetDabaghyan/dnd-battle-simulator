import java.util.*; //for Arrays.


//includes: FightLoop class, which is a separate environment for setting up fights.
//done - should add a way to end the loop, for example when combat is over but no one is dead.
//***should add a way for characters to rest/revive/return after fights.

public class FightLoop{
    //decided to just edit Character class to have "enemy" and "next_turn" fields.


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

                    active = active.next_turn; //change active character. like a LinkedList!
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

        p1.next_turn = p2;
        p2.next_turn = p1;

        Character active;
        if(init1 >= init2){
            active = p1;
        }else{
            active = p2;
        }
        return active;
    }//end setup1v1(p1, p2)

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
