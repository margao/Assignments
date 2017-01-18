import java.lang.Math;
import java.util.Scanner;

public class A1_Q3 {

	public static void main(String[] args) {
		double a;
		double b;
		double c;
		double d;
		double x;
		double y;
		double theta;
		int choice;
		
		Scanner input = new Scanner(System.in);
		
	    System.out.println("This program calculates the perimeter and area of a chosen polygon.");
	    System.out.println("[ 1 ] Quadrilateral");
	    System.out.println("[ 2 ] Triangle");
	    System.out.print("Please select the type of polygon: ");

	    choice = input.nextInt();
	    input.nextLine();
	    
	    switch(choice) {
	    
	    case 1:
	    	System.out.println("What type of Quadrilateral?");
	    	System.out.println("[ 1 ] Square");
	    	System.out.println("[ 2 ] Rectangle");
	    	System.out.println("[ 3 ] Other convex Quadrilateral");
		    System.out.print("Please select choice: ");
		    choice = input.nextInt();
		    input.nextLine();
		    
		    switch(choice) {
		    	case 1:
	    		System.out.println("Please enter values:");
	    		System.out.print("Side A = B = C = D = ");
	    		a = input.nextDouble();
	    		input.nextLine();
	    	
	    		Square sqr = new Square(a,a,a,a,0,0,0);		//All sides of square are of equal length.
	    	
	    		System.out.println("Area: " + sqr.getArea());
	    		System.out.println("Perimeter: " + sqr.getPerimeter());
	    		break;
	    		
		    	case 2:
	    		System.out.println("Please enter values:");
	    		System.out.print("Side A = C = ");
	    		a = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Side B = D = ");
	    		b = input.nextDouble();
	    		input.nextLine();	    		
	    	
	    		Rectangle rect = new Rectangle(a,b,a,b,0,0,0);		//Each pair of opposite sides of a rectangle are of equal length.
	    	
	    		System.out.println("Area: " + rect.getArea());
	    		System.out.println("Perimeter: " + rect.getPerimeter());
    			break;
	    		
		    	case 3:
	    		System.out.println("Please enter values:");
	    		System.out.print("Side A = ");
	    		a = input.nextDouble();
	    		input.nextLine();

	    		System.out.print("Side B = ");
	    		b = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Side C = ");
	    		c = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Side D = ");
	    		d = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Diagonal AC = ");
	    		x = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Diagonal BD = ");
	    		y = input.nextDouble();
	    		input.nextLine();
	    		
	    		System.out.print("Angle between diagonals = ");
	    		theta = input.nextDouble();
	    		input.nextLine();
	    		
	    		Quadrilateral quad = new Quadrilateral(a,b,c,d,x,y,theta);
	    		System.out.println("Area: " + quad.getArea());
	    		System.out.println("Perimeter: " + quad.getPerimeter());
	    		break;
	    		
		    	default: 
		    	System.out.println("Please enter valid choice!");			//Catch case of invalid selection
		    	break;
		    }
		    break;
		    
	    case 2:
	    	System.out.println("What type of Triangle?");
	    	System.out.println("[ 1 ] Isosceles");
	    	System.out.println("[ 2 ] Equilateral");
	    	System.out.println("[ 3 ] Other");
		    System.out.print("Please select choice: ");
		    choice = input.nextInt();
		    input.nextLine();
		    
		    switch (choice) {
		    
		    case 1:
		    	System.out.println("Please enter values:");
		    	System.out.print("Side A = B = ");
	    		a = input.nextDouble();
	    		input.nextLine();
	    		
		    	System.out.print("Side C = ");
	    		c = input.nextDouble();
	    		input.nextLine();
	    		
	    		IsoscelesTriangle iso = new IsoscelesTriangle(a,a,c);			//2 sides of isosceles triangle are of equal length.
	    		
	    		System.out.println("Area: " + iso.getArea());
	    		System.out.println("Perimeter: " + iso.getPerimeter());
	    		break;
	    		
		    case 2:
		    	System.out.println("Please enter values:");
		    	System.out.print("Side A = B = C = ");
	    		a = input.nextDouble();
	    		input.nextLine();
	    		
	    		EquilateralTriangle equi = new EquilateralTriangle(a,a,a);		//All 3 sides of equilateral triangle are of equal length.
	    		
	    		System.out.println("Area: " + equi.getArea());
	    		System.out.println("Perimeter: " + equi.getPerimeter());
	    		break;
	    		
		    case 3:
		    	System.out.println("Please enter values:");
		    	System.out.print("Side A = ");
	    		a = input.nextDouble();
	    		input.nextLine();
	    		
		    	System.out.println("Please enter values:");
		    	System.out.print("Side B = ");
	    		b = input.nextDouble();
	    		input.nextLine();
	    		
		    	System.out.print("Side C = ");
	    		c = input.nextDouble();
	    		input.nextLine();
	    		
	    		if ( a+b > c && a+c > b && b+c > a){		//Sum of any two sides of triangle must be greater than the third side.
	    		
	    			Triangle tri = new Triangle(a,b,c);
	    		
	    			System.out.println("Area: " + tri.getArea());
	    			System.out.println("Perimeter: " + tri.getPerimeter());
	    			break;
	    		} else {
	    			System.out.println("Error: The sum of any two sides of a triangle must be greater than the third side!");
	    			break;
	    		}
	    		
	    		
	    		
	    	default:
	    		System.out.println("Please enter valid choice!");
	    		break;
		    }
		    break;
	    }
	    
	    input.close();
	}
	
	
	//Every polygon has getArea and getPerimeter methods which return doubles
	public interface Polygon {
		public double getArea();
		public double getPerimeter();
	}

	
	//Subclass Triangle is-a Polygon
	public static class Triangle implements Polygon {
		protected double sideA;
		protected double sideB;
		protected double sideC;
		protected double semiP;
		protected double perimeter;
		protected double area;
		
