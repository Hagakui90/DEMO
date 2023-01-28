package Comparator_Comparable;

public class Student implements Comparable<Student>{
	private int idStudent;
	private String nameStudent;
	private String className;
	private double gpa;
	
	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public String getNameStudent() {
		return nameStudent;
	}

	public void setNameStudent(String nameStudent) {
		this.nameStudent = nameStudent;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public Student(int idStudent, String nameStudent, String className, double gpa) {
		this.idStudent = idStudent;
		this.nameStudent = nameStudent;
		this.className = className;
		this.gpa = gpa;
	}

	public String getName() {
		String s  = this.nameStudent.trim();
		if (s.indexOf(" ")>=0) {
			int vt = s.lastIndexOf(" ");
			return s.substring(vt+1);
		
		}else {
			return s;
		}
	}
	
	@Override
	public int compareTo(Student o) {
		// Compare via Name
		String nameThis = this.getName();
		String nameO = o.getName();
		
		return nameThis.compareTo(nameO);
	}
}
