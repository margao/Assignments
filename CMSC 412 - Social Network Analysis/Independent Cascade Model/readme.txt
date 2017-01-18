Assignment #4
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
ICM.java

Description:
-----------------------
Maximizes influence under independent cascade model. Program reads graph from file
called "graph.txt" located in the same folder as the program and outputs 2 lines to "im.txt" in same folder.

Input: The file “graph.txt” includes multiples lines in which the first line contains three integers
n, m, and k that correspond to the number of nodes, the number of edges, and the size of the
seed set, respectively. Each of the following m lines contain three numbers u, v, and p_uv𝑝𝑢𝑣
separated by spaces, to denote an edge from u to v with a probability of existence p_uv. Nodes
are numbered from 1 to n.
The output file “im.txt” contains exactly 2 lines in which the first line contains 𝑘 vertices found
by the greedy algorithm and the second line contains the expected number of influenced nodes
in the end.

Compile command: javac ICM.java
Run command: java ICM

Example:
-----------------------
file: graph.txt
4 4 2
1 2 0.5
1 3 0.4
1 4 0.2
4 1 0.1


Run command input: java ICM

Output:
file: im.txt
1 4
2.9

===========================================
===========================================