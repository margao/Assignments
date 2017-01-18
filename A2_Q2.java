import java.util.Iterator;

public class A2_Q2 {
	
	public static void main(String[] args) {

		MyDeque<String> aq = new MyDeque<String>(10);
		
		//Adds to deque the sequence DECBAEC
		aq.add("A"); //index 0
		aq.add("B"); //index 9
		aq.add("C"); //index 8
		aq.add("E"); //index 7
		aq.offerFirst("D"); //index 6
		aq.offerLast("E"); //index 1
		aq.offerLast("C"); //index 2
		
		Iterator<String> itr= aq.iterator();

		System.out.println("Ascending iteration: ");
		for (int i = 0; i < aq.size(); i++){
			System.out.print(itr.next());
		}
		
		Iterator<String> ditr= aq.descendingIterator();
		
		System.out.println("\n\nDescending iteration: ");
		for (int i = 0; i < aq.size(); i++){
			System.out.print(ditr.next());
		}
		
		aq.removeFirstOccurrence("C");
		System.out.println("\n\nRemove first occurence of C.");
	
		Iterator<String> itr2= aq.iterator();

		System.out.println("\nAscending iteration: ");
		for (int i = 0; i < aq.size(); i++){
			System.out.print(itr2.next());
		}
		
		aq.removeLastOccurrence("E");
		System.out.println("\n\nRemove last occurence of E.");
	
		Iterator<String> itr3= aq.iterator();

		System.out.println("\nAscending iteration: ");
		for (int i = 0; i < aq.size(); i++){
			System.out.print(itr3.next());
		}

		aq.pollLast();
		aq.pollFirst();
		
		System.out.println("\n\nPoll first and last elements.");
		
		Iterator<String> itr4= aq.iterator();

		System.out.println("\nAscending iteration: ");
		for (int i = 0; i < aq.size(); i++){
			System.out.print(itr4.next());
		}
		
	}
}
