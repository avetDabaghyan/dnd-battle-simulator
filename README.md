# dnd-battle-simulator
(updated 7/5/2020, release of v0.1.4)
Sometime long ago, I wanted to make a very simplistic battle simulator using combat rules of DnD. I wanted to make this scaleable and simulate large scale armies.
Now, this is gonna be used to calculate fight results for actual games. I already started using this to predict fight outcomes for some of my games.

Note: this README and the comments inside are designed to be beginner-friendly.


How to use: Input your characters, the monsters and their stats; output is showing you the probability of a TPK or a victory.
Right now, this isn't exactly usable - you have to input your characters in main.java, using the creator.CreationV2(...) method. There is no GUI to do it, which is my main concern for the future.

Development stack:
* Running environment: command line. (I want to use an IDE like Eclipse, but would like to start using it on another project.)
* Java version: 12.0.2

To try it out:
* Clone/download this;
* In command line, go to the folder using `cd folder-name`;
* Compile the main(driver) class, with `javac main.java`;
This should compile all other .java files as well, creating the .class files for each.
If it doesn't work, then use `javac .`, which should compile all files in this folder (denoted by the ".");
* Run it with `java main`; the output should be visible in the command line.
You'll see numbers and percentages - the percentage is for the probability of a TPK.

If there are issues with running this, let me know.
Also let me know if you have any suggestions.

-Avet
avet0.dabaghyan@gmail.com

---
Short version history
---

v0.1.3 - 12/6/2020 - Updating comment structures.
5/7/2020 - Updated comments to make this repo public.

v0.1.4 - 5/7/2020 - Adding a new "highest hp" enemy selection in FightLoop.setNextEnemy(); Combining Team.resetTeam() and FightLoop.resetChain()
