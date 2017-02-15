package mysite.web.dao;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.Part;

import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Content;
import mysite.web.dto.Course;
import mysite.web.dto.DiscussionBoard;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.exception.InstructorException;

public interface IInstructorDAO {

    boolean addCourse(Course Course) throws InstructorException;

    ArrayList<Course> retrieveCoursesByUsername(String userName) throws InstructorException;

    int getCourseIdByName(String courseName) throws InstructorException;

    ArrayList<Course> retrieveAllCourses() throws InstructorException;

    ArrayList<Course> retrieveCourseByName(String courseName) throws InstructorException;

    ArrayList<String> getContentById(int courseId) throws InstructorException;

    void setContentbyId(int courseId, InputStream inputStream, String filename) throws InstructorException;

    ArrayList<Assignment> getAssignmentById(int courseId) throws InstructorException;

    void setAssignmentById(int courseId, Assignment assignment) throws InstructorException;

    ArrayList<Post> getPostById(int courseId) throws InstructorException;

    ArrayList<Comments> getCommentById(int courseId) throws InstructorException;

    String getInfoById(int courseId) throws InstructorException;

    void setInfoById(int courseId, String information) throws InstructorException;

    void addPost(Post post) throws InstructorException;

    void setComment(Comments comments) throws InstructorException;

    InputStream downloadContentByName(String fileName) throws InstructorException;

    String extractFileName(Part part) throws InstructorException;

    TreeMap<String, GradeCenter> getGrades(int courseId) throws InstructorException;

    TreeMap<String, GradeCenter> getGradesInDescending(int courseId) throws InstructorException;

}
