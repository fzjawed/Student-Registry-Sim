//Student Name: Fatima Jawed 
import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student {
	private String name;
	private String id;
	public ArrayList<CreditCourse> courses;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
		this.courses = new ArrayList<CreditCourse>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// add a credit course to list of courses for this student
	public void addCourse(String courseName, String courseCode, String descr, String format, String sem, double grade) {
		// create a CreditCourse object
		// set course active
		// add to courses array list
		CreditCourse course = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
		course.setActive();
		this.courses.add(course);
	}

	// Prints a student transcript
	// Prints all completed (i.e. non active) courses for this student (course code,
	// course name,
	// semester, letter grade
	// see class CreditCourse for useful methods
	public void printTranscript() {
		for (CreditCourse course : this.courses) {
			if (!course.getActive()) {
				System.out.println(course.getCode() + " " + course.getName() + " " + course.getSemester() + " "
						+ Course.convertNumericGrade(course.getGrade()));
			}

		}
	}

	// Prints all active courses this student is enrolled in
	// see variable active in class CreditCourse
	public void printActiveCourses() {

		for (CreditCourse course : this.courses) {
			if (course.getActive()) {
				course.displayGrade();
			}
		}

	}

	// Drop a course (given by courseCode)
	// Find the credit course in courses arraylist above and remove it
	// only remove it if it is an active course
	public void removeActiveCourse(String courseCode) {
		int index = 0;
		int removeIndex = -1;
		for (CreditCourse course : this.courses) {
			if (course.getCode().equals(courseCode) && course.getActive()) {
				removeIndex = index;
				break;
			}
			index++;
		}

		if (removeIndex > -1) {
			this.courses.remove(removeIndex);
		}

	}

	public String toString() {
		return "Student ID: " + id + " Name: " + name;
	}

	// override equals method inherited from superclass Object
	// if student names are equal *and* student ids are equal (of "this" student
	// and "other" student) then return true
	// otherwise return false
	// Hint: you will need to cast other parameter to a local Student reference
	// variable
	public boolean equals(Object other) {
		Student otherStudent = (Student) other;
		return this.getId().equals(otherStudent.getId()) && this.name.equals(otherStudent.getName());
	}

	/**
	 * Get grade for the course using the coursecode
	 * @param courseCode
	 * @return
	 */

	public Double getGradeForCourse(String courseCode) {
		for (CreditCourse course : this.courses) {
			if (course.getCode().equals(courseCode)) {
				return course.getGrade();
			}
		}
		return 0.0;
	}

	/**
	 * find if the credit course is available for the course code
	 * @param courseCode
	 * @return
	 */
	public boolean isCreditCoursePresent(String courseCode) {

		return getCreditCourse(courseCode) != null;
	}

	/**
	 * Return the credit course if available using the course code
	 * @param courseCode
	 * @return
	 */

	public CreditCourse getCreditCourse(String courseCode) {
		for (CreditCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				return course;
			}
		}
		return null;
	}
}
