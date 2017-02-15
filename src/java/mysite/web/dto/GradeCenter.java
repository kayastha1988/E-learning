package mysite.web.dto;

public class GradeCenter {

	private int courseId;
	private String userName;
	private int marks;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "GradeCenter [courseId=" + courseId + ", userName=" + userName
				+ ", marks=" + marks + "]";
	}
	
	
}
