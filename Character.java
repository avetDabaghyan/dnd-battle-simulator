import java.util.*;     //for random. random.nextInt()
import java.lang.*;     //for Math. Math.max(a,b)

//comments updated on 31.12.2019
//happy new year!



//includes: a character class that should be the base for all people in the simulation.
//considering that items like weapons and chairs also have attributes/stats, this can even be used for inanimate objects.
//
//also includes a nested class, Weapon. Because all people will probably be carrying a weapon around.
public class Character{
    String name;
    int level;  //not used right now.

    //in DnD, every character has 6 attributes, also referred to as "stats"/"statistics"
    //they are used as modifiers for what the character is trying to do.
    int str;        //Strength
    int dex;        //Dexterity
    int con;        //Constitution
    int intel;      //Intelligence
    int wis;        //Wisdom
    int cha;        //Charisma

    //HP = health points (or hit points). Calculated using Constitution + a character's "hit dice" (not used here).
    int hp;

    //AC = Armor Class. Basically, this is the probability of avoiding attacks. Higher AC = higher probability.
    //Calculated using either A. Dexterity + Armor value, or B. Just armor value.
    int ac;

    //This is the proficiency bonus. This is a regular bonus shared by everyone.
    //This is applied if the character is proficient in what they are doing. In this case, it should be applied when attacking.
    //Level 1 characters have Proficiency = +2. Higher level characters have higher Proficiency (but that doesn't scale linearly).
    int proficiency = 2;

    //Because we're making a fighting simulator, we need weapons to fight.
    Weapon weapon = new Weapon();   //could be just int weapon_damage or int weapon_type, i don't know what's more correct.
                                    //i guess using a weapon class is good because 1 weapon should have all the information about itself
                                    //and in the future, this will be convenient for characters who swap weapons.


    Character(/*String template*/){     //constructor with templates
        //if (template == "default"){     //default template. I don't want to use these templates for now.
            name = "NPC";

            //in DnD, there are 2 representations of attributes: score and modifier.
            //We don't use scores right now. Just modifiers.
            //Modifier = 0 is average. They can be in range (-5, +5), sometimes higher.
            //usually, we'll use modifiers in range (-2, +4). these are common.
            level = 1;
            str = 0;
            dex = 0;
            con = 0;
            intel = 0;
            wis = 0;
            cha = 0;
            hp = 4;
            ac = 5;
            proficiency = 2;
            //weapon. Let the shortsword be the default weapon.
        //}                               //default template
    }//character constructor

    public int roll(int amount, int type, int modifier){        //dice roller. roll(4,5,6) = roll 4d5 + 6
        Random ran = new Random();
        int result = 0;
        for(int i = 0;i < amount; i++){     //roll "amount" many dice
            result += (1 + ran.nextInt(type));  //each dice is added. number is in range (1, "type").
        }
        result += modifier; //in the end, add modifier bonus
        return result;
    }//roll(amount, type, modifier)

    public int roll20(){        //shortcut method for single 20 rolling
        int result = this.roll(1, 20, 0);
        return result;
    }//roll20()

    class Weapon{
        int weapon_dice = 1;
        int type = 6;
        int rarity = 0;                 //some weapons also give a damage bonus. equal to its rarity.
        String name = "Shortsword";     //for special magical weapons. for later
        String usage = "str";           //can be str, dex, any.

        // Weapon(){     //do we need this constructor right now?  not really
        // }


        //i was thinking about whether the weapon should calculate its damage or the character.
        //for now, let the weapon return the damage that it should deal. this can be changed later anyway.
        //
        //this is used when an attack hits and now we need to calculate the damage.
        public int deal_damage(String crit, Character target, int combat_bonus){
            int damage = roll(weapon_dice, type, 0);
            if(crit == "crit") {        //if the attack was a crit (critical hit), then double the dice-only damage.
                damage *= 2;
            }
            damage += rarity;
            damage += combat_bonus;     //do we need this here? or in the attack()?
            target.hp -= damage;

            System.out.println("Dealt " + Integer.toString(damage) + " damage! ye");

            return damage;
        }//deal_damage()

        //combat bonus is used both when calculating the attack and the damage.
        //it is based on the type/usage of weapon.
        public int get_combat_bonus(){
            int combat_bonus = 0;
            if(usage == "any"){             //if weapon can be used using any modifier, take the highest.
                combat_bonus = Math.max(str,dex);
            }
            else{                           //if not, then use its modifier, Strength or Dexterity.
                if(usage == "str"){
                    combat_bonus = str;
                }
                else if(usage == "dex"){
                    combat_bonus = dex;
                }
            }
            return combat_bonus;
        }//get_combat_bonus()

    }//class Weapon

    //this attacks target. dice is compared to target's AC. if hits, deals damage based on weapon.
    public void attack(Character target){
        System.out.println("        -Have at thee, " + target.name);
        int combat_bonus = weapon.get_combat_bonus();
        int atk_bonus = proficiency + combat_bonus;    //attack bonus includes proficiency bonus

        int attack_dice = this.roll20();
        if (((attack_dice + atk_bonus) >= target.ac) || attack_dice == 20){     //if total attack >= target's AC, or if the attack is a crit:
            //System.out.println("Hit! " + Integer.toString(attack_dice) + " , " + Integer.toString(target.ac));

            if(attack_dice == 20) { this.weapon.deal_damage("crit", target, combat_bonus); }        //deal damage method from weapon. different for crit.
            else { this.weapon.deal_damage("", target, combat_bonus); }

        }
        else {      //else, miss the attack.
            System.out.println("Missed! " + Integer.toString(attack_dice) + " , " + Integer.toString(target.ac));
            if(attack_dice == 1) { System.out.println("haha you fumbled you fool"); }       //if fumbled (the opposite of crit), make fun of attacker.
        }
    }//attack target


    //just a silly method for testing 2 character's interaction
    public void talk_to(Character target){
        System.out.println("\n        -Hello! My name is " + this.name + ", what is your name?");
        System.out.println("        -Hi " + this.name + "! I am " + target.name + ". Good weather isn't it? \n");
    }//talk_to target


                //main, for testing
    // public static void main(String[] args){
    //     character Mike = new character("default");
    //     System.out.println(Mike.str);
    //     System.out.println(Mike.roll(1,6,0));
    // }

}
