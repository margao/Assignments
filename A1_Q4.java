import java.io.*;

public class A1_Q4 {

	public static void main(String[] args) throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\input.txt")))
		{

			String sCurrentLine;
			
			
			//Iterate through each line of input.txt
			//For each line, convert to postfix expression, evaluate and print result.
			while ((sCurrentLine = br.readLine()) != null) {  
				String input = sCurrentLine;				
				String output;
				
				postfix theTrans = new postfix(input);
				output = theTrans.doTrans();
				
				Evaluation e=new Evaluation();  
				System.out.println(e.calculate(output)); 
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		}
	
	
	/**
	 * Evaluates power expressions through recursion where base and power represent their obvious meanings.
	 */
	
	public static double pow(double base, double power){
	    if(power == 0) return 1;
	    return base * pow(base, --power);
	}
	
	
	/**
	 * Evaluates postfix expression using stack.
	 * Has nested stack class.
	 * Altered to evaluate doubles instead of ints.
	 * 
	 * Source: http://datastructure-using-java.blogspot.com/2010/02/write-java-program-to-evaluate-postfix.html
	 */
	
	public static class Evaluation{  
		   public double calculate(String s)  
		   {  
		     int n=0;  
		     double r = 0;
		     n=s.length();  
		     Stack a=new Stack(n);  
		     for(int i=0;i<n;i++)  
		     {  
		       char ch=s.charAt(i);  
		       if(ch>='0'&&ch<='9')  
		         a.push((int)(ch-'0'));  
		       else  
		       {  
		         double x=a.pop();  
		         double y=a.pop();  
		         
		         switch(ch)  
		         {  
		           case '+':r=x+y;  
		              break;  
		           case '-':r=y-x;  
		              break;  
		           case '*':r=x*y;  
		              break;  
		           case '/':r=y/x;  
		              break;  
		           case '^':r=pow(x,y);				//Added case for power operator
		           		break;
		           default:r=0;  
		         }  
		         a.push(r);  
		       }  
		     }  
		     r=a.pop();  
		     return(r);  
		   }  
		   
		   class Stack  
		   {  
		      private double[] a;  
		      private int top,m;  
		      public Stack(int max)  
		      {  
		        m=max;  
		        a=new double[m];  
		        top=-1;  
		      }  
		      public void push(double key)  
		      {  
		        a[++top]=key;  
		      }  
		      public double pop()  
		      {  
		        return(a[top--]);  
		      }  
		   }  
		   
		   
		}  
	
	
	/**
	 * Take infix expression and converts to postfix expression.
	 * Altered to accept power operators.
	 * 
	 * Source: http://www.tutorialspoint.com/javaexamples/data_intopost.htm
	 */
	public static class postfix {
		   private Stack theStack;
		   private String input;
		   private String output = "";
		   public postfix(String in) {
		      input = in;
		      int stackSize = input.length();
		      theStack = new Stack(stackSize);
		   }
		   public String doTrans() {
		      for (int j = 0; j < input.length(); j++) {
		         char ch = input.charAt(j);
		         switch (ch) {
		            case '+': 
		            case '-':
		            gotOper(ch, 1); 
		            break; 
		            case '*': 
		            	//Changes start here
		                char checkNext = input.charAt(j+1);		//Check next character
		                if (checkNext == '*'){					//If * this implies power operator (e.g. **)
		                	j++;								//Skip this extra *
		                	j++;								//Now at next integer
		                	output = output + input.charAt(j) + "^";  //Place integer in correct position and add ^ operator
		                	break;
		                }
		                //Changes end here
		            case '/':
		            gotOper(ch, 2); 
		            break; 
		            case '(': 
		            theStack.push(ch);
		            break;
		            case ')': 
		            gotParen(ch); 
		            break;
		            default: 
		            output = output + ch; 
		            break;
		         }
		      }
		      while (!theStack.isEmpty()) {
		         output = output + theStack.pop();
		      }
		      return output; 
		   }
		   public void gotOper(char opThis, int prec1) {
		      while (!theStack.isEmpty()) {
		         char opTop = theStack.pop();
		         
		         if (opTop == '(') {
		            theStack.push(opTop);
		            break;
		         }
		         else {
		            int prec2;
		            if (opTop == '+' || opTop == '-')
		            prec2 = 1;
		            else
		            prec2 = 2;
		            if (prec2 < prec1) { 
		               theStack.push(opTop);
		               break;
		            }
				    else
		            output = output + opTop;
		         }
		      }
		      theStack.push(opThis);
		   }
		   public void gotParen(char ch){ 
		      while (!theStack.isEmpty()) {
		         char chx = theStack.pop();
		         if (chx == '(') 
		         break; 
		         else
		         output = output + chx; 
		      }
		   }


	
	}
	
	/**
	 * Stack class for postfix to use.
	 * 
	 * Source: http://www.tutorialspoint.com/javaexamples/data_intopost.htm
	 */
	
	public static class Stack {
		      private int maxSize;
		      private char[] stackArray;
		      private int top;
		      public Stack(int max) {
		         maxSize = max;
		         stackArray = new char[maxSize];
		         top = -1;
		      }
		      public void push(char j) {
		         stackArray[++top] = j;
		      }
		      public char pop() {
		         return stackArray[top--];
		      }
		      public char peek() {
		         return stackArray[top];
		      }
		      public boolean isEmpty() {
		         return (top == -1);
		     }
		   }
	
}
