
public class A3_Q1 {

	public static void main(String[] args) {
		
		MyTree<Integer> A = new MyTree<Integer>(10);
		MyTree<Integer> B = new MyTree<Integer>(5);
		
		/*
		 * Create trees:
		 * A
		 * 		10
		 *     /  \
		 *    7    14
		 *   / \   / \
		 *  5  9  12  20
		 *  
		 *  
		 * B
		 * 		5
		 *     / \
		 *    2   8
		 *   / \   \
		 *  1   7   8
		 * 
		 * 
		 */
		A.insertLeft(7, A.root);
		A.insertRight(14, A.root);
		A.insertLeft(5, A.root.left);
		A.insertRight(9, A.root.left);
		A.insertLeft(12, A.root.right);
		B.insertLeft(2, B.root);
		B.insertRight(8, B.root);
		B.insertLeft(2, B.root.left);
		B.insertRight(7, B.root.left);
		B.insertRight(8, B.root.right);
		
		
		System.out.println("Is Tree A a valid BST? " + isValidBST(A));
		System.out.println("Is Tree B a valid BST? " + isValidBST(B));
		
	}
	
	//Nested Tree class for constructing tree nodes
	protected static class MyTree<T extends Comparable<T>> {
		Node<T> root;

		public MyTree(T element) {
			root = new Node<T>(element, null, null, null);
		}
		
		public MyTree() {
			root = null;
		}
		
		public void insertLeft(T element, Node<T> x) {
			Node<T> left = new Node<T>(element, x, null, null);
			x.setLeft(left);
		}
		
		public void insertRight(T element, Node<T> x) {
			Node<T> right = new Node<T>(element, x, null, null);
			x.setRight(right);
		}
		
	}

	//Check if given tree is valid BST
	protected static boolean isValidBST(MyTree tree) {
		//Start check with root node
		return isValidBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	//Recursively check if all nodes in left subtree are less than root and all nodes in right subtree are greater than root
	protected static boolean isValidBST(Node<Integer> root, int min, int max) {
		
		//root case
		if (root == null) {
			return true;
		}
		
		//If element is greater than max or less than min, return false
		if (root.element <= min || root.element >= max){
			return false;
		}
		
		//Traverse left subtree and right subtree
		return isValidBST(root.left, min, root.element) && isValidBST(root.right, root.element, max);
				
	}
	
	//Nested node class
	protected static class Node<T> {
		//Instance variables
		private T element;
		private Node<T> parent, left, right;
		
		//Constructor
		public Node(T data, Node<T> above, Node<T> prev, Node<T> next) {
			element = data;
			parent = above;
			left = prev;
			right = next;
		}
		
		//Accessor methods
		public T getElement() { return element; }
		public Node<T> getParent() { return parent; }
		public Node<T> getLeft() { return left; }
		public Node<T> getRight() { return right;}
		
		//Setter methods
		public void setElement(T data) { element = data; }
		public void setParent(Node<T> parentNode) { parent = parentNode; }
		public void setLeft(Node<T> leftNode) { left = leftNode; }
		public void setRight(Node<T> rightNode) { right = rightNode; }
		
	}
}
