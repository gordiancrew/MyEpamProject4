<!DOCTYPE html>

<head>
    <title>DELETE BOOK</title>
</head>

<body>
    <h1>
        Удаление книги
    </h1>
    <p>
        Введите ID книги, которую нужно удалить:
    </p>
    <form method="post" action="http://localhost:8080/library/deletebook">
        <input name="id">ID Книги<br />
        <button type="submit">УДАЛИТЬ</button>
    </form>
    <p>
        <%= request.getSession().getAttribute("result") %>
    </p>
    <ul>
        <li><a href="http://localhost:8080/library">Домашняя страница</a></li>
        <li><a href="http://localhost:8080/library/admin">Страница администрирование</a></li>
    </ul>
</body>
</html>