/**
 * Accepts strings of characters as arguments separated by space, separates unique strings
 * and counts number of times that unique string occurs.
 * 
 * @author Matthew Argao
 */

public class A1_Q1 {

	public static void main(String[] args) {
		//Declaration of counter.
		int counter;
		//Temporary string that we then compare to original arguments.
		String textToCompare;
		//Initialize copy of original arguments.
		String[] copyArray = new String[args.length];
		//Array size of unique terms.
		int uniqueSize;
		
		
		//Make copy of original arguments.
		for (int z = 0; z < args.length; z++) {
			copyArray[z] = args[z];
		}
		
		//Return array size of unique terms.
		uniqueSize = getUniqueSize(findUnique(copyArray));

		
		//For each term in unique terms array, compare to each term in the copy array.
		//Copy array is used as the findUnique method is destructive to the array called.
		for (int i = 0; i < uniqueSize; i++){
			textToCompare = findUnique(copyArray)[i];
			counter = 0;
			
			for (int j = 0; j < args.length; j++) {
				//If term is equal, increment counter.
				if (textToCompare.equals(args[j])) {
					counter++;		
				}
				
			}
			//Print unique term and # of occurences.
			System.out.println(textToCompare + " " + counter);
		}
		
	}
	
	
	//Eliminates redundant terms and returns array of only unique terms.
	public static String[] findUnique(String[] list) {
				
		int size = list.length;
		
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				//If element i equals element j, overwrite j by shifting all terms left by one index.
				if (list[i].equals(list[j])) {
					int shiftIndexLeft = j;
					for (int k = j+1; k < size; k++, shiftIndexLeft++) {
						list[shiftIndexLeft] = list[k];
					}
					size--;
					j--;
				}
			}
		}
		
		//Remove redundant tail end terms
		String[] uniqueText = new String[size];
		for (int i = 0; i < size; i++) {
			uniqueText[i] = list[i];
		}
		
		return uniqueText;
	}
	
	//Returns size of unique array
	public static int getUniqueSize(String[] uniqueList) { return uniqueList.length; }

}


