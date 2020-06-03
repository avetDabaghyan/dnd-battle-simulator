
////
//for future to-do items, search for "***" and find them in comments.
//for more information, see Notes section below. (if available)

//to-dos:
//none
////

//includes: a weapon class that has its own properties.
//i think i can include many templates in the constructor, for different weapons.
public class Weapon{
        int dice;
        int type;
        int rarity;             //some weapons also give a damage bonus. equal to its rarity.
        String name;            //for other weapons.
        String usage;           //can be str, dex, any.

        Weapon(String template){
            name = template;

            if(template == "Shortsword"){
                dice = 1;
                type = 6;
                rarity = 0;
                usage = "any";
            }//Shortsword template
        }//end Weapon constructor


        //combat bonus is used both when calculating the attack and the damage.
        //it is based on the type/usage of weapon.
        public int getCombatBonus(Character owner){
            int combat_bonus = 0;
            if(usage == "any"){             //if weapon can be used using any modifier, take the highest.
                combat_bonus = Math.max(owner.str,owner.dex);
            }
            else{                           //if not, then use its modifier, Strength or Dexterity.
                if(usage == "str"){
                    combat_bonus = owner.str;
                }
                else if(usage == "dex"){
                    combat_bonus = owner.dex;
                }
            }
            return combat_bonus;
        }//end getCombatBonus()

}//class Weapon
