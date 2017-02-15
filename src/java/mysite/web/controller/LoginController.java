package mysite.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysite.web.dto.Course;
import mysite.web.dto.User;
import mysite.web.exception.InstructorException;
import mysite.web.service.IInstructorService;
import mysite.web.service.IStudentService;
import mysite.web.service.IUserService;
import mysite.web.service.InstructorServiceImpl;
import mysite.web.service.StudentServiceImpl;
import mysite.web.service.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IUserService userservice = new UserServiceImpl();
    IInstructorService instructorService = new InstructorServiceImpl();
    IStudentService studentService = new StudentServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        userservice = new UserServiceImpl();
        instructorService = new InstructorServiceImpl();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    User userbean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = request.getParameter("action");
        if (action.equals("register")) {
            request.getRequestDispatcher("/instructor pages/register.jsp").forward(request, response);
        }
        if (action.equals("back")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action.equals("login")) {
            String userName = request.getParameter("username");

            User user = new User();
            try {
                user = userservice.getUser(userName);
                String password = request.getParameter("password");

                if (password.equals(user.getPassword())) {
                    session.setAttribute("user", user);
                    if (user.getRole().equals("student")) {
                        ArrayList<Course> courseList = studentService.getCourselist(user.getUserName());
                        session.setAttribute("courseList", courseList);
                        ArrayList<Course> myCourseList = studentService.myCourseList(user.getUserName());
                        session.setAttribute("myCourseList", myCourseList);

                        request.getRequestDispatcher("/student pages/studentHome.jsp").forward(request, response);
                    }
                    if (user.getRole().equals("instructor")) {

                        ArrayList<Course> courseList = instructorService.retrieveCoursesByUsername(user.getUserName());
                        session.setAttribute("courseList", courseList);
                        request.getRequestDispatcher("/instructor pages/instructorHome.jsp").forward(request, response);

                    }
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                }

            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }

        } else if (action.equals("register")) {

            userbean = new User();
            userbean.setName(request.getParameter("name"));
            userbean.setUserName(request.getParameter("username"));
            userbean.setPassword(request.getParameter("password"));
            userbean.setRole(request.getParameter("role"));

            try {
                userservice.registerUser(userbean);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

}
