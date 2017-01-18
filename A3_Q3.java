
public class A3_Q3 {

	public static void main(String[] args) {

		System.out.println(BinarySearch(args));  //O(1)
		
	}

	public static boolean BinarySearch(String[] args) {
		int low = 0; //O(1)
		int high = args.length - 1; //O(1)
		
		
		//The following is essentially a reworking of the binary search algorithm
		//If element at index is greater than the index itself then this invalidates the rest of the terms greater than the index
		//This is true because the initial matrix must be of the form A[0] < A[1] < A[2] < . . . < A[N-1].
		//Similarly for the case where the element is less than the index itself
		//The number of steps needed thus follows the same as a binary search
		//Ergo O(log n)
		while (low <= high) {
			int index = (low + high) / 2;
			int compare = Integer.parseInt(args[index]);
			
			if (compare > index) {
				high = index - 1;
			} else if (compare < index) {
				low = index + 1;
			} else if (compare == index) {
				return true;
			}
		}
		return false;

	}
}
