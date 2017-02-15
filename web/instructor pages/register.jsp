<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

<center>
<h2>Register</h2>
<form action="LoginController?action=register" method = "post" >
<table>
<tr><td>Name <td><input type = "text" name = "name"/></td></tr>
<tr><td>User Name<td><input type="text" name = "username"/></td></tr>
<tr><td>Password <td><input type="password" name = "password"/></td></tr>
<tr><td>Role<td><input type="radio" name = "role" value = "student"/>Student <td><input type = "radio" name ="role" value = "instructor">Instructor</td></tr>
<tr><td><td><input type = "submit" value="Register"></td></tr> 
</table>
</form>
<a href = "LoginController?action=back">Already a Member? Login here...</a>
</center>
</body>
</html>