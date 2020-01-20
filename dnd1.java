//import java.util.*;  //for random

//comments updated on 20/1/2020, 0.0.1
//now labeling with actual versions.
//this will be v0.0.1


//done - now trying to make a separate way to set up and run 1v1 to-the-death fights.

//includes: the main class, or driver class.


public class dnd1{

    public static void main(String[] args) {
        Fighter dredd = new Fighter("Dredd");   //the argument string is for the name attribute.

        // dredd.healthy();        //dredd tells us how healthy he is.
        // Character mike = new Character("Mike");
        //
        // dredd.talkTo(mike);
        // //mike.talk_to(dredd);      //same conversation, but names are reversed
        //
        // mike.attack(dredd);         //mike tries to attack dredd to lower his health.
        // mike.attack(dredd);
        // dredd.healthy();            //dredd reports his health after the fight

        Fighter greg = new Fighter("Greg");

        FightLoop fight1337 = new FightLoop();
        fight1337.fight1v1(dredd, greg);
    }//end main()
}
