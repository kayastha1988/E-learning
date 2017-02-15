package mysite.web.service;

import java.util.ArrayList;
import java.util.HashMap;

import mysite.web.dao.IStudentDAO;
import mysite.web.dao.StudentDAOImpl;
import mysite.web.dto.Assignment;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.exception.InstructorException;

public class StudentServiceImpl implements IStudentService{

	IStudentDAO studentDao;
	
	public StudentServiceImpl() {
		// TODO Auto-generated constructor stub
		studentDao = new StudentDAOImpl();
	}
	@Override
	public void enrollCourse(GradeCenter gradeCenter)
			throws InstructorException {
		// TODO Auto-generated method stub
		studentDao.enrollCourse(gradeCenter);
	}

	@Override
	public ArrayList<Course> myCourseList(String username)
			throws InstructorException {
		// TODO Auto-generated method stub
		return studentDao.myCourseList(username);
	}

	@Override
	public ArrayList<Course> getCourselist(String userName)
			throws InstructorException {
		// TODO Auto-generated method stub
		return studentDao.getCourselist(userName);
	}

	@Override
	public int assignGrade(GradeCenter gradeCenter) throws InstructorException {
		// TODO Auto-generated method stub
		return studentDao.assignGrade(gradeCenter);
	}
	@Override
	public HashMap<String, GradeCenter> getGrades(String userName)
			throws InstructorException {
		// TODO Auto-generated method stub
		return studentDao.getGrades(userName);
	}

}
