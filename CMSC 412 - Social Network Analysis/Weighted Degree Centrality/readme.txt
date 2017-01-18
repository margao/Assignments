Assignment #1 
CMSC 412: Social Network Analysis & Cybersecurity Risks
@author MATTHEW ARGAO
@email: margao@mymail.vcu.edu

	      Installation:
-------------------------------------------
1. Extract zip contents to any folder.
2. That's it.


		Programs:
-------------------------------------------
===========================================
WeightedDegreeCentrality.java

Description:
-----------------------
Computes weighted degree centrality of an undirected graph. Reads graph data from "graph.txt" 
in directory where WeightedDegreeCentrality.java is located. Outputs the degree centrality of 
all nodes to a file called "wdegree.txt" in same directory which consists of n lines in 
which the k-th line is the unnormalized weighted degree centrality of node k. If "wdegree.txt" 
already exists it will be overwritten. A sample input and output file has been
included.

Compile command: javac WeightedDegreeCentrality.java
Run command: java WeightedDegreeCentrality


Example:
-----------------------
file: graph.txt
3 2
1 2 4
1 3 7

Run command input: java WeightedDegreeCentrality

Output:
file: wdegree.txt
11
4
7

===========================================
===========================================