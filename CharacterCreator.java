import java.util.*; //for Scanner

//27.4.2020 - I wanna do this with JSON. Gonna start using an actual IDE first. Probably Eclipse.
//2.6.2020 - Nope, not gonna use Eclipse, not now.
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

    Character newFullCharacter(String name, int str, int dex, int max_hp, int ac, Team team, int weapon_type){
        Character new_char = new Character(name);

        // new_char.level = level;
        new_char.str = str;
        new_char.dex = dex;
        // new_char.con = con;

        new_char.max_hp = max_hp;
        new_char.hp = max_hp;

        new_char.ac = ac;

        new_char.team = team;
        team.addMember(new_char);

        new_char.weapon.type = weapon_type;

        return new_char;
    }

    void saveCharacter(Character target){
        /*
        im gonna need the following:
        Saving:
            - a single ID, "LastUsedID", that will help with making a new character
            and make sure no two ID-s are the same.

        Loading:
            - search by name. OR by player name. OR by ID.

        */
    }

    Character loadCharacter(String name){
        return null;
    }

}//end class CharacterCreator
