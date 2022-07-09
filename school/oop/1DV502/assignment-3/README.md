# Assignment 3
These are the instructions for the final part of assignment 3. They complement the open instructions available on gitlab.

Note that this file is in md format, if you view it in a text editor it looks ok, but remember to not include for example ` surrounding commands if using cut n' paste.

## Setup
1. clone your assignment repository in a convenient directory - assignment-3 directory is created
2. unpack the contents of the zip file into the directory (for example using `unzip monopoly.zip -d assignment-3`). You should have the following structure:
```
 assignment-3 (this is what you get when you check out the assignment)
 |- README.md (this is what you are reading)
 |- design.md (describes the design of the monopoly game)
 |- .gitignore
 |- gradlew
 |- gradlew.bat
 |- settings.gradle
 |- build.gradle
 |- .gitlab-ci
 |- src
 |  |- main
 |  |  |- java (this is the root of the source code)
 |  |  |  |- monopoly
 |  |  |  |  |- Board.java
 |  |  |  |  |- Game.java
 |  |  |  |  |- Player.java
 |  |  |  |  |- Dice.java
 |  |  |  |  |- ConsoleUI.java
 |  |  |  |  |- Free.java
 |  |  |  |  |- HumanPlayer.java
 |  |  |  |  |- Start.java
 |  |  |  |  |- Tile.java
 |  |- test
 |  |  |- java (this is the root of the test source code)
 |  |  |  |- monopoly
 |  |  |  |  |- TierOneTests.java (contains the test that should work when done)
 |  |  |  |  |- CodeQualityTests.java
 |- img
 |  |- class_diagram.jpg
 |  |- object_diagram.jpg
 |  |- sequence_diagram.jpg
 |  |- buy_sequence_diagram.jpg
 |- config
 |  |- checkstyle
 |  |  |- google_checks.xml
 |- gradle
 |  |- wrapper
 |  |  |- gradle-wrapper.jar
 |  |  |- gradle-wrapper.properties
```
3. Run  `chmod +x gradlew` - this adds the execute permission for gradlew which is needed on *nix operating systems
4. Run `./gradlew build` in the console - there should be no errors, if there are try to sort them out or use course slack channel for help.
5. Run `git add .`
6. Run `git update-index --chmod=+x 'gradlew'` - this changes the permissions of gradlew so it can be executed on gitlab to build the code you push
7. Run `git status` (check so that all files per above are added)
8. Run `git commit -m "Initial commit"`
9. Run `git push`
10. Optional: go to gitlab and check that all files have been pushed, and that the pipieline is run (under CI/CD)
11. Optional: go to gitlab and create the release branch then you do not need to create it later.
12. Optional: add the Gradle Tasks extension to VS Code - this is the easiest option to get things running in vscode
13. Optional: Prepare your IDE (i.e VS Code) to work in the directory assignment-3 directory
14. Optional: Change the tab settings in your IDE to 2 spaces. This is the most significant change to the google coding standards

## Passing Grade Task
1. Study the design available in `design.md`
2. Check the test cases in `src/test/monopoly/TierOneTests.java`
3. Uncomment one test case, work from the top to the bottom
4. Run `./gradlew build` - test should fail (note that when you have started to make fixes the test case may succeed, and vice versa an old test case may stop working if you introduce some problem in your fixes)
5. Fix the corresponding implementation
6. Run `./gradlew build` - repeat from 3 as long as the test fails
7. commit, push
8. If there are more test cases left goto 3
9. You are done for the passing grade and can run/play the game (this can be done all the time but there will be a null pointer exception until the first Tile constructor is fixed, this is the first test case). You can probably use your IDE to start the game, or alternatively use gradle: `./gradlew run -q --console=plain`

## Higher Grade Tasks
1. Implement at least one Computer player, avoid code duplication and use the existing design. The computer player does not need to be smart in any way, it can act on random (you can even have several types of players and then pit them against each other)
2. Update the class diagram with the changes save as `img/updated_class_diagram.jpg`
  

## Notes
Depending on IDE you may be able to run the test cases directly in the IDE, but make sure everything works with gradle, as there are additional test cases that concern code quality (google format and find bugs), these alert you if you have additional issues.

The code standard you should follow is the google java standard. Most notable change is that indendation is 2 spaces. This can be configured in your IDE.

For the passing grade the task is correcting itself, and it runs a pipeline on gitlab to show this. For the final merge request everything should be passing.
  

