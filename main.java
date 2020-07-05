import java.util.*;  //for random, for ArrayList, for maybe other things I don't know about.
import java.lang.Math;

// v0.1.4 - 5/7/2020 -  Adding a new "highest hp" enemy selection in FightLoop.setNextEnemy(); Combining Team.resetTeam() and FightLoop.resetChain()
//---------------------------
///for future to-do items, search for words such as *** / ADD / note
///for more information, see Notes section below. (if available)
//---------------------------
//***note: I can change the enemy-selecting from
//"random enemy, until killed, then random enemy" to "always attack whoever has highest HP" (or lowest),
//this can simulate DM's behaviour as well.
//***then: (maybe after error estimation) compare the TPK chance for each attacking behaviour.
//--------------------------


//includes: the main class, or driver class.
public class main{

    public static void main(String[] args) {
        System.out.println("\n--------\n");

        CharacterCreator creator = new CharacterCreator(); //Method collection for character creation.
        Team players = new Team("Players"); //Basically an ArrayList of Characters, with a few other fields.
        Team monsters = new Team("Monsters grr");

        //Below's variables are:            name, Str, Dex, HP, Base AC, Damage dice number and type, team.
        Character player0 = creator.CreationV2("Robert", -1, 3, 18, 14, 3, 6, players);
        Character player1 = creator.CreationV2("Edgar", 0, 0, 31, 21, 1, 8, players);
        Character player2 = creator.CreationV2("Hayk", 3, 0, 28, 19, 1, 12, players);
        Character player3 = creator.CreationV2("Tigran", -1, 2, 21, 11, 1, 12, players);

        Character monster0 = creator.CreationV2("Werewolf", 2, 5, 105, 11, 2, 10, monsters);


        FightLoop fight1337 = new FightLoop();  //Basically, separate classes and methods for fight management.
        //System.out.println("Beginning fight fight2teams . . .");
        //Will select enemies randomly.
        DataBag data100 = fight1337.fight2Teams(players, monsters, 30, Math.pow(10, 5), "random"); //Basically, a collection of FightResults. The fights are recorded and put into the data bag.
        data100.calculations(); //Calculations using the fights data.
        //Now with selecting the enemy which has the highest HP:
        DataBag data200 = fight1337.fight2Teams(players, monsters, 30, Math.pow(10, 5), "highest hp");
        data200.calculations();
        //Now selecting the enemy with the lowest HP:
        DataBag data300 = fight1337.fight2Teams(players, monsters, 30, Math.pow(10, 5), "lowest hp");
        data300.calculations();

        //System.out.println("\n--------\n");
    }//end main()
}//end class dnd1

//Below is old code.
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

        // Fighter dredd = new Fighter("Dredd");   //the argument string is for the name attribute.
        // Fighter greg = new Fighter("Greg");
        // Character bob = new Character("Bob");
        //
        // //setting up teams . . .
        // Team party = new Team("Pro Party");
        // party.addMember(dredd);
        // party.addMember(bob);
        // party.addMember(greg);
