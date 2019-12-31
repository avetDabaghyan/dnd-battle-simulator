//import stuff

//comments updated on 31.12.2019
//happy new year!


//includes: a DnD class Fighter. Parent is Character class because we need all the methods and fields that a regular person/character has.
//however, different classes will have different fields or additional methods. The first one is the Fighter.
public class Fighter extends Character{

    static int army = 0;        //just a random little variable that could be useful later

    Fighter(){
        //is this the constructor?
        //this is the constructor.
        super(/*"default"*/);   //no need to use the template for now.
        this.hp = 16;
        this.ac = 16;
        army++;     //army += 1 whenever a Fighter is created.
    }

    public void healthy(){      //method for keeping track of current HP.
        System.out.println("        -Hello! My current HP is: " + this.hp);
    }



    //we don't need main.
    //the class itself doesn't have to do anything
    //when program runs.
    // public static void main(String[] args) {
    // }
}