		//Constructor
		public Triangle(double a, double b, double c) {
			sideA = a;
			sideB = b;
			sideC = c;
		}

		public double getPerimeter() {
			perimeter = sideA + sideB + sideC;
			return perimeter;
		}
		
		public double getArea() {
			semiP = getPerimeter()/2;
			area = Math.sqrt(semiP*(semiP-sideA)*(semiP-sideB)*(semiP-sideC)); //Heron's formula
			return area;
		}
	}
	
	
	//IsoscelesTriangle is-a Triangle
	public static class IsoscelesTriangle extends Triangle {
		protected double equalSide;
		
		
		//Inherit constructor of Triangle
		//New instance variable equalSide which is equal to sideA and sideB
		public IsoscelesTriangle (double a, double b, double c){
			super(a, b, c);
			equalSide = sideA = sideB;
		}
		
		public double getArea() {
			area = .5*Math.pow(sideC, 2)*Math.sqrt((Math.pow(equalSide, 2)/Math.pow(sideC, 2)-.25));
			return area;
		}
	}
	
	
	//EquilateralTriangle is-a Triangle
	public static class EquilateralTriangle extends Triangle {
		protected double equalSide;
		
		//Inherit constructor of Triangle
		//New instance variable equalSide which is equal to all sides
		public EquilateralTriangle (double sideA, double sideB, double sideC){
			super(sideA, sideB, sideC);
			equalSide = sideA = sideB = sideC;
		}
		
		public double getArea() {
			area = .25*Math.sqrt(3)*Math.pow(equalSide, 2);
			return area;
		}
	}
	
	
	//Quadrilateral is-a Polygon
	public static class Quadrilateral implements Polygon {
		protected double sideA;
		protected double sideB;
		protected double sideC;
		protected double sideD;
		protected double diagonalX;
		protected double diagonalY;
		protected double angle;
		protected double perimeter;
		protected double area;
		
		public Quadrilateral(double a, double b, double c, double d, double x, double y, double theta) {
			sideA = a;
			sideB = b;
			sideC = c;
			sideD = d;
			diagonalX = x;
			diagonalY = y;
			angle = theta; //angle between diagonals
		}
		
		public double getPerimeter() {
			perimeter = sideA + sideB + sideC + sideD;
			return perimeter;
		}
		
		public double getArea() {
			area = .5*diagonalX*diagonalY*Math.sin(angle); //valid only for convex quadrilaterals
			return area;
		}
		
	}
	
	
	//Rectangle is-a Quadrilateral
	public static class Rectangle extends Quadrilateral {
		protected double equalSideA;
		protected double equalSideB;
		
		
		//Inherit Quadrilateral constructor
		//New instance variable equalSideA and equalSideB
		public Rectangle (double a, double b, double c, double d, double x, double y, double theta) {
			super(a, b, c, d, x, y, theta);
			equalSideA = sideA = sideC;
			equalSideB = sideB = sideD;
		}
		
		public double getArea() {
			area = equalSideA * equalSideB;
			return area;
		}
	}
	
	
	//Square is-a Quadrilateral
	public static class Square extends Quadrilateral {
		protected double equalSideA;
		
		
		//Inherit Quadrilateral constructor
		//New instance variable equalSideA which is equal to all sides
		public Square (double a, double b, double c, double d, double x, double y, double theta) {
			super(a, b, c, d, x, y, theta);
			equalSideA = a = b = c = d;
		}
		
		public double getArea() {
			area = Math.pow(equalSideA, 2);
			return area;
		}
	}
	
}

