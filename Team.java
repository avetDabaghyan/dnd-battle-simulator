
////
/*
for future to-do items, search for "***" and find them in comments.
for more information, see Notes section below. (if available)

to-dos:
***sample to-do.
done - sample to-do 2.
*/
////




public class Team{
        String name;
        Character[] members;

        Team(){
        }

        public void addMember(Character new_member, int position){
            //maybe use appending, instead of position? idk.

            this.members[position] = new_member;
            new_member.team = this;
        }
}
