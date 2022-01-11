<!DOCTYPE html>
<head>
    <title>ADD BOOK</title>
</head>
<body>
<h1>Добавление книги</h1>
<p>Добавте данные книги</p>
<form method="post"  action="http://localhost:8080/library/addbook" >
    <input type="text" name="name">NAME BOOK<br/>
    <input type="text" name="author">AUTHOR BOOK<br/>
    <input  name="year">YEAR<br/>
    <input name="number">NUMBER<br/>
    <button type="submit" >ДОБАВИТЬ КНИГУ</button>
</form>
<p> <%= request.getSession().getAttribute("result") %></p>
<ul>
<li><a href="http://localhost:8080/library" >Домашняя страница</a></li>
<li><a href="http://localhost:8080/library/admin" >Страница администрирование</a></li>
</ul>
</body>
</html>