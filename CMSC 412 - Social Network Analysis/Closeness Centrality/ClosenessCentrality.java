
/**
 * Assignment #2 
 * CMSC 412: Social Network Analysis & Cybersecurity Risks
 * 
 * @author Matthew Argao
 * @email: margao@mymail.vcu.edu
 */
import java.io.*;
import java.util.*;

public class ClosenessCentrality {

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
				graph.addEdge(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1,
						1); // Offset node naming
														// (node 1 corresponds
														// to array index 0)
			}
			br.close();

			// Array string where each node's weighted degree centrality is
			// separated by index
			tokens = graph.outputNormalizedCloseness();
			// Iterate and print each node's weighted degree centrality to a new
			// line
			PrintWriter writer = new PrintWriter("closeness.txt", "UTF-8");
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
	public static class Graph {
		int[][] edges; // Adjacency matrix
		int numNodes, numEdges;

		// Constructor
		public Graph(int n, int e) {
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

		// BFS of graph using queue FIFO structure
		// returns int array where each index corresponds to total distance from source node
		public int[] breadth(int source) {
			Queue<Integer> queue = new LinkedList<Integer>();
			int[] distanceTo = new int[numNodes];
			boolean[] visited = new boolean[numNodes];
			for (int i = 0; i < numNodes; i++) {
				distanceTo[i] = 0;
			}

			queue.clear();
			distanceTo[source] = 0;
			visited[source] = true;
			queue.add(source);

			while (!queue.isEmpty()) {
				int currentNode = queue.remove();

				for (int i = 0; i < numNodes; i++) {
					if (visited[i] == false && edges[currentNode][i] != 0) {
						distanceTo[i] = distanceTo[currentNode] + 1;
						visited[i] = true;
						queue.add(i);
					}
				}
			}
			return distanceTo;
		}

		// calculates normalized closeness given int array where each index corresponds to total distance from source node
		public double calcNormalizedCloseness(int[] distance) {
			double sum = 0;
			double n = (numNodes - 1);
			for (int i = 0; i < numNodes; i++) {
				sum = sum + distance[i];
			}
			return (n / sum);
		}

		// helper method calculates normalized closeness for each node in graph and outputs string array
		// string array index corresponds to chat node's calculated normalized closeness
		public String[] outputNormalizedCloseness() {
			String[] output = new String[numNodes];
			for (int i = 0; i < numNodes; i++) {
				output[i] = Double.toString(calcNormalizedCloseness(breadth(i)));
			}
			return output;
		}
	}
}