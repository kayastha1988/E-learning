package mysite.web.service;

import mysite.web.dto.User;
import mysite.web.exception.InstructorException;

public interface IUserService {
	User getUser(String userName) throws InstructorException;
	void registerUser(User userbean) throws InstructorException;
}
