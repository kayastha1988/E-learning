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
		<form action="InstructorController?action=addQuestions" method="post">



			<c:choose>
				<c:when test="${asslist.size() == '0'}">
    	Question 1 : <input type="text" placeholder="Enter question"
						name="question1">
					<br>
    	    	Option 1 : <input type="text" placeholder="Enter Option1"
						name="1opt1">
					<br>
    	    	Option 2 : <input type="text" placeholder="Enter Option2"
						name="1opt2">
					<br>
    	    	Option 3 : <input type="text" placeholder="Enter Option3"
						name="1opt3">
					<br>
    			Option 4 : <input type="text" placeholder="Enter Option4"
						name="1opt4">
					<br>
    	Answer : <input type="text" placeholder="Enter Answer"
						name="answer1">
					<br>
    			
    	Question 2 : <input type="text" placeholder="Enter question"
						name="question2">
					<br>
    	    	Option 1 : <input type="text" placeholder="Enter Option1"
						name="2opt1">
					<br>
    	    	Option 2 : <input type="text" placeholder="Enter Option2"
						name="2opt2">
					<br> 
    	    	Option 3 : <input type="text" placeholder="Enter Option3"
						name="2opt3">
					<br>
    			Option 4 : <input type="text" placeholder="Enter Option4"
						name="2opt4">
					<br>
    	Answer : <input type="text" placeholder="Enter Answer"
						name="answer2">
					<br>

					<input type="submit" value="Add Questions">

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
							<td><input type="radio" name="options" value="${option[0]}" />
								${option[0]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[1]}" />
								${option[1]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[2]}" />
								${option[2]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[3]}" />
								${option[3]}</td>
						</tr>
						<tr>
							<th>Answer</th>
							<td>${asslist.get(0).getAnswer() }</td>
						</tr>
						<tr>
							<th>Q3.</th>
							<td>${asslist.get(1).getQuestion() }</td>
						</tr>


						<c:set var="option"
							value="${fn:split(asslist.get(1).getOptions(), ',')}" />
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[0]}" />
								${option[0]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[1]}" />
								${option[1]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[2]}" />
								${option[2]}</td>
						</tr>
						<tr>
							<th></th>
							<td><input type="radio" name="options" value="${option[3]}" />
								${option[3]}</td>
						</tr>

						<tr>
							<th>Answer</th>
							<td>${asslist.get(1).getAnswer() }</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>




		</form>
		<br>
		<br>
		<hr>
		<br> <a href="InstructorController?action=back">Back To
			Course Menu</a> <br> <a href="InstructorController?action=logout">LogOut</a>

	</center>
</body>
</html>