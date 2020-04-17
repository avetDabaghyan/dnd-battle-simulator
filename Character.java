import java.util.*;     //for random. random.nextInt()
import java.lang.*;     //for Math. Math.max(a,b)

//comments updated on 24/1/2020 (edited roll() comments.)

//for future to-do items, search for "***" and find them in comments.
//for more information, see Notes section below.

//includes: a character class that should be the base for all people in the simulation.
//considering that items like weapons and chairs also have attributes like health, this might even be used for inanimate objects.
//also includes a (no longer nested) class, Weapon. Because all people will probably be carrying a weapon around.


public class Character{
    String name;
    int level = 1;

    //in DnD, every character has 6 attributes, also referred to as "stats"/"statistics"
    //they are used as modifiers for what the character is trying to do.
    int str;        //Strength
    int dex;        //Dexterity
    int con;        //Constitution
    int intel;      //Intelligence
    int wis;        //Wisdom
    int cha;        //Charisma

    int hp; //HP = health points (or hit points). HP = Constitution + a character's "hit dice" (not used here).

    int ac; //AC = Armor Class. Basically, this is the probability of avoiding attacks. AC = either A. Dexterity + Armor value, or B. Just armor value.

    int pos_x;  //
    int pos_y;  //
    int speed;  //how many spaces (including diagonally) can it move in 1 turn. e.g. 5 spaces up, or 5 spaces up + 5 spaces right.

    //This is the proficiency bonus. This is a regular, common bonus shared by everyone.
    int proficiency = 2;

    //moved nested class Weapon to a public class.
    //Because we're making a fighting simulator, we need weapons to fight.
    Weapon weapon;  //could be just int weapon_damage or int weapon_type, i don't know what's more correct.
                                    //i guess using a weapon class is good because 1 weapon should have all the information about itself
                                    //and in the future, this will be convenient for characters who swap weapons.

    Character enemy;                //this is for FightLoop class interaction. sets who should be attacked.
    Character next_turn;            //this is for FightLoop class interaction. sets who should go next. similar to LinkedList iteration.


    Character(String newName){     //constructor.
        name = newName;
        //these are the modifiers, NOT scores.
        str = 0;        //***need to add a rolling way to generate all these scores.
        dex = 0;
        con = 0;
        intel = 0;
        wis = 0;
        cha = 0;

        hp = 4;
        ac = 5;
        speed = 5;

        weapon = new Weapon("Shortsword"); //Let the shortsword be the default weapon.
    }//end Character constructor

    //about rolling
    //the format of rolling in DnD books is: XdY + Z, which means "roll Y-sided dice X times, then add Z".
    //e.g. 1d8 + 2 = roll a single 8-sided die and add 2. We get a number in range (3, 10).
    //this method: roll(X, Y, Z) = XdY + Z
    //e.g. roll(1,8,2) = 1d8+2
    public int roll(int amount, int type, int modifier){        //dice roller.
        Random ran = new Random();
        int result = 0;
        for(int i = 0;i < amount; i++){     //roll "amount" many dice
            result += (1 + ran.nextInt(type));  //each dice is added. number is in range (1, "type").
        }
        result += modifier; //in the end, add modifier bonus
        return result;
    }//end roll(amount, type, modifier)

    public int roll20(){        //shortcut method for single 20 rolling
        int result = this.roll(1, 20, 0);
        return result;
    }//end roll20()

    public int rollAdvantage(){ //take maximum of 2 d20's.
        int result = Math.max(roll20(), roll20());
        return result;
    }//end rollAdvantage()

    public int rollDisadvantage(){  //take minimum of 2 d20's.
        int result = Math.min(roll20(), roll20());
        return result;
    }//end rollDisadvantage()

    public int rollInitiative(){    //Initiative is used for determining turn order.
        int result = this.roll20() + dex;
        return result;
    }

    public void moveTo(int target_x, int target_y){
        //idk.
    }//end moveTo(target_x, target_y)

    public void move(int add_x, int add_y){
        if (Math.abs(add_x) <= speed){
            pos_x += add_x;
        }
        if (Math.abs(add_y) <= speed){
            pos_y += add_y;
        }
    }//end move(add_x, add_y)

    //this attacks target. dice is compared to target's AC. if >= it hits, and deals damage based on weapon.
    public void attack(Character target){
        //System.out.println("        -Have at thee, " + target.name);
        int combat_bonus = weapon.getCombatBonus(this);
        int atk_bonus = proficiency + combat_bonus;    //attack bonus includes proficiency bonus

        int attack_dice = roll20();
        if ((((attack_dice + atk_bonus) >= target.ac) || attack_dice == 20) && attack_dice != 1){     //if total attack >= target's AC, or if the attack is a crit:
            //System.out.println("Hit! " + Integer.toString(attack_dice) + " , " + Integer.toString(target.ac));
            if(attack_dice == 20) { dealDamage("crit", target, combat_bonus); }        //deal damage method from weapon. different for crit.
            else { dealDamage("", target, combat_bonus); }
        } else {      //else, miss the attack.
            System.out.println(name + " missed " + target.name);
            if(attack_dice == 1) { System.out.println(" -haha you fumbled you fool"); }       //if fumbled (the opposite of crit), make fun of attacker.
        }
    }//end attack target

    public int dealDamage(String crit, Character target, int combat_bonus){
        int damage = roll(weapon.dice, weapon.type, 0);
        if(crit == "crit") {        //if the attack was a crit (critical hit), then double the dice-only damage.
            damage *= 2;
        }
        damage += weapon.rarity;
        damage += combat_bonus;

        target.hp -= damage;
        System.out.println(name + " hit " + target.name  + " for " + Integer.toString(damage));

        return damage;  //don't need to return the damage yet. the damage is already dealt.
    }//end dealDamage()

    //just a silly method for testing 2 character's interaction
    public void talkTo(Character target){   //argument is Character, which is of non-basic data type.
        System.out.println("\n        -Hello! My name is " + this.name + ", what is your name?");
        System.out.println("        -Hi " + this.name + "! I am " + target.name + ". Good weather isn't it? \n");
    }//end talkTo target




    //main, for testing
    // public static void main(String[] args){
    //     character Mike = new character("default");
    //     System.out.println(Mike.str);
    //     System.out.println(Mike.roll(1,6,0));
    // }//end main

}





/////////////////////////////////////////////
//Notes
//
//Proficiency bonus is applied if the character is proficient in what they are doing.
//In this case, it is usually applied when attacking.
//Level 1 characters have Proficiency = +2. Higher level characters have higher Proficiency (but that doesn't scale linearly, see DnD PHB).
//
//in DnD, there are 2 representations of attributes: score and modifier.
//Scores are generally used to generate the modifiers.
//Modifier = 0 means average. They can be in range (-5, +5), sometimes higher.
//usually, we'll use modifiers in range (-2, +4). these are commonly seen in games.
//
//the number range of roll(X,Y,Z) = (X + Z, X*Y + Z)
