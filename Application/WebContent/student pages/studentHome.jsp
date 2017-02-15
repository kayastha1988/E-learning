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
		<div align="right">Welcome ${user.getName() }!!</div><br>
		<hr>
		<br>
		<table border="0">
			<c:forEach var="course" items="${courseList }">
				<tr>
					<th colspan="2" align="left" style="font-size: 20px"><b>${course.getCourseName()}</th>
				</tr>
				<tr>
					<td>   </td>
					<td align="right"><p><i>${course.getCourseInfo()}</i></p></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a
						href="StudentController?action=enroll&courseId=${course.getCourseId()}">Enroll
							For this course!</a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<center>
		<c:if test="${myCourseList.size()  > '0'}">
		Courses you have already enrolled
				</c:if>
		
		<br>
		<c:forEach var="course" items="${myCourseList}">
		<a href="StudentController?action=showMyCourse&courseId=${course.getCourseId()}">${course.getCourseName() }</a><br>
		</c:forEach>
		
	</center>
</body>
</html>