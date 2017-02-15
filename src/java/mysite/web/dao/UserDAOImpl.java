package mysite.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysite.web.dto.User;
import mysite.web.exception.InstructorException;
import mysite.web.util.DBUtil;

public class UserDAOImpl implements IUserDAO {

    static Connection con;

    public UserDAOImpl() {
        // TODO Auto-generated constructor stub
        con = DBUtil.getConnect();
    }

    @Override
    public User getUser(String userName) throws InstructorException {
        // TODO Auto-generated method stub
        User userbean = new User();
        try {

            String sql = "select * from user where username=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {

                userbean.setName(result.getString(1));
                userbean.setUserName(result.getString(2));
                userbean.setPassword(result.getString(3));
                userbean.setRole(result.getString(4));
            }

            if (userbean == null) {
                throw new InstructorException("No  such user, Please Register");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new InstructorException("Unable to load the users...Please try again after Sometime");
        }

        return userbean;
    }

    @Override
    public void registerUser(User userbean) throws InstructorException {
        // TODO Auto-generated method stub

        String sql = "Insert into user values(?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userbean.getName());
            pstmt.setString(2, userbean.getUserName());
            pstmt.setString(3, userbean.getPassword());
            pstmt.setString(4, userbean.getRole());
            int row = pstmt.executeUpdate();
            if (row == 0) {
                throw new InstructorException("Unable to register the users...Please try again after Sometime");

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new InstructorException("Unable to register the users...Please try again after Sometime");
        }

    }

}
