
<!DOCTYPE html>

 <head>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <link href="style.css" rel="stylesheet">
</head>
<body>

 <ul class="menu-main">
        <li><a href="http://localhost:8080/library">Домашняя страница</a></li>
        <li><a href="http://localhost:8080/library/books" >Книги</a></li>
      <c:if test="${user==NULL}">
        <li><a href="http://localhost:8080/library/book" class="current">Авторизация</a></li>
      </c:if>
      <c:if test="${user!=NULL}">
         <li><a href="http://localhost:8080/library/booksbox">Мои книги</a></li>
             <c:if test="${user.role=='LIBRARIAN'}">
                <li><a href="http://localhost:8080/library/admin">Администрирование</a></li>
             </c:if>
         <li><a href="http://localhost:8080/library/logout">Выход</a></li>
        </c:if>
</ul>

   <c:if test="${forminfo!=NULL}">
     <div class= "info">
         <h2>${forminfo}</h2>
        </div>

  </c:if>
  <div class="frameform">
    <form method="post" action="http://localhost:8080/library/registration" >
     <div class="container">

        <input type="text" placeholder="Введите имя" name="name" required>

        <input type="text" placeholder="Введите фамилию" name="surename" required>

        <input type="text" placeholder="Введите телефон" name="phone" required>

        <input type="text" placeholder="Введите логин" name="login" required>

        <input type="password" placeholder="Введите пароль" name="password" required>
        <button  type="submit" id="log">ЗАРЕГИСТРИРОВАТЬСЯ</button>

     </div>
   </form>
  </div>
 </body>
</html>
