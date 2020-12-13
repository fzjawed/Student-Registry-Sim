//Student Name: Fatima Jawed 
public class CreditCourse extends Course {

	private String semester;
	private Double grade;
	private boolean isActive;

	public CreditCourse(String name, String code, String descr, String fmt, String semester, Double grade) {
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
		this.isActive = true;
	}

	public boolean getActive() {
		return isActive;
	}

	public void setActive() {
		isActive = true;
	}

	public void setInactive() {
		isActive = false;
	}

	public String getSemester() {
		return semester;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String displayGrade() {
		return "[" + "name=" + getName() + ", code=" + getCode() + ", format=" + getFormat() + ", description="
				+ getDescription() + ", info=" + getInfo() + ", semester=" + semester + ", grade="
				+ convertNumericGrade(grade) + ", isActive=" + isActive + "]";
	}

}