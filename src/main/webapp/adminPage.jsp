<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Home page</title>
</head>
<body>
<br>

<form action = "addition.jsp">
    <input type="submit" value="Добавить нового пользователя">
</form>
<br>

<table border="2">
    <tr>
        <td>ID</td>
        <td>Имя</td>
        <td>Возраст</td>
        <td>Статус</td>
        <td>Действия</td>
    </tr>
    <c:forEach items="${users}" var = "user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getAge()}</td>
            <td>${user.getRole()}</td>
            <td>
                <form action = "/updation.jsp" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="age" value="${user.getAge()}">
                    <input type="hidden" name="role" value="${user.getRole()}">
                    <input type="submit" value="Изменить" style="float:left">
                </form>
                <form action="/deleteUser" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Удалить" style="float:left">
                </form></td>
        </tr>
    </c:forEach>
</table>

<br/>
<p><a href="http://localhost:8080/">Вернуться на страницу авторизации</a></p>

</body>
</html>
