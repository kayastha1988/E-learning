package mysite.web.dao;

import mysite.web.dto.User;
import mysite.web.exception.InstructorException;

public interface IUserDAO {
	User getUser(String userName) throws InstructorException;
	void registerUser(User userbean) throws InstructorException;
}
