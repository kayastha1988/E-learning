package mysite.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import mysite.web.dto.Assignment;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.exception.InstructorException;
import mysite.web.util.DBUtil;

public class StudentDAOImpl implements IStudentDAO{

	static Connection con;
	public StudentDAOImpl() {
		// TODO Auto-generated constructor stub
		con = DBUtil.getConnect();
	}
	
	@Override
	public void enrollCourse(GradeCenter gradeCenter)
			throws InstructorException {
		// TODO Auto-generated method stub
		
		try {
			String sql = "Insert into gradecenter(courseId,userName) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gradeCenter.getCourseId());
			pstmt.setString(2,gradeCenter.getUserName());
			int row = pstmt.executeUpdate();
			if(row==0)
				throw new InstructorException("Unable to enroll for the course right now....Please try again after Sometime");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException("Unable to enroll for the course right now....Please try again after Sometime");		}
		
		
	}

	@Override
	public ArrayList<Course> myCourseList(String username)
			throws InstructorException {
		// TODO Auto-generated method stub
		Course course = new Course();
		ArrayList<Course> myCourseList = new ArrayList<Course>();
		
		try {
			String sql = "Select * from course where courseId IN (select courseId from gradecenter where userName = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				course = new Course();
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setCourseInfo(result.getString(3));
				course.setUserName(result.getString(4));
				myCourseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException("Unable to load your enrolled courses right now....Please try again after Sometime");
			}
		
		return myCourseList;
	}

	@Override
	public ArrayList<Course> getCourselist(String userName)
			throws InstructorException {
		// TODO Auto-generated method stub
ArrayList<Course> courseList = new ArrayList<Course>();
Course course = new Course();
		try {
			String sql = "Select * from course where courseId not in (select courseId from gradecenter where username = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				course = new Course();
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setCourseInfo(result.getString(3));
				course.setUserName(result.getString(4));
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException("Unable to load the course right now....Please try again after Sometime");		}
		
		
		return courseList;
	}

	@Override
	public int assignGrade(GradeCenter gradeCenter) throws InstructorException {
		// TODO Auto-generated method stub
		try{
			String sql = "Update gradecenter set marks = ? where courseId = ? AND userName = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(2, gradeCenter.getCourseId());
			pstmt.setString(3, gradeCenter.getUserName());
			pstmt.setInt(1, gradeCenter.getMarks());
			int row = pstmt.executeUpdate();
			if(row==0)
				throw new InstructorException("123Unable to susbmit the assignment for this course right now....Please try again after Sometime");
			}catch(SQLException e) {
				throw new InstructorException("456Unable to susbmit the assignment for this course right now....Please try again after Sometime");
				}
			return gradeCenter.getMarks();
	}

	@Override
	public HashMap<String, GradeCenter> getGrades(String userName)
			throws InstructorException {
		// TODO Auto-generated method stub
		HashMap<String, GradeCenter> gradeMap = new HashMap<String, GradeCenter>();
		GradeCenter gradeCenter = new GradeCenter();
		String sql = "select c.courseName,g.courseId,g.userName,g.marks from course c,gradecenter g where g.courseId=c.courseId AND g.userName = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				gradeCenter = new GradeCenter();
				gradeCenter.setCourseId(result.getInt(2));
				gradeCenter.setUserName(result.getString(3));
				gradeCenter.setMarks(result.getInt(4));
				gradeMap.put(result.getString(1), gradeCenter);
			}
			if(gradeMap.size()==0){
				throw new InstructorException("You havent given any Assignment test");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException("Unable to load the grades right now.... Please try again after Sometime");

		}
		return gradeMap;
	}

}
