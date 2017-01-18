HOMEWORK #3
Problem 3-1 (Hash Tables)
CMSC-401
@author MATTHEW ARGAO


	      Installation:
-------------------------------------------
1. Extract zip contents to any folder.
2. That's it.


Programs:
-------------------------------------------
===========================================
cmsc401.java

Description:
-----------------------
Stores list of cities and coordinate data to a hash table.
City names are used as keys for the hashtable. 
Retrieve function retrieves latitude and longitude value corresponding to city.
Distance function calculates the distance between two cities in km using the Haversine formula.
User input ceases when "stop" is parsed. After which, the average length of linked lists per bucket is calculated for the hashtable.
Note: Arguments are case-sensitive (i.e. "retrieve dallas" will NOT work while "retrieve Dallas" will work).


Compile command: javac cmsc401.java
Run command: java cmsc401 cities.csv

Example: 
distance Boston, New York City
306
retrieve Dallas
32.7830556 -96.8066635
stop
10

===========================================
===========================================