
public class A4_Q3 {

	//Based on http://java2novice.com/java-sorting-algorithms/quick-sort/
	public static void main(String[] args) {
		
		int[] input = { 22, 1, 34, 56, 23, 74, 1, 0, 1, 80, 100, 66, 22 };
		
		System.out.println("Before quicksort: ");
		for (int i : input) {
			System.out.print(i);
			System.out.print(" ");
		}
		
		quickSort(0, input.length-1, input);

		System.out.println("\n\nAfter quicksort: ");
		for (int i : input) {
			System.out.print(i);
			System.out.print(" ");
		}
	}

	public static void quickSort(int low, int high, int[] array) {
		//Set lower and upper bounds of index
		int i = low;
		int j = high;

		//Set pivot to element at approx half index
		int pivot = array[low + (high - low) / 2];

		//Divide into halves
		while (i <= j) {
			//increment i if it satisfies being less than pivot
			while (array[i] < pivot) {
				i++;
			}
		
			//decrement j if it satisfies being more than pivot
			while (array[j] > pivot) {
				j--;
			}

			//while i is still less than or equal to j
			if (i <= j) {
				//swap elements i and j
				swap(i, j, array);
				//increment i and decrement j
				i++;
				j--;
			}
		}
		//check if j is greater than lower bound
		//if it is, then there are still unsorted elements
		//recursively call quickSort again for this section
		if (low < j) {
			quickSort(low, j, array);
		}

		//Similarly for upper section
		if (i < high) {
			quickSort(i, high, array);
		}
	}

	//Simple swap method for swapping two elements
	public static void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
