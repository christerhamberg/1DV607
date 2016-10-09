This README file highlights the thoughts I had about making the domain model for the Yacht Club.

Based on the main problems they had (I found 3 major issues) I divided these into 3 separate Domain Models.

- Administration
- Finance
- Calendar

Administration 

Covers both the user handling as well as the allocation of berths to the boats.
The reasoning behind this is that the secretary is the main actor, hence that person is likely to be the 
domain expert as well. So I kept these two tasks within the same domain, they could be split into their own domain as well.

Finance

When it came to finance I divided FEE into two special cases FixedFee and VariableFee. Yes they are both fees but a bit
like the "apple is a fruit" example. Somehow it just felt like the right thing to do. 

Calendar

I also broke out two special cases in the Events, Competition and Meetings. The reasoning for this is that 
they are events but they will probably be a bit different as a competition will probably have subclasses of boats
in the same competition. A starting list, a result list etc. Same kind of thoughts were applicable to the meetings.
They might require some preparation as well as a minutes of meetings and/or a vote. This was the thinking behind
separation of these two.

The use cases I broke down into sequence charts. I have a reason for this as well, and that is that the process
or order becomes more clear and can be discussed with the domain expert in a better way. I didn't plot all sequence charts back
to a box domain model. Simply because it didn't add much and the SW I used crashed a number of times when I tried to
covert them into box layout. The 3 main Domain Models (might lack a few data, associations and functions) but adding
them all into the picture does not make sense as that is just too much information. I would personally already remove some
of the listed data in the 3 domain model drawings I have just to clear them up and make them more useful, comments?

The association naming is perhaps not the best, I confess but I didn't find any reasonable fair names to use. Thoughts around this is more than welcome.
Also in real life I've not had much use of them, but added some.

Use Cases:

I Was not sure on how exact in detail one should model the use case according to the description. So here are some rough thoughts around the use cases:


Use Case 1 Authentication
- The role is derived from the memberID i.e. it would be returned when a successful login is made. 
- In the failed case a loop counter should be discussed with the domain expert to limit the number of tries a user has
  this is not in the Req. today.
- A hint for your password would be nice to have
- A reset mail etc. etc.
But these are new requirements.

Use Case 4 Register Boat

A number of things needs to be checked with the domain owner regarding the requirements.
- What about the case that there are no free berths?
- How should payment be done in case of offseason, 1st of January or when the berth is allocated? Etc.
  I considered that invoicing is done when berth is allocated. But this has to be cleared with the Domain Expert.
- I did not separate the rules for allocation into its own conceptual class, could be done or not. I chose not to.
  as the more rules you have the more combinations you need to test.

Use Case 5 Remove Boat

Well, should the member be removed as well, if they do not own any boats anymore? Invoicing, should a part of the fee be returned?
etc. etc. 

Use Case 6 Update Boat

Well the use case does not make sense... what can be updated, size => a new fee should be calculated, boat type => "renovation" of the boat has been done?
Somehow this is a odd use case and should be handled by the secretary in my opinion it should not be possible for a member to do.

And so on. There are lots of issues with the requirements.

However the main areas where I would be interested in comments are the association naming's, and what your opinion are if they make sense or not?
It might help to print the first 3 diagrams when checking the use case sequence diagrams.
I did not add dependency arrows, but consider most as dependent and no data would really be removed from the system.
Would it have been a good complement if the box diagram of the domain model had been updated after each use case with a higher level of details?


Good Luck, Chris

Comments, questions and thoughts you can always mail me at ch222xb@student.lnu.se, as I'm working full time and have these
courses as my evening and weekend hobby I'll try and answer them in the evening.   