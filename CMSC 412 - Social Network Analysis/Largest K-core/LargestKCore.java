import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LargestKCore {

	public static void main(String[] args) throws IOException {
		String currentLine;
		String[] tokens;
		int numNodes;
		int numEdges;

		try {
			BufferedReader br = new BufferedReader(new FileReader("graph.txt"));
			currentLine = br.readLine();
			tokens = currentLine.split("\\s+"); // Blank space delimiter
			// First line of graph.txt corresponds to number of nodes and edges
			numNodes = Integer.parseInt(tokens[0]);
			numEdges = Integer.parseInt(tokens[1]);

			Graph graph = new Graph(numNodes, numEdges);

			// Rest of lines of graph.txt correspond to edge data
			// Iterate through graph.txt until all edges have been added to the
			// graph
			for (int i = 0; i < numEdges; i++) {
				currentLine = br.readLine();
				tokens = currentLine.split("\\s+");
				graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), 1); // Offset
																							// node
																							// naming
																							// (node
																							// 1
																							// corresponds
																							// to
																							// array
																							// index
																							// 0)
			}
			br.close();

			// Find max K-core
			int[] answer = graph.calcMaxKCore();

			// Output to file
			PrintWriter writer = new PrintWriter("kcore.txt", "UTF-8");
			writer.println(answer[0]);
			for (int i = 1; i < numNodes; i++) {
				writer.print(answer[i] + " ");
			}
			writer.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	/*
	 * Nested graph class which holds all pertinent graph data.
	 * 
	 */
	public static class Graph {
		int[][] edges; // Adjacency matrix
		int numNodes, numEdges;

		// Constructor
		public Graph(int n, int e) {
			numNodes = n;
			numEdges = e;
			edges = new int[n][n];
		}

		// Calculates maximum K core
		public int[] calcMaxKCore() {
			int maxDegree = 0;
			int[] answer = null;

			// Calculate max degree in set of nodes
			for (int i = 0; i < numNodes; i++) {
				int degree = this.getDegree(i);
				if (degree > maxDegree) {
					maxDegree = degree;
				}
			}

			int[] nodeDegrees = null;
			int kcore = 0;

			// Max degree of nodes corresponds to the highest possible value of k for k-core
			// Start from this max k-value and decrement by 1 until a valid k-core is found
			// Since we start from the maximum, this k-core is by definition the largest
			// possible value of k such that there exists a k-core.
			for (int j = maxDegree; j > 0; j--) {
				
				// Calculate size of subgraph after running k-core algorithm
				int size = 0;
				for (int k = 0; k < numNodes; k++) {
					// Size 0 corresponds to an empty graph
					if (this.calcKCore(j)[k] > 0) {
						size++;
					}
				}
				
				// If a valid graph is found then return answer
				if (size != 0) {
					nodeDegrees = this.calcKCore(j);
					kcore = j;
					answer = new int[size + 1];
					
					// Answer is formatted such that:
					// answer[0] corresponds to value of k for k-core
					// All following elements hold values corresponding to the node number in subgraph
					answer[0] = kcore;
					int current = 1;
					for (int z = 1; z < numNodes + 1; z++) {
						if (nodeDegrees[z - 1] != 0) {
							answer[current] = z;
							current++;
						}
					}
					return answer;
				}
			}
			return answer;
		}

		// Calculates possible k-core subgraph for given k
		public int[] calcKCore(int k) {
			int[] nodeDegrees = new int[numNodes];
			int[][] tempEdges = new int[numNodes][numNodes];

			// Make copy of adjacency matrix
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					tempEdges[i][j] = edges[i][j];
				}
			}

			// Make array such that each element corresponds to the total degree of the node corresponding
			// to that element number
			for (int i = 0; i < numNodes; i++) {
				nodeDegrees[i] = this.getDegree(i);
			}

			// Continuously delete nodes that have degree < k until there exists a node < k
			// and the lowest degree node != 0 (corresponding to an empty graph)
			while (this.getLowestDegreeNode(nodeDegrees) < k && this.getLowestDegreeNode(nodeDegrees) != 0) {
				for (int i = 0; i < numNodes; i++) {
					if (nodeDegrees[i] < k) {
						// Remove node from adjacency matrix
						for (int j = 0; j < numNodes; j++) {
							tempEdges[i][j] = 0;
							tempEdges[j][i] = 0;
						}

						// Recalculate degrees of nodes
						for (int q = 0; q < numNodes; q++) {
							int degree = 0;
							for (int w = 0; w < numNodes; w++) {
								degree += tempEdges[q][w];
							}
							nodeDegrees[q] = degree;
						}
					}
				}
			}
			return nodeDegrees;
		}

		// Helper method that calculates the lowest degree node, given a set of
		// node total degrees
		public int getLowestDegreeNode(int[] nodeDegrees) {
			int lowest = 0;
			for (int i = 0; i < numNodes; i++) {
				int d = nodeDegrees[i];
				if (d < lowest && d != 0 || lowest == 0) {
					lowest = d;
				}
			}
			return lowest;
		}

		// Add edge between source node and target node with given edge weight
		// to adjacency matrix
		// As adjacency matrix is necessarily symmetric for an undirected graph,
		// data is input twice.
		public void addEdge(int source, int target, int weight) {
			edges[source - 1][target - 1] = weight;
			edges[target - 1][source - 1] = weight;
		}

		// Helper method that calculates total degree for a given node
		public int getDegree(int node) {
			int degree = 0;
			for (int i = 0; i < numNodes; i++) {
				degree += edges[node][i];
			}
			return degree;
		}

	}
}
