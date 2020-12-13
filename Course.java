//Student Name: Fatima Jawed 
public class Course {
	private String code;
	private String name;
	private String description;
	private String format;

	public Course() {
		this.code = "";
		this.name = "";
		this.description = "";
		this.format = "";
	}

	public Course(String name, String code, String descr, String fmt) {
		this.code = code;
		this.name = name;
		this.description = descr;
		this.format = fmt;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getFormat() {
		return format;
	}

	public String getDescr() {
		return this.description;
	}

	public String getDescription() {
		return code + " - " + name + "\n" + description + "\n" + format;
	}

	public String getInfo() {
		return code + " - " + name;
	}

	// static method to convert numeric score to letter grade string
	// e.g. 91 --> "A+"
	public static String convertNumericGrade(double score) {

		String grade = "F";

		if (score > 95) {
			grade = "A+";
		} else if (score > 90 && score <= 95) {
			grade = "A";
		} else if (score > 85 && score <= 90) {
			grade = "A-";
		} else if (score > 75 && score <= 85) {
			grade = "B+";
		} else if (score > 65 && score <= 75) {
			grade = "B";
		} else if (score > 55 && score <= 65) {
			grade = "C";
		} else if (score > 50 && score <= 55) {
			grade = "D";
		}
		return "Grade " + grade;
	}

}
