package mysite.web.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.exception.InstructorException;

public interface IStudentDAO {
	void enrollCourse(GradeCenter gradeCenter) throws InstructorException;
	ArrayList<Course> myCourseList(String username) throws InstructorException;
	/*int getCourseId(String courseName) throws InstructorException;
	InputStream downloadContentByName(String fileName)throws InstructorException;
	String getInfoById(int courseId) throws InstructorException;
	void setDisscuisionById(int courseId, String postInfo, String userName);
	ArrayList<Post> getPostById(int courseId) throws InstructorException;
	ArrayList<Comments> getCommentById(int courseId) throws InstructorException; 
	ArrayList<String> getContentById(int courseId) throws InstructorException;*/
	ArrayList<Course> getCourselist(String userName) throws InstructorException;
	int assignGrade (GradeCenter gradeCenter) throws InstructorException;
	HashMap<String, GradeCenter> getGrades(String userName) throws InstructorException;
}
