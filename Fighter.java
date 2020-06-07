//import stuff

//comments updated on 11/1/2020
////
//for future to-do items, search for "***" and find them in comments.
//for more information, see Notes section below. (if available)

//to-dos:
//***sample to-do.
//done - sample to-do 2.
////
//includes: a DnD class Fighter. Parent is Character class because we need all the methods and fields that a regular person/character has.
//however, different classes will have different fields or additional methods. The first one I made is the Fighter.
public class Fighter extends Character{

    static int army = 0;        //just a random little variable that could be useful later

    Fighter(String newName){
        super(newName);   //get everything that a regular Character has. Argument is name attribute.
        this.max_hp = 12;
        this.hp = this.max_hp;
        this.base_ac = 16;   //have to figure out fighter standard values later.
        army++;     //army += 1 whenever a Fighter is created.
        updateStats();
    }

    public void healthy(){      //method for keeping track of current HP.
        System.out.println("        -Hello! My current HP is: " + this.hp);
    }



    //we don't need main.
    // public static void main(String[] args) {
    // }
}
