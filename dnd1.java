import java.util.*;  //for random

//v0.1.2! Actually used my code to estimate for our game... horribly. 0.1% of TPK was false, it was actually 32%.
//Gotta define version milestones somewhere.

//comments updated on 3/6/2020. Adding CharacterCreator.CreationV1 usage for calculating.
//May have a few fight-printing methods that repeat.
////
///for future to-do items, search for "***" and find them in comments.
///for more information, see Notes section below. (if available)

//to-dos:
//none
////


//includes: the main class, or driver class.
public class dnd1{

    public static void main(String[] args) {
        System.out.println("\n--------\n");

        //System.out.println("Testing over, now regular stuff. ");
        CharacterCreator creator = new CharacterCreator();
        Team players = new Team("Players");
        Character player1 = creator.CreationV2("Robert", -1, 3, 18, 14, 3, 4, players);
        Character player2 = creator.CreationV2("Edgar", 2, 1, 31, 16, 1, 12, players);
        Character player3 = creator.CreationV2("Hayk", 3, 0, 28, 19, 1, 10, players);
        Character player4 = creator.CreationV2("Tigran", -1, 2, 21, 11, 1, 10, players);

        Fighter dredd = new Fighter("Dredd");   //the argument string is for the name attribute.
        Fighter greg = new Fighter("Greg");
        Character bob = new Character("Bob");

        //setting up teams . . .
        Team party = new Team("Pro Party");
        party.addMember(dredd);
        party.addMember(bob);
        party.addMember(greg);

        Team monsters = new Team("Monsters grr");
        Character monster0 = creator.CreationV2("Werewolf", 2, 5, 105, 11, 2, 12, monsters); //12 weapon die is experimental for now.


        FightLoop fight1337 = new FightLoop();  //separate classes and methods for fight management.
        //System.out.println("Beginning fight fight2teams . . .");
        DataBag data1337 = fight1337.fight2Teams(players, monsters, 30, 100000);

        //System.out.println("\n\t\t Back in dnd1. ");
        //for(FightResult i : data1337.fight_list){
            //i.printResultv1();
        //}
        //data1337.printAllResults();
        data1337.calculations();

        //***note: I can change the enemy-selecting from
        //"random enemy, until killed, then random enemy" to "always attack whoever has highest HP" (or lowest),
        //this can simulate DM's behaviour as well.
        //***then: (maybe after error estimation) compare the TPK chance for each attacking behaviour.
    ////NO. Stop. Take a break. Stop coding. Return to this in a few days.
    ////Return to this on:
    ////11.6.2020


        System.out.println("\n--------\n");
    }//end main()
}//end class dnd1

/////////////
        // dredd.healthy();        //dredd tells us how healthy he is.
        // Character mike = new Character("Mike");
        //
        // dredd.talkTo(mike);
        // //mike.talk_to(dredd);      //same conversation, but names are reversed
        //
        // mike.attack(dredd);         //mike tries to attack dredd to lower his health.
        // mike.attack(dredd);
        // dredd.healthy();            //dredd reports his health after the fight

        // System.out.println("begin fight1v1 . . .");
        // fight1337.fight1v1(dredd, greg);        //my first FightLoop method.

        //just movement test. hm
        // dredd.pos_x = 10;
        // dredd.pos_y = -7;
        // dredd.move(-3, 9);
        // System.out.println(dredd.pos_x);
        // System.out.println(dredd.pos_y);
