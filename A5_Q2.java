import java.util.*;

public class A5_Q2 {

	public static void main(String[] args) {

		MyGraph g = new MyGraph();
		
		/**
		 * 	Build following simple, undirected graph:
		 * 
		 * 	 1--2---3
		 * 	/|  |\  |\
		 * 0 |  8 \ | 4
		 * 	\|/ |  \|/ 
		 *   7--6---5
		 * 
		 * With following weights:
		 * 0 to 1 = 4
		 * 0 to 7 = 8
		 * 1 to 7 = 11
		 * 1 to 2 = 8
		 * 2 to 3 = 7
		 * 2 to 5 = 4
		 * 2 to 8 = 2
		 * 3 to 4 = 9
		 * 3 to 5 = 14
		 * 4 to 5 = 10
		 * 5 to 6 = 2
		 * 6 to 7 = 1
		 * 6 to 8 = 6
		 * 7 to 8 = 7
		 * 
		 * 
		 */

		g.addVertex(0);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		g.addVertex(7);
		g.addVertex(8);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(7, 8, 7);
		g.addEdge(7, 6, 1);
		g.addEdge(8, 2, 2);
		g.addEdge(8, 6, 6);
		g.addEdge(2, 5, 4);
		g.addEdge(2, 3, 7);
		g.addEdge(6, 5, 2);
		g.addEdge(5, 3, 14);
		g.addEdge(5, 4, 10);
		g.addEdge(3, 4, 9);

		System.out.println("Adjacency matrix: ");
		for (int i = 0; i < g.edges.length; i++) {
			for (int j = 0; j < g.edges.length; j++) {
				System.out.print(g.edges[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nDijkstra's Algorithm: ");

		for (int i = 0; i < 9; i++) {
			printPath(g, g.dijkstra(0), 0, i);
		}

		System.out.println("\nPrim's Algorithm: ");
		g.primMST(g.edges);

	}

	//Outputs path from source node (s) to target node (e)
	public static void printPath(MyGraph G, int[] pred, int s, int e) {
		final java.util.ArrayList path = new java.util.ArrayList();
		int x = e;
		while (x != s) {
			path.add(0, x);
			x = pred[x];
		}
		path.add(0, s);
		System.out.println("Shortest path from 0 to " + e + " " + path);
	}

}
