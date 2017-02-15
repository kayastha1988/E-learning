package mysite.web.dto;

public class Post {

	private int postId;
	private String postInfo;
	private String userName;
	private int courseId;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostInfo() {
		return postInfo;
	}
	public void setPostInfo(String postInfo) {
		this.postInfo = postInfo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postInfo=" + postInfo + ", userName=" + userName + ", courseId=" + courseId
				+ "]";
	}
	
	
}
