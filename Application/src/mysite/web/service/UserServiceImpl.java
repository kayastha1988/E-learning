package mysite.web.service;

import mysite.web.dao.IUserDAO;
import mysite.web.dao.UserDAOImpl;
import mysite.web.dto.User;
import mysite.web.exception.InstructorException;

public class UserServiceImpl implements IUserService{
	IUserDAO userdao = new UserDAOImpl();
	@Override
	public User getUser(String userName) throws InstructorException {
		// TODO Auto-generated method stub
		return userdao.getUser(userName);
	}

	@Override
	public void registerUser(User userbean)  throws InstructorException{
		// TODO Auto-generated method stub
		System.out.println("in service");
		userdao.registerUser(userbean);
	}

}
