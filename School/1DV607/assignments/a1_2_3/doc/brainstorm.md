#

# !! OUTDATED !!
## was made for getting a grasp of the task  

# Initial thoughts
The structure of the yacht club is in chaos as a whole.
There is issues regarding:
- Communication
- Bookings
- Payments

Members have a hard time keeping track of all information that is said within the club.
There is no simple approach to get "your" spot if you have several boats, thus endorcing members to trade spots which is against the rules.




## Brainstorm for conceptual classes
Secretary, Treasurer, Member, Boat, Berth, YachtClub, CreditCard, Calendar, Event

The requirements involve boats, berth assignments and dates regarding events.
Payment is not mentioned in the requirements which takes it for granted that whenever a berth is assigned, the payment made for it is considered correct and without error. Which makes us able to ignore the economy aspect as of now.

---
## Tidying up the conceptual classes

Since the economy is ignored for now, the conceptual classes can be narrowed down to:

- Club
- Secretary
- Boat
- Berth
- Calendar
- Event
- Member
+ MemberDetails
+ Registry

---
The secretary needs a sort of booking system whereas the different berths can be assigned to members and the members themselves can take a look at the berths booked.
Members with multiple boats want their "usual" spot without having to have their boats randomly placed elsewhere since the spots close to their initial spot is already taken.
The secretary is the one doing the booking, thus by making this process as effective as possible is a main focus. 

## Assigning berths
As of now, assigning berths seems to be in favor of members that are picked first when the secretary begin to work in the spring. 
It is very important that the berths that are no longer assigned to a member (if a member sells a boat or leaves the club) are updated first whenever it is time to assign members to berths.
Members with multiple boats should be something that the secretary is aware of, and this information should all be contained within each member and not with each boat individually.

A registry that contains all members necessary information is a solution to this problem. If a member has multiple boats the secretary will be able to see this by checking the name of the member.






---
# !Important!
My interpretation of the model in question was in a manner of that the secretary was in *charge* of both berth assignments and whenever a member had requests.
I see now that a **system** should handle inquiries from members and not the secretary. Since this is a *real* yacht club, creating software would be of no use if the secretary was the one to deal with all requests from members.
