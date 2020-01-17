//this version has:
//CombatNode. unfinished. but the idea is that a separate node class controls the Character.


import java.util.*; //for Arrays.


//includes: Looper class, a LinkedList for setting up turns.
//should have a way to end the loop, for example when combat is over but no one is dead.

public class FightLoop{

    //decided to make a separate nested Class, CombatNode for each combatant.
    class CombatNode{

        Character self;
        Character enemy;
        CombatNode next;

        CombatNode(){
        }//end CombatNode constructor

        void setValues(Character self_var, Character enemy_var, CombatNode next_var){
            self = self_var;
            enemy = enemy_var;
            next = next_var;
        }//end setValues()

    }//end class CombatNode



    //making a fight1v1() method, where 2 people roll initiative and take turns
    //hitting each other until one of them is dead.
    //
    public void fight(Character p1, Character p2){
        //get initiative of both characters
        int init1 = p1.rollInitiative();
        int init2 = p2.rollInitiative();

        CombatNode node1 = new CombatNode();
        CombatNode node2 = new CombatNode();
        node1.setValues(p1, p2, node2);
        System.out.println(node1.self.name);
        System.out.println(node1.self);


        boolean fight_over = false;
        // while(!fight_over){
        //     active.attack(active.enemy); //what is "active"? is it a Character? is it a Combatant/CombatNode inherits Character?
        //     if(enemy.hp <= 0){
        //         System.out.println(active.name + " pwned " + enemy.name);
        //         System.out.println(active.name + " wins!"); //maybe return the winning character.
        //         fight_over = true;  //end fight with this boolean switch.
        //     } else{
        //         nextTurn(); //change active character.
        //     }
        // }//end while(!fight_over)


        //
        //take turns to select character and attack the other
        //i will make a LinkedList Loop thingy that moves through turns.
        //ends when one of them is HP <= 0. announce the winner.
    }//end fight()



    // public static void main(String[] args){
    //     Integer[] arr = {7,5,11,8};
    //     Arrays.sort(arr, Collections.reverseOrder());
    //     System.out.println(Arrays.toString(arr));
    //
    // }//end main()



}//end class FightLoop
