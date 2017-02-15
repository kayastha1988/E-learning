package mysite.web.dao;


import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Part;

import mysite.web.dao.ValueComparatorForAsc;
import mysite.web.dao.ValueComparatorForDesc;
import mysite.web.dto.Assignment;
import mysite.web.dto.Comments;
import mysite.web.dto.Course;
import mysite.web.dto.GradeCenter;
import mysite.web.dto.Post;
import mysite.web.exception.InstructorException;
import mysite.web.util.DBUtil;

public class InstructorDAOImpl implements IInstructorDAO {
	static Connection con;

	public InstructorDAOImpl() {
		// TODO Auto-generated constructor stub
		con = DBUtil.getConnect();
	}

	@Override
	public boolean addCourse(Course course) throws InstructorException {
		// TODO Auto-generated method stub

		try {
			String sql = "insert into course values(null,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course.getCourseName());
			pstmt.setString(2, course.getCourseInfo());
			pstmt.setString(3, course.getUserName());
			int row = pstmt.executeUpdate();
			if (row == 0)
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to add course into database...Please Try again after Sometime");
		}

		return true;
	}

	@Override
	public ArrayList<Course> retrieveAllCourses() throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "select * from course";
		ArrayList<Course> courselist = new ArrayList<Course>();
		Course course = new Course();
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setCourseInfo(result.getString(3));
				course.setUserName(result.getString(4));
				courselist.add(course);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to retrieve courses from database...Please Try again after Sometime");
		}
		return courselist;
	}

	@Override
	public ArrayList<Course> retrieveCourseByName(String courseName)
			throws InstructorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getContentById(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "Select filename from contents where courseId = ?";
		ArrayList<String> files = new ArrayList<String>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				files.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			throw new InstructorException(
					"Unable to get the content from database...Please Try again after Sometime");
		}

		return files;
	}

	@Override
	public void setContentbyId(int courseId, InputStream inputStream,
			String filename) throws InstructorException {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO contents (courseId,content,filename) values(?,?,?)";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, courseId);

			// statement.setBlob(2, inputStream);
			statement.setBinaryStream(2, inputStream);
			statement.setString(3, filename);
			int row = statement.executeUpdate();
			if (row == 0) {
				throw new InstructorException(
						"Unable to upload the content to database...Please Try again after Sometime");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to upload the content to database...Please Try again after Sometime");
		}

	}

	@Override
	public ArrayList<Assignment> getAssignmentById(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		ArrayList<Assignment> asslist = new ArrayList<Assignment>();
		Assignment assignment = new Assignment();
		String sql = "select * from assignment where courseId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				assignment = new Assignment();
				assignment.setCourseId(courseId);
				assignment.setQuestion(result.getString(2));
				assignment.setOptions(result.getString(3));
				assignment.setAnswer(result.getString(4));
				asslist.add(assignment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to get assignments from database...Please Try again after Sometime");
		}

		return asslist;
	}

	@Override
	public void setAssignmentById(int courseId, Assignment assignment)
			throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "insert into assignment values(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			pstmt.setString(2, assignment.getQuestion());
			pstmt.setString(3, assignment.getOptions());
			pstmt.setString(4, assignment.getAnswer());
			int row = pstmt.executeUpdate();
			if (row <= 0)
				throw new InstructorException(
						"Not able add Assignment into database....Please try again after Sometime");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Not able add Assignment into database....Please try again after Sometime");
		}

	}

	@Override
	public ArrayList<Post> getPostById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "select * from post where courseid= ?";
		PreparedStatement pstmt;
		ArrayList<Post> postList = new ArrayList<Post>();
		Post post = new Post();
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				post = new Post();
				post.setCourseId(courseId);
				post.setPostId(result.getInt(1));
				post.setPostInfo(result.getString(2));
				post.setUserName(result.getString(3));
				postList.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to load Posts from database....Please try again after Sometime");

		}

		return postList;
	}

	@Override
	public String getInfoById(int courseId) throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "select courseinfo from course where courseid = ? ";
		String info = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				info = result.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to get Information from database....Please try again after Sometime");

		}
		return info;
	}

	@Override
	public void setInfoById(int courseId, String information)
			throws InstructorException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Course> retrieveCoursesByUsername(String userName)
			throws InstructorException {
		// TODO Auto-generated method stub
		ArrayList<Course> courseList = new ArrayList<Course>();
		Course course = null;
		String sql = "select * from course where userName = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				course = new Course();
				course.setCourseId(result.getInt(1));
				course.setCourseName(result.getString(2));
				course.setCourseInfo(result.getString(3));
				course.setUserName(userName);
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to load your courses from database....Please try again after Sometime");

		}
		return courseList;
	}

	@Override
	public int getCourseIdByName(String courseName) throws InstructorException {
		// TODO Auto-generated method stub
		int courseId = 0;
		String sql = "Select courseId from course where courseName = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseName);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				courseId = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to retrieve courseId from database....Please try again after Sometime");
		}
		return courseId;
	}

	@Override
	public ArrayList<Comments> getCommentById(int postId)
			throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "select * from comments where postId = ?";
		ArrayList<Comments> commentList = new ArrayList<Comments>();
		Comments comments;

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				comments = new Comments();
				comments.setPostId(postId);
				comments.setComment(result.getString(3));
				comments.setUserName(result.getString(2));
				commentList.add(comments);
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to load comments from database....Please try again after Sometime");

		}

		return commentList;
	}

	@Override
	public InputStream downloadContentByName(String fileName)
			throws InstructorException {
		// TODO Auto-generated method stub
		InputStream inputStream = null;
		try {
			String sql = "select * from contents where filename = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileName);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Blob blob = result.getBlob(2);
				inputStream = blob.getBinaryStream();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to download content from database....Please try again after Sometime");
		}

		return inputStream;
	}

	@Override
	public String extractFileName(Part part) throws InstructorException {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				String clientFileName = s.substring(s.indexOf("=") + 2,
						s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

	@Override
	public void setComment(Comments comments) throws InstructorException {
		// TODO Auto-generated method stub
		try {
			String sql = "Insert into comments values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comments.getPostId());
			pstmt.setString(2, comments.getUserName());
			pstmt.setString(3, comments.getComment());
			int row = pstmt.executeUpdate();
			if (row == 0)
				throw new InstructorException(
						"Unable to comment on this post...Please try again later");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to comment on this post...Please try again later");
		}

	}

	@Override
	public void addPost(Post post) throws InstructorException {
		// TODO Auto-generated method stub
		String sql = "insert into post values(null,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, post.getPostInfo());
			pstmt.setString(2, post.getUserName());
			pstmt.setInt(3, post.getCourseId());
			int row = pstmt.executeUpdate();
			if (row == 0)
				throw new InstructorException(
						"Cannot add your post...Please try again after sometime!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Cannot add your post...Please try again after sometime!!");
		}

	}

	@Override
	public TreeMap<String, GradeCenter> getGrades(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		HashMap<String, GradeCenter> gradeMap = new HashMap<String, GradeCenter>();
		ValueComparatorForAsc bvc = new ValueComparatorForAsc(gradeMap);
		TreeMap<String, GradeCenter> sorted_map = new TreeMap<String, GradeCenter>(
				bvc);
		GradeCenter gradeCenter = new GradeCenter();
		try {
			String sql = "select u.name,g.courseId,g.userName,g.marks from user u,gradecenter g where g.userName = u.userName AND g.courseId = ?";
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				gradeCenter = new GradeCenter();
				gradeCenter.setCourseId(result.getInt(2));
				gradeCenter.setUserName(result.getString(3));
				gradeCenter.setMarks(result.getInt(4));
				gradeMap.put(result.getString(1), gradeCenter);
			}
			sorted_map.putAll(gradeMap);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to load the grades from database....Please try again after Sometime");
		}
		return sorted_map;
	}

	@Override
	public TreeMap<String, GradeCenter> getGradesInDescending(int courseId)
			throws InstructorException {
		// TODO Auto-generated method stub
		HashMap<String, GradeCenter> gradeMap = new HashMap<String, GradeCenter>();
		ValueComparatorForDesc bvc = new ValueComparatorForDesc(gradeMap);
		TreeMap<String, GradeCenter> sorted_map = new TreeMap<String, GradeCenter>(
				bvc);

		System.out.println(gradeMap.size());
		GradeCenter gradeCenter = new GradeCenter();
		try {
			String sql = "select u.name,g.courseId,g.userName,g.marks from user u,gradecenter g where g.userName = u.userName AND g.courseId = ? order by g.marks desc";
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				gradeCenter = new GradeCenter();
				gradeCenter.setCourseId(result.getInt(2));
				gradeCenter.setUserName(result.getString(3));
				gradeCenter.setMarks(result.getInt(4));
				gradeMap.put(result.getString(1), gradeCenter);
			}
			sorted_map.putAll(gradeMap);

			System.out.println(gradeMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new InstructorException(
					"Unable to load the grades from database....Please try again after Sometime");
		}
		return sorted_map;
	}

}

/*
 * public class Testing { public static void main(String[] args) {
 * HashMap<String, Double> map = new HashMap<String, Double>(); ValueComparator
 * bvc = new ValueComparator(map); TreeMap<String, Double> sorted_map = new
 * TreeMap<String, Double>(bvc);
 * 
 * map.put("A", 99.5); map.put("B", 67.4); map.put("C", 67.4); map.put("D",
 * 67.3);
 * 
 * System.out.println("unsorted map: " + map); sorted_map.putAll(map);
 * System.out.println("results: " + sorted_map); } }
 */
class ValueComparatorForDesc implements Comparator<String> {
	Map<String, GradeCenter> base;

	public ValueComparatorForDesc(Map<String, GradeCenter> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	public int compare(String a, String b) {
		if (base.get(a).getMarks() >= base.get(b).getMarks()) {
			return -1;
		} else {
			return 1;
		}

	}
}

class ValueComparatorForAsc implements Comparator<String> {
	Map<String, GradeCenter> base;

	public ValueComparatorForAsc(Map<String, GradeCenter> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	public int compare(String a, String b) {
		if (base.get(a).getMarks() >= base.get(b).getMarks()) {
			return 1;
		} else {
			return -1;
		}

	}
}