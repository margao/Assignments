/**
 * Homework #3
 * Hash Tables
 * 
 * @author Matthew Argao
 * @email: margao@mymail.vcu.edu
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class cmsc401 {

	public static void main(String[] args) throws FileNotFoundException {

		String command = "";
		MyHash hs = new MyHash(1000);  // Create new hashtable with 1000 entries
		
		// Scanner instance and parse citis.csv
		Scanner scanner = new Scanner(new File(args[0]));
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			String[] tokens = str.split(",");
			hs.add(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
		}
		scanner.close();

		// Scanner instance and parse user input
		Scanner input = new Scanner(System.in);

		while (!command.equalsIgnoreCase("stop")) {
			String str = input.nextLine();
			String[] tokens = str.split(" ", 2);
			command = tokens[0];

			if (command.equalsIgnoreCase("retrieve")) {
				if (hs.contains(tokens[1])){	// Check if argument exists in hashtable
				System.out.println(hs.retrieveLat(tokens[1]) + " " + hs.retrieveLon(tokens[1]));
				} else {
					System.out.println("Error: Argument not found.");
				}
			}

			if (command.equalsIgnoreCase("distance")) {
				String[] place = tokens[1].split(", ", 2);
				if (hs.contains(place[0]) && hs.contains(place[1])){
					System.out.println(Math.round(haversine(hs, place[0], place[1]))); // Round to nearest km
				} else {
					System.out.println("Error: Arguments not found.");
				}
			}
		}
		input.close();
		
		System.out.println(Math.round(hs.calcAvgLength())); // Round to nearest whole number
	}


	// Source:
	// https://bigdatanerd.wordpress.com/2011/11/03/java-implementation-of-haversine-formula-for-distance-calculation-between-two-points/
	// Adjusted arguments to fit current implementation
	public static double haversine(MyHash hs, String arg1, String arg2) {
		final double R = 6373;
		double lat1 = hs.retrieveLat(arg1);
		double lat2 = hs.retrieveLat(arg2);
		double lon1 = hs.retrieveLon(arg1);
		double lon2 = hs.retrieveLon(arg2);

		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}

	// Nested hash class
	public static class MyHash {

		int currentSize;
		int maxSize;
		Node[] hashTable;

		// Constructor
		public MyHash(int size) {
			currentSize = 0;
			maxSize = size;
			hashTable = new Node[maxSize];
		}

		// Compress built in java hashcode to index within hash set
		int compress(int hashCode) {
			int index;
			index = (3 * hashCode + 15)  % maxSize;
			// hashcode may output negative values
			// correct value to positive integer index
			if (index < 0) {
				index = index * (-1);
			}
			return index;
		}

		// Adds string to the hash set
		boolean add(String s, double lat, double lon) {
			// Compress hashcode
			int i = compress(s.hashCode());

			// if node exists at index i then iterate through nodes at that
			// index until end of linked list is found
			if (hashTable[i] != null) {
				Node temp = hashTable[i];

				while (temp.getChild() != null) {
					temp = temp.getChild();
				}
				// create node at end of linked list and have previous node
				// point to new node
				temp.setChild(new Node(s.hashCode(), s, lat, lon, null));
			} else {
				// if node does not exist at index i then create new node at
				// that index
				hashTable[i] = new Node(s.hashCode(), s, lat, lon, null);
			}

			currentSize++;
			return true;
		}

		// Calculates average length of linked lists per bucket
		double calcAvgLength() {
			Node currentNode;
			int sum = 0;
			double avgLength;
			
			// Iterate through entire hashtable
			for (int i = 0; i < maxSize; i++) {
				int counter = 0;
				// if node exists at current bucket
				if (hashTable[i] != null){
					currentNode = hashTable[i];					
					counter++;
					// Check if current node has child
					while(currentNode.getChild() != null) {
						counter++;
						currentNode = currentNode.getChild();
					}
				}
				sum += counter;
			}
			avgLength = sum/maxSize;
			return avgLength;
		}
		
		// Checks whether string with specific key exists within the hashset
		boolean contains(String s) {
			// Compress hashcode
			int i = compress(s.hashCode());
			// Check if node exists at this index
			if (hashTable[i] != null) {
				// if node key equals hash code then return true
				if (hashTable[i].getKey() == s.hashCode()) {
					return true;
				}
				// if node key has child, check child
				if (hashTable[i].getChild() != null) {
					Node tempChild = hashTable[i].getChild();

					// iterate through linked list until end of list
					while (tempChild != null) {
						if (tempChild.getKey() == s.hashCode()) {
							return true;
						}
						tempChild = tempChild.getChild();
					}
				}
			}
			return false;
		}

		double retrieveLon(String s) {
			boolean found = false;
			// Compress hashcode
			int i = compress(s.hashCode());
			// Check if node exists at this index
			if (hashTable[i] != null) {
				// if node key equals hash code then return true
				if (hashTable[i].getKey() == s.hashCode()) {
					return hashTable[i].getLon();
				}
				// if node has child and key not found, check child
				if (hashTable[i].getChild() != null && found == false) {
					Node tempChild = hashTable[i].getChild();

					// iterate through linked list until end of list
					while (tempChild != null) {
						if (tempChild.getKey() == s.hashCode()) {
							return tempChild.getLon();
						}
						tempChild = tempChild.getChild();
					}
				}
			}
			return 0;
		}

		double retrieveLat(String s) {
			boolean found = false;
			// Compress hashcode
			int i = compress(s.hashCode());
			// Check if node exists at this index
			if (hashTable[i] != null) {
				// if node key equals hash code then return true
				if (hashTable[i].getKey() == s.hashCode()) {
					return hashTable[i].getLat();
				}
				// if node has child and key not found, check child
				if (hashTable[i].getChild() != null && found == false) {
					Node tempChild = hashTable[i].getChild();

					// iterate through linked list until end of list
					while (tempChild != null) {
						if (tempChild.getKey() == s.hashCode()) {
							return tempChild.getLat();
						}
						tempChild = tempChild.getChild();
					}
				}
			}
			return 0;
		}

		// Removes string from hash set and updates pointers appropriately
		boolean remove(String s) {

			// Compress hashcode
			int i = compress(s.hashCode());

			// If node exists at index i
			if (hashTable[i] != null) {
				// Check if node key equals hash code
				if (hashTable[i].getKey() == s.hashCode()) {
					// If it does, check whether node has a child and make child
					// replace old node's position
					if (hashTable[i].getChild() != null) {
						hashTable[i] = hashTable[i].getChild();
					} else {
						hashTable[i] = null;
					} // otherwise set element at index i to null
					currentSize--;
					return true;
				}
				// Check if node's child exists
				if (hashTable[i].getChild() != null) {
					// Current node
					Node temp = hashTable[i];

					// Iterate through linked lists and check whether node key
					// matches hash code
					while (temp.getChild() != null) {
						// if key of current node's child equals hash code then
						// update current node's pointer
						if (temp.getChild().getKey() == s.hashCode()) {
							// if child of child exists (otherwise known as
							// current node's grandchild) then make current node
							// point to grandchild
							if (temp.getChild().getChild() != null) {
								temp.setChild(temp.getChild().getChild());
							} else {
								// otherwise set child to null
								temp.setChild(null);
							}
							break;
						}
						temp = temp.getChild();
					}
					currentSize--;
					return true;
				}
			}
			return false;
		}

		// Nested node class
		public class Node {
			// Instance variables
			private int key;
			private String city;
			private Node child;
			private double latitude, longitude;

			// Constructor
			public Node(int k, String v, double lat, double lon, Node c) {
				key = k;
				city = v;
				child = c;
				latitude = lat;
				longitude = lon;
			}

			// Accessor methods
			public String getCity() {
				return city;
			}

			public Double getLat() {
				return latitude;
			}

			public Double getLon() {
				return longitude;
			}

			public int getKey() {
				return key;
			}

			public Node getChild() {
				return child;
			}

			// Setter methods
			public void setChild(Node childNode) {
				child = childNode;
			}

		}

	}

}
