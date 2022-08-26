## Design
---

A contract will move funds between members. This is done per day.  
The observer pattern could be of use here, with each day being a tick.  With each day passed, a daily check on a contracts termination day could take place.  
With a Calendar class of some sort, all contracts could be seen with their start and end dates. The Bank could make great use of this to foresee how much money is being transfered. If a member would go in debt, it is necessary for the bank to be able to have funds to spare. An overview of all contracts creates a way to plan for the future.  

An admin is in charge of new memberships which means that the Member class is not in need of being aware of the Calendar class. The date of newly created ID's is therefore available through the Admin class rather than having an association between Member and Calendar.  
  
Member and Bank will be associated because of Members withdrawing and depositing credits.  
