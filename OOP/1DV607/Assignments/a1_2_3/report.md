### Model not approved for passing grade
Comparing my model to the solution really shows how detailed my model is, without having proper details provided at all. From what I've read in the book the thing I've done is to "waterfall".  
The solution model meets the requirements and showcases a simple model which can be followed and still is held within the range of the requirements.  
There are a lot of classes that have been created by me without following the requirements, such as:
- Season
- MemberInquiry (with ChangeBoat and RegisterBoat)
- BerthDetails
- Port  

Classes like these can be implemented later on if needed but there is no need for them here in the initial domain model.  
They only take up space and make everything look cluttered and more advanced than it really is.  

  
The requirements involve
- Boat
- Berth
- Calendar/Event  
  
Calendar can be seen as a report class as stated in the solution, it is not really doing anything in the model (as of now with the current requirements).  
The events are what should be seen and the listing of them. Still, there is not much information to go on.  

---
### Differences  

Comparing my model to the solution shines light on major differences such as:
- Too many classes
- Diagram hard to follow
- Early implementation details w/o proper information
---
### What have I learned?

All of this is still quite new for me but seeing this comparison really has made some things easier to understand.  
My model simply is not just showing necessary relations between classes, it is detailed in ways that are outside of the requirements.  
There are many ways to implement code and the domain model should be a tool to get a grasp of a domain and its features. It should not be a visual representation that just *reflects*.  
Looking at a domain model should not require knowledge of code (though it certainly helps if you do). MemberInquiry with RegisterBoat/ChangeBoat does not provide much rather than making things look cluttered.   
My model is more *code-ish* than *domain-ish*. Some classes just *do* things, Secretary, Season and Port are good examples of this.  
The requirements should be met, designing further than that is time wasted since it is not I who is in charge of the requirements.  
Meaning that everything I add can be of no use if the client (or whoever who is in charge of the requirements) suddenly decides to shake things up.  
Thus the classes that definitely are needed is to be the ones included in the model. And the *needed* classes are the ones that fulfill the requirements.  

---
### To do differently in the future

- Keep it simple, don't overdo it
- Read the requirements carefully
- Stop modeling once in a while and compare the model to the requirements
- Do not implement things that are of no use 
