import java.util.*; //for Scanner

//27.4.2020 - I wanna do this with JSON. Gonna start using an actual IDE first. Probably Eclipse.
public class CharacterCreator{
    // ***maybe use a database thing, like XML or SQL, instead of JSON? table-based data would be much more convenient to know for future.


    Scanner sc = new Scanner(System.in);
    CharacterCreator(){

    }

    Character newCharacter(){
        System.out.println("Enter name for character: ");
        String new_name = sc.nextLine();

        //maybe set up quick template selections? like choose class Fighter, choose weapon, etc.

        return new Character(new_name);
    }//end newCharacter()

    // void saveCharacter(Character target){
        /*
        im gonna need the following:
        Saving:
            - a single ID, "LastUsedID", that will help with making a new character
            and make sure no two ID-s are the same.

        Loading:
            - search by name. OR by player name. OR by ID.

        */
    // }

    // Character loadCharacter(String name){

    // }


}//end class CharacterCreator
