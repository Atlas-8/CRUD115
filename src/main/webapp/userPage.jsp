<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Данные пользователя</title>
</head>
<body>
Добро пожаловать на страницу пользователя <c:out value="${name}" />

<br/><br/>
<p>Ваши регистрационные данные</p>
<br/>
<p>User name: <c:out value="${name}" /></p>
<br/>
<p>User age (password): <c:out value="${age}" /></p>
<br/>
<p>для изменения данных необходимо обратиться к администратору</p>
<br/>
<p><a href="http://localhost:8080/">Вернуться на страницу авторизации</a></p>

</body>
</html>