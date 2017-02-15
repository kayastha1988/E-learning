<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <center>
            <form action="LoginController?action=login" method="post">
                <table>
                    <tr>
                        <td>User Name
                        <td><input type="text" placeholder="Enter Username"
                                   name="username" required></td>
                    </tr>
                    <tr>
                        <td>Password
                        <td><input type="password" name="password"
                                   placeholder="Password" required></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login">
                </table>
            </form>
            <a href="LoginController?action=register">Click here to register</a>
        </center>
    </body>
</html>