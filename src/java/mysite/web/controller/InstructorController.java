package mysite.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Content;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.dto.User;
import mysite.web.exception.InstructorException;
import mysite.web.service.IInstructorService;
import mysite.web.service.InstructorServiceImpl;

/**
 * Servlet implementation class InstructorController
 */
@WebServlet("/InstructorController")
@MultipartConfig(maxFileSize = 16177215)
public class InstructorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorController() {
        super();
        // TODO Auto-generated constructor stub
    }

    Course course = new Course();
    IInstructorService instructorService = new InstructorServiceImpl();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action.equals("addCourse")) {
            request.getRequestDispatcher("/instructor pages/addCourse.jsp").forward(
                    request, response);
        }

        if (action.equals("courseMenu")) {
            String courseName = request.getParameter("courseName");
            try {
                int courseId = instructorService.getCourseIdByName(courseName);
                session.setAttribute("courseId", courseId);
                request.getRequestDispatcher("/instructor pages/CourseMenu.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/pages/Error.jsp").forward(request, response);
            }

        }
        if (action.equals("discussion")) {
            int courseId = (Integer) session.getAttribute("courseId");
            try {
                ArrayList<Comments> commentList = new ArrayList<Comments>();
                ArrayList<Post> postList = instructorService
                        .getPostById(courseId);

                session.setAttribute("postlist", postList);
                /* for (int i = 0; i < postList.size(); i++) { */
                if (!postList.isEmpty()) {
                    commentList = instructorService.getCommentById(postList.get(postList.size() - 1).getPostId());
                }
                session.setAttribute("commentList", commentList);

                request.getRequestDispatcher("/instructor pages/Discussion.jsp").forward(
                        request, response);

            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }
        if (action.equals("information")) {
            String courseName = request.getParameter("courseName");
            session.setAttribute("courseName", courseName);
            int courseId = (Integer) session.getAttribute("courseId");
            try {
                String information = instructorService.getInfoById(courseId);
                session.setAttribute("information", information);
                request.getRequestDispatcher("/instructor pages/showInformation.jsp")
                        .forward(request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/pages/Error.jsp").forward(request, response);
            }

        }
        if (action.equals("Assignments")) {
            int courseId = (Integer) session.getAttribute("courseId");
            try {
                ArrayList<Assignment> asslist = instructorService
                        .getAssignmentById(courseId);
                session.setAttribute("asslist", asslist);
                request.getRequestDispatcher("/instructor pages/showAssignments.jsp")
                        .forward(request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/pages/Error.jsp").forward(request, response);
            }

        }
        if (action.equals("content")) {
            int courseId = (Integer) session.getAttribute("courseId");
            try {
                ArrayList<String> files = instructorService
                        .getContentById(courseId);
                session.setAttribute("files", files);
                request.getRequestDispatcher("/instructor pages/Content.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }

        }

        if (action.equals("backToCoursePage")) {
            User user = (User) session.getAttribute("user");
            try {
                ArrayList<Course> courseList = instructorService
                        .retrieveCoursesByUsername(user.getUserName());
                session.setAttribute("courseList", courseList);
                request.getRequestDispatcher("/instructor pages/instructorHome.jsp")
                        .forward(request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("back")) {
            request.getRequestDispatcher("/instructor pages/CourseMenu.jsp").forward(
                    request, response);
        }

        /*
         * if (action.equals("addPost")) { Post post = new Post(); int courseId
         * = (Integer) session.getAttribute("courseId"); String userName =
         * (String) session.getAttribute("user"); String postInfo =
         * request.getParameter("postInfo");
         * instructorService.setDisscuisionById(courseId, postInfo, userName); }
         */
        if (action.equals("downloadContent")) {
            String name = request.getParameter("name");
            try {
                InputStream inputStream = instructorService
                        .downloadContentByName(name);
                session.setAttribute("inputStream", inputStream);
                ServletContext context = getServletContext();

                // sets MIME type for the file download
                String mimeType = context.getMimeType(name);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                // response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format(
                        "attachment; filename=\"%s\"", name);
                response.setHeader(headerKey, headerValue);

                // writes the file to the client
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
                request.getRequestDispatcher("/instructor pages/showContent.jsp").forward(
                        request, response);

            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("getGrades") || action.equals("sortInAsc")) {
            try {
                System.out.println("in asc");
                int courseId = (int) session.getAttribute("courseId");
                TreeMap<String, GradeCenter> gradeMap = instructorService
                        .getGrades(courseId);
                session.setAttribute("gradeMap", gradeMap);
                request.getRequestDispatcher("/instructor pages/Grades.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("sortInDesc")) {
            try {
                System.out.println("in desc");
                int courseId = (int) session.getAttribute("courseId");
                TreeMap<String, GradeCenter> gradeMap = instructorService
                        .getGradesInDescending(courseId);
                System.out.println(gradeMap);
                session.setAttribute("gradeMap", gradeMap);
                request.getRequestDispatcher("/instructor pages/Grades.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("logout")) {
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request,
                    response);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String courseName = request.getParameter("courseName");

        if (action.equals("addCourseDetails")) {

            boolean flag = false;

            course.setCourseName(request.getParameter("courseName"));
            course.setCourseInfo(request.getParameter("courseInfo"));
            User user = (User) session.getAttribute("user");
            course.setUserName(user.getUserName());
            try {
                flag = instructorService.addCourse(course);
                if (flag == true) {
                    ArrayList<Course> courseList = instructorService
                            .retrieveCoursesByUsername(user.getUserName());
                    session.setAttribute("courseList", courseList);
                    request.getRequestDispatcher("/instructor pages/instructorHome.jsp")
                            .forward(request, response);
                }
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }

        }

        if (action.equals("addQuestions")) {

            try {
                int courseId = (Integer) session.getAttribute("courseId");
                Assignment assignment = new Assignment();
                assignment.setCourseId(courseId);
                assignment.setQuestion(request.getParameter("question1"));
                assignment.setOptions(request.getParameter("1opt1") + ","
                        + request.getParameter("1opt2") + ","
                        + request.getParameter("1opt3") + ","
                        + request.getParameter("1opt4"));
                assignment.setAnswer(request.getParameter("answer1"));
                instructorService.setAssignmentById(courseId, assignment);
                assignment = new Assignment();
                assignment.setCourseId(courseId);
                assignment.setQuestion(request.getParameter("question2"));
                assignment.setOptions(request.getParameter("2opt1") + ","
                        + request.getParameter("2opt2") + ","
                        + request.getParameter("2opt3") + ","
                        + request.getParameter("2opt4"));
                assignment.setAnswer(request.getParameter("answer2"));
                instructorService.setAssignmentById(courseId, assignment);
                ArrayList<Assignment> asslist = instructorService
                        .getAssignmentById(courseId);
                session.setAttribute("asslist", asslist);
                request.getRequestDispatcher("/instructor pages/showAssignments.jsp")
                        .forward(request, response);

            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }
        if (action.equals("uploadFile")) {
            int courseId = (Integer) session.getAttribute("courseId");

            InputStream inputStream = null;

            Part filePart = request.getPart("photo");
            inputStream = filePart.getInputStream();
            try {
                String fileName = instructorService.extractFileName(filePart);
                instructorService.setContentbyId(courseId, inputStream,
                        fileName);
                ArrayList<String> files = instructorService
                        .getContentById(courseId);
                session.setAttribute("files", files);
                request.getRequestDispatcher("/instructor pages/Content.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("addComment")) {
            // ArrayList<Comments> commentList =
            // (ArrayList<Comments>)session.getAttribute("commentList");
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

                request.getRequestDispatcher("/instructor pages/Discussion.jsp").forward(
                        request, response);
            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

        if (action.equals("addPost")) {
            Post post = new Post();
            int courseId = (int) session.getAttribute("courseId");
            post.setCourseId(courseId);
            post.setPostInfo(request.getParameter("postInfo"));
            User user = (User) session.getAttribute("user");
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

                request.getRequestDispatcher("/instructor pages/Discussion.jsp").forward(
                        request, response);

            } catch (InstructorException e) {
                // TODO Auto-generated catch block
                session.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/instructor pages/Error.jsp").forward(request, response);
            }
        }

    }

}
