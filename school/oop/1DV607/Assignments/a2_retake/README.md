# Item Lending System 
---
#### Functional Requirements
1. Member
   1. Create with a name, email and mobile phone number. A _unique_ member id should be generated and assigned to the new member and the day of creation should be recorded.
       1. The member id should be 6 alpha-numeric characters.
       2. The email adress and phone number needs to be unique (no other members can have the same email or phone number).
   2. Delete a member.
   3. Change a member's information.
   4. Look at a specific members full information.
   5. List all members in a simple way (Name, email, current credits, and number of owned items)
   6. List all members in a verbose way (Name, email, information of all owned items (including who they are currently lent to and the time period))
2. Item
   1. Create a new item for a member, the item should have a category (Tool, Vehicle, Game, Toy, Sport, Other), a name, a short description, the day of creation should be recorded, and a cost per day to lend the item.
      1. When created the owning member gets 100 credits.
   2. Delete an item
   3. Change an item’s information.
   4. View an items information including the contracts for an item (historical and future)
3. Contract
   1. Establish a new lending contract with a starting day, an ending day and an item.
      1. Credits should be transfered according to the number of days and the price per day of the item
      2. Can only be done if the lender has enough credits.
      3. Can obly be done if the item is available during the time period
4. Time
   1. Time is handled as a day counter, 0 is the first day and is set when the system starts. Time is not connected to the system in this proof of concept.
   2. Advance day. In order to properly test the system there needs to be a way to advance the current day without relying on the system time.

5. _Prepare_ the design for persistence, i.e. add a persistence interface. Implement a hard coded "loading" of some members with boats, i.e. create some hard-coded data. You should __not__ implement any persistent loading or saving to file or database etc. for the passing grade.

#### Non-Functional Requirments
10. Strict Model-View-Controller architecture:
    1. The controller should be active and the view should be passive (controller depends on view, view does _not_ depend on controller).
    2. The view should only be able to read data from the model, not change it.
    3. The model should not depend on the view/controller (user interface) in any way (direct or indirect).
    4. The model should not have view/controller (user interface) responsibilities.
    5. The view/controller (user interface) should not implement model/domain functionality.
    6. The model should encapsulate business requirements and make them easy to reuse and make it hard to get the system into an "invalid state".
11. Good quality of code e.g. variable names, code duplication etc.
12. An object oriented design and implementation. This includes but is not limited to:
    1. Objects are connected using associations and not with keys/ids.
    2. Classes have high cohesion and are not too large or have too much responsibility.
    3. Classes have low coupling and are not too connected to other entities.
    4. No use of static variables and operations as well as global variables (the ´main´ method is the only exception).
    5. Avoid hidden dependencies, i.e. magic constants in different parts of the code.
    6. Enforce encapsulation, do not expose internal class design choices to the outside, do not expose un-needed functionality to classes in other packages.
    7. Use a natural design - let the domain model inspire the design
13. Simple error handling. The application should not crash but it does not need to be user friendly.
14. Proper use of versioning: there should be a number of commits (at least 20) that shows a natural progression of the application. "Big bang" delivery will result in a failed submission.
15. Use of gitlab build pipeline, gradle and quality-tests. Build using gradle regularley, fix any quality issues. Do not change the build-pipeline.
   1. Should be built using `./gradlew build`
   2. Should be run with `./gradlew run -q --console==plain`
16. You are free to add additional automatic tests of your code.
17. A class diagram that shows the final application - focus on classes/packages and relations (association, dependency, generalization, realization), add only some key attributes and operations. Use proper UML design class notation. The class diagram should correspond _exactly_ to the final implementation. Automatically reverse engineered diagrams are __not__ allowed. You should show that you have the skills to do such things manually.
18. A sequence diagram that corresponds to a scenario where a new third member is added to the system where there already exist two other members. It should involve objects with types from the model, view and controller (i.e. show the whole flow of messages). The sequence diagram should correspond _exactly_ to the final implementation and use proper UML notation. Automatically reverse engineered diagrams are __not__ allowed. You should show that you have the skills to do such things manually.
19. An object diagram that corresponds to the sequence diagram scenario.

### Deliverables
* Everything should be neatly available in your `grade_2`-branch as per the working process.
* Source code that can be immediately built and run by gradle. Do not add the compiled java files i.e. ´.class´ files, project files or other things to the project. There is a `.gitignore` that should cover most things but you may be working in a special environment, os etc. so take care. `git rm` is your friend.
* a `README.md` that explains usage of your application and if there are any parts missing.
* a `design.md` that shows your class diagram and sequence diagram and any text needed to explain the design.
