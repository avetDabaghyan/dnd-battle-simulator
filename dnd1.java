import java.util.*;  //for random

//v0.0.1. still at this version.
//comments updated on 18/4/2020. added to-do list for Operation "Encounter Balance"
////
/*
for future to-do items, search for "***" and find them in comments.
for more information, see Notes section below. (if available)


to-dos:
***sample to-do.
***??? Does actually estimating and calculating the encounter make it less human?

***prepare the Operation "Encounter Balance"
-how to export data? to json, excel?
--should be easy to do.

--i guess manual team-setting is okay.
-how do i UI all this? i'd like a UI for easy team-setting.
--do this later, get this all working first.
-will need conditions (% of victory with no downs; % of TPK; % of victory and 1 down, or 2 downs)
--create a Loop of FightLoops??? whenever FightLoop is over, save number of downs in a variable.
-where do I plan all this? here? Trello? txt file?

done - sample to-do 2.
done - now trying to make a separate way to set up and run 1v1 to-the-death fights.
done - set up teams. added another FightLoop method, with many supporting methods.
*/
////


//includes: the main class, or driver class.
public class dnd1{
//Testing git, without Eclipse. 6/2/2020
    public static void main(String[] args) {

        CharacterCreator creator = new CharacterCreator();
        // Character dude = creator.newCharacter();
        // System.out.println(dude.name);

        Fighter dredd = new Fighter("Dredd");   //the argument string is for the name attribute.
        Fighter greg = new Fighter("Greg");
        Character bob = new Character("Bob");

        //setting up teams . . .
        Team party = new Team("Pro Party");
        party.members = new Character[3];
        party.addMember(dredd, 0);
        party.addMember(bob, 1);
        party.addMember(greg, 2);


        Team monsters = new Team("Monster Muggers");
        monsters.members = new Character[1];
        monsters.addMember(new Character("Boi-0"), 0);


        FightLoop fight1337 = new FightLoop();  //separate classes and methods for fight management.
        System.out.println("Beginning fight fight2teams . . .");
        DataBag data1337 = fight1337.fight2Teams(party, monsters, 3);

        for(FightResult i : data1337.fight_data){
            System.out.println(i.fight_id);
        }

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
