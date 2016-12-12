## JetsProject
*J-E-T-S!* This project is a **JE**t **T**racking **S**ystem called **JE.T.S.**. It actually keeps track of all of your jet-powered aereoplanes. Neat, huh?

### Overview

**JE.T.S.** has a menu with 5 options:

1. List fleet:

   This option prints out the model, speed, range, and price of each jet. 
   * You get your first 5 jets, with a randomly chosen pilots, for free when the program starts
   * *Update:* Pilot info is now also included!

2. View fastest jet:

   Displays the information about fastest jet in your fleet.

3. View jet with longest range:

   Going on a long trip? This option displays the information about the jet with the longest range.

4. Add a jet to fleet:

   A user can add custom jets to the fleet. You will be prompted for the jet's make, model, range, and price. 
   We'll hire a pilot to fly your jet at no extra price!

5. Quit:

   Quit exits the program. (duh.)

Stuff that might get added:
* Users can hire pilots

### To Run

From the command line:
   `java JetsUI`


### Structure

Inspired by the [UML](https://github.com/SkillDistillery/SD8/blob/master/unit_1/week2/jets/UMLJets.png) in the assignment:

* Core Classes:
  * Jet
     * A jet abstraction
     * A Jet HAS-A Pilot
  * Pilot
     * A pilot abstraction
  * Hangar
     * A managed collection of Jet objects
     * A Hangar HAS-MANY Jets
  * Barracks
     * A managed collection of Pilot objects
     * A Barracks HAS-MANY Pilots
     * Static Helper Class - RandomPilotGenerator
* User Interface Clases:
  * JetsUI
     * Contains the primary main() method
     * A JetsUI HAS-A Hangar
     * A JetsUI HAS-A Barracks
     * A JetsUI HAS-A Menu
     * Helper Enum Class that extends Choosable interface
  * Menu
     * Generalized Menu/Input class
     * Helper Interface - Choosable



