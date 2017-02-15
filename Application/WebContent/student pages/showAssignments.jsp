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
		<form action="StudentController?action=submitAssignment" method="post">



			<c:choose>
				<c:when test="${asslist.size() == '0'}">
    					</c:when>
				<c:otherwise>
					<table>
						<tr>
							<th>Q1.</th>
							<td>${asslist.get(0).getQuestion() }</td>
						</tr>


						<c:set var="option"
							value="${fn:split(asslist.get(0).getOptions(), ',')}" />
						<tr>
							<th></th>
							<td><input type="radio" name="option1" value="${option[0]}" />
								${option[0]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option1" value="${option[1]}" />
								${option[1]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option1" value="${option[2]}" />
								${option[2]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option1" value="${option[3]}" />
								${option[3]}</td>
						</tr>
						<%-- <tr>
							<th>Answer</th>
							<td>${asslist.get(0).getAnswer() }</td>
						</tr> --%>
						<tr>
							<th>Q2.</th>
							<td>${asslist.get(1).getQuestion() }</td>
						</tr>


						<c:set var="option"
							value="${fn:split(asslist.get(1).getOptions(), ',')}" />
						<tr>
							<th></th>
							<td><input type="radio" name="option2" value="${option[0]}" />
								${option[0]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option2" value="${option[1]}" />
								${option[1]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option2" value="${option[2]}" />
								${option[2]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="option2" value="${option[3]}" />
								${option[3]}</td>
						</tr>
<%-- 
						<tr>
							<th>Answer</th>
							<td>${asslist.get(1).getAnswer() }</td>
						</tr> --%>
					</table>
					<input type="submit" value="Submit Assignment">
				</c:otherwise>
			</c:choose>




		</form>
		<br>
		<br>
		<hr>
		<br> <a href="StudentController?action=back">Back To
			Course Menu</a> <br> <a href="StudentController?action=logout">LogOut</a>

	</center>
</body>
</html>