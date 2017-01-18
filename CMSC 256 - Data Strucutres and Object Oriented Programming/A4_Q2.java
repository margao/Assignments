public class A4_Q2 {

	public static void main(String[] args) {

		//Skip list implementation primarily from: 
		//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/Progs/SkipList/SkipListEntry.java
		//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/Progs/SkipList/SkipList.java
		//Added removal method MySkipList based on pseudocode provided here:
		//http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Map/skip-list-impl.html
		
		MySkipList S = new MySkipList();


		S.put("6", 6);
		S.put("2", 2);
		S.put("1", 1);
		S.put("5", 5);
		S.put("7", 7);
		
		//Before removal
		System.out.println("Before removal of 6: ");
		S.printVertical();

		S.skipRemove("6");
		
		//After removal
		System.out.println("\nAfter removal of 6: ");
		S.printVertical();
	}
}
