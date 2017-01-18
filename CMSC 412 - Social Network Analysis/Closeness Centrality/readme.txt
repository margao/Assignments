Assignment #2
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
ClosenessCentrality.java

Description:
-----------------------
Computes normalized closeness centrality of an undirected graph. Program reads graph from file
called "graph.txt" located in the same folder as the program and outputs the normalized 
closeness centrality of the nodes to a file called "closeness.txt". If "closeness.txt" 
already exists it will be overwritten. A sample input and output file has been
included.

Compile command: javac ClosenessCentrality.java
Run command: java ClosenessCentrality


Example:
-----------------------
file: graph.txt
3 2
1 2
1 3

Run command input: java ClosenessCentrality

Output:
file: closeness.txt
1.0
0.6666666666666666
0.6666666666666666

===========================================
===========================================