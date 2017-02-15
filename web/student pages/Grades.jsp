<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Your Progress</h1>
		<table border="1">
			<tr>
				<th>Course Name</th>
				<th>Grade</th>
			</tr>
			<c:forEach var="grade" items="${gradeMap}">
				<tr>
					<td>${ grade.key}</td>
					<td>${grade.value.getMarks()}</td>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<hr>
		<br> <a href="StudentController?action=back">Back To Course
			Menu</a> <br> <a href="StudentController?action=logout">LogOut</a>

	</center>
</body>
</html>