//import java.util.*;  //for random

//comments updated on 31.12.2019
//happy new year!

public class dnd1{

    public static void main(String[] args) {
        Fighter Dredd = new Fighter();
        Dredd.name = "Dredd";
        Dredd.healthy();

        Character Mike = new Character(/*"default"*/);
        Mike.name = "Mike";

        Dredd.talk_to(Mike);
        //Mike.talk_to(Dredd);      //same conversation, but names are reversed

        Mike.attack(Dredd);         //Mike tries to attack Dredd to lower his health.
        Mike.attack(Dredd);

        Dredd.healthy();            //Dredd reports his health after the fight

        // fighter Greg = new fighter();
        // Greg.healthy();

    }
}
