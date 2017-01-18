
public class A3_Q2 {

	public static void main(String[] args) {

		MyPriorityQueue minHeap = new MyPriorityQueue();

		minHeap.enqueue(2);
		minHeap.enqueue(13);
		minHeap.enqueue(15);
		minHeap.enqueue(9);
		minHeap.enqueue(25);
		minHeap.enqueue(12);
		minHeap.enqueue(35);
		minHeap.enqueue(24);

		minHeap.printElements();
		System.out.println("Heap contents: ");
		minHeap.dequeue();
		minHeap.printElements();
		System.out.println("Heap contents: ");
		minHeap.dequeue();
		minHeap.printElements();
		System.out.println("Heap contents: ");
		minHeap.dequeue();
		minHeap.printElements();
		System.out.println("Heap contents: ");
		minHeap.dequeue();
		minHeap.printElements();
		System.out.println("Heap contents: ");
		minHeap.dequeue();
		minHeap.printElements();
		
	}

}
