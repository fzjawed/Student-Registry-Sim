//Student Name: Fatima Jawed
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course

public class ActiveCourse extends Course {
	private ArrayList<Student> students;
	private String semester;

	// Add a constructor method with appropriate parameters
	// should call super class constructor to initialize inherited variables
	// make sure to *copy* students array list being passed in into new arraylist of
	// students
	// see class Registry to see how an ActiveCourse object is created and used
	public ActiveCourse(String name, String code, String descr, String fmt, String semester,
			ArrayList<Student> students) {

		super(name, code, descr, fmt);
		this.semester = semester;

		if (students != null && students.size() > 0) {
			for (Student student : students) {
				if (this.students == null) {
					this.students = new ArrayList<Student>();
				}
				this.students.add(student);
			}
		}
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(String studentId) {
		if (this.students != null) {
			int index = 0;
			int studentIndex = -1;
			for (Student student : students) {
				if (student.getId().equals(studentId)) {
					studentIndex = index;
					break;
				}
				index++;
			}
			if (studentIndex > -1) {
				this.students.remove(studentIndex);
			}
		}
	}

	public String getSemester() {
		return semester;
	}

	// Prints the students in this course (name and student id)
	public void printClassList() {
		if (this.students != null) {
			for (Student student : students) {
				System.out.println("Student ID: " + student.getId() + " Name: " + student.getName());
			}
		}

	}

	// Prints the grade of each student in this course (along with name and student
	// id)
	//
	public void printGrades() {

		if (this.students != null && this.students.size() > 0) {
			for (Student student : this.students) {
				System.out.println(
						student.getId() + " " + student.getName() + " " + student.getGradeForCourse(this.getCode()));
			}
		}

	}

	public boolean isStudentPresent(String studentId) {
		return getStudent(studentId) != null;
	}

	public Student getStudent(String studentId) {
		if (this.students != null && this.students.size() > 0) {
			for (Student student : this.students) {
				if (student.getId().equals(studentId)) {
					return student;
				}
			}
		}
		return null;
	}

	// Returns the numeric grade in this course for this student
	// If student not found in course, return 0
	public double getGrade(String studentId) {
		// Search the student's list of credit courses to find the course code that
		// matches this active course
		// see class Student method getGrade()
		// return the grade stored in the credit course object

		for (Student student : this.students) {
			if (student.getId().equals(studentId)) {
				return student.getCreditCourse(this.getCode()).getGrade();
			}
		}
		return 0;
	}

	// Returns a String containing the course information as well as the semester
	// and the number of students
	// enrolled in the course
	// must override method in the superclass Course and use super class method
	// getDescription()
	public String getDescription() {
		return getCode() + " - " + getName() + "\n" + getDescr() + "\n" + getFormat() + "\nEnrollment:"
				+ this.students.size()+"\n";
	}

	// Sort the students in the course by name using the Collections.sort() method
	// with appropriate arguments
	// Make use of a private Comparator class below
	public void sortByName() {
		Collections.sort(this.students, new NameComparator());
	}

	// Fill in the class so that this class implement the Comparator interface
	// This class is used to compare two Student objects based on student name
	private class NameComparator implements Comparator<Student> {

		public int compare(Student o1, Student o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

	// Sort the students in the course by student id using the Collections.sort()
	// method with appropriate arguments
	// Make use of a private Comparator class below
	public void sortById() {
		Collections.sort(this.students, new IdComparator());
	}

	// Fill in the class so that this class implement the Comparator interface
	// This class is used to compare two Student objects based on student id
	private class IdComparator implements Comparator<Student> {
		public int compare(Student o1, Student o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}
}
