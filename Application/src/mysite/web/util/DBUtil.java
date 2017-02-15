package mysite.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;

public class DBUtil {

	static Connection con;
	public static Connection getConnect(){
		try {
			String driver= "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3360/mysite";
			String user = "root";
			String pass= "root";
			Class.forName(driver);
			con= DriverManager.getConnection(url, user, pass);
		}catch (SQLException sqle) 
		{
		    System.out.println("SQL Exception thrown: " + sqle);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
}
