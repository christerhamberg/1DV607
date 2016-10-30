This might or might not aid in the understanding of the changes implemented by me:
Stand was added according to the sequence diagram
The hidden dependency ‘h’ ‘s’ ‘q’ ‘p’ was removed by checking what request that was sent in, as I think adding a “coded” menu like ‘0’, ‘1’, ‘2’ is just as bad in this case.
Soft17 was added
New winner rule added and hooked up to the same ruleFactory
Refactoring of the code: I opted for the solution to avoid letting other classes know about the Deck. Only dealer needs to know about the Deck, so this was my starting point for refactoring. Another option was just to make an internal private method and use that one, but as said this was dropped.
Dealer knows about player, dealer and deck. So the refactored code was moved to Dealer. Also considering a real live picture, dealer is drawing one card from the deck giving it to player or oneself when acting as both player and dealer and in the end showing or not the card. So it is also a realistic and logical place to handle the function supporting lower coupling and higher cohesion. 
As there is only one message that can be subscribed on I made a quick and dirty solution with a singleton but place it in its own package for possible further enhancement. Normally I tend to over-engineer things but this time I opted for the smallest possible solution. It still handles the registration and sending of the message like the pattern descries. No removal of subscription is possible though.
Good luck with the review.
///Chris
