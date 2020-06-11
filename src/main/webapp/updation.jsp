<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить данные пользователя</title>
</head>
<body>

Редактировать данные пользователя ${param.name} (текущий статус: ${param.role})

<br/><br/>

<form action="/updateUser" method="post">
    <input type="hidden" name = "id" value="${param.id}">
    <input type="text" name="name" value="${param.name}" placeholder=${param.name}>
    <input type="text" name="age" value="${param.age}" placeholder=${param.age}>
    <br/><br/> установить новый статус:
    <p><input name="role" type="radio" value="user"> user</p>
    <p><input name="role" type="radio" value="admin"> admin</p>
    <input type="submit" value="Обновить">
</form>

</body>
</html>