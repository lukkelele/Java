# A3_2_3

Assignment 3, grade 2 and 3.


## Requirement 4 implementation
The soft 17 rule is implemented with test cases.  
The test cases test the Dealer.stand() method with the doHit() method from the rules.  
Different hands are valued at values that are either the same or under/over the value 17.  
Since the dealer is supposed to hit at values of 17 if there is an Ace, the amount of cards is predictable and can be measured to see if the rule is working or not.  
The same goes for all the test cases in one way or another.  
- If the hand is valued over 17, the amount of cards shall be unchanged.
- If the hand is valued under 17, the amount of cards shall be increased
- If the hand is valued at 17 with an Ace, hit. 
- If the hand is valued at 17 without an Ace, stand.  
---
