/**
 * Homework #2
 * Minimal Integral Distance
 * 
 * @author Matthew Argao
 * @email: margao@mymail.vcu.edu
 */

import java.util.Scanner;

public class cmsc401 {

	public static void main(String[] args) {
		int n;

		Scanner input = new Scanner(System.in);

		System.out.println("Input number of n positive integers within range 2 < n < 1000000:");
		n = input.nextInt();
		input.nextLine();

		if (n < 2 || n > 1000000) {
			input.close();
			throw new IllegalArgumentException("Data must be within range 2 < n < 1000000!");
		}

		int[] data = new int[n];

		System.out.println("Input data content of array separated by space (data must be within range 0 < x <= 1000000000):");

		for (int i = 0; i < n; i++) {
			data[i] = input.nextInt();
			if (data[i] <= 0 || data[i] > 1000000000){
				input.close();
				throw new IllegalArgumentException("Data must be within range 0 < x <= 1000000000!");
			}
		}
		input.close();

		quickSort(0, n - 1, data);

		int[] output = findMinDist(data);
		System.out.println("Minimum integral distance: ");
		for (int i : output) {
			System.out.print(i + " ");
		}

	}

	public static int[] findMinDist(int[] array) {
		int[] minDist = { array[0], array[1] };
		int dist = array[1] - array[0];	//assume first two integers of sorted array have min dist
		int i = 1;

		//check next two integers and iterate through entire array
		while (i < array.length - 1) {
			if (array[i + 1] - array[i] < dist) {
				minDist[0] = array[i];
				minDist[1] = array[i + 1];
				dist = array[i + 1] - array[i];
			}
			i++;
		}
		return minDist;
	}

	public static void quickSort(int low, int high, int[] array) {
		// Set lower and upper bounds of index
		int i = low;
		int j = high;

		// Set pivot to element at approx half index
		int pivot = array[low + (high - low) / 2];

		// Divide into halves
		while (i <= j) {
			// increment i if it satisfies being less than pivot
			while (array[i] < pivot) {
				i++;
			}

			// decrement j if it satisfies being more than pivot
			while (array[j] > pivot) {
				j--;
			}

			// while i is still less than or equal to j
			if (i <= j) {
				// swap elements i and j
				swap(i, j, array);
				// increment i and decrement j
				i++;
				j--;
			}
		}
		// check if j is greater than lower bound
		// if it is, then there are still unsorted elements
		// recursively call quickSort again for this section
		if (low < j) {
			quickSort(low, j, array);
		}

		// Similarly for upper section
		if (i < high) {
			quickSort(i, high, array);
		}
	}

	// Simple swap method for swapping two elements
	public static void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
