
public class A4_Q1 {

	public static void main(String[] args) {

		//Create String hash set of max size 10
		MyStringHash hs = new MyStringHash(10);


		String s1 = "wow";
		String s2 = "bob";
		String s3 = "dod";
		String s4 = "hello";
		String s5 = "goodbye";

		//Add previous 5 strings to hash set
		hs.add(s1);
		hs.add(s2);
		hs.add(s3);
		hs.add(s4);
		hs.add(s5);

		System.out.println("String \"" + s1 + "\" added to index: " + hs.compress(s1.hashCode()));
		System.out.println("String \"" + s2 + "\" added to index: " + hs.compress(s2.hashCode()));
		System.out.println("String \"" + s3 + "\" added to index: " + hs.compress(s3.hashCode()));
		System.out.println("String \"" + s4 + "\" added to index: " + hs.compress(s4.hashCode()));
		System.out.println("String \"" + s5 + "\" added to index: " + hs.compress(s5.hashCode()));

		System.out.println("Current size: " + hs.currentSize);

		System.out.println("Contains \"bob\": " + hs.contains("bob"));

		hs.remove("bob");
		System.out.println("String \"bob\" removed.");
		System.out.println("Current size: " + hs.currentSize);

		System.out.println("Contains \"bob\": " + hs.contains("bob"));
		System.out.println("Contains \"wow\": " + hs.contains("wow"));
		System.out.println("Contains \"dod\": " + hs.contains("dod"));
		System.out.println("Contains \"hello\": " + hs.contains("hello"));
		System.out.println("Contains \"goodbye\": " + hs.contains("goodbye"));

	}

}
