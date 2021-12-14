## Task

Design and implement a simple member registry with CRUD (Create, Retrieve, Update, Delete) functionality. Implementation (Java source code), class- and sequence-diagrams are to be created and presented. The sequence diagrams should show how a model-view separation is achieved (i.e. start in the UI) and how the different requirements are met. The design and implementation should match perfectly.

The focus is not to create a very usable or fancy user interface but to have a robust and well-documented design that can handle change and follows the MVC, GRASP and possibly GoF patterns. The application should be a java console application.

OBS: It is not permitted to use any type of framework, however, the satandard class libraries, etc. are permitted. Basically, you should design and code your own application.

You may work in any way you like. A good process is to design a little, code a little, test a little, then iterate udate and add more. Designing everthing up-front is often hard and requires a lot of experience. Coding everything without a clear (partial) design can lead to problems, esp. in a group setting.

---

### CRUD, 	[ Create Retrieve Update Delete ]







---
## BRAINSTORM  
A registry is to be created. The controller is the user or admin. A member could also be seen using this kind of application.  
To get things going the controller is named Controller and pretty much contains all use cases.  
Some similarities -> **Create - Set | Retrieve - Get | Update - Set (with new data) | Delete - Remove |**    
  
View will simply be a console which prints info that has been read.   
Four areas has to be covered. Create member, Remove member, Update member, Retrieve member details.  
There has to be some kind of storage which loads up at start and saves at program exit.  
The database which holds the members and their information shall be called Register. The Register class will hold information about all members and their boats. This can be done in by having linked arrays.  
An example could be **[ MemberName, MemberID, BoatName, BoatType, BoatLength ] <-> [ MemberName, MemberID, BoatName, BoatType, BoatLength ] <-> [.... ]** etc.  
That the linked list holds both *next* and *previous* is not necessarily something that has to be implemented. Just *next* could suffice.  
   
### What parts are necessary?  
The model package should contain Member, Boat, Register. New classes could show up, but lets keep it simple.  

Some use cases will help writing the code by having some sequence diagrams plotted showcasing necessary associations and dependencies.  

### Use cases
I'm not entirely sure about who the main actor really is as of now..  
Since it is possible to delete *any* member and not only yourself (if actor is a member), makes me see this from a viewpoint as an admin and not a regular user.  
Of course the main actor could also be another system of some sort that requests something and this system responds to it.  Thus it is not up to this system to decide if the user is an admin or a regular member. I'm just speculating of course.  

For now, I will just follow the requirements and try to fulfill them.  
---
1. Create a new member
A member ID shall be generated and assigned to this member which is up to 6 alpha-numeric characters.  
The creation of the member ID itself has some requirements. It should 1) be unique and 2) be assigned to the new member.  
Since it is *assigned* to member and to reduce cluttered code, a new class **MemberID** can be added.  
The MemberID class shall handle the requirements on its own and when done, get assigned to Member.




  
  
---
### Current classes
- Member
- MemberID
- Boat
- Register  
---

### Controller package

The controller is used by a Person WHICH **could** be a member. Being a person is quite loosely defined, and the use of having that within the controller package is only when registering.  
This in turn creates a Member from a Person. Or it transforms a Person in to a Member, correctly speaking.  

