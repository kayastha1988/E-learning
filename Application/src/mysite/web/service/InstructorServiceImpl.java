package mysite.web.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.Part;

import mysite.web.dao.IInstructorDAO;
import mysite.web.dao.InstructorDAOImpl;
import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.exception.InstructorException;

public class InstructorServiceImpl implements IInstructorService{
	IInstructorDAO instructordao = new InstructorDAOImpl();
	@Override
	public boolean addCourse(Course course) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.addCourse(course);
	}
	@Override
	public ArrayList<Course> retrieveAllCourses() throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.retrieveAllCourses();
	}
	@Override
	public ArrayList<Course> retrieveCourseByName(String courseName) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.retrieveCourseByName(courseName);
	}
	@Override
	public ArrayList<String> getContentById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getContentById(courseId);
	}
	@Override
	public void setContentbyId(int courseId,InputStream inputStream ,String filename) throws InstructorException {
		// TODO Auto-generated method stub
		instructordao.setContentbyId(courseId, inputStream,filename);
	}
	@Override
	public ArrayList<Assignment> getAssignmentById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getAssignmentById(courseId);
	}
	@Override
	public void setAssignmentById(int courseId, Assignment assignment) throws InstructorException {
		// TODO Auto-generated method stub
		instructordao.setAssignmentById(courseId, assignment);
	}
	@Override
	public ArrayList<Post> getPostById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getPostById(courseId);
	}
	
	@Override
	public String getInfoById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getInfoById(courseId);
	}
	@Override
	public void setInfoById(int courseId, String information) throws InstructorException {
		// TODO Auto-generated method stub
		instructordao.setInfoById(courseId, information);
	}
	@Override
	public ArrayList<Course> retrieveCoursesByUsername(String userName) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.retrieveCoursesByUsername(userName);
	}
	@Override
	public int getCourseIdByName(String courseName) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getCourseIdByName(courseName);
	}
	@Override
	public ArrayList<Comments> getCommentById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getCommentById(courseId);
	}
	@Override
	public InputStream downloadContentByName(String fileName) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.downloadContentByName(fileName);
	}
	@Override
	public String extractFileName(Part part) throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.extractFileName(part);
	}
	@Override
	public TreeMap<String, GradeCenter> getGrades(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getGrades(courseId);
	}
	@Override
	public void setComment(Comments comments) throws InstructorException {
		// TODO Auto-generated method stub
		instructordao.setComment(comments);
	}
	@Override
	public void addPost(Post post) throws InstructorException {
		// TODO Auto-generated method stub
		instructordao.addPost(post);
	}
	@Override
	public TreeMap<String, GradeCenter> getGradesInDescending(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		return instructordao.getGradesInDescending(courseId);
	}

	

}
