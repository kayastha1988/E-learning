package mysite.web.dto;

public class Assignment {

	private int courseId;
	private String question;
	private String options;
	private String answer;
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Assignment [courseId=" + courseId + ", question=" + question + ", options=" + options + ", answer="
				+ answer + "]";
	}
	
}
