package mysite.web.exception;

public class InstructorException extends Exception{

	String message;
	public InstructorException(String msg){
		message=msg;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
	
}
