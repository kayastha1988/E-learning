package mysite.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.dto.User;
import mysite.web.exception.InstructorException;
import mysite.web.service.IInstructorService;
import mysite.web.service.IStudentService;
import mysite.web.service.InstructorServiceImpl;
import mysite.web.service.StudentServiceImpl;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	IStudentService studentService;
	IInstructorService instructorService = new InstructorServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		studentService = new StudentServiceImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		session = request.getSession();

		if (action.equals("enroll")) {

			try {
				GradeCenter gradeCenter = new GradeCenter();
				String cId = request.getParameter("courseId");
				int courseId = Integer.parseInt(cId);
				gradeCenter.setCourseId(courseId);
				User user = (User) session.getAttribute("user");
				gradeCenter.setUserName(user.getUserName());
				studentService.enrollCourse(gradeCenter);
				session.setAttribute("courseId", courseId);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}
			request.getRequestDispatcher("/student pages/CourseMenu.jsp")
					.forward(request, response);
		}

		if (action.equals("content")) {
			int courseId = (int) session.getAttribute("courseId");
			try {
				ArrayList<String> files = instructorService
						.getContentById(courseId);
				session.setAttribute("files", files);
				request.getRequestDispatcher("/student pages/Content.jsp")
						.forward(request, response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}

		}

		if (action.equals("showMyCourse")) {
			String cId = request.getParameter("courseId");
			int courseId = Integer.parseInt(cId);
			session.setAttribute("courseId", courseId);
			request.getRequestDispatcher("/student pages/CourseMenu.jsp")
					.forward(request, response);

		}

		if (action.equals("Assignments")) {
			int courseId = (Integer) session.getAttribute("courseId");
			try {
				ArrayList<Assignment> asslist = instructorService
						.getAssignmentById(courseId);
				session.setAttribute("asslist", asslist);
				request.getRequestDispatcher(
						"/student pages/showAssignments.jsp").forward(request,
						response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}

		}

		if (action.equals("information")) {
			String courseName = request.getParameter("courseName");
			session.setAttribute("courseName", courseName);
			int courseId = (Integer) session.getAttribute("courseId");
			try {
				String information = instructorService.getInfoById(courseId);
				session.setAttribute("information", information);
				request.getRequestDispatcher(
						"/student pages/showInformation.jsp").forward(request,
						response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}

		}

		if (action.equals("discussion")) {
			int courseId = (Integer) session.getAttribute("courseId");
			try {
				ArrayList<Comments> commentList = new ArrayList<Comments>();
				ArrayList<Post> postList = instructorService
						.getPostById(courseId);
				session.setAttribute("postlist", postList);
				System.out.println(postList.size());
				if(postList.size()>0)
				commentList = instructorService.getCommentById(postList.get(
						postList.size()-1).getPostId());
				session.setAttribute("commentList", commentList);

				request.getRequestDispatcher("/student pages/Discussion.jsp")
						.forward(request, response);

			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}
		}

		if (action.equals("showGrades")) {
			try {
				User user = (User) session.getAttribute("user");
				HashMap<String, GradeCenter> gradeMap = studentService
						.getGrades(user.getUserName());
				session.setAttribute("gradeMap", gradeMap);
				request.getRequestDispatcher("/student pages/Grades.jsp")
						.forward(request, response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}

		}

		if (action.equals("back")) {
			request.getRequestDispatcher("/student pages/CourseMenu.jsp")
					.forward(request, response);

		}

		if (action.equals("backToCoursePage")){
			User user = (User)session.getAttribute("user");
			
			ArrayList<Course> courseList;
			try {
				courseList = studentService.getCourselist(user.getUserName());
				session.setAttribute("courseList", courseList);
				ArrayList<Course> myCourseList= studentService.myCourseList(user.getUserName());
				session.setAttribute("myCourseList", myCourseList);

				request.getRequestDispatcher("/student pages/studentHome.jsp").forward(request, response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}
			
		}
		if (action.equals("logout")) {
			session.invalidate();
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		session = request.getSession();

		if (action.equals("submitAssignment")) {
			System.out.println("in submit assignment");
			int marks ;
			String option1 = request.getParameter("option1");
			String option2 = request.getParameter("option2");
			ArrayList<Assignment> asslist = (ArrayList<Assignment>) session
					.getAttribute("asslist");
			if (option1.equals(asslist.get(0).getAnswer())
					&& option2.equals(asslist.get(1).getAnswer())) {
				marks = 20;
			} else if (option1.equals(asslist.get(0).getAnswer())
					|| option2.equals(asslist.get(1).getAnswer())) {
				marks = 10;

			} else
				marks = 0;
			User user = (User) session.getAttribute("user");
			int courseId = (int) session.getAttribute("courseId");
			GradeCenter gradeCenter = new GradeCenter();
			gradeCenter.setCourseId(courseId);
			gradeCenter.setUserName(user.getUserName());
			gradeCenter.setMarks(marks);
			try {
				int marksFromDb = studentService.assignGrade(gradeCenter);
				session.setAttribute("grade", marksFromDb);
				request.getRequestDispatcher(
						"/student pages/assignmentGrade.jsp").forward(request,
						response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/pages/Error.jsp").forward(
						request, response);
			}
		}
		
		
		if (action.equals("addComment")) {
			// ArrayList<Comments> commentList =
			// (ArrayList<Comments>)session.getAttribute("commentList");
			System.out.println("in std add comment");
			String pId = request.getParameter("postId");
			int courseId = (int) session.getAttribute("courseId");
			User user = (User) session.getAttribute("user");
			Comments comments = new Comments();
			comments.setPostId(Integer.parseInt(pId));
			comments.setUserName(user.getUserName());
			comments.setComment(request.getParameter("writeComment"));
			try {
				instructorService.setComment(comments);
				ArrayList<Comments> commentList = new ArrayList<Comments>();
				ArrayList<Post> postList = instructorService
						.getPostById(courseId);

				session.setAttribute("postlist", postList);
				/* for (int i = 0; i < postList.size(); i++) { */
				commentList = instructorService.getCommentById(postList.get(
						postList.size() - 1).getPostId());
				session.setAttribute("commentList", commentList);

				request.getRequestDispatcher("/student pages/Discussion.jsp").forward(
						request, response);
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/student pages/Error.jsp").forward(request, response);
			}
		}
		
		if(action.equals("addPost")){
			Post post = new Post();
			int courseId = (int)session.getAttribute("courseId");
			post.setCourseId(courseId);
			post.setPostInfo(request.getParameter("postInfo"));
			User user = (User)session.getAttribute("user");
			post.setUserName(user.getUserName());
			try {
				instructorService.addPost(post);
				ArrayList<Comments> commentList = new ArrayList<Comments>();
				ArrayList<Post> postList = instructorService
						.getPostById(courseId);

				session.setAttribute("postlist", postList);
				/* for (int i = 0; i < postList.size(); i++) { */
				commentList = instructorService.getCommentById(postList.get(
						postList.size() - 1).getPostId());
				session.setAttribute("commentList", commentList);

				request.getRequestDispatcher("/student pages/Discussion.jsp").forward(
						request, response);

				
			} catch (InstructorException e) {
				// TODO Auto-generated catch block
				session.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/student pages/Error.jsp").forward(request, response);
			}
		}
	}

}
