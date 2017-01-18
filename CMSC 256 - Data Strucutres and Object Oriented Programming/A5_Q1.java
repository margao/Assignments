import java.util.*;

public class A5_Q1 {

	public static void main(String[] args) {

		// Build matrix with following adjacency matrix
		int[][] adjacencyMatrix = { { 0, 1, 0, 0 }, { 0, 0, 0, 1 }, { 0, 1, 0, 0 }, { 1, 0, 1, 0 } };

		breathFirstTraversal(adjacencyMatrix, 0);
	}

	public static void breathFirstTraversal(int[][] aMatrix, int source) {
		QueueArray<Integer> queue = new QueueArray();
		
		//Calculate number of nodes
		int length = aMatrix.length;
		
		//Fill boolean array equal in length to number of nodes with false
		//This represents whether we have visited the node or not
		boolean[] visited = new boolean[length]; //all false initially

		//Queue source node and mark node as visited
		queue.enqueue(source);
		visited[source] = true;
		
		//While the queue is not empty, iterate through each node and visit all nodes that that node is connected to
		//Queue the node after marking it as visited.
		while (!queue.isEmpty()) {
			int i = queue.dequeue();
			System.out.print(i + " " + );
			for (int j = 0; j < length; j++) {
				if (aMatrix[i][j] > 0 && visited[j] == false) {
					queue.enqueue(j);
					visited[j] = true;
				}
			}
		}

	}

	//Nested queue array class
	//Source: http://eddmann.com/posts/implementing-a-queue-in-java-using-arrays-and-linked-lists/
	public static class QueueArray<T> {

		private T[] arr;

		private int total, first, next;

		public QueueArray() {
			arr = (T[]) new Object[2];
		}

		private void resize(int capacity) {
			T[] tmp = (T[]) new Object[capacity];

			for (int i = 0; i < total; i++)
				tmp[i] = arr[(first + i) % arr.length];

			arr = tmp;
			first = 0;
			next = total;
		}

		public QueueArray<T> enqueue(T ele) {
			if (arr.length == total)
				resize(arr.length * 2);
			arr[next++] = ele;
			if (next == arr.length)
				next = 0;
			total++;
			return this;
		}

		public T dequeue() {
			if (total == 0)
				throw new java.util.NoSuchElementException();
			T ele = arr[first];
			arr[first] = null;
			if (++first == arr.length)
				first = 0;
			if (--total > 0 && total == arr.length / 4)
				resize(arr.length / 2);
			return ele;
		}

		@Override
		public String toString() {
			return java.util.Arrays.toString(arr);
		}

		public boolean isEmpty() {
			if (total == 0) {
				return true;
			} else {
				return false;
			}
		}
	}

}
