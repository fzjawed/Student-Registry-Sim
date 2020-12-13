
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Registry {
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<ActiveCourse> courses = new ArrayList<ActiveCourse>();

	public Registry() throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("students.txt")));
			String line = br.readLine();
			while (line != null) {
				String[] studentTokens = line.split(" ");
				//check if length of string is for studentname and studentId
				if (studentTokens.length == 2) {
					String studentName = studentTokens[0];
					String studentId = studentTokens[1];
					students.add(new Student(studentName, studentId));
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found " + e.getLocalizedMessage());
			throw e;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					throw e;
				}
			}
		}

		try {
			br = new BufferedReader(new FileReader(new File("courses.txt")));
			String line = br.readLine();
			int count = 0;
			String[] data = new String[6];

			//adding the details of the individual course after reading first 6 lines and adding to the array
			//resetting the array after end of each course read
			while (line != null) {
				data[count] = line;
				if (count == 5) {
					String courseName = data[0];
					String courseCode = data[1];
					String descr = data[2];
					String format = data[3];
					String semester = data[4];
					String[] studentIds = data[5].split(" ");

					ArrayList<Student> list = new ArrayList<Student>();

					for (String studentId : studentIds) {
						int studentIndex = getStudentIndex(studentId);
						if (studentIndex > -1) {
							list.add(this.students.get(studentIndex));
							this.students.get(studentIndex).addCourse(courseName,courseCode,descr,format,semester,0.0);
						}
					}
					this.courses.add(new ActiveCourse(courseName, courseCode, descr, format, semester, list));

					count = 0;
					data = new String[6];
				}else {
					count++;

				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found " + e.getLocalizedMessage());
			throw e;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
					throw e;
				}
			}
		}

	}

	// Add new student to the registry (students arraylist above)
	public boolean addNewStudent(String name, String id) {
		// Create a new student object
		// check to ensure student is not already in registry
		// if not, add them and return true, otherwise return false
		// make use of equals method in class Student
		Student newStudent = new Student(name, id);
		boolean alreadyRegistered = isStudentRegistered(newStudent);

		if (!alreadyRegistered) {
			this.students.add(newStudent);
			return true;
		}
		return false;

	}

	// Remove student from registry
	public boolean removeStudent(String studentId) {
		// Find student in students arraylist
		// If found, remove this student and return true
		int studentIndex = getStudentIndex(studentId);

		if (studentIndex > -1) {
			this.students.remove(studentIndex);
			return true;
		}
		return false;
	}

	// Print all registered students
	public void printAllStudents() {
		for (int i = 0; i < students.size(); i++) {
			System.out.println("ID: " + students.get(i).getId() + " Name: " + students.get(i).getName());
		}

	}

	// Given a studentId and a course code, add student to the active course
	public void addCourse(String studentId, String courseCode) {
		// Find student object in registry (i.e. students arraylist)
		// Check if student has already taken this course in the past Hint: look at
		// their credit course list
		// If not, then find the active course in courses array list using course code
		// If active course found then check to see if student already enrolled in this
		// course
		// If not already enrolled
		// add student to the active course
		// add course to student list of credit courses with initial grade of 0
		int studentIndex = getStudentIndex(studentId);
		if (studentIndex > -1) {
			Student student = this.students.get(studentIndex);
			if (!student.isCreditCoursePresent(courseCode)) {
				int courseIndex = getCourseIndex(courseCode);

				if (courseIndex > -1) {
					ActiveCourse activeCourse = this.courses.get(courseIndex);
					if (!activeCourse.isStudentPresent(studentId)) {
						student.addCourse(activeCourse.getName(), courseCode, activeCourse.getDescription(),
								activeCourse.getFormat(), activeCourse.getSemester(), 0.0);
						activeCourse.addStudent(student);
					}
				}
			}
		}

	}

	// Given a studentId and a course code, drop student from the active course
	public void dropCourse(String studentId, String courseCode) {
		// Find the active course
		// Find the student in the list of students for this course
		// If student found:
		// remove the student from the active course
		// remove the credit course from the student's list of credit courses
		int courseIndex = getCourseIndex(courseCode);

		if (courseIndex > -1) {
			ActiveCourse course = this.courses.get(courseIndex);

			if (course.isStudentPresent(studentId)) {
				course.removeStudent(studentId);
				int studentIndex = getStudentIndex(studentId);
				Student student = this.students.get(studentIndex);
				student.removeActiveCourse(courseCode);
			}
		}

	}

	// Print all active courses
	public void printActiveCourses() {
		for (int i = 0; i < this.courses.size(); i++) {
			ActiveCourse ac = this.courses.get(i);
			System.out.println(ac.getDescription());
		}
	}

	// Print the list of students in an active course
	public void printClassList(String courseCode) {

		for (ActiveCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				course.printClassList();
				break;
			}
		}

	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseByName(String courseCode) {
		for (ActiveCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				course.sortByName();
				break;
			}
		}
	}

	// Given a course code, find course and sort class list by student name
	public void sortCourseById(String courseCode) {
		for (ActiveCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				course.sortById();
				break;
			}
		}
	}

	// Given a course code, find course and print student names and grades
	public void printGrades(String courseCode) {
		for (ActiveCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				course.printGrades();
			}
		}

	}

	// Given a studentId, print all active courses of student
	public void printStudentCourses(String studentId) {
		int studentIndex = getStudentIndex(studentId);
		if (studentIndex > -1) {
			for (CreditCourse course : this.students.get(studentIndex).courses) {
				System.out.println(course.getDescription());
			}
		}
	}

	// Given a studentId, print all completed courses and grades of student
	public void printStudentTranscript(String studentId) {
		int studentIndex = getStudentIndex(studentId);
		if (studentIndex > -1) {
			Student student = this.students.get(studentIndex);
			student.printTranscript();
		}
	}

	// Given a course code, student id and numeric grade
	// set the final grade of the student
	public void setFinalGrade(String courseCode, String studentId, double grade) {
		// find the active course
		// If found, find the student in class list
		// then search student credit course list in student object and find course
		// set the grade in credit course and set credit course inactive
		int courseIndex = getCourseIndex(courseCode);

		if (courseIndex > -1) {
			ActiveCourse course = this.courses.get(courseIndex);

			Student student = course.getStudent(studentId);

			CreditCourse creditCourse = student.getCreditCourse(courseCode);
			creditCourse.setGrade(grade);
			creditCourse.setInactive();
		}
	}

	/**
	 * Getting the student index in the list using the student array
	 * @param studentId
	 * @return
	 */

	private int getStudentIndex(String studentId) {

		int index = 0;
		int studentIndex = -1;
		for (Student student : this.students) {
			if (student.getId().equals(studentId)) {
				studentIndex = index;
				break;
			}
			index++;
		}

		return studentIndex;
	}

	/**
	 * Get course index in the list using the coursecode
	 * @param courseCode
	 * @return
	 */

	private int getCourseIndex(String courseCode) {

		int index = 0;
		int courseIndex = -1;
		for (ActiveCourse course : this.courses) {
			if (course.getCode().equalsIgnoreCase(courseCode)) {
				courseIndex = index;
				break;
			}
			index++;
		}

		return courseIndex;
	}

	/**
	 * check if the student is already registered
	 * @param newStudent
	 * @return
	 */

	private boolean isStudentRegistered(Student newStudent) {
		boolean alreadyRegistered = false;
		for (Student student : this.students) {
			if (newStudent.equals(student)) {
				alreadyRegistered = true;
				break;
			}
		}
		return alreadyRegistered;
	}

}
