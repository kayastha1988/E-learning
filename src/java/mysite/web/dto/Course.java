package mysite.web.dto;

public class Course {
	private String courseName;
	private String courseInfo;
	private int courseId;
	private String userName;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}
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
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", courseInfo=" + courseInfo + ", courseId=" + courseId
				+ ", userName=" + userName + "]";
	}
	
}
/*

CREATE TABLE Course (
courseId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  courseName VARCHAR(15) NOT NULL,
  courseInfo VARCHAR(200) NOT NULL,
  userName VARCHAR(15) ,
  FOREIGN KEY (userName) REFERENCES User(userName)
  );


*/