HOMEWORK #4
Problem 4-1 (Graphs and Paths)
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
Reads list of vertexes, edges and weights and stores them in an adjacency matrix.
In this case, vertexes, edges and weights represent cities, road connections and gas cost to travel between cities.
Motel cost per city is stored in a 1D array as well.
Using Dijkstra's algorithm, the cheapest route between two cities Richmond (City 1) and Montgomery (City 2) is calculated.
Motel cost and gas cost is considered together during execution of Dijkstra's algorithm.

Note: Although Richmond is referred to as City 1 and Montgomery is referred to as City 2, these two cities are represented within
the adjacency matrix with indices that are -1 of those values. I.E. In the adjacency matrix, city 1's adjacencies are represented by the row at index [0][*].
City 2's adjacencies are represented by the row at index [1][*] etc.


Compile command: javac cmsc401.java
Run command: java cmsc401 < input.txt

Example:
Contents of input.txt:
5
7
3 78
4 87
5 98
1 4 98
5 4 45
1 5 140
4 3 87
2 5 150
3 5 109
3 2 73


Output:
388

===========================================
===========================================