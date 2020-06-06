import java.util.*;  //for random

//v0.1.1! Gotta define version milestones somewhere.

//comments updated on 3/6/2020. Adding CharacterCreator.newFullCharacter usage for calculating.
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

        CharacterCreator creator = new CharacterCreator();
        Team players = new Team("Players");
        Character player1 = creator.newFullCharacter("Robert", 0, 3, 16, 14, players, 10);

            System.out.println("Testing over, now regular stuff. ");

        Fighter dredd = new Fighter("Dredd");   //the argument string is for the name attribute.
        Fighter greg = new Fighter("Greg");
        Character bob = new Character("Bob");

        //setting up teams . . .
        Team party = new Team("Pro Party");
        party.addMember(dredd);
        party.addMember(bob);
        party.addMember(greg);

        Team monsters = new Team("Monsters grr");
        Character monster0 = creator.newFullCharacter("Shalu", 2, 3, 58, 13, monsters, 12); //12 weapon die is experimental for now.


        FightLoop fight1337 = new FightLoop();  //separate classes and methods for fight management.
        System.out.println("Beginning fight fight2teams . . .");
        DataBag data1337 = fight1337.fight2Teams(party, monsters, 2000);

        System.out.println("\n\t\t Back in dnd1. ");
        //for(FightResult i : data1337.fight_list){
            //i.printResultv1();
        //}
        //data1337.printAllResults();
        data1337.calculations();


        // System.out.println("begin fight1v1 . . .");
        // fight1337.fight1v1(dredd, greg);        //my first FightLoop method.

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

        //just movement test. hm
        // dredd.pos_x = 10;
        // dredd.pos_y = -7;
        // dredd.move(-3, 9);
        // System.out.println(dredd.pos_x);
        // System.out.println(dredd.pos_y);
