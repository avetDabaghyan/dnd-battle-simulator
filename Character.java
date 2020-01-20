import java.util.*;     //for random. random.nextInt()
import java.lang.*;     //for Math. Math.max(a,b)

//comments updated on 20/1/2020 (added Advantage and Disadvantage)

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

    //This is the proficiency bonus. This is a regular, common bonus shared by everyone.
    int proficiency = 2;

    //Because we're making a fighting simulator, we need weapons to fight.
    Weapon weapon;  //could be just int weapon_damage or int weapon_type, i don't know what's more correct.
                                    //i guess using a weapon class is good because 1 weapon should have all the information about itself
                                    //and in the future, this will be convenient for characters who swap weapons.

    Character enemy;                //this is for FightLoop class interaction. sets who should be attacked.
    Character next_turn;            //this is for FightLoop class interaction. sets who should go next.

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
        weapon = new Weapon("Shortsword"); //Let the shortsword be the default weapon.
    }//end Character constructor

    public int roll(int amount, int type, int modifier){        //dice roller. roll(4,5,6) = roll 4d5 + 6
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

    public int rollAdvantage(){
        int result = Math.min(roll20(), roll20());
        return result;
    }//end rollAdvantage()

    public int rollDisadvantage(){
        int result = Math.max(roll20(), roll20());
        return result;
    }//end rollDisadvantage()

    public int rollInitiative(){
        int result = this.roll20() + dex;
        return result;
    }

    //moved nested class Weapon to a public class.

    //this attacks target. dice is compared to target's AC. if hits, deals damage based on weapon.
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
            System.out.println("Missed! " + Integer.toString(attack_dice) + " , " + Integer.toString(target.ac));
            if(attack_dice == 1) { System.out.println("haha you fumbled you fool"); }       //if fumbled (the opposite of crit), make fun of attacker.
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

        System.out.println("Dealt " + Integer.toString(damage) + " damage! ye");

        return damage;
    }//end dealDamage()

    //just a silly method for testing 2 character's interaction
    public void talkTo(Character target){
        System.out.println("\n        -Hello! My name is " + this.name + ", what is your name?");
        System.out.println("        -Hi " + this.name + "! I am " + target.name + ". Good weather isn't it? \n");
    }//end talkTo target




                //main, for testing
    // public static void main(String[] args){
    //     character Mike = new character("default");
    //     System.out.println(Mike.str);
    //     System.out.println(Mike.roll(1,6,0));
    // }

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
//usually, we'll use modifiers in range (-2, +4). these are common.
//
