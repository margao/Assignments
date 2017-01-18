import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class ICM {

	public static void main(String[] args) {

		String currentLine;
		String[] tokens;
		int numNodes;
		int numEdges;
		int seedSize; // Size of seed set
		int steps = 10000; // # of times we call runCascade for a given node.
						   // The higher this number the more accurate of a result calcAvgActivations is.
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("graph.txt"));
			currentLine = br.readLine();
			tokens = currentLine.split("\\s+"); // Blank space delimiter
			// First line of graph.txt corresponds to number of nodes and edges
			numNodes = Integer.parseInt(tokens[0]);
			numEdges = Integer.parseInt(tokens[1]);
			seedSize = Integer.parseInt(tokens[2]);

			Graph graph = new Graph(numNodes, numEdges);

			// Rest of lines of graph.txt correspond to edge data
			// Iterate through graph.txt until all edges have been added to the graph
			for (int i = 0; i < numEdges; i++) {
				currentLine = br.readLine();
				tokens = currentLine.split("\\s+");
				int weight = (int) (Double.parseDouble(tokens[2]) * 100);
				graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), weight); // Convert edge probability to integer from 1-100 for ease of calculation
			}
			br.close();

			// Calculate set of maximum influence with size equal to seedSize
			Set<Integer> maxInfluenceSet = calcMaxInfluenceSet(graph, seedSize, steps);
			
			// Print set elements to file
			PrintWriter writer = new PrintWriter("im.txt", "UTF-8");
			for (int i : maxInfluenceSet) {
				writer.print((i+1) + " ");
			}
			writer.println();
			// Calculate average number of activations for maximum influence set and write to file
			writer.print(calcAvgActivations(graph, maxInfluenceSet, steps));
			writer.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Calculates the set that gives the maximum influence on average when given desired size of seed set.
	 * 
	 * @param graph graph object
	 * @param seedSize desired size of seed set
	 * @param steps number of times to call runCascade, higher values correspond to more accurate calculations of average activations
	 * 
	 */
	public static Set<Integer> calcMaxInfluenceSet(Graph graph, int seedSize, int steps) {
		Set<Integer> seed = new HashSet<Integer>();

		double[] influence = new double[graph.numNodes];

		// Calculate influence of each node and place in influence array
		// where the influence array index corresponds to the node number
		for (int i = 0; i < graph.numNodes; i++) {
			seed.add(i); // add node i to set
			influence[i] = calcAvgActivations(graph, seed, steps); // calculate average activations for node i
			seed.remove(i); // remove node i (makes sure that set is empty each time we run calcAvgActivations so 
							// that we calculate influence of only a singular node
		}

		// Calculate the n nodes of highest influence
		// where n = seedSize
		for (int i = 0; i < seedSize; i++) {

			double maxValue = 0;
			int node = 0;
			// Iterate through influence array and find highest value
			for (int k = 0; k < graph.numNodes; k++) {
				if (influence[k] > maxValue) {
					maxValue = influence[k];
					node = k;
				}
			}
			seed.add(node); // Add highest value to seed set
			influence[node] = 0; // Remove highest value so that next highest value may be found
		}		
		return seed;
	}

	/*
	 * Calculates the average number of activations when given a certain seed set of nodes
	 * 
	 * @param graph graph object
	 * @param seed initial set of already activated nodes
	 * @param steps number of times to call runCascade, higher values correspond to more accurate calculations of average activations
	 * 
	 */
	public static double calcAvgActivations(Graph graph, Set<Integer> seed, int steps) {
		double sum = 0;

		// Call runCascade n number of times where n = steps
		for (int i = 0; i < steps; i++) {
			sum += runCascade(graph, seed);
		}
		
		double average = sum / (double)steps;
		
//		If you would like to debug this code, uncomment the following lines
//
//		System.out.print("Seed: ");
//		for (int i : seed) {
//			System.out.print((i+1) + " ");
//		}
//		System.out.println("\nAverage Activations: " + average);
		
		// Decimal formatting: round up, one decimal place
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return Double.parseDouble(df.format(average));
	}

	/*
	 * Simulates one cascade iteration when given a seed set of already activated nodes
	 * 
	 * @param graph graph object
	 * @param seed initial set of already activated nodes
	 * 
	 */
	public static int runCascade(Graph graph, Set<Integer> seed) {
		Set<Integer> activatedNodes = new HashSet<Integer>(seed); // Add seed set to activated nodes set
		Stack<Integer> stack = new Stack<Integer>();

		// Push activated nodes to stack
		// Nodes in stack will be tested individually to see whether they activate its neighbors
		for (int i : activatedNodes) {
			stack.push(i);
		}
		int currentNode;

		while (!stack.isEmpty()) {
			currentNode = stack.pop();
			if (!activatedNodes.contains(currentNode)){
				activatedNodes.add(currentNode); // Mark current node as activated if not already in activated nodes set
			}
			
			// For all neighbors of current node, check to see if neighbor is activated
			for (int i = 0; i < graph.numNodes; i++) {
				// If neighbor exists and hasn't already been activated
				if (graph.edges[currentNode][i] != 0 && !activatedNodes.contains(i)) {
					// generate random number 1-100 and check to see if it less than or equal to edge's probability of existing
					if (randNum() <= graph.edges[currentNode][i]) {
						stack.push(i); // if successfully activated, push activated node to stack to continue cascading
					}
				}
			}
		}
		// Finally, return number of activated nodes
		return activatedNodes.size();
	}

	// Generates random integer between 1-100
	public static int randNum() {
		Random rand = new Random();
		return rand.nextInt(100) + 1;
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
		public void addEdge(int source, int target, int weight) {
			edges[source - 1][target - 1] = weight;
		}

	}
}
