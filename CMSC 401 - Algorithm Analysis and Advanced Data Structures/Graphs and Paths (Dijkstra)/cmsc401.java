/**
 * Homework #4
 * Hash Graphs and Paths
 * 
 * @author Matthew Argao
 * @email: margao@mymail.vcu.edu
 */

import java.util.*;
import java.io.*;

public class cmsc401 {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

		String str = scanner.nextLine();
		int nVertex = Integer.parseInt(str);
		str = scanner.nextLine();
		int nEdges = Integer.parseInt(str);

		WeightedGraph g = new WeightedGraph(nVertex);

		// Build city of size = nVertex
		for (int i = 0; i < nVertex; i++) {
			g.addVertex(i);
		}

		// Set prices for motels at each city
		// Motel prices in Richmond (City 1, array index 0) and Montgomery (City 2, array index 1) equal to 0
		g.motelPrices[0] = 0;
		g.motelPrices[1] = 0;
		for (int i = 0; i < nVertex - 2; i++) {
			str = scanner.nextLine();
			String[] tokens = str.split(" ");
			g.motelPrices[Integer.parseInt(tokens[0]) - 1] = Integer.parseInt(tokens[1]);
		}

		// Set edges with respective weights connecting each city
		for (int i = 0; i < nEdges; i++) {
			str = scanner.nextLine();
			String[] tokens = str.split(" ");
			g.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
		}

		scanner.close();

		// Print cheapest path from Richmond (City 1, array index 0) to
		// Montgomery (City 2, array index 1)
		printPathWeight(g, g.dijkstra(0), 0, 1);

	}

	// Outputs path from source node (s) to target node (e)
	public static void printPathWeight(WeightedGraph G, int[] pred, int s, int e) {
		final java.util.ArrayList<Integer> path = new java.util.ArrayList<Integer>();
		int x = e;
		while (x != s) {
			path.add(0, x);
			x = pred[x];
		}
		path.add(0, s);

		int total = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			total = total + G.getWeight(path.get(i), path.get(i + 1));
		}
		System.out.println(total);
	}

	/**
	 *
	 * Nested graph class 
	 * Source: http://cs.fit.edu/~ryan/java/programs/graph/WeightedGraph-java.html
	 *
	 */
	public static class WeightedGraph {

		int[][] edges; // adjacency matrix
		int[] motelPrices;

		public WeightedGraph(int n) {
			edges = new int[0][0];
			motelPrices = new int[n];
		}

		/**
		 * Add a new vertex into the graph. If the new vertex already exists in
		 * the graph then return false.
		 * 
		 * @param int
		 *            newNode: the vertex number of the new node.
		 * @return true if the vertex number >=0 and is not used; otherwise
		 *         false.
		 */
		public boolean addVertex(int newNode) {
			// if empty graph, add one node
			if (edges.length == 0) {
				edges = new int[1][1];
				edges[0][0] = 0;
				return true;
			}

			// if not empty graph
			if (edges.length > 0 && newNode > edges.length - 1) {
				int oldSize = edges.length;
				int newSize = edges.length + 1;

				// make copy array that is n+1 in length
				int[][] newEdges = new int[newSize][newSize];

				// copy old array to new array
				for (int i = 0; i < oldSize; i++) {
					for (int j = 0; j < oldSize; j++) {
						newEdges[i][j] = edges[i][j];
					}
				}

				// Set last element in each row of array to 0
				for (int k = 0; k < newSize; k++) {
					newEdges[oldSize][k] = 0;
				}

				// Fill last row of array with 0's
				for (int x = 0; x < newSize; x++) {
					newEdges[x][oldSize] = 0;
				}
				edges = newEdges;
				return true;

			}
			return false;
		}

		/**
		 * Add a new edge into the graph.
		 * 
		 * @param int
		 *            source: the vertex number of the source node.
		 * @param int
		 *            destination: the vertex number of the destination node.
		 * @param int
		 *            weight: the weight of the edge.
		 * @return true if the edge satisfies the following criteria: 1. valid
		 *         source node. 2. valid destination node. 3. weight > 0. 4. No
		 *         existing edge sharing the same source and destination;
		 *         otherwise false.
		 */
		public boolean addEdge(int source, int destination, int weight) {

			// Offset input to array index
			source--;
			destination--;
			// Check if source and destination are the same
			if (source == destination) {
				return false;
			}

			// Check if source and destination exist in matrix
			// Check if weight is greater than 0
			if (source < edges.length && destination < edges.length && weight > 0) {
				// Check if there already exists an edge
				if (edges[source][destination] == 0) {
					edges[source][destination] = weight;
					edges[destination][source] = weight;
					return true;
				}
			}
			return false;

		}

		/**
		 * Print the shortest distances and shortest paths of the graph.
		 * 
		 * @param int
		 *            source: the vertex number of the source node.
		 * @return true if the source node is valid; otherwise false.
		 * 
		 *         Source:
		 *         http://cs.fit.edu/~ryan/java/programs/graph/Dijkstra-java.
		 *         html
		 */
		public int[] dijkstra(int source) {

			final int[] dist = new int[edges.length];
			final int[] pred = new int[edges.length];
			boolean[] visited = new boolean[edges.length];

			// Start with source node which is at "infinite" distance to other
			// nodes
			for (int i = 0; i < dist.length; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			dist[source] = 0;

			// For each node:
			for (int i = 0; i < dist.length; i++) {
				// Find minimum weighted edge connected to current node
				final int next = minVertex(dist, visited);
				// Mark node as visited
				visited[next] = true;

				// Find how many nodes are connected to current node
				final int[] n = findNeighbors(next);

				// For each node connected to this node determine if node is
				// minimum
				// weight
				for (int j = 0; j < n.length; j++) {
					final int v = n[j];
					final int d = dist[next] + getWeight(next, v);
					if (dist[v] > d) {
						dist[v] = d;
						pred[v] = next;
					}
				}
			}

			return pred;
		}

		// Returns weight of select edge from source to target
		public int getWeight(int source, int target) {
			return edges[source][target] + motelPrices[target];
		}

		// Returns number of vertices connected to current vertex
		public int[] findNeighbors(int vertex) {
			int count = 0;
			for (int i = 0; i < edges[vertex].length; i++) {
				if (edges[vertex][i] > 0)
					count++;
			}
			final int[] answer = new int[count];
			count = 0;
			for (int i = 0; i < edges[vertex].length; i++) {
				if (edges[vertex][i] > 0)
					answer[count++] = i;
			}
			return answer;
		}

		// Find vertex with minimum weight edge
		public int minVertex(int[] dist, boolean[] v) {
			int x = Integer.MAX_VALUE;
			int y = -1;
			for (int i = 0; i < dist.length; i++) {
				if (!v[i] && dist[i] < x) {
					y = i;
					x = dist[i];
				}
			}
			return y;
		}
	}
}
