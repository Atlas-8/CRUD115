<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница входа</title>
</head>
<body>

Добро пожаловать, аноним
<br/><br/>
<form action="/CRUD_war/admin" method="get">
    <input type="text" name="name" value="${param.name}" placeholder="оставьте это поле пустым"<%--${param.name}--%>>
    <input type="submit" value="Войти">
</form>

</body>
</html>