Some comments and thoughts that might help you understand the logic of the program, or not.

At work we never do a MWC layout we use MW instead, though most cases they go hand in hand and we need 
to update both anyway as we add new things to display or to store so somehow we need to get the data and to
be able to display it. So I opted for adding the Controller just to see if it really would add something
other that a mess and an additional box in the diagram.

I also found and started to use UMLet (http://www.umlet.com/) for the drawings it is freeware and simple enough 
to learn in an hour or so and looks quite okay.

Assumptions:
* The database will not be that large, but to avoid looping around and around hash Maps are used to store the data.
* Objects are not passed around rather the hash key is.
* In a real application the storage would probably be an SQL DB instead as that would automatically solve the need
  for storage to file and a lot of the search / filter stuff needed for grade 3 and 4.
* No real focuse has been made to make the console look nice as it would be replaced withe a WWW / GUI in a real case.
* When removing something it is removed from the chache store and the file. Data can not be restored.

YachtClubMain:
* contains the main function 
* use parameter --file <path> to where your files are stored or where you'ed like to create a new set of members/boats.

YachtClubController:
* is the controller that runs a loop until you request to quit
* Each use case is also modelled into a use case method

Boat:
* just stores the data of the boats
* has some get/set methods
* a writeToFile method that formats the output to the boat file
* uses one static variable BOAT_TYPES I kept this as I never remember that static stuff should be underlined in UML
  I know that according to the task it one should really not use any statics but as said I keep it as a UML syntax reminder

Member:
* Like Boat but for the member with set/get/writeToFile methods

CacheStorage:
* is the coordinator between the chached store and the fileStore 
* has two hashMaps over memberData and boatData

FileStorage:
* stores the data to three (3) different files id,member,boat all .csv files
* id only holds the number
* member only stores the member data
* boat only the boat data
* rebuildCache is called when the program is started by the CacheStorage and the files are read and data
  restored

UI:
* Well some ugly looking menues etc.

Some improvements:
* For example I didn't make a nice inc/dec method in Member when I store the number of boats, I used the get/set methods instead
  could make sense to add an inc/dec method as it would look nicer.
* Some Error codes will be added (when moving to the higher grades)
* I could have used for loops instead of actually keeping track of the hashkeys, but well... I would really have
  used an SQL DB or ElasticSearch or someother DB as then I would get a lot of search/filter options for free as 
  well as the file storage. I just assumed that we should avoid this in the task.
* There are some minor bugs in the program, have fun finding them ;)
  
Good luck!

Christer Hamberg (ch222xb@student.lnu.se)

