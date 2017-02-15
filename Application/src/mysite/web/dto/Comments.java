package mysite.web.dto;



public class Comments {

	private int postId;
	private String userName;
	private String comment;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comments [postId=" + postId + ", userName=" + userName + ", comment=" + comment + "]";
	}
	
	
	
}
