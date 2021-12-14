# Journal
---
## 30/9-2021
MemberID class added with possibility to generate a random ID.  
Checking for unique ID's is something to be noted, as of now there is a chance that two members can have the same ID.  


## 1/10-2021

### MemberID
MemberID generates a random ID. The requirement says it should be *unique*.  
Instead of having 6 random generated characters, there could be 5 instead. The sixth character could be a character that moves up one step per new member (A, B, C, D, etc...).  
Using both uppercase and lowercase this adds up to 26+26=52 characters.  
Now how big is the boat club? To ensure that there is spare room for new members with only uppercase letters are used, 2 characterscould be used instead.  
The ID will look something like this ===> [ Y Y - R R R R ] , Y can be A-Z, R is a randomly generated character that can range froma-z, A-Z, 0-9. 
Y will iterate from A-Z.   
This will ensure that each member has an unique ID.

### Registration of a new member  
I drew a sequence diagram of a new registration and will implement some code to see how things turn out.  
I'm not entirely sure about using datatype String for Members' names.  
Errorhandling could be within a Name class.  



---
### 4/10-2021  
I've experimented with some implementation to see if the sequence diagrams are of use, and they are.  
- A user can register to become a member.  
- A list can be shown, both verbose or compact (detailed VS compact).
- A unique ID is generated and is assigned to a member at registration.

Im making a domain model as of now to get a better grasp of the bigger picture and to not get lost.  
There are some things that has to be considered, especially with the View package.  
To reduce dependencies and to make everything as loosely coupled as possible, I have to map out the whole area the requirements cover.  
In case of change, the code should be able to adapt to that as well. I sense that things are *kind of* getting a bit cluttered, hence why I'm taking a step back.  
  
Input within Controller is to be moved to View as well.

---
### 5/10-2021 
Some ground has been covered today.  
Things that are working as of now:
- Search
- Registration
- Show current members (list)
   
I did some sequence diagrams to try to get a better grasp of the application.  
Later I did implementing which yielded quite positive results.  

The whole getMember() function within Register *should* use MemberID instead of the way its built now.  
I'll see if a fix is coming soon.

There is a problem that needs to be dealt with. Owned boats and their association.  
The requirements clearly stated that no keys/ID's were to be used, hence leaving associations via attributes.  
A member **HAS A** boat. This could be tied to MemberID as well...  

I'll experiment more tomorrow.  

---
To do next:
- Persistance
- Check attributes of User regarding register
- Work through the requirements, plot sequence diagrams for the different scenarios
- Gradle pipeline
---
