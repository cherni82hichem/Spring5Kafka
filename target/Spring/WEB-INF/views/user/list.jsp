<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>User List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href="<c:url value='/users/edit/${user.id}'/>">Edit</a>
                    |
                    <a href="<c:url value='/users/delete/${user.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="<c:url value='/users/add'/>">Add User</a>
</body>
</html>