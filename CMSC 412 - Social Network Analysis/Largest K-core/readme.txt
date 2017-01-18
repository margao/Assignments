Assignment #3
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
LargestKCore.java

Description:
-----------------------
Computes largest value of k such that there exists a k-core. Program reads graph from file
called "graph.txt" located in the same folder as the program and outputs 2 lines to "kcore.txt":
1st line is the value of the largest k such that there exists a k-core.
2nd line is a list of the nodes in the largest k-core, sorted in non-decreasing order.
If "kcore.txt" already exists it will be overwritten. A sample input and output file has been
included.

Compile command: javac LargestKCore.java
Run command: java LargestKCore


Example:
-----------------------
file: graph.txt
4 4
1 2
1 3
1 4
3 4

Run command input: java LargestKCore

Output:
file: kcore.txt
2
1 3 4

===========================================
===========================================