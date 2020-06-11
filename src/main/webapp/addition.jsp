<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить нового пользователя</title>
</head>
<body>
Addition of user <br><br>
<form action = "/saveUser" method="get">
    <input required type="text" name="name" placeholder="Имя">
    <input required type="text" name="age" placeholder="Возраст">
    <p><input name="role" type="radio" value="user" checked> user</p>
    <p><input name="role" type="radio" value="admin"> admin</p>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>