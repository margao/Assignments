import java.util.*;

public class A1_Q2 {

	   public static void main(String[] args) {
		      Scanner in = new Scanner(System.in);
		      
		      System.out.print("How many lines do you want to enter? ");
		      String[] input = new String[in.nextInt()];
		      //Accept input from above. 
		      in.nextLine();
		      
		      //Populate input array with each line from above.
		      System.out.println("Enter line (press Enter key when finished with line):");
		      for (int i = 0; i < input.length; ++i) {
		    	  input[i] = in.nextLine();
		      }
		     
		      
		      //Iterate through array from end-to-beginning and output reversed string.
		      for (int i = input.length; i > 0; i--) {
		    	  StringBuilder reverse = new StringBuilder(input[i-1]);
		    	  System.out.println(reverse.reverse().toString());
		      }
		      
		      in.close();

		}

}