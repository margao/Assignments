
public class A2_Q3 extends MyTree<Integer> {

	
	public static void main(String[] args){
		
		MyTree<Integer> tree = new MyTree<Integer>();
		
		
//         construct this tree
//          1A
//          |
//          2B ---------- 3D
//          |		      |
//          5C --- 6E     8H --- 9I  
//
//		or visualized another way:
//			
//					1A
//				   /  \
//				  2B  3D
//				 / |   | \
//			   5C  6E  8H 9I
//          
          
		Node<Integer> treeRoot = tree.addRoot(1);
		Node<Integer> B = tree.addChild(treeRoot, 2);
		Node<Integer> C = tree.addChild(B, 5);
		Node<Integer> D = tree.addRight(B, 3);
		Node<Integer> E = tree.addRight(C, 6);
		Node<Integer> H = tree.addChild(D, 8);
		Node<Integer> I = tree.addRight(H, 9);
		
		tree.printPreOrderTraversal(treeRoot);
		tree.printPostOrderTraversal(treeRoot);
		tree.printBreathFirstTraversal(treeRoot);

		System.out.println("\n\nIs tree complete? " + tree.isComplete(treeRoot));
		System.out.println("Is tree full? " + tree.isFull(treeRoot));
	}
	
	
}
