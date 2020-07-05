import java.util.*; //for Scanner

//includes: a place to quickly assign stats and create Characters.
//ADD Saving and loading characters from an external file.
public class CharacterCreator{
    // ***maybe use a database thing, like XML or SQL, instead of JSON? table-based data would be much more convenient to know for future.


    Scanner sc = new Scanner(System.in);
    CharacterCreator(){

    }

    public Character newCharacter(){
        System.out.println("Enter name for character: ");
        String new_name = sc.nextLine();

        //maybe set up quick template selections? like choose class Fighter, choose weapon, etc.
        return new Character(new_name);
    }//end newCharacter()

    //***do I need to make these methods public? I got a java.lang.IllegalAccessError, so trying to fix.
    public Character CreationV2(String name, int str, int dex, int max_hp, int base_ac, int weapon_dice, int weapon_type, Team team){
        Character new_char = new Character(name);

        // new_char.level = level;
        new_char.str = str;
        new_char.dex = dex;
        // new_char.con = con;

        new_char.max_hp = max_hp;
        new_char.hp = max_hp;

        new_char.base_ac = base_ac;

        new_char.weapon.dice = weapon_dice;
        new_char.weapon.type = weapon_type;

        new_char.team = team;
        team.addMember(new_char);
        //Finished. Returning...
        new_char.updateStats();
        return new_char;
    }//end CreationV2

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
    }//end CreationV2

}//end class CharacterCreator




///////////////Old code
/*
Character CreationV1(String name, int str, int dex, int max_hp, int ac, Team team, int weapon_type){
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
}//end CreationV1
*/
