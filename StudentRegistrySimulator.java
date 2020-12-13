//Student Name: Fatima Jawed 
import java.util.Scanner;

public class StudentRegistrySimulator {
	public static void main(String[] args) {
		try {
			Registry registry = new Registry();
			Scanner scanner = new Scanner(System.in);
			System.out.print(">");

			while (scanner.hasNextLine()) {
				try {
					String inputLine = scanner.nextLine();
					if (inputLine == null || inputLine.equals(""))
						continue;

					Scanner commandLine = new Scanner(inputLine);
					String command = commandLine.next();

					if (command == null || command.equals(""))
						continue;

					else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
						registry.printAllStudents();
					} else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT")) {
						commandLine.close();
						return;
					}

					else if (command.equalsIgnoreCase("REG")) {
						// register a new student in registry
						// get name and student id string
						// e.g. reg JohnBoy 74345
						// Checks:
						// ensure name is all alphabetic characters
						// ensure id string is all numeric characters

						String studentName = commandLine.next();
						String studentId = commandLine.next();

						if (isStringOnlyAlphabet(studentName) && isNumeric(studentId)) {
							boolean added = registry.addNewStudent(studentName, studentId);
							if (!added) {
								System.out.println("Student " + studentName + " already registered");
							}
						} else {
							if (!isStringOnlyAlphabet(studentName)) {
								System.out.println("Invalid Characters in Name " + studentName);
							}
							if (!isNumeric(studentId)) {
								System.out.println("Invalid Characters in ID " + studentId);
							}
						}

					} else if (command.equalsIgnoreCase("DEL")) {
						// delete a student from registry
						// get student id
						// ensure numeric
						// remove student from registry
						String studentId = commandLine.next();

						if (isNumeric(studentId)) {
							registry.removeStudent(studentId);
						} else {
							System.out.println("Invalid Characters in ID " + studentId);
						}

					}

					else if (command.equalsIgnoreCase("ADDC")) {
						// add a student to an active course
						// get student id and course code strings
						// add student to course (see class Registry)
						String studentId = commandLine.next();
						String courseCode = commandLine.next();

						registry.addCourse(studentId, courseCode);

					} else if (command.equalsIgnoreCase("DROPC")) {
						// get student id and course code strings
						// drop student from course (see class Registry)

						String studentId = commandLine.next();
						String courseCode = commandLine.next();

						registry.dropCourse(studentId, courseCode);

					} else if (command.equalsIgnoreCase("PAC")) {
						// print all active courses
						registry.printActiveCourses();
					} else if (command.equalsIgnoreCase("PCL")) {
						// get course code string
						// print class list (i.e. students) for this course
						String courseCode = commandLine.next();
						registry.printClassList(courseCode);

					} else if (command.equalsIgnoreCase("PGR")) {
						// get course code string
						// print name, id and grade of all students in active course
						String courseCode = commandLine.next();
						registry.printGrades(courseCode);

					} else if (command.equalsIgnoreCase("PSC")) {
						// get student id string
						// print all credit courses of student
						String studentId = commandLine.next();
						registry.printStudentCourses(studentId);

					} else if (command.equalsIgnoreCase("PST")) {
						// get student id string
						// print student transcript
						String studentId = commandLine.next();
						registry.printStudentTranscript(studentId);

					} else if (command.equalsIgnoreCase("SFG")) {
						// set final grade of student
						// get course code, student id, numeric grade
						// use registry to set final grade of this student (see class Registry)

						String courseCode = commandLine.next();
						String studentId = commandLine.next();
						Double grade = commandLine.nextDouble();

						registry.setFinalGrade(courseCode, studentId, grade);
					} else if (command.equalsIgnoreCase("SCN")) {
						// get course code
						// sort list of students in course by name (i.e. alphabetically)
						// see class Registry
						String courseCode = commandLine.next();
						registry.sortCourseByName(courseCode);

					} else if (command.equalsIgnoreCase("SCI")) {
						// get course code
						// sort list of students in course by student id
						// see class Registry
						String courseCode = commandLine.next();
						registry.sortCourseById(courseCode);
					}
					System.out.print("\n>");
					commandLine.close();
				} catch (Exception e) {

				}
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Unable to load the initial dataset " + e.getLocalizedMessage());
		}

	}

	private static boolean isStringOnlyAlphabet(String str) {
		// write method to check if string str contains only alphabetic characters
		boolean isStringOnlyAlphabet = true;

		if (str == null) {
			isStringOnlyAlphabet = false;

		} else {
			for (Character character : str.toCharArray()) {
				if (!(character >= 'A' && character <= 'Z') && !(character >= 'a' && character <= 'z')) {
					isStringOnlyAlphabet = false;
					break;
				}
			}
		}

		return isStringOnlyAlphabet;
	}

	public static boolean isNumeric(String str) {
		// write method to check if string str contains only numeric characters
		boolean isNumeric = true;

		if (str == null) {
			isNumeric = false;
		} else {
			try {
				Long.parseLong(str);
			} catch (NumberFormatException ex) {
				isNumeric = false;
			}
		}

		return isNumeric;
	}

}