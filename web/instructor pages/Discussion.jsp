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
            <c:if test="${postlist.size() >= '1' }">
                <form
                    action="InstructorController?action=addComment&postId=${postlist.get(postlist.size()-1).getPostId() }"
                    method="post">
                    <table>
                        <tr>
                            <th colspan="2" align="left" style="font-size: 20px">${postlist.get(postlist.size()-1).getPostInfo() }</th>
                        </tr>
                        <c:forEach var="comment" items="${commentList}">
                            <tr>
                                <td style="color: grey">${ comment.getUserName()}~~</td>
                                <td align="right">${comment.getComment() }</td>
                            </tr>
                        </c:forEach>
                        <tr>

                            <td colspan="2" align="center"><input type="text"
                                                                  name="writeComment" placeholder="Write your comment here"></td>
                        </tr>
                    </table>
                    <input type="submit" value="Add Comment">
                </form>
            </c:if>
            <br> <br> <br>
            <hr>
            Add new post
            <form action="InstructorController?action=addPost" method="post">
                <input type="text" name="postInfo" placeholder="Write new post here">
                <input type="submit" value="Post This">
            </form>
            <br> <br>
            <hr>
            <br> <a href="InstructorController?action=back">Back To
                Course Menu</a> <br> <a href="InstructorController?action=logout">LogOut</a>

        </center>
    </body>
</html>