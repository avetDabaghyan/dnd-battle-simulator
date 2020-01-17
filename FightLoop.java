import java.util.*; //for Arrays.


//includes: FightLoop class, which is a separate environment for setting up fights.
//***should add a way to end the loop, for example when combat is over but no one is dead. done.
//***should add a way for characters to rest/revive after fights.

public class FightLoop{
    //decided to just edit Character to have "enemy" and "next_turn" fields.
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



    //making a fight1v1() method, where 2 people roll initiative and take turns
    //hitting each other until one of them is dead.
    //
    public void fight1v1(Character p1, Character p2){
        //
        //take turns to attack the other Character
        //i will make a LinkedList Loop thingy that moves through turns.
        //ends when one of them is HP <= 0. announce the winner.
        //
        int init1 = p1.rollInitiative();
        int init2 = p2.rollInitiative();

        //set-up block. is there a way to use a method here?
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
        //set-up block. is there a way to use a method here?
        System.out.println(active.name);
        System.out.println("VS");
        System.out.println(active.enemy.name);
        System.out.println("FIGHT! \n");

        //fight block.
        int turn = 1;
        Character first_turn = active;  //save who was first in initiative.
        //***maybe use "String first_turn = active.name" ???
        int max_turn = 20;
        boolean fight_over = false;

        while(!fight_over){
            active.attack(active.enemy); //what is "active"? it's a Character!
            if(active.enemy.hp <= 0){   //if that last attack was lethal, ...
                System.out.println("     ..." + active.name + " pwned " + active.enemy.name);
                //***maybe return the winning character??
                System.out.println("     ...After " + Integer.toString(turn) + " turns, " + active.name + " wins! With " + Integer.toString(active.hp) + " HP left.");
                fight_over = true;  //end fight with this boolean switch.
            } else{ //if was not lethal, continue.
                if(turn >= 20){   //if the fight has gone too long, end it.
                    System.out.println("Time out! It's turn " + Integer.toString(max_turn));    //"Loop over" all fighters and announce their health.
                    System.out.println("     ..." + active.name + " finished with " + Integer.toString(active.hp) + " HP remaining.");
                    System.out.println("     ..." + active.enemy.name + " finished with " + Integer.toString(active.enemy.hp) + " HP remaining.");
                    fight_over = true; //switch for ending the fight.
                }else{  //continue...
                    active = active.next_turn; //change active character. like a LinkedList !
                    turn++;
                }
            }
        //fight block

        }//end while(!fight_over)

    }//end fight1v1()



    // public static void main(String[] args){
    //     Integer[] arr = {7,5,11,8};
    //     Arrays.sort(arr, Collections.reverseOrder());
    //     System.out.println(Arrays.toString(arr));
    //
    // }//end main()


}//end class FightLoop
