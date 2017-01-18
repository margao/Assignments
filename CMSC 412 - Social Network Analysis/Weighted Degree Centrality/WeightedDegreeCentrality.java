
/**
 * Assignment #1 
 * CMSC 412: Social Network Analysis & Cybersecurity Risks
 * 
 * @author Matthew Argao
 * @email: margao@mymail.vcu.edu
 */
import java.io.*;

public class WeightedDegreeCentrality {

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

			WeightedGraph graph = new WeightedGraph(numNodes, numEdges);

			// Rest of lines of graph.txt correspond to edge data
			// Iterate through graph.txt until all edges have been added to the
			// graph
			for (int i = 0; i < numEdges; i++) {
				currentLine = br.readLine();
				tokens = currentLine.split("\\s+");
				graph.addEdge(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1,
						Integer.parseInt(tokens[2])); // Offset node naming
														// (node 1 corresponds
														// to array index 0)
			}
			br.close();

			// Array string where each node's weighted degree centrality is
			// separated by index
			tokens = graph.outputCentrality();
			// Iterate and print each node's weighted degree centrality to a new
			// line
			PrintWriter writer = new PrintWriter("wdegree.txt", "UTF-8");
			for (int i = 0; i < numNodes; i++) {
				writer.println(tokens[i]);
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
	public static class WeightedGraph {
		int[][] edges; // Adjacency matrix
		int numNodes, numEdges;

		// Constructor
		public WeightedGraph(int n, int e) {
			numNodes = n;
			numEdges = e;
			edges = new int[n][n];
		}

		// Add edge between source node and target node with given edge weight
		// to adjacency matrix
		// As adjacency matrix is necessarily symmetric for an undirected graph,
		// data is input twice.
		public void addEdge(int source, int target, int weight) {
			edges[source][target] = weight;
			edges[target][source] = weight;
		}

		// Iterate through given node's row in adjacency matrix, sum weight of
		// edges incident to node and return total weighted degree
		public int calcWeightedDegree(int node) {
			int weightedDegree = 0;
			for (int i = 0; i < numNodes; i++) {
				weightedDegree += edges[node][i];
			}
			return weightedDegree;
		}

		// Helper method to calculate weighted degree for all nodes in adjacency
		// matrix
		// Returns string array
		public String[] outputCentrality() {
			String[] output = new String[numNodes];
			for (int i = 0; i < numNodes; i++) {
				output[i] = Integer.toString(calcWeightedDegree(i));
			}
			return output;
		}

	}
}
