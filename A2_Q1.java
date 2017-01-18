
public class A2_Q1 {

	public static void main(String[] args) {
		
		int x = Integer.parseInt(args[0]);		//parse first argument to type integer
		
		if (x == 0) {
			System.out.print("0x0");			//special case: output 0 if input is 0
		} else {								//otherwise continue to convert decimal to hexadecimal
			System.out.print("0x");				//hexadecimal prefix
			convertToHex(x);
		}
	}
	
	//Converts decimal to hexadecimal notation
	public static void convertToHex(int n){
		int remainder;
		remainder = n%16; 
		String output = "";								//initialize output string
		
		if (n>0){										//iterate through n recursively until n = 0
			output = hexCase(remainder) + output;		//convert remainder into correct hex code and save to output
			convertToHex(n/16);		
		}
		
		System.out.print(output);						//print final output
	}

	//Converts input into appropriate hexadecimal case
	public static String hexCase(int m){
		String output = "";								//initialize output
		
		if (m > 9){										//catch cases where m > 9 and output the corresponding hex code
			switch (m) {
				case 10:
					output = "A";
					return output;
				case 11:
					output = "B";
					return output;
				case 12:
					output = "C";
					return output;
				case 13:
					output = "D";
					return output;
				case 14:
					output = "E";
					return output;
				case 15:
					output = "F";
					return output;
				default:
					return output;
			}
		} else {
			output = Integer.toString(m);				//otherwise if m < 10 output m itself
			return output;
		}
	}

}
