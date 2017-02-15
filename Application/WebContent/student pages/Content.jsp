<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<c:forEach var="imap" items="${files}">
			<a href="InstructorController?action=downloadContent&name=${imap}">${imap}<br></a>
		</c:forEach>
		<br>
		<br> <br>
		<br>
		<hr>
		<br> <a href="StudentController?action=back">Back To
			Course Menu</a> <br>
		<a href="StudentController?action=logout">Log Out</a>


	</center>

</body>
</html>