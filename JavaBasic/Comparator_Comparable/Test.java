package Comparator_Comparable;

public class Test {
	public static void main(String[] args) {
		Student st1 = new  Student(100, "Roxanne Nichols", "Class 1", 9);
		Student st5 = new  Student(105, "Tina Mccormick", "Class 4", 7);
		Student st9 = new  Student(15, "Jane Miranda", "Class 2", 5);
		System.out.println(st1.compareTo(st5));
		System.out.println(st5.compareTo(st9));
		 
		 
		 
	}
}
