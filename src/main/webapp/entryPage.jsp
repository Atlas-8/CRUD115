<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница входа</title>
</head>
<body>

Добро пожаловать, аноним
<br/><br/>

<form action="/validate" method="get">
    <input type="text" name="name" placeholder="вводим логин">
    <input type="text" name="age" placeholder="укажите возраст">
    <input type="submit" value="Войти">
</form>

</body>
</html>